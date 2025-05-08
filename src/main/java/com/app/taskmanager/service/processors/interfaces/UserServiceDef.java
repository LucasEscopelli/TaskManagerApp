package com.app.taskmanager.service.processors.interfaces;

import com.app.taskmanager.api.dto.UserDto;

public interface UserServiceDef {
    void createUser(UserDto newUser);
    UserDto getUserById(Long id);
    void updateUser(Long id, UserDto userFromRequest);
    void deleteUser(Long id);
}
