package com.service.com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String USERNAME = "";
    private static final String PASSWORD = "";
    private static final String M_CONN_STRING =
            "jdbc:mysql://203.195.169.29:3306/DigitalLock_data";


    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(M_CONN_STRING, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public String processException(SQLException e) {
        System.err.println("Error message: " + e.getMessage());
        System.err.println("Error code: " + e.getErrorCode());
        System.err.println("SQL state: " + e.getSQLState());
        return ("Error message: " + e.getMessage() + " " + "Error code: " + e.getErrorCode() + " " + "SQL state: " + e.getSQLState());
    }

}
