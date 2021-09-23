package org.norton.peterb.atm.store;

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
    Map<BigDecimal,Integer> requestCash(BigDecimal amount);
}
