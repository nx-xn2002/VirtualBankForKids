package com.group20.backend.dao;

import com.group20.backend.model.Money;

/**
 * dao manage
 *
 * @author Ni Xiang
 */
public class DaoManage {
    private static final String PATH_FRONT1 = "src/main/resources/";
    private static final String PATH_FRONT2 = "";
    private static final UserDao USER_DAO = new UserDao(PATH_FRONT1 + "user_data.csv");
    private static final RelationDao RELATION_DAO = new RelationDao(PATH_FRONT1 + "relation_data.csv");
    private static final AccountDao ACCOUNT_DAO = new AccountDao(PATH_FRONT1 + "account_data.csv");
    private static final MoneyDao MONEY_DAO = new MoneyDao(PATH_FRONT1 + "money_data.csv");
    private static final TaskDao TASK_DAO = new TaskDao(PATH_FRONT1 + "src/main/resources/task_data.csv");

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

    public static TaskDao getTaskDaoInstance() {
        return TASK_DAO;
    }
}
