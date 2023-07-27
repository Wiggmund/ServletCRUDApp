package org.example.usersApp.repository.impl;

import org.example.usersApp.db.DBConnection;
import org.example.usersApp.dto.CreateUserDto;
import org.example.usersApp.dto.UpdateUserDto;
import org.example.usersApp.exception.UserNotFoundException;
import org.example.usersApp.model.User;
import org.example.usersApp.repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class UserRepositoryImpl implements UserRepository {
    private final static String ID_COL = "id";
    private final static String FIRST_NAME_COL = "first_name";
    private final static String LAST_NAME_COL = "last_name";
    private final static String AGE_COL = "age";
    private final static String UPDATE_USER_SQL = String.format(
            "UPDATE users SET %s = ?, %s = ?, %s = ? WHERE %s = ?",
            FIRST_NAME_COL, LAST_NAME_COL, AGE_COL, ID_COL);
    private DBConnection dbConnection;

    public UserRepositoryImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void setDbConnection(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<User> findAllUsers() {
        return null;
    }

    @Override
    public Optional<User> findUserById(Long userId) throws SQLException {
        return Optional.empty();
    }

    @Override
    public User createUser(CreateUserDto dto) {
        return null;
    }

    @Override
    public int updateUser(UpdateUserDto dto) throws SQLException {
        try(
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
    public User deleteUserById(Long id) {
        return null;
    }
}
