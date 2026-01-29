package bank;

import java.util.Objects;

public abstract class BankAccount implements Comparable<BankAccount> {
    private String accountNumber;
    protected double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public double getBalance() {
        return this.balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public abstract boolean withdraw(double amount);

    public int compareTo(BankAccount other) {
        return Double.compare(this.balance, other.balance);
    }

    public String toString() {
        return "Acc: " + this.accountNumber + " | Balance: " + this.balance;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            BankAccount that = (BankAccount)o;
            return Objects.equals(this.accountNumber, that.accountNumber);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.accountNumber});
    }
}