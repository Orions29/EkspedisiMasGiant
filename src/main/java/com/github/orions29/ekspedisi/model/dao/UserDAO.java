package com.github.orions29.ekspedisi.model.dao;

import com.github.orions29.ekspedisi.model.entity.User;

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
public interface UserDAO {
    /**
     *
     * <h3>Authentikasi User di DB</h3>
     * <p> </p>
     *
     *
     * @author Orions29
     * @since 30 May 2026
     * @param username  - Username User
     * @param password  - Hash Password user
     * @return {@link User} - Objek User
     * */
    User authenticate(String username, String password);

    /**
     *
     * <h3>Ambil Info User dari DB</h3>
     * <p> </p>
     *
     *
     * @author Orions29
     * @since 30 May 2026
     * @param userId  - User ID
     * @return {@link User} - Objek User
     *     */
    User getUserById(String userId);


    //    Mode Sapu Jagat

    /**
     *
     * <h3>Menambah User</h3>
     * <p> Menambahkan User kedalam sistem </p>
     *
     *
     * @author Orions29
     * @since 30 May 2026
     * @param user  - Objek user yang ingin diganti
     * @return {@link boolean} - Sukses atau tidaknya user ditambhakn
     *     */
    boolean insertUser(User user);

    /**
     *
     * <h3>Update Info User</h3>
     * <p> </p>
     *
     *
     * @author Orions29
     * @since 30 May 2026
     * @param user  - Deskripsi fungsi parameter ini
     * @return {@link boolean} - Sukses atau tidaknya user ditambahkan
     *     */
    boolean updateUser(User user);
}
