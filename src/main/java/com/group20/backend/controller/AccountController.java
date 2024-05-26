package com.group20.backend.controller;

import com.group20.Request;
import com.group20.Response;
import com.group20.backend.model.Account;
import com.group20.backend.model.Money;
import com.group20.backend.service.AccountService;
import com.group20.backend.service.AccountServiceImpl;
import com.group20.backend.service.MoneyService;
import com.group20.backend.service.MoneyServiceImpl;
import com.group20.utils.ResultUtil;

/**
 * account controller
 *
 * @author Ni Xiang
 */
public class AccountController implements Controller {
    private final AccountService accountService = new AccountServiceImpl();
    private final MoneyService moneyService = new MoneyServiceImpl();

    @Override
    public Response requestHandler(Request request) {
        String url = request.getUrl();
        if (url.contains("selectAccount")) {
            return accountService.selectAccountByUserId((Integer) request.getBody());
        } else if (url.contains("createAccount")) {
            return accountService.createAccount((Account) request.getBody());
        } else if (url.contains("removeAccount")) {
            return accountService.removeAccount((Account) request.getBody());
        } else if (url.contains("change")) {
            return moneyService.change((Money) request.getBody());
        } else if (url.contains("getMoneyList")) {
            return moneyService.getMoneyListByUserId((Integer) request.getBody());
        }
        return ResultUtil.fail("无效url");
    }
}
