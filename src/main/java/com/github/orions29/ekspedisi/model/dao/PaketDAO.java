package com.github.orions29.ekspedisi.model.dao;

import com.github.orions29.ekspedisi.model.entity.Paket;

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
public interface PaketDAO {
    /**
     *
     * <h3>Insert Paket</h3>
     * <p> nambahin paket</p>
     *
     *
     * @author Orions29
     * @since 30 May 2026
     * @param paket $END$ - Deskripsi fungsi parameter ini
     * @return {@link boolean} - Penjelasan mengenai data yang dikembalikan
     * */
    boolean insertPaket(Paket paket);

    /**
     *
     * <h3>Ambil Info Paket by Resi</h3>
     * <p> Ngambil info paket dari Resi ID</p>
     *
     *
     * @author Orions29
     * @since 30 May 2026
     * @param resiId - Deskripsi fungsi parameter ini
     * @return {@link Paket} - Penjelasan mengenai data yang dikembalikan
     * */
    Paket getPaketByResi(String resiId);
}
