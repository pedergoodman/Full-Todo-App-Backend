package com.example.Full_Todo_App_Backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String oktaId;

    // No-args constructor
    public User() {}

    // Constructor for everything but the ID
    public User(String firstName, String lastName, String email, String oktaId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.oktaId = oktaId;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}

