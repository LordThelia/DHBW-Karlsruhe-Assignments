package de.dhbwka.java.exams.jBay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BieterTerminal extends JFrame implements ActionListener {
    private Bieter bieter;
    private Auktionshaus ah;
    private JLabel lblTime;
    private JPanel panAuctions = new JPanel();

    public BieterTerminal(Bieter bieter, Auktionshaus ah) {
        super();
        this.bieter = bieter;
        this.ah = ah;

        this.lblTime = new JLabel(Calendar.getInstance().getTime().toString());
        this.add(this.lblTime, BorderLayout.NORTH);
        this.add(this.panAuctions);

        update();

        this.setTitle(bieter.getFullName());
        this.setSize(1100, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void update() {
        this.panAuctions.removeAll();

        List<Auktion> auctions = this.ah.getAuktionen();
        for(int i = 0; i < auctions.size(); ++i) {
            Auktion auc = auctions.get(i);

            JPanel pAuc = new JPanel();
            pAuc.setLayout(new GridLayout());

            pAuc.add(new JLabel(auc.getItem().getTitle()));
            pAuc.add(new JLabel(String.valueOf(auc.getPrice())));
            if(auc.getBid() == null) {
                pAuc.add(new JLabel("---"));
            } else {
                pAuc.add(new JLabel(auc.getBid().getBidder().getFullName()));
            }
            pAuc.add(new JLabel(auc.getEnd().getTime().toString()));
            JButton btn = new JButton("Gebot");
            btn.setActionCommand(String.valueOf(i));
            btn.addActionListener(this);

            if(Calendar.getInstance().getTime().after(auc.getEnd().getTime())) {
                btn.setEnabled(false);
            }

            pAuc.add(btn);
            this.panAuctions.add(pAuc);
        }

        this.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Auktion auc = this.ah.getAuktionen().get(Integer.valueOf(actionEvent.getActionCommand()));

        if(Calendar.getInstance().getTime().after(auc.getEnd().getTime())) {
            JOptionPane.showMessageDialog(null, "Die Auktion ist leider schon vorbei");
        } else {
            String inpBid = JOptionPane.showInputDialog("Bitte neues Gebot eingeben.", auc.getPrice() + Auktion.getIncrement());

            double bid = 0.0;
            try {
                bid = Double.valueOf(inpBid);
            } catch (Exception e) {}

            Gebot geb = new Gebot(bid, this.bieter);
            if(auc.gebotAbgeben(geb)) {
                JOptionPane.showMessageDialog(null, "Sie sind Höchstbietender!");
            } else {
                JOptionPane.showMessageDialog(null, "Gebot zu gering!");
            }

            this.ah.log(geb, auc.getItem());
        }

        this.ah.updateTerminals();
    }

    public void updateTime() {
        this.lblTime.setText(Calendar.getInstance().getTime().toString());
        this.revalidate();
    }
}
