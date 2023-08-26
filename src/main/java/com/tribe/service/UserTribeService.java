package com.tribe.service;

import com.tribe.dto.UserTribeDto;
import com.tribe.entity.User;
import com.tribe.entity.UserTribe;
import com.tribe.util.UserTribeId;

import java.util.List;

public interface UserTribeService {
    List<UserTribe> getAll();

    List<UserTribe> getAllByUserId(long userId);

    UserTribe getById(UserTribeId id);

    UserTribe getByIdOrNull(UserTribeId id);

    UserTribe save(UserTribe userTribe);

    UserTribe save(User authenticatedUser, UserTribeDto userTribeDto);

    void deleteById(UserTribeId id);
}
