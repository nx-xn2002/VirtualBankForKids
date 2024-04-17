package com.group20.service;

import com.group20.model.User;

/**
 * user service
 *
 * @author Ni Xiang
 */
public interface UserService {
    /**
     * login
     *
     * @param userLogin user login
     * @return {@link Boolean }
     * @author Ni Xiang
     */
    public Boolean login(User userLogin);
}
