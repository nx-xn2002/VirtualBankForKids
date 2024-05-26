package com.group20.backend.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AccountTest1 {

    @Test
    public void testAccountDefaultConstructor() {
        Account account = new Account();
        Assertions.assertNotNull(account, "Account object should not be null");
        Assertions.assertNull(account.getAccountId(), "Account ID should be null for default constructor");
        Assertions.assertNull(account.getType(), "Type should be null for default constructor");
        Assertions.assertNull(account.getBalance(), "Balance should be null for default constructor");
        Assertions.assertNull(account.getCreateTime(), "Create time should be null for default constructor");
        Assertions.assertNull(account.getUpdateTime(), "Update time should be null for default constructor");
    }

    @Test
    public void testAccountConstructorWithList() {
        List<String> inputList = new ArrayList<>();
        inputList.add("1");
        inputList.add("0");
        inputList.add("1000.00");
        String createTimeStr = "2023-05-01T10:00:00";
        inputList.add(createTimeStr);
        String updateTimeStr = "2023-05-02T11:00:00";
        inputList.add(updateTimeStr);

        Account account = new Account(inputList);

        Assertions.assertNotNull(account, "Account object should not be null");
        Assertions.assertEquals(1, account.getAccountId(), "Account ID should match the input value");
        Assertions.assertEquals((short) 0, account.getType(), "Type should match the input value");
        Assertions.assertEquals(1000.00f, account.getBalance(), "Balance should match the input value");

        LocalDateTime expectedCreateTime = LocalDateTime.parse(createTimeStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Assertions.assertEquals(expectedCreateTime, account.getCreateTime(), "Create time should match the input value");

        LocalDateTime expectedUpdateTime = LocalDateTime.parse(updateTimeStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Assertions.assertEquals(expectedUpdateTime, account.getUpdateTime(), "Update time should match the input value");
    }

    @Test
    public void testAccountToString() {
        List<String> inputList = new ArrayList<>();
        inputList.add("2");
        inputList.add("1");
        inputList.add("2000.00");
        //origin format like 2023-05-03T12:00:00 will fail
        String createTimeStr = "2023-05-03T12:00";
        inputList.add(createTimeStr);
        String updateTimeStr = "2023-05-04T13:00";
        inputList.add(updateTimeStr);

        Account account = new Account(inputList);

        String expectedToString = "2 1 2000.0 " + createTimeStr + " " + updateTimeStr;
        String actualToString = account.toString();
        actualToString = actualToString;
        Assertions.assertEquals(expectedToString, actualToString, "toString() output should match the expected format");
    }
}