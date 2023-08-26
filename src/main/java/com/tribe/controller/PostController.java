package com.tribe.controller;

import com.tribe.dto.ContentDto;
import com.tribe.dto.MessageDto;
import com.tribe.dto.PostRequestDto;
import com.tribe.entity.Post;
import com.tribe.service.PostService;
import com.tribe.util.AuthenticationUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/")
    public ResponseEntity<List<Post>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postService.getAll());
    }

    @GetMapping("/me")
    public ResponseEntity<List<Post>> getAllByUserId(Authentication authentication) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postService.getAllByUserId(AuthenticationUser.get(authentication).getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getById(@PathVariable long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Post> save(Authentication authentication, @Valid @RequestBody PostRequestDto postRequestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postService.save(AuthenticationUser.get(authentication), postRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> update(Authentication authentication, @PathVariable long id, @Valid @RequestBody ContentDto contentDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postService.update(AuthenticationUser.get(authentication), id, contentDto));
    }

    @PutMapping("/{id}/upvote")
    public ResponseEntity<Post> upvote(@PathVariable long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postService.upvote(id));
    }

    @PutMapping("/{id}/downvote")
    public ResponseEntity<Post> downvote(@PathVariable long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postService.downvote(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> deleteById(Authentication authentication, @PathVariable long id) {
        postService.deleteById(AuthenticationUser.get(authentication), id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MessageDto("Success"));
    }
}
