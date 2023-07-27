package org.example.servletcrudapp.service;

import org.example.servletcrudapp.dto.CreateUserDto;
import org.example.servletcrudapp.dto.UpdateUserDto;
import org.example.servletcrudapp.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long userId);
    User createUser(CreateUserDto dto);
    User updateUser(UpdateUserDto dto);
    void deleteUserById(Long userId) throws SQLException;
}
