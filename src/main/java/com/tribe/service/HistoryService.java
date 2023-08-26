package com.tribe.service;

import com.tribe.dto.ContentDto;
import com.tribe.entity.History;
import com.tribe.entity.User;

import java.util.List;

public interface HistoryService {
    List<History> getAll();

    List<History> getAllByUserId(long userId);

    History getById(long id);

    History save(History history);

    History save(User authenticatedUser, ContentDto contentDto);

    void clearAll(long userId);

    void clearById(User authenticatedUser, long id);
}
