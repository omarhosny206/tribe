package com.tribe.service;

import com.tribe.dto.PostBookmarkDto;
import com.tribe.entity.PostBookmark;
import com.tribe.entity.User;
import com.tribe.util.PostBookmarkId;

import java.util.List;

public interface PostBookmarkService {
    List<PostBookmark> getAll();

    PostBookmark getById(PostBookmarkId id);

    List<PostBookmark> getAllByUserId(long userId);

    List<PostBookmark> getAllByPostId(long postId);

    PostBookmark save(PostBookmark postBookmark);

    PostBookmark save(User authentatedUser, PostBookmarkDto postBookmarkDto);

    void deleteById(User authentatedUser, PostBookmarkId id);

    void checkAuthority(User authentatedUser, PostBookmark postBookmark);
}
