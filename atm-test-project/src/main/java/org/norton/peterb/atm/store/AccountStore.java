package org.norton.peterb.atm.store;

import org.norton.peterb.atm.bean.CustomerAccountBean;

import java.util.HashMap;
import java.util.Map;

public class AccountStore implements IAccountStore {

    private final Map<String,CustomerAccountBean> customerAccountMap = new HashMap<>();

    public AccountStore(Map<String, CustomerAccountBean> customerAccountMap) {
        this.customerAccountMap.putAll(customerAccountMap);
    }

    @Override
    public CustomerAccountBean getAccount(String accountNumber) {
        return customerAccountMap.get(accountNumber);
    }

    @Override
    public void updateAccount(CustomerAccountBean customerAccountBean) {
        customerAccountMap.put(customerAccountBean.getAccountNumber(),customerAccountBean);
    }
}
