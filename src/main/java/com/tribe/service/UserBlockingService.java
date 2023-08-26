package com.tribe.service;

import com.tribe.dto.UserBlockingDto;
import com.tribe.entity.User;
import com.tribe.entity.UserBlocking;
import com.tribe.util.UserBlockingId;

import java.util.List;

public interface UserBlockingService {
    List<UserBlocking> getAll();

    UserBlocking getById(UserBlockingId id);

    UserBlocking getByIdOrNull(UserBlockingId id);

    UserBlocking save(UserBlocking userBlocking);

    UserBlocking save(User authenticatedUser, UserBlockingDto userBlockingDto);

    void deleteById(User authenticatedUser, UserBlockingId id);
}
