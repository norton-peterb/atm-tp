package org.norton.peterb.atm.exception;

public class UnknownAccountException extends ATMException {
    public UnknownAccountException() {
        super("Unrecognised Account Number");
    }

    public UnknownAccountException(String message) {
        super(message);
    }
}
