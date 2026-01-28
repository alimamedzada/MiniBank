package org.example.com.minibank.model.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.example.com.minibank.model.account.Account;
import org.example.com.minibank.util.IdentifierUtil;

import java.util.ArrayList;


public class Customer extends Person {
    private final String customerId;
    private final ArrayList<Account> accounts = new ArrayList<>();
    private final int accountCount = 0;
    private String username;
    private String password;

    private String hashPassword(String password) {
        return BCrypt.withDefaults().hashToString(10, this.password.toCharArray());
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

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public int getAccountCount() {
        return accountCount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    @Override
    public String toString() {
        return super.toString() + " | Customer ID: " + customerId + " | Account count: " + accountCount;
    }
}