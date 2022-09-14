package com.example.controller;

import com.example.dto.CommentDto;
import com.example.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/")
    public ResponseEntity<?> save(Principal principal, @Valid @RequestBody CommentDto commentDto) {
        System.out.println(commentDto.getVotes());
        return commentService.save(principal, commentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id) {
        return commentService.deleteById(id);
    }

    @PostMapping("/upvote/{id}")
    public ResponseEntity<?> upvote(@PathVariable Long id) {
        return commentService.upvote(id);
    }

    @PostMapping("/downvote/{id}")
    public ResponseEntity<?> downvote(@PathVariable Long id) {
        return commentService.downvote(id);
    }
}
