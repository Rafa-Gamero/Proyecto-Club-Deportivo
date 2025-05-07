package com.Club.service;

import com.Club.model.AdultMember;
import com.Club.model.Member;
import com.Club.repository.MemberRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MemberServiceTest {

    private final MemberRepository memberRepository = mock(MemberRepository.class);
    private final MemberService memberService = new MemberService(memberRepository);

    @Test
    void testGetMemberById() {
        AdultMember member = new AdultMember();
        member.setId(1L);
        member.setName("Ana");
        member.setEmail("ana@example.com");
        member.setPhone("123456789");
        member.setStartDate(LocalDate.now().minusYears(1));
        member.setEndDate(LocalDate.now());
        member.setPrice(new BigDecimal("100"));
        member.setOccupation("Engineer");

        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        Member result = memberService.getMemberById(1L);
        assertEquals("Ana", result.getName());
    }

    @Test
    void testDeleteMember() {
        Long id = 1L;
        memberService.deleteMember(id);
        verify(memberRepository, times(1)).deleteById(id);
    }

    @Test
    void testSaveMember() {
        AdultMember member = new AdultMember();
        member.setName("Carlos");
        member.setEmail("carlos@example.com");
        member.setPhone("987654321");
        member.setStartDate(LocalDate.now().minusMonths(2));
        member.setEndDate(LocalDate.now().plusMonths(10));
        member.setPrice(new BigDecimal("150"));
        member.setOccupation("Doctor");

        when(memberRepository.save(any(Member.class))).thenReturn(member);

        Member saved = memberService.saveMember(member);
        assertEquals("Carlos", saved.getName());
        assertEquals("Doctor", ((AdultMember) saved).getOccupation());
    }

    @Test
    void testUpdateMember() {
        AdultMember existing = new AdultMember();
        existing.setId(1L);
        existing.setName("Laura");
        existing.setEmail("laura@example.com");
        existing.setPhone("000000000");
        existing.setStartDate(LocalDate.now().minusYears(2));
        existing.setEndDate(LocalDate.now());
        existing.setPrice(new BigDecimal("120"));
        existing.setOccupation("Teacher");

        AdultMember updated = new AdultMember();
        updated.setName("Laura Updated");
        updated.setEmail("laura.updated@example.com");
        updated.setPhone("111111111");
        updated.setStartDate(LocalDate.now().minusYears(1));
        updated.setEndDate(LocalDate.now().plusMonths(6));
        updated.setPrice(new BigDecimal("130"));
        updated.setOccupation("Principal");

        when(memberRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(memberRepository.save(any(Member.class))).thenReturn(updated);

        Member result = memberService.updateMember(1L, updated);

        assertEquals("Laura Updated", result.getName());
        assertEquals("laura.updated@example.com", result.getEmail());
        assertEquals("111111111", result.getPhone());
        assertEquals(new BigDecimal("130"), result.getPrice());
    }

}
