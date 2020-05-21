package de.dhbwka.java.exercise.ui;

import javax.swing.*;
import java.awt.BorderLayout;

public class CurrencyCalculator extends JFrame {
    JTextField textField;

    public  CurrencyCalculator() {
        BorderLayout layout = new BorderLayout();
        JPanel panel = new JPanel(layout);

        this.textField = new JTextField("Enter amount to convert...");
        panel.add(textField, layout.NORTH);

        JButton btnEURToUSD = new JButton("EUR -> USD");
        JButton btnUSDToEUR = new JButton("USD -> EUR");
        JButton btnCancel = new JButton("Cancel");

        panel.add(btnEURToUSD, layout.WEST);
        panel.add(btnUSDToEUR, layout.CENTER);
        panel.add(btnCancel, layout.EAST);

        this.setTitle("Currency converter");
        this.add(panel);
        this.setSize(400, 100);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new CurrencyCalculator();
    }
}
