package com.group20;

import com.group20.backend.model.Account;
import com.group20.backend.service.AccountServiceImpl;
import org.junit.jupiter.api.Test;

/**
 * account test
 *
 * @author Ni Xiang
 */
public class AccountTest {
    /**
     * add test
     *
     * @author Ni Xiang
     */
    @Test
    public void addTest() {
        AccountServiceImpl accountService = new AccountServiceImpl();
        Account account = new Account();
        account.setUserId(0);
        account.setType(Account.SAVING);
        accountService.createAccount(account);
    }
}
