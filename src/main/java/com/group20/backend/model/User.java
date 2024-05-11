package com.group20.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * User
 *
 * @author Ni Xiang
 */
@Data
@AllArgsConstructor
public class User {
    /**
     * data type
     */
    public final static String DATA_TYPE = "com.group20.backend.model.User";
    public final static Short PARENT = 1;
    public final static Short CHILD = 0;
    /**
     * user id
     */
    private Integer userId;
    /**
     * user name
     */
    private String username;
    /**
     * password
     */
    private String password;
    /**
     * age
     */
    private Integer age;
    /**
     * role
     * 0 - 孩子, 1 - 家长
     */
    private Short role;
    /**
     * phone
     */
    private String phone;
    /**
     * email
     */
    private String email;
    /**
     * create time
     */
    private LocalDateTime createTime;
    /**
     * update time
     */
    private LocalDateTime updateTime;

    public User() {
    }

    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    public User(List<String> s) {
        this.setUserId(Integer.valueOf(s.get(0)));
        this.setUsername(s.get(1));
        this.setPassword(s.get(2));
        this.setAge(Integer.valueOf(s.get(3)));
        this.setRole(Short.valueOf(s.get(4)));
        this.setPhone(s.get(5));
        this.setEmail(s.get(6));
        this.setCreateTime(LocalDateTime.parse(s.get(7)));
        this.setUpdateTime(LocalDateTime.parse(s.get(8)));
    }

    @Override
    public String toString() {
        return userId + "," +
                username + "," +
                password + "," +
                age + "," +
                role + "," +
                phone + "," +
                email + "," +
                createTime + "," +
                updateTime;
    }
}
