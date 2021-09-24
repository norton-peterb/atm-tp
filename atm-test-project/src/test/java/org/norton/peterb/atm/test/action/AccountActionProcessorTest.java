package org.norton.peterb.atm.test.action;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.norton.peterb.atm.action.AccountActionProcessor;
import org.norton.peterb.atm.action.IAccountActionProcessor;
import org.norton.peterb.atm.exception.*;
import org.norton.peterb.atm.model.BalanceQueryResponse;
import org.norton.peterb.atm.model.BaseAccountRequest;
import org.norton.peterb.atm.model.WithdrawFundsRequest;
import org.norton.peterb.atm.store.IAccountStore;
import org.norton.peterb.atm.store.IMachineCashStore;

import java.math.BigDecimal;

public class AccountActionProcessorTest {

    IAccountActionProcessor getAccountActionProcessor() {
        IAccountStore accountStore = new MockAccountStore();
        IMachineCashStore machineCashStore = new MockMachineCashStore(BigDecimal.valueOf(500L));
        return new AccountActionProcessor(accountStore,machineCashStore);
    }

    IAccountActionProcessor getAccountActionProcessorBigCash() {
        IAccountStore accountStore = new MockAccountStore();
        IMachineCashStore machineCashStore = new MockMachineCashStore(BigDecimal.valueOf(2000L));
        return new AccountActionProcessor(accountStore,machineCashStore);
    }

    @Test
    void testAccountBalance() throws ATMException {
        BaseAccountRequest accountRequest = new BaseAccountRequest();
        accountRequest.setAccountNumber("1234");
        accountRequest.setPin("1234");
        BalanceQueryResponse balanceQueryResponse =
                getAccountActionProcessor().checkBalance(accountRequest);
        Assertions.assertEquals(BigDecimal.valueOf(1200L),balanceQueryResponse.getBalance());
        Assertions.assertEquals(BigDecimal.valueOf(1400L),balanceQueryResponse.getAvailableBalance());
    }

    @Test
    void testAccountBalanceWrongPIN() {
        BaseAccountRequest accountRequest = new BaseAccountRequest();
        accountRequest.setAccountNumber("1234");
        accountRequest.setPin("2800");
        Assertions.assertThrows(InvalidPINException.class,() ->
            getAccountActionProcessor().checkBalance(accountRequest));
    }

    @Test
    void testAccountBalanceWrongAccountNumber() {
        BaseAccountRequest accountRequest = new BaseAccountRequest();
        accountRequest.setAccountNumber("1235");
        accountRequest.setPin("1234");
        Assertions.assertThrows(UnknownAccountException.class,() ->
                getAccountActionProcessor().checkBalance(accountRequest));
    }

    @Test
    void testAccountWithdraw() throws ATMException {
        WithdrawFundsRequest withdrawFundsRequest = new WithdrawFundsRequest();
        withdrawFundsRequest.setAccountNumber("1234");
        withdrawFundsRequest.setPin("1234");
        withdrawFundsRequest.setAmount(BigDecimal.valueOf(100L));
        BalanceQueryResponse balanceQueryResponse = getAccountActionProcessor().performWithdraw(
                withdrawFundsRequest
        );
        Assertions.assertEquals(BigDecimal.valueOf(1100L),balanceQueryResponse.getBalance());
        Assertions.assertEquals(BigDecimal.valueOf(1300L),balanceQueryResponse.getAvailableBalance());
    }

    @Test
    void testAccountWithdrawWrongPin() {
        WithdrawFundsRequest withdrawFundsRequest = new WithdrawFundsRequest();
        withdrawFundsRequest.setAccountNumber("1234");
        withdrawFundsRequest.setPin("8080");
        withdrawFundsRequest.setAmount(BigDecimal.valueOf(100L));
        Assertions.assertThrows(InvalidPINException.class,() ->
            getAccountActionProcessor().performWithdraw(withdrawFundsRequest)
        );
    }

    @Test
    void testAccountWithdrawWrongAccountNumber() {
        WithdrawFundsRequest withdrawFundsRequest = new WithdrawFundsRequest();
        withdrawFundsRequest.setAccountNumber("2234");
        withdrawFundsRequest.setPin("1234");
        withdrawFundsRequest.setAmount(BigDecimal.valueOf(100L));
        Assertions.assertThrows(UnknownAccountException.class,() ->
                getAccountActionProcessor().performWithdraw(withdrawFundsRequest)
        );
    }

    @Test
    void testAccountWithdrawInsufficientCash() {
        WithdrawFundsRequest withdrawFundsRequest = new WithdrawFundsRequest();
        withdrawFundsRequest.setAccountNumber("1234");
        withdrawFundsRequest.setPin("1234");
        withdrawFundsRequest.setAmount(BigDecimal.valueOf(600L));
        Assertions.assertThrows(InsufficientCashException.class,() ->
                getAccountActionProcessor().performWithdraw(withdrawFundsRequest)
        );
    }

    @Test
    void testAccountWithdrawInsufficientFunds() {
        WithdrawFundsRequest withdrawFundsRequest = new WithdrawFundsRequest();
        withdrawFundsRequest.setAccountNumber("1234");
        withdrawFundsRequest.setPin("1234");
        withdrawFundsRequest.setAmount(BigDecimal.valueOf(1600L));
        Assertions.assertThrows(InsufficientFundsException.class,() ->
                getAccountActionProcessorBigCash().performWithdraw(withdrawFundsRequest)
        );
    }

    @Test
    void testAccountWithdrawInvalidDenomination() {
        WithdrawFundsRequest withdrawFundsRequest = new WithdrawFundsRequest();
        withdrawFundsRequest.setAccountNumber("1234");
        withdrawFundsRequest.setPin("1234");
        withdrawFundsRequest.setAmount(BigDecimal.valueOf(16L));
        Assertions.assertThrows(InvalidDenominationException.class,() ->
                getAccountActionProcessorBigCash().performWithdraw(withdrawFundsRequest)
        );
    }

}
