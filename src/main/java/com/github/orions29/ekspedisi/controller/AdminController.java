package com.github.orions29.ekspedisi.controller;

import com.github.orions29.ekspedisi.model.dao.UserDAO;
import com.github.orions29.ekspedisi.model.dao.UserDAOMariaDb;
import com.github.orions29.ekspedisi.model.entity.User;
import com.github.orions29.ekspedisi.views.AdminViews;
import com.github.orions29.ekspedisi.views.LoginView;

import javax.swing.*;

/**
 * Project: EkspedisiMasRoi
 * Package: com.github.orions29.ekspedisi.controller
 * <p>
 * Deskripsi fungsional dari file ini.
 * </p>
 *
 * <hr>
 * <table border="0">
 * <tr><td><b>Author</b></td><td>: Orions29</td></tr>
 * <tr><td><b>Date</b></td><td>: 05 June 2026</td></tr>
 * <tr><td><b>Time</b></td><td>: 23:01</td></tr>
 * </table>
 * <hr>
 *
 * @author Orions29
 * @since 1.0
 */
public class AdminController {
    private AdminViews view;
    private UserDAO userDAO;
    private User user;

    public AdminController(AdminViews view, User user) {
        this.view = view;
        this.userDAO = new UserDAOMariaDb();
        this.user = user;
    }

    public AdminController() {
    }

    /**
     *
     * <h3>Initialization Controller</h3>
     * <p> </p>
     *
     * @author Orions29
     * @since 5 Jun 2026
     *
     */
    private void initController() {
        view.getBtnLogout().addActionListener(e -> handleLogoutEvent());
    }

    private void handleLogoutEvent() {
        SwingUtilities.invokeLater(() -> {
            JFrame loginFrame = new JFrame("EMR Tracking System - Login");
            LoginView loginView = new LoginView();

            new LoginController(loginView);

            loginFrame.setContentPane(loginView);
            loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            loginFrame.pack();
            loginFrame.setLocationRelativeTo(null);
            loginFrame.setVisible(true);
        });

        view.dispose();
    }
}
