package com.Club.servicetest;



import com.Club.model.User;
import com.Club.model.UserRole;
import com.Club.repository.UserRepository;
import com.Club.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

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
        savedUser.setPassword("password");
        savedUser.setRole(UserRole.ROLE_ADMIN);

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Ejecutar
        User result = userService.saveUser(newUser);

        // Verificar
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("test", result.getUsername());
        verify(userRepository, times(1)).save(any(User.class));
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

        when(userRepository.findAll()).thenReturn(userList);

        // Ejecutar
        List<User> result = userService.getAllUsers();

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

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Ejecutar
        Optional<User> result = userService.getUserById(1L);

        // Verificar
        assertTrue(result.isPresent());
        assertEquals("test", result.get().getUsername());
    }

    @Test
    void testDeleteUser() {
        // Preparar
        Long userId = 1L;
        doNothing().when(userRepository).deleteById(userId);

        // Ejecutar
        userService.deleteUserById(userId);

        // Verificar
        verify(userRepository, times(1)).deleteById(userId);
    }
}
