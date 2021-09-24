package org.norton.peterb.atm.controller;

import org.norton.peterb.atm.action.IAccountActionProcessor;
import org.norton.peterb.atm.exception.ATMException;
import org.norton.peterb.atm.model.BalanceQueryResponse;
import org.norton.peterb.atm.model.BaseAccountRequest;
import org.norton.peterb.atm.model.WithdrawFundsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ATMRestController {

    private final IAccountActionProcessor accountActionProcessor;

    public ATMRestController(@Autowired IAccountActionProcessor accountActionProcessor) {
        this.accountActionProcessor = accountActionProcessor;
    }

    @PostMapping(path = "/balance",produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BalanceQueryResponse balanceQuery(@RequestBody BaseAccountRequest baseAccountRequest) {
        try {
            return accountActionProcessor.checkBalance(baseAccountRequest);
        } catch (ATMException exception) {
            BalanceQueryResponse balanceQueryResponse = new BalanceQueryResponse();
            balanceQueryResponse.setStatus(-1);
            balanceQueryResponse.setMessage(exception.getMessage());
            return balanceQueryResponse;
        }
    }

    @PostMapping(path = "/withdrawFunds",produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BalanceQueryResponse withdrawFunds(@RequestBody WithdrawFundsRequest withdrawFundsRequest) {
        try {
            return accountActionProcessor.performWithdraw(withdrawFundsRequest);
        } catch (ATMException exception) {
            BalanceQueryResponse balanceQueryResponse = new BalanceQueryResponse();
            balanceQueryResponse.setStatus(-1);
            balanceQueryResponse.setMessage(exception.getMessage());
            return balanceQueryResponse;
        }
    }
}
