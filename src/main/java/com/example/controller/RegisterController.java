package com.example.controller;

import com.example.entity.User;
import com.example.service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private final RegistrationService registrationService;

    public RegisterController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/")
    public ResponseEntity<?> register(@RequestBody User user) {
        return registrationService.register(user);
    }
}
