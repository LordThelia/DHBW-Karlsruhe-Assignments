package de.dhbwka.java.exams.stadtLandFluss;

public enum ColumnType {
    CITY("Stadt"),
    COUNTRY("Land"),
    RIVER("Fluss"),
    PROFESSION("Beruf"),
    ANIMAL("Tier"),
    NAME("Vorname"),
    SPORT("Sportart"),
    FOOD("Lebensmittel"),
    BERVERAGE("Getränk"),
    GAME("Spiel");

    private String title;

    ColumnType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
