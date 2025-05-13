package com.Club.servicetest;


import com.Club.model.Trainer;
import com.Club.repository.TrainerRepository;
import com.Club.service.TrainerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrainerServiceTest {

    @Mock
    private TrainerRepository trainerRepository;

    @InjectMocks
    private TrainerService trainerService;

    @Test
    void testCreateTrainer() {
        // Preparar
        Trainer trainer = new Trainer();
        trainer.setName("Juan Pérez");
        trainer.setSpecialty("Yoga");

        Trainer savedTrainer = new Trainer();
        savedTrainer.setId(1L);
        savedTrainer.setName("Juan Pérez");
        savedTrainer.setSpecialty("Yoga");

        when(trainerRepository.save(any(Trainer.class))).thenReturn(savedTrainer);

        // Ejecutar
        Trainer result = trainerService.saveTrainer(trainer);

        // Verificar
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Juan Pérez", result.getName());
        assertEquals("Yoga", result.getSpecialty());
        verify(trainerRepository, times(1)).save(any(Trainer.class));
    }

    @Test
    void testGetAllTrainers() {
        // Preparar
        Trainer trainer1 = new Trainer();
        trainer1.setId(1L);
        trainer1.setName("Juan Pérez");
        trainer1.setSpecialty("Yoga");

        Trainer trainer2 = new Trainer();
        trainer2.setId(2L);
        trainer2.setName("María López");
        trainer2.setSpecialty("Pilates");

        List<Trainer> trainerList = Arrays.asList(trainer1, trainer2);

        when(trainerRepository.findAll()).thenReturn(trainerList);

        // Ejecutar
        List<Trainer> result = trainerService.getAllTrainers();

        // Verificar
        assertEquals(2, result.size());
        assertEquals("Juan Pérez", result.get(0).getName());
        assertEquals("María López", result.get(1).getName());
    }

    @Test
    void testGetTrainerById() {
        // Preparar
        Trainer trainer = new Trainer();
        trainer.setId(1L);
        trainer.setName("Juan Pérez");
        trainer.setSpecialty("Yoga");

        when(trainerRepository.findById(1L)).thenReturn(Optional.of(trainer));

        // Ejecutar
        Optional<Trainer> result = trainerService.getTrainerById(1L);

        // Verificar
        assertTrue(result.isPresent());
        assertEquals("Juan Pérez", result.get().getName());
        assertEquals("Yoga", result.get().getSpecialty());
    }

    @Test
    void testDeleteTrainer() {
        // Preparar
        Long trainerId = 1L;
        doNothing().when(trainerRepository).deleteById(trainerId);

        // Ejecutar
        trainerService.deleteTrainerById(trainerId);

        // Verificar
        verify(trainerRepository, times(1)).deleteById(trainerId);
    }
}