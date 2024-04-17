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
    /**
     * data type
     */
    public final static String DATA_TYPE = "com.group20.backend.model.Account";
    /**
     * accountId
     */
    public Integer accountId;
    /**
     * 0 - current accounts, 1 - saving accounts
     */
    public Short type;
    /**
     * balance
     */
    public Float balance;
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
        this.setType(Short.valueOf(s.get(1)));
        this.setBalance(Float.valueOf(s.get(2)));
        this.setCreateTime(LocalDateTime.parse(s.get(3)));
        this.setUpdateTime(LocalDateTime.parse(s.get(4)));
    }

    @Override
    public String toString() {
        return accountId + " " +
                type + " " +
                balance + " " +
                createTime + " " +
                updateTime;
    }
}
