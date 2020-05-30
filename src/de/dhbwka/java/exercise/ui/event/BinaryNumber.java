package de.dhbwka.java.exercise.ui.events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BinaryNumber extends JFrame {
    private JPanel pToggle;
    private JLabel sum;
    private JToggleButton t128, t64, t32, t16, t8, t4, t2, t1;

    public BinaryNumber() {
        this.pToggle = new JPanel();
        this.pToggle.setLayout(new GridLayout(2, 8));
        this.sum = new JLabel("0", SwingConstants.CENTER);
        this.sum.setFont(this.sum.getFont().deriveFont(30f));

        addToggleButtons();

        this.add(this.pToggle, BorderLayout.NORTH);
        this.add(this.sum, BorderLayout.SOUTH);

        this.setTitle("Binary Number");
        this.setSize(600, 200);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void addToggleButtons() {
        this.t128 = new JToggleButton();
        this.t64 = new JToggleButton();
        this.t32 = new JToggleButton();
        this.t16 = new JToggleButton();
        this.t8 = new JToggleButton();
        this.t4 = new JToggleButton();
        this.t2 = new JToggleButton();
        this.t1 = new JToggleButton();

        ImageIcon imgOff = new ImageIcon("icons/off.png");
        ImageIcon imgOn = new ImageIcon("icons/on.png");

        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateBinary();
            }
        };

        this.t128.setIcon(imgOff);
        this.t128.setSelectedIcon(imgOn);
        this.t64.setIcon(imgOff);
        this.t64.setSelectedIcon(imgOn);
        this.t32.setIcon(imgOff);
        this.t32.setSelectedIcon(imgOn);
        this.t16.setIcon(imgOff);
        this.t16.setSelectedIcon(imgOn);
        this.t8.setIcon(imgOff);
        this.t8.setSelectedIcon(imgOn);
        this.t4.setIcon(imgOff);
        this.t4.setSelectedIcon(imgOn);
        this.t2.setIcon(imgOff);
        this.t2.setSelectedIcon(imgOn);
        this.t1.setIcon(imgOff);
        this.t1.setSelectedIcon(imgOn);

        this.t128.addActionListener(action);
        this.t64.addActionListener(action);
        this.t32.addActionListener(action);
        this.t16.addActionListener(action);
        this.t8.addActionListener(action);
        this.t4.addActionListener(action);
        this.t2.addActionListener(action);
        this.t1.addActionListener(action);

        this.pToggle.add(this.t128);
        this.pToggle.add(this.t64);
        this.pToggle.add(this.t32);
        this.pToggle.add(this.t16);
        this.pToggle.add(this.t8);
        this.pToggle.add(this.t4);
        this.pToggle.add(this.t2);
        this.pToggle.add(this.t1);

        this.pToggle.add(new JLabel("2^7", SwingConstants.CENTER));
        this.pToggle.add(new JLabel("2^6", SwingConstants.CENTER));
        this.pToggle.add(new JLabel("2^5", SwingConstants.CENTER));
        this.pToggle.add(new JLabel("2^4", SwingConstants.CENTER));
        this.pToggle.add(new JLabel("2^3", SwingConstants.CENTER));
        this.pToggle.add(new JLabel("2^2", SwingConstants.CENTER));
        this.pToggle.add(new JLabel("2^1", SwingConstants.CENTER));
        this.pToggle.add(new JLabel("2^0", SwingConstants.CENTER));
    }

    private void calculateBinary() {
        int sum = 0;

        if(this.t128.isSelected()) { sum += 128; }
        if(this.t64.isSelected()) { sum += 64; }
        if(this.t32.isSelected()) { sum += 32; }
        if(this.t16.isSelected()) { sum += 16; }
        if(this.t8.isSelected()) { sum += 8; }
        if(this.t4.isSelected()) { sum += 4; }
        if(this.t2.isSelected()) { sum += 2; }
        if(this.t1.isSelected()) { sum += 1; }

        this.sum.setText(Integer.toString(sum));
    }

    public static void main(String[] args) {
        new BinaryNumber();
    }
}
