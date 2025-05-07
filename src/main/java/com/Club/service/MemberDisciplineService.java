package com.Club.service;

import com.Club.model.MemberDiscipline;
import com.Club.repository.MemberDisciplineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberDisciplineService {

    private final MemberDisciplineRepository memberDisciplineRepository;

    public MemberDiscipline assignDiscipline(MemberDiscipline association) {
        return memberDisciplineRepository.save(association);
    }

    public List<MemberDiscipline> getAll() {
        return memberDisciplineRepository.findAll();
    }

    public void delete(Long id) {
        memberDisciplineRepository.deleteById(id);
    }
}

