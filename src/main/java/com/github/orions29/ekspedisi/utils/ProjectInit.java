package com.github.orions29.ekspedisi.utils;

import com.github.orions29.ekspedisi.model.DatabaseConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

/**
 * Project: EkspedisiMasRoi
 * Package: com.github.orions29.ekspedisi.utils
 * <p>
 * Berfungsi melakukan checklist project ini
 * </p>
 *
 * <hr>
 * <table border="0">
 * <tr><td><b>Author</b></td><td>: Orions29</td></tr>
 * <tr><td><b>Date</b></td><td>: 29 May 2026</td></tr>
 * <tr><td><b>Time</b></td><td>: 21:52</td></tr>
 * </table>
 * <hr>
 *
 * @author Orions29
 * @since 1.0
 */
public class ProjectInit {
    private static Logger logger = LoggerFactory.getLogger(ProjectInit.class);

    /**
     *
     * <h3>Project Initiation Checklist</h3>
     * <p> Ngechecklist yang perlu di check </p>
     *
     * @author Orions29
     * @since 29 May 2026
     *
     */
    public static void projectCheck() {
        logger.info("Project Check Init Start");
        System.out.println(">> ProjectInit CheckList: ");


//        Checklist ENV
        boolean isEnvLoaded = SecretLoader.isLoad();
        System.out.println(isEnvLoaded ? "ENV : PASS" : "ENV : FAILED");

//        Checklist Database
        boolean isDbConnected = false;
        try {
            Connection testConn = DatabaseConfig.getConnection();
            isDbConnected = DatabaseConfig.isConnected();
        } catch (Exception e) {
//            System.err.println(e.getMessage());
            isDbConnected = false;
        }
        System.out.println(isDbConnected ? "DB CONN  : PASS" : "DB CONN  : FAILED");

//      TODO - Database Integrity (Kelengkapan Table)
        boolean isDbGood = false;
        System.out.println(isDbGood ? "DB Validity  : PASS" : "DB Validity  : FAILED");
        logger.info("Project Check Done");

//        Kalau ada yang gagal gaboleh Jalan Titik.
        if (!isEnvLoaded || !isDbConnected) {
            System.err.println("[FATAL ERROR] - Project init Checklist Failed. Program Stop.");
            logger.error("[FATAL ERROR] - Project init Checklist Failed");
            System.exit(1);
        }
    }

    /**
     *
     * <h3>Buat Ngetest DAO</h3>
     * <p> </p>
     *
     * @author Orions29
     * @since 31 May 2026
     *
     */
    public static void DAOTest() {
        DAOtester.main();
    }
}
