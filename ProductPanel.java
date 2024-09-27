package com.example.store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ProductPanel extends JPanel{
    private Product product;

    public ProductPanel(Product product,User user) {
        setLayout(new GridLayout(1, 5));
        this.product = product;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));


        String imageUrl= product.getImageUrl();
        /*try {
            URL url = new URL(imageUrl);
            ImageIcon icon = new ImageIcon(url);

            // Set image icon on the JLabel
            //label.setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        JLabel imageLabel = new JLabel(new ImageIcon(product.getImageUrl()));
        imageLabel.setSize(150, 150);

        JLabel nameLabel = new JLabel(product.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        String priceL=String.valueOf(product.getPrice())+"$";
        JLabel priceLabel = new JLabel(priceL);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel brandLabel = new JLabel(product.getBrand());

        JTextArea descriptionArea = new JTextArea(product.getDescription());
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 5, 5));

        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<Integer> cart= user.getCartProductsId();
                cart.add(product.getId());
                user.setCartProductsId(cart);
                user.applyChanges();

                        }
        });
        buttonPanel.add(priceLabel);
        buttonPanel.add(addToCartButton);

        JButton rateButton = new JButton("Rate Product");
        rateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double rate=((double)(product.getRate()* product.getNumberOfRates()) + 1)/(product.getNumberOfRates()+1);
                product.setRate(rate);
            }
        });
        buttonPanel.add(rateButton);

        add(imageLabel, BorderLayout.NORTH);
        add(nameLabel, BorderLayout.CENTER);
        add(brandLabel, BorderLayout.SOUTH);
        //add(new JScrollPane(descriptionArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        //addToCartButton.addActionListener(this);


    }
}
