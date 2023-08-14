package com.tribe.service.impl;

import com.tribe.entity.History;
import com.tribe.entity.User;
import com.tribe.exception.ApiError;
import com.tribe.repository.HistoryRepository;
import com.tribe.service.HistoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;

    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public List<History> getAll() {
        return historyRepository.findAll();
    }

    @Override
    public List<History> getAllByUserId(long userId) {
        return historyRepository.findAllByUserId(userId);
    }

    @Override
    public History getById(long id) {
        return historyRepository.findById(id)
                .orElseThrow(() -> ApiError.notFound("History not found with id=" + id));
    }

    @Override
    public void clearAll(long userId) {
        historyRepository.deleteAllByUserId(userId);
    }

    @Override
    public void clearById(User authenticatedUser, long id) {
        History history = getById(id);
        if (history.getUser().getId() != authenticatedUser.getId()) {
            throw ApiError.badRequest("Cannot delete the history, you are not the author");
        }
        historyRepository.deleteById(id);
    }
}
