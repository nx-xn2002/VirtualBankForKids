package com.group20.frontend.components;

import javax.swing.table.DefaultTableModel;

/**
 * user info table model
 *
 * @author Ni Xiang
 */
public class AccountTableModel extends DefaultTableModel {
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public AccountTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }
}
