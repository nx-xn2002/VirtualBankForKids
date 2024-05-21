package com.group20.backend.service;

import com.group20.Response;
import com.group20.backend.dao.DaoManage;
import com.group20.backend.dao.RelationDao;
import com.group20.backend.dao.UserDao;
import com.group20.backend.model.Relation;
import com.group20.backend.model.User;
import com.group20.utils.ResultUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * user service impl
 *
 * @author Ni Xiang
 */
public class UserServiceImpl implements UserService {
    private final UserDao userDao = DaoManage.getUserDaoInstance();
    private final RelationDao relationDao = DaoManage.getRelationDaoInstance();

    @Override
    public Response<User> login(User userLogin) {
        List<User> allUser = userDao.getAllUser();
        System.out.println("尝试登录：" + userLogin.getUsername() + " " + userLogin.getPassword());
        for (User user : allUser) {
            System.out.println(user.getUsername() + " " + user.getPassword());
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
            maxId = Math.max(maxId, userExist.getUserId());
        }
        user.setUserId(maxId + 1);
        allUser.add(user);
        userDao.saveAll(allUser);
        return ResultUtil.success();
    }

    @Override
    public Response<List<Relation>> selectRelationListByUser(User user) {
        if (user.getUserId() == null || user.getRole() == null) {
            return ResultUtil.fail("System error, userId or user role is null");
        }
        List<Relation> relationList = new ArrayList<>();
        List<Object> allRelationList = relationDao.getAll();
        for (Object o : allRelationList) {
            Relation relation = (Relation) o;
            //if the user is a child
            if (user.getRole() == 0 && relation.getChildId().equals(user.getUserId())) {
                relationList.add(relation);
            }
            //if the user is a parent
            if (user.getRole() == 1 && relation.getParentId().equals(user.getUserId())) {
                relationList.add(relation);
            }
        }
        return ResultUtil.success(relationList);
    }

    @Override
    public Response<Boolean> addRelation(User parent, User child) {
        List<User> allUser = userDao.getAllUser();
        boolean parentValid = false, childValid = false;
        for (User userExist : allUser) {
            if (userExist.getUsername().equals(parent.getUsername()) && userExist.getRole().equals(User.PARENT)) {
                parent.setUserId(userExist.getUserId());
                parentValid = true;
            }
            if (userExist.getUsername().equals(child.getUsername()) && userExist.getRole().equals(User.CHILD)) {
                child.setUserId(userExist.getUserId());
                childValid = true;
            }
        }
        if (!parentValid || !childValid) {
            return ResultUtil.fail("role is not valid or user is not exist!");
        }
        List<Object> allRelationList = relationDao.getAll();
        int maxId = Integer.MIN_VALUE;
        //relation should be distinct
        for (Object o : allRelationList) {
            Relation relation = (Relation) o;
            maxId = Math.max(relation.getRelationId(), maxId);
            if (relation.getParentId().equals(parent.getUserId()) && relation.getChildId().equals(child.getUserId())) {
                return ResultUtil.fail("relation already exists");
            }
        }
        allRelationList.add(new Relation(maxId + 1, parent.getUserId(), child.getUserId(), LocalDateTime.now(),
                LocalDateTime.now()));
        return ResultUtil.success();
    }

    @Override
    public Response<User> selectUserByUserId(Integer userId) {
        List<User> allUser = userDao.getAllUser();
        for (User user : allUser) {
            if (user.getUserId().equals(userId)) {
                return ResultUtil.success(user);
            }
        }
        return ResultUtil.fail("no such user");
    }
}
