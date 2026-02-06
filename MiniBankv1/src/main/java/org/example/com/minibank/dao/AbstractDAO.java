package org.example.com.minibank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AbstractDAO {
    public static Connection connect() throws SQLException {
        String url = "JDBC:mysql://localhost:3306/minibank";
        String username = "root";
        String password = "12345";
        return DriverManager.getConnection(url, username, password);
    }
}
