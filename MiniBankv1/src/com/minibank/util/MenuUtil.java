package com.minibank.util;

import com.minibank.model.Account;
import com.minibank.model.Customer;
import com.minibank.ProxyAccount;
import com.minibank.service.AccountService;
import com.minibank.service.CustomerService;

import java.math.BigDecimal;

public class MenuUtil {
    public static void processMenu() {
        CustomerService customerService = new CustomerService();
        Customer c = customerService.createCustomer();
        AccountService accountService = new AccountService();
        while (true) {
            System.out.println("    1 → createAccount()\n" +
                    "    2 →  showAccounts()\n" +
                    "    3 →  deposit()\n" +
                    "    4 →  withdraw()\n" +
                    "    5 →  showBalance()\n" +
                    "    0 →  exit");
            System.out.println("Please, choose: ");
            int menu = InputUtil.scanner.nextInt();
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
                        System.out.println("please enter the amount you want to deposit: ");

                        proxyAccount.deposit(BigDecimal.valueOf(InputUtil.scanner.nextDouble()));
                    }
                }
                case 4 -> {
                    Account acc = accountService.getAccount(c);
                    if (acc == null) {
                        System.out.println("Your account does not exist. Please create a new account!");
                        accountService.createAccount(c);
                    } else {
                        ProxyAccount proxyAccount = new ProxyAccount(acc);
                        System.out.println("please enter the amount you want to withdraw: ");
                        proxyAccount.withdraw(BigDecimal.valueOf(InputUtil.scanner.nextDouble()));
                    }
                }
                case 5 -> accountService.showAllAccountsBalance(c);
                case 0 -> System.exit(0);
            }
        }
    }
}