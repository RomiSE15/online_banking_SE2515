import java.util.Objects;

public class User implements Comparable<User> {
    private String name;
    private BankAccount account;

    // Конструктор
    public User(String name, BankAccount account) {
        this.name = name;
        this.account = account;
    }

    // === ЛОГИКА ВНУТРИ ЮЗЕРА (БЕЗ ГЕТТЕРОВ СЧЕТА) ===

    // Вместо user.getAccount().deposit()
    public void addMoney(double amount) {
        this.account.deposit(amount);
    }

    // Вместо user.getAccount().withdraw()
    public boolean takeMoney(double amount) {
        return this.account.withdraw(amount);
    }

    // Логика трансфера тоже здесь
    public boolean sendMoneyTo(User recipient, double amount) {
        if (this.account.withdraw(amount)) {
            recipient.addMoney(amount);
            return true;
        }
        return false;
    }

    // Для фильтрации и поиска (вместо геттеров используем методы-вопросы)
    public boolean nameIs(String searchName) {
        return this.name.equalsIgnoreCase(searchName);
    }

    public boolean hasBalanceMoreThan(double amount) {
        return this.account.getBalance() > amount;
    }

    // Геттер имени оставим только для красивого вывода в консоль
    public String getName() {
        return name;
    }

    // Метод для сортировки (сравниваем балансы через внутренний доступ)
    public double getInternalBalance() {
        return account.getBalance();
    }

    @Override
    public String toString() {
        return "User: " + name + " [" + account.toString() + "]";
    }

    @Override
    public int compareTo(User other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}