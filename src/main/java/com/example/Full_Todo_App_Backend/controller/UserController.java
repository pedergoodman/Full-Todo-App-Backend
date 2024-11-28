package com.example.Full_Todo_App_Backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Full_Todo_App_Backend.model.User;
import com.example.Full_Todo_App_Backend.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final ClientRegistration registration;
    private final UserService userService;

    public UserController(ClientRegistrationRepository registrations, UserService userService) {
        this.registration = registrations.findByRegistrationId("okta");
        this.userService = userService;
    }

    // Expose the /user endpoint for fetching user data
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/user")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal OAuth2User user) {
        if (user == null) {
            return ResponseEntity.status(401).body("User not authenticated");
        }

        // Check if user exists in the database, if not, save them
        User currentUser = userService.checkAndSaveAuthenticatedUser(user);

        // Return the authenticated user's details
        return ResponseEntity.ok(currentUser);
    }

    // Handle logout functionality
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request,
                                    @AuthenticationPrincipal(expression = "idToken") OidcIdToken idToken) {
        if (idToken == null) {
            return ResponseEntity.status(400).body("ID Token is missing");
        }

        String logoutUrl = this.registration.getProviderDetails().getConfigurationMetadata()
                .get("end_session_endpoint").toString();

        Map<String, String> logoutDetails = new HashMap<>();
        logoutDetails.put("logoutUrl", logoutUrl);
        logoutDetails.put("idToken", idToken.getTokenValue());

        // Invalidate the current session
        if (request.getSession(false) != null) {
            request.getSession(false).invalidate();
        }

        return ResponseEntity.ok(logoutDetails);
    }
}
