package com.example.service;

import com.example.dto.PostDto;
import com.example.entity.User;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

public interface UserService {
    User getById(long id);

    User getByEmail(String email);

    User getByUsername(String username);

    @Transactional
    ResponseEntity<String> follow(Principal principal, String username);

    @Transactional
    ResponseEntity<String> unfollow(Principal principal, String username);

    ResponseEntity<List<PostDto>> getFeed(Principal principal);
}
