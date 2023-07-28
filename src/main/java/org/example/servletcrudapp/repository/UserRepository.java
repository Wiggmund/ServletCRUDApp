package org.example.servletcrudapp.repository;

import org.example.servletcrudapp.dto.CreateUserDto;
import org.example.servletcrudapp.dto.UpdateUserDto;
import org.example.servletcrudapp.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAllUsers() throws SQLException;
    Optional<User> findUserById(Long userId) throws SQLException;
    void createUser(CreateUserDto dto);
    int updateUser(UpdateUserDto dto) throws SQLException;
    void deleteUserById(Long id) throws SQLException;
}
