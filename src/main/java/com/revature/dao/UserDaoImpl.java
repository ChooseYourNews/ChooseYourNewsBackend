package com.revature.dao;

import com.revature.models.Profile;
import com.revature.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class UserDaoImpl implements UserDao {
    private static SessionFactory sf = HibernateUtil.getSessionFactory();

    public Profile getProfile() {

        // Todo: Use actual userId
        String userId = null;

        Session sess = sf.openSession();

        String hql = "from Location where country = :userId";

        Query query = sess.createQuery(hql);

        query.setParameter("userId", userId);

        return null;
    }
}
