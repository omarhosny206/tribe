package com.example.service.impl;

import com.example.dto.LoginRequest;
import com.example.dto.LoginResponse;
import com.example.response.MessageResponse;
import com.example.service.LoginService;
import com.example.service.UserService;
import com.example.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public LoginServiceImpl(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> login(LoginRequest loginRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
            ));
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse("invalid username or password"), HttpStatus.BAD_REQUEST);
        }

        final UserDetails userDetails
                = userService.loadUserByUsername(loginRequest.getEmail());

        final String token =
                jwtUtil.generateToken(userDetails);

        return new ResponseEntity<>(new LoginResponse(loginRequest.getEmail(), token), HttpStatus.OK);
    }
}
