package com.github.orions29.ekspedisi.utils;

/**
 * Project: EkspedisiMasGiant
 * Package: com.github.orions29.ekspedisi.utils
 * <p>
 * Mesin kalkulasi tarif logistik (Tarif Tunggal / Flat-Rate).
 * </p>
 *
 * <hr>
 * <table border="0">
 * <tr><td><b>Author</b></td><td>: Orions29</td></tr>
 * <tr><td><b>Date</b></td><td>: 1 June 2026</td></tr>
 * </table>
 * <hr>
 */
public class PricingUtil {

    private static final double PEMBAGI_VOLUME = 6000.0;
    private static final double HARGA_PER_KG = 10000.0;

    /**
     *
     * <h3>Hitung Charge Berat</h3>
     * <p> </p>
     *
     *
     * @author Orions29
     * @since 1 Jun 2026
     * @param actualWeight - Berat Asli Paket
     * @param volumeCm3 - Volume Paket
     * @return {@link double} - Hasil Perhitungan Charge
     *     */
    public static double hitungChargeBerat(double actualWeight, double volumeCm3) {
        double beratVolume = volumeCm3 / PEMBAGI_VOLUME;
        double chargeByWeight = Math.max(actualWeight, beratVolume);

        return Math.ceil(chargeByWeight);
    }

    /**
     *
     * <h3> Hitung Harga Asli</h3>
     * <p> </p>
     *
     *
     * @author Orions29
     * @since 1 Jun 2026
     * @param actualWeight - Berat Aseli paket
     * @param volumeCm3 - Dimensi Paket
     * @return {@link double} - Hasil Perhitungan Akhir (non Rupiah)
     *     */
    public static double hitungHarga(double actualWeight, double volumeCm3) {
        double chargeByWeight = hitungChargeBerat(actualWeight, volumeCm3);

        return chargeByWeight * HARGA_PER_KG;
    }

    /**
     *
     * <h3></h3>
     * <p> </p>
     *
     *
     * @author Orions29
     * @since 1 Jun 2026
     * @param nominal $END$ - Deskripsi fungsi parameter ini
     * @return {@link String} - Penjelasan mengenai data yang dikembalikan
     *     */
    public static String formatRupiah(double nominal) {
        return String.format("%,.2f", nominal).replace(',', 'X').replace('.', ',').replace('X', '.');
    }
}