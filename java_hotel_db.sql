-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 31, 2025 at 01:19 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `java_hotel_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(20) NOT NULL,
  `customer_id` int(50) DEFAULT NULL,
  `total` double NOT NULL,
  `roomType` varchar(100) NOT NULL,
  `roomNumber` int(100) NOT NULL,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `phoneNumber` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `checkIn` date DEFAULT NULL,
  `checkOut` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `customer_id`, `total`, `roomType`, `roomNumber`, `firstName`, `lastName`, `phoneNumber`, `email`, `checkIn`, `checkOut`) VALUES
(1, 1, 0, 'King Room', 135, 'karem', 'salah', '01271031793', 'ka@gmail.com', '2025-05-01', '2025-05-09'),
(2, 2, 0, 'Single Room', 123, 'etch', 'hesham', '0212154554', 'etch@gmail.com', '2025-05-01', '2025-05-08'),
(3, 2, 0, 'Single Room', 123, 'etch', 'hesham', '0212154554', 'etch@gmail.com', '2025-05-01', '2025-05-08'),
(4, 2, 0, 'Single Room', 123, 'etch', 'hesham', '0212154554', 'etch@gmail.com', '2025-05-01', '2025-05-08'),
(5, 2, 0, 'Single Room', 123, 'etch', 'hesham', '0212154554', 'etch@gmail.com', '2025-05-01', '2025-05-08'),
(6, 2, 0, 'Quad Room', 127, 'etch', 'hesham', '0212154554', 'etch@gmail.com', '2025-05-01', '2025-05-16'),
(7, 3, 0, 'Quad Room', 127, 'karem', 'wahba', '01271031793', 'karemwahba15@gmail.com', '2025-05-01', '2025-05-10'),
(8, 4, 0, 'King Room', 124, 'check', 'check', '121212121', 'chech@check.com', '2025-05-01', '2025-05-28'),
(9, 5, 0, 'Single Room', 200, 'karem', 'wahba', '01271031793', 'karemwahba15@gmail.com', '2025-05-01', '2025-05-03'),
(10, 6, 0, 'Single Room', 123, 'newuser', '1', '0212122121', 'newuser1@gmail.com', '2025-05-01', '2025-05-03'),
(11, 7, 0, 'Single Room', 400, 'yarab', 'yarab', '221332', 'kare@gmail.com', '2025-05-01', '2025-05-30'),
(12, 8, 0, 'Quad Room', 127, 'testingreset', 'dfs', '54', 'dfsdf', '2025-05-01', '2025-05-03'),
(13, 9, 20000, 'King Room', 124, 'kmkm', 'wahbawahba', '01212121', 'kmwahba@gmial.com', '2025-05-01', '2025-05-09');

-- --------------------------------------------------------

--
-- Table structure for table `customer_receipt`
--

CREATE TABLE `customer_receipt` (
  `id` int(100) NOT NULL,
  `customer_num` int(100) NOT NULL,
  `total` double NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer_receipt`
--

INSERT INTO `customer_receipt` (`id`, `customer_num`, `total`, `date`) VALUES
(1, 3, 5400, '2025-05-01'),
(2, 4, 67500, '2025-05-01'),
(3, 5, 400, '2025-05-01'),
(4, 6, 246, '2025-05-01'),
(5, 7, 1450000, '2025-05-01'),
(6, 8, 1200, '2025-05-01'),
(7, 9, 20000, '2025-05-01');

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `id` int(11) NOT NULL,
  `roomNumber` varchar(20) NOT NULL,
  `type` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`id`, `roomNumber`, `type`, `status`, `price`) VALUES
(2, '123', 'Single Room', 'Occupied', 123),
(3, '124', 'King Room', 'Occupied', 2500),
(4, '125', 'Double Room', 'Occupied', 126),
(5, '127', 'Quad Room', 'Occupied', 600),
(6, '135', 'King Room', 'Occupied', 5000),
(7, '200', 'Single Room', 'Occupied', 200),
(8, '200', 'Quad Room', 'Occupied', 200),
(9, '400', 'Single Room', 'Occupied', 50000);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`) VALUES
(1, 'etch', 'etch'),
(2, 'karim', 'karim');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer_receipt`
--
ALTER TABLE `customer_receipt`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `customer_receipt`
--
ALTER TABLE `customer_receipt`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `room`
--
ALTER TABLE `room`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
