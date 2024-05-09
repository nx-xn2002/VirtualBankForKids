package com.group20.backend.controller;

import com.group20.Request;
import com.group20.Response;
import com.group20.backend.model.User;
import com.group20.backend.service.UserService;
import com.group20.backend.service.UserServiceImpl;
import com.group20.utils.ResultUtil;

/**
 * user controller
 *
 * @author Ni Xiang
 */
public class UserController implements Controller {
    private final UserService userService = new UserServiceImpl();

    @Override
    public Response requestHandler(Request request) {
        String url = request.getUrl();
        if (url.contains("login")) {
            return userService.login((User) request.getBody());
        }else if (url.contains("register")) {
            return userService.register((User) request.getBody());
        }
        return ResultUtil.fail("无效url");
    }
}
