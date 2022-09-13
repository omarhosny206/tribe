package com.example.service.impl;

import com.example.dto.LoginRequest;
import com.example.dto.LoginResponse;
import com.example.service.LoginService;
import com.example.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserServiceImpl userService;
    private final AuthenticationManager authenticationManager;

    public LoginServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public ResponseEntity<?> login(LoginRequest loginRequest) throws Exception {
        try
        {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
            ));
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("invalid username or password", HttpStatus.BAD_REQUEST);
        }
        final UserDetails userDetails
                = userService.loadUserByUsername(loginRequest.getEmail());
        final String token =
                jwtUtil.generateToken(userDetails);
        return new ResponseEntity<>(new LoginResponse(loginRequest.getEmail(),token),HttpStatus.OK);
    }
}
