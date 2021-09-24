package org.norton.peterb.atm.test.action;

import org.norton.peterb.atm.exception.InsufficientCashException;
import org.norton.peterb.atm.exception.InvalidDenominationException;
import org.norton.peterb.atm.store.IMachineCashStore;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class MockMachineCashStore implements IMachineCashStore {

    private final BigDecimal storeTotal;

    public MockMachineCashStore(BigDecimal storeTotal) {
        this.storeTotal = storeTotal;
    }

    @Override
    public Map<BigDecimal, Integer> requestCash(BigDecimal amount) throws InsufficientCashException, InvalidDenominationException {
        if(amount.compareTo(storeTotal) > 0) {
            throw new InsufficientCashException();
        }
        if(!amount.remainder(BigDecimal.valueOf(5L)).equals(BigDecimal.ZERO)) {
            throw new InvalidDenominationException();
        }
        return new HashMap<>();
    }

    @Override
    public void returnCash(Map<BigDecimal, Integer> cashMap) {

    }

    @Override
    public BigDecimal getStoreTotal() {
        return storeTotal;
    }
}
