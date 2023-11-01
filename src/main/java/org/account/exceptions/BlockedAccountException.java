package org.account.exceptions;

public class BlockedAccountException extends RuntimeException {

    public BlockedAccountException(String message) {
        super(message);
    }

}
