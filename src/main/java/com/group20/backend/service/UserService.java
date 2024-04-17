package com.group20.backend.service;

import com.group20.Response;
import com.group20.backend.model.User;

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
     * @return {@link Response }<{@link Boolean }>
     * @author Ni Xiang
     */
    public Response<Boolean> login(User userLogin);

    /**
     * register
     *
     * @param user user
     * @return {@link Response }<{@link Boolean }>
     * @author Ni Xiang
     */
    public Response<Boolean> register(User user);
}
