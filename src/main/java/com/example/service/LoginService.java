package com.example.service;

import com.example.dto.LoginRequest;
import org.springframework.http.ResponseEntity;

public interface LoginService {
    ResponseEntity<?> login(LoginRequest loginRequest) throws Exception;
}
