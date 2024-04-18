package com.group20.backend.dao;

/**
 * dao manage
 *
 * @author Ni Xiang
 */
public class DaoManage {
    private static final UserDao USER_DAO = new UserDao("src/main/resources/user_data.txt");
    private static final AccountDao ACCOUNT_DAO = new AccountDao("src/main/resources/account_data.txt");
    private static final UserAccountDao USER_ACCOUNT_DAO = new UserAccountDao("src/main/resources/user_account_data.txt");
    public static UserDao getUserDaoInstance(){
        return USER_DAO;
    }
}
