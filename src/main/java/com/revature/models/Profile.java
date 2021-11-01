package com.revature.models;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import java.util.List;

@Component
@Entity
public class Profile {
    private String name;
    List<String> interestList;

    Profile() {
        super();
    }

    Profile(String name, List<String> interestList) {
        super();
        this.name = name;
        this.interestList = interestList;
    }
}
