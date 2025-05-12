package com.Club.controllertest;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ChildMemberControllerTest extends AbstractControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testCreateChildMember() throws Exception {
        String childJson = """
            {
                "name": "Lucía Pérez",
                "email": "lucia@example.com",
                "phone": "5555555555",
                "startDate": "2025-01-01",
                "endDate": "2026-01-01",
                "price": 80.00,
                "guardianName": "María López"
            }
        """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/child-members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(childJson))
                .andExpect(status().isCreated());
    }
}