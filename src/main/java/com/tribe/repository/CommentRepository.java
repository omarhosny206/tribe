package com.tribe.repository;

import com.tribe.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByUserId(long userId);

    List<Comment> findAllByPostId(long postId);
}
