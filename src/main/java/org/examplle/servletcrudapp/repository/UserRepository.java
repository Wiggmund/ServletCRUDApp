package org.examplle.servletcrudapp.repository;

import org.examplle.servletcrudapp.dto.CreateUserDto;
import org.examplle.servletcrudapp.dto.UpdateUserDto;
import org.examplle.servletcrudapp.model.User;

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
