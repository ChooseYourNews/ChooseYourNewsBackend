package com.revature.dao;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface AuthorizationSessionDao {
    public String login(String email, CharSequence password) throws NoSuchAlgorithmException, InvalidKeyException;
    public boolean checkAuthorization(String token) throws IOException, NoSuchAlgorithmException, InvalidKeyException;
    public String register(String email, CharSequence password, String firstName, String lastName) throws NoSuchAlgorithmException, InvalidKeyException;
    public int getUserId(String token) throws IOException;
}
