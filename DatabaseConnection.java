package com.example.store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Replace these with your database credentials and URL
                String jdbcURL = "jdbc:mysql://localhost:3306/store";
                String username = "Kosar";
                String password = "1234Bw@t";
                
                connection = DriverManager.getConnection(jdbcURL, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    /*public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            String usersTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                                    "ID INT PRIMARY KEY AUTO_INCREMENT, " +
                                    "username VARCHAR(50), " +
                                    "password VARCHAR(50), " +
                                    "name VARCHAR(50), " +
                                    "contactNumber VARCHAR(50), "+
                                    "balance INT, "+
                                    "cartItemsId VARCHAR(100))";
            stmt.executeUpdate(usersTableSQL);

            String adminsTableSQL = "CREATE TABLE IF NOT EXISTS admins (" +
                                    "ID INT PRIMARY KEY AUTO_INCREMENT, " +
                                    "username VARCHAR(50), " +
                                    "password VARCHAR(50), " +
                                    "name VARCHAR(50), " +
                                    "contactNumber VARCHAR(50))";
            stmt.executeUpdate(adminsTableSQL);

            String productTableSQL = "CREATE TABLE IF NOT EXISTS products (" +
                                    "ID INT PRIMARY KEY AUTO_INCREMENT, " +
                                    "name VARCHAR(50), " +
                                    "description VARCHAR(240), " +
                                    "price DOUBLE))";
            stmt.executeUpdate(productTableSQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
/*
CREATE TABLE IF NOT EXISTS users (
                                    ID INT PRIMARY KEY AUTO_INCREMENT, 
                                    username VARCHAR(50),
                                    password VARCHAR(50),
                                    name VARCHAR(50),
                                    contactNumber VARCHAR(50),
                                    balance DOUBLE,
                                    cartItemsId VARCHAR(100)
                                    );
                                    */