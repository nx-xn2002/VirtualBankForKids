package com.group20.frontend.pages;

import com.group20.frontend.PageManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * main page
 *
 * @author Ni Xiang
 */
public class MainPage extends DefaultPage {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private final JPanel buttonContainer = new JPanel();

    public MainPage() {
        super(WIDTH, HEIGHT);
        init();
        add(new UserInfoPage(), BorderLayout.CENTER);
        this.setVisible(true);
    }

    public MainPage(JPanel containPage) {
        super(WIDTH, HEIGHT);
        init();
        add(containPage, BorderLayout.CENTER);
        this.setVisible(true);
    }

    private void init() {
        setLayout(new BorderLayout());
        buttonContainer.setLayout(new GridLayout(1, 5));
        JButton userInfoButton = new JButton("User Info");
        userInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PageManagement.changePage(new MainPage(new UserInfoPage()));
            }
        });
        JButton accountButton = new JButton("Account");
        accountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PageManagement.changePage(new MainPage(new AccountPage()));
            }
        });
        JButton moneyButton = new JButton("Money");
        moneyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PageManagement.changePage(new MainPage(new MoneyPage()));
            }
        });
        JButton taskButton = new JButton("Task");
        taskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PageManagement.changePage(new MainPage(new TaskPage()));
            }
        });
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PageManagement.changePage(new LoginPage());
            }
        });
        buttonContainer.add(userInfoButton);
        buttonContainer.add(accountButton);
        buttonContainer.add(moneyButton);
        buttonContainer.add(taskButton);
        buttonContainer.add(logoutButton);
        add(new JPanel());
        add(buttonContainer, BorderLayout.NORTH);
    }
}
