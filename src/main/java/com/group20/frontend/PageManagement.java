package com.group20.frontend;

import com.group20.backend.model.User;
import com.group20.frontend.pages.LoginPage;
import lombok.Data;

import javax.swing.*;

/**
 * page management
 *
 * @author Ni Xiang
 */
@Data
public class PageManagement {
    private final static PageManagement PAGE_MANAGEMENT = new PageManagement();

    private JFrame page;
    private User userLogin;

    private PageManagement() {
        userLogin = null;
        page = new LoginPage();
    }

    public static PageManagement getInstance() {
        return PAGE_MANAGEMENT;
    }

    public static void changePage(JFrame page) {
        JFrame oldPage = PAGE_MANAGEMENT.page;
        // 如果原来的页面不为空，则关闭它
        if (oldPage != null) {
            oldPage.dispose(); // 关闭并释放资源
        }
        PAGE_MANAGEMENT.page = page;
    }
}
