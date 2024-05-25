package com.group20.frontend.pages;

import com.group20.Request;
import com.group20.Response;
import com.group20.backend.model.Relation;
import com.group20.backend.model.User;
import com.group20.frontend.PageManagement;
import com.group20.frontend.components.UserInfoTableModel;
import com.group20.utils.RequestUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * user page
 *
 * @author Ni Xiang
 */
public class UserInfoPage extends JPanel {
    private final static String SELECT_RELATION = "/user/selectRelation";
    private final static String SELECT_USER_BY_ID = "/user/selectUserById";
    private final static String UPDATE_USER = "/user/updateUser";

    public UserInfoPage() {
        setLayout(new BorderLayout());
        add(new JLabel("User Info"), BorderLayout.NORTH);
        String[][] datas = {};
        String[] title = {"key", "value"};
        DefaultTableModel tableModel = new UserInfoTableModel(datas, title);

        Response<List<Relation>> response = RequestUtils.request(SELECT_RELATION,
                PageManagement.getInstance().getUserLogin());
        JTable userInfo = new JTable(tableModel);
        tableModel.addRow(new String[]{});
        tableModel.addRow(new String[]{"username", PageManagement.getInstance().getUserLogin().getUsername()});
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < PageManagement.getInstance().getUserLogin().getPassword().length(); i++) {
            password.append("*");
        }
        tableModel.addRow(new String[]{"password", password.toString()});
        tableModel.addRow(new String[]{"age", PageManagement.getInstance().getUserLogin().getAge().toString()});
        tableModel.addRow(new String[]{"role",
                (PageManagement.getInstance().getUserLogin().getRole().equals(User.CHILD) ?
                        "Child" : "Parent")});
        tableModel.addRow(new String[]{"email", PageManagement.getInstance().getUserLogin().getEmail()});
        tableModel.addRow(new String[]{"phone", PageManagement.getInstance().getUserLogin().getPhone()});
        tableModel.addRow(new String[]{"register date",
                PageManagement.getInstance().getUserLogin().getCreateTime().format(DateTimeFormatter.ISO_DATE)});
        if (PageManagement.getInstance().getUserLogin().getRole().equals(User.CHILD)) {
            for (int i = 0; i < response.getData().size(); i++) {
                Integer parentId = response.getData().get(i).getParentId();
                Response parentResponse = RequestUtils.request(SELECT_USER_BY_ID, parentId);
                if (parentResponse.getData() != null) {
                    User parent = (User) parentResponse.getData();
                    tableModel.addRow(new String[]{"Parent" + (i + 1), parent.getUsername()});
                }
            }
        }
        if (PageManagement.getInstance().getUserLogin().getRole().equals(User.PARENT)) {
            for (int i = 0; i < response.getData().size(); i++) {
                Integer childId = response.getData().get(i).getChildId();
                Response childResponse = RequestUtils.request(SELECT_USER_BY_ID, childId);
                if (childResponse.getData() != null) {
                    User child = (User) childResponse.getData();
                    tableModel.addRow(new String[]{"Child" + (i + 1), child.getUsername()});
                }
            }
        }
        tableModel.addRow(new String[]{});

        add(userInfo, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        JButton editInfoButton = new JButton("Update Info");
        editInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = (String) userInfo.getValueAt(2, 1);
                String age = (String) userInfo.getValueAt(3, 1);
                String email = (String) userInfo.getValueAt(5, 1);
                String phone = (String) userInfo.getValueAt(6, 1);
                User userUpdate = PageManagement.getInstance().getUserLogin();
                System.out.println(userUpdate);
                if (!password.contains("*")) {
                    userUpdate.setPassword(password);
                }
                userUpdate.setAge(Integer.parseInt(age));
                userUpdate.setEmail(email);
                userUpdate.setPhone(phone);
                Response<Boolean> request = RequestUtils.request(UPDATE_USER, userUpdate);
                if (request.getCode()) {
                    JOptionPane.showMessageDialog(UserInfoPage.this, "Update Success!");
                    PageManagement.changePage(new MainPage());
                } else {
                    JOptionPane.showMessageDialog(UserInfoPage.this, "Update Fail!");
                }
            }
        });
        JButton editRelationButton = new JButton("Manage Relation");
        editRelationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RelationEditPage();
            }
        });
        buttonPanel.add(editInfoButton);
        buttonPanel.add(editRelationButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}

