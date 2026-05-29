package com.github.orions29.ekspedisi.utils;

import com.github.orions29.ekspedisi.model.DatabaseConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project: EkspedisiMasGiant
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
     *
     * @author Orions29
     * @since 29 May 2026
     *     */
    public static void projectCheck() {
        logger.info("Project Check Init");
        System.out.println(">> ProjectInit CheckList: ");
        System.out.println(SecretLoader.isLoad()? "ENV : PASS":"ENV : FAILED");
        System.out.println(DatabaseConfig.testConnection()?"DB  : PASS":"DB  : FAILED");
    }
}
