package com.github.orions29.ekspedisi.views;

import com.github.orions29.ekspedisi.model.dao.UserDAO;
import com.github.orions29.ekspedisi.model.dao.UserDAOMariaDb;
import com.github.orions29.ekspedisi.model.entity.User;
import com.github.orions29.ekspedisi.utils.GeneratorId;
import com.github.orions29.ekspedisi.utils.HashUtil;

import javax.swing.*;

/**
 * Project: EkspedisiMasGiant
 * Package: com.github.orions29.ekspedisi.views
 * <p>
 * Panel kontrol Admin dengan kapabilitas Tuhan boyyyyy.
 * </p>
 *
 * <hr>
 * <table border="0">
 * <tr><td><b>Author</b></td><td>: Orions29</td></tr>
 * <tr><td><b>Date</b></td><td>: 1 June 2026</td></tr>
 * </table>
 * <hr>
 */
public class AdminViews extends javax.swing.JFrame {

    private final User loggedInAdmin;
    private final UserDAO userDao;

    public AdminViews(User admin) {
        if (admin == null || !"admin".equalsIgnoreCase(admin.getRole())) {
            throw new IllegalArgumentException("Akses Ilegal: Khusus Administrator.");
        }

        this.loggedInAdmin = admin;
        this.userDao = new UserDAOMariaDb();

        initComponents();
        setupFlatDesign();

        lblAdminInfo.setText(loggedInAdmin.getUsername() + " (" + loggedInAdmin.getId() + ")");
    }

    private void setupFlatDesign() {
        JButton[] buttons = {btnCari, btnSimpan, btnUpdate, btnHapus, btnLogout};
        java.awt.Color[] colors = {
                new java.awt.Color(102, 102, 102),
                new java.awt.Color(0, 102, 153),
                new java.awt.Color(204, 102, 0),
                new java.awt.Color(153, 0, 0),
                new java.awt.Color(153, 0, 51)
        };

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setBackground(colors[i]);
            buttons[i].setForeground(new java.awt.Color(255, 255, 255));
            buttons[i].setOpaque(true);
            buttons[i].setContentAreaFilled(true);
            buttons[i].setBorderPainted(false);
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblAdminInfo = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        panelForm = new javax.swing.JPanel();
        jLabelId = new javax.swing.JLabel();
        txtUserId = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();

        jLabel2 = new javax.swing.JLabel();
        comboRole = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        txtLokasi = new javax.swing.JTextField();

        btnSimpan = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();

        jScrollPane1 = new javax.swing.JScrollPane();
        txtConsole = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("EMG System - Administrator");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel1.setText("Panel Administrator");

        lblAdminInfo.setFont(new java.awt.Font("Segoe UI", 2, 12));
        lblAdminInfo.setText("admin_name (A-0000)");

        btnLogout.setText("Logout");
        btnLogout.addActionListener(this::btnLogoutActionPerformed);

        panelForm.setBorder(javax.swing.BorderFactory.createTitledBorder("Manajemen Data Pekerja"));

        jLabelId.setText("ID Pekerja (Kosongkan jika tambah baru):");
        btnCari.setText("Cari Data");
        btnCari.addActionListener(this::btnCariActionPerformed);

        jLabel2.setText("Role Pekerja:");
        comboRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Loket", "Gudang", "Kurir", "Admin"}));

        jLabel3.setText("Username:");
        jLabel4.setText("Password (Isi untuk simpan/update):");
        jLabel5.setText("Penempatan Lokasi (Kosongkan jika Kurir):");

        btnSimpan.setFont(new java.awt.Font("Segoe UI", 1, 12));
        btnSimpan.setText("Simpan Baru");
        btnSimpan.addActionListener(this::btnSimpanActionPerformed);

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 12));
        btnUpdate.setText("Update Data");
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);

        btnHapus.setFont(new java.awt.Font("Segoe UI", 1, 12));
        btnHapus.setText("Hapus Pekerja");
        btnHapus.addActionListener(this::btnHapusActionPerformed);

        javax.swing.GroupLayout panelFormLayout = new javax.swing.GroupLayout(panelForm);
        panelForm.setLayout(panelFormLayout);
        panelFormLayout.setHorizontalGroup(
                panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelFormLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelFormLayout.createSequentialGroup()
                                                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabelId)
                                                        .addComponent(txtUserId, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnCari))
                                        .addGroup(panelFormLayout.createSequentialGroup()
                                                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jLabel2)
                                                        .addComponent(comboRole, 0, 150, Short.MAX_VALUE)
                                                        .addComponent(jLabel4)
                                                        .addComponent(txtPassword))
                                                .addGap(18, 18, 18)
                                                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtUsername)
                                                        .addComponent(txtLokasi)
                                                        .addGroup(panelFormLayout.createSequentialGroup()
                                                                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel3)
                                                                        .addComponent(jLabel5))
                                                                .addGap(0, 0, Short.MAX_VALUE))))
                                        .addGroup(panelFormLayout.createSequentialGroup()
                                                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        panelFormLayout.setVerticalGroup(
                panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelFormLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabelId)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtUserId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCari))
                                .addGap(18, 18, 18)
                                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(comboRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtLokasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtConsole.setEditable(false);
        txtConsole.setBackground(new java.awt.Color(0, 0, 0));
        txtConsole.setColumns(20);
        txtConsole.setFont(new java.awt.Font("Consolas", 0, 12));
        txtConsole.setForeground(new java.awt.Color(0, 255, 0));
        txtConsole.setRows(5);
        jScrollPane1.setViewportView(txtConsole);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator1)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1)
                                                        .addComponent(lblAdminInfo))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnLogout))
                                        .addComponent(jScrollPane1))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblAdminInfo))
                                        .addComponent(btnLogout, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }

    private void logToConsole(String message) {
        String timestamp = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
        txtConsole.append(String.format("[%s] %s\n", timestamp, message));
        txtConsole.setCaretPosition(txtConsole.getDocument().getLength());
    }

    private void clearForm() {
        txtUserId.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtLokasi.setText("");
        comboRole.setSelectedIndex(0);
    }

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Logout dari Administrator System.");
        this.dispose();
    }

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {
        String targetId = txtUserId.getText().trim();
        if (targetId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan ID Pekerja terlebih dahulu.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        User user = userDao.getUserById(targetId);
        if (user != null) {
            txtUsername.setText(user.getUsername());
            txtLokasi.setText(user.getLocation() != null ? user.getLocation() : "");
            comboRole.setSelectedItem(user.getRole().substring(0, 1).toUpperCase() + user.getRole().substring(1).toLowerCase());
            txtPassword.setText("");
            logToConsole("DATA DITEMUKAN: " + user.getId() + " | " + user.getUsername());
        } else {
            logToConsole("DATA TIDAK DITEMUKAN: ID " + targetId + " fiktif.");
            JOptionPane.showMessageDialog(this, "Pekerja tidak ditemukan di database.", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {
        String role = comboRole.getSelectedItem().toString().toLowerCase();
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();
        String lokasi = txtLokasi.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username dan Password wajib diisi untuk data baru!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (lokasi.isEmpty() && !role.equals("kurir")) {
            JOptionPane.showMessageDialog(this, "Lokasi wajib diisi untuk staf Loket dan Gudang!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String idBaru = GeneratorId.generateUserId(role);
        String hashPass = HashUtil.hashSHA256(password);
        String finalLokasi = (lokasi.isEmpty() && role.equals("kurir")) ? null : lokasi;

        User userBaru = new User(idBaru, username, hashPass, role, finalLokasi);

        if (userDao.insertUser(userBaru)) {
            logToConsole("INSERT SUKSES: " + idBaru + " | " + username);
            clearForm();
        } else {
            logToConsole("INSERT GAGAL: Kemungkinan username sudah dipakai.");
        }
    }

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {
        String targetId = txtUserId.getText().trim();
        if (targetId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cari ID Pekerja terlebih dahulu sebelum melakukan update.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String role = comboRole.getSelectedItem().toString().toLowerCase();
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();
        String lokasi = txtLokasi.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username dan Password tidak boleh kosong saat update. Silakan ketik ulang password.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String finalLokasi = (lokasi.isEmpty() && role.equals("kurir")) ? null : lokasi;
        String hashPass = HashUtil.hashSHA256(password);

        User updatedUser = new User(targetId, username, hashPass, role, finalLokasi);

        if (userDao.updateUser(updatedUser)) {
            logToConsole("UPDATE SUKSES: Data untuk ID " + targetId + " telah diperbarui.");
            clearForm();
        } else {
            logToConsole("UPDATE GAGAL: Terjadi kesalahan pada database.");
        }
    }

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {
        String targetId = txtUserId.getText().trim();
        if (targetId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan ID Pekerja yang ingin dihapus.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(this, "Hapus pekerja " + targetId + " secara permanen?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (konfirmasi == JOptionPane.YES_OPTION) {
            if (userDao.deleteUser(targetId)) {
                logToConsole("DELETE SUKSES: Pekerja " + targetId + " dihapus permanen.");
                clearForm();
            } else {
                logToConsole("DELETE GAGAL: ID tidak ditemukan atau terikat pada data logistik.");
            }
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            User dummyAdmin = new User("A-0001", "super_admin", "hash", "admin", "Pusat HQ");
            AdminViews frame = new AdminViews(dummyAdmin);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> comboRole;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAdminInfo;
    private javax.swing.JPanel panelForm;
    private javax.swing.JTextArea txtConsole;
    private javax.swing.JTextField txtLokasi;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUserId;
    private javax.swing.JTextField txtUsername;
}