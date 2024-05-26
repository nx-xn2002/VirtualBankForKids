package com.group20.backend.dao;

import com.group20.backend.model.Money;

/**
 * dao manage
 *
 * @author Ni Xiang
 */
public class DaoManage {
    private static final UserDao USER_DAO = new UserDao("src/main/resources/user_data.csv");
    private static final RelationDao RELATION_DAO = new RelationDao("src/main/resources/relation_data.csv");
    private static final AccountDao ACCOUNT_DAO = new AccountDao("src/main/resources/account_data.csv");
    private static final MoneyDao MONEY_DAO = new MoneyDao("src/main/resources/money_data.csv");

    public static UserDao getUserDaoInstance() {
        return USER_DAO;
    }

    public static RelationDao getRelationDaoInstance() {
        return RELATION_DAO;
    }

    public static AccountDao getAccountDaoInstance() {
        return ACCOUNT_DAO;
    }

    public static MoneyDao getMoneyDaoInstance() {
        return MONEY_DAO;
    }
}
