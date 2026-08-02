package com.example.springsecuritydemo.controller;

import com.example.springsecuritydemo.DAO.UserDAO;
import com.example.springsecuritydemo.service.JWTService;
import com.example.springsecuritydemo.service.UserRegistrationFlowService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserFlowController {

    private final UserRegistrationFlowService userRegistrationFlowService;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDAO userDAO) {
        userRegistrationFlowService.registerUser(userDAO.username(), userDAO.password());
        return new ResponseEntity<>("User Registered", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDAO userDAO) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userDAO.username() , userDAO.password()));

        if (auth.isAuthenticated()) {
            String token = jwtService.generateToken(userDAO.username());
            return new ResponseEntity<>(token , HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
        }
    }

}
