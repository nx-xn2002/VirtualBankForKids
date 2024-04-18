package com.group20.frontend.pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * default page
 *
 * @author Ni Xiang
 */
public class DefaultPage extends JFrame {
    private static final String TITLE = "Virtual Bank For Kids - Group 20";

    public DefaultPage(int width, int height) {
        super(TITLE);
        setSize(width, height);
        showInTheCenter(width, height);
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(DefaultPage.this, "Are you sure you want to exit the app?", "", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    dispose();
                    System.exit(0);
                }
            }
        });
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
