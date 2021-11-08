package com.revature.models;

import org.springframework.stereotype.Component;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Component
public class UserOutletInfo {
    public NewsOutletInfo newsOutlet;
    public boolean wantToSee;

    UserOutletInfo() {
        super();
    }

    public UserOutletInfo(NewsOutletInfo newsOutlet, boolean wantToSee) {
        super();
        this.newsOutlet = newsOutlet;
        this.wantToSee = wantToSee;
    }

    public boolean getWantToSee() {
        return wantToSee;
    }
}
