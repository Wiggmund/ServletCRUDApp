package org.example.servletcrudapp.service.impl;

import org.example.servletcrudapp.dto.CreateUserDto;
import org.example.servletcrudapp.dto.UpdateUserDto;
import org.example.servletcrudapp.exception.DBInternalException;
import org.example.servletcrudapp.exception.UserAlreadyExistException;
import org.example.servletcrudapp.exception.UserNotFoundException;
import org.example.servletcrudapp.model.User;
import org.example.servletcrudapp.repository.UserRepository;
import org.example.servletcrudapp.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final DuplicationService duplicationService;

    public UserServiceImpl(UserRepository userRepository, DuplicationService duplicationService) {
        this.userRepository = userRepository;
        this.duplicationService = duplicationService;
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return userRepository.findAllUsers();
        } catch (SQLException e) {
            throw new DBInternalException(e.getMessage());
        }
    }

    @Override
    public User getUserById(Long userId) {
        try {
            return userRepository.findUserById(userId).orElseThrow(UserNotFoundException::new);
        } catch (SQLException e) {
            throw new DBInternalException(e.getMessage());
        }
    }

    @Override
    public void createUser(CreateUserDto dto) {
        try {
            userRepository.createUser(dto);
        } catch (SQLException e) {
            throw new DBInternalException(e.getMessage());
        }
    }

    @Override
    public User updateUser(UpdateUserDto dto) {
        try {
            User user = userRepository.findUserById(dto.id()).orElseThrow(UserNotFoundException::new);

            boolean isFirstNameTheSame = dto.firstName().equalsIgnoreCase(user.getFirstName());
            boolean isLastNameTheSame = dto.lastName().equalsIgnoreCase(user.getLastName());

            if (!isFirstNameTheSame || !isLastNameTheSame) {
                boolean doTheSameUserExist = duplicationService.doTheSameUserExist(dto.firstName(), dto.lastName());

                if (doTheSameUserExist) {
                    throw new UserAlreadyExistException();
                }
            }

            userRepository.updateUser(dto);
            return userRepository.findUserById(dto.id()).orElseThrow(UserNotFoundException::new);
        } catch (SQLException e) {
            throw new DBInternalException(e.getMessage());
        }
    }

    @Override
    public void deleteUserById(Long userId) {
        try {
            userRepository.findUserById(userId).orElseThrow(UserNotFoundException::new);
            userRepository.deleteUserById(userId);
        } catch (SQLException e) {
            throw new DBInternalException(e.getMessage());
        }
    }
}