package com.example.service;

import com.example.dto.PostDto;
import com.example.entity.Post;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.Optional;

public interface PostService {
    ResponseEntity<String> save(Principal principal, PostDto postDto);

    ResponseEntity<?> deleteById(long id);

    Optional<Post> getById(long id);
}
