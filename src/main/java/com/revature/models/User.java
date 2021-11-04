package com.revature.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.*;


//@Component
@Entity
@Table(name = "users", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Email
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    public String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
