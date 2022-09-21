package com.example.service.impl;

import com.example.dto.CommentDto;
import com.example.dto.ContentDto;
import com.example.entity.Comment;
import com.example.entity.Post;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.repository.CommentRepository;
import com.example.repository.UserRepository;
import com.example.response.MessageResponse;
import com.example.service.CommentService;
import com.example.service.PostService;
import org.modelmapper.ModelMapper;
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
    private final PostService postService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostService postService, UserRepository userRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postService = postService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<?> save(Principal principal, CommentDto commentDto) {
        Optional<Post> post = postService.getById(commentDto.getPostId());

        if (post.isPresent()) {
            String username = principal.getName();
            User user = userRepository.findByEmail(username);
            Comment comment = modelMapper.map(commentDto, Comment.class);
            comment.setUser(user);
            comment.setPost(post.get());
            commentRepository.save(comment);
            return new ResponseEntity<>(new MessageResponse("Comment created successfully"), HttpStatus.CREATED);

        }

        return new ResponseEntity<>(new MessageResponse("No post to associate with"), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> deleteById(long id) {
        Comment comment = commentRepository.findById(id).orElse(null);

        if (comment == null)
            throw new CustomException("Not found comment with id = " + id);

        commentRepository.deleteById(id);
        return new ResponseEntity<>(new MessageResponse("Comment deleted successfully"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> upvote(long id) {
        Comment comment = commentRepository.findById(id).orElse(null);

        if (comment == null) {
            throw new CustomException("comment not found");
        }

        comment.setVotes(comment.getVotes() + 1);
        return new ResponseEntity<>(new MessageResponse("upvoted successfully"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> downvote(long id) {
        Comment comment = commentRepository.findById(id).orElse(null);

        if (comment == null) {
            throw new CustomException("comment not found");
        }

        if (comment.getVotes() == 0) {
            throw new CustomException("can't downvote comment, comment has 0 votes");
        }

        comment.setVotes(comment.getVotes() - 1);
        return new ResponseEntity<>(new MessageResponse("downvoted successfully"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> edit(Principal principal, long id, ContentDto content)
    {
        String username = principal.getName();
        User user = userRepository.findByEmail(username);
        Comment comment = commentRepository.findById(id).orElse(null);

        if(comment==null)
        {
            throw new CustomException("comment not found");
        }

        if(!(user.getComments().contains(comment)))
        {
            throw new CustomException("you didn't create this comment");
        }

        comment.setContent(content.getContent());
        return new ResponseEntity<>(new MessageResponse("comment edited successfully"), HttpStatus.OK);
    }

}
