package com.example.service.impl;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<String> register(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>("user registered", HttpStatus.OK);
    }
}
