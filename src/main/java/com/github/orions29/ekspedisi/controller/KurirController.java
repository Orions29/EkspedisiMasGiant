package com.github.orions29.ekspedisi.controller;

import com.github.orions29.ekspedisi.model.dao.TrackingDAO;
import com.github.orions29.ekspedisi.model.dao.TrackingDAOMariaDb;
import com.github.orions29.ekspedisi.model.entity.ShipmentLog;
import com.github.orions29.ekspedisi.model.entity.User;
import com.github.orions29.ekspedisi.views.KurirViews;

import java.util.List;

import javax.swing.*;

/**
 * Controller buat mengelola proses pengiriman
 * dan update status paket oleh kang kurir
 *
 * <p>
 * Tugasnya adalah:
 * validasi nomor resi,
 * update status paket saat dibawa kurir,
 * pencatatan paket diterima penerima,
 * serta penyimpanan tracking pengiriman ke database
 * </p>
 *
 * <p>
 * Controller ini menghubungkan KurirViews
 * dengan TrackingDAO untuk proses
 * update riwayat pengiriman paket
 * </p>
 *
 * @author Erlan
 */


// constructor utama kurirController
// nerima data user kurir yang lagi login, inisialisasi trackingDAO, jalanin semua event listener controller
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


    // method buat konekin semua komponen UI sama logic controller, menggunakan event listener
    private void initController() {

        view.getSubmitPaket()
                .addActionListener(e -> {

                    handleDeliveryUpdate();
                });

        view.getPaketSelesaiButton()
                .addActionListener(e -> {

                    handlePaketSelesai();
                });

        view.getCekPaketButton().addActionListener(e -> {
            handleCekMuatan();
        });

        view.getLogoutButton().addActionListener(e -> {
            handleLogoutEvent();
        });
    }

    private void handleLogoutEvent() {
        SwingUtilities.invokeLater(() -> {
            JFrame loginFrame = new JFrame("EMG Tracking System - Login");
            com.github.orions29.ekspedisi.views.LoginView loginView = new com.github.orions29.ekspedisi.views.LoginView();

            new LoginController(loginView);

            loginFrame.setContentPane(loginView);
            loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            loginFrame.pack();
            loginFrame.setLocationRelativeTo(null);
            loginFrame.setVisible(true);
        });

        view.dispose();
    }


    // memproses update status paket ketika paket dibawa kurir
    // Status : "Diserahkan ke kurir"
    // method ini akan membuat log baru buat disimpen di db
    private void handleDeliveryUpdate() {

        String resi =
                view.getResiPaketIn()
                        .getText()
                        .trim();

        if (resi.isEmpty()) {

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

        if (success) {

            JOptionPane.showMessageDialog(
                    null,
                    "Status pengiriman berhasil diperbarui!\n\n"
                            + "Resi : "
                            + resi
            );

            view.getResiPaketIn()
                    .setText("");

        } else { // error handling gagal update tracking

            JOptionPane.showMessageDialog(
                    null,
                    "Gagal update tracking paket!"
            );
        }
    }


    // method untuk mengubah status paket menjadi diterima oleh penerima
    // status yang dicatat: "diterima oleh penerima"
    private void handlePaketSelesai() {

        // ngambil resi dari input field
        String resi =
                view.getResiPaketIn()
                        .getText()
                        .trim();

        // validasi agar resi tidak kosong
        if (resi.isEmpty()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Nomor resi wajib diisi"
            );

            return;
        }

        // Buat tracking log baru
        // dengan status paket diterima oleh penerima
        ShipmentLog logBaru =
                new ShipmentLog(
                        resi,
                        "Paket Telah Diterima",
                        "Paket sudah diterima oleh penerima",
                        loggedInUser.getId()
                );

        // menyimpan tracking pengiriman ke db
        boolean success =
                trackingDAO.insertLog(logBaru);

        // success handler
        if (success) {

            JOptionPane.showMessageDialog(
                    null,
                    "Paket berhasil diselesaikan!"
            );

            view.getResiPaketIn()
                    .setText("");

        } else {
            // failed handler
            JOptionPane.showMessageDialog(
                    null,
                    "Gagal update status paket!"
            );
        }
    }

    private void handleCekMuatan() {

        // 1. Tarik data murni dari MariaDB berdasarkan DNA Kurir
        List<String> daftarResi = trackingDAO.getResiByLatestStatusAndUser("Dibawa Kurir", loggedInUser.getId());

        // 2. Bersihkan layar radar sebelum mencetak data baru
        view.getListPaketArea().setText("");

        // 3. Validasi Amunisi (Kalau lagi nggak bawa paket)
        if (daftarResi.isEmpty()) {
            view.getListPaketArea().setText("[KOSONG]\n\nTidak ada paket yang sedang kamu bawa saat ini.\nSantai dulu ngab! ☕");
            return;
        }

        // 4. Rakit Tampilan UI Radar
        StringBuilder sb = new StringBuilder();
        sb.append("=== DAFTAR MUATAN SAAT INI ===\n");
        sb.append("Total Paket: ").append(daftarResi.size()).append(" item\n\n");

        int nomor = 1;
        for (String resi : daftarResi) {
            sb.append(nomor).append(". ").append(resi).append("\n");
            nomor++;
        }

        // 5. Tembakkan ke Kanvas View
        view.getListPaketArea().setText(sb.toString());

        // 6. Kunci scrollbar agar selalu mulai dari paling atas
        view.getListPaketArea().setCaretPosition(0);
    }
}