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
public class Account {
    public final static Short CURRENT = 0;
    public final static Short SAVING = 1;
    /**
     * data type
     */
    public final static String DATA_TYPE = "com.group20.backend.model.Account";
    /**
     * accountId
     */
    private Integer accountId;
    /**
     * user id
     */
    private Integer userId;
    /**
     * 0 - current accounts, 1 - saving accounts
     */
    private Short type;
    /**
     * balance
     */
    private Float balance;
    /**
     * createTime
     */
    private LocalDateTime createTime;
    /**
     * update time
     */
    private LocalDateTime updateTime;

    public Account() {
    }

    public Account(List<String> s) {
        this.setAccountId(Integer.valueOf(s.get(0)));
        this.setUserId(Integer.valueOf(s.get(1)));
        this.setType(Short.valueOf(s.get(2)));
        this.setBalance(Float.valueOf(s.get(3)));
        this.setCreateTime(LocalDateTime.parse(s.get(4)));
        this.setUpdateTime(LocalDateTime.parse(s.get(5)));
    }

    @Override
    public String toString() {
        return accountId + "," +
                userId + "," +
                type + "," +
                balance + "," +
                createTime + "," +
                updateTime;
    }
}
