-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Anamakine: localhost:3306
-- Üretim Zamanı: 06 Eyl 2020, 12:22:40
-- Sunucu sürümü: 10.3.16-MariaDB
-- PHP Sürümü: 7.3.12
-- Mehmet Fatih Okuyan

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `id14789706_root`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `filmler`
--

CREATE TABLE `filmler` (
  `film_id` int(11) NOT NULL,
  `film_ad` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `film_yil` int(11) NOT NULL,
  `film_resim` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `kategori_id` int(11) NOT NULL,
  `yonetmen_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Tablo döküm verisi `filmler`
--

INSERT INTO `filmler` (`film_id`, `film_ad`, `film_yil`, `film_resim`, `kategori_id`, `yonetmen_id`) VALUES
(1, 'Interstellar', 2015, 'interstellar.png', 4, 1),
(2, 'Inception', 2010, 'inception.png', 4, 1),
(3, 'The Pianist', 2002, 'thepianist.png', 3, 4),
(4, 'Bir Zamanlar Anadolu\'da', 2011, 'birzamanlaranadoluda.png', 3, 3),
(5, 'The Hateful Eight', 2015, 'thehatefuleight.png', 1, 2),
(18, 'Django', 2013, 'django.png', 1, 2);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kategoriler`
--

CREATE TABLE `kategoriler` (
  `kategori_id` int(11) NOT NULL,
  `kategori_ad` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Tablo döküm verisi `kategoriler`
--

INSERT INTO `kategoriler` (`kategori_id`, `kategori_ad`) VALUES
(1, 'Action'),
(3, 'Drama'),
(4, 'Sci-Fi');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kelimeler`
--

CREATE TABLE `kelimeler` (
  `kelime_id` int(9) DEFAULT NULL,
  `ingilizce` varchar(9) COLLATE utf8_unicode_ci DEFAULT NULL,
  `turkce` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Tablo döküm verisi `kelimeler`
--

INSERT INTO `kelimeler` (`kelime_id`, `ingilizce`, `turkce`) VALUES
(1, 'apple', 'elma'),
(2, 'door', 'kapı'),
(3, 'pencil', 'kalem'),
(4, 'window', 'pencere'),
(5, 'table', 'masa'),
(6, 'chair', 'sandalye'),
(7, 'lock', 'kilit'),
(8, 'box', 'kutu'),
(9, 'night', 'gece'),
(10, 'day', 'gün'),
(11, 'morning', 'sabah'),
(12, 'cable', 'kablo'),
(13, 'room', 'oda'),
(14, 'phone', 'telefon'),
(15, 'sun', 'güneş'),
(16, 'moon', 'ay'),
(17, 'world', 'dünya'),
(18, 'book', 'kitap'),
(19, 'light', 'ışık'),
(20, 'red', 'kırmızı'),
(21, 'green', 'yeşil'),
(22, 'grey', 'gri'),
(23, 'yellow', 'sarı'),
(24, 'home', 'ev'),
(25, 'garden', 'bahçe'),
(26, 'kitchen', 'mutfak'),
(27, 'breakfast', 'kahvaltı'),
(28, 'dinner', 'akşam yemeği'),
(29, 'word', 'kelime'),
(30, 'school', 'okul'),
(31, 'doctor', 'doktor'),
(32, 'order', 'sipariş'),
(33, 'lesson', 'ders'),
(34, 'page', 'sayfa'),
(35, 'city', 'şehir'),
(36, 'country', 'ülke'),
(37, 'flag', 'bayrak'),
(38, 'homework', 'ödev'),
(39, 'teacher', 'öğretmen'),
(40, 'week', 'hafta');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kisiler`
--

CREATE TABLE `kisiler` (
  `kisi_id` int(11) NOT NULL,
  `kisi_ad` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `kisi_tel` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Tablo döküm verisi `kisiler`
--

INSERT INTO `kisiler` (`kisi_id`, `kisi_ad`, `kisi_tel`) VALUES
(54, 'Tammy D. Harvey', '803-658-1374'),
(55, 'Judith J. Noah', '471 49 760'),
(56, 'Claudia Schwartz', '0478 89 86 59'),
(57, 'Sandra Zimmer', '0483 37 72 96'),
(58, 'Julieta Gomes Araujo', '03421 53 77 15'),
(59, 'Carlos Ribeiro Barros', '05734 34 42 88'),
(61, 'Quintino Napolitano', '0374 0183653');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `notlar`
--

CREATE TABLE `notlar` (
  `not_id` int(11) NOT NULL,
  `ders_adi` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `not1` int(10) NOT NULL,
  `not2` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Tablo döküm verisi `notlar`
--

INSERT INTO `notlar` (`not_id`, `ders_adi`, `not1`, `not2`) VALUES
(1, 'Biology', 80, 100),
(2, 'Chemistry', 90, 65),
(7, 'Physics', 90, 90),
(8, 'Literature', 100, 97),
(10, 'Art', 70, 90),
(11, 'Math', 35, 55);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `yonetmenler`
--

CREATE TABLE `yonetmenler` (
  `yonetmen_id` int(11) NOT NULL,
  `yonetmen_ad` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Tablo döküm verisi `yonetmenler`
--

INSERT INTO `yonetmenler` (`yonetmen_id`, `yonetmen_ad`) VALUES
(1, 'Christopher Nolan'),
(2, 'Quentin Tarantino'),
(3, 'Nuri Bilge Ceylan'),
(4, 'Roman Polanski');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `filmler`
--
ALTER TABLE `filmler`
  ADD PRIMARY KEY (`film_id`),
  ADD KEY `fk_yonetmen` (`yonetmen_id`),
  ADD KEY `fk_kategori` (`kategori_id`);

--
-- Tablo için indeksler `kategoriler`
--
ALTER TABLE `kategoriler`
  ADD PRIMARY KEY (`kategori_id`);

--
-- Tablo için indeksler `kisiler`
--
ALTER TABLE `kisiler`
  ADD PRIMARY KEY (`kisi_id`);

--
-- Tablo için indeksler `notlar`
--
ALTER TABLE `notlar`
  ADD PRIMARY KEY (`not_id`);

--
-- Tablo için indeksler `yonetmenler`
--
ALTER TABLE `yonetmenler`
  ADD PRIMARY KEY (`yonetmen_id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `filmler`
--
ALTER TABLE `filmler`
  MODIFY `film_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Tablo için AUTO_INCREMENT değeri `kategoriler`
--
ALTER TABLE `kategoriler`
  MODIFY `kategori_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Tablo için AUTO_INCREMENT değeri `kisiler`
--
ALTER TABLE `kisiler`
  MODIFY `kisi_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- Tablo için AUTO_INCREMENT değeri `notlar`
--
ALTER TABLE `notlar`
  MODIFY `not_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Tablo için AUTO_INCREMENT değeri `yonetmenler`
--
ALTER TABLE `yonetmenler`
  MODIFY `yonetmen_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
