package com.tribe.controller;

import com.tribe.dto.MessageDto;
import com.tribe.dto.PostBookmarkDto;
import com.tribe.entity.PostBookmark;
import com.tribe.service.PostBookmarkService;
import com.tribe.util.AuthenticationUser;
import com.tribe.util.PostBookmarkId;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts-bookmarks")
public class PostBookmarkController {
    private final PostBookmarkService postBookmarkService;

    public PostBookmarkController(PostBookmarkService postBookmarkService) {
        this.postBookmarkService = postBookmarkService;
    }

    @GetMapping("/")
    public ResponseEntity<List<PostBookmark>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postBookmarkService.getAll());
    }

    @GetMapping("/me")
    public ResponseEntity<List<PostBookmark>> getAllByUserId(Authentication authentication) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postBookmarkService.getAllByUserId(AuthenticationUser.get(authentication).getId()));
    }

    @PostMapping("/")
    public ResponseEntity<PostBookmark> save(Authentication authentication, @Valid @RequestBody PostBookmarkDto postBookmarkDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postBookmarkService.save(AuthenticationUser.get(authentication), postBookmarkDto));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<MessageDto> deleteById(Authentication authentication, @PathVariable long postId) {
        postBookmarkService.deleteById(AuthenticationUser.get(authentication), new PostBookmarkId(AuthenticationUser.get(authentication).getId(), postId));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MessageDto("Success"));
    }
}
