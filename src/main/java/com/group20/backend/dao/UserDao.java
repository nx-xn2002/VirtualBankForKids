package com.group20.backend.dao;

import com.group20.backend.model.User;

import java.time.LocalDateTime;
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
            users.add((User) userObject);
        }
        return users;
    }

    public boolean update(User user) {
        if (user.getUsername() == null) {
            return false;
        }
        List<User> allUser = getAllUser();
        User userToUpdate = null;
        int index = -1;
        for (int i = 0; i < allUser.size(); i++) {
            if (allUser.get(i).getUsername().equals(user.getUsername())) {
                userToUpdate = allUser.get(i);
                index = i;
                break;
            }
        }
        if (index == -1) {
            return false;
        }
        if (user.getPassword() != null) {
            userToUpdate.setPassword(user.getPassword());
        }
        if (user.getAge() != null) {
            userToUpdate.setAge(user.getAge());
        }
        if (user.getEmail() != null) {
            userToUpdate.setEmail(user.getEmail());
        }
        if (user.getPhone() != null) {
            userToUpdate.setPhone(user.getPhone());
        }
        userToUpdate.setUpdateTime(LocalDateTime.now());
        allUser.set(index, userToUpdate);
        saveAll(allUser);
        return true;
    }
}
