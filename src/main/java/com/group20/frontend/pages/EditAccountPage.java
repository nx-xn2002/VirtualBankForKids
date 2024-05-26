package com.group20.frontend.pages;

import com.group20.Response;
import com.group20.backend.model.Account;
import com.group20.backend.model.User;
import com.group20.frontend.PageManagement;
import com.group20.utils.RequestUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * add account page
 *
 * @author Ni Xiang
 */
public class EditAccountPage extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 150;
    private static final String TITLE = "Add Account";
    private final static String LOGIN_URL = "/user/login";
    private final static String CREATE_ACCOUNT = "/account/createAccount";
    private final static String SELECT_ACCOUNT = "/account/selectAccount";
    private final static String REMOVE_ACCOUNT = "/account/removeAccount";

    public EditAccountPage() {
        super(TITLE);
        setSize(WIDTH, HEIGHT);
        showInTheCenter(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        componentInit();
        setVisible(true);
    }

    private void componentInit() {
        setLayout(new GridLayout(4, 1));
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        panel1.setLayout(new GridLayout(1, 2));
        panel2.setLayout(new GridLayout(1, 2));
        panel3.setLayout(new GridLayout(1, 2));
        panel4.setLayout(new GridLayout(1, 2));
        JComboBox<String> typeBox = new JComboBox<>();
        typeBox.addItem("");
        typeBox.addItem("current account");
        typeBox.addItem("saving account");

        panel1.add(new JLabel("Account Type"));
        panel1.add(typeBox);

        panel2.add(new JLabel("Account Holder Username"));
        JTextField usernameField = new JTextField();
        panel2.add(usernameField);

        panel3.add(new JLabel("Account Holder Password"));
        JPasswordField passwordField = new JPasswordField();
        panel3.add(passwordField);


        JButton addButton = new JButton("Add Account");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                String typeStr = (String) typeBox.getSelectedItem();
                Account accountArgs = getAccountArgs(username, password, typeStr);
                if (accountArgs != null) {
                    Response<Boolean> response = RequestUtils.request(CREATE_ACCOUNT, accountArgs);
                    if (!response.getCode()) {
                        JOptionPane.showMessageDialog(EditAccountPage.this, "Add fail," + response.getMessage());
                    } else {
                        JOptionPane.showMessageDialog(EditAccountPage.this, "Add Success");
                        PageManagement.changePage(new MainPage(new AccountPage()));
                    }
                }
            }
        });
        JButton removeButton = new JButton("Remove Account");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                String typeStr = (String) typeBox.getSelectedItem();
                Account accountArgs = getAccountArgs(username, password, typeStr);
                if (accountArgs != null) {
                    Response<List<Account>> response = RequestUtils.request(SELECT_ACCOUNT, accountArgs.getUserId());
                    for (Account account : response.getData()) {
                        if (account.getType().equals(accountArgs.getType())) {
                            if (!account.getBalance().equals(0F)) {
                                int option = JOptionPane.showConfirmDialog(EditAccountPage.this, "Your account still " +
                                                "has balance:[" + account.getBalance() + "]. Are you sure you want to" +
                                                " " +
                                                "delete the " +
                                                "account? " +
                                                "(This action cannot be undone)", "",
                                        JOptionPane.YES_NO_OPTION);
                                if (option == JOptionPane.YES_OPTION) {
                                    Response<Boolean> removeResponse = RequestUtils.request(REMOVE_ACCOUNT,
                                            accountArgs);
                                    if (removeResponse.getCode()) {
                                        JOptionPane.showMessageDialog(EditAccountPage.this, "Remove Success");
                                        PageManagement.changePage(new MainPage(new AccountPage()));
                                    } else {
                                        JOptionPane.showMessageDialog(EditAccountPage.this,
                                                "Remove fail," + removeResponse.getMessage());
                                    }
                                }
                                return;
                            }
                        }
                    }
                    JOptionPane.showMessageDialog(EditAccountPage.this, "Account No Found");
                }
            }
        });
        panel4.add(addButton);
        panel4.add(removeButton);

        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);
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

    private Account getAccountArgs(String username, String password, String typeStr) {
        Integer userId = authCheck(username, password);
        if (userId != null) {
            Account account = new Account();
            account.setUserId(userId);
            if (typeStr.equals("current account")) {
                account.setType(Account.CURRENT);
            } else if (typeStr.equals("saving account")) {
                account.setType(Account.SAVING);
            } else {
                account.setType((short) -1);
            }
            return account;
        }
        return null;
    }

    /**
     * auth check
     *
     * @param username username
     * @param password password
     * @return {@link Integer } the proper userId
     * @author Ni Xiang
     */
    private Integer authCheck(String username, String password) {
        if (username == null || password == null) {
            JOptionPane.showMessageDialog(EditAccountPage.this, "username or password should not be null");
            return null;
        }
        User userLogin = PageManagement.getInstance().getUserLogin();
        if (username.equals(userLogin.getUsername()) && password.equals(userLogin.getPassword())) {
            return userLogin.getUserId();
        } else {
            Response<User> response = RequestUtils.request(LOGIN_URL, new User(username, password));
            if (!response.getCode()) {
                JOptionPane.showMessageDialog(EditAccountPage.this, "Wrong username or password");
                return null;
            } else {
                return response.getData().getUserId();
            }
        }
    }
}
