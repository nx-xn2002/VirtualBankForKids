package com.group20.frontend.pages;

import com.group20.Response;
import com.group20.backend.model.User;
import com.group20.frontend.PageManagement;
import com.group20.utils.RequestUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * relation edit page
 *
 * @author Ni Xiang
 */
public class RelationEditPage extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 150;
    private static final String TITLE = "Edit Relation";
    private static final String ADD_RELATION = "/user/addRelation";
    private static final String REMOVE_RELATION = "/user/removeRelation";

    public RelationEditPage() {
        super(TITLE);
        setSize(WIDTH, HEIGHT);
        showInTheCenter(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        componentInit();
        setVisible(true);
    }

    private void componentInit() {
        setLayout(new GridLayout(2, 2));
        add(new JLabel("Username"));
        JTextField usernameField = new JTextField();
        add(usernameField);
        JButton addButton = new JButton("Add Relation");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User[] relationArgs = setRelationArgs(usernameField.getText());
                Response response = RequestUtils.request(ADD_RELATION, relationArgs);
                if (response.getCode()) {
                    JOptionPane.showMessageDialog(RelationEditPage.this, "Add Success!");
                    PageManagement.changePage(new MainPage());
                } else {
                    JOptionPane.showMessageDialog(RelationEditPage.this, "Add fail, " + response.getMessage());
                }
            }
        });
        JButton removeButton = new JButton("Remove Relation");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User[] relationArgs = setRelationArgs(usernameField.getText());
                Response response = RequestUtils.request(REMOVE_RELATION, relationArgs);
                if (response.getCode()) {
                    JOptionPane.showMessageDialog(RelationEditPage.this, "Remove Success!");
                    PageManagement.changePage(new MainPage());
                } else {
                    JOptionPane.showMessageDialog(RelationEditPage.this, "Remove fail, " + response.getMessage());
                }
            }
        });
        add(addButton);
        add(removeButton);
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

    private User[] setRelationArgs(String usernameInput) {
        //当前用户的username
        String username1 = PageManagement.getInstance().getUserLogin().getUsername();
        Short role = PageManagement.getInstance().getUserLogin().getRole();
        String username2 = usernameInput;
        User child = new User();
        User parent = new User();
        if (role.equals(User.CHILD)) {
            child.setUsername(username1);
            parent.setUsername(username2);
        } else {
            child.setUsername(username2);
            parent.setUsername(username1);
        }
        return new User[]{parent, child};
    }
}
