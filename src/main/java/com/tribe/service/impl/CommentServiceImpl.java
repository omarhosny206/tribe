package com.tribe.service.impl;

import com.tribe.dto.CommentRequestDto;
import com.tribe.dto.ContentDto;
import com.tribe.entity.Comment;
import com.tribe.entity.Post;
import com.tribe.entity.User;
import com.tribe.exception.ApiError;
import com.tribe.repository.CommentRepository;
import com.tribe.service.CommentService;
import com.tribe.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;

    public CommentServiceImpl(CommentRepository commentRepository, PostService postService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
    }


    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getById(long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> ApiError.notFound("Comment not found with id=" + id));
    }

    @Override
    public List<Comment> getAllByUserId(long userId) {
        return commentRepository.findAllByUserId(userId);
    }

    @Override
    public List<Comment> getAllByPostId(long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment save(User authenticatedUser, CommentRequestDto commentRequestDto) {
        Post post = postService.getById(commentRequestDto.getPostId());
        Comment comment = new Comment(commentRequestDto.getContent(), post, authenticatedUser);
        return save(comment);
    }

    @Override
    public Comment update(User authenticatedUser, long id, ContentDto contentDto) {
        Comment comment = getById(id);
        checkAuthority(authenticatedUser, comment);
        comment.setContent(contentDto.getContent());
        return save(comment);
    }

    @Override
    public Comment upvote(long id) {
        Comment comment = getById(id);
        comment.setVotes(comment.getVotes() + 1);
        return save(comment);
    }

    @Override
    public Comment downvote(long id) {
        Comment comment = getById(id);
        if (comment.getVotes() == 0) {
            throw ApiError.badRequest("Cannot downvote the comment, current votes equal 0");
        }
        comment.setVotes(comment.getVotes() - 1);
        return save(comment);
    }

    @Override
    public void deleteById(User authenticatedUser, long id) {
        Comment comment = getById(id);
        checkAuthority(authenticatedUser, comment);
        commentRepository.deleteById(id);
    }

    @Override
    public void checkAuthority(User authenticatedUser, Comment comment) {
        if (comment.getUser().getId() != authenticatedUser.getId()) {
            throw ApiError.badRequest("Cannot do this action, you are not the author.");
        }
    }
}
