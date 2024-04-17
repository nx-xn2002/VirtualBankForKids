package com.group20.utils;

import com.group20.Response;

/**
 * result util
 *
 * @author Ni Xiang
 */
public class ResultUtil {
    public static <T> Response<T> success(T data) {
        return new Response<>(data, "", 200);
    }

    public static <T> Response<T> success() {
        return new Response<>(null, "", 200);
    }

    public static <T> Response<T> fail(String message) {
        return new Response<>(null, message, 0);
    }
}
