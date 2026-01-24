package com.minibank.model;

import com.minibank.exception.WithdrawalLimitExceededException;

import java.math.BigDecimal;

public class SavingsAccount extends Account {
    public SavingsAccount(BigDecimal balance) {
        super(balance);
    }

    public void addInterest() {
        BigDecimal countInterst = super.getBalance().multiply(BigDecimal.valueOf(0.05));
        super.deposit(countInterst);
        System.out.println((countInterst) + " AZN has been successfully deposited into your account.");
    }

    @Override
    public void deposit(BigDecimal amount) {
        addInterest();
        super.deposit(amount);
    }

    @Override
    public void withdraw(BigDecimal ammount) {
        BigDecimal countInterst = super.getBalance().multiply(BigDecimal.valueOf(0.10));
        if (ammount.compareTo(BigDecimal.valueOf(500)) > 0
                || ammount.compareTo(countInterst) > 0) {
            throw new WithdrawalLimitExceededException("The withdrawn amount cannot exceed 500 AZN or 10% of your balance (" + countInterst + " AZN)!");
        }
        super.withdraw(ammount);
    }
}
