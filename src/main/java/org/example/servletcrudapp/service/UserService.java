package org.example.servletcrudapp.service;

import org.example.servletcrudapp.dto.CreateUserDto;
import org.example.servletcrudapp.dto.UpdateUserDto;
import org.example.servletcrudapp.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long userId);

    User updateUser(UpdateUserDto dto);

    void deleteUserById(Long userId);

    void createUser(CreateUserDto dto);
}
