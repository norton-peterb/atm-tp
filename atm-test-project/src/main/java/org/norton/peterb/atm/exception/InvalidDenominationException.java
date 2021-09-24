package org.norton.peterb.atm.exception;

public class InvalidDenominationException extends ATMException {
    public InvalidDenominationException() {
        super("Invalid Amount specified in withdrawal request");
    }

    public InvalidDenominationException(String message) {
        super(message);
    }
}
