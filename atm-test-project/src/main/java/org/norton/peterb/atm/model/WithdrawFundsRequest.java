package org.norton.peterb.atm.model;

import java.math.BigDecimal;

public class WithdrawFundsRequest extends BaseAccountRequest {
    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
