package com.example.Full_Todo_App_Backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PrivateController {

    @GetMapping("/private")
    public ResponseEntity<?> redirectToHome() {
        return ResponseEntity.status(302) // HTTP 302 Found
                             .header("Location", "/") // Redirect to the home page
                             .build();
    }
}
