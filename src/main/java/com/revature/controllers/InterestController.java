package com.revature.controllers;

import com.revature.models.Interest;
import com.revature.models.Profile;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/interests")
public class InterestController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Interest>> getInterests() throws IOException {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String authTokenHeader = request.getHeader("Authorization");
        String authToken = authTokenHeader.split(" ")[1];
        int userId = userService.getUserId(authToken);
        return new ResponseEntity<>(userService.getInterests(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Interest> addInterest(@RequestBody Interest interest) throws IOException {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String authTokenHeader = request.getHeader("Authorization");
        String authToken = authTokenHeader.split(" ")[1];
        int userId = userService.getUserId(authToken);
        return new ResponseEntity<>(userService.addInterest(interest, userId), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Interest> deleteInterest(@RequestBody Interest interest) throws IOException {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String authTokenHeader = request.getHeader("Authorization");
        String authToken = authTokenHeader.split(" ")[1];
        int userId = userService.getUserId(authToken);
        return new ResponseEntity<>(userService.deleteInterest(interest, userId), HttpStatus.CREATED);
    }

}
