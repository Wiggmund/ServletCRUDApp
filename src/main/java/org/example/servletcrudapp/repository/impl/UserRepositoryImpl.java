package org.example.servletcrudapp.repository.impl;

import org.example.servletcrudapp.db.DBConnection;

import org.example.servletcrudapp.dto.CreateUserDto;
import org.example.servletcrudapp.dto.UpdateUserDto;
import org.example.servletcrudapp.model.User;
import org.example.servletcrudapp.repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final static String ID_COL = "id";
    private static final String TABLE_NAME = "users";
    private final static String FIRST_NAME_COL = "first_name";
    private final static String LAST_NAME_COL = "last_name";
    private final static String AGE_COL = "age";
    private static final String SELECT_ALL_SQL = String.format("SELECT * FROM %s", TABLE_NAME);
    private static final String SELECT_USER_BY_ID = String.format("SELECT * FROM %s WHERE %s = ?", TABLE_NAME, ID_COL );
    private final static String CREATE_USER_SQL = String.format("INSERT INTO users (%s, %s, %s) VALUES(?,?,?)",
            FIRST_NAME_COL, LAST_NAME_COL, AGE_COL);
    private final static String UPDATE_USER_SQL = String.format(
            "UPDATE %s SET %s = ?, %s = ?, %s = ? WHERE %s = ?",
            TABLE_NAME, FIRST_NAME_COL, LAST_NAME_COL, AGE_COL, ID_COL);
    private static final String DELETE_USER_SQL = String.format("DELETE FROM %s WHERE id = ?", TABLE_NAME);
    private final DBConnection dbConnection;

    public UserRepositoryImpl(DBConnection dbConnection) {
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
                Integer age = resultSet.getInt(AGE_COL);

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
                    int age = resultSet.getInt(AGE_COL);

                    user = new User(id, firstName, lastName, age);
                }
            }
            return Optional.ofNullable(user);
        }
    }

    @Override
    public void createUser(CreateUserDto dto) {
        try (PreparedStatement ps = dbConnection.getConnection().prepareStatement(CREATE_USER_SQL)) {
            ps.setString(1, dto.firstName());
            ps.setString(2, dto.lastName());
            ps.setInt(3, dto.age());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while creating user", e);
        }
    }

    @Override
    public int updateUser(UpdateUserDto dto) throws SQLException {
        try (
            Connection connection = dbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE_USER_SQL)
        ) {
            ps.setString(1, dto.firstName());
            ps.setString(2, dto.lastName());
            ps.setInt(3, dto.age());
            ps.setLong(4, dto.id());

            return ps.executeUpdate();
        }
    }

    @Override
    public void deleteUserById(Long id) throws SQLException {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL);
        ) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}
