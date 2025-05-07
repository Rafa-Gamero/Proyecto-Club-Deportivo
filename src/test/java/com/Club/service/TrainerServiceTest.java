package com.Club.service;



import com.Club.model.Trainer;
import com.Club.repository.TrainerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TrainerServiceTest {

    @Mock
    private TrainerRepository trainerRepository;

    @InjectMocks
    private TrainerService trainerService;

    private Trainer trainer;

    @BeforeEach
    void setUp() {
        trainer = new Trainer("Juan", "Karate");
        trainer.setId(1L);
    }

    @Test
    void testGetTrainerById() {
        when(trainerRepository.findById(1L)).thenReturn(java.util.Optional.of(trainer));

        Trainer result = trainerService.getTrainerById(1L);
        assertEquals("Juan", result.getName());
    }

    @Test
    void testSaveTrainer() {
        when(trainerRepository.save(trainer)).thenReturn(trainer);

        Trainer result = trainerService.saveTrainer(trainer);
        assertNotNull(result);
        assertEquals("Juan", result.getName());
    }

    @Test
    void testUpdateTrainer() {
        when(trainerRepository.findById(1L)).thenReturn(java.util.Optional.of(trainer));
        trainer.setName("Updated Name");

        Trainer updatedTrainer = new Trainer("Updated Name", "Karate");
        updatedTrainer.setId(1L);

        when(trainerRepository.save(updatedTrainer)).thenReturn(updatedTrainer);

        Trainer result = trainerService.updateTrainer(1L, updatedTrainer);

        assertEquals("Updated Name", result.getName());
    }

    @Test
    void testDeleteTrainer() {
        Long id = 1L;
        trainerService.deleteTrainer(id);
        verify(trainerRepository, times(1)).deleteById(id);
    }
}

