package org.example.com.minibank.model.account;

import java.math.BigDecimal;

public class CheckingAccount extends Account {
    public CheckingAccount(BigDecimal balance) {
        super(balance);
    }


    @Override
    public void withdraw(BigDecimal ammount) {
        super.withdraw(ammount);
    }

    @Override
    public void deposit(BigDecimal ammount) {
        super.deposit(ammount);
    }
}
