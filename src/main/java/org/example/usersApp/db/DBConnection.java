package org.example.usersApp.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnection {
    Connection getConnection() throws SQLException;
}
