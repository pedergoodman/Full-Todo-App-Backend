package com.example.Full_Todo_App_Backend.repository;

import com.example.Full_Todo_App_Backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}

