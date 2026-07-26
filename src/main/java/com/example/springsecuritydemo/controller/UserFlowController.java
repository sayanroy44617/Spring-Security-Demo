package com.example.springsecuritydemo.controller;

import com.example.springsecuritydemo.DAO.UserDAO;
import com.example.springsecuritydemo.service.UserRegistrationFlowService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserFlowController {

    private final UserRegistrationFlowService userRegistrationFlowService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDAO userDAO) {
        userRegistrationFlowService.registerUser(userDAO.id(), userDAO.username(), userDAO.password());
        return new ResponseEntity<>("User Registered", HttpStatus.OK);
    }

}
