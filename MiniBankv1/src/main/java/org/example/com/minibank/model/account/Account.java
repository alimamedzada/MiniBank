package org.example.com.minibank.model.account;

import org.example.com.minibank.exception.InsufficientBalanceException;
import org.example.com.minibank.exception.InvalidAmountException;
import org.example.com.minibank.service.inter.AccountOperationsInter;
import org.example.com.minibank.util.IdentifierUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Account implements AccountOperationsInter {
    private final String accountId;
    private BigDecimal balance;
    private final String createDate;

    public Account(BigDecimal balance) {
        this.accountId = IdentifierUtil.generateAzerbaijanIBANId();
        this.balance = balance;
        this.createDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getAccountId() {
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

    public void showAllAccountsBalances() {

        System.out.println("Account ID: " + accountId + "\033[3m" + "\u001B[1m" +
                " | Balance: " + balance + " AZN" + "\u001B[0m" + "\033[0m");
    }


    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account ID: " + accountId + " | Balance: " + balance + " AZN" + " | Registration date: " + createDate;
    }
}
