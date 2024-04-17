package com.group20.service;

import com.group20.dao.DaoManage;
import com.group20.dao.UserDao;
import com.group20.model.User;

import java.util.List;

/**
 * user service impl
 *
 * @author Ni Xiang
 */
public class UserServiceImpl implements UserService {
    private final UserDao userDao = DaoManage.getUserDaoInstance();

    @Override
    public Boolean login(User userLogin) {
        List<User> allUser = userDao.getAllUser();
        for (User user : allUser) {
            if (user.getUserName().equals(userLogin.getUserName()) && user.getPassword().equals(userLogin.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
