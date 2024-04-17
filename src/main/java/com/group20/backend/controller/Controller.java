package com.group20.backend.controller;

import com.group20.Request;
import com.group20.Response;

/**
 * controller interface
 *
 * @author Ni Xiang
 */
public interface Controller {
    /**
     * request handler
     *
     * @param request request
     * @return {@link Response }<{@link T }>
     * @author Ni Xiang
     */
    public <T> Response<T> requestHandler(Request<T> request);
}
