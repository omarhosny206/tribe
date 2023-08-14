package com.tribe.service.impl;

import com.tribe.entity.User;
import com.tribe.exception.ApiError;
import com.tribe.repository.UserRepository;
import com.tribe.service.UserService;
import com.tribe.util.CustomUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> ApiError.notFound("User not found with id=" + id));
    }

    @Override
    public User getByEmail(String email) {
        User user = getByEmailOrNull(email);
        if (user == null) {
            throw ApiError.notFound("User not found with email=" + email);
        }
        return user;
    }

    @Override
    public User getByEmailOrNull(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getByUsername(String username) {
        User user = getByUsernameOrNull(username);
        if (user == null) {
            throw ApiError.notFound("User not found with username=" + username);
        }
        return user;
    }

    @Override
    public User getByUsernameOrNull(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getByEmailOrNull(email);
        if (user == null) {
            throw ApiError.unauthorized("Bad credentials");
        }
        return new CustomUser(user);
    }
}
