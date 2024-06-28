-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 28, 2024 at 08:53 AM
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
-- Database: `Hospital_Patients`
--

-- --------------------------------------------------------

--
-- Table structure for table `Patient_Details`
--

CREATE TABLE `Patient_Details` (
  `id` int(11) NOT NULL,
  `Phone_No` varchar(10) NOT NULL,
  `Aadhar_No` varchar(12) NOT NULL,
  `Disease` varchar(50) NOT NULL,
  `First_Name` varchar(50) NOT NULL,
  `Last_Name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Patient_Details`
--

INSERT INTO `Patient_Details` (`id`, `Phone_No`, `Aadhar_No`, `Disease`, `First_Name`, `Last_Name`) VALUES
(9, '9024779695', '123456897458', 'Heart Attack', 'Ather', 'Niyargar'),
(10, '8239276718', '546897853214', 'Erectile Dysfunction', 'Arvind', 'Chauhan'),
(11, '9521751901', '568912347586', 'Blood Cancer', 'Saloni ', 'Tiwari'),
(12, '7976359188', '123456789123', 'heart attack', 'Qazi Faizan', 'Ullah');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Patient_Details`
--
ALTER TABLE `Patient_Details`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Patient_Details`
--
ALTER TABLE `Patient_Details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
