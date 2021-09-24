package org.norton.peterb.atm.model;

import java.math.BigDecimal;

public class BalanceQueryResponse extends BaseResponse {
    private BigDecimal balance;
    private BigDecimal availableBalance;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }
}
