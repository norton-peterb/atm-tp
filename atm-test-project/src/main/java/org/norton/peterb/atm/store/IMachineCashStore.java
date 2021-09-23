package org.norton.peterb.atm.store;

import org.norton.peterb.atm.exception.InsufficientCashException;
import org.norton.peterb.atm.exception.InvalidDenominationException;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Store for contents of the ATM including
 * internal mapping for Notes
 */
public interface IMachineCashStore {
    /**
     * Request Cash from the Machine Cash Store
     * @param amount Amount to request
     * @return Denominations Dispensed
     */
    Map<BigDecimal,Integer> requestCash(BigDecimal amount) throws InsufficientCashException,
            InvalidDenominationException;

    /**
     * Return Cash to allocation for failed withdrawal
     * @param cashMap Map Containing Denominations
     */
    void returnCash(Map<BigDecimal,Integer> cashMap);

    /**
     * Get the Total amount of cash in the store
     * @return Total Cash stored
     */
    BigDecimal getStoreTotal();
}
