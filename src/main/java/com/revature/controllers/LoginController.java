package com.revature.controllers;

import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

//    @PostMapping
//    public ResponseEntity<Authorization> authenticate(@RequestBody LoginParams loginParams){
//        return new ResponseEntity<>(userService.authenticate(breed), HttpStatus.CREATED);
//    }


}
