package com.tribe.service.impl;

import com.tribe.dto.TribeRequestDto;
import com.tribe.entity.Tribe;
import com.tribe.entity.User;
import com.tribe.entity.UserTribe;
import com.tribe.exception.ApiError;
import com.tribe.repository.TribeRepository;
import com.tribe.service.TribeService;
import com.tribe.service.UserTribeService;
import com.tribe.util.UserTribeId;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TribeServiceImpl implements TribeService {
    private final TribeRepository tribeRepository;
    private final UserTribeService userTribeService;

    public TribeServiceImpl(TribeRepository tribeRepository, @Lazy UserTribeService userTribeService) {
        this.tribeRepository = tribeRepository;
        this.userTribeService = userTribeService;
    }

    @Override
    public List<Tribe> getAll() {
        return tribeRepository.findAll();
    }

    @Override
    public Tribe getById(long id) {
        return tribeRepository.findById(id)
                .orElseThrow(() -> ApiError.badRequest("Tribe not found with id=" + id));
    }

    @Override
    public List<Tribe> getAllByUserId(long userId) {
        return tribeRepository.findAllByUserId(userId);
    }

    @Override
    public Tribe getByName(String name) {
        Tribe tribe = getByNameOrNull(name);
        if (tribe == null) {
            throw ApiError.notFound("Tribe not found with name=" + name);
        }
        return tribe;
    }

    @Override
    public Tribe getByNameOrNull(String name) {
        return tribeRepository.findByName(name);
    }

    @Override
    public Tribe save(Tribe tribe) {
        return tribeRepository.save(tribe);
    }

    @Override
    public Tribe save(User authenticatedUser, TribeRequestDto tribeRequestDto) {
        Tribe tribeByName = getByNameOrNull(tribeRequestDto.getName());
        if (tribeByName != null) {
            throw ApiError.badRequest("Tribe with this name already exists");
        }
        Tribe tribe = new Tribe(tribeRequestDto.getName(), authenticatedUser);
        Tribe savedTribe = save(tribe);
        userTribeService.save(new UserTribe(new UserTribeId(authenticatedUser.getId(), savedTribe.getId()), authenticatedUser, savedTribe));
        return savedTribe;
    }

    @Override
    public void deleteById(User authenticatedUser, long id) {
        Tribe tribe = getById(id);
        if (tribe.getUser().getId() != authenticatedUser.getId()) {
            throw ApiError.badRequest("Cannot delete the tribe, you are not the author.");
        }
        tribeRepository.deleteById(id);
    }
}
