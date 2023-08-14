package com.tribe.repository;

import com.tribe.entity.Tribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TribeRepository extends JpaRepository<Tribe, Long> {
    Tribe findByName(String name);

    List<Tribe> findAllByUserId(long userId);
}
