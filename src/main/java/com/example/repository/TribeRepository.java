package com.example.repository;

import com.example.entity.Tribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TribeRepository extends JpaRepository<Tribe,Long> {
}
