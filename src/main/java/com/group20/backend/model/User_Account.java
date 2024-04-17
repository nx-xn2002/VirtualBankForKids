package com.group20.backend.model;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Account
 *
 * @author Wang BoWen
 */
public class User_Account {
    /**
     * data type
     */
    public static String DATA_TYPE = "com.group20.model.User_Account";
    /**
     * accountId
     */
    public Integer accountId;
    /**
     * user id
     */
    private Integer userId;
    /**
     * createTime
     */
    private LocalDateTime createTime;
    /**
     * update time
     */
    private LocalDateTime updateTime;
    public User_Account() {

    }
    public User_Account(List<String> s) {
        this.setAccountId(Integer.valueOf(s.get(0)));
        this.setUserId(Integer.valueOf(s.get(1)));
        this.setCreateTime(LocalDateTime.parse(s.get(2)));
        this.setUpdateTime(LocalDateTime.parse(s.get(3)));
    }
}
