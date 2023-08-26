package com.tribe.service.impl;

import com.tribe.dto.UserBlockingDto;
import com.tribe.entity.User;
import com.tribe.entity.UserBlocking;
import com.tribe.exception.ApiError;
import com.tribe.repository.UserBlockingRepository;
import com.tribe.service.UserBlockingService;
import com.tribe.service.UserService;
import com.tribe.util.UserBlockingId;

import java.util.List;

public class UserBlockingServiceImpl implements UserBlockingService {
    private final UserBlockingRepository userBlockingRepository;
    private final UserService userService;

    public UserBlockingServiceImpl(UserBlockingRepository userBlockingRepository, UserService userService) {
        this.userBlockingRepository = userBlockingRepository;
        this.userService = userService;
    }

    @Override
    public List<UserBlocking> getAll() {
        return userBlockingRepository.findAll();
    }

    @Override
    public UserBlocking getById(UserBlockingId id) {
        UserBlocking userBlocking = getByIdOrNull(id);
        if (userBlocking == null) {
            throw ApiError.notFound("UserBlocking not found with id=(" + id.getBlockerId() + ", " + id.getBlockedId() + ")");
        }
        return userBlocking;
    }

    @Override
    public UserBlocking getByIdOrNull(UserBlockingId id) {
        return userBlockingRepository.findById(id).orElse(null);
    }

    @Override
    public UserBlocking save(UserBlocking userBlocking) {
        return userBlockingRepository.save(userBlocking);
    }

    @Override
    public UserBlocking save(User authenticatedUser, UserBlockingDto userBlockingDto) {
        User userToBlock = userService.getById(userBlockingDto.getUserId());
        UserBlocking userBlocking = new UserBlocking(
                new UserBlockingId(authenticatedUser.getId(), userToBlock.getId()),
                authenticatedUser,
                userToBlock
        );
        return save(userBlocking);
    }

    @Override
    public void deleteById(User authenticatedUser, UserBlockingId id) {
        UserBlocking userBlocking = getById(id);
        if (userBlocking.getBlocker().getId() != authenticatedUser.getId()) {
            System.out.println("Cannot delete UserBlocking, you are not the author.");
        }
        userBlockingRepository.deleteById(id);
    }
}
