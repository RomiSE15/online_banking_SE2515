public class BankService {

    public void deposit(User user, double amount) {
        user.getAccount().deposit(amount);
        System.out.println("add " + amount + " to " + user.getName());
    }

    public void withdraw(User user, double amount) {
        boolean ok = user.getAccount().withdraw(amount);
        if (ok) {
            System.out.println("Withdrawn " + amount + " from " + user.getName());
        } else {
            System.out.println("Not enough balance for " + user.getName());
        }
    }

    public void transfer(User fromUser, User toUser, double amount) {
        if (fromUser.getAccount().withdraw(amount)) {
            toUser.getAccount().deposit(amount);
            System.out.println("Transferred " + amount + " from " +
                    fromUser.getName() + " to " + toUser.getName());
        } else {
            System.out.println("Transfer failed: insufficient funds.");
        }
    }
}