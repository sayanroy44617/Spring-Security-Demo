package com.example.springsecuritydemo.controller;

import com.example.springsecuritydemo.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/security")
    public String security_get(){
        return "Security Endpoint";
    }

    @PostMapping("/student")
    public String student(@RequestBody Student student)
    {
        return student.toString();
    }
}

