package com.example.service.impl;

import com.example.entity.User;
import com.example.exception.UserAlreadyFoundException;
import com.example.message.MessageResponse;
import com.example.repository.UserRepository;
import com.example.service.RegistrationService;
import com.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RegistrationServiceImpl(UserRepository userRepository, UserService userService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<?> register(User user) {
        User userByEmail = userService.getByEmail(user.getEmail());
        User userByUsername = userService.getByUsername(user.getUsername());

        if (userByEmail !=  null && userByUsername != null) {
            throw new UserAlreadyFoundException("Email and Username are already taken");
        }

        if (userByEmail != null) {
            throw new UserAlreadyFoundException("Email is already taken");
        }

        if (userByUsername != null) {
            throw new UserAlreadyFoundException("Username is already taken");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>(new MessageResponse("user registered successfully"), HttpStatus.OK);
    }
}
