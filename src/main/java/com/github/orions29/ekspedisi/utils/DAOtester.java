package com.github.orions29.ekspedisi.utils;

import com.github.orions29.ekspedisi.model.dao.UserDAO;
import com.github.orions29.ekspedisi.model.dao.UserDAOMariaDb;
import com.github.orions29.ekspedisi.model.entity.User;

/**
 * Project: EkspedisiMasGiant
 * Package: com.github.orions29.ekspedisi.utils
 * <p>
 * Class buat ngetest apakah nanti ini bisa jalan
 * </p>
 *
 * <hr>
 * <table border="0">
 * <tr><td><b>Author</b></td><td>: Orions29</td></tr>
 * <tr><td><b>Date</b></td><td>: 30 May 2026</td></tr>
 * <tr><td><b>Time</b></td><td>: 12:06</td></tr>
 * </table>
 * <hr>
 *
 * @author Orions29
 * @since 1.0
 */
public class DAOtester {
    public static void main() {
        System.out.println("\n==>> TEST DAO <<==\n");

//Testing UserDAO

        System.out.println(">> Test USER DAO <<");
        UserDAO userDao = new UserDAOMariaDb();

//        Akses DAO User
        System.out.println(">> TEST 1: Tarik Data Berdasarkan ID");
        User testUser1 = userDao.getUserById("G-2001");
        System.out.println("Hasil (Harus Budi Gudang) : " + testUser1);

        User testUser2 = userDao.getUserById("X-9999");
        System.out.println("Hasil (Harus null)        : " + testUser2);
        System.out.println(">>");

//        Auth User
        System.out.println(">> TEST 2: Tes Gerbang Autentikasi");
        System.out.println("Hasil Login (Harus null)  : " + userDao.authenticate("loket_rani", "password_salah_nih"));
        System.out.println("Hasil Login (Correcto)  : " + userDao.authenticate("kurir_anto", "bee5688aea66a47460b19c76f8f199c6b9585eb726f8322b1429793863609ca3"));
        System.out.println(">>\n");


        System.out.println(">> Test DAO Selesai");
    }
}
