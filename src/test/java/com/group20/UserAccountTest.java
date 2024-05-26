package com.group20.backend.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UserAccountTest {

    @Test
    public void testUserAccountConstructorWithList() {
        // Prepare test data
        List<String> testData = new ArrayList<>();
        testData.add("1");
        testData.add("2");
        String createTimeStr = "2023-03-20T10:15:30";
        testData.add(createTimeStr);
        String updateTimeStr = "2023-03-21T11:30:00";
        testData.add(updateTimeStr);
        UserAccount userAccount = new UserAccount(testData);
        Assertions.assertEquals(1, userAccount.getAccountId());
        Assertions.assertEquals(2, userAccount.getUserId());

        // LocalDateTime objects for comparison
        LocalDateTime expectedCreateTime = LocalDateTime.parse(createTimeStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime expectedUpdateTime = LocalDateTime.parse(updateTimeStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        Assertions.assertEquals(expectedCreateTime, userAccount.getCreateTime());
        Assertions.assertEquals(expectedUpdateTime, userAccount.getUpdateTime());

        // Test toString method
        String expectedToString = "1 2 " + expectedCreateTime + " " + expectedUpdateTime;
        Assertions.assertEquals(expectedToString.trim(), userAccount.toString().trim());
    }

    @Test
    public void testUserAccountDefaultConstructorAndSetters() {
        UserAccount userAccount = new UserAccount();
        // initialize the object
        userAccount.setAccountId(10);
        userAccount.setUserId(20);
        userAccount.setCreateTime(LocalDateTime.now());
        userAccount.setUpdateTime(LocalDateTime.now());

        Assertions.assertEquals(10, userAccount.getAccountId());
        Assertions.assertEquals(20, userAccount.getUserId());
        Assertions.assertNotNull(userAccount.getCreateTime());
        Assertions.assertNotNull(userAccount.getUpdateTime());
    }
}