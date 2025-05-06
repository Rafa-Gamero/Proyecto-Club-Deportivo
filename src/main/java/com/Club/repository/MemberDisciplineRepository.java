package com.Club.repository;

import com.Club.model.MemberDiscipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDisciplineRepository extends JpaRepository<MemberDiscipline, Long> {
}
