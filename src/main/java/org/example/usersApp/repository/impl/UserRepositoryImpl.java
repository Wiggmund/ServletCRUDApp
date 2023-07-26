package org.example.usersApp.repository.impl;

import org.example.usersApp.db.DBConnection;
import org.example.usersApp.dto.CreateUserDto;
import org.example.usersApp.dto.UpdateUserDto;
import org.example.usersApp.model.User;
import org.example.usersApp.repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserRepositoryImpl implements UserRepository {
    private DBConnection dbConnection;

    public UserRepositoryImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void setDbConnection(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                long id = resultSet.getLong("ID");
                String firstName = resultSet.getString("First Name");
                String lastName = resultSet.getString("Last Name");
                Integer age = resultSet.getInt("Age");
                User user = new User(firstName,lastName, age);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Optional<User> findUserById(Long userId) throws SQLException {
        User user = null;
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String firstName = resultSet.getString("First Name");
                String lastName = resultSet.getString("Last Name");
                String age = resultSet.getString("Age");
                user = new User(userId, firstName, lastName, age);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
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
    public User deleteUserById(Long id) {
        return null;
    }
}
