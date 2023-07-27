package org.example.servletcrudapp.service.impl;

import org.example.servletcrudapp.dto.CreateUserDto;
import org.example.servletcrudapp.dto.UpdateUserDto;
import org.example.servletcrudapp.exception.UserNotFoundException;
import org.example.servletcrudapp.model.User;
import org.example.servletcrudapp.repository.UserRepository;
import org.example.servletcrudapp.service.UserService;

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
    public void createUser(CreateUserDto dto) {
        userRepository.createUser(dto);
    }

    @Override
    public User updateUser(UpdateUserDto dto) throws SQLException {
        userRepository.findUserById(dto.id()).orElseThrow(UserNotFoundException::new);
        userRepository.updateUser(dto);
        return userRepository.findUserById(dto.id()).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void deleteUserById(Long userId) throws SQLException {
        userRepository.findUserById(userId).orElseThrow(UserNotFoundException::new);
        userRepository.deleteUserById(userId);
    }
}
