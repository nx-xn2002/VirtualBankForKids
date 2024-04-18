package com.group20.backend.dao;

import com.group20.backend.model.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * account dao
 *
 * @author Ni Xiang
 */
public class AccountDao extends BaseDao<Account> {
    public AccountDao(String path) {
        super(Account.DATA_TYPE, path);
    }

    public List<Account> getAllAccount() {
        List<Object> list = super.getAll();
        List<Account> accountList = new ArrayList<>();
        for (Object o : list) {
            accountList.add((Account) o);
        }
        return accountList;
    }

}
