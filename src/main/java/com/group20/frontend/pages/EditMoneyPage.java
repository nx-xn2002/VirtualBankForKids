package com.group20.frontend.pages;

import com.group20.Response;
import com.group20.backend.model.Money;
import com.group20.frontend.PageManagement;
import com.group20.utils.RequestUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * edit money page
 *
 * @author Ni Xiang
 */
public class EditMoneyPage extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 150;
    private static final String SAVE_TITLE = "Save Money";
    private static final String WITHDRAW_TITLE = "Withdraw Money";
    private static final String TRANSFER_TITLE = "Transfer Money";
    private final static String CHANGE = "/account/change";

    public EditMoneyPage(Short type, Integer currentAccountId) {
        super();
        if (type.equals(Money.WITHDRAW)) {
            setTitle(WITHDRAW_TITLE);
        } else if (type.equals(Money.SAVE)) {
            setTitle(SAVE_TITLE);
        } else if (type.equals(Money.TRANSFER)) {
            setTitle(TRANSFER_TITLE);
        }
        setSize(WIDTH, HEIGHT);
        showInTheCenter(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        componentInit(type, currentAccountId);
        setVisible(true);
    }

    private void componentInit(Short type, Integer currentAccountId) {
        if (type.equals(Money.WITHDRAW) || type.equals(Money.SAVE)) {
            //amount
            //withdraw / save
            setLayout(new GridLayout(2, 1));
            JPanel jPanel = new JPanel();
            jPanel.setLayout(new GridLayout(1, 2));
            jPanel.add(new JLabel("Amount"));
            JTextField amountField = new JTextField();
            jPanel.add(amountField);
            add(jPanel);
            String operation = type.equals(Money.WITHDRAW) ? "Withdraw" : "Save";
            JButton button = new JButton(operation);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Money money = new Money();
                    money.setType(type);
                    money.setAccountIdA(currentAccountId);
                    float amount = 0;
                    try {
                        amount = Float.parseFloat(amountField.getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(EditMoneyPage.this, "Wrong Amount!");
                        return;
                    }
                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(EditMoneyPage.this, "Wrong Amount!");
                        return;
                    }
                    money.setAmount(amount);
                    Response<Boolean> request = RequestUtils.request(CHANGE, money);
                    if (!request.getCode()) {
                        JOptionPane.showMessageDialog(EditMoneyPage.this, operation + " Error," + request.getMessage());
                    } else {
                        JOptionPane.showMessageDialog(EditMoneyPage.this, operation + " Success");
                        PageManagement.changePage(new MainPage(new MoneyPage()));
                        EditMoneyPage.this.dispose();
                    }
                }
            });
            add(button);
        } else {
            //account a
            //account b
            //amount
            //transfer
            setLayout(new GridLayout(4, 1));
            JPanel jPanel1 = new JPanel();
            jPanel1.setLayout(new GridLayout(1, 2));
            jPanel1.add(new JLabel("Account A"));
            JTextField accountA = new JTextField();
            jPanel1.add(accountA);

            JPanel jPanel2 = new JPanel();
            jPanel2.setLayout(new GridLayout(1, 2));
            jPanel2.add(new JLabel("Account B"));
            JTextField accountB = new JTextField();
            jPanel2.add(accountB);

            JPanel jPanel3 = new JPanel();
            jPanel3.setLayout(new GridLayout(1, 2));
            jPanel3.add(new JLabel("Amount"));
            JTextField amountField = new JTextField();
            jPanel3.add(amountField);

            JButton transferButton = new JButton("Transfer");
            transferButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Money money = new Money();
                    money.setType(type);
                    int accountIdA = 0, accountIdB = 0;
                    try {
                        accountIdA = Integer.parseInt(accountA.getText());
                        accountIdB = Integer.parseInt(accountB.getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(EditMoneyPage.this, "Wrong Account!");
                        return;
                    }
                    if (accountIdA < 10000000 || accountIdA > 100000000 || accountIdB < 10000000 || accountIdB > 100000000) {
                        JOptionPane.showMessageDialog(EditMoneyPage.this, "Wrong Account!");
                        return;
                    }
                    money.setAccountIdA(accountIdA);
                    money.setAccountIdB(accountIdB);
                    float amount = 0;
                    try {
                        amount = Float.parseFloat(amountField.getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(EditMoneyPage.this, "Wrong Amount!");
                        return;
                    }
                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(EditMoneyPage.this, "Wrong Amount!");
                        return;
                    }
                    money.setAmount(amount);
                    int option = JOptionPane.showConfirmDialog(EditMoneyPage.this,
                            "You are trying to transfer " + amount + " from account " + accountIdA + " to account " + accountIdB + ". Do you want to " + "proceed with this " + "operation?", "", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        Response<Boolean> response = RequestUtils.request(CHANGE, money);
                        if (response.getCode()) {
                            JOptionPane.showMessageDialog(EditMoneyPage.this, "Transfer Success");
                            PageManagement.changePage(new MainPage(new MoneyPage()));
                            EditMoneyPage.this.dispose();
                        } else {
                            JOptionPane.showMessageDialog(EditMoneyPage.this,
                                    "Transfer Failed" + response.getMessage());
                        }
                    }
                }
            });

            add(jPanel1);

            add(jPanel2);

            add(jPanel3);

            add(transferButton);
        }
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
}
