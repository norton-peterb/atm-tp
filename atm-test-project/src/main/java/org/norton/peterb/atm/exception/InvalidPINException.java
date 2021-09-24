package org.norton.peterb.atm.exception;

public class InvalidPINException extends ATMException {

    public InvalidPINException() {
        super("Invalid PIN Number entered");
    }

    public InvalidPINException(String message) {
        super(message);
    }
}
