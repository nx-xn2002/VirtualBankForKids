package com.group20.backend.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * User
 *
 * @author Ni Xiang
 */
@Data
public class User {
    /**
     * data type
     */
    public final static String DATA_TYPE = "com.group20.backend.model.User";
    /**
     * user id
     */
    private Integer userId;
    /**
     * user name
     */
    private String userName;
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

    public User(List<String> s) {
        this.setUserId(Integer.valueOf(s.get(0)));
        this.setUserName(s.get(1));
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
        return userId + " " +
                userName + " " +
                age + " " +
                role + " " +
                phone + " " +
                email + " " +
                createTime + " " +
                updateTime + " ";
    }
}
