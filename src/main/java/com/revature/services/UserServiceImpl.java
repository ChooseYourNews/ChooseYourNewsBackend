package com.revature.services;

import com.revature.dao.*;
import com.revature.models.Interest;
import com.revature.models.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public InterestDao interestDao = new InterestDaoImpl();
    @Autowired
    public UserDao userDao = new UserDaoImpl();
    @Autowired
    public AuthorizationSessionDao authorizationSessionDao = new AuthorizationSessionDaoImpl();

    @Override
    public Interest addInterest(Interest interest) {
        return interestDao.addInterest(interest.getInterestName());
    }

    @Override
    public Profile getProfile() {
        return userDao.getProfile();
    }

    @Override
    public Interest deleteInterest(Interest interest) { return interestDao.deleteInterest(interest);}

    @Override
    public String login(String email, CharSequence password) throws NoSuchAlgorithmException, InvalidKeyException { return authorizationSessionDao.login(email, password); }

    @Override
    public boolean checkAuthorization(String token) throws IOException, NoSuchAlgorithmException, InvalidKeyException { return authorizationSessionDao.checkAuthorization(token);}

    @Override
    public String register(String email, CharSequence password) throws NoSuchAlgorithmException, InvalidKeyException { return authorizationSessionDao.register(email, password); }

}
