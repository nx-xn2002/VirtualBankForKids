package com.group20.frontend.pages;

import com.group20.Response;
import com.group20.frontend.PageManagement;
import com.group20.utils.RequestUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * finish task page
 *
 * @author Ni Xiang
 */
public class FinishTaskPage extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 150;
    private static final String TITLE = "Finish Task";
    private static final String FINISH_TASK = "/user/finishTask";

    public FinishTaskPage() {
        super(TITLE);
        setSize(WIDTH, HEIGHT);
        showInTheCenter(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        componentInit();
        setVisible(true);
    }

    private void componentInit() {
        setLayout(new GridLayout(2, 1));
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(1, 2));
        jPanel.add(new JLabel("Task Id"));
        JTextField jTextField = new JTextField();
        jPanel.add(jTextField);
        JButton finishButton = new JButton("Finish");
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = jTextField.getText();
                Integer taskId = null;
                try {
                    taskId = Integer.parseInt(text);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(FinishTaskPage.this, "Invalid Task Id!");
                    return;
                }
                Response request = RequestUtils.request(FINISH_TASK, taskId);
                if (request.getCode()) {
                    JOptionPane.showMessageDialog(FinishTaskPage.this, "Finish Success!");
                    PageManagement.changePage(new MainPage(new TaskPage()));
                    FinishTaskPage.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(FinishTaskPage.this, "Error" + request.getMessage());
                }
            }
        });
        add(jPanel);
        add(finishButton);
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
