public class BankTransactionSimulator {

    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.addAccount(1, 1000);
        bank.addAccount(2, 2000);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                bank.getAccount(1).deposit(100);
                bank.getAccount(2).withdraw(10);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                bank.transfer(1, 2, 50);
                bank.transfer(2, 1, 10);
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

        System.out.println("Final balance of account 1: " + bank.getAccount(1).getBalance());
        System.out.println("Final balance of account 2: " + bank.getAccount(2).getBalance());
    }
}
