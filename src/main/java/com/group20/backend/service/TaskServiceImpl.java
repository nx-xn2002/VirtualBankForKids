package com.group20.backend.service;

import com.group20.Response;
import com.group20.backend.dao.DaoManage;
import com.group20.backend.dao.RelationDao;
import com.group20.backend.dao.TaskDao;
import com.group20.backend.dao.UserDao;
import com.group20.backend.model.Relation;
import com.group20.backend.model.Task;
import com.group20.backend.model.User;
import com.group20.utils.ResultUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * task service impl
 *
 * @author Ni Xiang
 */
public class TaskServiceImpl implements TaskService {
    private final TaskDao taskDao = DaoManage.getTaskDaoInstance();
    private final RelationDao relationDao = DaoManage.getRelationDaoInstance();

    @Override
    public Response<List<Task>> selectTaskByUser(User user) {
        if (user.getUserId() == null || user.getRole() == null) {
            return ResultUtil.fail("no such user");
        }
        List<Task> allTask = taskDao.getAllTask();
        List<Task> resTaskList = new ArrayList<>();
        for (Task task : allTask) {
            if (user.getRole().equals(User.CHILD)) {
                if (task.getChildId().equals(user.getUserId())) {
                    resTaskList.add(task);
                }
            } else if (user.getRole().equals(User.PARENT)) {
                if (task.getParentId().equals(user.getUserId())) {
                    resTaskList.add(task);
                }
            }
        }
        return ResultUtil.success(resTaskList);
    }

    @Override
    public Response<Boolean> createTask(Task task) {
        if (task.getChildId() == null || task.getParentId() == null || task.getDescription() == null) {
            String error = "Null Args[";
            error += task.getChildId() == null ? " childId " : "";
            error += task.getParentId() == null ? " parentId " : "";
            error += task.getDescription() == null ? " description " : "";
            error += "]";
            return ResultUtil.fail(error);
        }
        List<Relation> allRelation = relationDao.getAllRelation();
        boolean relationValid = false;
        for (Relation relation : allRelation) {
            if (relation.getChildId().equals(task.getChildId()) && relation.getParentId().equals(task.getParentId())) {
                relationValid = true;
                break;
            }
        }
        if (!relationValid) {
            return ResultUtil.fail("You can only assign tasks to your children.");
        }
        StringBuilder sb = new StringBuilder();
        String description = task.getDescription();
        for (int i = 0; i < description.length(); i++) {
            if (description.charAt(i) == ',') {
                sb.append(' ');
                continue;
            }
            sb.append(description.charAt(i));
        }
        task.setDescription(sb.toString());
        taskDao.add(task);
        return ResultUtil.success(true);
    }

    @Override
    public Response<Boolean> finishTask(Integer taskId) {
        if (taskId == null) {
            return ResultUtil.fail("Null task id");
        }
        List<Task> allTask = taskDao.getAllTask();
        boolean validTaskId = false;
        for (Task task : allTask) {
            if (task.getTaskId().equals(taskId)) {
                validTaskId = true;
                task.setIsFinished(true);
                break;
            }
        }
        if (!validTaskId) {
            return ResultUtil.fail("No such task");
        }
        taskDao.saveAll(allTask);
        return ResultUtil.success(true);
    }
}
