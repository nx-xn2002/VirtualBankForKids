package com.group20.backend.dao;

/**
 * dao manage
 *
 * @author Ni Xiang
 */
public class DaoManage {
    private static final UserDao USER_DAO = new UserDao("src/main/resources/testdata.txt");
    public static UserDao getUserDaoInstance(){
        return USER_DAO;
    }
}
