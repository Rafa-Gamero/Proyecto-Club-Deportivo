
package com.Club.controller;

import com.Club.model.Discipline;
import com.Club.service.DisciplineService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid; // Import jakarta.validation
import lombok.RequiredArgsConstructor; // Import RequiredArgsConstructor

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/disciplines")
@RequiredArgsConstructor // Use RequiredArgsConstructor
public class DisciplineController {

    private final DisciplineService disciplineService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'TRAINER')")
    public ResponseEntity<List<Discipline>> getAllDisciplines() { // Return ResponseEntity
        List<Discipline> disciplines = disciplineService.getAllDisciplines();
        return ResponseEntity.ok(disciplines);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TRAINER')")
    public ResponseEntity<Discipline> getDisciplineById(@PathVariable Long id) { // Return ResponseEntity
        Optional<Discipline> discipline = disciplineService.getDisciplineById(id);
        return discipline.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Discipline> createDiscipline(@RequestBody @Valid Discipline discipline) { // Added @Valid
        Discipline savedDiscipline = disciplineService.saveDiscipline(discipline);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDiscipline); // Return 201
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Discipline> updateDiscipline(@PathVariable Long id, @RequestBody @Valid Discipline updated) { // Added @Valid
        Discipline updatedDiscipline = disciplineService.updateDiscipline(id, updated);
        return ResponseEntity.ok(updatedDiscipline);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteDiscipline(@PathVariable Long id) { // Return ResponseEntity
        disciplineService.deleteDiscipline(id);
        return ResponseEntity.noContent().build();
    }
}