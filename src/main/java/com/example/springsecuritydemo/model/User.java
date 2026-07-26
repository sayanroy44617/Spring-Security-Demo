package com.example.springsecuritydemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Data
@Getter @Setter
public class User {

    @Id
    private int Id;
    private String username;
    private String password;
}
