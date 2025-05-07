package com.Club.controller;

import com.Club.model.Discipline;
import com.Club.service.DisciplineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplines")
@RequiredArgsConstructor
public class DisciplineController {

    private final DisciplineService disciplineService;

    @GetMapping
    public List<Discipline> getAll() {
        return disciplineService.getAll();
    }

    @GetMapping("/{id}")
    public Discipline getById(@PathVariable Long id) {
        return disciplineService.getById(id);
    }

    @PostMapping
    public Discipline create(@RequestBody Discipline discipline) {
        return disciplineService.create(discipline);
    }

    @PutMapping("/{id}")
    public Discipline update(@PathVariable Long id, @RequestBody Discipline updatedDiscipline) {
        return disciplineService.update(id, updatedDiscipline);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        disciplineService.delete(id);
    }
}
