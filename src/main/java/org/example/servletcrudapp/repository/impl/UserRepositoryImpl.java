package org.example.servletcrudapp.repository.impl;

import org.example.servletcrudapp.db.DBConnection;
import org.example.servletcrudapp.model.User;
import org.example.servletcrudapp.repository.UserRepository;
import org.example.servletcrudapp.dto.CreateUserDto;
import org.example.servletcrudapp.dto.UpdateUserDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserRepositoryImpl implements UserRepository {
    private DBConnection dbConnection;
    private static final String ID_COL = "id";
    private static final String FIRST_NAME_COL = "firstName";
    private static final String LAST_NAME_COL = "lastName";
    private static final String AGE_C0L = "age";
    private static final String TABLE_NAME = "users";
    private static final String SELECT_ALL_SQL = String.format("SELECT * FROM %s", TABLE_NAME);
    private static final String SELECT_USER_BY_ID = String.format("SELECT * FROM %s WHERE %s = ?", TABLE_NAME, ID_COL );


    public UserRepositoryImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void setDbConnection(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<User> findAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                long id = resultSet.getLong(ID_COL);
                String firstName = resultSet.getString(FIRST_NAME_COL);
                String lastName = resultSet.getString(LAST_NAME_COL);
                Integer age = resultSet.getInt(AGE_C0L);
                User user = new User(id, firstName, lastName, age);
                users.add(user);
            }
        }

        return users;
    }

    @Override
    public Optional<User> findUserById(Long id) throws SQLException {
        User user = null;
        try (
                Connection connection = dbConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)
        ) {
            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String firstName = resultSet.getString(FIRST_NAME_COL);
                    String lastName = resultSet.getString(LAST_NAME_COL);
                    int age = resultSet.getInt(AGE_C0L);
                    user = new User(id, firstName, lastName, age);
                }
            }
            return Optional.ofNullable(user);
        }
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
