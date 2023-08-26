package com.tribe.controller;

import com.tribe.dto.CommentRequestDto;
import com.tribe.dto.ContentDto;
import com.tribe.dto.MessageDto;
import com.tribe.entity.Comment;
import com.tribe.service.CommentService;
import com.tribe.util.AuthenticationUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Comment>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.getAll());
    }

    @GetMapping("/me")
    public ResponseEntity<List<Comment>> getAllByUserId(Authentication authentication) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.getAllByUserId(AuthenticationUser.get(authentication).getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getById(@PathVariable long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Comment> save(Authentication authentication, @Valid @RequestBody CommentRequestDto commentRequestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commentService.save(AuthenticationUser.get(authentication), commentRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> update(Authentication authentication, @PathVariable long id, @Valid @RequestBody ContentDto contentDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.update(AuthenticationUser.get(authentication), id, contentDto));
    }

    @PutMapping("/{id}/upvote")
    public ResponseEntity<Comment> upvote(@PathVariable long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.upvote(id));
    }

    @PutMapping("/{id}/downvote")
    public ResponseEntity<Comment> downvote(@PathVariable long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.downvote(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> deleteById(Authentication authentication, @PathVariable long id) {
        commentService.deleteById(AuthenticationUser.get(authentication), id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MessageDto("Success"));
    }
}
