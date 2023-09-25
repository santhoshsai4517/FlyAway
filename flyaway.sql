-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 24, 2023 at 02:26 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `flyaway`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE DATABASE flyaway;
USE flyaway;

CREATE TABLE `admin` (
  `username` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`username`, `password`) VALUES
('admin', 'scode');

-- --------------------------------------------------------

--
-- Table structure for table `airline`
--

CREATE TABLE `airline` (
  `airlinename` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `airline`
--

INSERT INTO `airline` (`airlinename`) VALUES
('AirIndia'),
('Indigo'),
('SpiceJet');

-- --------------------------------------------------------

--
-- Table structure for table `bookings`
--

CREATE TABLE `bookings` (
  `bookingid` varchar(25) NOT NULL,
  `flightid` varchar(25) NOT NULL,
  `firstname` varchar(25) NOT NULL,
  `lastname` varchar(25) NOT NULL,
  `phno` varchar(15) NOT NULL,
  `email` text NOT NULL,
  `date` varchar(25) NOT NULL,
  `seats` int(11) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bookings`
--

INSERT INTO `bookings` (`bookingid`, `flightid`, `firstname`, `lastname`, `phno`, `email`, `date`, `seats`, `price`) VALUES
('2ooqYHT7XH', '4117', 'sad', 'sa', '+919063517617', 's@gmail.com', '2023-09-24', 2, 40),
('8WfkZsD7AW', '4117', 'sad', 'sa', '+919063517617', 's@gmail.com', '2023-09-24', 54, 1080),
('IZwCpYL4rD', '4117', 'sad', 'sa', '+919063517617', 's@gmail.com', '2023-09-24', 54, 1080),
('j6CTrvGSvZ', '4117', 'sad', 'sa', '+919063517617', 's@gmail.com', '2023-09-24', 3, 60),
('JhFJXgIj5c', '4124', 'scode', 'sai', '+919063517617', 's@gmail.com', '2023-09-23', 1, 12),
('mCiioePTe6', '4117', 'sai', 'santhosh', '+919063517617', 's@gmail.com', '2023-09-24', 2, 40),
('OfUjimJvbG', '4117', 'sad', 'sa', '+919063517617', 's@gmail.com', '2023-09-24', 54, 1080),
('UgHfhbOxjc', '4117', 'sad', 'sa', '+919063517617', 's@gmail.com', '2023-09-24', 54, 1080),
('WpPHPC4yMZ', '4117', 'scode', 'sai', '+919063517617', 's@gmail.com', '2023-09-24', 5, 100),
('ZtxuIPeMDq', '4117', 'scode', 'sai', '+919063517617', 's@gmail.com', '2023-09-24', 10, 200);

-- --------------------------------------------------------

--
-- Table structure for table `flights`
--

CREATE TABLE `flights` (
  `flightid` varchar(25) NOT NULL,
  `source` varchar(25) NOT NULL,
  `destination` varchar(25) NOT NULL,
  `airline` varchar(25) NOT NULL,
  `seats` int(11) NOT NULL,
  `date` varchar(25) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `flights`
--

INSERT INTO `flights` (`flightid`, `source`, `destination`, `airline`, `seats`, `date`, `price`) VALUES
('4117', 'kadapa', 'Hyderabad', 'Indigo', 124, '2023-09-24', 20),
('4124', 'kadapa', 'Hyderabad', 'Indigo', 199, '2023-09-23', 12),
('4217', 'kadapa', 'Hyderabad', 'AirIndia', 100, '2023-09-24', 50),
('4218', 'kadapa', 'Hyderabad', 'Indigo', 200, '2023-09-26', 20),
('4517', 'kadapa', 'Hyderabad', 'Indigo', 200, '2023-09-24', 100);

-- --------------------------------------------------------

--
-- Table structure for table `places`
--

CREATE TABLE `places` (
  `place` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `places`
--

INSERT INTO `places` (`place`) VALUES
('Chennai'),
('Hyderabad'),
('kadapa');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `airline`
--
ALTER TABLE `airline`
  ADD PRIMARY KEY (`airlinename`);

--
-- Indexes for table `bookings`
--
ALTER TABLE `bookings`
  ADD PRIMARY KEY (`bookingid`),
  ADD KEY `test3` (`flightid`);

--
-- Indexes for table `flights`
--
ALTER TABLE `flights`
  ADD PRIMARY KEY (`flightid`),
  ADD KEY `test` (`source`),
  ADD KEY `test1` (`destination`),
  ADD KEY `test2` (`airline`);

--
-- Indexes for table `places`
--
ALTER TABLE `places`
  ADD PRIMARY KEY (`place`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bookings`
--
ALTER TABLE `bookings`
  ADD CONSTRAINT `test3` FOREIGN KEY (`flightid`) REFERENCES `flights` (`flightid`);

--
-- Constraints for table `flights`
--
ALTER TABLE `flights`
  ADD CONSTRAINT `test` FOREIGN KEY (`source`) REFERENCES `places` (`place`),
  ADD CONSTRAINT `test1` FOREIGN KEY (`destination`) REFERENCES `places` (`place`),
  ADD CONSTRAINT `test2` FOREIGN KEY (`airline`) REFERENCES `airline` (`airlinename`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
