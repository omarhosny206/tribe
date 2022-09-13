package com.example.service;

import com.example.entity.User;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.security.Principal;

public interface UserService {
    User getById(long id);

    User getByEmail(String email);

    User getByUsername(String username);

    @Transactional
    ResponseEntity<String> follow(Principal principal, String username);
}
