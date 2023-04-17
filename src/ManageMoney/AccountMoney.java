package ManageMoney;

import java.util.Scanner;

public class AccountMoney {
    private static Thread thread;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Account account = new Account();

        Thread thread = new Thread(() -> {
            while (true) {
                String command = input.nextLine();
                if (command.startsWith("NT ")) {
                    int amount = Integer.parseInt(command.substring(3));
                    account.deposit(amount);
                } else if (command.startsWith("RT ")) {
                    int amount = Integer.parseInt(command.substring(3));
                    account.withdraw(amount);
                } else if (command.equals("TT")) {
                    account.printBalance();
                } else if (command.equals("KT")) {
                    System.exit(0);
                } else {
                    System.out.println("Error");
                }
            }
        });

        Thread printBalanceThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                account.printBalance();
            }
        });

        thread.start();
        printBalanceThread.start();

        try {
            thread.join();
            printBalanceThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Account {
    private int balance = 0;

    public Account() {
        Scanner input = new Scanner(System.in);
        System.out.println("BD ");
        int initialBalance = input.nextInt();
        deposit(initialBalance);
    }

    public synchronized void deposit(int amount) {
        balance += amount;
    }

    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Không đủ tiền trong tài khoản");
        }
    }

    public synchronized void printBalance() {
        System.out.println("Tổng tiền trong tài khoản: " + balance);
    }
}


