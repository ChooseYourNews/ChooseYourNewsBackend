package com.revature.dao;

import com.revature.models.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public interface NewsOutletDao {
    public List<UserOutletInfo> getOutlets(int userId);
    public UserOutletInfo addOrUpdateOutlet(UserOutletInfo userOutletInfo, int userId);
    public UserOutletInfo deleteOutlet(UserOutletInfo userOutletInfo, int userId);
}
