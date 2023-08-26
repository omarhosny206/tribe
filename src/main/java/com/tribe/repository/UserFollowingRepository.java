package com.tribe.repository;

import com.tribe.entity.UserFollowing;
import com.tribe.util.UserFollowingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFollowingRepository extends JpaRepository<UserFollowing, UserFollowingId> {
}
