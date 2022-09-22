package com.example.service;

import com.example.dto.ContentDto;
import com.example.dto.PostDto;
import com.example.entity.Post;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface PostService {
    ResponseEntity<?> save(Principal principal, PostDto postDto);

    ResponseEntity<?> update(Principal principal, ContentDto contentDto, Long id);

    ResponseEntity<?> deleteById(long id);

    Optional<Post> getById(long id);

    public ResponseEntity<?> upVote(long id);

    public ResponseEntity<?> downVote(long id);

    ResponseEntity<List<PostDto>> getAllBookmarked(Principal principal);

    @Transactional
    ResponseEntity<?> addBookmark(Principal principal, Long id);

    @Transactional
    ResponseEntity<?> removeBookmark(Principal principal, Long id);
}
