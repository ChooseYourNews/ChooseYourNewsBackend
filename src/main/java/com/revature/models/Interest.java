package com.revature.models;

import javax.persistence.Table;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "interests", schema = "public")
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "interest_name")
    private String interestName;

    public Interest(){
        super();
    }

    public Interest(String interestName) {
        super();
        this.interestName = interestName;
    }

    public Interest(int id, String interestName) {
        super();
        this.id = id;
        this.interestName = interestName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInterestName() {
        return interestName;
    }

    @Override
    public String toString() {
        return "Interest{" +
                "id=" + id +
                ", interestName='" + interestName +
                '}';
    }
}
