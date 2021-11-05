package com.revature.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.revature.util.HibernateUtil.getSessionFactory;

@Repository
public class AuthorizationSessionDaoImpl implements AuthorizationSessionDao {
    private static SessionFactory sf = getSessionFactory();

    @Override
    public String login(String email, CharSequence password) throws NoSuchAlgorithmException, InvalidKeyException {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Base64.Encoder base64Encoder = Base64.getUrlEncoder();

        try(Session sess = sf.openSession()) {
            String hql = "from User as u where u.email = :email";

            Query query = sess.createQuery(hql);

            query.setParameter("email", email);
            User user = (User)query.getSingleResult();
            if(bCryptPasswordEncoder.matches(password, user.password)) {
                KeyGenerator kg = KeyGenerator.getInstance("HmacSHA256");
                SecretKey tokenKey = kg.generateKey();
                String token = base64Encoder.encodeToString(tokenKey.getEncoded());

                AuthorizationSession authorizationSession = new AuthorizationSession(token, user);

                Transaction tx = sess.beginTransaction();
                sess.save(authorizationSession);
                System.out.println("Inserted Authorization Session: " + authorizationSession);
                tx.commit();
                sess.close();

                return authorizationSession.getJwt();
            }
        }
        return null;
    }

    @Override
    public boolean checkAuthorization(String token) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        Base64.Decoder base64Decoder = Base64.getUrlDecoder();
        Base64.Encoder base64Encoder = Base64.getUrlEncoder();

        String[] split = token.split("\\.");
        String headerBase64 = split[0];
        byte[] header = base64Decoder.decode(headerBase64);
        String payloadBase64 = split[1];
        byte[] payload = base64Decoder.decode(payloadBase64);
        String verifySignatureBase64 = split[2];
        ObjectMapper om = new ObjectMapper();
        AuthorizationJWTHeader authorizationJWTHeader = om.readValue(header, AuthorizationJWTHeader.class);
        AuthorizationJWTPayload authorizationJWTPayload = om.readValue(payload, AuthorizationJWTPayload.class);
        Session sess = sf.openSession();

        String hql = "from AuthorizationSession as a where a.token = :token";

        Query query = sess.createQuery(hql);

        query.setParameter("token", authorizationJWTPayload.token);

        AuthorizationSession authorizationSession = (AuthorizationSession) query.getSingleResult();

        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(authorizationSession.secret, "HmacSHA256"));
        byte[] verifySignatureRecreated = mac.doFinal((headerBase64 + "." + payloadBase64).getBytes());
        String verifySignatureBase64Recreated = base64Encoder.encodeToString(verifySignatureRecreated);
        return verifySignatureBase64.equals(verifySignatureBase64Recreated);
    }

    @Override
    public String register(String email, CharSequence password, String firstName, String lastName) throws NoSuchAlgorithmException, InvalidKeyException {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Base64.Encoder base64Encoder = Base64.getUrlEncoder();
        String bcrypt = bCryptPasswordEncoder.encode(password);
        User user = new User(email, bcrypt, firstName, lastName);

        KeyGenerator kg = KeyGenerator.getInstance("HmacSHA256");
        SecretKey tokenKey = kg.generateKey();
        String token = base64Encoder.encodeToString(tokenKey.getEncoded());

        AuthorizationSession authorizationSession = new AuthorizationSession(token, user);

        try (Session sess = sf.openSession()) {
            Transaction tx = sess.beginTransaction();
            int userId = (int) sess.save(user);
            System.out.println("Inserted User: " + user);
            sess.save(authorizationSession);
            System.out.println("Inserted Authorization Session: " + authorizationSession);
            tx.commit();
        } catch (Exception throwable) {
            throwable.printStackTrace();
        } finally {
            return authorizationSession.getJwt();
        }
    }

    @Override
    public int getUserId(String token) throws IOException {
        Base64.Decoder base64Decoder = Base64.getUrlDecoder();
        Base64.Encoder base64Encoder = Base64.getUrlEncoder();

        String[] split = token.split("\\.");
        String headerBase64 = split[0];
        byte[] header = base64Decoder.decode(headerBase64);
        String payloadBase64 = split[1];
        byte[] payload = base64Decoder.decode(payloadBase64);
        String verifySignatureBase64 = split[2];
        ObjectMapper om = new ObjectMapper();
        AuthorizationJWTHeader authorizationJWTHeader = om.readValue(header, AuthorizationJWTHeader.class);
        AuthorizationJWTPayload authorizationJWTPayload = om.readValue(payload, AuthorizationJWTPayload.class);
        Session sess = sf.openSession();

        String hql = "select u.id from AuthorizationSession as a join a.user as u where a.token = :token";

        Query query = sess.createQuery(hql);

        query.setParameter("token", authorizationJWTPayload.token);

        return (int)query.getSingleResult();
    }
}
