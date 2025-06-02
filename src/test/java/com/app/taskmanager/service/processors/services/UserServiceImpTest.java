package com.app.taskmanager.service.processors.services;

import com.app.taskmanager.model.User;
import com.app.taskmanager.service.nullobjects.NullUser;
import com.app.taskmanager.service.processors.updater.TaskUpdater;
import com.app.taskmanager.service.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImpTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImp userService;

    @Test
    void testCreateUser() {
        User newUser = new User();
        newUser.setName("John Doe");

        userService.createUser(newUser);

        verify(userRepository, times(1)).save(newUser);
    }

    @Test
    void testGetUserByIdFound() {
        User user = new User();
        user.setUserId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);

        assertEquals(user, result);
    }

    @Test
    void testGetUserByIdNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        User result = userService.getUserById(1L);

        assertInstanceOf(NullUser.class, result);
    }

    @Test
    void testUpdateUserFound() {

        User originalUser = new User();
        originalUser.setUserId(1L);
        originalUser.setName("original name");

        User updateUser = new User();
        updateUser.setUserId(1L);
        updateUser.setName("updated name");

        when(userRepository.findById(1L)).thenReturn(Optional.of(originalUser));

        userService.updateUser(1L, updateUser);

        verify(userRepository, times(1)).save(originalUser);
        assertEquals(updateUser.getName(), originalUser.getName());
    }

    @Test
    void testUpdateUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Exception ex = assertThrows(EntityNotFoundException.class, () -> {
            userService.updateUser(1L, new User());
        });

        assertEquals("User not found", ex.getMessage());
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }
}
