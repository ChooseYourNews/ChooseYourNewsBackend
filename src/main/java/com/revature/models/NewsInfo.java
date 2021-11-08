package com.revature.models;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

@Component
public class NewsInfo implements Serializable {
    public NewsOutletInfo newsOutlet;
    public JsonNode result;
    public Interest interest;

    public NewsInfo() {
        super();
    }

    public NewsInfo(NewsOutletInfo newsOutlet, JsonNode result, Interest interest) {
        super();
        this.newsOutlet = newsOutlet;
        this.result = result;
        this.interest = interest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsInfo storyInfo = (NewsInfo) o;
        return Objects.equals(newsOutlet, storyInfo.newsOutlet) && Objects.equals(result, storyInfo.result) && Objects.equals(interest, storyInfo.interest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newsOutlet, result, interest);
    }
}
