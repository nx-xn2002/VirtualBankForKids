package com.group20.backend.service;

import com.group20.Response;
import com.group20.backend.model.Task;
import com.group20.backend.model.User;

import java.util.List;

/**
 * task service
 *
 * @author Ni Xiang
 */
public interface TaskService {
    /**
     * select task by user
     *
     * @param user user
     * @return {@link Response }<{@link List }<{@link Task }>>
     * @author Ni Xiang
     */
    public Response<List<Task>> selectTaskByUser(User user);

    /**
     * create task
     *
     * @param task task
     * @return {@link Response }<{@link Boolean }>
     * @author Ni Xiang
     */
    public Response<Boolean> createTask(Task task);

    /**
     * finish task
     *
     * @param taskId task id
     * @return {@link Response }<{@link Boolean }>
     * @author Ni Xiang
     */
    public Response<Boolean> finishTask(Integer taskId);
}
