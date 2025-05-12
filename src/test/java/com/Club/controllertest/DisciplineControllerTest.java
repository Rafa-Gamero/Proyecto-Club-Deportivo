package com.Club.controllertest;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class DisciplineControllerTest extends AbstractControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testCreateDiscipline() throws Exception {
        String disciplineJson = """
            {
                "name": "Yoga",
                "schedule": "Lunes 10:00 - 11:00"
            }
        """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/disciplines")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(disciplineJson))
                .andExpect(status().isCreated());
    }
}
