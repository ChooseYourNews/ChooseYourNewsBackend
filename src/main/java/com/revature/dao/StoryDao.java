package com.revature.dao;

import com.revature.models.NewsInfo;

import java.io.IOException;
import java.util.List;

public interface StoryDao {
    public List<NewsInfo> getStories(int userId) throws IOException;
}
