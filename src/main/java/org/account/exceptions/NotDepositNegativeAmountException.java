package org.account.exceptions;

public class NotDepositNegativeAmountException extends RuntimeException {

    public NotDepositNegativeAmountException() {
        super("It is not possible to deposit a negative amount.");
    }

}
