package com.example.service;

import com.example.dto.CommentDto;
import com.example.entity.Comment;
import com.example.entity.Post;
import com.example.repository.CommentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
/*
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostSerivce postSerivce;

    public CommentService(CommentRepository commentRepository, PostSerivce postSerivce) {
        this.commentRepository = commentRepository;
        this.postSerivce = postSerivce;
    }

    public ResponseEntity<String> save(CommentDto commentDto) {
        Post post = postSerivce.getById(commentDto.getPostId());

        if (post == null) {
            return new ResponseEntity<>("No post to associate with", HttpStatus.BAD_REQUEST);
        }

        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setPost(post);

        commentRepository.save(comment);
        return new ResponseEntity<>("Comment created successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteById(long id) {
        Comment comment = commentRepository.findById(id).orElse(null);

        if (comment == null)
            return new ResponseEntity<>("Comment not found", HttpStatus.BAD_REQUEST);

        commentRepository.deleteById(id);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
*/