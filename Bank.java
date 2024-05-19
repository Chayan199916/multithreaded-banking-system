import java.util.concurrent.ConcurrentHashMap;

public class Bank {
    private final ConcurrentHashMap<Integer, BankAccount> accounts = new ConcurrentHashMap<>();

    public void addAccount(int accountId, double initialBalance, double overdraftLimit) {
        accounts.put(accountId, new BankAccount(initialBalance, overdraftLimit));
    }

    public BankAccount getAccount(int accountId) {
        return accounts.get(accountId);
    }

    public void transfer(int fromAccountId, int toAccountId, double amount) {
        BankAccount fromAccount = accounts.get(fromAccountId);
        BankAccount toAccount = accounts.get(toAccountId);

        fromAccount.getLock().lock();
        toAccount.getLock().lock();
        try {
            if (fromAccount.getBalance() + fromAccount.getOverdraftLimit() >= amount) {
                System.out.println("Transferring " + amount + " from " + fromAccountId + " to " + toAccountId);
                fromAccount.withdraw(amount);
                toAccount.deposit(amount);
            } else {
                System.out.println("Insufficient funds for transfer");
            }
        } finally {
            fromAccount.getLock().unlock();
            toAccount.getLock().unlock();
        }
    }
}
