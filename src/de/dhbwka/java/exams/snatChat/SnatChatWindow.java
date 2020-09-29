package de.dhbwka.java.exams.snatChat;

import javax.swing.*;
import java.awt.*;

public class SnatChatWindow extends JFrame implements SnatChatFrontend {
    private SnatChatRoom room;
    private Account acc;
    private ChatMessagesComponent msgComponent = new ChatMessagesComponent();

    public SnatChatWindow(SnatChatRoom room, Account acc) {
        this.room = room;
        this.acc = acc;

        this.setTitle(acc.getName() + " (" + room.getRoomName() + ")");
        this.setLayout(new BorderLayout());

        JLabel lblName = new JLabel(acc.getName(), JLabel.CENTER);
        lblName.setForeground(acc.getColor());
        this.add(lblName, BorderLayout.NORTH);

        JPanel panChat = new JPanel(new BorderLayout());
        panChat.add(this.msgComponent, BorderLayout.CENTER);

        JPanel panStatus = new JPanel();
        ButtonGroup statusGroup = new ButtonGroup();
        for(State state : State.values()) {
            JRadioButton rbtn = new JRadioButton(state.getLabel());

            if(acc.getState() == state) {
                rbtn.setSelected(true);
            }

            statusGroup.add(rbtn);
            panStatus.add(rbtn);

            rbtn.addActionListener(e -> {
                String notification = "State of user '" + this.acc.getName() + "' is now '" + state.getLabel() + "'";
                this.room.sendMessage(notification);
                this.acc.setState(state);
            });
        }
        panChat.add(panStatus, BorderLayout.SOUTH);

        JPanel panInput = new JPanel(new BorderLayout());
        JTextField txtMsg = new JTextField();
        panInput.add(txtMsg, BorderLayout.CENTER);

        JButton btnSend = new JButton("Send");
        btnSend.addActionListener(e -> {
            String msg = txtMsg.getText();

            if(msg.isBlank()) {
                JOptionPane.showMessageDialog(panInput, "Please enter a Message!");
            } else {
                this.room.sendMessage(new Message(msg, this.acc));
                txtMsg.setText("");
            }
        });
        panInput.add(btnSend, BorderLayout.EAST);

        this.add(panChat, BorderLayout.CENTER);
        this.add(panInput, BorderLayout.SOUTH);

        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void receiveMessage(Message msg) {
        JLabel lblText = new JLabel(msg.getSender().getName() + ": " + msg.getText());
        lblText.setForeground(msg.getSender().getColor());
        addMessageLabel(lblText);
    }

    @Override
    public void receiveMessage(String text) {
        JLabel lblText = new JLabel(text);
        lblText.setForeground(Color.GRAY);
        addMessageLabel(lblText);
    }

    @Override
    public Account getAccount() {
        return this.acc;
    }

    private void addMessageLabel(JLabel lbl) {
        String origTxt = lbl.getText();
        this.msgComponent.add(lbl);

        new Thread(() -> {
            int rest = 30;
            while(rest > 0) {
                lbl.setText(origTxt + " [" + rest + "]");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}

                --rest;
            }

            msgComponent.remove(lbl);
        }).start();
    }
}
