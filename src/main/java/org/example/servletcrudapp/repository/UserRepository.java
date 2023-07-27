package org.example.servletcrudapp.repository;

import org.example.servletcrudapp.model.User;
import org.example.servletcrudapp.dto.CreateUserDto;
import org.example.servletcrudapp.dto.UpdateUserDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAllUsers() throws SQLException;
    Optional<User> findUserById(Long userId) throws SQLException;
    User createUser(CreateUserDto dto);
    User updateUser(UpdateUserDto dto);
    User deleteUserById(Long id);
}
