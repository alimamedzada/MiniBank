package com.minibank.model;

import com.minibank.exception.InsufficientBalanceException;
import com.minibank.exception.InvalidAmountException;
import com.minibank.service.inter.AccountOperations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Account implements AccountOperations {
    private static int nextAccountID = 9000;
    private final int accountId;
    private BigDecimal balance;
    private final String createDate;

    public Account(BigDecimal balance) {
        this.accountId = ++nextAccountID;
        this.balance = balance;
        this.createDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public int getAccountId() {
        return accountId;
    }

    @Override
    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.valueOf(0)) > 0) {
            balance = balance.add(amount);

        } else if (amount.compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new InvalidAmountException("The amount cannot be negative!");
        }
        System.out.println(amount + " AZN has been successfully deposited into your account.");
        showBalance();
    }

    @Override
    public void withdraw(BigDecimal amount) {
        if ((amount.compareTo(balance) < 0)) {
            throw new InvalidAmountException("The amount cannot be negative!");
        }
        if (amount.compareTo(balance) > 0) {
            throw new InsufficientBalanceException("You don't have enough balance in your account!" + balance);
        }
        balance = balance.subtract(amount);
        System.out.println(amount + " AZN has been withdrawn from your account..");
        showBalance();
    }

    @Override
    public void showBalance() {
        System.out.println("Balance: " + balance);
    }


    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account ID: " + accountId + " | Balance: " + balance + " AZN" + " | Registration date: " + createDate;
    }
}
