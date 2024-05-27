package com.group20.frontend.pages;

import com.group20.Response;
import com.group20.backend.model.Task;
import com.group20.backend.model.User;
import com.group20.frontend.PageManagement;
import com.group20.frontend.components.AccountTableModel;
import com.group20.utils.RequestUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

/**
 * task page
 *
 * @author Ni Xiang
 */
public class TaskPage extends JPanel {
    private final static String SELECT_TASK = "/user/selectTask";

    public TaskPage() {
        setLayout(new BorderLayout());
        add(new JLabel("Tasks"), BorderLayout.NORTH);
        Response<List<Task>> response = RequestUtils.request(SELECT_TASK,
                PageManagement.getInstance().getUserLogin());
        List<Task> taskList = response.getData();
        JTable taskInfo;
        if (taskList == null || taskList.isEmpty()) {
            String[][] datas = {};
            String[] title = {"key", "value"};
            DefaultTableModel tableModel = new AccountTableModel(datas, title);
            taskInfo = new JTable(tableModel);
            tableModel.addRow(new String[]{"No tasks found"});
            add(taskInfo, BorderLayout.CENTER);
        } else {
            String[][] datas = {};
            String[] title = {"index", "task id", "description", "Finished", "parent ID", "child ID", "create time",
                    "update time"};
            DefaultTableModel tableModel = new AccountTableModel(datas, title);
            taskInfo = new JTable(tableModel);
            int index = 1;
            for (Task task : taskList) {
                if (task.getIsFinished()) {
                    continue;
                }
                Integer taskId = task.getTaskId();
                String description = task.getDescription();
                Boolean isFinished = task.getIsFinished();
                Integer parentId = task.getParentId();
                Integer childId = task.getChildId();
                LocalDateTime createTime = task.getCreateTime();
                LocalDateTime updateTime = task.getUpdateTime();
                tableModel.addRow(
                        new String[]{String.valueOf(index++),
                                taskId.toString(),
                                description,
                                isFinished.toString(),
                                parentId.toString(),
                                childId.toString(),
                                createTime.toString(),
                                updateTime.toString()
                        });
            }
            for (Task task : taskList) {
                if (!task.getIsFinished()) {
                    continue;
                }
                Integer taskId = task.getTaskId();
                String description = task.getDescription();
                Boolean isFinished = task.getIsFinished();
                Integer parentId = task.getParentId();
                Integer childId = task.getChildId();
                LocalDateTime createTime = task.getCreateTime();
                LocalDateTime updateTime = task.getUpdateTime();
                tableModel.addRow(
                        new String[]{String.valueOf(index++),
                                taskId.toString(),
                                description,
                                isFinished.toString(),
                                parentId.toString(),
                                childId.toString(),
                                createTime.toString(),
                                updateTime.toString()
                        });
            }
            JScrollPane scrollPane = new JScrollPane(taskInfo);
            add(scrollPane, BorderLayout.CENTER);
        }
        JPanel southPanel = new JPanel();
        JButton createTaskButton = new JButton("Create Task");
        createTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateTaskPage();
            }
        });
        JButton finishTaskButton = new JButton("Finish Task");
        finishTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FinishTaskPage();
            }
        });
        if (PageManagement.getInstance().getUserLogin().getRole().equals(User.PARENT)) {
            southPanel.setLayout(new GridLayout(1, 2));
            southPanel.add(createTaskButton);
            southPanel.add(finishTaskButton);
        } else {
            southPanel.add(finishTaskButton);
        }
        add(southPanel, BorderLayout.SOUTH);
    }
}
