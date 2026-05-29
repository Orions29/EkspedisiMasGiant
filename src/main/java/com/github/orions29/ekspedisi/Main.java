package com.github.orions29.ekspedisi;

import com.github.orions29.ekspedisi.model.DatabaseConfig;
import com.github.orions29.ekspedisi.utils.ProjectInit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Project: Default (Template) Project
 * Package: com.github.orions29.eskpedisi
 * <p>
 * Deskripsi fungsional dari file ini.
 * </p>
 *
 * <hr>
 * <table border="0">
 * <tr><td><b>Author</b></td><td>: Orions29</td></tr>
 * <tr><td><b>Date</b></td><td>: 15 May 2026</td></tr>
 * <tr><td><b>Time</b></td><td>: 22:05</td></tr>
 * </table>
 * <hr>
 *
 * @author Orions29
 * @since 1.0
 */
public class Main {
    static void main() {
        Logger logger = LoggerFactory.getLogger(Main.class);
//        logger.info("Dummy Info Test");
//        logger.error("Dummy Error Test");
        System.out.println("Erlan, Roi, dan Giant Membuat Sebuah Ekspedisi Yang Paling Well");
        ProjectInit.projectCheck();

        Connection conn = DatabaseConfig.getConnection();

        System.out.println("Is Connected Now: "+ DatabaseConfig.isConnected());
        try{
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        System.out.println("Is Connected Now: "+ DatabaseConfig.isConnected());

    }
}
