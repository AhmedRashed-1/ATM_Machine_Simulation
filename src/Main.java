import java.util.Scanner;

public class Main {

    private static final String CORRECT_PIN = "1234";
    private static final double INITIAL_BALANCE = 2500.75;

    private static double balance = INITIAL_BALANCE;
    private static int successfulTransactions = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (!login(scanner)) {
            scanner.close();
            return;
        }

        int choice;
        do {
            displayMenu();
            System.out.print("Please choose an option (1-5): ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid option.");
                scanner.next();
                System.out.print("Please choose an option (1-5): ");
            }

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit(scanner);
                    break;
                case 3:
                    withdraw(scanner);
                    break;
                case 4:
                    showAccountStatus();
                    break;
                case 5:
                    System.out.println("Thank you for using our ATM.");
                    System.out.println("Successful transactions: " + successfulTransactions);
                    break;
                default:
                    System.out.println("Invalid option.");
            }

        } while (choice != 5);

        scanner.close();
    }

    private static boolean login(Scanner scanner) {
        int attempts = 0;

        while (attempts < 3) {
            System.out.print("Please enter your 4-digit PIN: ");
            String enteredPin = scanner.next();

            if (CORRECT_PIN.equals(enteredPin)) {
                System.out.println("Login successful. Welcome!");
                return true;
            } else {
                attempts++;
                int remainingAttempts = 3 - attempts;

                if (remainingAttempts > 0) {
                    System.out.println("Incorrect PIN. Attempts remaining: " + remainingAttempts);
                } else {
                    System.out.println("Your account has been locked.");
                }
            }
        }

        return false;
    }

    private static void displayMenu() {
        System.out.println("\n========= ATM =========");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Show Account Status");
        System.out.println("5. Exit");
        System.out.println("=======================");
    }

    private static void checkBalance() {
        System.out.printf("Current balance: %.2f%n", balance);
    }

    private static void deposit(Scanner scanner) {
        System.out.print("Enter the amount you want to deposit: ");

        if (!scanner.hasNextDouble()) {
            System.out.println("Invalid amount.");
            scanner.next();
            return;
        }

        double amount = scanner.nextDouble();

        if (amount > 0) {
            balance += amount;
            successfulTransactions++;
            System.out.printf("Deposit successful. Updated balance: %.2f%n", balance);
        } else {
            System.out.println("Invalid amount.");
        }
    }

    private static void withdraw(Scanner scanner) {
        System.out.print("Enter the amount you want to withdraw: ");

        if (!scanner.hasNextDouble()) {
            System.out.println("Invalid amount.");
            scanner.next();
            return;
        }

        double amount = scanner.nextDouble();

        if (amount == 0) {
            System.out.println("Transaction cancelled.");
        } else if (amount < 0) {
            System.out.println("Invalid amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            successfulTransactions++;
            System.out.printf("Withdrawal successful. Updated balance: %.2f%n", balance);

            if (balance == 0) {
                System.out.println("Warning: Your account is empty.");
            }
        }
    }

    private static void showAccountStatus() {
        if (balance >= 5000) {
            System.out.println("Account status: VIP Customer");
        } else if (balance >= 1000) {
            System.out.println("Account status: Regular Customer");
        } else {
            System.out.println("Account status: Low Balance");
        }
    }
}
