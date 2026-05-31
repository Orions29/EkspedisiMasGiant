package com.github.orions29.ekspedisi.controller;
import com.github.orions29.ekspedisi.views.GudangViews;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GudangController {
    private GudangViews view;
    public GudangController(GudangViews view){
        this.view = view;
        initController();
    }

    private void initController(){
        view.getBtnUpdate().addActionListener(e -> {
            handleMassTransitUpdate();
        });
    }

    private void handleMassTransitUpdate(){
        String resi = view.getTxtResi().getText().trim();
        String status = (String) view.getComboStatus().getSelectedItem();
        if (resi.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nomor resi wajib diisi!");
            return;
        }

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String logMessage = "[" + timestamp +"]"
                + "SUCCESS : "
                + resi
                +" -> "
                + status
                + "\n";
        view.getTxtConsole().append(logMessage);
        view.getTxtConsole().setCaretPosition(view.getTxtConsole().getDocument().getLength());
        JOptionPane.showMessageDialog(null, "Status gudang berhasil diperbarui!");
        view.getTxtResi().setText("");
        view.getTxtResi().requestFocus();
    }
}
