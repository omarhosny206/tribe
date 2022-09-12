package com.example.service;

import com.example.dto.PostDto;
import com.example.entity.Post;
import com.example.entity.User;
import com.example.repository.PostRepository;
import com.example.repository.UserRepository;
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
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    public ResponseEntity<String> add(Principal principal, PostDto postDto)
    {
        String username=principal.getName();
        User user=userRepository.findByEmail(username);
        Post post=modelMapper.map(postDto,Post.class);
        user.getPosts().add(post);
        post.setUser(user);
        postRepository.save(post);
        return new ResponseEntity<>("post added successfully",HttpStatus.OK);
    }

    public ResponseEntity<?>delete(long postId)
    {
        System.out.println("post id is"+postId);
        Post post=postRepository.findById(postId).orElse(null);
        if(post==null)
        {
            return new ResponseEntity<>("post not found", HttpStatus.BAD_REQUEST);
        }
        else
        {
            postRepository.delete(post);
            return new ResponseEntity<>("post deleted",HttpStatus.OK);
        }
    }

    public Optional<Post> getById(Long postId)
    {
        return postRepository.findById(postId);
    }
}
