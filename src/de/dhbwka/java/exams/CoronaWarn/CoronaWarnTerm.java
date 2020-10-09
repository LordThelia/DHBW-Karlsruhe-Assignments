package de.dhbwka.java.exams.CoronaWarn;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CoronaWarnTerm extends JFrame implements CoronaWarnClient, Runnable {
    private JPhone phone;
    private WarnStatus status;
    private List<Token> tokensNearby = new ArrayList<>();
    private List<Token> tokensSelf;
    private Token currentToken;
    private JLabel lblWarnStatus;
    private List<JButton> buttons = new ArrayList<>();
    private JLabel lblSeen = new JLabel("", JLabel.CENTER);
    private Thread tokenThread;


    public CoronaWarnTerm(JPhone phone) {
        this.setLayout(new GridLayout(6, 1));

        this.phone = phone;
        this.status = WarnStatus.UNKNOWN;
        this.tokensSelf = CoronaWarn.loadTokens(phone);

        generateNewToken();

        this.lblWarnStatus = new JLabel();
        this.lblWarnStatus.setOpaque(true);
        this.lblWarnStatus.setPreferredSize(new java.awt.Dimension(0, 100));
        this.lblWarnStatus.setHorizontalAlignment(JLabel.CENTER);
        updateWarnLabel();

        JButton btnNewToken = new JButton("New Token");
        btnNewToken.addActionListener(e -> {
            generateNewToken();
            updateTooltip();
        });
        JButton btnCheck = new JButton("Check for infections");
        btnCheck.addActionListener(e -> {
            this.status = CoronaWarnAPI.checkInfection(this) ? WarnStatus.ALARM : WarnStatus.OK;
            updateWarnLabel();
        });
        JButton btnClear = new JButton("Clear tokens");
        btnClear.addActionListener(e -> {
            this.tokensSelf.clear();
            this.tokensNearby.clear();
            CoronaWarn.clearTokenStore(this.phone);
            generateNewToken();
            updateSeenLabel();
            updateTooltip();
        });
        JButton btnReport = new JButton("Report infection");
        btnReport.addActionListener(e -> {
            CoronaWarnAPI.reportInfection(this);
            for(JButton btn : this.buttons) {
                btn.setEnabled(false);
            }
            this.status = WarnStatus.INFECTED;
            updateWarnLabel();
        });

        this.buttons.add(btnNewToken);
        this.buttons.add(btnCheck);
        this.buttons.add(btnClear);
        this.buttons.add(btnReport);

        updateSeenLabel();
        updateTooltip();

        this.add(this.lblWarnStatus);
        for(JButton btn : this.buttons) {
            this.add(btn);
        }
        this.add(this.lblSeen);

        this.tokenThread = new Thread(this);
        this.tokenThread.start();

        this.setTitle(phone.getOwner());
        this.setSize(300, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        CoronaWarnAPI.checkInfection(this);
        updateSeenLabel();
    }

    private void updateSeenLabel() {
        this.lblSeen.setText("Seen Tokens: " + this.tokensNearby.size());
        this.revalidate();
    }

    private void updateTooltip() {
        this.lblSeen.setToolTipText(this.currentToken.toString());
    }

    private void updateWarnLabel() {
        this.lblWarnStatus.setText(this.status.getText());
        this.lblWarnStatus.setBackground(this.status.getColor());
        this.revalidate();
    }

    private void generateNewToken() {
        this.tokensSelf.add(this.currentToken);

        Token token = new Token();
        this.currentToken = token;
        CoronaWarn.saveToken(this.phone, token);

        CoronaWarnAPI.sendToken(this);
    }

    @Override
    public Token getCurrentToken() {
        return this.currentToken;
    }

    @Override
    public List<Token> getAllTokens() {
        return this.tokensSelf;
    }

    @Override
    public List<Token> getAllSeenTokens() {
        return this.tokensNearby;
    }

    @Override
    public void tokenReceived(Token token) {
        this.tokensNearby.add(token);
        updateSeenLabel();
    }

    @Override
    public void run() {
        while(this.status != WarnStatus.INFECTED) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            generateNewToken();
            updateTooltip();
        }
    }
}
