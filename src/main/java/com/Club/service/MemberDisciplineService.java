package com.Club.service;



import com.Club.model.MemberDiscipline;
import com.Club.repository.MemberDisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberDisciplineService {

    @Autowired
    private MemberDisciplineRepository memberDisciplineRepository;

    public List<MemberDiscipline> getAllEnrollments() {
        return memberDisciplineRepository.findAll();
    }

    public Optional<MemberDiscipline> getEnrollmentById(Long id) {
        return memberDisciplineRepository.findById(id);
    }

    public MemberDiscipline saveEnrollment(MemberDiscipline enrollment) {
        return memberDisciplineRepository.save(enrollment);
    }

    public void deleteEnrollmentById(Long id) {
        memberDisciplineRepository.deleteById(id);
    }
}
