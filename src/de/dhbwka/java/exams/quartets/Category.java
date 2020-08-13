package de.dhbwka.java.exams.quartets;

public enum Category {
    FREEWAY("Autobahnen", "km"),
    EUROROUTE("Europastraßen", "km"),
    PIPELINE("Öl-Pipelines", "km"),
    RAILS("Schienennetz", "km"),
    INTERNET("Internet", "%"),
    INTERNET_MOBILE("Internet (mobil)", "%");

    private String title;
    private String unit;

    public String getTitle() {
        return title;
    }

    public String getUnit() {
        return unit;
    }

    Category(String title, String unit) {
        this.title = title;
        this.unit = unit;
    }
}
