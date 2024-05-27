package com.group20.backend.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * task
 *
 * @author Ni Xiang
 */
@Data
public class Task {
    /**
     * data type
     */
    public final static String DATA_TYPE = "com.group20.backend.model.Task";
    /**
     * task id
     */
    private Integer taskId;
    /**
     * description
     */
    private String description;
    /**
     * is finished
     */
    private Boolean isFinished;
    /**
     * parent id
     */
    private Integer parentId;
    /**
     * child id
     */
    private Integer childId;
    /**
     * create time
     */
    private LocalDateTime createTime;
    /**
     * update time
     */
    private LocalDateTime updateTime;

    public Task() {
    }

    public Task(List<String> list) {
        taskId = Integer.parseInt(list.get(0));
        description = list.get(1);
        isFinished = Boolean.parseBoolean(list.get(2));
        parentId = Integer.parseInt(list.get(3));
        childId = Integer.parseInt(list.get(4));
        createTime = LocalDateTime.parse(list.get(5));
        updateTime = LocalDateTime.parse(list.get(6));
    }

    @Override
    public String toString() {
        return taskId +
                "," + description +
                "," + isFinished +
                "," + parentId +
                "," + childId +
                "," + createTime +
                "," + updateTime;
    }
}
