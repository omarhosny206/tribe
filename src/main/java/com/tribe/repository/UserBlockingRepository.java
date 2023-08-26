package com.tribe.repository;

import com.tribe.entity.UserBlocking;
import com.tribe.util.UserBlockingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBlockingRepository extends JpaRepository<UserBlocking, UserBlockingId> {
}
