package com.revature.dao;

import com.revature.models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.revature.util.HibernateUtil.getSessionFactory;

@Repository
public class NewsOutletDaoImpl implements NewsOutletDao {
    private static SessionFactory sf = getSessionFactory();

    public List<UserOutletInfo> getOutlets(int userId) {
        try(Session sess = sf.openSession()) {

            String newsOutletListHql = "from UserOutlet as a where a.user.id = :userId";

            Query newsOutletListQuery = sess.createQuery(newsOutletListHql);

            newsOutletListQuery.setParameter("userId", userId);

            List<UserOutlet> newsOutletList = newsOutletListQuery.list();
            Iterator newsOutletListIterator = newsOutletList.iterator();

            ArrayList<UserOutletInfo> newsOutletInfoList = new ArrayList<UserOutletInfo>();

            while(newsOutletListIterator.hasNext()) {
                UserOutlet newsOutlet = (UserOutlet) newsOutletListIterator.next();
                newsOutletInfoList.add(new UserOutletInfo(new NewsOutletInfo(newsOutlet.newsOutlet.id, newsOutlet.newsOutlet.nameOfOrg), newsOutlet.wantToSee));
            }

            return (List)newsOutletInfoList;
        }
    }

    @Override
    public UserOutletInfo addOrUpdateOutlet(UserOutletInfo userOutletInfo, int userId) {

        try(Session sess = sf.openSession()) {
            User user = sess.get(User.class, userId);
            Transaction tx = sess.beginTransaction();
            sess.saveOrUpdate(new UserOutlet(user, userOutletInfo));
            System.out.println("Inserted UserOutlet: " + new UserOutlet(user, userOutletInfo));
            tx.commit();
            sess.close();
            return userOutletInfo;
        }
    }

    @Override
    public UserOutletInfo deleteOutlet(UserOutletInfo userOutletInfo, int userId) {
        try(Session sess = sf.openSession()) {
            User user = sess.get(User.class, userId);
            Transaction tx = sess.beginTransaction();
            sess.delete(new UserOutlet(user, userOutletInfo));
            System.out.println("Deleted UserOutlet: " + new UserOutlet(user, userOutletInfo));
            tx.commit();
            return userOutletInfo;
        }
    }
}
