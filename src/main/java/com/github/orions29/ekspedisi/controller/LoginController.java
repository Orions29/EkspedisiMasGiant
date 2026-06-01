package com.github.orions29.ekspedisi.controller;
import com.github.orions29.ekspedisi.model.dao.UserDAO;
import com.github.orions29.ekspedisi.model.dao.UserDAOMariaDb;
import com.github.orions29.ekspedisi.model.entity.User;
import com.github.orions29.ekspedisi.utils.HashUtil;
import com.github.orions29.ekspedisi.views.*;
import javax.swing.*;

public class LoginController {

    private LoginView view;
    private UserDAO userDAO;

    public LoginController(LoginView view) {

        this.view = view;
        this.userDAO = new UserDAOMariaDb();

        initController();
    }

    private void initController() {

        view.getLoginButton().addActionListener(e -> {

            handleLoginEvent();
        });

        view.getTrackButton().addActionListener(e -> {

            TrackingViews.main(null);
        });
    }

    private void handleLoginEvent() {

        String username =
                view.getUsernameInput()
                        .getText()
                        .trim();

        String password =
                String.valueOf(
                        view.getPasswordInput()
                                .getPassword()
                );

        if(username.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Username dan Password wajib diisi!"
            );

            return;
        }

        // Hash password input user
        String hashedPassword =
                HashUtil.hashSHA256(password);

        // Authenticate ke database
        User user =
                userDAO.authenticate(
                        username,
                        hashedPassword
                );

        if(user != null) {

            JOptionPane.showMessageDialog(
                    null,
                    "Login Berhasil!"
            );

            routeByRole(user);

        } else {

            JOptionPane.showMessageDialog(
                    null,
                    "Username/Password Salah ngab!"
            );
        }
    }

    private void routeByRole(User user) {

        String role =
                user.getRole();

        switch(role.toLowerCase()) {

            case "loket":

                OutletViews outletViews =
                        new OutletViews(user);

                new OutletController(
                        outletViews,
                        user
                );

                outletViews.setLocationRelativeTo(null);
                outletViews.setVisible(true);

                SwingUtilities
                        .getWindowAncestor(view)
                        .dispose();

                break;

            case "gudang":

                GudangViews gudangViews =
                        new GudangViews(user);

                new GudangController(
                        gudangViews,
                        user
                );

                gudangViews.setLocationRelativeTo(null);
                gudangViews.setVisible(true);

                SwingUtilities
                        .getWindowAncestor(view)
                        .dispose();

                break;

            case "kurir":

                KurirViews kurirViews =
                        new KurirViews(user);

                new KurirController(
                        kurirViews,
                        user
                );

                kurirViews.setLocationRelativeTo(null);
                kurirViews.setVisible(true);

                SwingUtilities
                        .getWindowAncestor(view)
                        .dispose();

                break;

            case "admin":

                JOptionPane.showMessageDialog(
                        null,
                        "Login admin berhasil!"
                );

                break;

            default:

                JOptionPane.showMessageDialog(
                        null,
                        "Role tidak dikenali, anomali ta iki!"
                );
        }
    }
}