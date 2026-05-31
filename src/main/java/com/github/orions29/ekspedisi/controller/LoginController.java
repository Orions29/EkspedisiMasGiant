package com.github.orions29.ekspedisi.controller;
import com.github.orions29.ekspedisi.views.LoginView;
import  javax.swing.*;


public class LoginController {
    private  LoginView view;

    public LoginController(LoginView view){
        this.view = view;

        initController();
    }

    private void initController() {
        view.getLoginButton().addActionListener(e -> {
            handleLoginEvent();
        });

        view.getTrackButton().addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Fitur tracking blm tersedia."
            );
        });
    }

    private  void handleLoginEvent() {
        String username = view.getUsernameInput().getText();
        String password = String.valueOf(
                view.getPasswordInput().getPassword()
        );

        if(username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username dan Password wajib diisi!");
            return;
        }

        // dummy login mas
        if(username.equals("admin") && password.equals("123")) {
            JOptionPane.showMessageDialog(null, "Login Berhasil!"
            );
        } else {
            JOptionPane.showMessageDialog(null, "Username/Password Salah ngab!");
        }
    }
}
