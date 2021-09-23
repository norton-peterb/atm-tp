package org.norton.peterb.atm.action;

import org.apache.commons.codec.digest.DigestUtils;
import org.norton.peterb.atm.bean.CustomerAccountBean;

public class ValidatePINAction {

    public boolean validatePIN(String pin, CustomerAccountBean customerAccountBean) {
        // Combine Account And Pin and generate hashcode
        String sha256hash = DigestUtils.sha256Hex(customerAccountBean.getAccountNumber()
            + ":" + pin);
        return sha256hash.equalsIgnoreCase(customerAccountBean.getPinHash());
    }
}
