package com.Club.controllers;


import com.Club.model.AdultMember;
import com.Club.service.AdultMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adult-members")
public class AdultMemberController {

    @Autowired
    private AdultMemberService adultMemberService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<AdultMember> getAllAdultMembers() {
        return adultMemberService.getAllAdultMembers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINER')")
    public ResponseEntity<AdultMember> getAdultMemberById(@PathVariable Long id) {
        return adultMemberService.getAdultMemberById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AdultMember> createAdultMember(@RequestBody AdultMember adultMember) {
        AdultMember saved = adultMemberService.saveAdultMember(adultMember);
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AdultMember> updateAdultMember(@PathVariable Long id, @RequestBody AdultMember adultDetails) {
        return adultMemberService.getAdultMemberById(id)
                .map(member -> {
                    member.setName(adultDetails.getName());
                    member.setEmail(adultDetails.getEmail());
                    member.setPhone(adultDetails.getPhone());
                    member.setStartDate(adultDetails.getStartDate());
                    member.setEndDate(adultDetails.getEndDate());
                    member.setPrice(adultDetails.getPrice());
                    member.setOccupation(adultDetails.getOccupation());
                    AdultMember updated = adultMemberService.saveAdultMember(member);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAdultMember(@PathVariable Long id) {
        if (adultMemberService.getAdultMemberById(id).isPresent()) {
            adultMemberService.deleteAdultMemberById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}