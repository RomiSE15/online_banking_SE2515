import java.util.*;

public class Main {
  public static void main(String[] args) {

    Account acc1 = new SavingsAccount("ACC1001", 500);
    Account acc2 = new SavingsAccount("ACC2001", 300);

    User u1 = new User("Ramazan", acc1);
    User u2 = new User("Nurasyl", acc2);

    List<User> users = new ArrayList<>();
    users.add(u1);
    users.add(u2);

    BankService service = new BankService();
    Scanner sc = new Scanner(System.in);

    while (true) {
      System.out.println("\n1. Add money");
      System.out.println("2. Withdraw");
      System.out.println("3. Transfer");
      System.out.println("4. Show all users");
      System.out.println("5. Sort by balance");
      System.out.println("6. Exit");

      int choice = sc.nextInt();

      switch (choice) {

        case 1 -> {
          System.out.print("Enter amount: ");
          double amount = sc.nextDouble();
          service.deposit(u1, amount);
        }

        case 2 -> {
          System.out.print("Enter amount: ");
          double amount = sc.nextDouble();
          service.withdraw(u1, amount);
        }

        case 3 -> {
          System.out.print("Enter amount: ");
          double amount = sc.nextDouble();
          service.transfer(u1, u2, amount);
        }

        case 4 -> users.forEach(System.out::println);

        case 5 -> {
          users.sort(Comparator.comparingDouble(
                  u -> u.getAccount().getBalance()));
          users.forEach(System.out::println);
        }

        case 6 -> System.exit(0);
      }
    }
  }
}
