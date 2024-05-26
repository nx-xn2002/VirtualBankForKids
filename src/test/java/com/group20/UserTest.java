import com.group20.backend.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UserTest {

    @Test
    public void testUserDefaultConstructor() {
        User user = new User();
        Assertions.assertNull(user.getUserId());
        Assertions.assertNull(user.getUsername());
        Assertions.assertNull(user.getPassword());
        Assertions.assertNull(user.getAge());
        Assertions.assertNull(user.getRole());
        Assertions.assertNull(user.getPhone());
        Assertions.assertNull(user.getEmail());
        Assertions.assertNull(user.getCreateTime());
        Assertions.assertNull(user.getUpdateTime());
    }

    @Test
    public void testUserWithUsernameAndPasswordConstructor() {
        User user = new User("testUser", "testPassword");
        Assertions.assertNull(user.getUserId());
        Assertions.assertEquals("testUser", user.getUsername());
        Assertions.assertEquals("testPassword", user.getPassword());
        Assertions.assertNull(user.getAge());
        Assertions.assertNull(user.getRole());
        Assertions.assertNull(user.getPhone());
        Assertions.assertNull(user.getEmail());
        Assertions.assertNull(user.getCreateTime());
        Assertions.assertNull(user.getUpdateTime());
    }

    @Test
    public void testUserWithListConstructor() {
        List<String> userData = new ArrayList<>();
        userData.add("1");
        userData.add("testUser");
        userData.add("testPassword");
        userData.add("10");
        userData.add("0");
        userData.add("1234567890");
        userData.add("test@example.com");
        LocalDateTime now = LocalDateTime.now();
        String createTimeString = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        userData.add(createTimeString);
        userData.add(createTimeString);

        User user = new User(userData);
        Assertions.assertEquals(1, (int) user.getUserId());
        Assertions.assertEquals("testUser", user.getUsername());
        Assertions.assertEquals("testPassword", user.getPassword());
        Assertions.assertEquals(10, (int) user.getAge());
        Assertions.assertEquals((short) 0, user.getRole().shortValue());
        Assertions.assertEquals("1234567890", user.getPhone());
        Assertions.assertEquals("test@example.com", user.getEmail());
        Assertions.assertNotNull(user.getCreateTime());
        Assertions.assertNotNull(user.getUpdateTime());
    }

    @Test
    public void testUserToString() {
        User user = new User();
        user.setUserId(1);
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setAge(25);
        user.setRole(User.CHILD);
        user.setPhone("1234567890");
        user.setEmail("test@example.com");
        LocalDateTime now = LocalDateTime.now();
        user.setCreateTime(now);
        user.setUpdateTime(now);

        String userString = user.toString();
        String[] fields = userString.split(",");
        Assertions.assertEquals(9, fields.length);
        Assertions.assertEquals("1", fields[0].trim());
        Assertions.assertEquals("testUser", fields[1].trim());
        Assertions.assertEquals("testPassword", fields[2].trim());
        Assertions.assertEquals("25", fields[3].trim());
        Assertions.assertEquals("0", fields[4].trim());
        Assertions.assertEquals("1234567890", fields[5].trim());
        Assertions.assertEquals("test@example.com", fields[6].trim());
        // The exact time format may vary based on the default formatter, so we'll just check that it's not empty
        Assertions.assertFalse(fields[7].trim().isEmpty());
        Assertions.assertFalse(fields[8].trim().isEmpty());
    }
}
