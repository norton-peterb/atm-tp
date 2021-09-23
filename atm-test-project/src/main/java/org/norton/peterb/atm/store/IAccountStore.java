package org.norton.peterb.atm.store;

import org.norton.peterb.atm.bean.CustomerAccountBean;

public interface IAccountStore {
    CustomerAccountBean getAccount(String accountNumber);
    void updateAccount(CustomerAccountBean customerAccountBean);
}
