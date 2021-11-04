package com.revature.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;


//@Component
@Entity
@Table(name = "UserInterest", schema = "public")
public class UserInterest implements Serializable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    int id;

    @Id
    @ManyToOne
    @JoinColumn(name = "id")
    User user;

    @Id
    @OneToOne
    @JoinColumn(name = "id")
    Interest interest;

//    @ManyToMany
//    @JoinTable(
//            name = "Book_Reader",
//            joinColumns = @JoinColumn(name = "book_id"),
//            inverseJoinColumns = @JoinColumn(name = "reader_id")
//    )
//    @WhereJoinTable( clause = "created_on > DATEADD( 'DAY', -7, CURRENT_TIMESTAMP() )")
//    private List<Reader> currentWeekReaders = new ArrayList<>( );
}
