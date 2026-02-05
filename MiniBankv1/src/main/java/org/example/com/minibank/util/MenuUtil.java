package org.example.com.minibank.util;

import org.example.com.minibank.model.account.Account;
import org.example.com.minibank.model.user.Customer;
import org.example.com.minibank.service.impl.AccountServiceImpl;
import org.example.com.minibank.service.impl.AuthServiceImpl;
import org.example.com.minibank.service.impl.CustomerServiceImpl;
import org.example.com.minibank.service.proxy.ProxyAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class MenuUtil {
    private static final AuthServiceImpl authService = new AuthServiceImpl();
    private static final CustomerServiceImpl customerService = new CustomerServiceImpl();
    private static final AccountServiceImpl accountService = new AccountServiceImpl();
    private static final Logger log = LoggerFactory.getLogger(MenuUtil.class);

    public static void showEntryMenu() {
        while (true) {
            System.out.println("    1 →  Login\n" +
                    "    2 →  Register");
            int entryMenu = InputUtil.requireNumber("Please, choose: ");
            switch (entryMenu) {
                case 1 -> {
                    String username = InputUtil.requireUsername();
                    String password = InputUtil.requirePassword();
                    Customer loginCustomer = authService.login(username, password);
                    if (loginCustomer != null) {
                        processMenu(loginCustomer);
                    }
                }
                case 2 -> {
                    Customer registerCustomer = authService.register(customerService.createCustomer());
                    processMenu(registerCustomer);
                }
                case 0 -> System.exit(0);
            }

        }
    }

    public static void processMenu(Customer customer) {
        while (true) {
            System.out.println("    1 →  createAccount()\n" +
                    "    2 →  showAccounts()\n" +
                    "    3 →  deposit()\n" +
                    "    4 →  withdraw()\n" +
                    "    5 →  showBalance()\n" +
                    "    6 →  Logout\n" +
                    "    0 →  exit");
            int menu = InputUtil.requireNumber("Please, choose: ");
            if (menu < 0 || menu > 6) {
                System.out.println("Service doesn't exist! Please, choose correct service!");
            }
            switch (menu) {
                case 1 -> accountService.createAccount(customer);
                case 2 -> accountService.showAccounts(customer);
                case 3 -> {
                    Account acc = accountService.getAccount(customer);
                    if (acc == null) {
                        System.out.println("Your account does not exist. Please create a new account!");
                        accountService.createAccount(customer);
                    } else {
                        ProxyAccount proxyAccount = new ProxyAccount(acc);
                        proxyAccount.deposit(BigDecimal.valueOf(InputUtil.
                                requireDouble("please enter the amount you want to deposit: ")));
                    }
                }
                case 4 -> {
                    accountService.showAccounts(customer);
                    Account acc = accountService.getAccount(customer);
                    if (acc == null) {
                        System.out.println("Your account does not exist. Please create a new account!");
                        accountService.createAccount(customer);
                    } else {
                        ProxyAccount proxyAccount = new ProxyAccount(acc);
                        proxyAccount.withdraw(BigDecimal.valueOf(InputUtil.
                                requireDouble("please enter the amount you want to withdraw: ")));
                    }
                }
                case 5 -> accountService.showAllAccountsBalance(customer);
                case 6 -> {
                    log.info("logged out", customer.getUsername());
                    return;
                }
                case 0 -> System.exit(0);
            }
        }
    }
}