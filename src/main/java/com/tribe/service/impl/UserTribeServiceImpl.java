package com.tribe.service.impl;

import com.tribe.dto.UserTribeDto;
import com.tribe.entity.Tribe;
import com.tribe.entity.User;
import com.tribe.entity.UserTribe;
import com.tribe.exception.ApiError;
import com.tribe.repository.UserTribeRepository;
import com.tribe.service.TribeService;
import com.tribe.service.UserTribeService;
import com.tribe.util.UserTribeId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTribeServiceImpl implements UserTribeService {
    private final UserTribeRepository userTribeRepository;
    private final TribeService tribeService;

    public UserTribeServiceImpl(UserTribeRepository userTribeRepository, TribeService tribeService) {
        this.userTribeRepository = userTribeRepository;
        this.tribeService = tribeService;
    }

    @Override
    public List<UserTribe> getAll() {
        return userTribeRepository.findAll();
    }

    @Override
    public List<UserTribe> getAllByUserId(long userId) {
        return userTribeRepository.findAllByUserId(userId);
    }

    @Override
    public UserTribe getById(UserTribeId id) {
        UserTribe userTribe = getByIdOrNull(id);
        if (userTribe == null) {
            throw ApiError.notFound("UserTribe not found with id=(" + id.getUserId() + ", " + id.getTribeId() + ")");
        }
        return userTribe;
    }

    @Override
    public UserTribe getByIdOrNull(UserTribeId id) {
        return userTribeRepository.findById(id).orElse(null);
    }

    @Override
    public UserTribe save(UserTribe userTribe) {
        return userTribeRepository.save(userTribe);
    }

    @Override
    public UserTribe save(User authenticatedUser, UserTribeDto userTribeDto) {
        Tribe tribe = tribeService.getByName(userTribeDto.getTribe());
        UserTribe userTribe = new UserTribe(
                new UserTribeId(authenticatedUser.getId(), tribe.getId()),
                authenticatedUser,
                tribe
        );
        return save(userTribe);
    }

    @Override
    public void deleteById(UserTribeId id) {
        userTribeRepository.deleteById(id);
    }
}
