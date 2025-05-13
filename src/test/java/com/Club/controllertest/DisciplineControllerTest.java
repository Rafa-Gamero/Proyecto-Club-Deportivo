package com.Club.controllertest;



import com.Club.controllers.DisciplineController;
import com.Club.model.Discipline;
import com.Club.service.DisciplineService;
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
class DisciplineControllerTest {

    @Mock
    private DisciplineService disciplineService;

    @InjectMocks
    private DisciplineController disciplineController;

    @Test
    void testCreateDiscipline() {
        // Preparar
        Discipline discipline = new Discipline();
        discipline.setName("Yoga");
        discipline.setSchedule("Lunes y Miércoles 18:00-19:00");

        Discipline savedDiscipline = new Discipline();
        savedDiscipline.setId(1L);
        savedDiscipline.setName("Yoga");
        savedDiscipline.setSchedule("Lunes y Miércoles 18:00-19:00");

        when(disciplineService.saveDiscipline(any(Discipline.class))).thenReturn(savedDiscipline);

        // Ejecutar
        ResponseEntity<Discipline> response = disciplineController.createDiscipline(discipline);

        // Verificar
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Yoga", response.getBody().getName());
        verify(disciplineService, times(1)).saveDiscipline(any(Discipline.class));
    }

    @Test
    void testGetAllDisciplines() {
        // Preparar
        Discipline discipline1 = new Discipline();
        discipline1.setId(1L);
        discipline1.setName("Yoga");

        Discipline discipline2 = new Discipline();
        discipline2.setId(2L);
        discipline2.setName("Pilates");

        List<Discipline> disciplineList = Arrays.asList(discipline1, discipline2);

        when(disciplineService.getAllDisciplines()).thenReturn(disciplineList);

        // Ejecutar
        List<Discipline> result = disciplineController.getAllDisciplines();

        // Verificar
        assertEquals(2, result.size());
        assertEquals("Yoga", result.get(0).getName());
        assertEquals("Pilates", result.get(1).getName());
    }

    @Test
    void testGetDisciplineById() {
        // Preparar
        Discipline discipline = new Discipline();
        discipline.setId(1L);
        discipline.setName("Yoga");

        when(disciplineService.getDisciplineById(1L)).thenReturn(Optional.of(discipline));

        // Ejecutar
        ResponseEntity<Discipline> response = disciplineController.getDisciplineById(1L);

        // Verificar
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals("Yoga", response.getBody().getName());
    }

    @Test
    void testUpdateDiscipline() {
        // Preparar
        Long disciplineId = 1L;

        Discipline existingDiscipline = new Discipline();
        existingDiscipline.setId(disciplineId);
        existingDiscipline.setName("Yoga");
        existingDiscipline.setSchedule("Lunes y Miércoles 18:00-19:00");

        Discipline updatedDetails = new Discipline();
        updatedDetails.setName("Yoga Avanzado");
        updatedDetails.setSchedule("Lunes y Miércoles 20:00-21:00");

        Discipline savedDiscipline = new Discipline();
        savedDiscipline.setId(disciplineId);
        savedDiscipline.setName("Yoga Avanzado");
        savedDiscipline.setSchedule("Lunes y Miércoles 20:00-21:00");

        when(disciplineService.getDisciplineById(disciplineId)).thenReturn(Optional.of(existingDiscipline));
        when(disciplineService.saveDiscipline(any(Discipline.class))).thenReturn(savedDiscipline);

        // Ejecutar
        ResponseEntity<Discipline> response = disciplineController.updateDiscipline(disciplineId, updatedDetails);

        // Verificar
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals("Yoga Avanzado", response.getBody().getName());
        assertEquals("Lunes y Miércoles 20:00-21:00", response.getBody().getSchedule());
    }

    @Test
    void testDeleteDiscipline() {
        // Preparar
        Long disciplineId = 1L;

        Discipline discipline = new Discipline();
        discipline.setId(disciplineId);

        when(disciplineService.getDisciplineById(disciplineId)).thenReturn(Optional.of(discipline));
        doNothing().when(disciplineService).deleteDisciplineById(disciplineId);

        // Ejecutar
        ResponseEntity<Void> response = disciplineController.deleteDiscipline(disciplineId);

        // Verificar
        assertTrue(response.getStatusCode().is2xxSuccessful());
        verify(disciplineService, times(1)).deleteDisciplineById(disciplineId);
    }
}
