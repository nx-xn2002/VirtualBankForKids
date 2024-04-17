package com.group20.backend.service;

import com.group20.Response;
import com.group20.backend.dao.DaoManage;
import com.group20.backend.dao.UserDao;
import com.group20.backend.model.User;
import com.group20.utils.ResultUtil;

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
    public Response<Boolean> login(User userLogin) {
        List<User> allUser = userDao.getAllUser();
        for (User user : allUser) {
            if (user.getUserName().equals(userLogin.getUserName()) && user.getPassword().equals(userLogin.getPassword())) {
                return ResultUtil.success();
            }
        }
        return ResultUtil.fail("登录失败，用户名或密码错误");
    }

    @Override
    public Response<Boolean> register(User user) {
        List<User> allUser = userDao.getAllUser();
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        if (allUser.isEmpty()) {
            user.setUserId(0);
            allUser.add(user);
            userDao.saveAll(allUser);
            return ResultUtil.success();
        }
        int maxId = Integer.MIN_VALUE;
        for (User userExist : allUser) {
            if (userExist.getUserName().equals(user.getUserName())) {
                return ResultUtil.fail("用户已存在");
            }
        }
        user.setUserId(maxId + 1);
        allUser.add(user);
        userDao.saveAll(allUser);
        return ResultUtil.success();
    }
}
