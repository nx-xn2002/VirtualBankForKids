package com.group20.backend.service;

import com.group20.Response;
import com.group20.backend.model.Account;
import com.group20.backend.model.User;

import java.util.List;

/**
 * account service
 *
 * @author Ni Xiang
 */
public interface AccountService {
    /**
     * create account
     *
     * @param account account
     * @return {@link Response }<{@link Boolean }>
     * @author Ni Xiang
     */
    public Response<Boolean> createAccount(Account account);

    /**
     * select account by user id
     *
     * @param userId user id
     * @return {@link Response }<{@link Account[] }>
     * @author Ni Xiang
     */
    public Response<List<Account>> selectAccountByUserId(Integer userId);

    /**
     * remove account
     *
     * @param account account
     * @return {@link Response }<{@link Boolean }>
     * @author Ni Xiang
     */
    public Response<Boolean> removeAccount(Account account);
}
