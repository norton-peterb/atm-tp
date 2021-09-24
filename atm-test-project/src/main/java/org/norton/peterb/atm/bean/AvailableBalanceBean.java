package org.norton.peterb.atm.bean;

import java.math.BigDecimal;

/**
 * Bean to represent a response from service with balance information
 */
public class AvailableBalanceBean {
    private final BigDecimal accountBalance;
    private final BigDecimal availableBalance;

    public AvailableBalanceBean(BigDecimal accountBalance, BigDecimal availableBalance) {
        this.accountBalance = accountBalance;
        this.availableBalance = availableBalance;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }
}
