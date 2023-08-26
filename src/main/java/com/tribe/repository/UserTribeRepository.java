package com.tribe.repository;

import com.tribe.entity.UserTribe;
import com.tribe.util.UserTribeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTribeRepository extends JpaRepository<UserTribe, UserTribeId> {
    List<UserTribe> findAllByUserId(long userId);
}
