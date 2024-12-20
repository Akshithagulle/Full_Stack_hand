
  package pack.mod9.collections;

import java.util.*;
import java.util.stream.Collectors;

class BankAccount {
    private int accountNumber;
    private String accountHolder;
    private double balance;

    public BankAccount(int accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return accountNumber == that.accountNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }

    @Override
    public String toString() {
        return "AccountNumber: " + accountNumber + ", Holder: " + accountHolder + ", Balance: " + balance;
    }
}

public class AccountOperations {
    private Set<BankAccount> accounts;

    public AccountOperations() {
        accounts = new HashSet<>();
    }

    public boolean addAccount(BankAccount account) {
        return accounts.add(account);
    }

    public boolean deposit(int accountNumber, double amount) {
        return accounts.stream()
                .filter(a -> a.getAccountNumber() == accountNumber)
                .findFirst()
                .map(a -> {
                    a.deposit(amount);
                    return true;
                }).orElse(false);
    }

    public boolean withdraw(int accountNumber, double amount) {
        return accounts.stream()
                .filter(a -> a.getAccountNumber() == accountNumber)
                .findFirst()
                .map(a -> a.withdraw(amount))
                .orElse(false);
    }

    public void checkBalance(int accountNumber) {
        accounts.stream()
                .filter(a -> a.getAccountNumber() == accountNumber)
                .findFirst()
                .ifPresentOrElse(
                        a -> System.out.println("Balance: " + a.getBalance()),
                        () -> System.out.println("Account not found."));
    }

    public void removeInactiveAccounts(double threshold) {
        accounts.removeIf(a -> a.getBalance() <= threshold);
    }

    public void displayAllAccounts() {
        accounts.forEach(System.out::println);
    }

    public void displayAccountsWithBalanceGreaterThan(double amount) {
        accounts.stream()
                .filter(account -> account.getBalance() > amount)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        AccountOperations operations = new AccountOperations();

        BankAccount account1 = new BankAccount(101, "Alice", 5000);
        BankAccount account2 = new BankAccount(102, "Bob", 3000);
        BankAccount account3 = new BankAccount(103, "Charlie", 10000);

        operations.addAccount(account1);
        operations.addAccount(account2);
        operations.addAccount(account3);

        System.out.println("All Accounts:");
        operations.displayAllAccounts();

        System.out.println("\nDepositing 2000 to Account 101:");
        operations.deposit(101, 2000);
        operations.checkBalance(101);

        System.out.println("\nWithdrawing 1500 from Account 102:");
        operations.withdraw(102, 1500);
        operations.checkBalance(102);

        System.out.println("\nAccounts with balance greater than 4000:");
        operations.displayAccountsWithBalanceGreaterThan(4000);

        System.out.println("\nRemoving inactive accounts (balance <= 3000):");
        operations.removeInactiveAccounts(3000);
        operations.displayAllAccounts();
    }
}
