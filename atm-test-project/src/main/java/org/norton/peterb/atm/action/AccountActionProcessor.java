package org.norton.peterb.atm.action;

import org.apache.commons.codec.digest.DigestUtils;
import org.norton.peterb.atm.bean.CustomerAccountBean;
import org.norton.peterb.atm.exception.ATMException;
import org.norton.peterb.atm.exception.InsufficientFundsException;
import org.norton.peterb.atm.exception.InvalidPINException;
import org.norton.peterb.atm.exception.UnknownAccountException;
import org.norton.peterb.atm.model.BalanceQueryResponse;
import org.norton.peterb.atm.model.BaseAccountRequest;
import org.norton.peterb.atm.model.WithdrawFundsRequest;
import org.norton.peterb.atm.store.IAccountStore;
import org.norton.peterb.atm.store.IMachineCashStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Map;

public class AccountActionProcessor implements IAccountActionProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountActionProcessor.class);

    private final IAccountStore accountStore;
    private final IMachineCashStore machineCashStore;

    public AccountActionProcessor(IAccountStore accountStore, IMachineCashStore machineCashStore) {
        this.accountStore = accountStore;
        this.machineCashStore = machineCashStore;
    }

    private void validatePin(BaseAccountRequest baseAccountRequest) throws InvalidPINException,
            UnknownAccountException {
        CustomerAccountBean customerAccountBean = accountStore.getAccount(baseAccountRequest.getAccountNumber());
        // Combine Account And Pin and generate hashcode
        String sha256hash = DigestUtils.sha256Hex(customerAccountBean.getAccountNumber()
                + ":" + baseAccountRequest.getPin());
        if(!sha256hash.equalsIgnoreCase(customerAccountBean.getPinHash())) {
            throw new InvalidPINException();
        }
    }

    @Override
    public BalanceQueryResponse performWithdraw(WithdrawFundsRequest withdrawFundsRequest) throws ATMException {
        validatePin(withdrawFundsRequest);
        CustomerAccountBean customerAccountBean = accountStore.getAccount(withdrawFundsRequest.getAccountNumber());
        BigDecimal availableFunds =
                customerAccountBean.getAccountBalance().add(customerAccountBean.getOverdraftAmount());
        if(withdrawFundsRequest.getAmount().compareTo(availableFunds) > 0) {
            throw new InsufficientFundsException();
        }
        Map<BigDecimal, Integer> cash =
                machineCashStore.requestCash(withdrawFundsRequest.getAmount());
        if(LOGGER.isDebugEnabled()) {
            cash.forEach(
                    (k,v) -> LOGGER.debug("Dispensing {} note(s) of value {}€",v,k)
            );
        }
        BigDecimal newBalance = customerAccountBean.getAccountBalance()
                .subtract(withdrawFundsRequest.getAmount());
        customerAccountBean.setAccountBalance(newBalance);
        accountStore.updateAccount(customerAccountBean);
        BalanceQueryResponse balanceQueryResponse = new BalanceQueryResponse();
        balanceQueryResponse.setBalance(customerAccountBean.getAccountBalance());
        balanceQueryResponse.setAvailableBalance(
                customerAccountBean.getAccountBalance().add(customerAccountBean.getOverdraftAmount())
        );
        return balanceQueryResponse;
    }

    @Override
    public BalanceQueryResponse checkBalance(BaseAccountRequest baseAccountRequest) throws ATMException {
        validatePin(baseAccountRequest);
        CustomerAccountBean customerAccountBean = accountStore.getAccount(baseAccountRequest.getAccountNumber());
        BalanceQueryResponse balanceQueryResponse = new BalanceQueryResponse();
        balanceQueryResponse.setBalance(customerAccountBean.getAccountBalance());
        balanceQueryResponse.setAvailableBalance(customerAccountBean.getAccountBalance()
                .add(customerAccountBean.getOverdraftAmount()));
        return balanceQueryResponse;
    }
}
