package com.revature.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
public class Profile {
    public UserInfo user;
    @ElementCollection
    public List<Interest> interestList;
    public List<UserOutletInfo> newsOutletList;

    Profile() {
        super();
    }

    public Profile(UserInfo user, List<Interest> interestList, List<UserOutletInfo> newsOutletList) {
        super();
        this.user = user;
        this.interestList = interestList;
        this.newsOutletList = newsOutletList;
    }
}
