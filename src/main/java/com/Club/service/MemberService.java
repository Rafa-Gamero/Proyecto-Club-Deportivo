package com.Club.service;

import com.Club.model.Member;
import com.Club.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }

    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    public Member updateMember(Long id, Member updatedMember) {
        return memberRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedMember.getName());
                    existing.setEmail(updatedMember.getEmail());
                    existing.setPhone(updatedMember.getPhone());
                    existing.setStartDate(updatedMember.getStartDate());
                    existing.setEndDate(updatedMember.getEndDate());
                    existing.setPrice(updatedMember.getPrice());
                    return memberRepository.save(existing);
                }).orElseThrow(() -> new RuntimeException("Member not found"));
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
