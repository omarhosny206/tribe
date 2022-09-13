package com.example.service.impl;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
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
    public ResponseEntity<String> follow(Principal principal, String username) {
        User currentUser = getByEmail(principal.getName());
        User userToFollow = getByUsername(username);

        if(userToFollow == null) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
        currentUser.getFollowing().add(userToFollow);
        userToFollow.getFollowers().add(currentUser);
        return new ResponseEntity<>("Followed successfully", HttpStatus.OK);

    }

    @Transactional
    @Override
    public ResponseEntity<String> unfollow(Principal principal, String username) {
        User currentUser = getByEmail(principal.getName());
        User userToUnFollow=getByUsername(username);

        if(userToUnFollow==null)
        {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
        currentUser.getFollowing().remove(userToUnFollow);
        userToUnFollow.getFollowers().remove(currentUser);
        return new ResponseEntity<>("UnFollowed successfully",HttpStatus.OK);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(username);
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),new ArrayList<>());
    }
}
