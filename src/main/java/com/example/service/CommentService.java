package com.example.service;

import com.example.dto.CommentDto;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface CommentService {
    public ResponseEntity<String> save(Principal principal, CommentDto commentDto);

    ResponseEntity<String> deleteById(long id);
}
