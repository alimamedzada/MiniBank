package org.example.com.minibank.service.inter;

import java.math.BigDecimal;

public interface AccountOperationsInter {
    void deposit(BigDecimal amount);

    void withdraw(BigDecimal amount);

    void showBalance();
}
