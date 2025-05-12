package com.Club.controllertest;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MemberDisciplineControllerTest extends AbstractControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testEnrollMemberInDiscipline() throws Exception {
        String enrollmentJson = """
            {
                "enrollmentDate": "2025-01-01"
            }
        """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/enrollments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(enrollmentJson))
                .andExpect(status().isCreated());
    }
}