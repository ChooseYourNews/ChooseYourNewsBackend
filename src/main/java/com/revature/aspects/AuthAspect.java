package com.revature.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuthAspect {

    private Logger logger = Logger.getLogger(AuthAspect.class);

    @Around("execution(* addInterest(..))")
    public ResponseEntity<?> authorizeRequest(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String authToken = request.getHeader("Authorization");
        if(authToken == null){
            // prevent addInterest method from executing
            logger.warn("no token received from request");
            // return 401 to the client
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            // allow addInterest to execute normally
            // TODO: Look up authorization token
            logger.info("token received");
            return (ResponseEntity<?>) pjp.proceed();
        }
    }

}

