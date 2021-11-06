package com.revature.dao;

import com.revature.models.User;
import com.revature.models.UserInterest;
import com.revature.util.HibernateUtil;
import com.revature.models.Interest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.revature.util.HibernateUtil.getSessionFactory;

@Repository
public class InterestDaoImpl implements InterestDao {

    private static SessionFactory sf = getSessionFactory();

    @Override
    public Interest addInterest(String interest, int userId) {

        try(Session sess = sf.openSession()) {
            User user = sess.get(User.class, userId);
            Transaction tx = sess.beginTransaction();
            int interestId = (int)sess.save(new Interest(interest));
            System.out.println("Inserted Interest: " + new Interest(interestId, interest));
            sess.saveOrUpdate(new UserInterest(user, new Interest(interestId, interest)));
            System.out.println("Inserted UserInterest: " + new UserInterest(user, new Interest(interestId, interest)));
            tx.commit();
            sess.close();
            return new Interest(interestId, interest);
        }
    }

    @Override
    public Interest deleteInterest(Interest interest, int userId) {
        try(Session sess = sf.openSession()) {
            User user = sess.get(User.class, userId);
            Transaction tx = sess.beginTransaction();
            sess.delete(new UserInterest(user, interest));
            System.out.println("Deleted UserInterest: " + new UserInterest(user, interest));
            sess.delete(interest);
            System.out.println("Deleted Interest: " + interest);
            tx.commit();
            return interest;
        }
    }

    @Override
    public List<Interest> getInterests(int userId) {
        try(Session sess = sf.openSession()) {

            String interestListHql = "SELECT a.interest FROM UserInterest AS a JOIN a.interest AS b WHERE a.user.id = :userId";

            Query interestListQuery = sess.createQuery(interestListHql);

            interestListQuery.setParameter("userId", userId);

            List<Interest> interestList = interestListQuery.list();

            return interestList;
        }
    }
}
