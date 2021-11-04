package com.revature.dao;

import com.revature.models.Profile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface UserDao {
    public Profile getProfile();
}
