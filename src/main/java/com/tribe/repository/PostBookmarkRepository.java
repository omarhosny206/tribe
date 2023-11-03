package com.tribe.repository;

import com.tribe.entity.PostBookmark;
import com.tribe.util.PostBookmarkId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostBookmarkRepository extends JpaRepository<PostBookmark, PostBookmarkId> {
    List<PostBookmark> findAllByUserId(long userId);

    List<PostBookmark> findAllByPostId(long postId);
}
