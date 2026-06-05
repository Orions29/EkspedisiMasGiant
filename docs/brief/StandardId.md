# Standarisasi Unique Values

- MENJADI PEDOMAN STANDAR PENGEMBANGAN TITIK.

## 1. User ID (ID Pekerja / ID User)

**Fungsi:** Identifikasi unik tingkat *database* untuk setiap akun pekerja (HRD dan Operasional). Bertindak sebagai
*Primary Key* di tabel `users`.

**Format Mutlak:** `[Inisial Role]-[4 Karakter Random UUID Uppercase]`

**Daftar Prefix Kasta:**

- `A-XXXX` : Administrator (God Mode / HRD).
- `L-XXXX` : Staf Loket (Operasional Outlet).
- `G-XXXX` : Staf Gudang (Pusat Sortir).
- `K-XXXX` : Kurir (Operasional Lapangan).
- `U-XXXX` : *Fallback* otomatis jika *role* tidak dikenali sistem. (JANGAN SAMPE MUNCUL YA MBUT)


**Contoh Output:** `K-F72A`, `L-90BC`, `G-11X2`.

**Sifat:** *Immutable* (permanen dan tidak bisa di-edit setelah pekerja direkrut) untuk menjaga integritas data
logistik yang pernah mereka input.

---

## 2. Resi ID (Nomor Pelacakan Logistik)

**Fungsi:** Identifikasi tunggal untuk setiap paket fisik yang melintasi ekosistem. Bertindak sebagai *Primary Key* di
tabel `paket` dan *Foreign Key* absolut di tabel `tracking_logs`.

**Format Mutlak:** `RESI-[yyyyMMdd]-[HHmm]-[4 Karakter Random UUID Uppercase]`

**Komponen Pembentuk:**
- `RESI-` : Prefix penanda dari sistem EkspedisiMasRoi.
- `yyyyMMdd` : Stempel tanggal paket masuk (Contoh: 20260601).
- `HHmm` : Stempel jam dan menit paket diinput (Contoh: 1430) INGAT 24 HOURS.
- `XXXX` : Potongan UUID murni untuk mencegah tabrakan data.

**Contoh Output:** `RESI-20260601-1430-A1B2`.

**Sifat:** Dicetak hanya satu kali oleh sistem Loket. Bersifat final.

---

## 3. Log ID (Riwayat Pergerakan/Tracking)

**Fungsi:** Mencatat kronologi setiap detak pergerakan paket dari hulu ke hilir.

**Format Mutlak:** `Integer` (Angka bulat).

**Mekanisme:** *Auto-Increment* murni yang diatur oleh *engine* database, disini kita pake MariaDB karena di xammmmmmmmp.

**Sifat:** Arsitektur aplikasi Java **dilarang keras** memanipulasi atau mengirimkan ID ini. Codingan hanya bertugas
melempar data, MariaDB yang akan mengecap nomor urutnya secara mandiri untuk menghindari Ngaconya seluruh sistem.