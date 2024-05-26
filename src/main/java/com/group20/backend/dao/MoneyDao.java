package com.group20.backend.dao;

import com.group20.backend.model.Money;
import com.group20.backend.model.Money;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * money dao
 *
 * @author Ni Xiang
 */
public class MoneyDao extends BaseDao<Money> {

    public MoneyDao(String path) {
        super(Money.DATA_TYPE, path);
    }

    public List<Money> getAllMoney() {
        List<Object> list = super.getAll();
        List<Money> moneyList = new ArrayList<>();
        for (Object o : list) {
            moneyList.add((Money) o);
        }
        return moneyList;
    }

    public void add(Money money) {
        List<Money> allMoney = getAllMoney();
        int max = Integer.MIN_VALUE;
        for (Money moneyExist : allMoney) {
            max = Math.max(max, moneyExist.getMoneyId());
        }
        money.setCreateTime(LocalDateTime.now());
        money.setMoneyId(max + 1);
        if (money.getAccountIdB() == null) {
            money.setAccountIdB(0);
        }
        allMoney.add(money);
        saveAll(allMoney);
    }
}
