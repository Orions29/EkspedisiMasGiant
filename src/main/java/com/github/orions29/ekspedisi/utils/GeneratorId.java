package com.github.orions29.ekspedisi.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Project: EkspedisiMasGiant
 * Package: com.github.orions29.ekspedisi.utils
 * <p>
 * Class untuk pembuatan ID baik itu resi ataupun userId
 * </p>
 *
 * <hr>
 * <table border="0">
 * <tr><td><b>Author</b></td><td>: Orions29</td></tr>
 * <tr><td><b>Date</b></td><td>: 30 May 2026</td></tr>
 * <tr><td><b>Time</b></td><td>: 11:51</td></tr>
 * </table>
 * <hr>
 *
 * @author Orions29
 * @since 1.0
 */
public class GeneratorId {
    /**
     *
     * <h3>Generate Resi ID</h3>
     * <p> </p>
     *
     * @return {@link String} - hasil Resi ID
     * @author Orions29
     * @since 30 May 2026
     *
     */
    public static String generateResi() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmm");
        String timePart = now.format(formatter);

        // Ambil 4 karakter acak dari UUID untuk mencegah bentrok di detik yang sama
        String randomPart = UUID.randomUUID().toString().substring(0, 4).toUpperCase();

        return "RESI-" + timePart + "-" + randomPart;
    }

    /**
     *
     * <h3>Pembuat user ID</h3>
     * <p>
     * Mencetak ID Pekerja berdasarkan Role.
     * Format: [InisialRole]-[Random 4 Karakter]
     * Contoh: K-F72A (Kurir), L-90BC (Loket), G-11X2 (Gudang)
     * </p>
     *
     * @param role - Role user
     * @return {@link String} - UserId yang sudah dibuat
     * @author Orions29
     * @since 30 May 2026
     *
     */
    public static String generateUserId(String role) {
        String prefix = "U";
        if (role != null) {
            switch (role.toLowerCase()) {
                case "kurir":
                    prefix = "K";
                    break;
                case "loket":
                    prefix = "L";
                    break;
                case "gudang":
                    prefix = "G";
                    break;
                case "admin":
                    prefix = "A";
                    break;
            }
        }

        String randomPart = UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        return prefix + "-" + randomPart;
    }
}
