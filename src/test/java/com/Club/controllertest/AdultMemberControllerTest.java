package com.Club.controllertest;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AdultMemberControllerTest extends AbstractControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testCreateAdultMember() throws Exception {
        String adultJson = """
            {
                "name": "Carlos Ruiz",
                "email": "carlos@example.com",
                "phone": "9876543210",
                "startDate": "2025-01-01",
                "endDate": "2026-01-01",
                "price": 120.00,
                "occupation": "Ingeniero"
            }
        """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/adult-members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(adultJson))
                .andExpect(status().isCreated());
    }
}
