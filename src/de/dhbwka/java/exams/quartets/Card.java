package de.dhbwka.java.exams.quartets;

import java.util.Map;
import java.util.TreeMap;

public class Card {
    private String title;
    private Map<Category, Integer> values = new TreeMap<Category, Integer>();

    public Card(String title) {
        this.title = title;
    }

    public void setValue(Category cat, Integer val) {
        this.values.put(cat, val);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getValue(Category cat) {
        return this.values.get(cat);
    }

    public int compareTo(Category cat, Card card) {
        return this.getValue(cat) - card.getValue(cat);
    }
}
