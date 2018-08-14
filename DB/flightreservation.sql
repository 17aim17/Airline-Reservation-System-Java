-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 03, 2018 at 08:14 AM
-- Server version: 5.1.53
-- PHP Version: 5.3.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `flightreservation`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`username`, `password`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `booking_master`
--

CREATE TABLE IF NOT EXISTS `booking_master` (
  `booking_no` int(11) NOT NULL,
  `booking_date` date NOT NULL,
  `dept_date` date NOT NULL,
  `a_code` varchar(7) NOT NULL,
  `source` varchar(25) NOT NULL,
  `destination` varchar(25) NOT NULL,
  `eco_tickets` int(11) NOT NULL DEFAULT '0',
  `buis_tickets` int(11) NOT NULL DEFAULT '0',
  `charge_per_ticket` double NOT NULL,
  `total` double NOT NULL,
  PRIMARY KEY (`booking_no`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `booking_master`
--


-- --------------------------------------------------------

--
-- Table structure for table `booking_tran`
--

CREATE TABLE IF NOT EXISTS `booking_tran` (
  `b_no` int(11) NOT NULL,
  `t_no` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `email` varchar(30) DEFAULT NULL,
  `contact` varchar(15) DEFAULT NULL,
  `aadhar_no` varchar(15) DEFAULT NULL,
  `age` int(11) NOT NULL,
  `type` varchar(10) NOT NULL,
  `gender` varchar(7) NOT NULL,
  PRIMARY KEY (`t_no`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `booking_tran`
--


-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE IF NOT EXISTS `employee` (
  `f_name` varchar(30) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `email` varchar(30) NOT NULL,
  `gender` varchar(7) NOT NULL,
  `user_id` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `j_date` date NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`f_name`, `address`, `phone`, `email`, `gender`, `user_id`, `password`, `j_date`) VALUES
('Harry Potter', 'Hogwards', '878761694', 'theboywholived@hogwards.edu', 'Male', 'theboywholived', 'HPaX5', '2018-06-21'),
('Hermione', 'hogwards', '977961616', 'hermione@hogwards.edu', 'Male', 'hermione', 'ashish', '2018-06-21'),
('Draco', 'malfoy mnsion', '+1 23883-239', 'malfoy@slythreine.com', 'Male', 'malfoy', 'PAXpC', '2018-08-01'),
('Ron Weasly', 'Hogward', '97966984', 'ronweasly12@horwards.edu', 'Male', 'ronweasly12', 'TTguY', '2018-06-21');

-- --------------------------------------------------------

--
-- Table structure for table `fleetinfo`
--

CREATE TABLE IF NOT EXISTS `fleetinfo` (
  `a_code` varchar(7) NOT NULL,
  `a_name` varchar(20) NOT NULL,
  `eco_seats` int(11) NOT NULL,
  `buis_seats` int(11) NOT NULL,
  `eco_ba` int(11) NOT NULL,
  `buis_ba` int(11) NOT NULL,
  `status` varchar(120) NOT NULL,
  PRIMARY KEY (`a_code`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fleetinfo`
--

INSERT INTO `fleetinfo` (`a_code`, `a_name`, `eco_seats`, `buis_seats`, `eco_ba`, `buis_ba`, `status`) VALUES
('A101', 'Melliniem', 30, 15, 1000, 700, 'Not Available'),
('A109', 'Falcon', 40, 20, 2000, 1000, 'Not Available'),
('A102', 'Dutchmen', 55, 35, 5000, 4000, 'Not Available'),
('A103', 'Despicable', 30, 10, 1000, 400, 'Not Available'),
('A104', 'Black Perl', 50, 12, 1000, 200, 'Not Available'),
('A105', 'JetRay', 60, 30, 7000, 6000, 'Not Available'),
('A016', 'Vulture', 50, 30, 12000, 6000, 'Not Available'),
('A107', 'Avatar', 50, 30, 5600, 3200, 'Not Available'),
('A108', 'Airbus', 60, 40, 13000, 7000, 'Not Available');

-- --------------------------------------------------------

--
-- Table structure for table `flight_booking_info`
--

CREATE TABLE IF NOT EXISTS `flight_booking_info` (
  `a_code` varchar(7) NOT NULL,
  `departure_date` date NOT NULL,
  `r_code` varchar(7) NOT NULL,
  `av_eco_st` int(11) NOT NULL,
  `av_buis_st` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `flight_booking_info`
--


-- --------------------------------------------------------

--
-- Table structure for table `route`
--

CREATE TABLE IF NOT EXISTS `route` (
  `r_code` varchar(7) NOT NULL,
  `a_code` varchar(7) NOT NULL,
  `source` varchar(25) NOT NULL,
  `destination` varchar(25) NOT NULL,
  `distance` double NOT NULL,
  `basic` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `route`
--

INSERT INTO `route` (`r_code`, `a_code`, `source`, `destination`, `distance`, `basic`) VALUES
('r1', 'A101', 'Delhi', 'Chennai', 1250, 5500),
('r1', 'A105', 'Delhi', 'Chennai', 1250, 6500),
('r2', 'A109', 'Delhi', 'Mumbai', 1400, 6800),
('r3', 'A102', 'Chandigarh', 'Delhi', 250, 3200),
('r6', 'A103', 'Kolkata', 'Mumbai', 2000, 8000),
('r9', 'A107', 'Ahemdabad', 'Delhi', 800, 5500),
('r6', 'A104', 'Kolkata', 'Mumbai', 2000, 7200),
('r4', 'A016', 'Delhi', 'Pudducherry', 1200, 4600),
('r8', 'A108', 'Surat', 'Delhi', 1200, 6500);

-- --------------------------------------------------------

--
-- Table structure for table `route_master`
--

CREATE TABLE IF NOT EXISTS `route_master` (
  `r_code` varchar(7) NOT NULL,
  `source` varchar(25) NOT NULL,
  `destination` varchar(25) NOT NULL,
  PRIMARY KEY (`r_code`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `route_master`
--

INSERT INTO `route_master` (`r_code`, `source`, `destination`) VALUES
('r1', 'Delhi', 'Chennai'),
('r2', 'Delhi', 'Mumbai'),
('r3', 'Chandigarh', 'Delhi'),
('r4', 'Delhi', 'Pudducherry'),
('r5', 'Hyderabad', 'Banglore'),
('r6', 'Kolkata', 'Mumbai'),
('r7', 'Delhi', 'Kolkata'),
('r8', 'Surat', 'Delhi'),
('r9', 'Ahemdabad', 'Delhi'),
('r10', 'Ahemdabad', 'Srinagar');

-- --------------------------------------------------------

--
-- Table structure for table `timing`
--

CREATE TABLE IF NOT EXISTS `timing` (
  `a_code` varchar(7) NOT NULL,
  `r_code` varchar(7) NOT NULL,
  `d_time` varchar(7) NOT NULL,
  `hours` varchar(10) NOT NULL,
  `a_time` varchar(7) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `timing`
--

INSERT INTO `timing` (`a_code`, `r_code`, `d_time`, `hours`, `a_time`) VALUES
('A101', 'r1', '10:00', '03:00', '13:00'),
('A105', 'r1', '04:00', '03:00', '07:00'),
('A109', 'r2', '22:00', '03:00', '01:00'),
('A103', 'r6', '05:00', '03:00', '08:00'),
('A107', 'r9', '17:00', '01:30', '18:30'),
('A104', 'r6', '16:00', '03:00', '19:00');
