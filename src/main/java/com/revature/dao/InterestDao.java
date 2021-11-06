package com.revature.dao;

import com.revature.models.Interest;

import java.util.List;

public interface InterestDao {
    public Interest addInterest(String interest, int userId);
    public Interest deleteInterest(Interest interest, int userId);
    public List<Interest> getInterests(int userId);
}
