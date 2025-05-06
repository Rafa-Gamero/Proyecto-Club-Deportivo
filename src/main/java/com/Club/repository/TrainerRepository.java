package com.Club.repository;

import com.Club.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
}
