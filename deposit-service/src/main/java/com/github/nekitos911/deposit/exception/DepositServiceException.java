package com.github.nekitos911.deposit.exception;

public class DepositServiceException extends RuntimeException {
    public DepositServiceException(String message) {
        super(message);
    }

    public DepositServiceException(Throwable cause) {
        super(cause);
    }
}
