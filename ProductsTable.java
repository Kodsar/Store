package com.example.store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductsTable {
    private static Connection connection = DatabaseConnection.getConnection();

    public void insertToProductsTable(int id, String name, String description, String imageUrl, double price, int stockQuantity, String brand, double rate, int numberOfRates) {
        String query = "INSERT INTO products (ID, Name, Description, ImageUrl, Price, StockQuantity, Brand, Rate, NumberOfRates) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, description);
            statement.setString(4, imageUrl);
            statement.setDouble(5, price);
            statement.setInt(6, stockQuantity);
            statement.setString(7, brand);
            statement.setDouble(8, rate);
            statement.setInt(9, numberOfRates);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new product was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                String description = resultSet.getString("Description");
                String imageUrl = resultSet.getString("ImageUrl");
                double price = resultSet.getDouble("Price");
                int stockQuantity = resultSet.getInt("StockQuantity");
                String brand = resultSet.getString("Brand");
                double rate = resultSet.getDouble("Rate");
                int numberOfRates = resultSet.getInt("NumberOfRates");

                products.add(new Product(id, name, description, imageUrl, price, stockQuantity, brand));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> getProductsOrderedById() {
        List<Product> products = getAllProducts();
        Collections.sort(products, Comparator.comparingInt(Product::getId));
        return products;
    }

    public List<Product> getProductsOrderedByIdInverse() {
        List<Product> products = getAllProducts();
        Collections.sort(products, (p1, p2) -> Integer.compare(p2.getId(), p1.getId()));
        return products;
    }

    public List<Product> getProductsOrderedByPrice() {
        List<Product> products = getAllProducts();
        Collections.sort(products, Comparator.comparingDouble(Product::getPrice));
        return products;
    }

    public List<Product> getProductsOrderedByPriceInverse() {
        List<Product> products = getAllProducts();
        Collections.sort(products, (p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
        return products;
    }

    public boolean deleteProductById(int id) {
        String query = "DELETE FROM products WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
