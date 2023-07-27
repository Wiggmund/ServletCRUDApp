package org.example.usersApp.service;

import org.example.usersApp.dto.CreateUserDto;
import org.example.usersApp.dto.UpdateUserDto;
import org.example.usersApp.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long userId);
    User createUser(CreateUserDto dto);
    User updateUser(UpdateUserDto dto) throws SQLException;
    User deleteUserById(Long userId);
}
