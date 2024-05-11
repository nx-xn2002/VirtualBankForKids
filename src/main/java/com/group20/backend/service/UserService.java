package com.group20.backend.service;

import com.group20.Response;
import com.group20.backend.model.Relation;
import com.group20.backend.model.User;

import java.util.List;

/**
 * user service
 *
 * @author Ni Xiang
 */
public interface UserService {
    /**
     * login
     *
     * @param userLogin user login
     * @return {@link Response }<{@link Boolean }>
     * @author Ni Xiang
     */
    public Response<User> login(User userLogin);

    /**
     * register
     *
     * @param user user
     * @return {@link Response }<{@link Boolean }>
     * @author Ni Xiang
     */
    public Response<Boolean> register(User user);


    /**
     * select relation list by user
     *
     * @param user user
     * @return {@link Response }<{@link List }<{@link Relation }>>
     * @author Ni Xiang
     */
    public Response<List<Relation>> selectRelationListByUser(User user);

    /**
     * add relation
     *
     * @param parent parent
     * @param child  child
     * @return {@link Response }<{@link Boolean }>
     * @author Ni Xiang
     */
    public Response<Boolean> addRelation(User parent, User child);
}
