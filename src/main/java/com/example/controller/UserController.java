package com.example.controller;

import com.example.dto.PostDto;
import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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

    @GetMapping("/{email}")
    public User getByEmail(@PathVariable String email) {
        return userService.getByEmail(email);
    }

    @GetMapping("/{username}")
    public User getByUsername(@PathVariable String username) {
        return userService.getByUsername(username);
    }

    @PostMapping("/followers")
    public ResponseEntity<?> follow(Principal principal, @RequestParam String username) {
        return userService.follow(principal, username);
    }

    @DeleteMapping("/followers")
    public ResponseEntity<?> unFollow(Principal principal, @RequestParam String username) {
        return userService.unfollow(principal, username);
    }

    @PostMapping("/block")
    public ResponseEntity<?> block(Principal principal, @RequestParam String username) {
        return userService.block(principal, username);
    }

    @PostMapping("/unblock")
    public ResponseEntity<?> unblock(Principal principal, @RequestParam String username) {
        return userService.unblock(principal, username);
    }

    @GetMapping("/feed")
    public ResponseEntity<List<PostDto>> getFeed(Principal principal) {
        return userService.getFeed(principal);
    }

    @GetMapping("/feed/{username}")
    public ResponseEntity<List<PostDto>> getFeed(Principal principal, @PathVariable String username) {
        return userService.getFeed(principal, username);
    }
}
