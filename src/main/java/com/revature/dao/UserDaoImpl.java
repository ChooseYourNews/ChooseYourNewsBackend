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
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static com.revature.util.HibernateUtil.getSessionFactory;

@Repository
public class UserDaoImpl implements UserDao {
    private static SessionFactory sf = getSessionFactory();

    @Override
    public Profile getProfile(int userId) {

        Session sess = sf.openSession();

//        List<Interest> interestList = sess.createCriteria(Interest.class)
//                .add(Restrictions.eq("user_id", userId))
//                .list();


        String interestListHql = "select a.interest from UserInterest as a join a.interest as b where a.user.id = :userId";

        Query interestListQuery = sess.createQuery(interestListHql);

        interestListQuery.setParameter("userId", userId);

        List<Interest> interestList = interestListQuery.list();

        String userHql = "from User as u where u.id = :userId";

        Query userQuery = sess.createQuery(userHql);

        userQuery.setParameter("userId", userId);

        User user = (User)userQuery.getSingleResult();

        String newsOutletListHql = "from UserOutlet as uo where uo.user.id = :userId";

        Query newsOutletQuery = sess.createQuery(newsOutletListHql);

        newsOutletQuery.setParameter("userId", userId);

        List<UserOutlet> newsOutletList = (List<UserOutlet>)newsOutletQuery.list();

        ArrayList<UserOutletInfo> newsOutletInfoList = new ArrayList<UserOutletInfo>();

        Iterator newsOutletListIterator = newsOutletList.iterator();

        while(newsOutletListIterator.hasNext()) {
            UserOutlet newsOutlet = (UserOutlet) newsOutletListIterator.next();
            newsOutletInfoList.add(new UserOutletInfo(new NewsOutletInfo(newsOutlet.newsOutlet.id, newsOutlet.newsOutlet.nameOfOrg), newsOutlet.wantToSee));
        }


//        CriteriaBuilder interestListCriteriaBuilder = sess.getCriteriaBuilder();
//        CriteriaQuery<Interest> interestListCriteriaQuery = interestListCriteriaBuilder.createQuery(Interest.class);
//
//        Root<Interest> interestListRoot = interestListCriteriaQuery.from(Interest.class);
//        interestListCriteriaQuery.select(interestListRoot);
//
//        interestListCriteriaQuery.where(interestListCriteriaBuilder.equal(interestListRoot.get("user_id"), userId));
//        Query<Interest> interestListQuery = sess.createQuery(interestListCriteriaQuery);
//        List<Interest> interestList = interestListQuery.list();

        return new Profile(new UserInfo(user.firstName, user.lastName), interestList, newsOutletInfoList);
    }
}

