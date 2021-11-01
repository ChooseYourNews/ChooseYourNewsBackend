package com.revature.controllers;

import com.revature.models.Interest;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/interests")
public class InterestController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Interest> addInterest(@RequestBody Interest interest){
        return new ResponseEntity<>(userService.addInterest(interest), HttpStatus.CREATED);
    }

}
