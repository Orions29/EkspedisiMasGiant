package com.github.orions29.ekspedisi.controller;

import com.github.orions29.ekspedisi.model.dao.TrackingDAO;
import com.github.orions29.ekspedisi.model.dao.TrackingDAOMariaDb;
import com.github.orions29.ekspedisi.model.entity.ShipmentLog;
import com.github.orions29.ekspedisi.model.entity.User;
import com.github.orions29.ekspedisi.views.KurirViews;

import javax.swing.*;

public class KurirController {

    private KurirViews view;

    private User loggedInUser;

    private TrackingDAO trackingDAO;

    public KurirController(
            KurirViews view,
            User loggedInUser
    ) {

        this.view = view;
        this.loggedInUser = loggedInUser;

        this.trackingDAO =
                new TrackingDAOMariaDb();

        initController();
    }

    private void initController(){

        view.getSubmitPaket()
                .addActionListener(e -> {

                    handleDeliveryUpdate();
                });

        view.getPaketSelesaiButton()
                .addActionListener(e -> {

                    handlePaketSelesai();
                });
    }

    private void handleDeliveryUpdate(){

        String resi =
                view.getResiPaketIn()
                        .getText()
                        .trim();

        if (resi.isEmpty()){

            JOptionPane.showMessageDialog(
                    null,
                    "Nomor resi wajib diisi"
            );

            return;
        }

        ShipmentLog logBaru =
                new ShipmentLog(
                        resi,
                        loggedInUser.getLocation(),
                        "Dibawa Kurir",
                        loggedInUser.getId()
                );

        boolean success =
                trackingDAO.insertLog(logBaru);

        if(success) {

            JOptionPane.showMessageDialog(
                    null,
                    "Status pengiriman berhasil diperbarui!\n\n"
                            + "Resi : "
                            + resi
            );

            view.getResiPaketIn()
                    .setText("");

        } else {

            JOptionPane.showMessageDialog(
                    null,
                    "Gagal update tracking paket!"
            );
        }
    }

    private void handlePaketSelesai(){

        String resi =
                view.getResiPaketIn()
                        .getText()
                        .trim();

        if(resi.isEmpty()){

            JOptionPane.showMessageDialog(
                    null,
                    "Nomor resi wajib diisi"
            );

            return;
        }

        ShipmentLog logBaru =
                new ShipmentLog(
                        resi,
                        "Paket Telah Diterima",
                        "Paket sudah diterima oleh penerima",
                        loggedInUser.getId()
                );

        boolean success =
                trackingDAO.insertLog(logBaru);

        if(success){

            JOptionPane.showMessageDialog(
                    null,
                    "Paket berhasil diselesaikan!"
            );

            view.getResiPaketIn()
                    .setText("");

        } else {

            JOptionPane.showMessageDialog(
                    null,
                    "Gagal update status paket!"
            );
        }
    }
}