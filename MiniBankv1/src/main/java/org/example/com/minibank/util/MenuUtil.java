package org.example.com.minibank.util;

import org.example.com.minibank.model.account.Account;
import org.example.com.minibank.model.user.Customer;
import org.example.com.minibank.service.proxy.ProxyAccount;
import org.example.com.minibank.service.impl.AccountServiceImpl;
import org.example.com.minibank.service.impl.CustomerServiceImpl;

import java.math.BigDecimal;

public class MenuUtil {
    public static void processMenu() {
        CustomerServiceImpl customerService = new CustomerServiceImpl();
        Customer c = customerService.createCustomer();
        AccountServiceImpl accountService = new AccountServiceImpl();
        while (true) {
            System.out.println("    1 → createAccount()\n" +
                    "    2 →  showAccounts()\n" +
                    "    3 →  deposit()\n" +
                    "    4 →  withdraw()\n" +
                    "    5 →  showBalance()\n" +
                    "    0 →  exit");
            int menu = InputUtil.requireNumber("Please, choose: ");
            if (menu < 0 || menu > 5) {
                System.out.println("Service doesn't exist! Please, choose correct service!");
            }
            switch (menu) {
                case 1 -> accountService.createAccount(c);
                case 2 -> accountService.showAccounts(c);
                case 3 -> {
                    Account acc = accountService.getAccount(c);
                    if (acc == null) {
                        System.out.println("Your account does not exist. Please create a new account!");
                        accountService.createAccount(c);
                    } else {
                        ProxyAccount proxyAccount = new ProxyAccount(acc);
                        proxyAccount.deposit(BigDecimal.valueOf(InputUtil.
                                requireDouble("please enter the amount you want to deposit: ")));
                    }
                }
                case 4 -> {
                    Account acc = accountService.getAccount(c);
                    if (acc == null) {
                        System.out.println("Your account does not exist. Please create a new account!");
                        accountService.createAccount(c);
                    } else {
                        ProxyAccount proxyAccount = new ProxyAccount(acc);
                        proxyAccount.withdraw(BigDecimal.valueOf(InputUtil.
                                requireDouble("please enter the amount you want to withdraw: ")));
                    }
                }
                case 5 -> accountService.showAllAccountsBalance(c);
                case 0 -> System.exit(0);
            }
        }
    }
}