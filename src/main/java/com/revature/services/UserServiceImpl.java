package com.revature.services;

import com.revature.dao.InterestDaoImpl;
import com.revature.models.Interest;
import com.revature.dao.InterestDao;
import com.revature.models.Profile;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    @Autowired
    public InterestDao interestDao = new InterestDaoImpl();

    @Override
    public Interest addInterest(Interest interest) {
        return interestDao.addInterest(interest.getInterest());
    }

    public Profile getProfile() {
        // Todo: Get profile from database
        return null;
    }
}
