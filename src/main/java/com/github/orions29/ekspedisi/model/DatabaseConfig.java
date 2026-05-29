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
 * untuk performa bare-metal.
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
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);

    private static Connection conn;

    private static final String DB_USER = SecretLoader.get("DB_USER");
    private static final String DB_PASS = SecretLoader.get("DB_PASS");
    private static final String DB_PORT = SecretLoader.get("DB_PORT");
    private static final String DB_HOST = SecretLoader.get("DB_HOST");
    private static final String DB_NAME = SecretLoader.get("DB_NAME");

    private static final String DB_URL = "jdbc:mariadb://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;

    public static Connection getConnection() throws RuntimeException {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                logger.info("Database Connection Josjis");
            }
        } catch (SQLException e) {
            logger.error("DB Connection Failed: {}", e.getMessage());

            throw new RuntimeException("Error saat membuka koneksi database", e);
        }
        return conn;
    }

    public static boolean testConnection() {
        try (Connection c = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            return c != null && !c.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }


    public static boolean isConnected() {
        try {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

}