package de.dhbwka.java.exercise.classes;

public class Account {
    private int acc_number;
    private String owner;
    private int balance;
    private int limit;

    public Account(int acc_number, String owner, int balance, int limit) {
        this.acc_number = acc_number;
        this.owner = owner;
        this.balance = balance;
        this.limit = limit;
    }

    public void processDeposit(int amount) {
        if(amount >= 0) {
            this.balance += amount;
        }
    }

    public void processPayment(int amount) {
        if(amount >= 0 && (this.balance - amount >= 0) && amount <= this.limit) {
            this.balance -= amount;
        } else {
            System.out.println("Error");
        }
    }

    public String accountInfo() {
        return "Stand: " + this.balance;
    }

    public String toString() {
        return "Konto-Nr: " + this.acc_number + " (" + this.owner + "), Stand: " + this.balance + "ct, Limit: " + this.limit;
    }
}
