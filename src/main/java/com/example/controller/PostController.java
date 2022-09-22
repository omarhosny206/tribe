package com.example.controller;

import com.example.dto.ContentDto;
import com.example.dto.PostDto;
import com.example.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/")
    public ResponseEntity<?> add(Principal principal, @Valid @RequestBody PostDto postDto) {
        return postService.save(principal, postDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(Principal principal, @RequestBody ContentDto contentDto, @PathVariable Long id) {
        return postService.update(principal, contentDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        return postService.deleteById(id);
    }

    @PostMapping("/upvote/{id}")
    public ResponseEntity<?> upvote(@PathVariable Long id) {
        return postService.upVote(id);
    }

    @PostMapping("/downvote/{id}")
    public ResponseEntity<?> downvote(@PathVariable Long id) {
        return postService.downVote(id);
    }

    @GetMapping("/bookmark")
    public ResponseEntity<List<PostDto>> getAllBookmarked(Principal principal) {
        return postService.getAllBookmarked(principal);
    }

    @PostMapping("/bookmark/{id}")
    public ResponseEntity<?> addBookmark(Principal principal, @PathVariable Long id) {
        return postService.addBookmark(principal, id);
    }

    @DeleteMapping("/bookmark/{id}")
    public ResponseEntity<?> removeBookmark(Principal principal, @PathVariable Long id) {
        return postService.removeBookmark(principal, id);
    }
}
