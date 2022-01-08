-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 07 Jan 2022 pada 18.04
-- Versi server: 10.4.11-MariaDB
-- Versi PHP: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `siwarga014`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_kk`
--

CREATE TABLE `detail_kk` (
  `kode_warga` varchar(25) NOT NULL,
  `no_kk` varchar(30) NOT NULL,
  `no_ktp` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `dt_erte`
--

CREATE TABLE `dt_erte` (
  `id` int(11) NOT NULL,
  `kode_rt` varchar(20) NOT NULL,
  `ketua_rt` varchar(100) NOT NULL,
  `wakil_rt` varchar(100) DEFAULT NULL,
  `sekretaris` varchar(100) DEFAULT NULL,
  `bendahara` varchar(100) DEFAULT NULL,
  `status_rt` varchar(15) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `dt_erte`
--

INSERT INTO `dt_erte` (`id`, `kode_rt`, `ketua_rt`, `wakil_rt`, `sekretaris`, `bendahara`, `status_rt`, `username`, `password`) VALUES
(1, 'RT-014 / RW-05', 'Wahyu setiawan', 'Aji santoso', '-', '-', 'Aktif', 'admin', 'admin');

-- --------------------------------------------------------

--
-- Struktur dari tabel `dt_isipindah`
--

CREATE TABLE `dt_isipindah` (
  `id` int(11) NOT NULL,
  `kode_pindah` varchar(10) NOT NULL,
  `no_kk` varchar(50) NOT NULL,
  `no_ktp` varchar(50) NOT NULL,
  `nama_lengkap` varchar(100) NOT NULL,
  `jenis_kelamin` varchar(25) NOT NULL,
  `status_warga` varchar(20) NOT NULL,
  `status_pindah` varchar(20) NOT NULL,
  `foto` text NOT NULL,
  `jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `dt_kartukeluarga`
--

CREATE TABLE `dt_kartukeluarga` (
  `no` int(11) NOT NULL,
  `no_kk` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `dt_kematian`
--

CREATE TABLE `dt_kematian` (
  `kode_kematian` varchar(10) NOT NULL,
  `no_ktp` varchar(30) NOT NULL,
  `no_kk` varchar(30) NOT NULL,
  `nama_lengkap` varchar(255) NOT NULL,
  `jenis_kelamin` varchar(30) NOT NULL,
  `usia` varchar(5) DEFAULT NULL,
  `penyebab` text DEFAULT NULL,
  `tanggal_wafat` date NOT NULL,
  `jumlah` int(11) NOT NULL,
  `jenis_warga` varchar(25) NOT NULL,
  `status_warga` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `dt_pindah`
--

CREATE TABLE `dt_pindah` (
  `kode_pindah` varchar(20) NOT NULL,
  `tanggal_pindah` date NOT NULL,
  `no_kk` varchar(30) NOT NULL,
  `alamat_pindah` text NOT NULL,
  `jumlah_pindah` int(11) NOT NULL,
  `status` varchar(20) NOT NULL,
  `kode_rt` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `dt_statistik`
--

CREATE TABLE `dt_statistik` (
  `kode` varchar(25) NOT NULL,
  `jumlah_warga` int(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `dt_statistik`
--

INSERT INTO `dt_statistik` (`kode`, `jumlah_warga`) VALUES
('Meninggal', 0),
('Pendatang', 0),
('Pindah', 0),
('Tetap', 0);

-- --------------------------------------------------------

--
-- Struktur dari tabel `dt_suratkematian`
--

CREATE TABLE `dt_suratkematian` (
  `no_surat` varchar(10) NOT NULL,
  `kode_kematian` varchar(10) NOT NULL,
  `kode_rt` varchar(20) DEFAULT NULL,
  `tanggal_surat` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `dt_suratpengantar`
--

CREATE TABLE `dt_suratpengantar` (
  `no_surat` varchar(10) NOT NULL,
  `kode_warga` varchar(10) NOT NULL,
  `kode_rt` varchar(15) NOT NULL,
  `keterangan` text NOT NULL,
  `tanggal_surat` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `dt_suratpindah`
--

CREATE TABLE `dt_suratpindah` (
  `no_suratpindah` varchar(20) NOT NULL,
  `kode_pindah` varchar(20) NOT NULL,
  `tanggal_surat` date NOT NULL,
  `keterangan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `dt_warga`
--

CREATE TABLE `dt_warga` (
  `kode_warga` varchar(20) NOT NULL,
  `no_ktp` varchar(30) NOT NULL,
  `no_kk` varchar(30) NOT NULL,
  `nama_lengkap` varchar(255) NOT NULL,
  `jenis_kelamin` varchar(25) NOT NULL,
  `tempat_lahir` varchar(255) DEFAULT NULL,
  `tgl_lahir` date NOT NULL,
  `agama` varchar(50) NOT NULL,
  `pekerjaan` varchar(100) NOT NULL,
  `pendidikan` varchar(100) NOT NULL,
  `alamat_tinggal` text DEFAULT NULL,
  `alamat_asal` text DEFAULT NULL,
  `status_keluarga` varchar(50) NOT NULL,
  `status_warga` varchar(20) NOT NULL,
  `telepon` varchar(50) DEFAULT NULL,
  `jumlah_data` int(11) NOT NULL,
  `kode_rt` varchar(20) NOT NULL,
  `foto` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `detail_kk`
--
ALTER TABLE `detail_kk`
  ADD PRIMARY KEY (`kode_warga`),
  ADD KEY `no_kk` (`no_kk`);

--
-- Indeks untuk tabel `dt_erte`
--
ALTER TABLE `dt_erte`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `dt_isipindah`
--
ALTER TABLE `dt_isipindah`
  ADD PRIMARY KEY (`id`),
  ADD KEY `kode_pindah` (`kode_pindah`);

--
-- Indeks untuk tabel `dt_kartukeluarga`
--
ALTER TABLE `dt_kartukeluarga`
  ADD PRIMARY KEY (`no`);

--
-- Indeks untuk tabel `dt_kematian`
--
ALTER TABLE `dt_kematian`
  ADD PRIMARY KEY (`kode_kematian`),
  ADD KEY `no_ktp` (`no_ktp`);

--
-- Indeks untuk tabel `dt_pindah`
--
ALTER TABLE `dt_pindah`
  ADD PRIMARY KEY (`kode_pindah`);

--
-- Indeks untuk tabel `dt_statistik`
--
ALTER TABLE `dt_statistik`
  ADD PRIMARY KEY (`kode`);

--
-- Indeks untuk tabel `dt_suratkematian`
--
ALTER TABLE `dt_suratkematian`
  ADD PRIMARY KEY (`no_surat`),
  ADD KEY `kode_kematian` (`kode_kematian`);

--
-- Indeks untuk tabel `dt_suratpengantar`
--
ALTER TABLE `dt_suratpengantar`
  ADD PRIMARY KEY (`no_surat`),
  ADD KEY `kode_warga` (`kode_warga`);

--
-- Indeks untuk tabel `dt_suratpindah`
--
ALTER TABLE `dt_suratpindah`
  ADD PRIMARY KEY (`no_suratpindah`),
  ADD KEY `kode_pindah` (`kode_pindah`);

--
-- Indeks untuk tabel `dt_warga`
--
ALTER TABLE `dt_warga`
  ADD PRIMARY KEY (`kode_warga`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `dt_erte`
--
ALTER TABLE `dt_erte`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `dt_isipindah`
--
ALTER TABLE `dt_isipindah`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `dt_kartukeluarga`
--
ALTER TABLE `dt_kartukeluarga`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
