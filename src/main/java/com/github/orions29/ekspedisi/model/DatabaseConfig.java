package com.github.orions29.ekspedisi.model;

import com.github.orions29.ekspedisi.utils.SecretLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Project: EkspedisiMasGiant
 * Package: com.github.orions29.ekspedisi.model
 * <p>
 * Konfigurasi koneksi database MariaDB dengan pola Singleton murni
 * </p>
 *
 * <hr>
 * <table border="0">
 * <tr><td><b>Author</b></td><td>: Orions29</td></tr>
 * <tr><td><b>Date</b></td><td>: 29 May 2026</td></tr>
 * <tr><td><b>Time</b></td><td>: 22:48</td></tr>
 * </table>
 * <hr>
 *
 * @author Orions29
 * @since 1.0
 */
public class DatabaseConfig {
    //    Logger
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);

    private static Connection conn;

    private static final String DB_USER = SecretLoader.get("DB_USER");
    private static final String DB_PASS = SecretLoader.get("DB_PASS");
    private static final String DB_PORT = SecretLoader.get("DB_PORT");
    private static final String DB_HOST = SecretLoader.get("DB_HOST");
    private static final String DB_NAME = SecretLoader.get("DB_NAME");

    private static final String DB_URL = "jdbc:mariadb://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;

    //    biar gaada yang ngenew
    private DatabaseConfig() {
    }

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    logger.info("Database connection closed safely via JVM Shutdown Hook");
                }
            } catch (SQLException e) {
                logger.warn("[WARN] - Error closing database connection during shutdown", e);
            }
        }));
    }

    /**
     *
     * <h3>Membuat Koneksi SQL</h3>
     * <p> Membuat Koneksi ke Database MariaDB dengan safe net </p>
     *
     * @return {@link Connection} - Ngembaliin Objek Koneksi
     * @throws RuntimeException Kalau misal something wong maka langsung ngeluarin sesuatu
     * @author Orions29
     * @since 30 May 2026
     *
     */
    public static synchronized Connection getConnection() throws RuntimeException {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
//                logger.info("Database Connection Josjis");
            }
        } catch (SQLException e) {
            logger.error("[FATAL ERROR] - DB Connection Failed: {}", e.getMessage());
//            Ngethrow Error Fatal Ke Terminal
            throw new RuntimeException("[FATAL ERROR] - Error saat membuka koneksi database", e);
        }
        return conn;
    }

    /**
     *
     * <h3>Cek Koneksi Database</h3>
     * <p> </p>
     *
     * @return {@link boolean} - True Artinya masih terkoneksi False Artinya dia no no
     * @author Orions29
     * @since 30 May 2026
     *
     */
    public static boolean isConnected() {
        try {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     *
     * <h3> Force Close koneksi</h3>
     * <p> kalau mau manual ngeclose connection buat testing BUAT TESTING</p>
     *
     *
     * @author Orions29
     * @since 30 May 2026
     *     */
    public static synchronized void forceCloseConn() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                logger.info("Database connection closed manually via forceCloseConn");
            }
        } catch (SQLException e) {
            logger.warn("Error closing database connection", e);
        }
    }

}