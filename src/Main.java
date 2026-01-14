import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankService service = new BankService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Show All | \n2. Add User | " +
                    "\n3. Deposit/Withdraw | \n4. Delete User | \n0. Exit");
            int choice = scanner.nextInt();

            if (choice == 0) break;

            switch (choice) {
                case 1:
                    service.printAll();
                    break;
                case 2:
                    System.out.print("Name: ");
                    String name = scanner.next();
                    System.out.print("Acc Num: ");
                    String acc = scanner.next();
                    System.out.print("Balance: ");
                    double bal = scanner.nextDouble();
                    service.addUser(name, acc, bal);
                    break;
                case 3:
                    System.out.print("User Name: ");
                    String uName = scanner.next();
                    System.out.print("Amount (+ to deposit, - to withdraw): ");
                    double amount = scanner.nextDouble();
                    service.updateBalance(uName, amount);
                    break;
                case 4:
                    System.out.print("Name to delete: ");
                    String delName = scanner.next();
                    service.deleteUser(delName);
                    break;
            }
        }
    }
}