package com.example.Full_Todo_App_Backend.repository;

import com.example.Full_Todo_App_Backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByOktaId(String oktaId);
}

