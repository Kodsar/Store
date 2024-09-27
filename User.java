package com.example.store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {
    private int id;
    private String username;
    private String password;
    private String name;
    private String contactNumber;
    private double balance;
    ArrayList<Integer>  cartItemsId;
    public User(int id, String username,String password, String name, String contactNumber, double balance, ArrayList<Integer>  cartItemsId){
        this.id= id;
        if(id==-1){
            this.id =(new UsersTable().getLastUserId())+1;

        }
        this.username=username;
        this.password=password;
        this.name=name;
        this.contactNumber=contactNumber;
        this.balance=balance;
        this. cartItemsId=cartItemsId;
    }
    
        public int getId(){
            return id;
        }
        public String getUsername(){
            return username;
        }
        public String getPassword(){
            return password;
        }
        public String getName(){
            return name;
        }
        public String getContactNumber(){
            return contactNumber;
        }
        public double getBalance(){
            return balance;
        }
        public ArrayList<Integer> getCartProductsId(){
            return cartItemsId;
        }
        

        public void setId(int id){
            this.id= id;
        }
        public void setUsername(String username){
            this.username=username;
        }
        public void setPassword(String password){
            this.password=password;
        }
        public void setName(String name){
            this.name=name;
        }
        public void setContactNumber(String contactNumber){
            this.contactNumber=contactNumber;
        }
        public void setBalance(double balance){
            this.balance=balance;
        }
        public void setCartProductsId(ArrayList<Integer> cartItemsId){
            this.cartItemsId=cartItemsId;
        }



        public void insertUser(){
            new UsersTable().insertToUsersTable(id,username,password,name,contactNumber,balance,new ArrayListToString().aToS(cartItemsId));
        }


        public void applyChanges() {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
    
            try {
                connection = DatabaseConnection.getConnection();
                String updateSQL = "UPDATE users SET username = ?, password = ?, name = ?, contactNumber = ?, balance = ?, cartItemsId = ? WHERE id = ?";
                preparedStatement = connection.prepareStatement(updateSQL);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, name);
                preparedStatement.setString(4, contactNumber);
                preparedStatement.setDouble(5, balance);
                preparedStatement.setString(6, new ArrayListToString().aToS(cartItemsId));
                preparedStatement.setInt(7, id);
    
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("User information updated successfully.");
                } else {
                    System.out.println("Failed to update user information. User not found.");
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
