package com.group20.backend.service;

import com.group20.Response;
import com.group20.backend.model.Money;

import java.util.List;

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

    /**
     * get money list by user id
     *
     * @param userId user id
     * @return {@link Response }<{@link List }<{@link Money }>>
     * @author Ni Xiang
     */
    public Response<List<Money>> getMoneyListByUserId(Integer userId);
}
