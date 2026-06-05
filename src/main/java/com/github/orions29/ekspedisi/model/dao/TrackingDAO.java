package com.github.orions29.ekspedisi.model.dao;

import com.github.orions29.ekspedisi.model.entity.ShipmentLog;

import java.util.List;

/**
 * Project: EkspedisiMasRoi
 * Package: com.github.orions29.ekspedisi.model.dao
 * <p>
 * Interface DAO untuk Tracking
 * </p>
 *
 * <hr>
 * <table border="0">
 * <tr><td><b>Author</b></td><td>: Orions29</td></tr>
 * <tr><td><b>Date</b></td><td>: 30 May 2026</td></tr>
 * <tr><td><b>Time</b></td><td>: 10:29</td></tr>
 * </table>
 * <hr>
 *
 * @author Orions29
 * @since 1.0
 */
public interface TrackingDAO {

    /**
     *
     * <h3>Nambah log pada paket</h3>
     * <p> </p>
     *
     * @param log $END$ - Deskripsi fungsi parameter ini
     * @return {@link boolean} - Penjelasan mengenai data yang dikembalikan
     * @author Orions29
     * @since 30 May 2026
     *
     */
    boolean insertLog(ShipmentLog log);

    /**
     *
     * <h3>Get Shipment Log by Resi</h3>
     * <p> </p>
     *
     * @param resiId - Resi ID Paket
     * @return {@link List<ShipmentLog>} - Seluruh List Shipment Log
     * @author Orions29
     * @since 30 May 2026
     *
     */
    List<ShipmentLog> getShipmentLogByResi(String resiId);

    /**
     *
     * <h3>Mengambil Paket yang Masih Dipegang Kurir Tertentu</h3>
     * <p> </p>
     *
     * @param targetStatus $END$ - Status yang ingin dicari
     * @param userId       - UserID Kurir
     * @return {@link List<String>} - List Paket yang masih di pegang Kurir
     * @author Orions29
     * @since 2 Jun 2026
     *
     */
    List<String> getResiByLatestStatusAndUser(String targetStatus, String userId);
}
