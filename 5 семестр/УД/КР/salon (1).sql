-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Дек 22 2018 г., 02:45
-- Версия сервера: 5.5.25
-- Версия PHP: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `salon`
--

-- --------------------------------------------------------

--
-- Структура таблицы `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `id_Customer` int(5) NOT NULL AUTO_INCREMENT,
  `Second_Name` varchar(20) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Father_Name` varchar(20) NOT NULL,
  `Phone` varchar(11) NOT NULL,
  `Visit` int(4) NOT NULL,
  PRIMARY KEY (`id_Customer`),
  UNIQUE KEY `id_Customer` (`id_Customer`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=13 ;

--
-- Дамп данных таблицы `customer`
--

INSERT INTO `customer` (`id_Customer`, `Second_Name`, `Name`, `Father_Name`, `Phone`, `Visit`) VALUES
(1, 'Сирота', 'Марина', 'Романовна', '79788585833', 0),
(2, 'Голикова', 'Александра', 'Антоновна', '79781117717', 0),
(3, 'Синча', 'Ника', 'Андреевна', '79782221144', 0),
(4, 'Коваль', 'Ангелина', 'Александровна', '79783333333', 0),
(5, 'Добони', 'Ксения', 'Александровна', '79784441441', 0),
(11, 'Иванова', 'Юлия', 'Степановна', '79781122333', 0),
(12, 'Сидорова', 'Ольга', 'Петровна', '79782277333', 0);

-- --------------------------------------------------------

--
-- Структура таблицы `record`
--

CREATE TABLE IF NOT EXISTS `record` (
  `id_Customer` int(5) NOT NULL,
  `id_Service` int(5) NOT NULL,
  `id_Worker` int(5) NOT NULL,
  `Date` date NOT NULL,
  `Hour` enum('8','10','12','14','16','18') NOT NULL,
  `Status` enum('0','1','-1') NOT NULL,
  PRIMARY KEY (`id_Customer`,`id_Service`,`id_Worker`,`Date`,`Hour`),
  KEY `id_Service` (`id_Service`,`id_Worker`),
  KEY `id_Worker` (`id_Worker`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `record`
--

INSERT INTO `record` (`id_Customer`, `id_Service`, `id_Worker`, `Date`, `Hour`, `Status`) VALUES
(1, 3, 4, '2018-12-22', '10', '1'),
(1, 5, 4, '2018-12-23', '16', '0'),
(1, 6, 6, '2018-12-24', '10', '0'),
(2, 3, 4, '2018-12-03', '14', '1'),
(2, 3, 5, '2018-12-27', '16', '0'),
(3, 3, 5, '2018-12-02', '14', '1'),
(3, 6, 6, '2018-12-24', '12', '0'),
(4, 4, 5, '2018-12-27', '8', '0'),
(5, 1, 2, '2018-12-24', '12', '0'),
(5, 2, 1, '2018-12-24', '14', '0'),
(11, 7, 6, '2018-12-05', '18', '1'),
(11, 7, 6, '2018-12-11', '10', '1'),
(12, 3, 4, '2018-12-16', '10', '1'),
(12, 7, 6, '2018-12-24', '18', '0');

-- --------------------------------------------------------

--
-- Структура таблицы `service_worker`
--

CREATE TABLE IF NOT EXISTS `service_worker` (
  `id_Service` int(5) NOT NULL,
  `id_Worker` int(5) NOT NULL,
  PRIMARY KEY (`id_Service`,`id_Worker`),
  UNIQUE KEY `id_Service` (`id_Service`,`id_Worker`),
  KEY `id_Worker` (`id_Worker`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `service_worker`
--

INSERT INTO `service_worker` (`id_Service`, `id_Worker`) VALUES
(1, 1),
(2, 1),
(1, 2),
(2, 2),
(3, 4),
(4, 4),
(5, 4),
(3, 5),
(4, 5),
(5, 5),
(6, 6),
(7, 6);

-- --------------------------------------------------------

--
-- Структура таблицы `sevice`
--

CREATE TABLE IF NOT EXISTS `sevice` (
  `id_Service` int(5) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) NOT NULL,
  `Cost` int(5) NOT NULL,
  `Time` int(5) NOT NULL,
  PRIMARY KEY (`id_Service`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Дамп данных таблицы `sevice`
--

INSERT INTO `sevice` (`id_Service`, `Name`, `Cost`, `Time`) VALUES
(1, 'Маникюр', 1000, 60),
(2, 'Педикюр', 1200, 90),
(3, 'Стрижка', 500, 30),
(4, 'Окрашивание', 2000, 120),
(5, 'Омбре', 5000, 240),
(6, 'Макияж', 2000, 90),
(7, 'Коррекция бровей', 500, 30);

-- --------------------------------------------------------

--
-- Структура таблицы `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`login`),
  UNIQUE KEY `password` (`password`),
  UNIQUE KEY `user_Name` (`login`),
  UNIQUE KEY `user_Name_2` (`login`,`password`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `user`
--

INSERT INTO `user` (`login`, `password`) VALUES
('admin', 'admin'),
('user1', 'user1'),
('user2', 'user2');

-- --------------------------------------------------------

--
-- Структура таблицы `worker`
--

CREATE TABLE IF NOT EXISTS `worker` (
  `id_Worker` int(5) NOT NULL AUTO_INCREMENT,
  `Second_Name` varchar(20) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Father_Name` varchar(20) NOT NULL,
  `Phone` varchar(11) NOT NULL,
  `Prossent` float NOT NULL,
  `Start_Day` date NOT NULL,
  PRIMARY KEY (`id_Worker`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Дамп данных таблицы `worker`
--

INSERT INTO `worker` (`id_Worker`, `Second_Name`, `Name`, `Father_Name`, `Phone`, `Prossent`, `Start_Day`) VALUES
(1, 'Сергеева', 'Елена', 'Андреевна', '79787676611', 23.8, '2017-10-05'),
(2, 'Петрова', 'Инна', 'Ивановна', '79784534147', 33.5, '2018-12-07'),
(4, 'Ковальчук', 'Светлана', 'Ивановна', '79781211299', 23.8, '2018-11-05'),
(5, 'Петрова', 'Ольга', 'Степановна', '79787375712', 23.8, '2018-11-05'),
(6, 'Сидорова', 'Александра', 'Сергеевна', '79781111154', 44.5, '2017-01-05');

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `record`
--
ALTER TABLE `record`
  ADD CONSTRAINT `record_ibfk_1` FOREIGN KEY (`id_Customer`) REFERENCES `customer` (`id_Customer`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `record_ibfk_2` FOREIGN KEY (`id_Service`, `id_Worker`) REFERENCES `service_worker` (`id_Service`, `id_Worker`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `record_ibfk_3` FOREIGN KEY (`id_Service`) REFERENCES `service_worker` (`id_Service`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `record_ibfk_4` FOREIGN KEY (`id_Worker`) REFERENCES `service_worker` (`id_Worker`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `service_worker`
--
ALTER TABLE `service_worker`
  ADD CONSTRAINT `service_worker_ibfk_2` FOREIGN KEY (`id_Worker`) REFERENCES `worker` (`id_Worker`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `service_worker_ibfk_3` FOREIGN KEY (`id_Service`) REFERENCES `sevice` (`id_Service`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
