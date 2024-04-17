package com.group20.backend.dao;

import com.group20.backend.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * user dao
 *
 * @author Ni Xiang
 */
public class UserDao extends BaseDao<User> {
    public UserDao(String path) {
        super(User.DATA_TYPE, path);
    }

    public List<User> getAllUser() {
        List<Object> userObjects = super.getAll();
        List<User> users = new ArrayList<>();
        for (Object userObject : userObjects) {
            users.add((User) userObjects);
        }
        return users;
    }
}
