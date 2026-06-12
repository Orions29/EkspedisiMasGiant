package com.github.orions29.ekspedisi.views;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicBoolean;

import com.github.sarxos.webcam.ds.openimaj.OpenImajDriver;

public class CameraScannerDialog extends JDialog {

    static {
        // Mengubah driver menjadi OpenIMAJ to fix OBS Virtual Camera buffer size issues
        Webcam.setDriver(new OpenImajDriver());
    }

    private JComboBox<Webcam> webcamComboBox;
    private WebcamPanel webcamPanel;
    private Webcam webcam;
    private Thread scannerThread;
    private AtomicBoolean running = new AtomicBoolean(false);
    private String scannedResult = null;
    private JPanel centerPanel;

    public CameraScannerDialog(JFrame parent) {
        super(parent, "Scan QR / Barcode via Kamera", true);
        setLayout(new BorderLayout());
        setSize(640, 560);
        setLocationRelativeTo(parent);

        initUI();
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stopScanner();
            }
        });
    }

    private void initUI() {
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Pilih Kamera: "));

        webcamComboBox = new JComboBox<>();
        for (Webcam w : Webcam.getWebcams()) {
            webcamComboBox.addItem(w);
        }
        
        if (webcamComboBox.getItemCount() > 0) {
            webcamComboBox.setSelectedIndex(0);
        }

        JButton btnStart = new JButton("Mulai Scan");
        btnStart.addActionListener(e -> startScanner());

        topPanel.add(webcamComboBox);
        topPanel.add(btnStart);

        add(topPanel, BorderLayout.NORTH);

        centerPanel = new JPanel(new BorderLayout());
        add(centerPanel, BorderLayout.CENTER);
    }

    private void startScanner() {
        if (running.get()) {
            return;
        }

        Webcam selectedWebcam = (Webcam) webcamComboBox.getSelectedItem();
        if (selectedWebcam == null) {
            JOptionPane.showMessageDialog(this, "Tidak ada kamera yang dipilih!");
            return;
        }

        if (webcam != null && webcam.isOpen()) {
            webcam.close();
        }

        webcam = selectedWebcam;

        // Coba set resolusi VGA — kalau gagal (misalnya OBS Virtual Camera
        // yang memiliki resolusi berbeda), fallback ke resolusi native kamera
        try {
            webcam.setViewSize(WebcamResolution.VGA.getSize());
        } catch (Exception ex) {
            // Biarkan kamera pakai resolusi default-nya sendiri
            System.out.println("[CameraScanner] VGA tidak didukung, pakai resolusi default: " + ex.getMessage());
        }

        try {
            webcamPanel = new WebcamPanel(webcam);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Gagal membuka kamera: " + ex.getMessage() +
                    "\nCoba pilih kamera lain atau restart OBS Virtual Camera.",
                    "Error Kamera", JOptionPane.ERROR_MESSAGE);
            running.set(false);
            return;
        }
        webcamPanel.setFPSDisplayed(true);
        webcamPanel.setMirrored(false);

        centerPanel.removeAll();
        centerPanel.add(webcamPanel, BorderLayout.CENTER);
        centerPanel.revalidate();
        centerPanel.repaint();

        running.set(true);

        // Setup hints untuk meningkatkan sensitivitas decode QR
        java.util.Map<DecodeHintType, Object> hints = new java.util.EnumMap<>(DecodeHintType.class);
        hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        hints.put(DecodeHintType.POSSIBLE_FORMATS, java.util.Arrays.asList(
                BarcodeFormat.QR_CODE,
                BarcodeFormat.CODE_128,
                BarcodeFormat.CODE_39
        ));
        MultiFormatReader reader = new MultiFormatReader();
        reader.setHints(hints);

        scannerThread = new Thread(() -> {
            while (running.get()) {
                try {
                    Thread.sleep(200); // 200ms delay to save CPU
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }

                if (!webcam.isOpen()) {
                    continue;
                }

                BufferedImage image = webcam.getImage();
                if (image == null) {
                    continue;
                }

                String detectedText = null;

                // Coba decode langsung
                try {
                    LuminanceSource source = new BufferedImageLuminanceSource(image);
                    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                    Result result = reader.decodeWithState(bitmap);
                    if (result != null) {
                        detectedText = result.getText();
                    }
                } catch (NotFoundException e) {
                    // coba dengan gambar diflip horizontal sebagai fallback
                } finally {
                    reader.reset();
                }

                // Fallback: coba gambar diflip jika belum berhasil
                if (detectedText == null) {
                    try {
                        BufferedImage flipped = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
                        java.awt.Graphics2D g2d = flipped.createGraphics();
                        g2d.drawImage(image, image.getWidth(), 0, -image.getWidth(), image.getHeight(), null);
                        g2d.dispose();

                        LuminanceSource source = new BufferedImageLuminanceSource(flipped);
                        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                        Result result = reader.decodeWithState(bitmap);
                        if (result != null) {
                            detectedText = result.getText();
                        }
                    } catch (NotFoundException e) {
                        // tidak terdeteksi di frame ini
                    } finally {
                        reader.reset();
                    }
                }

                if (detectedText != null) {
                    // Pause scanner dan keluar dari loop sebelum show dialog
                    running.set(false);
                    final String finalText = detectedText;

                    SwingUtilities.invokeLater(() -> {
                        int option = JOptionPane.showConfirmDialog(
                                CameraScannerDialog.this,
                                "Terdeteksi resi: " + finalText + "\nApakah anda ingin menggunakan resi ini?",
                                "Konfirmasi Resi",
                                JOptionPane.YES_NO_OPTION
                        );

                        if (option == JOptionPane.YES_OPTION) {
                            scannedResult = finalText;
                            stopScanner();
                            dispose();
                        } else {
                            // Lanjutkan scanning
                            running.set(true);
                        }
                    });

                    // Langsung break agar thread tidak lanjut decode frame berikutnya
                    break;
                }
            }
        });

        scannerThread.start();
    }

    private void stopScanner() {
        running.set(false);
        if (scannerThread != null && scannerThread.isAlive()) {
            scannerThread.interrupt();
        }
        if (webcam != null && webcam.isOpen()) {
            webcam.close();
        }
    }

    public String getScannedResult() {
        return scannedResult;
    }
}
