package com.github.orions29.ekspedisi.views;

import com.github.orions29.ekspedisi.controller.LoginController;
import com.github.orions29.ekspedisi.model.entity.User;

import javax.swing.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Project: EkspedisiMasGiant
 * Package: com.github.orions29.ekspedisi.views
 * <p>
 * Antarmuka untuk Staf Gudang dengan injeksi DNA User absolut.
 * Dioptimalkan untuk integrasi Barcode Scanner (Input + Auto-Enter).
 * </p>
 *
 * <hr>
 * <table border="0">
 * <tr><td><b>Author</b></td><td>: Orions29</td></tr>
 * <tr><td><b>Date</b></td><td>: 30 May 2026</td></tr>
 * </table>
 * <hr>
 *
 * @author Orions29
 * @since 1.2
 */
public class GudangViews extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GudangViews.class.getName());

    // Kapsul identitas mutlak dari pekerja yang berhasil login
    private User loggedInUser;

    /**
     * Constructor yang memaksa injeksi data User.
     *
     * @param user Kapsul data User dari hasil DAO authenticate()
     */
    public GudangViews(User user) {
        // Keamanan Lapis 2: Bunuh jendela ini kalau ada yang iseng manggil tanpa login!
        if (user == null) {
            throw new IllegalArgumentException("Fatal: Akses ilegal! Jendela gudang wajib menerima data User yang valid.");
        }
        this.loggedInUser = user;

        initComponents();
        setupScannerListener();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labelUsername = new javax.swing.JLabel();
        labelLokasi = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        panelOperasi = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtResi = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        comboStatus = new javax.swing.JComboBox<>();
        btnUpdate = new javax.swing.JButton();

        jScrollPane1 = new javax.swing.JScrollPane();
        txtConsole = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("EMG Tracking System - Fasilitas Gudang");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel1.setText("Panel Gudang Ekspedisi");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel2.setText("Petugas :");

        // UI langsung merender data dari RAM (Tidak ada lagi hardcode!)
        labelUsername.setText(loggedInUser.getUsername() + " (" + loggedInUser.getId() + ")");

        labelLokasi.setFont(new java.awt.Font("Segoe UI", 2, 12));

        labelLokasi.setText("Bertugas di: " + (loggedInUser.getLocation() != null ? loggedInUser.getLocation() : "Lokasi Tidak Diketahui"));


        btnLogout.setText("Logout");
        btnLogout.addActionListener(this::btnLogoutActionPerformed);

        panelOperasi.setBorder(javax.swing.BorderFactory.createTitledBorder("Operasi Scan Paket"));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jLabel4.setText("Nomor Resi / Barcode:");

        txtResi.setFont(new java.awt.Font("Consolas", 1, 16));

        jLabel5.setText("Set Status Berikutnya:");

        comboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
                "Tiba di Fasilitas Sortir",
                "Sedang Disortir",
                "Transit Gudang",
                "Diserahkan ke Kurir"
        }));


        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 12));
        btnUpdate.setText("Update Status Logistik [ENTER]");
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);

        javax.swing.GroupLayout panelOperasiLayout = new javax.swing.GroupLayout(panelOperasi);
        panelOperasi.setLayout(panelOperasiLayout);
        panelOperasiLayout.setHorizontalGroup(
                panelOperasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelOperasiLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelOperasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panelOperasiLayout.createSequentialGroup()
                                                .addGroup(panelOperasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel4)
                                                        .addComponent(txtResi, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panelOperasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(panelOperasiLayout.createSequentialGroup()
                                                                .addComponent(jLabel5)
                                                                .addGap(0, 77, Short.MAX_VALUE))
                                                        .addComponent(comboStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap())
        );
        panelOperasiLayout.setVerticalGroup(
                panelOperasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelOperasiLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelOperasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelOperasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtResi, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                        .addComponent(comboStatus))
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtConsole.setEditable(false);
        txtConsole.setBackground(new java.awt.Color(0, 0, 0));
        txtConsole.setColumns(20);
        txtConsole.setFont(new java.awt.Font("Consolas", 0, 12));
        txtConsole.setForeground(new java.awt.Color(0, 255, 0));
        txtConsole.setRows(5);
        jScrollPane1.setViewportView(txtConsole);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel6.setText("Console Log Scan (Real-time):");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator1)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(panelOperasi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jLabel6)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jLabel2)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(labelUsername))
                                                                        .addComponent(labelLokasi))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(btnLogout)))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel2)
                                                        .addComponent(labelUsername))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(labelLokasi))
                                        .addComponent(btnLogout))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelOperasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }

    private void setupScannerListener() {
        txtResi.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnUpdate.doClick();
                }
            }
        });
    }

    private void btnLogoutActionPerformed(
            java.awt.event.ActionEvent evt
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Berhasil Logout dari sistem gudang."
        );

        javax.swing.JFrame frame =
                new javax.swing.JFrame(
                        "EMG Tracking System - Login"
                );

        LoginView loginView =
                new LoginView();

        new LoginController(loginView);

        frame.setContentPane(loginView);

        frame.setDefaultCloseOperation(
                javax.swing.JFrame.EXIT_ON_CLOSE
        );

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        this.dispose();
    }

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {
        String resiId = txtResi.getText().trim();
        String selectedStatus = (String) comboStatus.getSelectedItem();

        // 🚀 THE MAGIC: Ambil lokasi otomatis dari DNA User!
        String lokasiOtomatis = loggedInUser.getLocation();

        if (resiId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tembak barcode atau masukkan nomor resi dulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            txtResi.requestFocus();
            return;
        }

        /* TODO: PANGGIL TRACKING DAO DI SINI
           ShipmentLog logBaru = new ShipmentLog(resiId, loggedInUser.getId(), selectedStatus, lokasiOtomatis);
           boolean success = trackingDao.insertLog(logBaru);
        */

        // Simulasi Console yang diperbarui dengan lokasi otomatis
        String timestamp = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
        String logMessage = String.format("[%s] SUCCESS: Resi %s -> [%s] @ %s\n",
                timestamp, resiId, selectedStatus, lokasiOtomatis);

        txtConsole.append(logMessage);
        txtConsole.setCaretPosition(txtConsole.getDocument().getLength());

        txtResi.setText("");
        txtResi.requestFocus();
    }

    // Di produksi, UI ini dipanggil dari LoginViews.
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        SwingUtilities.invokeLater(() -> {

            // Simulasi Dummy User agar form tetap bisa dites lewat psvm main
            User dummyUser = new User("G-2001", "gudang_budi", "hash", "gudang", "Fasilitas Sortir Godean");
            GudangViews frame = new GudangViews(dummyUser);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public javax.swing.JButton getBtnUpdate() {
        return btnUpdate;
    }

    public javax.swing.JTextField getTxtResi() {
        return txtResi;
    }

    public javax.swing.JComboBox<String> getComboStatus() {
        return comboStatus;
    }

    public javax.swing.JTextArea getTxtConsole() {
        return txtConsole;
    }

    // Variables declaration
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> comboStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelUsername;
    private javax.swing.JLabel labelLokasi;
    private javax.swing.JPanel panelOperasi;
    private javax.swing.JTextArea txtConsole;
    private javax.swing.JTextField txtResi;
    // End of variables declaration
}