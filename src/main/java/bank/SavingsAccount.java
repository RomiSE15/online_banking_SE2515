package bank;

public class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String accountNumber, double balance, double interestRate) {
        super(accountNumber, balance);
        this.interestRate = interestRate;
    }

    public boolean withdraw(double amount) {
        if (this.balance - amount >= (double)0.0F) {
            this.balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}