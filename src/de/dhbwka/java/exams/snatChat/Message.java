package de.dhbwka.java.exams.snatChat;

public class Message {
    private String text;
    private Account sender;

    public Message(String text, Account sender) {
        this.text = text;
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public static String rot13(String message) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < message.length(); ++i) {
            char c = message.charAt(i);

            if       (c >= 'a' && c <= 'm') c += 13;
            else if  (c >= 'A' && c <= 'M') c += 13;
            else if  (c >= 'n' && c <= 'z') c -= 13;
            else if  (c >= 'N' && c <= 'Z') c -= 13;

            sb.append(c);
        }

        return sb.toString();
    }
}
