package com.Club.controller;




import com.Club.model.Discipline;
import com.Club.service.DisciplineService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DisciplineController.class)
public class DisciplineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private DisciplineService disciplineService;

    @Test
    void testGetAllDisciplines() throws Exception {
        Discipline discipline = new Discipline("Karate", "Lunes y Miércoles");
        discipline.setId(1L);
        when(disciplineService.getAllDisciplines()).thenReturn(List.of(discipline));

        mockMvc.perform(get("/api/disciplines")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Karate"))
                .andExpect(jsonPath("$[0].schedule").value("Lunes y Miércoles"));
    }
}
