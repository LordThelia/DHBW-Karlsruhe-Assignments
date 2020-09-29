package de.dhbwka.java.exams.speedyQuiz;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameTerm extends JFrame implements GameClient {
    private String playerName;
    private Game game;
    private QuestionNumberLabel[] progress;
    private JLabel question;
    private JLabel timeLeft;
    private JPanel pButtons = new JPanel();
    private ArrayList<JButton> answerButtons = new ArrayList<JButton>();
    private int points = 0;
    private int secondsRemaining = 10;

    public GameTerm(String playerName, Game game) {
        this.playerName = playerName;
        this.game = game;

        this.progress = new QuestionNumberLabel[game.getQuestionsCount()];
        JPanel pProgress = new JPanel();
        pProgress.setLayout(new GridLayout(1, game.getQuestionsCount()));

        for(int i = 0; i < game.getQuestionsCount(); ++i) {
            QuestionNumberLabel lbl = new QuestionNumberLabel(String.valueOf(i));
            lbl.setBackground(lbl.getStatus().getColor());
            this.progress[i] = lbl;
            pProgress.add(this.progress[i]);
        }

        JPanel pQuestion = new JPanel();
        pQuestion.setLayout(new GridLayout(2, 1));
        this.question = new JLabel("Starting...");
        this.timeLeft = new JLabel("0");
        pQuestion.add(this.question);
        pQuestion.add(this.timeLeft);

        this.pButtons.setLayout(new GridLayout(2, 2));
        for(int i = 0; i < 4; ++i) {
            JButton btn = new JButton(this.game.getQuestions().get(0).getAnswers()[i]);
            int index = i;
            btn.addActionListener(e -> this.game.answerSelected(this, index));
            this.pButtons.add(btn);
            this.answerButtons.add(btn);
        }

        this.add(pProgress, BorderLayout.NORTH);
        this.add(pQuestion, BorderLayout.CENTER);
        this.add(this.pButtons, BorderLayout.SOUTH);

        this.setTitle(playerName);
        this.setSize(800, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public String getPlayerName() {
        return this.playerName;
    }

    @Override
    public int getPoints() {
        if(this.points < 0) {
            return 0;
        }

        return this.points;
    }

    @Override
    public void setQuestion(int questionIndex, Question q) {
        this.progress[questionIndex].setStatus(Status.ACTIVE);
        this.question.setText(q.getQuestionText());
        this.timeLeft.setText(String.valueOf(this.secondsRemaining));

        for(int i = 0; i < this.answerButtons.size(); ++i) {
            this.answerButtons.get(i).setText(q.getAnswers()[i]);
        }

        revalidate();
    }

    @Override
    public void setRemainingSeconds(int seconds) {
        this.secondsRemaining = seconds;
        this.timeLeft.setText(String.valueOf(seconds));
        revalidate();
    }

    @Override
    public void gameIsOver() {
        for(JButton btn : this.answerButtons) {
            btn.setEnabled(false);
        }
    }

    @Override
    public void setAnswerState(int questionIndex, Status status) {
        this.points += status.getPoints();
        this.progress[questionIndex].setStatus(status);
        revalidate();
    }
}
