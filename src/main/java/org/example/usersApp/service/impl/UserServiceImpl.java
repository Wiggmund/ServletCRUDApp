package org.example.usersApp.service.impl;

import org.example.usersApp.dto.CreateUserDto;
import org.example.usersApp.dto.UpdateUserDto;
import org.example.usersApp.model.User;
import org.example.usersApp.repository.UserRepository;
import org.example.usersApp.service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
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
