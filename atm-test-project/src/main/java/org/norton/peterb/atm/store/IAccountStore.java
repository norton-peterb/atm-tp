package org.norton.peterb.atm.store;

import org.norton.peterb.atm.bean.CustomerAccountBean;
import org.norton.peterb.atm.exception.UnknownAccountException;

public interface IAccountStore {
    CustomerAccountBean getAccount(String accountNumber) throws UnknownAccountException;
    void updateAccount(CustomerAccountBean customerAccountBean) throws UnknownAccountException;
}
