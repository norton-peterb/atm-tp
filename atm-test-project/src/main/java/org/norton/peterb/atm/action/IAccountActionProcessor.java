package org.norton.peterb.atm.action;

import org.norton.peterb.atm.exception.*;
import org.norton.peterb.atm.model.BalanceQueryResponse;
import org.norton.peterb.atm.model.BaseAccountRequest;
import org.norton.peterb.atm.model.WithdrawFundsRequest;

/**
 * Processor for Business Logic invoked by Service
 */
public interface IAccountActionProcessor {

    /**
     * Perform a withdrawal with the enclosed request details
     * @param withdrawFundsRequest Withdraw Funds Request data (account,pin and amount)
     * @return Response including the current and available balance
     * @throws ATMException
     */
    BalanceQueryResponse performWithdraw(WithdrawFundsRequest withdrawFundsRequest)
            throws ATMException;

    /**
     * Check the balance for an account
     * @param baseAccountRequest Basic Account Request (account,pin)
     * @return Response including current and available balance
     * @throws ATMException
     */
    BalanceQueryResponse checkBalance(BaseAccountRequest baseAccountRequest)
            throws ATMException;
}
