package com.example.controller;

import com.example.dto.CommentDto;
import com.example.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/")
    public ResponseEntity<String> save(CommentDto commentDto) {
        return commentService.save(commentDto);
    }

    @DeleteMapping("/")
    public ResponseEntity<String> deleteById(@RequestParam long id) {
        return commentService.deleteById(id);
    }
}
