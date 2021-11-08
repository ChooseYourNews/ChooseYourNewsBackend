package com.revature.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.revature.util.HibernateUtil.getSessionFactory;

@Repository
public class StoryDaoImpl implements StoryDao {
    private static SessionFactory sf = getSessionFactory();

    @Override
    public List<NewsInfo> getStories(int userId) throws IOException {
        try(Session sess = sf.openSession()) {
            RestTemplate restTemplate = new RestTemplate();

            String newsOutletListHql = "select a.newsOutlet from UserOutlet as a where a.user.id = :userId and a.wantToSee = true";

            Query newsOutletListQuery = sess.createQuery(newsOutletListHql);

            newsOutletListQuery.setParameter("userId", userId);

            List<NewsOutlet> newsOutletList = newsOutletListQuery.list();
            Iterator newsOutletListIterator = newsOutletList.iterator();

            String interestListHql = "select a.interest from UserInterest as a join a.interest AS b where a.user.id = :userId";

            Query interestListQuery = sess.createQuery(interestListHql);

            interestListQuery.setParameter("userId", userId);

            List<Interest> interestList = interestListQuery.list();

            Iterator interestListIterator = interestList.iterator();
            ArrayList<NewsInfo> stories = new ArrayList<NewsInfo>();
            while(interestListIterator.hasNext()) {
                Interest interest = (Interest)interestListIterator.next();

                while(newsOutletListIterator.hasNext()) {
                    NewsOutlet newsOutlet = (NewsOutlet)newsOutletListIterator.next();
                    String resultsString = restTemplate.getForObject(newsOutlet.apiPattern, String.class, interest.interestName, newsOutlet.apiKey);
                    ObjectMapper om = new ObjectMapper();
                    JsonNode result = om.readTree(resultsString);

                    stories.add(new NewsInfo(new NewsOutletInfo(newsOutlet.id, newsOutlet.nameOfOrg), result, interest));
                }
            }
            return (List) stories;
        }
    }
}
