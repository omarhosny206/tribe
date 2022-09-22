package com.example.repository;

import com.example.dto.HistoryId;
import com.example.entity.History;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface HistoryRepository extends JpaRepository<History, HistoryId> {
    @Query(nativeQuery = true, value = "delete from history where user_id= ?1")
    @Modifying
    @Transactional
    public void deleteByUser(Long userId);
}
