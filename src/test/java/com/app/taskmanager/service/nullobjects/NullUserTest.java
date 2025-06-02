package com.app.taskmanager.service.nullobjects;

import com.app.taskmanager.service.nullobjects.NullUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@ExtendWith(MockitoExtension.class)
public class NullUserTest {
    @InjectMocks
    private NullUser nullUser;

    @Test
    void testGetUserId(){
        assertEquals(-1L, nullUser.getUserId());
    }

    @Test
    void testGetName(){
        assertEquals("User Not Available", nullUser.getName());
    }

    @Test
    void testGetTasks(){
        assertEquals(Collections.emptyList(), nullUser.getTasks());
    }
}
