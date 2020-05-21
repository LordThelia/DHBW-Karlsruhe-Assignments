package de.dhbwka.java.exercise.ui;

import javax.swing.*;

public class EditorSimple extends JFrame {
    public EditorSimple() {
        JTextPane textPane = new JTextPane();
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.getContentPane().add(scrollPane);

        JMenuBar menuBar = new JMenuBar();

        JMenu sendToSubMenu = new JMenu("Senden an") {{
            add(new JMenuItem("E-Mail-Empfänger"));
            add(new JMenuItem("E-Mail-Empfänger (zur Überarbeitung)"));
            add(new JMenuItem("E-Mail-Empfänger (als Anlage)"));
            addSeparator();
            add(new JMenuItem("Verteilerempfänger..."));
            add(new JMenuItem("Onlinebesprechungsteilnehmer"));
            add(new JMenuItem("Exchange-Ordner..."));
            add(new JMenuItem("Fax-Empfänger..."));
            addSeparator();
            add(new JMenuItem("Microsoft PowerPoint"));
        }};

        JMenu file = new JMenu("Datei") {{
            add(new JMenuItem("Neu"));
            add(new JMenuItem("Öffnen"));
            addSeparator();
            add(new JMenuItem("Schließen"));
            addSeparator();
            add(new JMenuItem("Speichern"));
            add(new JMenuItem("Speichern unter..."));
            add(new JMenuItem("Als Webseite speichern"));
            add(new JMenuItem("Suchen"));
            addSeparator();
            add(new JMenuItem("Versionen"));
            addSeparator();
            add(new JMenuItem("Webseitenvorschau"));
            addSeparator();
            add(new JMenuItem("Seite einrichten..."));
            add(new JMenuItem("Seitenansicht"));
            add(new JMenuItem("Drucken"));
            add(sendToSubMenu);
            add(new JMenuItem("Eigenschaften"));
            addSeparator();
            add(new JMenuItem("Beenden"));
        }};

        JMenu edit = new JMenu("Bearbeiten") {{
            add(new JMenuItem("Rückgängig"));
            add(new JMenuItem("Wiederholen"));
            addSeparator();
            add(new JMenuItem("Ausschneiden"));
            add(new JMenuItem("Kopieren"));
            add(new JMenuItem("Office-Zwischenablage"));
            add(new JMenuItem("Einfügen"));
            add(new JMenuItem("Inhalte einfügen"));
            add(new JMenuItem("Als Hyperlink einfügen"));
            addSeparator();
            add(new JMenuItem("Löschen"));
            add(new JMenuItem("Alles markieren"));
            addSeparator();
            add(new JMenuItem("Suchen..."));
            add(new JMenuItem("Ersetzen..."));
            add(new JMenuItem("Gehe zu ..."));
            addSeparator();
            add(new JMenuItem("Verknüpfungen ..."));
            add(new JMenuItem("Objekt"));
        }};

        menuBar.add(file);
        menuBar.add(edit);
        this.setJMenuBar(menuBar);

        this.setSize(640, 480);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new EditorSimple();
    }
}
