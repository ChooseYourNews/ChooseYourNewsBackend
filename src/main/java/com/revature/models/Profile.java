package com.revature.models;

import org.hibernate.Session;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
@Entity
public class Profile {
    @Id
    private User user;
    @ElementCollection
    private List<Interest> interestList;

    Profile() {
        super();
    }

    public Profile(User user, List<Interest> interestList) {
        super();
        this.user = user;
        this.interestList = interestList;
    }
}
