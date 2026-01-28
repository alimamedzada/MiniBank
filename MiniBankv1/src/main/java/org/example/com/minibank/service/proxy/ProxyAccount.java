package org.example.com.minibank.service.proxy;

import org.example.com.minibank.model.account.Account;
import org.example.com.minibank.service.inter.AccountOperationsInter;

import java.math.BigDecimal;

public class ProxyAccount implements AccountOperationsInter {
    private final Account realAccount;

    public ProxyAccount(Account realAccount) {
        this.realAccount = realAccount;
    }

    public void deposit(BigDecimal amount) {
        System.out.println("LOG -> deposit is called: " + amount);
        realAccount.deposit(amount);
    }

    public void withdraw(BigDecimal amount) {
        System.out.println("LOG -> withdraw  is called: " + amount);
        realAccount.withdraw(amount);
    }

    public void showBalance() {
        System.out.println("LOG -> showBalance()");
        realAccount.showBalance();
    }
}
