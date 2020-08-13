package de.dhbwka.java.exams.quartets;

public interface QuartetsClient {
    public Player getPlayer();
    public void showTopCard(boolean active);
    public void setPlayedSeconds(int secs);
}
