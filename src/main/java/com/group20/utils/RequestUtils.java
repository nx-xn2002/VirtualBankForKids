package com.group20.utils;

import com.group20.Request;
import com.group20.Response;
import com.group20.backend.controller.BaseController;

/**
 * request controller
 *
 * @author Ni Xiang
 */
public class RequestUtils {
    public static Response request(String url, Object body) {
        return BaseController.getInstance().requestHandler(new Request<>(url, body));
    }
}
