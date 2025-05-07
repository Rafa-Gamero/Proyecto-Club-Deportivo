package com.Club.controller;

import com.Club.model.MemberDiscipline;
import com.Club.service.MemberDisciplineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member-disciplines")
@RequiredArgsConstructor
public class MemberDisciplineController {

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

