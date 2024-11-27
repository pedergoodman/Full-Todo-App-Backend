package com.example.Full_Todo_App_Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Full_Todo_App_Backend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
