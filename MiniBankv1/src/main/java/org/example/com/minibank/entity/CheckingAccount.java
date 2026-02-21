package org.example.com.minibank.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("Checking")
public class CheckingAccount extends Accounts {

    public CheckingAccount() {
    }

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
