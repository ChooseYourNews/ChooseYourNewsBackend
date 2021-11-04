package com.revature.aspects;

import com.revature.services.UserService;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

//@Aspect
//@Component
public class AuthAspect {

    private Logger logger = Logger.getLogger(AuthAspect.class);

    @Autowired
    private UserService userService;

    @Around("execution(* get*(..)) || execution(* set*(..)) || execution(* delete*(..))")
    public ResponseEntity<?> authorizeRequest(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String authTokenHeader = request.getHeader("Authorization");
        String[] split = authTokenHeader.split(" ");
        if(split.length == 2 && split[0] == "Bearer") {
            String authToken = split[1];
            if(userService.checkAuthorization(authToken)) {
                // allow methods execute normally
                logger.info("token received");
                return (ResponseEntity<?>) pjp.proceed();
            }
        }
        // prevent methods from executing
        logger.warn("no token received from request");
        // return 401 to the client
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}

