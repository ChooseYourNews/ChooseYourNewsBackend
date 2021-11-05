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

@RestController
@CrossOrigin
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Profile> getProfile() throws IOException {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String authTokenHeader = request.getHeader("Authorization");
        String authToken = authTokenHeader.split(" ")[1];
        int userId = userService.getUserId(authToken);
        return new ResponseEntity<>(userService.getProfile(userId), HttpStatus.OK);
    }

}
