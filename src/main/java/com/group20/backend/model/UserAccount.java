package com.group20.backend.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Account
 *
 * @author Wang BoWen
 */
@Data
public class UserAccount {
    /**
     * data type
     */
    public final static String DATA_TYPE = "com.group20.backend.model.UserAccount";
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

    public UserAccount() {
    }

    public UserAccount(List<String> s) {
        this.setAccountId(Integer.valueOf(s.get(0)));
        this.setUserId(Integer.valueOf(s.get(1)));
        this.setCreateTime(LocalDateTime.parse(s.get(2)));
        this.setUpdateTime(LocalDateTime.parse(s.get(3)));
    }

    @Override
    public String toString() {
        return accountId +
                " " + userId +
                " " + createTime +
                " " + updateTime;
    }
}
