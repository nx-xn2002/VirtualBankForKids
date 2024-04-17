package com.group20.frontend.pages;

import javax.swing.*;

/**
 * login page
 *
 * @author Ni Xiang
 */
public class LoginPage extends DefaultPage {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 600;
    private JLabel jLabel = new JLabel("Virtual Bank For Kids - Group 20"+"Login");
    public LoginPage() {
        super(WIDTH, HEIGHT);
        this.add(jLabel);
        this.setVisible(true);
    }
}
