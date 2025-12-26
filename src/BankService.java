public class BankService {

    public void deposit(User user, double amount) {
        user.getAccount().deposit(amount);
        System.out.println("Added " + amount + " to " + user.getName());
    }

    public void withdraw(User user, double amount) {
        if (user.getAccount().withdraw(amount)) {
            System.out.println("Withdrawn " + amount + " from " + user.getName());
        } else {
            System.out.println("Not enough balance");
        }
    }

    public void transfer(User from, User to, double amount) {
        if (from.getAccount().withdraw(amount)) {
            to.getAccount().deposit(amount);
            System.out.println("Transferred " + amount + " from " +
                    from.getName() + " to " + to.getName());
        } else {
            System.out.println("Transfer failed");
        }
    }
}
