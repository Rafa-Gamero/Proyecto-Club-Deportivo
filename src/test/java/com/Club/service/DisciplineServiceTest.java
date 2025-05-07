package com.Club.service;



import com.Club.model.Discipline;
import com.Club.repository.DisciplineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class DisciplineServiceTest {

    @Mock
    private DisciplineRepository disciplineRepository;

    @InjectMocks
    private DisciplineService disciplineService;

    private Discipline discipline;

    @BeforeEach
    void setUp() {
        discipline = new Discipline("Karate", "Lunes y Miércoles");
        discipline.setId(1L);
    }

    @Test
    void testGetDisciplineById() {

        when(disciplineRepository.findById(1L)).thenReturn(java.util.Optional.of(discipline));


        Optional<Discipline> result = disciplineService.getDisciplineById(1L);


        assertEquals("Karate", result.get().getName());
    }

    @Test
    void testSaveDiscipline() {
        when(disciplineRepository.save(discipline)).thenReturn(discipline);

        Discipline result = disciplineService.saveDiscipline(discipline);
        assertNotNull(result);
        assertEquals("Karate", result.getName());
    }

    @Test
    void testUpdateDiscipline() {
        when(disciplineRepository.findById(1L)).thenReturn(java.util.Optional.of(discipline));
        discipline.setName("Updated Discipline");

        Discipline updatedDiscipline = new Discipline("Updated Discipline", "Lunes y Miércoles");
        updatedDiscipline.setId(1L);

        when(disciplineRepository.save(updatedDiscipline)).thenReturn(updatedDiscipline);

        Discipline result = disciplineService.updateDiscipline(1L, updatedDiscipline);

        assertEquals("Updated Discipline", result.getName());
    }

    @Test
    void testDeleteDiscipline() {
        Long id = 1L;
        disciplineService.deleteDiscipline(id);
        verify(disciplineRepository, times(1)).deleteById(id);
    }
}

