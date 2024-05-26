package com.group20.frontend.pages;

import com.group20.Response;
import com.group20.backend.model.User;
import com.group20.frontend.PageManagement;
import com.group20.utils.RequestUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

/**
 * login page
 *
 * @author Ni Xiang
 */
public class LoginPage extends DefaultPage {
    private final static String LOGIN_URL = "/user/login";
    private final static String REGISTER_URL = "/user/register";
    private static final int WIDTH = 400;
    private static final int HEIGHT = 600;
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JTextField ageField;
    private final JComboBox<String> roleBox;
    private final JTextField phoneField;
    private final JTextField emailField;
    private final JCheckBox registerCheck;
    private final JButton loginButton;
    private final JButton registerButton;

    public LoginPage() {
        super(WIDTH, HEIGHT);
        setLayout(new GridLayout(8, 2));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        ageField = new JTextField();
        roleBox = new JComboBox<>();
        roleBox.addItem("");
        roleBox.addItem("child");
        roleBox.addItem("parent");
        phoneField = new JTextField(1);
        emailField = new JTextField(1);
        registerCheck = new JCheckBox();
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        enableRegister(false);

        add(new JLabel("username:"));
        add(usernameField);
        add(new JLabel("password:"));
        add(passwordField);
        add(new Label("age:"));
        add(ageField);
        add(new Label("role:"));
        add(roleBox);
        add(new Label("phone:"));
        add(phoneField);
        add(new Label("email:"));
        add(emailField);
        add(new Label("Select to register"));
        add(registerCheck);
        add(loginButton);
        add(registerButton);

        registerCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean selected = registerCheck.isSelected();
                enableRegister(selected);
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                Response<User> request = RequestUtils.request(LOGIN_URL, new User(username, password));
                if (request.getCode()) {
                    JOptionPane.showMessageDialog(LoginPage.this, "Login Success!");
                    PageManagement.getInstance().setUserLogin(request.getData());
                    PageManagement.changePage(new MainPage());
                    LoginPage.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginPage.this, request.getMessage());
                }
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                String age = ageField.getText();
                Short role = null;
                String roleStr = (String) roleBox.getSelectedItem();
                if (roleStr == null) {
                    role = 0;
                } else if (roleStr.equals("child")) {
                    role = 0;
                } else {
                    role = 1;
                }
                String phone = phoneField.getText();
                String email = emailField.getText();
                if (isNotBlank(username, password, age, roleStr, phone, email)) {
                    Response<Boolean> request = RequestUtils.request(REGISTER_URL, new User(0, username, password,
                            Integer.valueOf(age), role, phone, email, LocalDateTime.now(),
                            LocalDateTime.now()));
                    if (request.getCode()) {
                        JOptionPane.showMessageDialog(LoginPage.this, "Register Success！");
                    } else {
                        JOptionPane.showMessageDialog(LoginPage.this, request.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(LoginPage.this, "Register failed！All fields must not be empty.");
                }
            }
        });
        this.setVisible(true);
    }

    private void enableRegister(boolean enable) {
        loginButton.setEnabled(!enable);
        ageField.setEnabled(enable);
        roleBox.setEnabled(enable);
        phoneField.setEnabled(enable);
        emailField.setEnabled(enable);
        registerButton.setEnabled(enable);
    }

    private static boolean isNotBlank(String... args) {
        for (String arg : args) {
            if (arg == null || arg.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
