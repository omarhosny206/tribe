package com.example.controller;

import com.example.dto.PostDto;
import com.example.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/")
    public ResponseEntity<String> add(Principal principal, @RequestBody PostDto postDto) {
        return postService.save(principal, postDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        return postService.deleteById(id);
    }
}
