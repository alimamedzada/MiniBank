package org.example.com.minibank.service.impl;

import org.example.com.minibank.exception.BankException;
import org.example.com.minibank.exception.InvalidAccountException;
import org.example.com.minibank.entity.Accounts;
import org.example.com.minibank.entity.CheckingAccount;
import org.example.com.minibank.entity.SavingsAccount;
import org.example.com.minibank.entity.Customers;
import org.example.com.minibank.service.inter.AccountServiceInter;
import org.example.com.minibank.util.InputUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import org.example.com.minibank.dao.impl.CustomerDaoImpl;
import org.example.com.minibank.dao.inter.CustomerDaoInter;

public class AccountServiceImpl implements AccountServiceInter {

    CustomerDaoInter customerDao = new CustomerDaoImpl();

    public void createAccount(Customers customer) {
        Accounts account;
        int number = InputUtil.requireNumber("Please select your account type: \n 1. Checking Account + \n 2. Savings Account");
        if (number == 1) {
            account = new CheckingAccount(BigDecimal.ZERO);
        } else if (number == 2) {
            account = new SavingsAccount(BigDecimal.ZERO);
        } else {
            System.out.println("The account type you entered does not exist! For this reason, Checking Account is opened by default!");
            account = new CheckingAccount(BigDecimal.ZERO);
        }

        customerDao.updateCustomer(customer);
        customer.addAccount(account);
        System.out.println("New account created: " + account.getId());
    }

    public Accounts findAccount(Customers customer, String accNum) {
        return customer.getAccounts().stream().filter(Objects::nonNull).filter((a) -> a.getId().equals(accNum)).findFirst().orElseThrow();
    }

    public Accounts getAccount(Customers customer) {
        int accountCount = customer.getAccounts().size();
        List<Accounts> accounts = customer.getAccounts();
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
                    if (accounts.get(i) != null) {
                        System.out.println("Account ID: " + accounts.get(i).getId());
                    }
                }
                String num = InputUtil.requireText("Which account would you want to switch? ");
                Accounts acc = findAccount(customer, num);
                if (acc != null) {
                    return acc;
                }
            }
        } catch (BankException e) {
            throw new InvalidAccountException("Account is not found!");
        }
    }

    @Override
    public void showAccounts(Customers customer) {
        List<Accounts> accounts = customer.getAccounts();
        accounts.stream().filter(Objects::nonNull).forEach(System.out::println);
    }

    public void showAllAccountsBalance(Customers customer) {
        List<Accounts> accounts = customer.getAccounts();
        for (Accounts account : accounts) {
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
