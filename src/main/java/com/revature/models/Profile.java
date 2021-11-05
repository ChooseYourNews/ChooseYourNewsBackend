package com.revature.models;

import org.hibernate.Session;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
public class Profile {
    public UserInfo user;
    @ElementCollection
    public List<Interest> interestList;

    Profile() {
        super();
    }

    public Profile(UserInfo user, List<Interest> interestList) {
        super();
        this.user = user;
        this.interestList = interestList;
    }
}
