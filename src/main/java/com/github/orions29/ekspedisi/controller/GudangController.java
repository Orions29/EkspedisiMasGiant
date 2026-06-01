package com.github.orions29.ekspedisi.controller;

import com.github.orions29.ekspedisi.model.dao.TrackingDAO;
import com.github.orions29.ekspedisi.model.dao.TrackingDAOMariaDb;

import com.github.orions29.ekspedisi.model.entity.ShipmentLog;

import com.github.orions29.ekspedisi.views.GudangViews;

import javax.swing.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GudangController {

    private GudangViews view;

    private TrackingDAO trackingDAO;

    public GudangController(GudangViews view){

        this.view = view;

        this.trackingDAO =
                new TrackingDAOMariaDb();

        initController();
    }

    private void initController(){

        view.getBtnUpdate()
                .addActionListener(e -> {

                    handleMassTransitUpdate();
                });
    }

    private void handleMassTransitUpdate(){

        String resi =
                view.getTxtResi()
                        .getText()
                        .trim();

        String status =
                (String) view.getComboStatus()
                        .getSelectedItem();

        if (resi.isEmpty()){

            JOptionPane.showMessageDialog(
                    null,
                    "Nomor resi wajib diisi!"
            );

            return;
        }

        try {

            // Insert tracking log ke database
            ShipmentLog log =
                    new ShipmentLog(
                            resi,
                            "Gudang Pusat",
                            status,
                            "system-gudang"
                    );

            trackingDAO.insertLog(log);

            // Console visual
            String timestamp =
                    LocalDateTime.now()
                            .format(
                                    DateTimeFormatter.ofPattern(
                                            "HH:mm:ss"
                                    )
                            );

            String logMessage =
                    "[" + timestamp + "] "
                            + "SUCCESS : "
                            + resi
                            + " -> "
                            + status
                            + "\n";

            view.getTxtConsole()
                    .append(logMessage);

            view.getTxtConsole()
                    .setCaretPosition(
                            view.getTxtConsole()
                                    .getDocument()
                                    .getLength()
                    );

            JOptionPane.showMessageDialog(
                    null,
                    "Status gudang berhasil diperbarui!"
            );

            view.getTxtResi().setText("");

            view.getTxtResi()
                    .requestFocus();

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    null,
                    "Gagal update tracking!\n"
                            + ex.getMessage()
            );
        }
    }
}