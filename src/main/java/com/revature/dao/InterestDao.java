package com.revature.dao;

import com.revature.models.Interest;

public interface InterestDao {
    public Interest addInterest(String interest, int userId);
    public Interest deleteInterest(Interest interest, int userId);
}
