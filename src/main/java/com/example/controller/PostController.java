package com.example.controller;

import com.example.dto.PostDto;
import com.example.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("post")
public class PostController {
    @Autowired
    PostService postService;
    @PostMapping("/add")
    public ResponseEntity<String> add(Principal principal,@RequestBody PostDto postDto)
    {
        return postService.add(principal,postDto);
    }
    @DeleteMapping("delete/{postId}")
    public ResponseEntity<?> delete(@PathVariable long postId)
    {
        return postService.delete(postId);
    }
}
