package com.Club.service;

import com.Club.model.Discipline;
import com.Club.repository.DisciplineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplineService {

    private final DisciplineRepository disciplineRepository;

    public DisciplineService(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    public List<Discipline> getAllDisciplines() {
        return disciplineRepository.findAll();
    }

    public Optional<Discipline> getDisciplineById(Long id) {
        return disciplineRepository.findById(id);
    }

    public Discipline saveDiscipline(Discipline discipline) {
        return disciplineRepository.save(discipline);
    }

    public Discipline updateDiscipline(Long id, Discipline updatedDiscipline) {
        return disciplineRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedDiscipline.getName());
                    existing.setSchedule(updatedDiscipline.getSchedule());
                    existing.setTrainer(updatedDiscipline.getTrainer());
                    return disciplineRepository.save(existing);
                }).orElseThrow(() -> new RuntimeException("Discipline not found"));
    }

    public void deleteDiscipline(Long id) {
        disciplineRepository.deleteById(id);
    }

    public List<Discipline> getAll() {
        return null;
    }

    public Discipline getById(Long id) {
        return null;
    }

    public Discipline create(Discipline discipline) {
        return null;
    }

    public Discipline update(Long id, Discipline updatedDiscipline) {
        return null;
    }

    public void delete(Long id) {
    }
}