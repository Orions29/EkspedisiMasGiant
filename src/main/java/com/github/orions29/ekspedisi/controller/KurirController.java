package com.github.orions29.ekspedisi.controller;
import com.github.orions29.ekspedisi.views.KurirViews;
import javax.swing.*;

public class KurirController {
    private KurirViews view;
    public KurirController(KurirViews view){
        this.view = view;
        initController();
    }

    private void initController(){
        view.getSubmitPaket().addActionListener(e -> {
            handleDeliveryUpdate();
        });
    }

    private void handleDeliveryUpdate(){
        String resi = view.getResiPaketIn().getText().trim();
        if (resi.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nomor resi wajib diisi");
            return;
        }
        JOptionPane.showMessageDialog(null, "Status pengiriman berhasil diperbarui!\n\n" + "Resi : " + resi);
        view.getResiPaketIn().setText("");
    }
}
