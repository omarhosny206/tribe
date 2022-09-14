package com.example.service.impl;

import com.example.dto.CommentDto;
import com.example.dto.PostDto;
import com.example.entity.User;
import com.example.exception.ResourceNotFoundException;
import com.example.message.MessageResponse;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    @Override
    public ResponseEntity<?> follow(Principal principal, String username) {
        User currentUser = getByEmail(principal.getName());
        User userToFollow = getByUsername(username);

        if (userToFollow == null) {
            throw new ResourceNotFoundException("user not found");
        }
        currentUser.getFollowing().add(userToFollow);
        userToFollow.getFollowers().add(currentUser);
        return new ResponseEntity<>(new MessageResponse("Followed successfully"), HttpStatus.OK);

    }

    @Transactional
    @Override
    public ResponseEntity<?> unfollow(Principal principal, String username) {
        User currentUser = getByEmail(principal.getName());
        User userToUnFollow = getByUsername(username);

        if (userToUnFollow == null) {
            throw new ResourceNotFoundException("user not found");
        }
        currentUser.getFollowing().remove(userToUnFollow);
        userToUnFollow.getFollowers().remove(currentUser);
        return new ResponseEntity<>(new MessageResponse("UnFollowed successfully"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PostDto>> getFeed(Principal principal) {
        List<PostDto> result = new ArrayList<>();
        User user = getByEmail(principal.getName());

        user.getFollowing().forEach(following -> {
            following.getPosts().forEach(post -> {
                List<CommentDto> commentsDtos = post.getComments().stream()
                        .map(comment -> new CommentDto(comment.getUser().getUsername(), post.getId(), comment.getContent(), comment.getDate()))
                        .toList();

                PostDto postDto = new PostDto(following.getUsername(), post.getContent(), post.getDate(), commentsDtos);
                result.add(postDto);
            });
        });

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}