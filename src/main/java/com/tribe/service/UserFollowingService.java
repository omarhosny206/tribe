package com.tribe.service;

import com.tribe.dto.UserFollowingDto;
import com.tribe.entity.User;
import com.tribe.entity.UserFollowing;
import com.tribe.util.UserFollowingId;

import java.util.List;

public interface UserFollowingService {
    List<UserFollowing> getAll();

    UserFollowing getById(UserFollowingId id);

    UserFollowing getByIdOrNull(UserFollowingId id);

    UserFollowing save(UserFollowing userFollowing);

    UserFollowing save(User authenticatedUser, UserFollowingDto userFollowingDto);

    void deleteById(User authenticatedUser, UserFollowingId id);
}
