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
    private static final String SELECT_BY_FIRST_AND_LAST_NAME_SQL = String.format(
            "SELECT * from %s WHERE %s = ? AND %s = ?", TABLE_NAME, FIRST_NAME_COL, LAST_NAME_COL);
    private final DBConnection dbConnection;

    public UserRepositoryImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<User> findAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet resultSet = ps.executeQuery()
        ) {
            while (resultSet.next()) {
                users.add(extractUserEntity(resultSet));
            }
        }

        return users;
    }

    @Override
    public Optional<User> findUserById(Long id) throws SQLException {
        try (
            Connection connection = dbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_ID)
        ) {
            ps.setLong(1, id);

            try (ResultSet resultSet = ps.executeQuery()) {
                return getUserIfPresent(resultSet);
            }
        }
    }

    @Override
    public Optional<User> findUserByFirstNameAndLastName(String firstName, String lastName) throws SQLException {
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_BY_FIRST_AND_LAST_NAME_SQL)
        ) {
            ps.setString(1, firstName);
            ps.setString(2, lastName);

            try(ResultSet resultSet = ps.executeQuery()) {
                return getUserIfPresent(resultSet);
            }
        }
    }

    @Override
    public void createUser(CreateUserDto dto) throws SQLException {
        try (
            Connection connection = dbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(CREATE_USER_SQL)
        ) {
            ps.setString(1, dto.firstName());
            ps.setString(2, dto.lastName());
            ps.setInt(3, dto.age());
            ps.executeUpdate();
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
             PreparedStatement ps = connection.prepareStatement(DELETE_USER_SQL);
        ) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    private User extractUserEntity(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID_COL);
        String firstName1 = resultSet.getString(FIRST_NAME_COL);
        String lastName1 = resultSet.getString(LAST_NAME_COL);
        int age = resultSet.getInt(AGE_COL);

        return new User(id, firstName1, lastName1, age);
    }

    private Optional<User> getUserIfPresent(ResultSet resultSet) throws SQLException {
        User user = null;

        if (resultSet.next()) {
            user = extractUserEntity(resultSet);
        }

        return Optional.ofNullable(user);
    }
}
