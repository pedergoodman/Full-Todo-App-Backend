package com.example.Full_Todo_App_Backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String oktaId;

    // lombok provides all args and no args constructor
    // this is for everything but the id (and will probably be used most often
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
