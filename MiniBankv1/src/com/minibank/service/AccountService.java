package com.minibank.service;

import com.minibank.exception.BankException;
import com.minibank.exception.InvalidAccountException;
import com.minibank.model.Account;
import com.minibank.model.CheckingAccount;
import com.minibank.model.Customer;
import com.minibank.model.SavingsAccount;
import com.minibank.service.inter.AccountServiceInter;
import com.minibank.util.InputUtil;

import java.math.BigDecimal;

public class AccountService implements AccountServiceInter {
    public void createAccount(Customer customer) {
        Account account;
        System.out.println("Please select your account type: \n 1. Checking Account + \n 2. Savings Account");
        int number = InputUtil.scanner.nextInt();
        if (number == 1) {
            account = new CheckingAccount(BigDecimal.ZERO);
        } else if (number == 2) {
            account = new SavingsAccount(BigDecimal.ZERO);
        } else {
            System.out.println("The account type you entered does not exist! For this reason, Checking Account is opened by default!");
            account = new CheckingAccount(BigDecimal.ZERO);
        }

        customer.addAccount(account);
        System.out.println("New account created: " + account.getAccountId());
    }

    public Account findAccount(Customer customer, int accNum) {
        Account[] accounts = customer.getAccounts();
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null) {
                if (accounts[i].getAccountId() == accNum) {
                    return accounts[i];
                }
            }
        }
        throw new InvalidAccountException("Please, enter the account number correctly: " + accNum);
    }

    public Account getAccount(Customer customer) {
        int accountCount = customer.getAccountCount();
        Account[] accounts = customer.getAccounts();
        try {
            if (accountCount == 0) {
                throw new InvalidAccountException("Account is not found!");
            }
            if (accountCount == 1) {
                return accounts[0];
            }
            while (true) {
                System.out.println("Which account would you want to switch? ");
                for (int i = 0; i < accounts.length; i++) {
                    if (accounts[i] != null)
                        System.out.println("Account ID: " + accounts[i].getAccountId());
                }
                int num = InputUtil.scanner.nextInt();
                Account acc = findAccount(customer, num);
                if (acc != null) {
                    return acc;
                }
            }
        } catch (BankException e) {
            throw new InvalidAccountException("Account is not found!");
        }
    }

    @Override
    public void showAccounts(Customer customer) {
        Account[] accounts = customer.getAccounts();
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null)
                System.out.println(accounts[i]);
        }
    }

    public void showAllAccountsBalance(Customer customer) {
        Account[] accounts = customer.getAccounts();
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null) {
                if (accounts[i] instanceof CheckingAccount) {
                    System.out.print("CheckingAccount :  ");
                } else if (accounts[i] instanceof SavingsAccount) {
                    System.out.print("SavingsAccount  :  ");
                }
                System.out.println(accounts[i]);
            }
        }
        if (accounts.length == 0) {
            System.out.println("You have no account! Please, choose 1 for create new account!");
        }
    }
}
