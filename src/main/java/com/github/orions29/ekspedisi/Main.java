package com.github.orions29.ekspedisi;

import com.github.orions29.ekspedisi.controller.LoginController;
import com.github.orions29.ekspedisi.utils.DAOtester;
import com.github.orions29.ekspedisi.utils.HashUtil;
import com.github.orions29.ekspedisi.utils.ProjectInit;
import com.github.orions29.ekspedisi.views.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Project: Ekspedisi Mas Giannt
 * Package: com.github.orions29.eskpedisi
 * <p>
 * Deskripsi fungsional dari file ini.
 * </p>
 *
 * <hr>
 * <table border="0">
 * <tr><td><b>Author</b></td><td>: Orions29</td></tr>
 * <tr><td><b>Date</b></td><td>: 15 May 2026</td></tr>
 * <tr><td><b>Time</b></td><td>: 22:05</td></tr>
 * </table>
 * <hr>
 *
 * @author Orions29
 * @since 1.0
 */
public class Main {
    public static void main(String[] args) {
//        Project Checklist
//        ProjectInit.projectCheck();

//        Buat UI
        javax.swing.SwingUtilities.invokeLater(() -> {

            javax.swing.JFrame frame =
                    new javax.swing.JFrame(
                            "EMG Tracking System - Login"
                    );

            LoginView loginView =
                    new LoginView();

            new LoginController(loginView);

            frame.setContentPane(loginView);

            frame.setDefaultCloseOperation(
                    javax.swing.JFrame.EXIT_ON_CLOSE
            );

            frame.pack();

            frame.setLocationRelativeTo(null);

            frame.setVisible(true);
        });
//        OutletViews.main(args);
//        KurirViews.main(args);
//        GudangViews.main(args);
//        TrackingViews.main(args);
//        AdminViews.main(args);

//        System.out.println(HashUtil.hashSHA256("123"));
//        System.out.println(HashUtil.hashSHA256("123"));
    }
}
