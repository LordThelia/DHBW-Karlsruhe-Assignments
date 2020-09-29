package de.dhbwka.java.exams.stadtLandFluss;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Sheet extends JFrame {
    private Player player;
    private Game game;
    private JButton btnStart = new JButton("Start");
    private JButton btnStop = new JButton("Stop");
    private List<JTextField> textFields = new ArrayList<>();
    private List<Integer> points = new ArrayList<>();
    private JPanel panTop = new JPanel(new GridLayout(3, 2));
    private JPanel panCenter = new JPanel();
    private JPanel panBottom = new JPanel(new FlowLayout(FlowLayout.CENTER));

    public Sheet(Player player, Game game) {
        super();
        this.player = player;
        this.game = game;

        setPanelTop();
        this.panCenter.add(new JLabel("Kein Spiel aktiv.", JLabel.CENTER));

        this.btnStart.addActionListener(e -> game.startGame());
        this.btnStop.addActionListener(e -> game.stopGame());
        this.btnStop.setEnabled(false);
        this.panBottom.add(this.btnStart);
        this.panBottom.add(this.btnStop);

        this.add(this.panTop, BorderLayout.NORTH);
        this.add(this.panCenter, BorderLayout.CENTER);
        this.add(this.panBottom, BorderLayout.SOUTH);

        setTitle(player.getName());
        this.setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void startGame() {
        this.btnStart.setEnabled(false);
        this.btnStop.setEnabled(true);
        setPanelTop();
        setPanelCenter(false);
    }

    public void stopGame() {
        this.btnStart.setEnabled(true);
        this.btnStop.setEnabled(false);
    }

    private void setPanelTop() {
        this.panTop.removeAll();

        this.panTop.add(new JLabel("Punkte"));
        this.panTop.add(new JLabel(String.valueOf(this.player.getPoints())));
        this.panTop.add(new JLabel("Verbleibende Sekunden"));
        this.panTop.add(new JLabel(String.valueOf(this.game.getTimeLeft())));
        this.panTop.add(new JLabel("Anfangsbuchstabe"));
        this.panTop.add(new JLabel(String.valueOf(this.game.getFirstChar())));

        this.revalidate();
    }

    private void setPanelCenter(boolean addPoints) {
        this.panCenter.removeAll();
        this.panCenter.setLayout(new GridLayout(this.game.getColumns().size(), 3));

        if(!addPoints) {
            this.textFields = new ArrayList<>();
        }

        List<ColumnType> columns = this.game.getColumns();
        for(int i = 0; i < columns.size(); ++i) {
            this.panCenter.add(new JLabel(columns.get(i).getTitle()));

            if(addPoints) {
                this.panCenter.add(this.textFields.get(i));
                this.panCenter.add(new JLabel(String.valueOf(this.points.get(i))));
            } else {
                JTextField txt = new JTextField(40);
                this.textFields.add(txt);

                this.panCenter.add(txt);
                this.panCenter.add(new JLabel("0"));
            }
        }

        this.revalidate();
    }

    public void setPoints(List<Integer> points) {
        this.points = points;
        setPanelCenter(true);
    }

    public void refreshTop() {
        setPanelTop();
    }

    public String getAnswer(int index) {
        return this.textFields.get(index).getText();
    }

    public Player getPlayer() {
        return player;
    }
}
