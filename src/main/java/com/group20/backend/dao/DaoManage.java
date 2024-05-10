package com.group20.backend.dao;

/**
 * dao manage
 *
 * @author Ni Xiang
 */
public class DaoManage {
    private static final UserDao USER_DAO = new UserDao("src/main/resources/user_data.csv");
    private static final RelationDao RELATION_DAO = new RelationDao("src/main/resources/relation.csv");
    private static final AccountDao ACCOUNT_DAO = new AccountDao("src/main/resources/account_data.csv");
    private static final UserAccountDao USER_ACCOUNT_DAO = new UserAccountDao("src/main/resources/user_account_data.csv");
    public static UserDao getUserDaoInstance(){
        return USER_DAO;
    }
    public static RelationDao getRelationDaoInstance(){
        return RELATION_DAO;
    }
}
