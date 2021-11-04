package com.revature.services;

import com.revature.models.Interest;
import com.revature.models.Profile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface UserService {
    public Interest addInterest(Interest interest);
    public Profile getProfile();
    public Interest deleteInterest(Interest interest);
    public String login(String email, CharSequence password) throws NoSuchAlgorithmException, InvalidKeyException;
    public boolean checkAuthorization(String token) throws IOException, NoSuchAlgorithmException, InvalidKeyException;
    public String register(String email, CharSequence password) throws NoSuchAlgorithmException, InvalidKeyException;
}
