package com.revature.dao;

import com.revature.util.HibernateUtil;
import com.revature.models.Interest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class InterestDaoImpl implements InterestDao {

    private static SessionFactory sf = HibernateUtil.getSessionFactory();

    @Override
    public Interest addInterest(String interest) {

        try(Session sess = sf.openSession()) {
            Transaction tx = sess.beginTransaction();
            int id = (int)sess.save(interest);
            System.out.println("Inserted Interest: " + new Interest(id, interest));
            tx.commit();
            sess.close();
            return new Interest(id, interest);
        }
    }

    @Override
    public Interest deleteInterest(Interest interest) {
        try(Session sess = sf.openSession()) {
            Transaction tx = sess.beginTransaction();
            tx.commit();
            sess.delete(interest);
            return interest;
        }
    }
}
