package org.norton.peterb.atm.action;

import org.norton.peterb.atm.bean.AvailableBalanceBean;
import org.norton.peterb.atm.bean.CustomerAccountBean;

public class CheckBalanceAvailableAction {

    public AvailableBalanceBean checkAvailableBalance(CustomerAccountBean customerAccountBean) {
        return new AvailableBalanceBean(customerAccountBean.getAccountBalance(),
                customerAccountBean.getAccountBalance().add(customerAccountBean.getOverdraftAmount()));
    }
}
