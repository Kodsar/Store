package com.example.store;
import javax.swing.*;

import java.awt.Color;

public class myButton extends JButton {
    myButton(String Text) {
        this.setText(Text);
        this.setBackground(new Color(241, 238, 220));
        //this.setBackground(new Color(182, 187, 196));
        //this.setFont(new Font("Arial-PLAIN-11", Font.BOLD, 14));
    }
}