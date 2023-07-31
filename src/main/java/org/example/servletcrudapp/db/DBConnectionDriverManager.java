package org.example.servletcrudapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionDriverManager implements DBConnection {
    private final DBConfig dbConfig;

    public DBConnectionDriverManager(DBConfig dbConfig) {
        this.dbConfig = dbConfig;
    }

    @Override
    public Connection getConnection() throws SQLException {
        try {
            Class.forName(dbConfig.getDriver());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return DriverManager.getConnection(dbConfig.getUrl(), dbConfig.getUsername(), dbConfig.getPassword());
    }
}