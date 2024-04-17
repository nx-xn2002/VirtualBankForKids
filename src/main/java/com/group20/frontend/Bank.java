package com.group20.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Bank {
    // 存储所有账户的哈希表
    private HashMap<String, Account> accounts;

    // GUI组件
    private JFrame frame;
    private JButton createAccountButton, depositButton, withdrawButton, viewBalanceButton;

    public Bank() {
        accounts = new HashMap<>(); // 初始化哈希表

        // 初始化GUI组件
        frame = new JFrame("Virtual Bank Application for Kids");
        createAccountButton = new JButton("Create Account");
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        viewBalanceButton = new JButton("View Balance");

        // 添加事件监听器
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里调用createAccount方法
            }
        });
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AccountWindow(); // 创建并打开新的窗口
            }
        });
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里为选定的账户调用deposit方法
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里为选定的账户调用withdraw方法
            }
        });

        viewBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里为选定的账户调用trackBalance方法
            }
        });

        // 将按钮添加到frame中
        frame.add(createAccountButton);
        frame.add(depositButton);
        frame.add(withdrawButton);
        frame.add(viewBalanceButton);

        // 设置frame属性
        frame.setSize(800, 500);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // 创建账户的方法
    public void createAccount(String accountName) {
        if (!accounts.containsKey(accountName)) {
            accounts.put(accountName, new Account(accountName)); // 如果账户名未被使用，则创建新的账户
        } else {
            System.out.println("Account already exists!"); // 如果账户名已被使用，则打印错误消息
        }
    }

    // 查看账户余额的方法
    public void trackBalance(String accountName) {
        if (accounts.containsKey(accountName)) {
            System.out.println("Balance: " + accounts.get(accountName).getBalance()); // 如果账户存在，打印余额
        } else {
            System.out.println("Account not found!"); // 如果账户不存在，打印错误消息
        }
    }

    // 存款方法
    public void deposit(String accountName, double amount) {
        if (accounts.containsKey(accountName)) {
            accounts.get(accountName).deposit(amount); // 如果账户存在，进行存款
        } else {
            System.out.println("Account not found!"); // 如果账户不存在，打印错误消息
        }
    }

    // 取款方法
    public void withdraw(String accountName, double amount) {
        if (accounts.containsKey(accountName)) {
            accounts.get(accountName).withdraw(amount); // 如果账户存在，进行取款
        } else {
            System.out.println("Account not found!"); // 如果账户不存在，打印错误消息
        }
    }
    class AccountWindow {
        private JFrame frame;
        private JButton registerButton, loginButton, depositButton, withdrawButton;
        private JTextField nameField, amountField;
        private JLabel balanceLabel;

        public AccountWindow() {
            frame = new JFrame("Account Window");
            registerButton = new JButton("Register");
            loginButton = new JButton("Login");
            depositButton = new JButton("Deposit");
            withdrawButton = new JButton("Withdraw");
            nameField = new JTextField(20); // 输入名字的文本框，长度为20
            amountField = new JTextField(20); // 输入金额的文本框，长度为20
            balanceLabel = new JLabel("Balance: 0"); // 初始余额为0

            // 添加事件监听器
            registerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText(); // 获取输入的名字
                    createAccount(name); // 调用createAccount方法创建新的账户
                }
            });

            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    trackBalance(name); // 调用trackBalance方法查看账户余额
                    // 更新余额标签
                    balanceLabel.setText("Balance: " + accounts.get(name).getBalance());
                }
            });

            depositButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    double amount = Double.parseDouble(amountField.getText());
                    deposit(name, amount); // 调用deposit方法进行存款
                    // 更新余额标签
                    balanceLabel.setText("Balance: " + accounts.get(name).getBalance());
                }
            });

            withdrawButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    double amount = Double.parseDouble(amountField.getText());
                    withdraw(name, amount); // 调用withdraw方法进行取款
                    // 更新余额标签
                    balanceLabel.setText("Balance: " + accounts.get(name).getBalance());
                }
            });

            // 将按钮和文本框添加到frame中
            frame.add(nameField);
            frame.add(amountField);
            frame.add(registerButton);
            frame.add(loginButton);
            frame.add(depositButton);
            frame.add(withdrawButton);
            frame.add(balanceLabel);

            // 设置frame属性
            frame.setSize(300, 200);
            frame.setLayout(new FlowLayout());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        }
    }
    public static void main(String[] args) {
        new Bank(); // 创建Bank对象，启动应用
    }
}