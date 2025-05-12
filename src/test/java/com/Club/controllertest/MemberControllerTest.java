package com.Club.controllertest;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MemberControllerTest extends AbstractControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testGetAllMembers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/members"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateMember() throws Exception {
        String memberJson = """
            {
                "name": "Jane Smith",
                "email": "jane@example.com",
                "phone": "1234567890",
                "startDate": "2025-01-01",
                "endDate": "2026-01-01",
                "price": 100.00
            }
        """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(memberJson))
                .andExpect(status().isCreated());
    }
}
