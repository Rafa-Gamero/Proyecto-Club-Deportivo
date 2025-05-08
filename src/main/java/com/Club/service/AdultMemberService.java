package com.Club.service;



import com.Club.model.AdultMember;
import com.Club.repository.AdultMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdultMemberService {

    @Autowired
    private AdultMemberRepository adultMemberRepository;

    public List<AdultMember> getAllAdultMembers() {
        return adultMemberRepository.findAll();
    }

    public Optional<AdultMember> getAdultMemberById(Long id) {
        return adultMemberRepository.findById(id);
    }

    public AdultMember saveAdultMember(AdultMember adultMember) {
        return adultMemberRepository.save(adultMember);
    }

    public void deleteAdultMemberById(Long id) {
        adultMemberRepository.deleteById(id);
    }
}
