package com.Club.controllertest;


import com.Club.controllers.UserController;
import com.Club.model.User;
import com.Club.model.UserRole;
import com.Club.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void testCreateUser() {
        // Preparar
        User newUser = new User();
        newUser.setUsername("test");
        newUser.setPassword("password");
        newUser.setRole(UserRole.ROLE_ADMIN);

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername("test");
        savedUser.setPassword("encodedPassword");
        savedUser.setRole(UserRole.ROLE_ADMIN);

        when(userService.saveUser(any(User.class))).thenReturn(savedUser);

        // Ejecutar
        User result = userController.createUser(newUser);

        // Verificar
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("test", result.getUsername());
        verify(userService, times(1)).saveUser(any(User.class));
    }

    @Test
    void testGetAllUsers() {
        // Preparar
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("user1");

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("user2");

        List<User> userList = Arrays.asList(user1, user2);

        when(userService.getAllUsers()).thenReturn(userList);

        // Ejecutar
        List<User> result = userController.getAllUsers();

        // Verificar
        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUsername());
        assertEquals("user2", result.get(1).getUsername());
    }

    @Test
    void testGetUserById() {
        // Preparar
        User user = new User();
        user.setId(1L);
        user.setUsername("test");

        when(userService.getUserById(1L)).thenReturn(Optional.of(user));

        // Ejecutar
        ResponseEntity<User> response = userController.getUserById(1L);

        // Verificar
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals("test", response.getBody().getUsername());
    }

    @Test
    void testGetUserById_NotFound() {
        // Preparar
        when(userService.getUserById(999L)).thenReturn(Optional.empty());

        // Ejecutar
        ResponseEntity<User> response = userController.getUserById(999L);

        // Verificar
        assertTrue(response.getStatusCode().is4xxClientError());
        assertNull(response.getBody());
    }
}