package com.group20.frontend.components;

import javax.swing.table.DefaultTableModel;

/**
 * user info table model
 *
 * @author Ni Xiang
 */
public class UserInfoTableModel extends DefaultTableModel {
    @Override
    public boolean isCellEditable(int row, int column) {
        if (column == 0) {
            return false;
        }
        return isRowEditable(row);
    }

    private boolean isRowEditable(int row) {
        if (row > 6 || row == 0 || row == 1 || row == 4) {
            return false;
        }
        return true;
    }

    public UserInfoTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }
}
