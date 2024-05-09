package com.group20.frontend.pages;

import com.group20.frontend.PageManagement;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

/**
 * user page
 *
 * @author Ni Xiang
 */
public class UserInfoPage extends JPanel {
    public UserInfoPage() {
        setLayout(new GridLayout(6, 1));
        add(new JLabel("username:" + PageManagement.getInstance().getUserLogin().getUsername()));
        add(new JLabel("age:" + PageManagement.getInstance().getUserLogin().getAge()));
        add(new JLabel("role:" + (PageManagement.getInstance().getUserLogin().getRole() == 0 ? "Child" : "Parent")));
        add(new JLabel("email:" + PageManagement.getInstance().getUserLogin().getEmail()));
        add(new JLabel("phone:" + PageManagement.getInstance().getUserLogin().getPhone()));
        add(new JLabel("register date:" + PageManagement.getInstance().getUserLogin().getCreateTime().format(DateTimeFormatter.ISO_DATE)));
    }
}
