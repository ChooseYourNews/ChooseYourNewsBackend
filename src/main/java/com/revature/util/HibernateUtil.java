package com.revature.util;

import com.revature.models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null) {

            Properties settings = new Properties();
            settings.put(Environment.DRIVER, "org.postgresql.Driver");
            settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/postgres");
            settings.put(Environment.USER, "postgres");
            settings.put(Environment.PASS, "password");
            settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");

            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.HBM2DDL_AUTO, "validate");
            // create, update, create-drop, validate

            sessionFactory = new Configuration()
                    .setProperties(settings)
                    .addAnnotatedClass(Interest.class)
                    .addAnnotatedClass(UserInterest.class)
                    .addAnnotatedClass(AuthorizationSession.class)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        }
        return sessionFactory;
    }

    public static Session getSession(){
        return getSessionFactory().openSession();
    }
}
