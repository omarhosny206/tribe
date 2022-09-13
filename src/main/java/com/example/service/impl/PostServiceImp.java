package com.example.service.impl;

import com.example.dto.PostDto;
import com.example.entity.Post;
import com.example.entity.User;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.PostRepository;
import com.example.repository.UserRepository;
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
public class PostServiceImp implements PostService {
    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public PostServiceImp(PostRepository postRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<String> save(Principal principal, PostDto postDto) {
        String username = principal.getName();
        User user = userRepository.findByEmail(username);
        Post post = modelMapper.map(postDto, Post.class);
        user.getPosts().add(post);
        post.setUser(user);
        postRepository.save(post);
        return new ResponseEntity<>("post added successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteById(long id) {
        System.out.println("post id is" + id);
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            throw new ResourceNotFoundException("post not found");
        } else {
            postRepository.delete(post);
            return new ResponseEntity<>("post deleted", HttpStatus.OK);
        }
    }

    @Override
    public Optional<Post> getById(long id) {
        return postRepository.findById(id);
    }
}
