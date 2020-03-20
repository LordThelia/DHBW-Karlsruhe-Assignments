package de.dhbwka.java.exams.darts;

public class Player {
    private String name;
    private int countDartsThrown;
    private Visit[] visits;

    public Player(String name) {
        this.name = name;
        this.countDartsThrown = 0;
        this.visits = new Visit[10];
    }

    public int getRemainingPoints() {
        int remaining = 501;

        for(int i = 0; i < this.visits.length; ++i) {
            if(this.visits[i] != null) {
                remaining -= this.visits[i].getValue();
            }
        }

        return remaining;
    }

    public boolean addVisit(Visit visit) {
        int remaining = getRemainingPoints();
        int visitValue = visit.getValue();
        boolean isAdded = true;

        this.countDartsThrown += visit.getFields().length;

        if(remaining - visitValue < 0) {
            isAdded = false;
        } else {
            if(remaining - visitValue == 0 && !visit.getLastField().isDoubleField()) {
                isAdded = false;
            } else if(remaining - visitValue == 1) {
                isAdded = false;
            }
        }

        if(isAdded) {
            for (int i = 0; i < this.visits.length; ++i) {
                if (this.visits[i] == null) {
                    // Add one visit, leave the loop
                    this.visits[i] = visit;
                    break;
                }
            }
        }

        return isAdded;
    }

    public String getName() {
        return name;
    }

    public int getCountDartsThrown() {
        return countDartsThrown;
    }

    public int getValidVisitsLength() {
        int visitCount = 0;

        for(int i = 0; i < this.visits.length; ++i) {
            if(this.visits[i] != null) {
                ++visitCount;
            }
        }

        return visitCount;
    }
}
