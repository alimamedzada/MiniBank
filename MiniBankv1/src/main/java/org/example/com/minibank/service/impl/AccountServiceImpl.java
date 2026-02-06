package org.example.com.minibank.service.impl;

import org.example.com.minibank.exception.BankException;
import org.example.com.minibank.exception.InvalidAccountException;
import org.example.com.minibank.model.account.Account;
import org.example.com.minibank.model.account.CheckingAccount;
import org.example.com.minibank.model.account.SavingsAccount;
import org.example.com.minibank.model.user.Customer;
import org.example.com.minibank.service.inter.AccountServiceInter;
import org.example.com.minibank.util.InputUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class AccountServiceImpl implements AccountServiceInter {

    public void createAccount(Customer customer) {
        Account account;
        int number = InputUtil.requireNumber("Please select your account type: \n 1. Checking Account + \n 2. Savings Account");
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

    public Account findAccount(Customer customer, String accNum) {
        return customer.getAccounts().stream().filter(Objects::nonNull).filter((a) -> a.getAccountId().equals(accNum)).findFirst().orElseThrow();
    }

    public Account getAccount(Customer customer) {
        int accountCount = customer.getAccounts().size();
        List<Account> accounts = customer.getAccounts();
        try {
            if (accounts.isEmpty()) {
                throw new InvalidAccountException("Account is not found!");
            }
            if (accountCount == 1) {
                return accounts.stream().findFirst().get();
            }
            while (true) {
                accounts.stream().filter(Objects::nonNull).forEach(System.out::println);
                for (int i = 0; i < accounts.size(); i++) {
                    if (accounts.get(i) != null) System.out.println("Account ID: " + accounts.get(i).getAccountId());
                }
                String num = InputUtil.requireText("Which account would you want to switch? ");
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
        List<Account> accounts = customer.getAccounts();
        accounts.stream().filter(Objects::nonNull).forEach(System.out::println);
    }

    public void showAllAccountsBalance(Customer customer) {
        List<Account> accounts = customer.getAccounts();
        for (Account account : accounts) {
            if (account != null) {
                if (account instanceof CheckingAccount) {
                    System.out.print("CheckingAccount :  ");
                } else if (account instanceof SavingsAccount) {
                    System.out.print("SavingsAccount  :  ");
                }
                account.showAllAccountsBalances();
            }
        }
        if (accounts.isEmpty()) {
            System.out.println("You have no account! Please, choose 1 for create new account!");
        }
    }
}
