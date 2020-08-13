package de.dhbwka.java.exams.quartets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerTerm extends JFrame implements QuartetsClient, ActionListener {
    private Player player;
    private Game game;
    private JLabel lblCardTitle = new JLabel("", SwingConstants.CENTER);
    private JLabel lblCardNum = new JLabel("", SwingConstants.CENTER);
    private JPanel panCategories;

    public PlayerTerm(Player player, Game game) {
        super();
        this.player = player;
        this.game = game;

        this.panCategories = new JPanel(new GridLayout(game.getCategories().size(), 3));

        this.add(this.lblCardTitle, BorderLayout.NORTH);
        this.add(this.panCategories, BorderLayout.CENTER);
        this.add(this.lblCardNum, BorderLayout.SOUTH);

        this.setTitle(player.getName());
        this.setSize(300, 250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public void showTopCard(boolean active) {
        if(this.player.hasCards()) {
            this.lblCardTitle.setText(player.getTopCard().getTitle());
            this.lblCardNum.setText(player.getCards().size() + " Cards");
            updateCategories(active);
        } else {
            this.lblCardTitle.setText("No more cards");
            this.lblCardNum.setText("0 Cards");
        }

        this.revalidate();
    }

    @Override
    public void setPlayedSeconds(int seconds) {
        this.setTitle(this.player.getName() + " (" + seconds + ")");
        this.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        game.categorySelected(game.getCategories().get(Integer.valueOf(actionEvent.getActionCommand())));
    }

    private void updateCategories(boolean active) {
        this.panCategories.removeAll();
        for(int i = 0; i < game.getCategories().size(); ++i) {
            Category cat = game.getCategories().get(i);
            this.panCategories.add(new JLabel(cat.getTitle()));
            this.panCategories.add(new JLabel(this.player.getTopCard().getValue(cat) + cat.getUnit()));

            JButton btn = new JButton("Go!");
            btn.setActionCommand(String.valueOf(i));
            btn.addActionListener(this);
            btn.setEnabled(active);
            this.panCategories.add(btn);
        }
    }
}
