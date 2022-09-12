package com.example.service;

import com.example.entity.Post;
import com.example.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    public ResponseEntity<?>delete(long postId)
    {
        System.out.println("post id is"+postId);
        Post post=postRepository.findById(postId).orElse(null);
        if(post==null)
        {
            return new ResponseEntity<>("post not found", HttpStatus.BAD_REQUEST);
        }
        else
        {
            postRepository.delete(post);
            return new ResponseEntity<>("post deleted",HttpStatus.OK);
        }
    }
}
