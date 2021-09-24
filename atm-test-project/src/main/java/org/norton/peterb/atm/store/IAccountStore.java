package org.norton.peterb.atm.store;

import org.norton.peterb.atm.bean.CustomerAccountBean;
import org.norton.peterb.atm.exception.UnknownAccountException;

/**
 * Store for Account Information
 */
public interface IAccountStore {
    /**
     * Get the details for an account
     * @param accountNumber Account Number to use
     * @return Customer Account Information
     * @throws UnknownAccountException
     */
    CustomerAccountBean getAccount(String accountNumber) throws UnknownAccountException;

    /**
     * Update Account details
     * @param customerAccountBean Account Information to merge in
     * @throws UnknownAccountException
     */
    void updateAccount(CustomerAccountBean customerAccountBean) throws UnknownAccountException;
}
