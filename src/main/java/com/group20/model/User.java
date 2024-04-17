package com.group20.model;

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
    public static String DATA_TYPE = "com.group20.model.User";
    /**
     * user id
     */
    private Integer userId;
    /**
     * user name
     */
    private String userName;
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
    /**
     * is deleted
     * 逻辑删除 0 - 正常, 1 - 删除
     */
    private Short isDeleted;

    public User() {
    }

    public User(List<String> s) {
        this.setUserId(Integer.valueOf(s.get(0)));
        this.setUserName(s.get(1));
        this.setAge(Integer.valueOf(s.get(2)));
        this.setRole(Short.valueOf(s.get(3)));
        this.setPhone(s.get(4));
        this.setEmail(s.get(5));
        this.setCreateTime(LocalDateTime.parse(s.get(6)));
        this.setUpdateTime(LocalDateTime.parse(s.get(7)));
        this.setIsDeleted(Short.valueOf(s.get(8)));
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
                updateTime + " " +
                isDeleted;

    }
}
