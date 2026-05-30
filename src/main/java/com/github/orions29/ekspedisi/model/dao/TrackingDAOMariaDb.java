package com.github.orions29.ekspedisi.model.dao;

import com.github.orions29.ekspedisi.model.DatabaseConfig;
import com.github.orions29.ekspedisi.model.entity.ShipmentLog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: EkspedisiMasGiant
 * Package: com.github.orions29.ekspedisi.model.dao
 * <p>
 * Implementasi bare-metal DAO untuk riwayat Tracking.
 * Menghajar port 3306 untuk mencatat setiap detak pergerakan paket.
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
 * @since 1.0
 */
public class TrackingDAOMariaDb implements TrackingDAO {

    private static final Logger logger = LoggerFactory.getLogger(TrackingDAOMariaDb.class);

    @Override
    public boolean insertLog(ShipmentLog log) {
        // Timestamp diurus langsung oleh DEFAULT CURRENT_TIMESTAMP di MariaDB
        String sql = "INSERT INTO tracking_logs (resi_id, user_id, status, location) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, log.getResiId());
            stmt.setString(2, log.getUserId());
            stmt.setString(3, log.getStatus());
            stmt.setString(4, log.getLocation());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("[TRACKING] Jejak baru dicatat! Resi [{}] -> Status: {} di {}",
                        log.getResiId(), log.getStatus(), log.getLocation());
                return true;
            }

        } catch (SQLException e) {

            // Error handling spesifik untuk Foreign Key constraint (jika resi_id tidak ada di tabel paket)
            if (e.getErrorCode() == 1452) {
                logger.error("[INSERT FAILED] - Resi [{}] fiktif! Tidak ditemukan di tabel induk 'paket'.", log.getResiId());
            } else {
                logger.error("[QUERY ERROR] - Gagal mencatat log resi [{}]: {}", log.getResiId(), e.getMessage());
            }
        }
        return false;
    }

    @Override
    public List<ShipmentLog> getShipmentLogByResi(String resiId) {
        String sql = "SELECT id, resi_id, user_id, status, location, timestamp " + "FROM tracking_logs WHERE resi_id = ? ORDER BY timestamp ASC";

        List<ShipmentLog> logHistory = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, resiId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ShipmentLog log = new ShipmentLog(
                            rs.getInt("id"),
                            rs.getString("resi_id"),
                            rs.getString("location"),
                            rs.getString("status"),
                            rs.getTimestamp("timestamp").toLocalDateTime(),
                            rs.getString("user_id")
                    );
                    logHistory.add(log);
                }
                logger.info("[TRACKING] Ditemukan {} jejak riwayat untuk Resi [{}]", logHistory.size(), resiId);
            }
        } catch (SQLException e) {
            logger.error("[QUERY ERROR] - Gagal menarik riwayat tracking resi [{}]: {}", resiId, e.getMessage());
        }

        return logHistory;
    }
}