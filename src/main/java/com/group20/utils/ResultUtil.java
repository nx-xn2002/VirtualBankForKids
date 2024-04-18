package com.group20.utils;

import com.group20.Response;

/**
 * result util
 *
 * @author Ni Xiang
 */
public class ResultUtil {
    public static <T> Response<T> success(T data) {
        return new Response<>(data, "", true);
    }

    public static <T> Response<T> success() {
        return new Response<>(null, "", true);
    }

    public static <T> Response<T> success(String message) {
        return new Response<>(null, message, true);
    }

    public static <T> Response<T> fail(String message) {
        return new Response<>(null, message, false);
    }
}
