import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private double balance;
    private final ReentrantLock lock = new ReentrantLock();
    private final double overdraftLimit;

    public double getOverdraftLimit() {
        lock.lock();
        try {
            return overdraftLimit;
        } finally {
            lock.unlock();
        }
    }

    private final List<Transaction> transactionHistory = new ArrayList<>();

    public ReentrantLock getLock() {
        return lock;
    }

    public BankAccount(double initialBalance, double overdraftLimit) {
        this.balance = initialBalance;
        this.overdraftLimit = overdraftLimit;
    }

    public void deposit(double amount) {
        lock.lock();
        try {
            balance += amount;
            transactionHistory.add(new Transaction(amount, TransactionType.DEPOSIT));
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(double amount) {
        lock.lock();
        try {
            if (balance + overdraftLimit >= amount) {
                balance -= amount;
                transactionHistory.add(new Transaction(amount, TransactionType.WITHDRAWAL));
            } else {
                System.out.println("Withdrawal amount exceeds available balance and overdraft limit.");
            }
        } finally {
            lock.unlock();
        }
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public double getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }
}

class Transaction {
    private double amount;
    private TransactionType type;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Transaction(double amount, TransactionType type) {
        this.amount = amount;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transaction: " + type + ", Amount: " + amount;
    }

}

enum TransactionType {
    DEPOSIT, WITHDRAWAL
}
