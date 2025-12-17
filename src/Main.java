import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    BankAccount acc1 = new BankAccount("ACC1001", 500.0);
    BankAccount acc2 = new BankAccount("ACC2001", 300.0);

    User user1 = new User("Ramazan", acc1);
    User user2 = new User("Nurasyl", acc2);

    BankService service = new BankService();
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("\n=== ONLINE BANKING SYSTEM ===");
      System.out.println("1. Add money");
      System.out.println("2. Withdraw money");
      System.out.println("3. Transfer to Nurasyl");
      System.out.println("4. Show balance");
      System.out.println("5. Exit");
      System.out.print("Choose an option: ");

      int choice = scanner.nextInt();

      switch (choice) {

        case 1:
          System.out.print("Enter add amount: ");
          double depositAmount = scanner.nextDouble();
          service.deposit(user1, depositAmount);
          break;

        case 2:
          System.out.print("Enter withdrawal amount: ");
          double withdrawAmount = scanner.nextDouble();
          service.withdraw(user1, withdrawAmount);
          break;

        case 3:
          System.out.print("Enter transfer amount to Nurasyl: ");
          double transferAmount = scanner.nextDouble();
          service.transfer(user1, user2, transferAmount);
          break;

        case 4:
          System.out.println("Ramazan's balance: " + user1.getAccount().getBalance());
          System.out.println("Nurasyl's balance: " + user2.getAccount().getBalance());
          break;

        case 5:
          System.out.println("Exiting the program...");
          return;

        default:
          System.out.println("Invalid option. Please try again.");
      }
    }
  }
}