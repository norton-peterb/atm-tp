package org.norton.peterb.atm.store;

import org.norton.peterb.atm.bean.CustomerAccountBean;
import org.norton.peterb.atm.exception.UnknownAccountException;

import java.util.HashMap;
import java.util.Map;

public class AccountStore implements IAccountStore {

    private final Map<String,CustomerAccountBean> customerAccountMap = new HashMap<>();

    public AccountStore(Map<String, CustomerAccountBean> customerAccountMap) {
        this.customerAccountMap.putAll(customerAccountMap);
    }

    @Override
    public CustomerAccountBean getAccount(String accountNumber) throws UnknownAccountException {
        if(customerAccountMap.containsKey(accountNumber)) {
            return customerAccountMap.get(accountNumber);
        } else {
            throw new UnknownAccountException("Account Number " + accountNumber + " not recognised");
        }
    }

    @Override
    public void updateAccount(CustomerAccountBean customerAccountBean) throws UnknownAccountException {
        if(customerAccountMap.containsKey(customerAccountBean.getAccountNumber())) {
            customerAccountMap.put(customerAccountBean.getAccountNumber(), customerAccountBean);
        } else {
            throw new UnknownAccountException("Account Number " + customerAccountBean.getAccountNumber()
                    + " not recognised");
        }
    }
}
