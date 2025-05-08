package com.Club.controllers;



import com.Club.model.MemberDiscipline;
import com.Club.service.MemberDisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class MemberDisciplineController {

    @Autowired
    private MemberDisciplineService memberDisciplineService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINER')")
    public List<MemberDiscipline> getAllEnrollments() {
        return memberDisciplineService.getAllEnrollments();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINER')")
    public ResponseEntity<MemberDiscipline> getEnrollmentById(@PathVariable Long id) {
        return memberDisciplineService.getEnrollmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MemberDiscipline> enrollMemberInDiscipline(@RequestBody MemberDiscipline enrollment) {
        MemberDiscipline saved = memberDisciplineService.saveEnrollment(enrollment);
        return ResponseEntity.status(201).body(saved);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
        if (memberDisciplineService.getEnrollmentById(id).isPresent()) {
            memberDisciplineService.deleteEnrollmentById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}