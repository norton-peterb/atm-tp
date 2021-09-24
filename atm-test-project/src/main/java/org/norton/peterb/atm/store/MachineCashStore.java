package org.norton.peterb.atm.store;

import org.norton.peterb.atm.exception.InsufficientCashException;
import org.norton.peterb.atm.exception.InvalidDenominationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class MachineCashStore implements IMachineCashStore {

    private static final Logger LOGGER = LoggerFactory.getLogger(MachineCashStore.class);

    private final Map<BigDecimal, Integer> cashStore = new HashMap<>();
    private final List<BigDecimal> denominationList = new ArrayList<>();

    public MachineCashStore(Map<BigDecimal, Integer> cashStore) {
        this.cashStore.putAll(cashStore);
        denominationList.addAll(this.cashStore.keySet());
        denominationList.sort(Collections.reverseOrder());
    }

    // Compute the Allocation from the cash store
    private Map<BigDecimal,Integer> computeAllocation(BigDecimal amount) {
        Map<BigDecimal, Integer> cashAllocation = new HashMap<>();
        BigDecimal remainingAmount = amount;
        // Iterate through denominations largest first for smallest amount of notes
        for (BigDecimal denomination : denominationList) {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("Allocating Denomination of {}€",denomination);
                LOGGER.debug("Remaining Request to allocate is {}€",remainingAmount);
            }
            // Determine number available from those in the store
            BigDecimal notesAvailable = remainingAmount.divide(denomination, RoundingMode.DOWN);
            // If notes from this denomination can be calculated perform operation
            if (!notesAvailable.equals(BigDecimal.ZERO)) {
                // Insert the denomination to the cash allocation
                cashAllocation.put(denomination, notesAvailable.intValue());
                // Subtract from the amount remaining for next denomination iteration
                remainingAmount = remainingAmount.subtract(denomination.multiply(notesAvailable));
                // Remove the allocated notes from the cash store
                cashStore.put(denomination, cashStore.get(denomination) - notesAvailable.intValue());
            }
        }
        return cashAllocation;
    }

    // Function to check that the cash store can provide exact amount against
    // the denominations
    private boolean checkDenominationValidity(BigDecimal amount) {
        for(BigDecimal denomination : cashStore.keySet()) {
            // If the amount is divisible against at least one denomination then it is valid
            if(amount.remainder(denomination).equals(BigDecimal.ZERO)) {
                return true;
            }
        }
        // Will only reach here if no denomination is valid
        return false;
    }

    @Override
    public Map<BigDecimal, Integer> requestCash(BigDecimal amount) throws InsufficientCashException,
            InvalidDenominationException  {
        // Check if the amount requested exceeds the store
        if(amount.compareTo(getStoreTotal()) > 0) {
            throw new InsufficientCashException();
        }
        if(!checkDenominationValidity(amount)) {
            throw new InvalidDenominationException("Unable to dispense value of " + amount);
        }
        return computeAllocation(amount);
    }

    @Override
    public BigDecimal getStoreTotal() {
        // Zero Total
        BigDecimal total = BigDecimal.ZERO;
        // Iterate through denominations in cash store adding each one
        for(Map.Entry<BigDecimal,Integer> cashDenomination : cashStore.entrySet()) {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("Store Denomination {}€ has {} note(s)",
                        cashDenomination.getKey(),cashDenomination.getValue());
            }
            total = total.add(
                    cashDenomination.getKey().multiply(BigDecimal.valueOf(cashDenomination.getValue()))
            );
        }
        return total;
    }

    @Override
    public void returnCash(Map<BigDecimal, Integer> cashMap) {
        cashMap.forEach(
                (k,v) -> cashStore.merge(k, v, Integer::sum)
        );
    }
}
