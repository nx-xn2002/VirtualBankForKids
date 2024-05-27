package com.group20.backend.dao;

/**
 * dao manage
 *
 * @author Ni Xiang
 */
public class DaoManage {
    private static final String PATH_FRONT = true ? "src/main/resources/" : "";
    private static final UserDao USER_DAO = new UserDao(PATH_FRONT + "user_data.csv");
    private static final RelationDao RELATION_DAO = new RelationDao(PATH_FRONT + "relation_data.csv");
    private static final AccountDao ACCOUNT_DAO = new AccountDao(PATH_FRONT + "account_data.csv");
    private static final MoneyDao MONEY_DAO = new MoneyDao(PATH_FRONT + "money_data.csv");
    private static final TaskDao TASK_DAO = new TaskDao(PATH_FRONT + "task_data.csv");

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
