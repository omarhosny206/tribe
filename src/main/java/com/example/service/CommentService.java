package com.example.service;

import com.example.dto.CommentDto;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    ResponseEntity<String> save(CommentDto commentDto);

    ResponseEntity<String> deleteById(long id);
}
