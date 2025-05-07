package com.Club.service;

import com.Club.model.Trainer;
import com.Club.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    public Optional<Trainer> getTrainerById(Long id) {
        return trainerRepository.findById(id);
    }

    public Trainer saveTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    public Trainer updateTrainer(Long id, Trainer updatedTrainer) {
        return trainerRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedTrainer.getName());
                    existing.setSpecialty(updatedTrainer.getSpecialty());
                    return trainerRepository.save(existing);
                }).orElseThrow(() -> new RuntimeException("Trainer not found"));
    }

    public void deleteTrainer(Long id) {
        trainerRepository.deleteById(id);
    }
}
