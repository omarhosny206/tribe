package com.example.service;

import com.example.dto.PostDto;
import com.example.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

public interface UserService extends UserDetailsService {
    User getById(long id);

    User getByEmail(String email);

    User getByUsername(String username);

    ResponseEntity<List<PostDto>> getFeed(Principal principal);

    @Transactional
    ResponseEntity<List<PostDto>> getFeed(Principal principal, String username);

    @Transactional
    ResponseEntity<?> follow(Principal principal, String username);

    @Transactional
    ResponseEntity<?> unfollow(Principal principal, String username);

    @Transactional
    ResponseEntity<?> block(Principal principal, String username);

    @Transactional
    public ResponseEntity<?> unblock(Principal principal, String username);

    @Transactional
    ResponseEntity<?> joinTribe(Principal principal, String name);

    @Transactional
    ResponseEntity<?> leaveTribe(Principal principal, String name);
}
