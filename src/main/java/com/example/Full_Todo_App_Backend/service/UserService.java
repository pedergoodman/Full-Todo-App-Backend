package com.example.Full_Todo_App_Backend.service;

import org.springframework.stereotype.Service;

import com.example.Full_Todo_App_Backend.model.User;
import com.example.Full_Todo_App_Backend.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUserInDatabase(User user) {
        // check if user exists in database
        userRepository.save(user);
    }
}

