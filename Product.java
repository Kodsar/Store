package com.example.store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Product {
    private int id;
    private String name;
    private String description;
    private String imageUrl;
    private double price;
    private int stockQuantity;
    private double rate = 5.0;
    private int numberOfRates = 0;
    private String brand;

    public Product(int id, String name, String description, String imageUrl, double price, int stockQuantity, String brand) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.brand = brand;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
    public double getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }
    public double getRate() { return rate; }
    public int getNumberOfRates() { return numberOfRates; }
    public String getBrand() { return brand; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setPrice(double price) { this.price = price; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
    public void setRate(double rate) { this.rate = rate; }
    public void setNumberOfRates(int numberOfRates) { this.numberOfRates = numberOfRates; }
    public void setBrand(String brand) { this.brand = brand; }

    public void insertToProductsTable() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO products (ID, Name, Description, ImageUrl, Price, StockQuantity, Brand, Rate, NumberOfRates) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, description);
            statement.setString(4, imageUrl);
            statement.setDouble(5, price);
            statement.setInt(6, stockQuantity);
            statement.setString(7, brand);
            statement.setDouble(8, rate);
            statement.setInt(9, numberOfRates);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rating(int newRate) {
        rate = ((numberOfRates * rate) + newRate) / (++numberOfRates);
    }

    public void applyChanges() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DatabaseConnection.getConnection();
            String updateSQL = "UPDATE products SET Name = ?, Description = ?, ImageUrl = ?, Price = ?, StockQuantity = ?, Brand = ?, Rate = ?, NumberOfRates = ? WHERE ID = ?";
            preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, imageUrl);
            preparedStatement.setDouble(4, price);
            preparedStatement.setInt(5, stockQuantity);
            preparedStatement.setString(6, brand);
            preparedStatement.setDouble(7, rate);
            preparedStatement.setInt(8, numberOfRates);
            preparedStatement.setInt(9, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product information updated successfully.");
            } else {
                System.out.println("Failed to update product information. Product not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
