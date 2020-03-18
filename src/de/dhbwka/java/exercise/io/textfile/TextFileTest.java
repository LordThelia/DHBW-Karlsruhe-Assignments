package de.dhbwka.java.exercise.io.textfile;

public class TextFileTest {
    public static void main(String[] args) {
        TextFile tf = new TextFile(System.getProperty("user.home") + "\\Documents\\Java\\bla.txt");
        tf.read();

        tf.replaceAll("meine", "unsre");
        tf.close();
    }
}
