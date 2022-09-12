package com.example.service;

import com.example.dto.CommentDto;
import com.example.entity.Comment;
import com.example.entity.Post;
import com.example.repository.CommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postSerivce;
    @Autowired
    private ModelMapper modelMapper;
    public CommentService(CommentRepository commentRepository, PostService postSerivce) {
        this.commentRepository = commentRepository;
        this.postSerivce = postSerivce;
    }

    public ResponseEntity<String> save(CommentDto commentDto) {
        Optional<Post> post = postSerivce.getById(commentDto.getPostId());

        if (post.isPresent()) {
            Comment comment =modelMapper.map(commentDto,Comment.class);
            comment.setPost(post.get());
            commentRepository.save(comment);
            return new ResponseEntity<>("Comment created successfully", HttpStatus.CREATED);

        }
        else {
            return new ResponseEntity<>("No post to associate with", HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<String> deleteById(long id) {
        Comment comment = commentRepository.findById(id).orElse(null);

        if (comment == null)
            return new ResponseEntity<>("Comment not found", HttpStatus.BAD_REQUEST);

        commentRepository.deleteById(id);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
