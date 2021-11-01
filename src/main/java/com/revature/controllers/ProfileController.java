package com.revature.controllers;

import com.revature.models.Interest;
import com.revature.models.Profile;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Profile> getProfile(){
        return new ResponseEntity<>(userService.getProfile(), HttpStatus.OK);
    }

}
