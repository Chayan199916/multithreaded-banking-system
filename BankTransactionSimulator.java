public class BankTransactionSimulator {

    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.addAccount(1, 1000, 500); // Account 1 with initial balance 1000 and overdraft limit 500
        bank.addAccount(2, 2000, 500); // Account 2 with initial balance 2000 and overdraft limit 500

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                bank.getAccount(1).deposit(100); // Deposit 100 to Account 1
                bank.getAccount(2).withdraw(10); // Withdraw 10 from Account 2
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                bank.transfer(1, 2, 50); // Transfer 50 from Account 1 to Account 2
                bank.transfer(2, 1, 10); // Transfer 10 from Account 2 to Account 1
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print transaction history for each account
        System.out.println("Transaction history for account 1:");
        for (Transaction transaction : bank.getAccount(1).getTransactionHistory()) {
            System.out.println(transaction);
        }
        System.out.println("Transaction history for account 2:");
        for (Transaction transaction : bank.getAccount(2).getTransactionHistory()) {
            System.out.println(transaction);
        }

        System.out.println("Final balance of account 1: " + bank.getAccount(1).getBalance());
        System.out.println("Final balance of account 2: " + bank.getAccount(2).getBalance());
    }
}
