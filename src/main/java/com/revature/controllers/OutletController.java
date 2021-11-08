package com.revature.controllers;

import com.revature.models.Interest;
import com.revature.models.NewsOutletInfo;
import com.revature.models.UserOutletInfo;
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
@RequestMapping("/outlets")
public class OutletController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserOutletInfo>> getOutlets() throws IOException {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String authTokenHeader = request.getHeader("Authorization");
        String authToken = authTokenHeader.split(" ")[1];
        int userId = userService.getUserId(authToken);
        return new ResponseEntity<>(userService.getOutlets(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserOutletInfo> postOutlet(@RequestBody UserOutletInfo userOutletInfo) throws IOException {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String authTokenHeader = request.getHeader("Authorization");
        String authToken = authTokenHeader.split(" ")[1];
        int userId = userService.getUserId(authToken);
        return new ResponseEntity<>(userService.addOrUpdateOutlet(userOutletInfo, userId), HttpStatus.CREATED);
    }


    @DeleteMapping
    public ResponseEntity<UserOutletInfo> deleteOutlet(@RequestBody UserOutletInfo userOutletInfo) throws IOException {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String authTokenHeader = request.getHeader("Authorization");
        String authToken = authTokenHeader.split(" ")[1];
        int userId = userService.getUserId(authToken);
        return new ResponseEntity<>(userService.deleteOutlet(userOutletInfo, userId), HttpStatus.CREATED);
    }
}
