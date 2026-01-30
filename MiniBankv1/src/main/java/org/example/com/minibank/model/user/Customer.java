package org.example.com.minibank.model.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.example.com.minibank.model.account.Account;
import org.example.com.minibank.util.IdentifierUtil;

import java.util.ArrayList;
import java.util.HashMap;


public class Customer extends Person {
    private final String customerId;
    private final HashMap<String, Account> accounts = new HashMap<>();
    private String username;
    private String password;

    private String hashPassword(String password) {
        return BCrypt.withDefaults().hashToString(10, password.toCharArray());
    }

    public Customer(String name, String surname, int age, String AzeID, String username, String password) {
        super(name, surname, age, AzeID);

        this.username = username;
        this.password = hashPassword(password);

        customerId = IdentifierUtil.generateId(10);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public HashMap<String, Account> getAccounts() {
        return accounts;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountId(), account);
    }

    @Override
    public String toString() {
        return super.toString() + " | Customer ID: " + customerId;
    }
}