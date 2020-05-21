package de.dhbwka.java.exercise.ui.editor;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.List;

public class Editor extends JFrame {
    private JTextPane textPane;
    private JFileChooser fileChooser;
    private JMenuItem itemSave;
    private String filePath;

    public Editor() {
        super("Editor");

        this.textPane = new JTextPane();
        JScrollPane scrollPane = new JScrollPane(this.textPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.getContentPane().add(scrollPane);

        addMenuBar();

        this.setSize(640, 480);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu sendToSubMenu = new JMenu("Senden an");
        sendToSubMenu.add(new JMenuItem("E-Mail-Empfänger"));
        sendToSubMenu.add(new JMenuItem("E-Mail-Empfänger (zur Überarbeitung)"));
        sendToSubMenu.add(new JMenuItem("E-Mail-Empfänger (als Anlage)"));
        sendToSubMenu.addSeparator();
        sendToSubMenu.add(new JMenuItem("Verteilerempfänger..."));
        sendToSubMenu.add(new JMenuItem("Onlinebesprechungsteilnehmer"));
        sendToSubMenu.add(new JMenuItem("Exchange-Ordner..."));
        sendToSubMenu.add(new JMenuItem("Fax-Empfänger..."));
        sendToSubMenu.addSeparator();
        sendToSubMenu.add(new JMenuItem("Microsoft PowerPoint"));

        JMenu file = new JMenu("Datei");
        JMenuItem itemNew = new JMenuItem("Neu");
        itemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        itemNew.addActionListener(f -> this.textPane.setText(""));
        file.add(itemNew);

        initFileChooser();
        JMenuItem itemOpen = new JMenuItem("Öffnen");
        itemOpen.addActionListener(this::chooseFile);
        file.add(itemOpen);

        file.addSeparator();
        file.add(new JMenuItem("Schließen"));

        file.addSeparator();
        this.itemSave = new JMenuItem("Speichern");
        this.itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        this.itemSave.addActionListener(this::saveFile);
        this.itemSave.setEnabled(false);
        file.add(this.itemSave);

        file.add(new JMenuItem("Speichern unter..."));
        file.add(new JMenuItem("Als Webseite speichern"));
        file.add(new JMenuItem("Suchen"));
        file.addSeparator();
        file.add(new JMenuItem("Versionen"));
        file.addSeparator();
        file.add(new JMenuItem("Webseitenvorschau"));
        file.addSeparator();
        file.add(new JMenuItem("Seite einrichten..."));
        file.add(new JMenuItem("Seitenansicht"));
        file.add(new JMenuItem("Drucken"));
        file.add(sendToSubMenu);
        file.add(new JMenuItem("Eigenschaften"));
        file.addSeparator();

        JMenuItem itemEnd = new JMenuItem("Beenden");
        itemEnd.addActionListener(f -> {
            String[] opts = {"Yes", "No", "Cancel"};
            int n = JOptionPane.showOptionDialog(null, "Close Program?","Confirm closing", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, opts, opts[0]);

            if(n == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        file.add(itemEnd);

        JMenu edit = new JMenu("Bearbeiten");
        edit.add(new JMenuItem("Rückgängig"));
        edit.add(new JMenuItem("Wiederholen"));
        edit.addSeparator();
        edit.add(new JMenuItem("Ausschneiden"));
        edit.add(new JMenuItem("Kopieren"));
        edit.add(new JMenuItem("Office-Zwischenablage"));
        edit.add(new JMenuItem("Einfügen"));
        edit.add(new JMenuItem("Inhalte einfügen"));
        edit.add(new JMenuItem("Als Hyperlink einfügen"));
        edit.addSeparator();
        edit.add(new JMenuItem("Löschen"));
        edit.add(new JMenuItem("Alles markieren"));
        edit.addSeparator();
        edit.add(new JMenuItem("Suchen..."));
        edit.add(new JMenuItem("Ersetzen..."));
        edit.add(new JMenuItem("Gehe zu ..."));
        edit.addSeparator();
        edit.add(new JMenuItem("Verknüpfungen ..."));
        edit.add(new JMenuItem("Objekt"));

        menuBar.add(file);
        menuBar.add(edit);

        this.setJMenuBar(menuBar);
    }

    private void saveFile(ActionEvent event) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath));
            writer.write(this.textPane.getText());
            writer.flush();
            writer.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    private void initFileChooser() {
        this.fileChooser = new JFileChooser();
        this.fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory() || file.getName().toLowerCase().endsWith(".txt");
            }

            @Override
            public String getDescription() {
                return "Text Files";
            }
        });
    }

    private void chooseFile(ActionEvent event) {
        int status = this.fileChooser.showOpenDialog(this);

        if(status == JFileChooser.APPROVE_OPTION) {
            this.filePath = this.fileChooser.getSelectedFile().getAbsolutePath();
            File textFile = new File(this.filePath);

            try {
                BufferedReader br = new BufferedReader(new FileReader(this.filePath));
                this.textPane.read(br, textFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.itemSave.setEnabled(true);
        }
    }

    public static void main(String[] args) {
        new Editor();
    }
}
