package com.revature.controllers;

import com.revature.models.NewsInfo;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/stories")
public class StoriesController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<NewsInfo>> getStories() throws IOException {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String authTokenHeader = request.getHeader("Authorization");
        String authToken = authTokenHeader.split(" ")[1];
        int userId = userService.getUserId(authToken);

        return new ResponseEntity<>(userService.getStories(userId), HttpStatus.OK);
    }
}
