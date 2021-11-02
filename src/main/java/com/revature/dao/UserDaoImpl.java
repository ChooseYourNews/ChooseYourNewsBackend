package com.revature.dao;

import com.revature.models.Interest;
import com.revature.models.Profile;
import com.revature.models.User;
import com.revature.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static SessionFactory sf = HibernateUtil.getSessionFactory();

    public Profile getProfile() {

        // Todo: Use actual userId
        String userId = null;

        Session sess = sf.openSession();
        CriteriaBuilder interestListCriteriaBuilder = sess.getCriteriaBuilder();
        CriteriaQuery<Interest> interestListCriteriaQuery = interestListCriteriaBuilder.createQuery(Interest.class);

        Root<Interest> interestListRoot = interestListCriteriaQuery.from(Interest.class);
        interestListCriteriaQuery.select(interestListRoot);

        interestListCriteriaQuery.where(interestListCriteriaBuilder.equal(interestListRoot.get("UserInterest").get("user_id"), userId));
        Query<Interest> interestListQuery = sess.createQuery(interestListCriteriaQuery);
        List<Interest> interestList = interestListQuery.list();

        User user = sess.get(User.class, userId);

        return new Profile(user, interestList);
    }


}
