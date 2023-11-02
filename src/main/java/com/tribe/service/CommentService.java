package com.tribe.service;

import com.tribe.dto.CommentRequestDto;
import com.tribe.dto.ContentDto;
import com.tribe.entity.Comment;
import com.tribe.entity.User;

import java.util.List;

public interface CommentService {
    List<Comment> getAll();

    Comment getById(long id);

    List<Comment> getAllByUserId(long userId);

    List<Comment> getAllByPostId(long postId);

    Comment save(Comment comment);

    Comment save(User authenticatedUser, CommentRequestDto commentRequestDto);

    Comment update(User authenticatedUser, long id, ContentDto contentDto);

    Comment upvote(long id);

    Comment downvote(long id);

    void deleteById(User authenticatedUser, long id);

    void checkAuthority(User authenticatedUser, Comment comment);
}
