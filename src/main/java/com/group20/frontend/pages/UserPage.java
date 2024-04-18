package com.group20.frontend.pages;

import com.group20.backend.model.User;
import com.group20.frontend.PageManagement;

import javax.swing.*;

/**
 * user page
 *
 * @author Ni Xiang
 */
public class UserPage extends JPanel {
    public UserPage(){
        add(new JLabel(PageManagement.getInstance().getUserLogin().toString()));
    }
}
