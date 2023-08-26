package com.tribe.service.impl;

import com.tribe.dto.UserFollowingDto;
import com.tribe.entity.User;
import com.tribe.entity.UserFollowing;
import com.tribe.exception.ApiError;
import com.tribe.repository.UserFollowingRepository;
import com.tribe.service.UserBlockingService;
import com.tribe.service.UserFollowingService;
import com.tribe.service.UserService;
import com.tribe.util.UserBlockingId;
import com.tribe.util.UserFollowingId;

import java.util.List;

public class UserFollowingServiceImpl implements UserFollowingService {
    private final UserFollowingRepository userFollowingRepository;
    private final UserService userService;
    private final UserBlockingService userBlockingService;

    public UserFollowingServiceImpl(UserFollowingRepository userFollowingRepository, UserService userService, UserBlockingService userBlockingService) {
        this.userFollowingRepository = userFollowingRepository;
        this.userService = userService;
        this.userBlockingService = userBlockingService;
    }

    @Override
    public List<UserFollowing> getAll() {
        return userFollowingRepository.findAll();
    }

    @Override
    public UserFollowing getById(UserFollowingId id) {
        UserFollowing userFollowing = getByIdOrNull(id);
        if (userFollowing == null) {
            throw ApiError.notFound("UserFollowing not found with id=(" + id.getFollowerId() + ", " + id.getFolloweeId() + ")");
        }
        return userFollowing;
    }

    @Override
    public UserFollowing getByIdOrNull(UserFollowingId id) {
        return userFollowingRepository.findById(id).orElse(null);
    }

    @Override
    public UserFollowing save(UserFollowing userFollowing) {
        return userFollowingRepository.save(userFollowing);
    }

    @Override
    public UserFollowing save(User authenticatedUser, UserFollowingDto userFollowingDto) {
        if (userBlockingService.getByIdOrNull(new UserBlockingId(authenticatedUser.getId(), userFollowingDto.getUserId())) != null) {
            throw ApiError.badRequest("Cannot save UserFollowing, you blocked this user.");
        }

        if (userBlockingService.getByIdOrNull(new UserBlockingId(userFollowingDto.getUserId(), authenticatedUser.getId())) != null) {
            throw ApiError.badRequest("Cannot save UserFollowing, this user blocked you.");
        }

        User userToFollow = userService.getById(userFollowingDto.getUserId());
        UserFollowing userFollowing = new UserFollowing(
                new UserFollowingId(authenticatedUser.getId(), userToFollow.getId()),
                authenticatedUser,
                userToFollow
        );
        return save(userFollowing);
    }

    @Override
    public void deleteById(User authenticatedUser, UserFollowingId id) {
        UserFollowing userFollowing = getByIdOrNull(id);
        if (userFollowing.getFollower().getId() != authenticatedUser.getId()) {
            throw ApiError.badRequest("Cannot delete UserFollowing, you are not the author");
        }
        userFollowingRepository.deleteById(id);
    }
}
