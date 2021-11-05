package com.revature.models;

import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.*;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

//@Component
@Entity
@Table(name = "AuthorizationSession", schema = "public")
public class AuthorizationSession implements Serializable {


    public byte[] secret;

    @Id
    public String token;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    public AuthorizationSession() {
        super();
    }

    public AuthorizationSession(String token, User user) throws NoSuchAlgorithmException {
        this.token = token;
        this.user = user;

        // Generate secret key for HmacSHA256
        KeyGenerator kg = KeyGenerator.getInstance("HmacSHA256");
        secret = kg.generateKey().getEncoded();
    }

    public String getJwt() throws NoSuchAlgorithmException, InvalidKeyException {
        Base64.Encoder base64Encoder = Base64.getUrlEncoder();
        String JWTHeader = base64Encoder.encodeToString("{\"alg\":\"HS256\",\"typ\":\"JWT\"}".getBytes(StandardCharsets.UTF_8));
        String JWTPayload = base64Encoder.encodeToString(("{\"token\":\"" + token + "\"}").getBytes(StandardCharsets.UTF_8));

        // Get instance of Mac object implementing HmacSHA256, and
        // initialize it with the above secret key
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret, "HmacSHA256"));
        byte[] result = mac.doFinal((JWTHeader + "." + JWTPayload).getBytes());
        String JWTVerifySignature = base64Encoder.encodeToString(result);

        return JWTHeader + "." + JWTPayload + "." + JWTVerifySignature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorizationSession that = (AuthorizationSession) o;
        return Arrays.equals(secret, that.secret) && Objects.equals(token, that.token) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(token, user);
        result = 31 * result + Arrays.hashCode(secret);
        return result;
    }
}
