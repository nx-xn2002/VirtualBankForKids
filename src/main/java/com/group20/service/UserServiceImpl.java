package com.group20.service;

import com.group20.dao.DaoManage;
import com.group20.dao.UserDao;
import com.group20.model.User;

import java.time.LocalDateTime;
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

    @Override
    public Boolean register(User user) {
        List<User> allUser = userDao.getAllUser();
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        if (allUser.isEmpty()) {
            user.setUserId(0);
            allUser.add(user);
            userDao.saveAll(allUser);
            return true;
        }
        int maxId = Integer.MIN_VALUE;
        for (User userExist : allUser) {
            if (userExist.getUserName().equals(user.getUserName())) {
                return false;
            }
        }
        user.setUserId(maxId + 1);
        allUser.add(user);
        userDao.saveAll(allUser);
        return true;
    }
}
