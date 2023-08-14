package com.tribe.service;

import com.tribe.dto.TribeRequestDto;
import com.tribe.entity.Tribe;
import com.tribe.entity.User;

import java.util.List;

public interface TribeService {
    List<Tribe> getAll();

    Tribe getById(long id);

    List<Tribe> getAllByUserId(long userId);

    Tribe getByName(String name);

    Tribe getByNameOrNull(String name);

    Tribe save(Tribe tribe);


    Tribe save(User authenticatedUser, TribeRequestDto tribeRequestDto);

    void deleteById(User authenticatedUser, long id);
}
