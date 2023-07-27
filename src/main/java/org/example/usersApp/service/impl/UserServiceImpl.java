package org.example.usersApp.service.impl;

import org.example.usersApp.dto.CreateUserDto;
import org.example.usersApp.dto.UpdateUserDto;
import org.example.usersApp.exception.UserNotFoundException;
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
        return null;
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return Optional.empty();
    }

    @Override
    public User createUser(CreateUserDto dto) {
        return null;
    }

    @Override
    public User updateUser(UpdateUserDto dto) throws SQLException {
        userRepository.findUserById(dto.id()).orElseThrow(UserNotFoundException::new);
        userRepository.updateUser(dto);
        return userRepository.findUserById(dto.id()).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User deleteUserById(Long userId) {
        return null;
    }
}
