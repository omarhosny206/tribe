package com.example.service.impl;

import com.example.dto.CommentDto;
import com.example.dto.PostDto;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.repository.UserRepository;
import com.example.response.MessageResponse;
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
            throw new CustomException("user not found");
        }

        List<User> blocked = currentUser.getBlocked();
        if (blocked.contains(userToFollow))
            throw new CustomException("can't follow, user is already blocked");

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
            throw new CustomException("user not found");
        }

        List<User> following = currentUser.getFollowing();
        if (!following.contains(userToUnFollow)) {
            throw new CustomException("can't unfollow, user is not followed by you");
        }

        currentUser.getFollowing().remove(userToUnFollow);
        userToUnFollow.getFollowers().remove(currentUser);
        return new ResponseEntity<>(new MessageResponse("Unfollowed successfully"), HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<?> block(Principal principal, String username) {
        User userToBlock = getByUsername(username);
        User currentUser = getByEmail(principal.getName());

        if (userToBlock == null)
            throw new CustomException("user not found");

        List<User> blocked = currentUser.getBlocked();

        if (blocked.contains(userToBlock)) {
            throw new CustomException("user is already blocked");
        }

        unfollow(principal, username);
        blocked.add(userToBlock);
        return new ResponseEntity<>(new MessageResponse("blocked successfully"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PostDto>> getFeed(Principal principal) {
        List<PostDto> result = new ArrayList<>();
        User user = getByEmail(principal.getName());

        user.getFollowing().forEach(following -> {
            following.getPosts().forEach(post -> {
                List<CommentDto> commentsDtos = post.getComments().stream()
                        .map(comment -> new CommentDto(comment.getUser().getUsername(), post.getId(), comment.getContent(), comment.getVotes(), comment.getDate()))
                        .toList();

                PostDto postDto = new PostDto(following.getUsername(), post.getContent(), post.getVotes(), post.getDate(), commentsDtos);
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
