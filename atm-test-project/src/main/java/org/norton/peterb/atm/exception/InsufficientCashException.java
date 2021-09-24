package org.norton.peterb.atm.exception;

public class InsufficientCashException extends ATMException {
    public InsufficientCashException() {
        super("Unable to dispense requested amount");
    }

    public InsufficientCashException(String message) {
        super(message);
    }
}
