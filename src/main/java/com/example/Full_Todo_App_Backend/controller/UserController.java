package com.example.Full_Todo_App_Backend.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
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

import com.example.Full_Todo_App_Backend.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController()
@RequestMapping("/api/v1")
public class UserController {
    private final ClientRegistration registration;
    private final UserService userService;

    public UserController(ClientRegistrationRepository registrations, UserService userService) {
        this.registration = registrations.findByRegistrationId("okta");
        this.userService = userService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/user")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal OAuth2User user) {
        if (user == null) {
            return new ResponseEntity<>("", HttpStatus.OK);
        } else {
            // this is where we would connect it to the DB!
            return ResponseEntity.ok().body(user.getAttributes());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, @AuthenticationPrincipal(expression = "idToken") OidcIdToken idToken) {
        // send logout URL to client so they can initiate logout
        String logoutUrl = this.registration.getProviderDetails().getConfigurationMetadata().get("end_session_endpoint").toString();

        Map<String, String> logoutDetails = new HashMap<>();
        logoutDetails.put("logoutUrl", logoutUrl);
        logoutDetails.put("idToken", idToken.getTokenValue());
        request.getSession(false).invalidate();
        System.out.println("LogoutDetails, logoutURL: " + logoutUrl);
        System.out.println("LogoutDetails, idToken: " + idToken.getTokenValue());
        return ResponseEntity.ok().body(logoutDetails);
    }


}


