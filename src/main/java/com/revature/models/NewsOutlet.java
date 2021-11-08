package com.revature.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "NewsOutlet", schema = "public")
public class NewsOutlet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;
    @Column(name = "name_of_org")
    public String nameOfOrg;
    @Column(name = "api_pattern")
    public String apiPattern;
    @Column(name = "api_key")
    public String apiKey;

    public NewsOutlet() {
        super();
    }

    public NewsOutlet(NewsOutletInfo newsOutletInfo) {
        super();
        this.id = newsOutletInfo.id;
        this.nameOfOrg = newsOutletInfo.nameOfOrg;

    }

    public NewsOutlet(int id, String nameOfOrg) {
        super();
        this.id = id;
        this.nameOfOrg = nameOfOrg;
    }
}
