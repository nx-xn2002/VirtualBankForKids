package com.group20.backend.controller;

import com.group20.Request;
import com.group20.Response;
import com.group20.utils.ResultUtil;

/**
 * base controller
 *
 * @author Ni Xiang
 */
public class BaseController implements Controller {
    private final BaseController baseController = new BaseController();
    private UserController userController;

    public BaseController getInstance() {
        return baseController;
    }

    @Override
    public Response requestHandler(Request request) {
        if (request.getUrl().contains("/user")) {
            if (userController == null) {
                userController = new UserController();
            }
            return userController.requestHandler(request);
        }
        return ResultUtil.fail("无效url");
    }
}
