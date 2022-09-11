package com.example.service;

import com.example.dto.LoginRequest;
import com.example.dto.LoginResponse;
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
public class LoginService {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;
    private final AuthenticationManager authenticationManager;

    public LoginService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

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
            System.out.println("a7aaaaaaaaaaaaa");
            return new ResponseEntity<>(new String("invalid username or password"), HttpStatus.BAD_REQUEST);
        }
        final UserDetails userDetails
                = userService.loadUserByUsername(loginRequest.getEmail());
        final String token =
                jwtUtil.generateToken(userDetails);
        return new ResponseEntity<>(new LoginResponse(loginRequest.getEmail(),token),HttpStatus.OK);
    }
}
