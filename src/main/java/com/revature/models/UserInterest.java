package com.revature.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


//@Component
@Entity
@Table(name = "UserInterest", schema = "public")
public class UserInterest implements Serializable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    int id;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @Id
    @OneToOne
    @JoinColumn(name = "interest_id")
    Interest interest;

//    @ManyToMany
//    @JoinTable(
//            name = "Book_Reader",
//            joinColumns = @JoinColumn(name = "book_id"),
//            inverseJoinColumns = @JoinColumn(name = "reader_id")
//    )
//    @WhereJoinTable( clause = "created_on > DATEADD( 'DAY', -7, CURRENT_TIMESTAMP() )")
//    private List<Reader> currentWeekReaders = new ArrayList<>( );


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInterest that = (UserInterest) o;
        return Objects.equals(user, that.user) && Objects.equals(interest, that.interest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, interest);
    }
}
