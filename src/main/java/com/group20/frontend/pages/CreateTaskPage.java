package com.group20.frontend.pages;

import com.group20.Response;
import com.group20.backend.model.Relation;
import com.group20.backend.model.Task;
import com.group20.backend.model.User;
import com.group20.frontend.PageManagement;
import com.group20.utils.RequestUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * add task page
 *
 * @author Ni Xiang
 */
public class CreateTaskPage extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 150;
    private static final String TITLE = "Create Task";
    private static final String CREATE_TASK = "/user/createTask";
    private final static String SELECT_RELATION = "/user/selectRelation";
    private final static String SELECT_USER_BY_ID = "/user/selectUserById";

    public CreateTaskPage() {
        super(TITLE);
        setSize(WIDTH, HEIGHT);
        showInTheCenter(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        componentInit();
        setVisible(true);
    }

    private void componentInit() {
        setLayout(new GridLayout(3, 1));
        //description
        //child name
        //createButton
        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new GridLayout(1, 2));
        jPanel1.add(new JLabel("Task Description"));
        JTextField description = new JTextField();
        jPanel1.add(description);

        JPanel jPanel2 = new JPanel();
        jPanel2.setLayout(new GridLayout(1, 2));
        jPanel2.add(new JLabel("Child Name"));
        JTextField childName = new JTextField();
        jPanel2.add(childName);

        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task task = new Task();
                task.setParentId(PageManagement.getInstance().getUserLogin().getUserId());
                task.setDescription(description.getText());
                String childNameStr = childName.getText();
                Response<List<Relation>> response = RequestUtils.request(SELECT_RELATION,
                        PageManagement.getInstance().getUserLogin());
                Integer childId = null;
                if (response.getCode()) {
                    for (Relation relation : response.getData()) {
                        Response childResponse = RequestUtils.request(SELECT_USER_BY_ID, relation.getChildId());
                        System.out.println(childResponse.getData());
                        if (childResponse.getData() != null) {
                            User child = (User) childResponse.getData();
                            if (child.getUsername().equals(childNameStr)) {
                                childId = child.getUserId();
                            }
                        }
                    }
                }
                task.setChildId(childId);
                Response<Boolean> createResponse = RequestUtils.request(CREATE_TASK, task);
                if (createResponse.getCode()) {
                    JOptionPane.showMessageDialog(CreateTaskPage.this, "Create Success!");
                    PageManagement.changePage(new MainPage(new TaskPage()));
                    CreateTaskPage.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(CreateTaskPage.this, "Create Error." + createResponse.getMessage());
                }
            }
        });

        add(jPanel1);

        add(jPanel2);

        add(createButton);
    }

    private void showInTheCenter(int widthFrame, int heightFrame) {
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = defaultToolkit.getScreenSize();
        double widthScreen = screenSize.getWidth();
        double heightScreen = screenSize.getHeight();
        if (widthFrame > widthScreen) {
            widthFrame = (int) widthScreen;
        }
        if (heightFrame > heightScreen) {
            heightFrame = (int) heightScreen;
        }
        int positionX = (int) ((widthScreen - widthFrame) / 2);
        int positionY = (int) ((heightScreen - heightFrame) / 2);
        setLocation(new Point(positionX, positionY));
    }
}
