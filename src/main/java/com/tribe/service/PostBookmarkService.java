package com.tribe.service;

import com.tribe.dto.PostBookmarkDto;
import com.tribe.entity.PostBookmark;
import com.tribe.entity.User;
import com.tribe.util.PostBookmarkId;

import java.util.List;

public interface PostBookmarkService {
    List<PostBookmark> getAll();

    List<PostBookmark> getAllByUserId(long userId);

    PostBookmark getById(PostBookmarkId id);

    PostBookmark save(PostBookmark postBookmark);

    PostBookmark save(User authentatedUser, PostBookmarkDto postBookmarkDto);

    void deleteById(User authentatedUser, PostBookmarkId id);
}
