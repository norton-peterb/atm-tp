package org.norton.peterb.atm.exception;

import java.math.BigDecimal;

public class InsufficientCashException extends Exception {
    private final BigDecimal requested;
    private final BigDecimal available;

    public InsufficientCashException(BigDecimal requested, BigDecimal available) {
        this.requested = requested;
        this.available = available;
    }

    public InsufficientCashException(String message, BigDecimal requested, BigDecimal available) {
        super(message);
        this.requested = requested;
        this.available = available;
    }

    public BigDecimal getRequested() {
        return requested;
    }

    public BigDecimal getAvailable() {
        return available;
    }
}
