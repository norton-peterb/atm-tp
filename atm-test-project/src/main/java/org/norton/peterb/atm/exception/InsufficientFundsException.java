package org.norton.peterb.atm.exception;

import java.math.BigDecimal;

public class InsufficientFundsException extends ATMException {

    public InsufficientFundsException() {
        super("Insufficient Funds in Account");
    }

    public InsufficientFundsException(String message) {
        super(message);
    }
}
