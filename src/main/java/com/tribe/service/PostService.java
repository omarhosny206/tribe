package com.tribe.service;

import com.tribe.dto.ContentDto;
import com.tribe.dto.PostRequestDto;
import com.tribe.entity.Post;
import com.tribe.entity.User;

import java.util.List;

public interface PostService {
    List<Post> getAll();

    List<Post> getAllByUserId(long userId);

    Post getById(long id);

    Post save(Post post);

    Post save(User authenticatedUser, PostRequestDto postRequestDto);

    Post update(User authenticatedUser, long id, ContentDto contentDto);

    Post upvote(long id);

    Post downvote(long id);

    void deleteById(User authenticatedUser, long id);
}
