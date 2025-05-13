package com.Club.servicetest;



import com.Club.model.Discipline;
import com.Club.repository.DisciplineRepository;
import com.Club.service.DisciplineService;
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
class DisciplineServiceTest {

    @Mock
    private DisciplineRepository disciplineRepository;

    @InjectMocks
    private DisciplineService disciplineService;

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

        when(disciplineRepository.save(any(Discipline.class))).thenReturn(savedDiscipline);

        // Ejecutar
        Discipline result = disciplineService.saveDiscipline(discipline);

        // Verificar
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Yoga", result.getName());
        verify(disciplineRepository, times(1)).save(any(Discipline.class));
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

        when(disciplineRepository.findAll()).thenReturn(disciplineList);

        // Ejecutar
        List<Discipline> result = disciplineService.getAllDisciplines();

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

        when(disciplineRepository.findById(1L)).thenReturn(Optional.of(discipline));

        // Ejecutar
        Optional<Discipline> result = disciplineService.getDisciplineById(1L);

        // Verificar
        assertTrue(result.isPresent());
        assertEquals("Yoga", result.get().getName());
    }

    @Test
    void testDeleteDiscipline() {
        // Preparar
        Long disciplineId = 1L;
        doNothing().when(disciplineRepository).deleteById(disciplineId);

        // Ejecutar
        disciplineService.deleteDisciplineById(disciplineId);

        // Verificar
        verify(disciplineRepository, times(1)).deleteById(disciplineId);
    }
}