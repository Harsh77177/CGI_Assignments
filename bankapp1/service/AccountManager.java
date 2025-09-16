package service;

import model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountManager {
    private final List<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Optional<Account> findAccount(String accountNumber) {
        return accounts.stream()
                .filter(a -> a.getAccountNumber().equals(accountNumber))
                .findFirst();
    }

    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts to display.");
            return;
        }
        accounts.forEach(System.out::println);
    }

    public boolean depositToAccount(String accountNumber, double amount) {
        Optional<Account> opt = findAccount(accountNumber);
        if (opt.isPresent()) {
            opt.get().deposit(amount);
            return true;
        }
        System.out.println("Account not found: " + accountNumber);
        return false;
    }

    public boolean withdrawFromAccount(String accountNumber, double amount) {
        Optional<Account> opt = findAccount(accountNumber);
        if (opt.isPresent()) {
            return opt.get().withdraw(amount);
        }
        System.out.println("Account not found: " + accountNumber);
        return false;
    }

    public double calculateInterestForAccount(String accountNumber) {
        Optional<Account> opt = findAccount(accountNumber);
        if (opt.isPresent()) {
            return opt.get().calculateInterest();
        }
        System.out.println("Account not found: " + accountNumber);
        return 0.0;
    }
}
