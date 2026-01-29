package bank;

import java.util.Objects;

public class User implements Comparable<User> {
    private String name;
    private BankAccount account;

    public User(String name, BankAccount account) {
        this.name = name;
        this.account = account;
    }

    public void addMoney(double amount) {
        this.account.deposit(amount);
    }

    public boolean takeMoney(double amount) {
        return this.account.withdraw(amount);
    }

    public boolean sendMoneyTo(User recipient, double amount) {
        if (this.account.withdraw(amount)) {
            recipient.addMoney(amount);
            return true;
        } else {
            return false;
        }
    }

    public boolean nameIs(String searchName) {
        return this.name.equalsIgnoreCase(searchName);
    }

    public boolean hasBalanceMoreThan(double amount) {
        return this.account.getBalance() > amount;
    }

    public String getName() {
        return this.name;
    }

    public double getInternalBalance() {
        return this.account.getBalance();
    }

    public String toString() {
        String var10000 = this.name;
        return "bank.User: " + var10000 + " [" + this.account.toString() + "]";
    }

    public int compareTo(User other) {
        return this.name.compareTo(other.name);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            User user = (User)o;
            return Objects.equals(this.name, user.name);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.name});
    }
}