package com.tribe.repository;

import com.tribe.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {
    void deleteAllByUserId(Long userId);
    public List<History> findAllByUserId(Long userId);
}
