package org.norton.peterb.atm.test.action;

import org.norton.peterb.atm.bean.CustomerAccountBean;
import org.norton.peterb.atm.exception.UnknownAccountException;
import org.norton.peterb.atm.store.IAccountStore;

import java.math.BigDecimal;

public class MockAccountStore implements IAccountStore {
    @Override
    public CustomerAccountBean getAccount(String accountNumber) throws UnknownAccountException {
        if(accountNumber.equals("1234")) {
            return new CustomerAccountBean(
                    "1234",
                    "b58f51929f7fe337cebc3a7fc162673d510b757fdfc439d7aed4947f7561fc90",
                    BigDecimal.valueOf(1200L),
                    BigDecimal.valueOf(200L)
            );
        } else {
            throw new UnknownAccountException();
        }
    }

    @Override
    public void updateAccount(CustomerAccountBean customerAccountBean) throws UnknownAccountException {
        if(!customerAccountBean.getAccountNumber().equals("1234")) {
            throw new UnknownAccountException();
        }
    }
}
