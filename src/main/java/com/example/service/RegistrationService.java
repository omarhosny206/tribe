package com.example.service;

import com.example.entity.User;
import org.springframework.http.ResponseEntity;

public interface RegistrationService {
    ResponseEntity<?> register(User user);
}
