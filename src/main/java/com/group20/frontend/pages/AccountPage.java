package com.group20.frontend.pages;

import com.group20.Response;
import com.group20.backend.model.Account;
import com.group20.backend.model.User;
import com.group20.frontend.PageManagement;
import com.group20.frontend.components.AccountTableModel;
import com.group20.utils.RequestUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * account page
 *
 * @author Ni Xiang
 */
public class AccountPage extends JPanel {
    private final static String SELECT_ACCOUNT = "/account/selectAccount";

    public AccountPage() {
        setLayout(new BorderLayout());
        add(new JLabel("Accounts"), BorderLayout.NORTH);

        Response response = RequestUtils.request(SELECT_ACCOUNT,
                PageManagement.getInstance().getUserLogin().getUserId());
        List<Account> accountList = (List<Account>) response.getData();
        JTable accountInfo;
        if (accountList == null || accountList.isEmpty()) {
            String[][] datas = {};
            String[] title = {"key", "value"};
            DefaultTableModel tableModel = new AccountTableModel(datas, title);
            accountInfo = new JTable(tableModel);
            tableModel.addRow(new String[]{"No accounts found"});
        } else {
            String[][] datas = {};
            String[] title = {"key", "value"};
            DefaultTableModel tableModel = new AccountTableModel(datas, title);
            accountInfo = new JTable(tableModel);
            int index = 1;
            for (Account account : accountList) {
                tableModel.addRow(new String[]{"account" + index++});
                tableModel.addRow(new String[]{"Account Id", account.getAccountId().toString()});
                String type = account.getType().equals(Account.CURRENT) ? "current account" : "saving account";
                tableModel.addRow(new String[]{"Type", type});
                tableModel.addRow(new String[]{"Balance", account.getBalance().toString()});
                tableModel.addRow(new String[]{"Create Time", account.getCreateTime().toString()});
                tableModel.addRow(new String[]{"Update Time", account.getUpdateTime().toString()});
                tableModel.addRow(new String[]{});
                tableModel.addRow(new String[]{});
            }
        }


        add(accountInfo, BorderLayout.CENTER);
        JButton editButton = new JButton("Edit Account");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Short role = PageManagement.getInstance().getUserLogin().getRole();
                if (role.equals(User.PARENT)) {
                    new EditAccountPage();
                } else if (role.equals(User.CHILD)) {
                    JOptionPane.showMessageDialog(AccountPage.this, "You do not have permission to edit the account. " +
                            "Please contact your parents.");
                }
            }
        });
        add(editButton, BorderLayout.SOUTH);
    }
}
