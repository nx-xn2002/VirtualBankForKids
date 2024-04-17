package com.group20;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * request
 *
 * @author Ni Xiang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request<T> {
    /**
     * request url
     */
    private String url;
    /**
     * request body
     */
    private T body;
}
