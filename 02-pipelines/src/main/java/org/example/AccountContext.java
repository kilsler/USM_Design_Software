package org.example;

public class AccountContext {
    private String name;
    private double balance;
    private boolean isActive;
    private boolean isDone;
    private StringBuilder history;

    public AccountContext(String name, double balance, boolean isActive) {
        this.name = name;
        this.balance = balance;
        this.isActive = isActive;
        this.isDone = false;
        this.history = new StringBuilder();
    }

    public String getName() { return name; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) {
        this.balance = balance;
        if (balance < 0) {
            isDone = true;
            history.append("Balance below zero, pipeline stopped.\n");
        }
    }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
    public boolean isDone() { return isDone; }
    public void setDone(boolean isDone){this.isDone = isDone;}
    public StringBuilder getHistory() { return history; }

    public void setHistory(StringBuilder history) {
        this.history = history;
    }
}

