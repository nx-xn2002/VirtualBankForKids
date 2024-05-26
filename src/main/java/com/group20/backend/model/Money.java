package com.group20.backend.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * money
 *
 * @author Ni Xiang
 */
@Data
public class Money {
    public final static Short WITHDRAW = 0;
    public final static Short SAVE = 1;
    public final static Short TRANSFER = 2;
    /**
     * data type
     */
    public final static String DATA_TYPE = "com.group20.backend.model.Money";
    /**
     * money id
     */
    private Integer moneyId;
    /**
     * type
     * 0 - Withdraw取款,1 - Save存钱, 2 - Transfer转账
     */
    private Short type;
    /**
     * amount
     */
    private Float amount;
    /**
     * account ida
     */
    private Integer accountIdA;
    /**
     * account idb
     */
    private Integer accountIdB;
    /**
     * create time
     */
    private LocalDateTime createTime;

    public Money() {
    }

    public Money(List<String> list) {
        this.setMoneyId(Integer.parseInt(list.get(0)));
        this.setType(Short.parseShort(list.get(1)));
        this.setAmount(Float.parseFloat(list.get(2)));
        this.setAccountIdA(Integer.parseInt(list.get(3)));
        this.setAccountIdB(Integer.parseInt(list.get(4)));
        this.setCreateTime(LocalDateTime.parse(list.get(5)));
    }

    @Override
    public String toString() {
        return moneyId +
                "," + type +
                "," + amount +
                "," + accountIdA +
                "," + accountIdB +
                "," + createTime;
    }
}
