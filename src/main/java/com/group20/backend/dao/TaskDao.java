package com.group20.backend.dao;

import com.group20.backend.model.Money;
import com.group20.backend.model.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * task dao
 *
 * @author Ni Xiang
 */
public class TaskDao extends BaseDao<Task> {
    public TaskDao(String path) {
        super(Task.DATA_TYPE, path);
    }

    public List<Task> getAllTask() {
        List<Object> list = super.getAll();
        List<Task> taskList = new ArrayList<>();
        for (Object o : list) {
            taskList.add((Task) o);
        }
        return taskList;
    }

    public void add(Task task) {
        List<Task> allTask = getAllTask();
        int max = -1;
        for (Task taskExist : allTask) {
            max = Math.max(max, taskExist.getTaskId());
        }
        task.setIsFinished(false);
        task.setCreateTime(LocalDateTime.now());
        task.setUpdateTime(LocalDateTime.now());
        task.setTaskId(max + 1);
        allTask.add(task);
        saveAll(allTask);
    }
}
