package com.example.repository;

import com.example.dto.HistoryId;
import com.example.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface HistoryRepository extends JpaRepository<History, HistoryId> {
    @Query(nativeQuery = true, value = "DELETE FROM history WHERE user_id= ?1")
    @Modifying
    @Transactional
    public void deleteByUserId(Long userId);

    @Query(nativeQuery = true, value = "SELECT content FROM history WHERE user_id= ?1")
    @Transactional
    public List<String> getByUserId(Long userId);
}
