package com.revature.models;


import org.springframework.stereotype.Component;

@Component
public class NewsOutletInfo {
    public int id;
    public String nameOfOrg;
    private String apiPattern;
    private String apiKey;

    public NewsOutletInfo() {
        super();
    }

    public NewsOutletInfo(int id, String nameOfOrg) {
        super();
        this.id = id;
        this.nameOfOrg = nameOfOrg;
    }
}
