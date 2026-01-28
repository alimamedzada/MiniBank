package org.example.com.minibank.exception;

public class WithdrawalLimitExceededException extends BankException {
    public WithdrawalLimitExceededException(String message) {
        super(message);
    }

    public WithdrawalLimitExceededException(String message, Throwable cause) {
        super(message, cause);
    }
}
