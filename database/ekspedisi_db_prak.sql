-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 01 Jun 2026 pada 14.19
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.4.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ekspedisi_db_prak`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `paket`
--

CREATE TABLE `paket` (
  `resi_id` varchar(30) NOT NULL,
  `sender_name` varchar(100) NOT NULL,
  `origin_city` varchar(100) NOT NULL,
  `receiver_name` varchar(100) NOT NULL,
  `destination_city` varchar(100) NOT NULL,
  `destination_address` text NOT NULL,
  `weight` decimal(8,2) NOT NULL,
  `volume` decimal(8,2) NOT NULL,
  `type_paket` varchar(50) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `paket`
--

INSERT INTO `paket` (`resi_id`, `sender_name`, `origin_city`, `receiver_name`, `destination_city`, `destination_address`, `weight`, `volume`, `type_paket`, `created_at`) VALUES
('RESI-20260601-2052-8C75', 'Budi', 'Loket Pusat Godean', 'heru', 'Yogyakarta', 'Jogja Bagian apalah itu', 30.00, 17.00, 'Baju Kotor', '2026-06-01 13:52:41'),
('RESI-20260601-2059-065B', 'Budi Herryanto', 'Loket Pusat Godean', 'Heri Antonio', 'hahay', 'Inilah begitulah ini lah', 100.00, 2000.00, 'Baju Bekas', '2026-06-01 13:59:34'),
('RESI-20260601-2110-D7B8', 'Haryani', 'Loket Pusat Godean', 'Budi Santoto', 'Godean', 'Ya sini lah ya', 8.00, 140.00, 'Baju Kotor', '2026-06-01 14:10:53');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tracking_logs`
--

CREATE TABLE `tracking_logs` (
  `log_id` int(11) NOT NULL,
  `resi_id` varchar(30) NOT NULL,
  `user_id` varchar(20) DEFAULT NULL,
  `status` varchar(50) NOT NULL,
  `location` varchar(100) NOT NULL,
  `timestamp` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tracking_logs`
--

INSERT INTO `tracking_logs` (`log_id`, `resi_id`, `user_id`, `status`, `location`, `timestamp`) VALUES
(1, 'RESI-20260601-2052-8C75', 'L-1002', 'Diterima di Loket', 'Loket Pusat Godean', '2026-06-01 20:52:41'),
(2, 'RESI-20260601-2059-065B', 'L-1002', 'Diterima di Loket', 'Loket Pusat Godean', '2026-06-01 20:59:34'),
(3, 'RESI-20260601-2110-D7B8', 'L-1002', 'Diterima di Loket', 'Loket Pusat Godean', '2026-06-01 21:10:53'),
(4, 'RESI-20260601-2110-D7B8', 'K-3003', 'Dibawa Kurir', 'Godean', '2026-06-01 21:12:17'),
(5, 'RESI-20260601-2110-D7B8', 'G-2002', 'Tiba di Fasilitas Sortir', 'DC Cakung', '2026-06-01 21:15:33'),
(6, 'RESI-20260601-2110-D7B8', 'K-3003', 'Dibawa Kurir', 'Godean', '2026-06-01 21:16:04'),
(7, 'RESI-20260601-2110-D7B8', 'K-3003', 'Paket sudah diterima oleh penerima', 'Paket Telah Diterima', '2026-06-01 21:16:07');

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `id` varchar(20) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `role` enum('admin','kurir','gudang','loket') NOT NULL,
  `location` varchar(100) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`id`, `username`, `password_hash`, `role`, `location`, `created_at`) VALUES
('	\r\nA-1001', 'admintest', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'admin', 'Admin YK', '2026-05-31 17:27:47'),
('G-2002', 'gudangtest', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'gudang', 'DC Cakung', '2026-05-31 17:28:22'),
('K-3003', 'kurirtest', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'kurir', 'Godean', '2026-05-31 17:29:14'),
('L-1002', 'lokettest', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'loket', 'Loket Pusat Godean', '2026-05-31 17:28:49');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `paket`
--
ALTER TABLE `paket`
  ADD PRIMARY KEY (`resi_id`);

--
-- Indeks untuk tabel `tracking_logs`
--
ALTER TABLE `tracking_logs`
  ADD PRIMARY KEY (`log_id`),
  ADD KEY `idx_tracking_resi` (`resi_id`),
  ADD KEY `idx_tracking_courier` (`user_id`);

--
-- Indeks untuk tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `tracking_logs`
--
ALTER TABLE `tracking_logs`
  MODIFY `log_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `tracking_logs`
--
ALTER TABLE `tracking_logs`
  ADD CONSTRAINT `fk_tracking_paket` FOREIGN KEY (`resi_id`) REFERENCES `paket` (`resi_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_tracking_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
