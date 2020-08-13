package de.dhbwka.java.exams.quartets;

import java.util.LinkedList;
import java.util.Queue;

public class Player {
    private String name;
    private Queue<Card> cards;

    public Player(String name, LinkedList<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public boolean hasCards() {
        return !this.cards.isEmpty();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Card getTopCard() {
        return this.cards.peek();
    }

    public Queue<Card> getCards() {
        return cards;
    }

    public void setCards(Queue<Card> cards) {
        this.cards = cards;
    }

    public Card drawTopCard() {
        return this.cards.poll();
    }
}
