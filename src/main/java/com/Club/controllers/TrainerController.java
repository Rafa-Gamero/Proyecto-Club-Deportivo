package com.Club.controllers;


import com.Club.model.Trainer;
import com.Club.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINER')")
    public List<Trainer> getAllTrainers() {
        return trainerService.getAllTrainers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINER')")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable Long id) {
        return trainerService.getTrainerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Trainer> createTrainer(@RequestBody Trainer trainer) {
        Trainer saved = trainerService.saveTrainer(trainer);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Trainer> updateTrainer(@PathVariable Long id, @RequestBody Trainer trainerDetails) {
        return trainerService.getTrainerById(id)
                .map(trainer -> {
                    trainer.setName(trainerDetails.getName());
                    trainer.setSpecialty(trainerDetails.getSpecialty());
                    Trainer updated = trainerService.saveTrainer(trainer);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTrainer(@PathVariable Long id) {
        if (trainerService.getTrainerById(id).isPresent()) {
            trainerService.deleteTrainerById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
