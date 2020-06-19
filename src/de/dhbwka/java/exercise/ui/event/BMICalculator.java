package de.dhbwka.java.exercise.ui.event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BMICalculator extends JFrame {
    private JTextField tfWeight;
    private JTextField tfHeight;
    private JRadioButton rbMale;
    private JRadioButton rbFemale;
    private JTextField tfBMI;
    private JTextField tfInfo;

    public BMICalculator() {
        // Weight/Height entries
        JPanel topEntries = new JPanel();
        topEntries.setLayout(new GridLayout(2,2));

        topEntries.add(new JLabel("Weight [kg]:"));
        this.tfWeight = new JTextField();
        topEntries.add(this.tfWeight);

        topEntries.add(new JLabel("Body height [m]"));
        this.tfHeight = new JTextField();
        topEntries.add(this.tfHeight);

        // Radio Buttons
        JPanel middleSelection = new JPanel();
        middleSelection.setLayout(new GridLayout(1, 2));
        ButtonGroup gender = new ButtonGroup();

        this.rbMale = new JRadioButton("male");
        this.rbFemale = new JRadioButton("female");
        gender.add(rbMale);
        gender.add(rbFemale);
        this.rbMale.setSelected(true);

        middleSelection.add(this.rbMale);
        middleSelection.add(this.rbFemale);

        // Calculate Button
        JButton calc = new JButton("Calculate");
        calc.addActionListener(this::calculateBMI);

        // BMI Output
        JPanel bottomBMI = new JPanel();
        bottomBMI.setLayout(new GridLayout(1,2));

        bottomBMI.add(new JLabel("BMI:"));
        this.tfBMI = new JTextField();
        bottomBMI.add(this.tfBMI);

        this.tfInfo = new JTextField();

        this.setLayout(new GridLayout(5, 1));
        this.add(topEntries);
        this.add(middleSelection);
        this.add(calc);
        this.add(bottomBMI);
        this.add(this.tfInfo);

        this.setTitle("BMI Calculator");
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void calculateBMI(ActionEvent event) {
        double bmi = 0.0;
        try {
            bmi = Double.parseDouble(this.tfWeight.getText()) / Math.pow(Double.parseDouble(this.tfHeight.getText()), 2);
        } catch (NumberFormatException e) {
            this.tfWeight.setText("");
            this.tfHeight.setText("");
            JOptionPane.showMessageDialog(null, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);

            return;
        }

        this.tfBMI.setText(String.valueOf(bmi));

        if(this.rbMale.isSelected()) {
            if(bmi < 20) {
                this.tfInfo.setText("Untergewicht (Short weight)");
            } else if(bmi < 25) {
                this.tfInfo.setText("Normalgewicht (Normal weight)");
            } else if(bmi < 30) {
                this.tfInfo.setText("Übergewicht (Overweight)");
            } else if(bmi < 40) {
                this.tfInfo.setText("Adipositas (Adiposity)");
            } else {
                this.tfInfo.setText("massive Adipositas (Massive Adiposity)");
            }
        } else {
            if(bmi < 19) {
                this.tfInfo.setText("Untergewicht (Short weight)");
            } else if(bmi < 24) {
                this.tfInfo.setText("Normalgewicht (Normal weight)");
            } else if(bmi < 30) {
                this.tfInfo.setText("Übergewicht (Overweight)");
            } else if(bmi < 40) {
                this.tfInfo.setText("Adipositas (Adiposity)");
            } else {
                this.tfInfo.setText("massive Adipositas (Massive Adiposity)");
            }
        }
    }

    public static void main(String[] args) {
        new BMICalculator();
    }
}
