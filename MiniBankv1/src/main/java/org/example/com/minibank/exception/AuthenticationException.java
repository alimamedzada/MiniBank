package org.example.com.minibank.exception;

public class AuthenticationException extends BankException {
    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
