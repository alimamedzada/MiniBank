package org.example.com.minibank.model.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.example.com.minibank.model.account.Account;
import org.example.com.minibank.util.IdentifierUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Customer extends Person {
    private final List<Account> accounts = new ArrayList<>();
    private final String customerId;
    private String username;
    private String password;

    private String hashPassword(String password) {
        return BCrypt.withDefaults().hashToString(10, password.toCharArray());
    }

    private Customer(String name, String surname, int age, String AzeID, String username, String password, String customerId, boolean isNew) {
        super(name, surname, age, AzeID);
        this.username = username;
        this.customerId = customerId;
        this.password = isNew ? hashPassword(password) : password;
    }

    private Customer(Account account, String name, String surname, int age, String AzeID, String username, String password, String customerId, boolean isNew) {
        super(name, surname, age, AzeID);
        this.username = username;
        this.customerId = customerId;
        this.password = isNew ? hashPassword(password) : password;
        if (account != null) {
            this.accounts.add(account);
        }
    }

    public static Customer createNewCustomer(String name, String surname, int age, String AzeID, String username, String password) {
        String generatedId = IdentifierUtil.generateId(10);
        return new Customer(name, surname, age, AzeID, username, password, generatedId, true);
    }

    public static Customer mapFromDatabase(Account account, String name, String surname, int age, String AzeID, String username, String password, String customerId) {
        return new Customer(account, name, surname, age, AzeID, username, password, customerId, false);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    @Override
    public String toString() {
        return super.toString() + " | Customer ID: " + customerId + "| Username: " + username + " | Accounts: " + accounts;
    }
}