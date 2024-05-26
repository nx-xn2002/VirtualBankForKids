package com.group20.backend.dao;

import com.group20.backend.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AccountDaoTest {

    private AccountDao accountDao;
    String path = "src/main/resources/TestAccounts.csv";

    @BeforeEach
    public void setUp() {
        accountDao = new AccountDao(path);
    }

    @Test
    public void testGetAllAccount() {
        System.out.println(path);
        List<Account> accounts = accountDao.getAllAccount();
        assertNotNull(accounts, "Account list should not be null");

        Account firstAccount = accounts.get(0);
        Account SecondAccount = accounts.get(1);
        assertNotNull(firstAccount, "First account should not be null");
        assertEquals(3, (int) firstAccount.getAccountId(), "Account ID mismatch for the first account");
        assertEquals((short) 0, firstAccount.getType(), "Account type mismatch for the first account");
        assertNotNull(SecondAccount, "sec account should not be null");
    }

}