package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public User getById(@RequestParam long id) {
        return userService.getById(id);
    }

    @GetMapping("/")
    public User getByUsername(@RequestParam String username) {
        return userService.getByUsername(username);
    }

    @PostMapping("/followers")
    public ResponseEntity<String> follow(@RequestParam String username) {
        return userService.follow(username);
    }
}
