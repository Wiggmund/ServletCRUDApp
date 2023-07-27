package org.example.servletcrudapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionDriverManager implements DBConnection {
    private final String URL = "jdbc:postgresql://192.168.1.87:5432/servletcrud";
    private final String USERNAME = "postgres";
    private final String PASSWORD = "05051987";



    @Override
    public  Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
