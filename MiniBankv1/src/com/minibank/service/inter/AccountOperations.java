package com.minibank.service.inter;

import java.math.BigDecimal;

public interface AccountOperations {
    void deposit(BigDecimal amount);

    void withdraw(BigDecimal amount);

    void showBalance();
}
