package com.revature.models;

import javax.persistence.Table;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "interest", schema = "public")
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "interest")
    private String interest;

    public Interest(){
        super();
    }

    public Interest(String interest) {
        super();
        this.interest = interest;
    }

    public Interest(int id, String interest) {
        super();
        this.id = id;
        this.interest = interest;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInterest() {
        return interest;
    }

    @Override
    public String toString() {
        return "Interest{" +
                "id=" + id +
                ", interest='" + interest +
                '}';
    }
}
