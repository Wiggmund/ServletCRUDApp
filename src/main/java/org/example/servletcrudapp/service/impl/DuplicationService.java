package org.example.servletcrudapp.service.impl;

import org.example.servletcrudapp.repository.UserRepository;

import java.sql.SQLException;

public class DuplicationService {
    private final UserRepository userRepository;

    public DuplicationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean doTheSameUserExist(String firstName, String lastName) throws SQLException {
        return userRepository.findUserByFirstNameAndLastName(firstName, lastName).isPresent();
    }
}
