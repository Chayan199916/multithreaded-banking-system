# Multithreaded Banking System

This project implements a multithreaded banking system in Java. It demonstrates the use of multithreading and concurrency control mechanisms to manage bank accounts and perform transactions in a thread-safe manner.

## Features

- **BankAccount Class**: Provides methods for deposit and withdrawal operations with thread safety ensured using locks.
- **Bank Class**: Manages multiple bank accounts and facilitates transfers between accounts while maintaining data consistency.
- **Transaction Simulation**: Simulates concurrent transactions using multiple threads to demonstrate the system's concurrency handling capabilities.
- **Transaction History**: Tracks and stores transaction history for each account, including details such as transaction type and amount.
- **Overdraft Protection**: Implements overdraft protection to prevent accounts from going below a specified negative balance limit.

## Implementation Details

- **Synchronization**: The `BankAccount` class uses locks to synchronize deposit and withdrawal operations, ensuring data consistency in a multithreaded environment.
- **Transfer Mechanism**: Transfers between accounts in the `Bank` class are implemented with proper locking to prevent race conditions and maintain transaction atomicity.
- **Concurrency Control**: ConcurrentHashMap is used to manage bank accounts, and fine-grained locking ensures thread safety during transactions.
- **Transaction History**: Each `BankAccount` instance maintains a transaction history, recording details of every deposit and withdrawal operation. The `Transaction` class provides a custom string representation of transaction details for better readability.

- **Overdraft Protection**: The `BankAccount` class includes an overdraft limit parameter to prevent account balances from going below a specified negative limit. Withdrawal operations are rejected if they would cause the account balance to exceed the available funds plus the overdraft limit.

## Usage

To run the simulation, execute the `BankTransactionSimulator` class. Adjust the number of threads and transaction parameters as needed for testing different scenarios.

```bash
javac BankTransactionSimulator.java
java BankTransactionSimulator
```
