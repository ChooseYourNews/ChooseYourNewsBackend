package com.revature.services;

import com.revature.dao.*;
import com.revature.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public InterestDao interestDao = new InterestDaoImpl();
    @Autowired
    public UserDao userDao = new UserDaoImpl();
    @Autowired
    public AuthorizationSessionDao authorizationSessionDao = new AuthorizationSessionDaoImpl();
    @Autowired
    public StoryDao storyDao = new StoryDaoImpl();
    @Autowired
    public NewsOutletDao newsOutletDao = new NewsOutletDaoImpl();

    @Override
    public Interest addInterest(Interest interest, int userId) {
        return interestDao.addInterest(interest.getInterestName(), userId);
    }

    @Override
    public Profile getProfile(int userId) {
        return userDao.getProfile(userId);
    }

    @Override
    public Interest deleteInterest(Interest interest, int userId) { return interestDao.deleteInterest(interest, userId);}

    @Override
    public String login(String email, CharSequence password) throws NoSuchAlgorithmException, InvalidKeyException { return authorizationSessionDao.login(email, password); }

    @Override
    public boolean checkAuthorization(String token) throws IOException, NoSuchAlgorithmException, InvalidKeyException { return authorizationSessionDao.checkAuthorization(token);}

    @Override
    public String register(String email, CharSequence password, String firstName, String lastName) throws NoSuchAlgorithmException, InvalidKeyException { return authorizationSessionDao.register(email, password, firstName, lastName); }

    @Override
    public int getUserId(String token) throws IOException { return authorizationSessionDao.getUserId(token);}

    @Override
    public List<Interest> getInterests(int userId) { return interestDao.getInterests(userId);}

    @Override
    public List<NewsInfo> getStories(int userId) throws IOException { return storyDao.getStories(userId);}

    @Override
    public List<UserOutletInfo> getOutlets(int userId) { return newsOutletDao.getOutlets(userId);}

    @Override
    public UserOutletInfo addOrUpdateOutlet(UserOutletInfo userOutletInfo, int userId) { return newsOutletDao.addOrUpdateOutlet(userOutletInfo, userId);}

    @Override
    public UserOutletInfo deleteOutlet(UserOutletInfo userOutletInfo, int userId) { return newsOutletDao.deleteOutlet(userOutletInfo, userId);}
}
