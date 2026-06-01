package com.github.orions29.ekspedisi.model.dao;

import com.github.orions29.ekspedisi.model.entity.User;

/**
 * Project: EkspedisiMasGiant
 * Package: com.github.orions29.ekspedisi.model.dao
 * <p>
 * Model Table dari User
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
     * @param username - Username User
     * @param password - Hash Password user
     * @return {@link User} - Objek User
     * @author Orions29
     * @since 30 May 2026
     *
     */
    User authenticate(String username, String password);

    /**
     *
     * <h3>Ambil Info User dari DB</h3>
     * <p> </p>
     *
     * @param userId - User ID
     * @return {@link User} - Objek User
     * @author Orions29
     * @since 30 May 2026
     *
     */
    User getUserById(String userId);


    //    Mode Sapu Jagat

    /**
     *
     * <h3>Menambah User</h3>
     * <p> Menambahkan User kedalam sistem </p>
     *
     * @param user - Objek user yang ingin diganti
     * @return {@link boolean} - Sukses atau tidaknya user ditambhakn
     * @author Orions29
     * @since 30 May 2026
     *
     */
    boolean insertUser(User user);

    /**
     *
     * <h3>Update Info User</h3>
     * <p> </p>
     *
     * @param user - Deskripsi fungsi parameter ini
     * @return {@link boolean} - Sukses atau tidaknya user ditambahkan
     * @author Orions29
     * @since 30 May 2026
     *
     */
    boolean updateUser(User user);

    /**
     *
     * <h3>Delete User From Database</h3>
     * <p> Menghapus User dari Database</p>
     *
     * @param userId $END$ - Deskripsi fungsi parameter ini
     * @return {@link boolean} - Penjelasan mengenai data yang dikembalikan
     * @author Orions29
     * @since 1 Jun 2026
     *
     */
    boolean deleteUser(String userId);
}
