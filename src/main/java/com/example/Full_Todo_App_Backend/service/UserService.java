package com.example.Full_Todo_App_Backend.service;

import java.util.Map;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.Full_Todo_App_Backend.model.User;
import com.example.Full_Todo_App_Backend.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User checkAndSaveAuthenticatedUser(OAuth2User user) {
        // Deconstruct user object from Okta and make it usable for our DB needs
        Map<String, Object> attributes = user.getAttributes();
        String email = (String) attributes.get("preferred_username");
        String first_name = (String) attributes.get("given_name");
        String last_name = (String) attributes.get("family_name");
        String oktaId = (String) attributes.get("sub");

        // create new user object
        User currentUser = new User(first_name, last_name, email, oktaId);

        System.out.println("User object: " + currentUser);
        // System.out.println("User fullname: " + currentUser.getFullName());

        // check if user exists in database

        // if user exists, send user data back to database
        // if user doesn't exists, add them to DB, then send user data back to database

        // userService.checkAndSaveAuthenticatedUser(currentUser);

        // this is where we would connect it to the DB!
        return currentUser;
    };
}
