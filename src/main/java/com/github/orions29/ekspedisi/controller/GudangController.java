package com.github.orions29.ekspedisi.controller;

import com.github.orions29.ekspedisi.model.dao.TrackingDAO;
import com.github.orions29.ekspedisi.model.dao.TrackingDAOMariaDb;
import com.github.orions29.ekspedisi.model.entity.ShipmentLog;
import com.github.orions29.ekspedisi.model.entity.User;
import com.github.orions29.ekspedisi.views.GudangViews;

import javax.swing.*;

public class GudangController {

    private GudangViews view;
    private TrackingDAO trackingDAO;
    private User loggedInUser;

    public GudangController(
            GudangViews view,
            User loggedInUser
    ) {

        this.view = view;
        this.loggedInUser = loggedInUser;

        this.trackingDAO =
                new TrackingDAOMariaDb();

        initController();
    }

    private void initController() {

        view.getBtnUpdate()
                .addActionListener(e -> {

                    handleMassTransitUpdate();
                });
    }

    public void handleMassTransitUpdate() {

        String resiId =
                view.getTxtResi()
                        .getText()
                        .trim();

        String selectedStatus =
                view.getComboStatus()
                        .getSelectedItem()
                        .toString();

        if(resiId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Tembak barcode atau masukkan nomor resi dulu!"
            );

            return;
        }

        ShipmentLog logBaru =
                new ShipmentLog(
                        resiId,
                        loggedInUser.getLocation(),
                        selectedStatus,
                        loggedInUser.getId()
                );

        boolean success =
                trackingDAO.insertLog(logBaru);

        if(success) {

            JOptionPane.showMessageDialog(
                    null,
                    "Status paket berhasil diperbarui!"
            );

            // Console realtime
            String timestamp =
                    java.time.LocalDateTime.now()
                            .format(
                                    java.time.format.DateTimeFormatter
                                            .ofPattern("HH:mm:ss")
                            );

            String logMessage =
                    String.format(
                            "[%s] SUCCESS: Resi %s -> [%s] @ %s\n",
                            timestamp,
                            resiId,
                            selectedStatus,
                            loggedInUser.getLocation()
                    );

            view.getTxtConsole()
                    .append(logMessage);

            view.getTxtConsole()
                    .setCaretPosition(
                            view.getTxtConsole()
                                    .getDocument()
                                    .getLength()
                    );

            view.getTxtResi()
                    .setText("");

            view.getTxtResi()
                    .requestFocus();

        } else {

            JOptionPane.showMessageDialog(
                    null,
                    "Gagal memperbarui status paket!"
            );
        }
    }
}