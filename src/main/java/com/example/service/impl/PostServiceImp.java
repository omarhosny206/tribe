package com.example.service.impl;

import com.example.dto.CommentDto;
import com.example.dto.ContentDto;
import com.example.dto.PostDto;
import com.example.entity.Post;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.repository.PostRepository;
import com.example.repository.UserRepository;
import com.example.response.MessageResponse;
import com.example.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    public ResponseEntity<?> save(Principal principal, PostDto postDto) {
        String username = principal.getName();
        User user = userRepository.findByEmail(username);
        Post post = modelMapper.map(postDto, Post.class);
        user.getPosts().add(post);
        post.setUser(user);
        postRepository.save(post);
        return new ResponseEntity<>(new MessageResponse("post added successfully"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(Principal principal, ContentDto contentDto, Long id) {
        Post post = getById(id).orElse(null);
        User currentUser = userRepository.findByEmail(principal.getName());

        if (post == null)
            throw new CustomException("can't update, post not found");

        if (post.getUser() != currentUser)
            throw new CustomException("can't update, you aren't the author of this post");

        post.setContent(contentDto.getContent());
        return new ResponseEntity<>(new MessageResponse("Updated successfully"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteById(long id) {
        System.out.println("post id is" + id);
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            throw new CustomException("post not found");
        } else {
            postRepository.delete(post);
            return new ResponseEntity<>(new MessageResponse("post deleted"), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> upVote(long id) {
        System.out.println("post id is" + id);
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            throw new CustomException("post not found");
        } else {
            post.setVotes(post.getVotes() + 1);
            return new ResponseEntity<>(new MessageResponse("upvoted successfully"), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> downVote(long id) {
        System.out.println("post id is" + id);
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            throw new CustomException("post not found");
        }
        if (post.getVotes() == 0) {
            throw new CustomException("can't downvote post, post has 0 votes");
        }
        post.setVotes(post.getVotes() - 1);
        return new ResponseEntity<>(new MessageResponse("downvoted successfully"), HttpStatus.OK);
    }

    @Override
    public Optional<Post> getById(long id) {
        return postRepository.findById(id);
    }

    @Override
    public ResponseEntity<List<PostDto>> getAllBookmarked(Principal principal) {
        List<PostDto> result = new ArrayList<>();
        User currentUser = userRepository.findByEmail(principal.getName());
        List<Post> bookmark = currentUser.getBookmark();

        bookmark.forEach(post -> {
            List<CommentDto> commentsDtos = post.getComments().stream()
                    .map(comment -> new CommentDto(comment.getUser().getUsername(), post.getId(), comment.getContent(), comment.getVotes(), comment.getDate()))
                    .sorted((a, b) -> (int) (b.getVotes() - a.getVotes()))
                    .toList();

            PostDto postDto = new PostDto(post.getUser().getUsername(), post.getContent(), post.getVotes(), post.getDate(), commentsDtos);
            System.out.println(postDto);
            result.add(postDto);
        });

        Collections.sort(result, (a, b) -> (int) (b.getVotes() - a.getVotes()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> addBookmark(Principal principal, Long id) {
        Post post = getById(id).orElse(null);
        User currentUser = userRepository.findByEmail(principal.getName());

        if (post == null)
            throw new CustomException("Can't bookmark, post not found");

        List<Post> bookmark = currentUser.getBookmark();
        if (bookmark.contains(post))
            throw new CustomException("Can't bookmark, post is already bookmarked");

        bookmark.add(post);
        return new ResponseEntity<>(new MessageResponse("Bookmarked successfully"), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> removeBookmark(Principal principal, Long id) {
        Post post = getById(id).orElse(null);
        User currentUser = userRepository.findByEmail(principal.getName());

        if (post == null)
            throw new CustomException("Can't remove bookmark, post not found");

        List<Post> bookmark = currentUser.getBookmark();
        if (!bookmark.contains(post))
            throw new CustomException("Can't remove bookmark, post is not bookmarked");

        bookmark.remove(post);
        return new ResponseEntity<>(new MessageResponse("Removed bookmark successfully"), HttpStatus.OK);
    }
}
