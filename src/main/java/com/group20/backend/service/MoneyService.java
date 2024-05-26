package com.group20.backend.service;

import com.group20.Response;
import com.group20.backend.model.Money;

/**
 * money service
 *
 * @author Ni Xiang
 */
public interface MoneyService {
    /**
     * change
     *
     * @param money money
     * @return {@link Response }<{@link Boolean }>
     * @author Ni Xiang
     */
    public Response<Boolean> change(Money money);
}
