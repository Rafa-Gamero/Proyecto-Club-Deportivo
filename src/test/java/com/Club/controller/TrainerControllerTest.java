package com.Club.controller;




import com.Club.model.Trainer;
import com.Club.service.TrainerService;
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

@WebMvcTest(TrainerController.class)
public class TrainerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TrainerService trainerService;

    @Test
    void testGetAllTrainers() throws Exception {
        Trainer trainer = new Trainer("Juan", "Karate");
        trainer.setId(1L);

        when(trainerService.getAllTrainers()).thenReturn(List.of(trainer));

        mockMvc.perform(get("/api/trainers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Juan"))
                .andExpect(jsonPath("$[0].specialty").value("Karate"));
    }
}
