package com.group20.frontend.pages;

import javax.swing.*;
import java.awt.*;

/**
 * main page
 *
 * @author Ni Xiang
 */
public class MainPage extends DefaultPage{
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private final JPanel buttonContainer = new JPanel();
    public MainPage() {
        super(WIDTH, HEIGHT);
        setLayout(new BorderLayout());
        buttonContainer.setLayout(new GridLayout(1,3));
        buttonContainer.add(new Button("UserInfo"));
        buttonContainer.add(new Button("Account"));
        buttonContainer.add(new Button("Button3"));
        JPanel page = new UserInfoPage();
        add(new JPanel());
        add(buttonContainer,BorderLayout.NORTH);
        add(page,BorderLayout.CENTER);
        this.setVisible(true);
    }
}
