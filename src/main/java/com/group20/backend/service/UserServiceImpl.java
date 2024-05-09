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
    public Response<User> login(User userLogin) {
        List<User> allUser = userDao.getAllUser();
        System.out.println("尝试登录："+userLogin.getUsername()+" "+userLogin.getPassword());
        for (User user : allUser) {
            System.out.println(user.getUsername()+" "+user.getPassword());
            if (user.getUsername().equals(userLogin.getUsername()) && user.getPassword().equals(userLogin.getPassword())) {
                return ResultUtil.success(user);
            }
        }
        return ResultUtil.fail("Login failed, incorrect username or password");
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
            if (userExist.getUsername().equals(user.getUsername())) {
                return ResultUtil.fail("User already exists");
            }
        }
        user.setUserId(maxId + 1);
        allUser.add(user);
        userDao.saveAll(allUser);
        return ResultUtil.success();
    }
}
