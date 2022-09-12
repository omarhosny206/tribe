package com.example.controller;

import com.example.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("post")
public class PostController {
    @Autowired
    PostService postService;
    @DeleteMapping("delete/{postId}")
    public ResponseEntity<?> delete(@PathVariable long postId)
    {
        return postService.delete(postId);
    }
}
