package com.group20.backend.dao;

import com.group20.backend.model.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserAccountDaoTest {

    private UserAccountDao userAccountDao;
    String path = "src/main/resources/TestUserAccounts.csv";

    @BeforeEach
    public void setUp() {
        userAccountDao = new UserAccountDao(path);
    }

    @Test
    public void testGetAllUserAccount() {
        System.out.println(path);
        List<UserAccount> userAccounts = userAccountDao.getAllUserAccount();
        assertNotNull(userAccounts, "UserAccount list should not be null");

        assertFalse(userAccounts.isEmpty(), "UserAccount list should not be empty");

        UserAccount firstUserAccount = userAccounts.get(0);
        assertNotNull(firstUserAccount, "First UserAccount should not be null");
        assertEquals(1, (int) firstUserAccount.getAccountId(), "Account ID mismatch for the first UserAccount");
        assertEquals(1001, (int) firstUserAccount.getUserId(), "User ID mismatch for the first UserAccount");

    }
}