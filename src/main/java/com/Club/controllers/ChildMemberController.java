package com.Club.controllers;



import com.Club.model.ChildMember;
import com.Club.service.ChildMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/child-members")
public class ChildMemberController {

    @Autowired
    private ChildMemberService childMemberService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<ChildMember> getAllChildMembers() {
        return childMemberService.getAllChildMembers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINER')")
    public ResponseEntity<ChildMember> getChildMemberById(@PathVariable Long id) {
        return childMemberService.getChildMemberById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ChildMember> createChildMember(@RequestBody ChildMember childMember) {
        ChildMember saved = childMemberService.saveChildMember(childMember);
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ChildMember> updateChildMember(@PathVariable Long id, @RequestBody ChildMember childDetails) {
        return childMemberService.getChildMemberById(id)
                .map(member -> {
                    member.setName(childDetails.getName());
                    member.setEmail(childDetails.getEmail());
                    member.setPhone(childDetails.getPhone());
                    member.setStartDate(childDetails.getStartDate());
                    member.setEndDate(childDetails.getEndDate());
                    member.setPrice(childDetails.getPrice());
                    member.setGuardianName(childDetails.getGuardianName());
                    ChildMember updated = childMemberService.saveChildMember(member);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteChildMember(@PathVariable Long id) {
        if (childMemberService.getChildMemberById(id).isPresent()) {
            childMemberService.deleteChildMemberById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}