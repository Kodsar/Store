package com.example.store;
import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Stack;


public class LoginFrame extends JFrame implements ActionListener{
    private JTextField usernameField,nameTF,balanceTF,contactNumberTF;
    private JPasswordField passwordField;
    private myButton userLoginButton, adminLoginButton, signUpButton,signingUpButton;
    private JLabel orLabel, errorLabel;
    private boolean errorFlag=false;
    UsersTable UT= new UsersTable();

    public LoginFrame() {
        setTitle("Login");
        setSize(300, 230);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(190, 215, 220));

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(10, 30, 80, 25);
        add(usernameLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(100, 30, 160, 25);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 70, 80, 25);
        add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 70, 160, 25);
        add(passwordField);

        userLoginButton = new myButton("Login");
        userLoginButton.setBounds(120, 110, 80, 25);
        add(userLoginButton);


        orLabel = new JLabel("or:");
        orLabel.setBounds(10, 170, 80, 25);
        add(orLabel);

        adminLoginButton = new myButton("Admin Login");
        adminLoginButton.setBounds(105, 145, 110, 25);
        add(adminLoginButton);


        signUpButton = new myButton("Sign Up");
        signUpButton.setBounds(120, 170, 80, 25);
        add(signUpButton);


        userLoginButton.addActionListener(this);
        adminLoginButton.addActionListener(this);
        signUpButton.addActionListener(this);

    }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == userLoginButton) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                int ID= UT.findUser(username,password);
                if(ID!=-1){
                    
                        this.dispose();  
                        UserFrame userFrame =new UserFrame(UT.getUserById(ID));
                        userFrame.toFront();
                        userFrame.setVisible(true);

                        


                    /*this.getContentPane().removeAll();
                        
                    setSize(700, 800);                        
                    UserSidePanel userPanel= new UserSidePanel(this);
                    add(userPanel);*/
                    //add(userPanel);
                        
                    setVisible(true);
                    repaint();
                    revalidate();
                }

            }else if(e.getSource() == adminLoginButton){
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                //DatabaseConnection db=new DatabaseConnection();
                int ID= new AdminsTable().findAdmin(username,password);
                if(ID!=-1){
                    
                        this.dispose();  
                        //UserFrame userFrame =new UserFrame(new AdminsTable().getAdminById(ID));
                        //userFrame.toFront();
                        //userFrame.setVisible(true);


                    /*this.getContentPane().removeAll();
                        
                    setSize(700, 800);                        
                    UserSidePanel userPanel= new UserSidePanel(this);
                    add(userPanel);*/
                    //add(userPanel);
                        
                    setVisible(true);
                    repaint();
                    revalidate();
                }
                //new AdminsTable().findAdmin(username,password);
                //if(UT.findUser(username,password)){
                    
            }else if(e.getSource() == signUpButton){
                remove(userLoginButton);
                remove(orLabel);
                remove(adminLoginButton);
                remove(signUpButton);

                repaint();
                revalidate();
                setSize(300, 280);
                JLabel nameLabel = new JLabel("Name:");
                nameLabel.setBounds(10, 110, 80, 25);
                add(nameLabel);

                nameTF = new JTextField(20);
                nameTF.setBounds(100, 110, 160, 25);
                add(nameTF);

                JLabel contactNumberLabel = new JLabel("Contact Number:");
                contactNumberLabel.setBounds(10, 150, 120, 25);
                add(contactNumberLabel);

                contactNumberTF = new JTextField(20);
                contactNumberTF.setBounds(120, 150, 140, 25);
                add(contactNumberTF);

                JLabel balanceLabel = new JLabel("Balance:");
                balanceLabel.setBounds(10, 190, 80, 25);
                add(balanceLabel);

                balanceTF = new JTextField(20);
                balanceTF.setBounds(100, 190, 160, 25);
                add(balanceTF);

                signingUpButton = new myButton("Sign Up");
                signingUpButton.setBounds(120, 220, 80, 25);
                add(signingUpButton);
                signingUpButton.addActionListener(this);


            }else if(e.getSource() == signingUpButton){
                
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String name = nameTF.getText();
                String contactNumber = contactNumberTF.getText();
                double balance =  Double.parseDouble(balanceTF.getText());



                /*Stack<String> error = new Stack<>();
                Validator validator = new Validator();
                error = validator.nameField.setValuePhoneNumber(contactNumber);
                JLabel errorLabel= new JLabel(error.toString());
                errorLabel.setForeground(Color.RED);
                errorLabel.setBounds(20, 30, 80, 15);
                add(errorLabel);*/
                Validator validator = new Validator();
                
                if(UT.isUsername(username)){
                    if(errorFlag==true){
                        remove(errorLabel);
                    }
                    errorLabel = new JLabel("Invalid username!");
                    errorLabel.setBounds(10, 5, 150, 25);
                    errorLabel.setForeground(Color.RED);
                    add(errorLabel);
                    errorFlag=true;
                    repaint();
                    revalidate();

                }else if(validator.usernameValidator(username) && validator.phoneValidator(contactNumber)){
                    User user = new User(-1, username,password,name,contactNumber,balance,new ArrayList<>());
                    user.insertUser();
                    this.dispose(); 
                    //UserFrame userFrame=new UserFrame(0);
                    //userFrame.setVisible(true);
                    new UserFrame(user).setVisible(true);
                    repaint();
                    revalidate();

                    /*this.getContentPane().removeAll();
                        
                        setSize(700, 800);                        
                        UserPanel userPanel= new UserPanel(this);
                        add(userPanel);
                        userPanel.viewProfileButton.addActionListener(this);
                        userPanel.viewCartButton.addActionListener(this);
                        userPanel.viewProductsButton.addActionListener(this);
                        userPanel.searchProductsButton.addActionListener(this);
                        userPanel.rateProductsButton.addActionListener(this);
                         */                                
                        

                    
                }else if(!validator.usernameValidator(username)){
                    if(errorFlag==true){
                        remove(errorLabel);
                    }
                    errorLabel = new JLabel("Invalid username!");
                    errorLabel.setBounds(10, 5, 150, 25);
                    errorLabel.setForeground(Color.RED);
                    add(errorLabel);
                    errorFlag=true;
                    repaint();
                    revalidate();

                }else if(!validator.phoneValidator(contactNumber)){
                    if(errorFlag==true){
                        remove(errorLabel);
                    }
                    errorLabel = new JLabel("Invalid phone number!");
                    errorLabel.setBounds(10, 5, 150, 25);
                    errorLabel.setForeground(Color.RED);
                    add(errorLabel);
                    errorFlag=true;
                    repaint();
                    revalidate();
                    

                }





            }
}
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
    
}