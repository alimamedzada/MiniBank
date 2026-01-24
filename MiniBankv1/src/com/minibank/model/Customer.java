package com.minibank.model;

import com.minibank.Person;
import com.minibank.exception.BankException;
import com.minibank.exception.InvalidAccountException;
import com.minibank.util.InputUtil;


public class Customer extends Person {
    public static int nextID = 1000;
    private int customerId = 0;
    private final Account[] accounts = new Account[10];
    private int accountCount = 0;

    public Customer(String name, String surname, int age, String tcID) {
        super(name, surname, age, tcID);
        customerId = ++nextID;
    }

    public static int getNextID() {
        return nextID;
    }

    public static void setNextID(int nextID) {
        Customer.nextID = nextID;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Account[] getAccounts() {
        return accounts;
    }

    public int getAccountCount() {
        return accountCount;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void addAccount(Account account) {
        if (accountCount >= accounts.length) {
            throw new RuntimeException("You have exceeded your account limit!"); // Veya kendi Exception'in
        }
        accounts[accountCount++] = account;
        System.out.println("The account has been added to the customer: " + account.getAccountId());
    }

    @Override
    public String toString() {
        return super.toString() + " | Account ID: " + customerId + " | Account count: " + accountCount;
    }
}
