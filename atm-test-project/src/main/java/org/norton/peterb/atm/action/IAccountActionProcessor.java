package org.norton.peterb.atm.action;

import org.norton.peterb.atm.exception.*;
import org.norton.peterb.atm.model.BalanceQueryResponse;
import org.norton.peterb.atm.model.BaseAccountRequest;
import org.norton.peterb.atm.model.WithdrawFundsRequest;

public interface IAccountActionProcessor {

    BalanceQueryResponse performWithdraw(WithdrawFundsRequest withdrawFundsRequest)
            throws ATMException;

    BalanceQueryResponse checkBalance(BaseAccountRequest baseAccountRequest)
            throws ATMException;
}
