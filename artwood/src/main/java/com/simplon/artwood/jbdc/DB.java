package com.simplon.artwood.jbdc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static DB instance;
    private Connection conn;

    private DB() throws SQLException {
        String database= "artwoodd";
        String url = "jdbc:mysql://localhost:3306/"+database;
        String user = "root";
        String password = "";
        this.conn = DriverManager.getConnection(url, user, password);
    }

    public static DB getInstance() throws SQLException {
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }

    public Connection getConnection() {
        return this.conn;
    }
}
