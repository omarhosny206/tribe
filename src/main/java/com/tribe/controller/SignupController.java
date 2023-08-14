package com.tribe.controller;

import com.tribe.dto.SignupRequestDto;
import com.tribe.entity.User;
import com.tribe.service.SignupService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/signup")
public class SignupController {
    private final SignupService signupService;

    public SignupController(SignupService signupService) {
        this.signupService = signupService;
    }

    @PostMapping("/")
    public ResponseEntity<User> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(signupService.signup(signupRequestDto));
    }
}
