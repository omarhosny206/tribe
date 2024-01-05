package com.tribe.repository;

import com.tribe.entity.Tribe;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TribeRepository extends JpaRepository<Tribe, Long> {
    @EntityGraph(value = "Tribe.user")
    @Override
    Optional<Tribe> findById(Long aLong);

    Tribe findByName(String name);

    List<Tribe> findAllByUserId(long userId);
}
