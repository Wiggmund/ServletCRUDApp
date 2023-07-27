package org.example.servletcrudapp.service.impl;

import org.example.servletcrudapp.dto.CreateUserDto;
import org.example.servletcrudapp.dto.UpdateUserDto;
import org.example.servletcrudapp.model.User;
import org.example.servletcrudapp.repository.UserRepository;
import org.example.servletcrudapp.service.UserService;

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
        System.out.println("######### я перешел в сервис #############");
        //return new User(dto.firstName(), dto.lastName(), dto.age());
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