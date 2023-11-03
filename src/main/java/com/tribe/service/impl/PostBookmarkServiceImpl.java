package com.tribe.service.impl;

import com.tribe.dto.PostBookmarkDto;
import com.tribe.entity.Post;
import com.tribe.entity.PostBookmark;
import com.tribe.entity.User;
import com.tribe.exception.ApiError;
import com.tribe.repository.PostBookmarkRepository;
import com.tribe.service.PostBookmarkService;
import com.tribe.service.PostService;
import com.tribe.util.PostBookmarkId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostBookmarkServiceImpl implements PostBookmarkService {
    private final PostBookmarkRepository postBookmarkRepository;
    private final PostService postService;

    public PostBookmarkServiceImpl(PostBookmarkRepository postBookmarkRepository, PostService postService) {
        this.postBookmarkRepository = postBookmarkRepository;
        this.postService = postService;
    }

    @Override
    public List<PostBookmark> getAll() {
        return postBookmarkRepository.findAll();
    }

    @Override
    public List<PostBookmark> getAllByUserId(long userId) {
        return postBookmarkRepository.findAllByUserId(userId);
    }

    @Override
    public List<PostBookmark> getAllByPostId(long postId) {
        return postBookmarkRepository.findAllByPostId(postId);
    }

    @Override
    public PostBookmark getById(PostBookmarkId id) {
        return postBookmarkRepository.findById(id)
                .orElseThrow(() -> ApiError.notFound("PostBookmark not found with id=(" + id.getUserId() + ", " + id.getPostId() + ")"));
    }

    @Override
    public PostBookmark save(PostBookmark postBookmark) {
        return postBookmarkRepository.save(postBookmark);
    }

    @Override
    public PostBookmark save(User authentatedUser, PostBookmarkDto postBookmarkDto) {
        Post post = postService.getById(postBookmarkDto.getPostId());
        PostBookmark postBookmark = new PostBookmark(
                new PostBookmarkId(authentatedUser.getId(), post.getId()),
                authentatedUser,
                post
        );
        return save(postBookmark);
    }

    @Override
    public void deleteById(User authentatedUser, PostBookmarkId id) {
        PostBookmark postBookmark = getById(id);
        checkAuthority(authentatedUser, postBookmark);
        postBookmarkRepository.deleteById(id);
    }

    @Override
    public void checkAuthority(User authentatedUser, PostBookmark postBookmark) {
        if (postBookmark.getUser().getId() != authentatedUser.getId()) {
            throw ApiError.forbidden("Cannot delete the post bookmark, you are not the author");
        }
    }
}
