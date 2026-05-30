package com.github.orions29.ekspedisi.model.dao;

import com.github.orions29.ekspedisi.model.entity.ShipmentLog;

import java.util.List;

/**
 * Project: EkspedisiMasGiant
 * Package: com.github.orions29.ekspedisi.model.dao
 * <p>
 * Deskripsi fungsional dari file ini.
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
     *
     * @author Orions29
     * @since 30 May 2026
     * @param log $END$ - Deskripsi fungsi parameter ini
     * @return {@link boolean} - Penjelasan mengenai data yang dikembalikan
     *     */
    boolean insertLog(ShipmentLog log);

    /**
     *
     * <h3>Get Shipment Log by Resi</h3>
     * <p> </p>
     *
     *
     * @author Orions29
     * @since 30 May 2026
     * @param resiId - Resi ID Paket
     * @return {@link List<ShipmentLog>} - Seluruh List Shipment Log
     * */
    List<ShipmentLog> getShipmentLogByResi(String resiId);
}
