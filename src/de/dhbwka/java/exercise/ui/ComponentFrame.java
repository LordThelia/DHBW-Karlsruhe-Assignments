package de.dhbwka.java.exercise.ui;

import javax.swing.*;


public class ComponentFrame extends JFrame {
    public ComponentFrame() {
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(700, 100);
        setTitle("Several basic Swing components");

        JPanel panel = new JPanel();
        panel.setSize(600, 100);
        add(panel);

        JLabel label = new JLabel("JLabel");
        JTextField textField = new JTextField("JTextField");
        textField.setSize(60, 20);
        JPasswordField passwordField = new JPasswordField("JPasswordField");
        passwordField.setSize(60, 20);
        JButton button = new JButton("JButton");
        JToggleButton toggleButton = new JToggleButton("JToggleButton");
        JCheckBox checkBox = new JCheckBox("JCheckBox");
        JComboBox<String> comboBox = new JComboBox<String>();

        comboBox.addItem("1. Item");
        comboBox.addItem("2. Item");
        comboBox.addItem("3. Item");

        panel.add(label);
        panel.add(textField);
        panel.add(passwordField);
        panel.add(button);
        panel.add(toggleButton);
        panel.add(checkBox);
        panel.add(comboBox);

        JPanel radioPanel = new JPanel();

        ButtonGroup buttonGroup = new ButtonGroup();

        JRadioButton radio1 = new JRadioButton("Radio-Button-1");
        JRadioButton radio2 = new JRadioButton("Radio-Button-2");
        JRadioButton radio3 = new JRadioButton("Radio-Button-3");

        buttonGroup.add(radio1);
        buttonGroup.add(radio2);
        buttonGroup.add(radio3);

        radioPanel.add(radio1);
        radioPanel.add(radio2);
        radioPanel.add(radio3);

        panel.add(radioPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new ComponentFrame();
    }
}