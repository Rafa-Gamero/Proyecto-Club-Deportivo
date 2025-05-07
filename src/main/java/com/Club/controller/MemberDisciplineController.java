package com.Club.controller;

import com.Club.model.MemberDiscipline;
import com.Club.service.MemberDisciplineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member-disciplines")
@RequiredArgsConstructor
public class MemberDisciplineController {

    private final MemberDisciplineService memberDisciplineService;

    @PostMapping
    public ResponseEntity<MemberDiscipline> assignDisciplineToMember(@RequestBody MemberDiscipline association) {
        MemberDiscipline savedAssociation = memberDisciplineService.assignDiscipline(association);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAssociation);
    }

    @GetMapping
    public ResponseEntity<List<MemberDiscipline>> getAll() {
        List<MemberDiscipline> associations = memberDisciplineService.getAll();
        return ResponseEntity.ok(associations);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        memberDisciplineService.delete(id);
        return ResponseEntity.noContent().build();
    }
}