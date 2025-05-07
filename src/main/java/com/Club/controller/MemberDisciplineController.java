package com.Club.controller;

import com.Club.model.Discipline;
import com.Club.model.Member;
import com.Club.model.MemberDiscipline;
import com.Club.service.MemberDisciplineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import lombok.*;
import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "member_discipline")
@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RestController
@RequestMapping("/api/member-disciplines")
@RequiredArgsConstructor
public class MemberDisciplineController {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "discipline_id", nullable = false)
    private Discipline discipline;

    private final MemberDisciplineService service;

    @PostMapping
    public MemberDiscipline assignDisciplineToMember(@RequestBody MemberDiscipline association) {
        return service.assignDiscipline(association);
    }

    @GetMapping
    public List<MemberDiscipline> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

