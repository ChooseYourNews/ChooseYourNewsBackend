package com.revature.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Component
@Entity
@Table(name = "UserOutlet", schema = "public")
public class UserOutlet implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "outlet_id")
    public NewsOutlet newsOutlet;

    @Column(name = "want_to_see")
    public boolean wantToSee;

    public UserOutlet() {
        super();
    }

    public UserOutlet(User user, UserOutletInfo userOutletInfo) {
        super();
        this.user = user;
        this.newsOutlet = new NewsOutlet(userOutletInfo.newsOutlet);
        this.wantToSee = userOutletInfo.wantToSee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserOutlet that = (UserOutlet) o;
        return wantToSee == that.wantToSee && Objects.equals(user, that.user) && Objects.equals(newsOutlet, that.newsOutlet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, newsOutlet, wantToSee);
    }
}
