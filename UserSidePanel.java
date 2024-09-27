package com.example.store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserSidePanel extends JPanel implements ActionListener {
    JButton viewProfileButton, viewCartButton, viewProductsButton, searchProductsButton, rateProductsButton;

    public UserSidePanel(JFrame frame) {
        setLayout(new GridLayout(5, 1));
        setBackground(new Color(190, 215, 220));
        viewProfileButton = new JButton("View Profile");
        viewCartButton = new JButton("View Cart");
        viewProductsButton = new JButton("View Products");
        searchProductsButton = new JButton("Search Products");
        rateProductsButton = new JButton("Rate Products");

        viewProfileButton.setBackground(new Color(179, 200, 207));
        viewCartButton.setBackground(new Color(179, 200, 207));
        viewProductsButton.setBackground(new Color(179, 200, 207));
        viewProfileButton.setBackground(new Color(179, 200, 207));
        searchProductsButton.setForeground(new Color(49, 54, 63));
        rateProductsButton.setBackground(new Color(49, 54, 63));



        add(viewProfileButton);
        add(viewCartButton);
        add(viewProductsButton);
        add(searchProductsButton);
        add(rateProductsButton);

        viewProfileButton.addActionListener(this);
        viewCartButton.addActionListener(this);
        viewProductsButton.addActionListener(this);
        searchProductsButton.addActionListener(this);
        rateProductsButton.addActionListener(this);


        setBounds(570,10,120,500);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewProfileButton) {
        }
    }

    
}

