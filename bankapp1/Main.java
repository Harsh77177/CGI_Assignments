import model.Account;
import service.AccountManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccountManager manager = new AccountManager();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1. Add account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Display all accounts");
            System.out.println("5. Calculate interest for account");
            System.out.println("6. Get total account count");
            System.out.println("7. Get balance for account");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");
            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    System.out.print("Enter account number: ");
                    String accNo1 = scanner.nextLine();
                    System.out.print("Enter account holder name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter initial balance: ");
                    double bal = Double.parseDouble(scanner.nextLine());
                    Account acc = new Account(accNo1, name, bal);
                    manager.addAccount(acc);
                    System.out.println("Account added: " + acc);
                    break;

                case 2:
                    System.out.print("Enter account number: ");
                    String accNo2 = scanner.nextLine();
                    System.out.print("Enter amount to deposit: ");
                    double amtD = Double.parseDouble(scanner.nextLine());
                    if (manager.depositToAccount(accNo2, amtD)) {
                        System.out.println("Deposit successful.");
                    }
                    break;

                case 3:
                    System.out.print("Enter account number: ");
                    String accNo3 = scanner.nextLine();
                    System.out.print("Enter amount to withdraw: ");
                    double amtW = Double.parseDouble(scanner.nextLine());
                    if (manager.withdrawFromAccount(accNo3, amtW)) {
                        System.out.println("Withdrawal successful.");
                    }
                    break;

                case 4:
                    manager.displayAllAccounts();
                    break;

                case 5:
                    System.out.print("Enter account number: ");
                    String accNo5 = scanner.nextLine();
                    double interest = manager.calculateInterestForAccount(accNo5);
                    System.out.printf("Calculated interest: %.2f%n", interest);
                    break;

                case 6:
                    System.out.println("Total accounts: " + Account.getAccountCount());
                    break;

                case 7:
                    System.out.print("Enter account number: ");
                    String accNo7 = scanner.nextLine();
                    if (manager.findAccount(accNo7).isPresent()) {
                        System.out.println("Balance: " + manager.findAccount(accNo7).get().getBalance());
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 0:
                    running = false;
                    System.out.println("Exiting. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }
}
