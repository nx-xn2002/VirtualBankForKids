package com.group20.frontend.pages;

import com.group20.frontend.PageManagement;

import javax.swing.*;
import java.awt.*;

/**
 * user page
 *
 * @author Ni Xiang
 */
public class UserPage extends JPanel {
    public UserPage(){
        setLayout(new GridLayout(3,1));
        add(new JLabel("username:"+PageManagement.getInstance().getUserLogin().getUsername()));
        add(new JLabel("email:"+PageManagement.getInstance().getUserLogin().getEmail()));
        add(new JLabel("phone:"+PageManagement.getInstance().getUserLogin().getPhone()));
    }
}
