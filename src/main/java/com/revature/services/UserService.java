package com.revature.services;

import com.revature.models.Interest;
import com.revature.models.Profile;

public interface UserService {
    public Interest addInterest(Interest interest);
    public Profile getProfile();
}
