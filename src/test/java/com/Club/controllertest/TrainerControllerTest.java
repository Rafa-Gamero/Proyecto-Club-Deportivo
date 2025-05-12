package com.Club.controllertest;




import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TrainerControllerTest extends AbstractControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testGetAllTrainers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/trainers"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateTrainer() throws Exception {
        String trainerJson = """
            {
                "name": "John Doe",
                "specialty": "Fitness"
            }
        """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/trainers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(trainerJson))
                .andExpect(status().isCreated());
    }
}