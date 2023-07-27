package org.example.usersApp.repository.impl;

import org.example.usersApp.db.DBConnection;
import org.example.usersApp.dto.CreateUserDto;
import org.example.usersApp.dto.UpdateUserDto;
import org.example.usersApp.model.User;
import org.example.usersApp.repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return null;
    }

    @Override
    public Optional<User> findUserById(Long userId) throws SQLException {
        return Optional.empty();
    }

    @Override
    public void createUser(CreateUserDto dto) {
        String sql = "INSERT INTO users VALUES(?,?,?)";
        try (PreparedStatement ps = dbConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, dto.firstName());
            ps.setString(2, dto.lastName());
            ps.setInt(3, dto.age());
            ps.executeUpdate();
        } catch (SQLException e) {throw new RuntimeException();}
        //return new User(dto.firstName(), dto.lastName(), dto.age());
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
