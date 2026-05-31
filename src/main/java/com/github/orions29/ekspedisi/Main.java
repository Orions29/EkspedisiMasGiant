package com.github.orions29.ekspedisi;

import com.github.orions29.ekspedisi.utils.DAOtester;
import com.github.orions29.ekspedisi.utils.ProjectInit;
import com.github.orions29.ekspedisi.views.GudangViews;
import com.github.orions29.ekspedisi.views.KurirViews;
import com.github.orions29.ekspedisi.views.LoginView;
import com.github.orions29.ekspedisi.views.OutletViews;
import com.github.orions29.ekspedisi.controller.LoginController;
import com.github.orions29.ekspedisi.model.entity.User;
import com.github.orions29.ekspedisi.controller.OutletController;
import com.github.orions29.ekspedisi.controller.KurirController;
import com.github.orions29.ekspedisi.controller.GudangController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(Main.class);

        logger.info("Dummy Info Test");
        logger.error("Dummy Error Test");

        ProjectInit.projectCheck();

        // Ngetest DAO
        DAOtester.main(args);

        // Buat UI Login
  //      javax.swing.SwingUtilities.invokeLater(() -> {

  //          javax.swing.JFrame frame =
   //                 new javax.swing.JFrame(
  //                          "EMG Tracking System - Login"
   //                 );

    //        LoginView loginView = new LoginView();

            // Pasang Controller
    //        new LoginController(loginView);

    //        frame.setContentPane(loginView);

    //        frame.setDefaultCloseOperation(
    //               javax.swing.JFrame.EXIT_ON_CLOSE
    //        );

     //       frame.pack();
    //        frame.setLocationRelativeTo(null);
     //       frame.setVisible(true);
     //   });

        // Login sementara dimatikan bray demi developing yg tentram

//        User dummyUser =
//                new User(
//                        "L-1001",
//                        "loket_rani",
//                        "hash",
//                        "loket",
//                        "Loket Pusat"
//                );

//        OutletViews outletViews =
//                new OutletViews(dummyUser);

//        new OutletController(outletViews);

//        outletViews.setLocationRelativeTo(null);
//        outletViews.setVisible(true);

        // OutletViews.main(args);
        // KurirViews.main(args);
        // GudangViews.main(args);

        // test kurir controller
//        KurirViews kurirViews =
//                new KurirViews();

//        new KurirController(kurirViews);

//        kurirViews.setLocationRelativeTo(null);
//        kurirViews.setVisible(true);

        // test gudang controller
        User dummyUser =
                new User(
                        "G-2001",
                        "gudang_budi",
                        "hash",
                        "gudang",
                        "Gudang Pusat"
                );

        GudangViews gudangViews =
                new GudangViews(dummyUser);

        new GudangController(gudangViews);

        gudangViews.setLocationRelativeTo(null);
        gudangViews.setVisible(true);
    }
}