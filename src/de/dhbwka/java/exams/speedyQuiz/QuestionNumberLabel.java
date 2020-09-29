package de.dhbwka.java.exams.speedyQuiz;

import javax.swing.*;

public class QuestionNumberLabel extends JLabel {
    private Status status = Status.PENDING;

    public QuestionNumberLabel(String text) {
        super(text);
        this.setOpaque(true);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setBackground(status.getColor());
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
        this.setBackground(status.getColor());
        this.revalidate();
    }
}
