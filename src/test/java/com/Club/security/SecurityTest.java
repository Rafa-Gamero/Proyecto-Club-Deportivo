package com.Club.security;



import com.Club.config.SecurityConfig;
import com.Club.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.when;
import static org.springframework.security.core.userdetails.User.withUsername;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest
public class SecurityTest {

    @Mock
    private UserService userService;

    @Mock
    private JwtUtil jwtUtil;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        // Configuración antes de cada test
    }

    // Test de autenticación y validación de JWT
    @Test
    void testLoginAndGetToken() throws Exception {
        String username = "testuser";
        String password = "password";

        // Simulando la respuesta del servicio de usuario
        UserDetails userDetails = withUsername(username)
                .password(password)
                .roles("USER")
                .build();

        when(userService.loadUserByUsername(username)).thenReturn(userDetails);

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(header().string("Authorization", "Bearer "));  // Verifica que el token esté en el header
    }

    // Test de acceso con un JWT válido
    @Test
    void testAccessWithValidToken() throws Exception {
        String token = "valid-jwt-token";
        String username = "testuser";

        when(jwtUtil.extractUsername(token)).thenReturn(username);

        mockMvc.perform(post("/api/protected-endpoint")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Access granted"));
    }

    // Test de acceso con un JWT inválido
    @Test
    void testAccessWithInvalidToken() throws Exception {
        String token = "invalid-jwt-token";

        mockMvc.perform(post("/api/protected-endpoint")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    // Test de protección de rutas según rol ADMIN
    @Test
    void testAccessForAdminRole() throws Exception {
        String token = "valid-jwt-token-for-admin";

        when(jwtUtil.extractUsername(token)).thenReturn("admin");
        when(jwtUtil.isTokenValid(token)).thenReturn(true);

        mockMvc.perform(post("/api/admin")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Admin access granted"));
    }

    // Test de protección de rutas según rol USER
    @Test
    void testAccessForUserRole() throws Exception {
        String token = "valid-jwt-token-for-user";

        when(jwtUtil.extractUsername(token)).thenReturn("user");
        when(jwtUtil.isTokenValid(token)).thenReturn(true);

        mockMvc.perform(post("/api/user")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User access granted"));
    }

    // Test de acceso denegado para un rol no autorizado
    @Test
    void testAccessForUnauthorizedRole() throws Exception {
        String token = "valid-jwt-token-for-user";

        when(jwtUtil.extractUsername(token)).thenReturn("user");
        when(jwtUtil.isTokenValid(token)).thenReturn(true);

        mockMvc.perform(post("/api/admin")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
}

