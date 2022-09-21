package com.example.service;

import com.example.dto.CommentDto;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface CommentService {
    public ResponseEntity<?> save(Principal principal, CommentDto commentDto);

    ResponseEntity<?> deleteById(long id);

    ResponseEntity<?> upvote(long id);

    ResponseEntity<?> downvote(long id);

    public ResponseEntity<?> edit(Principal principal,long id,String content);
}
