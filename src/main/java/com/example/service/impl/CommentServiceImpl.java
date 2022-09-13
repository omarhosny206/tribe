package com.example.service.impl;

import com.example.dto.CommentDto;
import com.example.entity.Comment;
import com.example.entity.Post;
import com.example.repository.CommentRepository;
import com.example.service.CommentService;
import com.example.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostService postSerivce;
    @Autowired
    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostService postSerivce) {
        this.commentRepository = commentRepository;
        this.postSerivce = postSerivce;
    }

    @Override
    public ResponseEntity<String> save(CommentDto commentDto) {
        Optional<Post> post = postSerivce.getById(commentDto.getPostId());

        if (post.isPresent()) {
            Comment comment = modelMapper.map(commentDto, Comment.class);
            comment.setPost(post.get());
            commentRepository.save(comment);
            return new ResponseEntity<>("Comment created successfully", HttpStatus.CREATED);

        }

        return new ResponseEntity<>("No post to associate with", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> deleteById(long id) {
        Comment comment = commentRepository.findById(id).orElse(null);

        if (comment == null)
            return new ResponseEntity<>("Comment not found", HttpStatus.BAD_REQUEST);

        commentRepository.deleteById(id);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
