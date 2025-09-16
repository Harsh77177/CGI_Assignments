package model;

import util.Transaction;

public class Account extends BankAccount implements Transaction {

    public static final String BANK_NAME = "Example Bank";

    private double balance;

    private static int accountCount = 0;

    public Account(String accountNumber, String accountHolderName, double initialBalance) {
        super(accountNumber, accountHolderName);
        this.balance = initialBalance;
        accountCount++;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdraw amount must be positive.");
            return false;
        }
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            System.out.println("Insufficient funds.");
            return false;
        }
    }

    @Override
    public double calculateInterest() {
        double rate = 0.04;
        return balance * rate;
    }

    public final String getBankName() {
        return BANK_NAME;
    }

    public static int getAccountCount() {
        return accountCount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
