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
    private final static BaseController BASE_CONTROLLER = new BaseController();
    private UserController userController;

    public static BaseController getInstance() {
        return BASE_CONTROLLER;
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
