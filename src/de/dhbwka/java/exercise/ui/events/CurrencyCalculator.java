package de.dhbwka.java.exercise.ui.event;

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
        btnEURToUSD.addActionListener(f -> {
            Double amount = validateInput();
            if(amount != null) {
                textField.setText(String.valueOf(amount * 1.09));
            }
        });

        JButton btnUSDToEUR = new JButton("USD -> EUR");
        btnUSDToEUR.addActionListener(f -> {
            Double amount = validateInput();
            if(amount != null) {
                textField.setText(String.valueOf(amount / 1.09));
            }
        });

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(f -> System.exit(0));

        panel.add(btnEURToUSD, layout.WEST);
        panel.add(btnUSDToEUR, layout.CENTER);
        panel.add(btnCancel, layout.EAST);

        this.setTitle("Currency converter");
        this.add(panel);
        this.setSize(400, 100);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private Double validateInput() {
        try {
            return Double.parseDouble(this.textField.getText());
        } catch (Exception e) {
            textField.setText("Invalid input");
            return null;
        }
    };

    public static void main(String[] args) {
        new CurrencyCalculator();
    }
}
