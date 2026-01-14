import java.util.Objects;

// Абстрактный класс
public abstract class BankAccount implements Comparable<BankAccount> {
    private String accountNumber;
    protected double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Геттеры оставим только для внутреннего пользования или toString
    // В Main мы их вызывать не будем
    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public abstract boolean withdraw(double amount);

    @Override
    public int compareTo(BankAccount other) {
        return Double.compare(this.balance, other.balance);
    }

    @Override
    public String toString() {
        return "Acc: " + accountNumber + " | Balance: " + balance;
    }

    // equals и hashCode нужны для коллекций
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return Objects.equals(accountNumber, that.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }
}