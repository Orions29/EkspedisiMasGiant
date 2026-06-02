package com.github.orions29.ekspedisi.controller;

import com.github.orions29.ekspedisi.model.dao.PaketDAO;
import com.github.orions29.ekspedisi.model.dao.PaketDAOMariaDb;
import com.github.orions29.ekspedisi.model.dao.TrackingDAO;
import com.github.orions29.ekspedisi.model.dao.TrackingDAOMariaDb;
import com.github.orions29.ekspedisi.model.entity.Paket;
import com.github.orions29.ekspedisi.model.entity.ShipmentLog;
import com.github.orions29.ekspedisi.model.entity.User;
import com.github.orions29.ekspedisi.utils.GeneratorId;
import com.github.orions29.ekspedisi.utils.PricingUtil;
import com.github.orions29.ekspedisi.views.LoketViews;
import javax.swing.*;

public class LoketController {

    private LoketViews view;

    private User loggedInUser;

    private PaketDAO paketDAO;
    private TrackingDAO trackingDAO;

    public LoketController(
            LoketViews view,
            User loggedInUser
    ) {

        this.view = view;
        this.loggedInUser = loggedInUser;

        this.paketDAO =
                new PaketDAOMariaDb();

        this.trackingDAO =
                new TrackingDAOMariaDb();

        initController();
    }

    private void initController(){

        view.getInputPaketButton()
                .addActionListener(e -> {

                    handleInputPaket();
                });

        view.getBeratInput()
                .addChangeListener(e -> {

                    updateEstimasiHarga();
                });

        view.getVolumeInput()
                .addChangeListener(e -> {

                    updateEstimasiHarga();
                });
    }



    private void handleInputPaket(){

        String senderName =
                view.getNamaPengirimInput()
                        .getText()
                        .trim();

        String originCity =
                loggedInUser.getLocation();

        String receiverName =
                view.getNamaPenerimaInput()
                        .getText()
                        .trim();

        String destinationCity =
                view.getDestinasiPaketInput()
                        .getText()
                        .trim();

        String destinationAddress =
                view.getAlamatTujuanInput()
                        .getText()
                        .trim();

        String typePaket =
                view.getTipePaketInput()
                        .getText()
                        .trim();

        double weight =
                (Double) view.getBeratInput()
                        .getValue();

        double volume =
                (Double) view.getVolumeInput()
                        .getValue();

        if (senderName.isEmpty()
                || receiverName.isEmpty()
                || destinationCity.isEmpty()
                || destinationAddress.isEmpty()
                || typePaket.isEmpty()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Semua data wajib diisi!"
            );

            return;
        }

        if (weight <= 0 || volume <= 0){

            JOptionPane.showMessageDialog(
                    null,
                    "Berat dan Volume harus lebih dari 0!"
            );

            return;
        }

        try {

            // Generate Resi
            String resiId =
                    GeneratorId.generateResi();

            // Buat object Paket
            Paket paket =
                    new Paket(
                            resiId,
                            senderName,
                            originCity,
                            receiverName,
                            destinationCity,
                            destinationAddress,
                            weight,
                            volume,
                            typePaket
                    );

            // Insert paket ke database
            boolean paketInserted =
                    paketDAO.insertPaket(paket);

            if (!paketInserted) {

                JOptionPane.showMessageDialog(
                        null,
                        "Gagal menyimpan paket!"
                );

                return;
            }
            // Tracking awal
            ShipmentLog log =
                    new ShipmentLog(
                            resiId,
                            loggedInUser.getLocation(),
                            "Diterima di Loket",
                            loggedInUser.getId()
                    );

            // Insert tracking log
            trackingDAO.insertLog(log);

            String pesanSukses = String.format("Data Paket berhasil disimpan ke database MariaDB!\n\n"
                            + "Nomor Resi: %s\n"
                            + "Pengirim: %s (%s)\n"
                            + "Penerima: %s (%s)\n"
                            + "Berat: %.2f kg\n"
                            + "Volume: %.2f cm3\n"
                            + "Tipe: %s\n\n"
                            + "Lokasi Input: %s",
                    resiId, senderName, originCity, receiverName, destinationCity, weight, volume, typePaket, loggedInUser.getLocation());

            JOptionPane.showMessageDialog(
                    null,
                    pesanSukses
            );

            // Reset form
            view.getNamaPengirimInput().setText("");
            view.getNamaPenerimaInput().setText("");
            view.getDestinasiPaketInput().setText("");
            view.getAlamatTujuanInput().setText("");
            view.getTipePaketInput().setText("");

            view.getBeratInput().setValue(0.0d);
            view.getVolumeInput().setValue(0.0d);

            view.getHargaLabel().setText("0,00");

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    null,
                    "Terjadi kesalahan!\n"
                            + ex.getMessage()
            );
        }
    }

    private void updateEstimasiHarga() {

        double weight =
                (Double) view.getBeratInput()
                        .getValue();

        double volume =
                (Double) view.getVolumeInput()
                        .getValue();

        double harga =
                PricingUtil.hitungHarga(
                        weight,
                        volume
                );

        view.getHargaLabel().setText(
                PricingUtil.formatRupiah(harga)
        );
    }

}