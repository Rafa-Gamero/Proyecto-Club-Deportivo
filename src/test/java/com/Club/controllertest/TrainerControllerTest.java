package com.Club.controllertest;



import com.Club.controllers.TrainerController;
import com.Club.model.Trainer;
import com.Club.service.TrainerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrainerControllerTest {

    @Mock
    private TrainerService trainerService;

    @InjectMocks
    private TrainerController trainerController;

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

        when(trainerService.saveTrainer(any(Trainer.class))).thenReturn(savedTrainer);

        // Ejecutar
        ResponseEntity<Trainer> response = trainerController.createTrainer(trainer);

        // Verificar
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Juan Pérez", response.getBody().getName());
        assertEquals("Yoga", response.getBody().getSpecialty());
        verify(trainerService, times(1)).saveTrainer(any(Trainer.class));
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

        when(trainerService.getAllTrainers()).thenReturn(trainerList);

        // Ejecutar
        List<Trainer> result = trainerController.getAllTrainers();

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

        when(trainerService.getTrainerById(1L)).thenReturn(Optional.of(trainer));

        // Ejecutar
        ResponseEntity<Trainer> response = trainerController.getTrainerById(1L);

        // Verificar
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals("Juan Pérez", response.getBody().getName());
        assertEquals("Yoga", response.getBody().getSpecialty());
    }

    @Test
    void testUpdateTrainer() {
        // Preparar
        Long trainerId = 1L;

        Trainer existingTrainer = new Trainer();
        existingTrainer.setId(trainerId);
        existingTrainer.setName("Juan Pérez");
        existingTrainer.setSpecialty("Yoga");

        Trainer updatedDetails = new Trainer();
        updatedDetails.setName("Juan Pérez");
        updatedDetails.setSpecialty("Yoga y Pilates");

        Trainer savedTrainer = new Trainer();
        savedTrainer.setId(trainerId);
        savedTrainer.setName("Juan Pérez");
        savedTrainer.setSpecialty("Yoga y Pilates");

        when(trainerService.getTrainerById(trainerId)).thenReturn(Optional.of(existingTrainer));
        when(trainerService.saveTrainer(any(Trainer.class))).thenReturn(savedTrainer);

        // Ejecutar
        ResponseEntity<Trainer> response = trainerController.updateTrainer(trainerId, updatedDetails);

        // Verificar
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals("Juan Pérez", response.getBody().getName());
        assertEquals("Yoga y Pilates", response.getBody().getSpecialty());
    }

    @Test
    void testDeleteTrainer() {
        // Preparar
        Long trainerId = 1L;

        Trainer trainer = new Trainer();
        trainer.setId(trainerId);

        when(trainerService.getTrainerById(trainerId)).thenReturn(Optional.of(trainer));
        doNothing().when(trainerService).deleteTrainerById(trainerId);

        // Ejecutar
        ResponseEntity<Void> response = trainerController.deleteTrainer(trainerId);

        // Verificar
        assertTrue(response.getStatusCode().is2xxSuccessful());
        verify(trainerService, times(1)).deleteTrainerById(trainerId);
    }
}
