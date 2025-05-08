package com.Club.controllers;



import com.Club.model.Discipline;
import com.Club.service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplines")
public class DisciplineController {

    @Autowired
    private DisciplineService disciplineService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINER')")
    public List<Discipline> getAllDisciplines() {
        return disciplineService.getAllDisciplines();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINER')")
    public ResponseEntity<Discipline> getDisciplineById(@PathVariable Long id) {
        return disciplineService.getDisciplineById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Discipline> createDiscipline(@RequestBody Discipline discipline) {
        Discipline saved = disciplineService.saveDiscipline(discipline);
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Discipline> updateDiscipline(@PathVariable Long id, @RequestBody Discipline disciplineDetails) {
        return disciplineService.getDisciplineById(id)
                .map(discipline -> {
                    discipline.setName(disciplineDetails.getName());
                    discipline.setSchedule(disciplineDetails.getSchedule());
                    Discipline updated = disciplineService.saveDiscipline(discipline);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteDiscipline(@PathVariable Long id) {
        if (disciplineService.getDisciplineById(id).isPresent()) {
            disciplineService.deleteDisciplineById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}