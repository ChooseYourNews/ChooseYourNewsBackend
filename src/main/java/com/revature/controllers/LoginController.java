package com.revature.controllers;

import com.revature.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {
    private Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Object> authenticate(@RequestParam("email") String email, @RequestParam("password") String password) throws NoSuchAlgorithmException, InvalidKeyException {

        String authToken = userService.login(email, (CharSequence)password);

        if(authToken == null) {
            // prevent methods from executing
            logger.warn("no token received from request");
            // return 401 to the client
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            logger.info("token created");
            return new ResponseEntity<>(authToken, HttpStatus.CREATED);
        }
    }
}
