package com.app.taskmanager.service.nullobjects;

import com.app.taskmanager.model.Task;
import com.app.taskmanager.model.enums.Status;
import com.app.taskmanager.service.nullobjects.NullTask;
import com.app.taskmanager.service.nullobjects.NullUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NullTaskTest {

    @InjectMocks
    private NullTask nullTask;

    @Test
    void testGetTaskId(){
        assertEquals(-1L, nullTask.getTaskId());
    }

    @Test
    void testGetTaskStatus(){
        assertEquals(Status.UNKNOWN, nullTask.getTaskStatus());
    }

    @Test
    void testGetTaskDescription(){
        assertEquals("No description available", nullTask.getTaskDescription());
    }

    @Test
    void testGetTaskHistory(){
        assertEquals(Collections.emptyList(), nullTask.getTaskHistory());
    }

    @Test
    void testGetOwnerUser(){
        assertInstanceOf(NullUser.class, nullTask.getOwnerUser());
    }
}
