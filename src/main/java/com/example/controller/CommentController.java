package com.example.controller;

import com.example.dto.CommentDto;
import com.example.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/")
    public ResponseEntity<String> save(Principal principal, @RequestBody CommentDto commentDto) {
        return commentService.save(principal,commentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id) {
        return commentService.deleteById(id);
    }
}
