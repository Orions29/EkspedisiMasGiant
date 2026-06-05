package com.github.orions29.ekspedisi.model.dao;

import com.github.orions29.ekspedisi.model.DatabaseConfig;
import com.github.orions29.ekspedisi.model.entity.Paket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Project: EkspedisiMasRoi
 * Package: com.github.orions29.ekspedisi.model.dao
 * <p>
 * Implementasi DAO untuk Paket.
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
public class PaketDAOMariaDb implements PaketDAO {

    private static final Logger logger = LoggerFactory.getLogger(PaketDAOMariaDb.class);

    @Override
    public boolean insertPaket(Paket paket) {
        String querySql = "INSERT INTO paket (resi_id, sender_name, origin_city, receiver_name, destination_city, destination_address, weight, volume, type_paket) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(querySql)) {

            stmt.setString(1, paket.getResiId());
            stmt.setString(2, paket.getSenderName());
            stmt.setString(3, paket.getOriginCity());
            stmt.setString(4, paket.getReceiverName());
            stmt.setString(5, paket.getDestinationCity());
            stmt.setString(6, paket.getDestinationAddress());
            stmt.setDouble(7, paket.getWeight());
            stmt.setDouble(8, paket.getVolume());
            stmt.setString(9, paket.getTypePaket());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Sukses - Resi [{}] berhasil dicetak ke database.", paket.getResiId());
                return true;
            }

        } catch (SQLException e) {
            // Error 1062: Duplicate Entry
            if (e.getErrorCode() == 1062) {
                logger.error("[INSERT FAILED] - Constraint Lock Resi Kembar [{}] sudah ada di database.", paket.getResiId());
            } else {
                logger.error("[QUERY ERROR] - Gagal menyimpan data resi [{}]: {}", paket.getResiId(), e.getMessage());
            }
        }
        return false;
    }

    @Override
    public Paket getPaketByResi(String resiId) {

        String querySql = "SELECT resi_id, sender_name, origin_city, receiver_name, destination_city, destination_address, weight, volume, type_paket, created_at " +
                "FROM paket WHERE resi_id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(querySql)) {

            stmt.setString(1, resiId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Paket(
                            rs.getString("resi_id"),
                            rs.getString("sender_name"),
                            rs.getString("origin_city"),
                            rs.getString("receiver_name"),
                            rs.getString("destination_city"),
                            rs.getString("destination_address"),
                            rs.getDouble("weight"),
                            rs.getDouble("volume"),
                            rs.getString("type_paket"),
                            rs.getTimestamp("created_at").toLocalDateTime()
                    );
                }
            }
        } catch (SQLException e) {
            logger.error("[QUERY ERROR] - Gagal melacak resi {}: {}", resiId, e.getMessage());
        }
        return null;
    }
}