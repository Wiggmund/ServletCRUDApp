package org.example.servletcrudapp.service;

import org.example.servletcrudapp.dto.CreateUserDto;
import org.example.servletcrudapp.dto.UpdateUserDto;
import org.example.servletcrudapp.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers() throws SQLException;
    Optional<User> getUserById(Long userId) throws SQLException;
    User createUser(CreateUserDto dto);
    User updateUser(UpdateUserDto dto);
    User deleteUserById(Long userId);
}
