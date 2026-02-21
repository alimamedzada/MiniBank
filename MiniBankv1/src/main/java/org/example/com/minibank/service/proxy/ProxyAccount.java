package org.example.com.minibank.service.proxy;

import org.example.com.minibank.entity.Accounts;
import org.example.com.minibank.service.inter.AccountOperationsInter;

import java.math.BigDecimal;

public class ProxyAccount implements AccountOperationsInter {
    private final Accounts realAccount;

    public ProxyAccount(Accounts realAccount) {
        this.realAccount = realAccount;
    }

    public void deposit(BigDecimal amount) {
        realAccount.deposit(amount);
    }

    public void withdraw(BigDecimal amount) {
        realAccount.withdraw(amount);
    }

    public void showBalance() {
        System.out.println("LOG -> showBalance()");
        realAccount.showBalance();
    }
}
