package com.example.store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class UsersTable {
    private static Connection connection = DatabaseConnection.getConnection();

    public void insertToUsersTable(int id, String username, String password, String name, String contactNumber, double balance, String cartItemsId) {
        String query = "INSERT INTO users (ID, username, password, name, contactNumber, balance, cartItemsId) VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.setString(4, name);
            statement.setString(5, contactNumber);
            statement.setDouble(6, balance);
            statement.setString(7, cartItemsId);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int findUser(String username, String password) {
        if (connection != null) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    int userId = resultSet.getInt("ID");
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    return userId;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password.");
                    return -1;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return -1;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Database connection failed.");
            return -1;
        }
    }

    public User getUserById(int id) {
        String query = "SELECT * FROM users WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
    
            if (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String contactNumber = resultSet.getString("contactNumber");
                double balance = resultSet.getDouble("balance");
                String cart = resultSet.getString("cartItemsId");
                String[] items = cart.split(",");
                ArrayList<Integer> cartItemsId = new ArrayList<>();
                for (String item : items) {
                    cartItemsId.add(Integer.parseInt(item.trim())); // Trim whitespace
                }
                User user = new User(ID, username, password, name, contactNumber, balance, cartItemsId);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    

    public int getLastUserId() {
        String query = "SELECT ID FROM users ORDER BY ID DESC LIMIT 1";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean isUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
