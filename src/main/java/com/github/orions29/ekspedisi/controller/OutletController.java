package com.github.orions29.ekspedisi.controller;
import com.github.orions29.ekspedisi.views.OutletViews;
import javax.swing.*;

public class OutletController {
    private OutletViews view;

    public OutletController(OutletViews view){
        this.view = view;
        initController();
    }

    private void initController(){
        view.getInputPaketButton()
                .addActionListener(e -> {
                    handleInputPaket();
                });
    }

    private void handleInputPaket(){
        String senderName = view.getNamaPengirimInput().getText().trim();
        String receiverName = view.getNamaPenerimaInput().getText().trim();
        String destinationCity = view.getDestinasiPaketInput().getText().trim();
        String destinationAddress = view.getAlamatTujuanInput().getText().trim();
        String paketType = view.getTipePaketInput().getText().trim();
        double weight = (Double) view.getBeratInput().getValue();
        double volume = (Double) view.getVolumeInput().getValue();

        if (senderName.isEmpty()
        || receiverName.isEmpty()
        || destinationCity.isEmpty()
        || destinationAddress.isEmpty()
        || paketType.isEmpty()){
            JOptionPane.showMessageDialog(null, "Semua data wajib diisi!");
            return;
        }

        if (weight <= 0 || volume <= 0){
            JOptionPane.showMessageDialog(null, "Berat dan Volume harus lebih dari 0!");
            return;
        }

        String pesan = "Data Paket berhasil diproses!\n\n"
                + "Pengirim: " + senderName + "\n"
                + "Penerima: " + receiverName + "\n"
                + "Tujuan: " + destinationCity + "\n"
                + "Tipe Paket: " + paketType + "\n"
                + "Berat: " + weight + "kg\n"
                + "Volume: " + volume + "Cm3";

        JOptionPane.showMessageDialog(null, pesan);
    }
}
