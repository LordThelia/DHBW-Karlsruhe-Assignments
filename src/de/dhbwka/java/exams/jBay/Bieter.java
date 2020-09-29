package de.dhbwka.java.exams.jBay;

public class Bieter {
    private String firstname;
    private String lastname;

    public Bieter(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFullName() {
        return this.firstname + " " + this.lastname;
    }
}
