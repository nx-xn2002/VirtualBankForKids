package com.group20.backend.dao;

import com.group20.backend.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {

    private UserDao userDao;
    private static final String CSV_FILE_PATH = "src/main/resources/TestUsers.csv";

    @BeforeEach
    public void setUp() {
        userDao = new UserDao(CSV_FILE_PATH);
    }

    @Test
    public void testGetAllUser() {
        System.out.println("Reading users from: " + CSV_FILE_PATH);
        List<User> users = userDao.getAllUser();

        assertNotNull(users, "User list should not be null");
        assertFalse(users.isEmpty(), "User list should not be empty");

        User firstUser = users.get(0);
        assertNotNull(firstUser, "First User should not be null");

        assertEquals(1, (int) firstUser.getUserId(), "User ID mismatch for the first User");

        assertEquals("0x1", firstUser.getUsername(), "Username mismatch for the first User");
        System.out.println(firstUser.getPassword()+"\n"+firstUser.getUserId()+"\n"+firstUser.getUsername()+"\n"+firstUser.getRole()+"\n"+firstUser.getAge()+"\n"+firstUser.getEmail()+"\n"+firstUser.getPhone());

    }
}