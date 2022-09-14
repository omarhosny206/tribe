package com.example.service;

import com.example.dto.CommentDto;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface CommentService {
    public ResponseEntity<?> save(Principal principal, CommentDto commentDto);

    ResponseEntity<?> deleteById(long id);

    public ResponseEntity<?> downVote(long id);

    public ResponseEntity<?> upVote(long id);
}
