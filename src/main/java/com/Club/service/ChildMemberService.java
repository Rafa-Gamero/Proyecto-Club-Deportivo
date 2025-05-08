package com.Club.service;



import com.Club.model.ChildMember;
import com.Club.repository.ChildMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChildMemberService {

    @Autowired
    private ChildMemberRepository childMemberRepository;

    public List<ChildMember> getAllChildMembers() {
        return childMemberRepository.findAll();
    }

    public Optional<ChildMember> getChildMemberById(Long id) {
        return childMemberRepository.findById(id);
    }

    public ChildMember saveChildMember(ChildMember childMember) {
        return childMemberRepository.save(childMember);
    }

    public void deleteChildMemberById(Long id) {
        childMemberRepository.deleteById(id);
    }
}
