package com.minibank;

import com.minibank.model.Account;
import com.minibank.service.inter.AccountOperations;

import java.math.BigDecimal;

public class ProxyAccount implements AccountOperations {
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
