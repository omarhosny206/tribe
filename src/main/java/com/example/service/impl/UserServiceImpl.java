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
import java.util.Collections;
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

        if (currentUser.getBlocked().contains(userToFollow))
            throw new CustomException("can't follow, user is blocked by you");

        if (userToFollow.getBlocked().contains(currentUser))
            throw new CustomException("can't follow, user blocked you");

        List<User> following = currentUser.getFollowing();
        if (following.contains(userToFollow)) {
            throw new CustomException("user is already followed by you");
        }

        following.add(userToFollow);
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

        following.remove(userToUnFollow);
        userToUnFollow.getFollowers().remove(currentUser);
        return new ResponseEntity<>(new MessageResponse("Unfollowed successfully"), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> block(Principal principal, String username) {
        User userToBlock = getByUsername(username);
        User currentUser = getByEmail(principal.getName());

        if (userToBlock == null)
            throw new CustomException("can't block, user not found");

        List<User> blocked = currentUser.getBlocked();
        if (blocked.contains(userToBlock)) {
            throw new CustomException("can't block, user is already blocked");
        }

        if (userToBlock.getBlocked().contains(currentUser)) {
            throw new CustomException("can't block, user blocked you");
        }

        List<User> currentUserFollowing = currentUser.getFollowing();
        List<User> userToBlockFollowing = userToBlock.getFollowing();

        if (currentUserFollowing.contains(userToBlock)) {
            unfollow(principal, username);
        }

        if (userToBlockFollowing.contains(currentUser)) {
            userToBlockFollowing.remove(currentUser);
            currentUser.getFollowers().remove(userToBlock);
        }

        blocked.add(userToBlock);
        return new ResponseEntity<>(new MessageResponse("blocked successfully"), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> unblock(Principal principal, String username)
    {
        User currentUser = getByEmail(principal.getName());
        User userToUnBlock=getByUsername(username);
        if(!(currentUser.getBlocked().contains(userToUnBlock)))
        {
            throw new CustomException("user isn't blocked");
        }
        List<User> blocked = currentUser.getBlocked();
        currentUser.getBlocked().remove(userToUnBlock);
        return new ResponseEntity<>(new MessageResponse("unblocked successfully"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PostDto>> getFeed(Principal principal) {
        List<PostDto> result = new ArrayList<>();
        User currentUser = getByEmail(principal.getName());

        currentUser.getFollowing().forEach(following -> {
            following.getPosts().forEach(post -> {
                List<CommentDto> commentsDtos = post.getComments().stream()
                        .map(comment -> new CommentDto(comment.getUser().getUsername(), post.getId(), comment.getContent(), comment.getVotes(), comment.getDate()))
                        .sorted((a, b) -> (int) (b.getVotes() - a.getVotes()))
                        .toList();

                PostDto postDto = new PostDto(following.getUsername(), post.getContent(), post.getVotes(), post.getDate(), commentsDtos);
                result.add(postDto);
            });
        });

        Collections.sort(result, (a, b) -> (int) (b.getVotes() - a.getVotes()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PostDto>> getFeed(Principal principal, String username) {
        List<PostDto> result = new ArrayList<>();
        User userToSearch = getByUsername(username);
        User currentUser = getByEmail(principal.getName());

        if (userToSearch == null)
            throw new CustomException("user not found");

        if (currentUser.getBlocked().contains(userToSearch))
            throw new CustomException("can't get feed, user is blocked by you");

        if (userToSearch.getBlocked().contains(currentUser))
            throw new CustomException("can't get feed, user blocked you");

        userToSearch.getPosts().forEach(post -> {
            List<CommentDto> commentsDtos = post.getComments().stream()
                    .map(comment -> new CommentDto(comment.getUser().getUsername(), post.getId(), comment.getContent(), comment.getVotes(), comment.getDate()))
                    .sorted((a, b) -> (int) (b.getVotes() - a.getVotes()))
                    .toList();

            PostDto postDto = new PostDto(userToSearch.getUsername(), post.getContent(), post.getVotes(), post.getDate(), commentsDtos);
            result.add(postDto);
        });

        Collections.sort(result, (a, b) -> (int) (b.getVotes() - a.getVotes()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
