package com.revature.services;

import com.revature.models.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {
    public Interest addInterest(Interest interest, int userId);
    public Profile getProfile(int userId);
    public Interest deleteInterest(Interest interest, int userId);
    public String login(String email, CharSequence password) throws NoSuchAlgorithmException, InvalidKeyException;
    public boolean checkAuthorization(String token) throws IOException, NoSuchAlgorithmException, InvalidKeyException;
    public String register(String email, CharSequence password, String firstName, String lastName) throws NoSuchAlgorithmException, InvalidKeyException;
    public int getUserId(String token) throws IOException;
    public List<Interest> getInterests(int userId);
    public List<NewsInfo> getStories(int userId) throws IOException;
    public List<UserOutletInfo> getOutlets(int userId);
    public UserOutletInfo addOrUpdateOutlet(com.revature.models.UserOutletInfo userOutletInfo, int userId);
    public UserOutletInfo deleteOutlet(UserOutletInfo userOutletInfo, int userId);
}
