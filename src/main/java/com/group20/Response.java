package com.group20;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * response
 *
 * @author Ni Xiang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private T data;
    private String message;
    private Boolean code;
}
