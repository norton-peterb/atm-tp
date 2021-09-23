package org.norton.peterb.atm.bean;

import java.math.BigDecimal;

/**
 * Bean to represent a Customer Account
 */
public class CustomerAccountBean {
    private String accountNumber;
    private String pinHash;
    private BigDecimal accountBalance;
    private BigDecimal overdraftAmount;

    public CustomerAccountBean(String accountNumber,
                               String pinHash,
                               BigDecimal accountBalance,
                               BigDecimal overdraftAmount) {
        this.accountNumber = accountNumber;
        this.pinHash = pinHash;
        this.accountBalance = accountBalance;
        this.overdraftAmount = overdraftAmount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPinHash() {
        return pinHash;
    }

    public void setPinHash(String pinHash) {
        this.pinHash = pinHash;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public BigDecimal getOverdraftAmount() {
        return overdraftAmount;
    }

    public void setOverdraftAmount(BigDecimal overdraftAmount) {
        this.overdraftAmount = overdraftAmount;
    }
}
