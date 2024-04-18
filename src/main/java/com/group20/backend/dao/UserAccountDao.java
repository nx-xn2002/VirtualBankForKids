package com.group20.backend.dao;

import com.group20.backend.model.UserAccount;

import java.util.ArrayList;
import java.util.List;

/**
 * user account dao
 *
 * @author Ni Xiang
 */
public class UserAccountDao extends BaseDao<UserAccount> {
    public UserAccountDao(String path) {
        super(UserAccount.DATA_TYPE, path);
    }

    public List<UserAccount> getAllUserAccount() {
        List<Object> list = super.getAll();
        List<UserAccount> userAccountList = new ArrayList<>();
        for (Object object : list) {
            userAccountList.add((UserAccount) object);
        }
        return userAccountList;
    }
}
