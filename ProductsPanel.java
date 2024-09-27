package com.example.store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.net.MalformedURLException;

public class ProductsPanel extends JPanel implements ActionListener {
    private ProductsTable productsTable;
    private List<Product> productsList;
    private JPanel productsPanel;
    private JButton OBOldestB,OBNewestB,OBPriceB,OBExpensiveB;
    private User user;

    public ProductsPanel(User user,List<Product> productsList){
        this.user=user;
        productsTable = new ProductsTable();
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Your existing buttons (order by ID, price etc.)
        // ...
         OBOldestB=new JButton("Order by oldest");
         OBNewestB=new JButton("Order by newest");
         OBPriceB=new JButton("Order by price");
         OBExpensiveB=new JButton("Order by the most expensive");


        buttonPanel.add(OBOldestB);
        buttonPanel.add(OBNewestB);
        buttonPanel.add(new JButton("OBPriceB"));
        buttonPanel.add(new JButton("OBPriceB"));

        add(buttonPanel, BorderLayout.NORTH);

        // Default order by IDInverse
        productsList = productsTable.getProductsOrderedByIdInverse();
        productsPanel = new JPanel(new GridLayout(0, 3, 10, 10)); 

        for (Product product : productsList) {
            ProductPanel productPanel = new ProductPanel(product,user);
            productsPanel.add(productPanel);
        }

        //JScrollPane scrollPane = new JScrollPane(productsPanel);
        //add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == OBOldestB) {
            productsList = productsTable.getProductsOrderedByIdInverse();
            productsPanel = new JPanel(new GridLayout(0, 3, 10, 10)); 
        for (Product product : productsList) {
            ProductPanel productPanel = new ProductPanel(product,user);
            productsPanel.add(productPanel);
        }
        } else if (e.getSource() == OBNewestB) {
            

        }
            
         
    }
}
