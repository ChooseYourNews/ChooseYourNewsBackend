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
import java.util.List;

import static com.revature.util.HibernateUtil.getSessionFactory;

@Repository
public class UserDaoImpl implements UserDao {
    private static SessionFactory sf = getSessionFactory();

    @Override
    public Profile getProfile() {

        // Todo: Use actual userId
        String userId = null;

        Session sess = sf.openSession();

//        List<Interest> interestList = sess.createCriteria(Interest.class)
//                .add(Restrictions.eq("user_id", userId))
//                .list();


        String hql = "SELECT b.id, b.interestName FROM UserInterest AS a JOIN a.interest AS b WHERE a.user.id = :userId";

        Query query = sess.createQuery(hql);

        query.setParameter("userId", userId);

        List<Interest> interestList = query.list();

//        CriteriaBuilder interestListCriteriaBuilder = sess.getCriteriaBuilder();
//        CriteriaQuery<Interest> interestListCriteriaQuery = interestListCriteriaBuilder.createQuery(Interest.class);
//
//        Root<Interest> interestListRoot = interestListCriteriaQuery.from(Interest.class);
//        interestListCriteriaQuery.select(interestListRoot);
//
//        interestListCriteriaQuery.where(interestListCriteriaBuilder.equal(interestListRoot.get("user_id"), userId));
//        Query<Interest> interestListQuery = sess.createQuery(interestListCriteriaQuery);
//        List<Interest> interestList = interestListQuery.list();

        User user = sess.get(User.class, userId);

        return new Profile(user, interestList);
    }
}

