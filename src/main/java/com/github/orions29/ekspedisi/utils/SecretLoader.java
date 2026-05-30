package com.github.orions29.ekspedisi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Paths;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Project: EkspedisiMasGiant
 * Package: com.github.orions29.ekspedisi.utils
 * <p>
 * Untuk memuat ENV di root project
 * </p>
 *
 * <hr>
 * <table border="0">
 * <tr><td><b>Author</b></td><td>: Orions29</td></tr>
 * <tr><td><b>Date</b></td><td>: 29 May 2026</td></tr>
 * <tr><td><b>Time</b></td><td>: 18:45</td></tr>
 * </table>
 * <hr>
 *
 * @author Orions29
 * @since 1.0
 */
public class SecretLoader {
    //    Nyimpen Objek isi File .env disini
    private final static Dotenv env;
//Objek Logger
    private static final Logger logger = LoggerFactory.getLogger(SecretLoader.class);

    //    Error handling untuk loading
    static {
        Dotenv tempEnv = null;
        try {
            tempEnv = Dotenv.configure().directory("./").load();
        } catch (Exception e) {
            logger.error("[FATAL ERROR] - .env Ga Ke Load"+e.getMessage());
        }
        env = tempEnv;
    }

    /**
     *
     * <h3>Is Env Load</h3>
     * <p> Ngecek apakah file .env ke load</p>
     *
     * @return {@link boolean} - `False` kalau dia ga keload `True` kalau keload
     * @author Orions29
     * @since 29 May 2026
     *
     */
    public static boolean isLoad() {
        return Files.exists(Paths.get(".env"));
    }

    /**
     *
     * <h3>Cek Key Values</h3>
     * <p> apakah di file .env mengandung key tertentu </p>
     *
     * @param key $END$ - key yang mau di cek valuesnya
     * @return {@link boolean} - `False` kalau dia ga keload `True` kalau keload
     * @author Orions29
     * @since 29 May 2026
     *
     */
    public static boolean isContain(String key) {
        if (env==null || env.get(key) == null) {
            return false;
        }
        return true;
    }

    /**
     *
     * <h3>Cek Key Values dan Menampilkan</h3>
     * <p>apakah di file .env mengandung key tertentu dan menampilkannya </p>
     *
     * @param key $END$ - Deskripsi fungsi parameter ini
     * @return {@link String} - Penjelasan mengenai data yang dikembalikan
     * @author Orions29
     * @since 29 May 2026
     *
     */
    public static String isContainDisplay(String key) {
        String display;
        if (env== null) {
            return "false";
        }
        display = env.get(key);
        if (display == null) {
        }
        return key + "=" + display;
    }


    /**
     *
     * <h3>Ngambil Env dari .env</h3>
     * <p> Ngambil Env values dari .env ya</p>
     *
     * @param key - list env apa yang mau kamu ambil
     * @return {@link String} - Ngembaliin value .env tanpa ada manipulasi
     * @author Orions29
     * @since 29 May 2026
     *
     */
    public static String get(String key) {
        if (env == null || (env.get(key) == null)) {
            return null;
        }
        return env.get(key);
    }


}
