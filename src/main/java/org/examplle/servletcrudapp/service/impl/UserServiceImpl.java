package org.examplle.servletcrudapp.service.impl;

import org.examplle.servletcrudapp.dto.CreateUserDto;
import org.examplle.servletcrudapp.dto.UpdateUserDto;
import org.examplle.servletcrudapp.model.User;
import org.examplle.servletcrudapp.repository.UserRepository;
import org.examplle.servletcrudapp.service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        return userRepository.findAllUsers();
    }

    @Override
    public Optional<User> getUserById(Long userId) throws SQLException {
        return userRepository.findUserById(userId);
    }

    @Override
    public User createUser(CreateUserDto dto) {
        return null;
    }

    @Override
    public User updateUser(UpdateUserDto dto) {
        return null;
    }

    @Override
    public User deleteUserById(Long userId) {
        return null;
    }
}
