package com.example.service.impl;

import com.example.dto.CommentDto;
import com.example.entity.Comment;
import com.example.entity.Post;
import com.example.entity.User;
import com.example.repository.CommentRepository;
import com.example.repository.UserRepository;
import com.example.service.CommentService;
import com.example.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Optional;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostService postSerivce;
    private final UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostService postSerivce, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postSerivce = postSerivce;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<String> save(Principal principal,CommentDto commentDto) {
        Optional<Post> post = postSerivce.getById(commentDto.getPostId());

        if (post.isPresent()) {
            String username = principal.getName();
            User user = userRepository.findByEmail(username);
            Comment comment = modelMapper.map(commentDto, Comment.class);
            comment.setUser(user);
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
