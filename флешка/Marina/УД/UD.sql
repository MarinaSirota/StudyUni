-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Окт 08 2018 г., 01:51
-- Версия сервера: 5.5.25
-- Версия PHP: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `kp_ud`
--

-- --------------------------------------------------------

--
-- Структура таблицы `гостиница`
--

CREATE TABLE IF NOT EXISTS `гостиница` (
  `Номер гостиницы` int(4) NOT NULL,
  `Название` varchar(20) COLLATE cp1250_bin NOT NULL,
  `Адрес` varchar(20) COLLATE cp1250_bin NOT NULL,
  `Разряд` int(1) NOT NULL,
  `Номер фирмы` int(3) NOT NULL,
  PRIMARY KEY (`Номер гостиницы`)
) ENGINE=InnoDB DEFAULT CHARSET=cp1250 COLLATE=cp1250_bin;

--
-- Дамп данных таблицы `гостиница`
--

INSERT INTO `гостиница` (`Номер гостиницы`, `Название`, `Адрес`, `Разряд`, `Номер фирмы`) VALUES
(1122, 'Tomato', 'simferopol', 5, 111),
(1133, 'Moscow', 'simferopol', 4, 111),
(2233, 'Potato', 'sevastopol', 4, 222),
(3311, 'Russia', 'yalta', 3, 333),
(3322, 'crimea', 'yalta', 3, 333),
(4411, 'Ukraine', 'sevastopol', 5, 222),
(7000, 'Tourist', 'alushta', 4, 333),
(55655, 'Intourist', 'yalta', 5, 333);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
