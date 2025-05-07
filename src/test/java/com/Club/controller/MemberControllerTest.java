package com.Club.controller;



import com.Club.model.AdultMember;
import com.Club.model.Member;
import com.Club.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private MemberService memberService;

    @Autowired
    private ObjectMapper objectMapper;

    private Member createTestMember() {
        AdultMember member = new AdultMember();
        member.setId(1L);
        member.setName("Ana");
        member.setEmail("ana@example.com");
        member.setPhone("123456789");
        member.setStartDate(LocalDate.now().minusYears(1));
        member.setEndDate(LocalDate.now());
        member.setPrice(new BigDecimal("100"));
        member.setOccupation("Engineer");
        return member;
    }

    @Test
    void testGetAllMembers() throws Exception {
        Member member = createTestMember();

        when(memberService.getAllMembers()).thenReturn(List.of(member));

        mockMvc.perform(get("/api/members")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Ana"));
    }

    @Test
    void testGetMemberById() throws Exception {
        Member member = createTestMember();

        when(memberService.getMemberById(1L)).thenReturn(member);

        mockMvc.perform(get("/api/members/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("ana@example.com"));
    }

    @Test
    void testCreateMember() throws Exception {
        Member member = createTestMember();

        when(memberService.saveMember(any(Member.class))).thenReturn(member);

        mockMvc.perform(post("/api/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(member)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ana"));
    }

    @Test
    void testUpdateMember() throws Exception {
        Member member = createTestMember();
        member.setName("Ana Updated");

        when(memberService.updateMember(any(Long.class), any(Member.class))).thenReturn(member);

        mockMvc.perform(put("/api/members/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(member)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ana Updated"));
    }

    @Test
    void testDeleteMember() throws Exception {
        mockMvc.perform(delete("/api/members/1"))
                .andExpect(status().isOk());
    }
}


