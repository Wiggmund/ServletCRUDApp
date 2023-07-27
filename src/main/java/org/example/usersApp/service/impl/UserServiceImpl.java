package org.example.usersApp.service.impl;

import org.example.usersApp.dto.CreateUserDto;
import org.example.usersApp.dto.UpdateUserDto;
import org.example.usersApp.model.User;
import org.example.usersApp.repository.UserRepository;
import org.example.usersApp.service.UserService;

import java.io.PrintWriter;
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
//        PrintWriter writer = resp.getWriter();
//        writer.println("service invoked");
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
