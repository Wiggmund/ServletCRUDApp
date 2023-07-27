package org.example.usersApp.repository;

import org.example.usersApp.dto.CreateUserDto;
import org.example.usersApp.dto.UpdateUserDto;
import org.example.usersApp.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAllUsers();
    Optional<User> findUserById(Long userId) throws SQLException;
    //User createUser(CreateUserDto dto);
    void createUser(CreateUserDto dto);
    User updateUser(UpdateUserDto dto);
    User deleteUserById(Long id);
}
