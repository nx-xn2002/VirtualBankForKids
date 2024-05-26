package com.group20.frontend.pages;

import com.group20.Response;
import com.group20.backend.model.Account;
import com.group20.backend.model.Money;
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
 * money page
 *
 * @author Ni Xiang
 */
public class MoneyPage extends JPanel {
    private final static String GET_MONEY_LIST = "/account/getMoneyList";
    private final static String SELECT_ACCOUNT = "/account/selectAccount";

    public MoneyPage() {
        setLayout(new BorderLayout());
        add(new JLabel("Money Page"), BorderLayout.NORTH);
        Response<List<Money>> response = RequestUtils.request(GET_MONEY_LIST,
                PageManagement.getInstance().getUserLogin().getUserId());
        List<Money> moneyList = (List<Money>) response.getData();
        JTable moneyInfo;
        if (moneyList == null || moneyList.isEmpty()) {
            String[][] datas = {};
            String[] title = {"key", "value"};
            DefaultTableModel tableModel = new AccountTableModel(datas, title);
            moneyInfo = new JTable(tableModel);
            tableModel.addRow(new String[]{"No Payment Records Found"});
            add(moneyInfo, BorderLayout.CENTER);
        } else {
            String[][] datas = {};
            String[] title = {"index", "type", "Account A", "Account B", "Amount", "Time"};
            DefaultTableModel tableModel = new AccountTableModel(datas, title);
            moneyInfo = new JTable(tableModel);
            int index = 1;
            for (Money money : moneyList) {
                Short type = money.getType();
                String typeStr;
                if (type.equals(Money.WITHDRAW)) {
                    typeStr = "Withdraw";
                } else if (type.equals(Money.SAVE)) {
                    typeStr = "Save";
                } else {
                    typeStr = "Transfer";
                }
                Float amount = money.getAmount();
                String amountStr = amount > 0 ? "+" + amount.toString() : amount.toString();
                tableModel.addRow(
                        new String[]{String.valueOf(index++),
                                typeStr,
                                money.getAccountIdA().toString(),
                                money.getAccountIdB() == 0 ? "" : money.getAccountIdB().toString(),
                                amountStr,
                                money.getCreateTime().toString()
                        });
            }
            JScrollPane scrollPane = new JScrollPane(moneyInfo);
            add(scrollPane, BorderLayout.CENTER);
        }
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(1, 3));

        JButton saveButton = new JButton("Save Money");
        saveButton.addActionListener(new MyListener(Money.SAVE));
        JButton withdrawButton = new JButton("Withdraw Money");
        withdrawButton.addActionListener(new MyListener(Money.WITHDRAW));
        JButton transferButton = new JButton("Transfer Money");
        transferButton.addActionListener(new MyListener(Money.TRANSFER));

        southPanel.add(saveButton);
        southPanel.add(withdrawButton);
        southPanel.add(transferButton);
        add(southPanel, BorderLayout.SOUTH);
    }

    static class MyListener implements ActionListener {
        private final Short type;

        public MyListener(Short type) {
            this.type = type;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Integer currentAccountId = null;
            if (type.equals(Money.WITHDRAW) || type.equals(Money.SAVE)) {
                Integer userId = PageManagement.getInstance().getUserLogin().getUserId();
                Response<List<Account>> response = RequestUtils.request(SELECT_ACCOUNT,
                        userId);
                List<Account> accountList = response.getData();
                for (Account account : accountList) {
                    if (account.getUserId().equals(userId) && account.getType().equals(Account.CURRENT)) {
                        currentAccountId = account.getAccountId();
                        break;
                    }
                }
            }
            new EditMoneyPage(type, currentAccountId);
        }
    }
}
