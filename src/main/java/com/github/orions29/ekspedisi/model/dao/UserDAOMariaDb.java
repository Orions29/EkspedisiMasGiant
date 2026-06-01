package com.github.orions29.ekspedisi.model.dao;

import com.github.orions29.ekspedisi.model.DatabaseConfig;
import com.github.orions29.ekspedisi.model.entity.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Project: EkspedisiMasGiant
 * Package: com.github.orions29.ekspedisi.model.dao
 * <p>
 * User DAO Implement untuk MariaDB dan MySQL.
 * Sudah mendukung injeksi lokasi mutlak.
 * </p>
 *
 * <hr>
 * <table border="0">
 * <tr><td><b>Author</b></td><td>: Orions29</td></tr>
 * <tr><td><b>Date</b></td><td>: 30 May 2026</td></tr>
 * <tr><td><b>Time</b></td><td>: 11:21</td></tr>
 * </table>
 * <hr>
 *
 * @author Orions29
 * @since 1.1
 */
public class UserDAOMariaDb implements UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDAOMariaDb.class);

    @Override
    public User authenticate(String username, String passwordHash) {
        String sql = "SELECT id, username, password_hash, role, location FROM users WHERE username = ? AND password_hash = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, passwordHash);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    logger.info("[AUTH] - Autentikasi lolos untuk user: {}", username);
                    return new User(
                            rs.getString("id"),
                            rs.getString("username"),
                            rs.getString("password_hash"),
                            rs.getString("role"),
                            rs.getString("location")
                    );
                } else {
                    logger.warn("[AUTH] - Upaya login gagal untuk username: {}", username);
                }
            }
        } catch (SQLException e) {
            logger.error("[QUERY ERROR] - Query gagal dijalankan: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public User getUserById(String userId) {
        String sql = "SELECT id, username, password_hash, role, location FROM users WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getString("id"),
                            rs.getString("username"),
                            rs.getString("password_hash"),
                            rs.getString("role"),
                            rs.getString("location")
                    );
                }
            }
        } catch (SQLException e) {
            logger.error("[QUERY ERROR] - Gagal menarik data pekerja ID {}: {}", userId, e.getMessage());
        }
        return null;
    }

    @Override
    public boolean insertUser(User user) {
        String sql = "INSERT INTO users (id, username, password_hash, role, location) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getId());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPasswordHash());
            stmt.setString(4, user.getRole());
            stmt.setString(5, user.getLocation()); // Injeksi lokasi baru

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Rekrutmen Sukses! Pekerja baru [{}] (Role: {}) berhasil diinjeksi.", user.getUsername(), user.getRole());
                return true;
            }

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                logger.error("[INSERT FAILED] - ID atau Username [{}] sudah dipakai orang lain!", user.getUsername());
            } else {
                logger.error("[QUERY ERROR] - Gagal Insert Pekerja [{}]: {}", user.getUsername(), e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        String sql = "UPDATE users SET username = ?, password_hash = ?, role = ?, location = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPasswordHash());
            stmt.setString(3, user.getRole());
            stmt.setString(4, user.getLocation());
            stmt.setString(5, user.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Update Sukses!, Data pekerja ID [{}] berhasil diperbarui.", user.getId());
                return true;
            } else {
                logger.warn("[UPDATE FAILED] - Pekerja dengan ID [{}] fiktif atau tidak ditemukan di MariaDB.", user.getId());
            }

        } catch (SQLException e) {
            logger.error("[QUERY ERROR] - saat update pekerja ID [{}]: {}", user.getId(), e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteUser(String userId) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userId);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                logger.info("Sukses menghapus pekerja dengan ID [{}]", userId);
                return true;
            }
        } catch (SQLException e) {
            logger.error("[QUERY ERROR] - Gagal menghapus UserID [{}]: {}", userId, e.getMessage());
        }
        return false;
    }
}