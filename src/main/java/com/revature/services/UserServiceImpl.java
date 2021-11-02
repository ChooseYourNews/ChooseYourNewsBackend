package com.revature.services;

import com.revature.dao.InterestDaoImpl;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.models.Interest;
import com.revature.dao.InterestDao;
import com.revature.models.Profile;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    @Autowired
    public InterestDao interestDao = new InterestDaoImpl();
    @Autowired
    public UserDao userDao = new UserDaoImpl();

    @Override
    public Interest addInterest(Interest interest) {
        return interestDao.addInterest(interest.getInterest());
    }

    public Profile getProfile() {
        return userDao.getProfile();
    }
}
