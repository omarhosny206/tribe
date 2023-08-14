package com.tribe.service;

import com.tribe.entity.History;
import com.tribe.entity.User;

import java.util.List;

public interface HistoryService {
    List<History> getAll();

    List<History> getAllByUserId(long userId);

    History getById(long id);

    void clearAll(long userId);

    void clearById(User authenticatedUser, long id);
}
