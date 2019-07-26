CREATE DATABASE  IF NOT EXISTS `employee_portal` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `employee_portal`;
-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: employee_portal
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `companies`
--

DROP TABLE IF EXISTS `companies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `companies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(45) NOT NULL,
  `address_line_1` varchar(45) NOT NULL,
  `address_line_2` varchar(45) DEFAULT NULL,
  `zip` varchar(10) DEFAULT NULL,
  `logo` varchar(45) NOT NULL DEFAULT 'default_logo.png',
  `page_views` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companies`
--

LOCK TABLES `companies` WRITE;
/*!40000 ALTER TABLE `companies` DISABLE KEYS */;
INSERT INTO `companies` VALUES (1,'Adidas','Herzogenaurach','Germany','752463','adidas.svg',11541),(2,'Nike','Beaverton, Oregon','USA','450001','nike.svg',9538),(3,'Reebok','Boston, Massachusetts','USA','457569','reebok.svg',13459),(4,'Fila','Seoul, South Korea',NULL,'783669','fila.svg',8429);
/*!40000 ALTER TABLE `companies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `employees` (
  `username` varchar(20) NOT NULL,
  `_password` varchar(45) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `job` varchar(45) NOT NULL,
  `company_id` int(11) DEFAULT NULL,
  `image` varchar(45) NOT NULL DEFAULT 'default_img.jpg',
  `activity` int(11) NOT NULL DEFAULT '0',
  `last_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`username`),
  KEY `FK_COMPANY_idx` (`company_id`),
  CONSTRAINT `FK_COMPANY` FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES ('audrey.olivier','audrey123','Audrey','Olivier','Web Developer',1,'audrey.olivier.png',0,'2019-07-26 00:47:14'),('bette.nicholson','bette123','Bette','Nicholson','Intern',1,'bette.nicholson.png',0,'2019-07-26 00:47:44'),('bob.fawcett','bob123','Bob','Fawcett','Business Analyst',4,'bob.fawcett.png',0,'2019-07-26 00:48:04'),('ed.chase','ed123','Ed','Chase','Graphics Designer',3,'ed.chase.png',2,'2019-07-26 01:08:17'),('elvis.marx','elvis123','Elvis','Marx','HR',4,'elvis.marx.png',0,'2019-07-26 00:48:44'),('fred.costner','fred123','Fred','Costner','Intern',1,'fred.costner.png',0,'2019-07-26 00:49:08'),('helen.voight','helen123','Helen','Voight','HR',2,'helen.voight.png',0,'2019-07-26 00:49:52'),('jessica.bailey','jessica123','Jessica','Bailey','HR',4,'jessica.bailey.png',0,'2019-07-26 00:50:13'),('joe.swank','joe123','Joe','Swank','Product Manager',2,'joe.swank.png',0,'2019-07-26 00:50:33'),('johnny.cage','johnny123','Johnny','Cage','Accountant',2,'johnny.cage.png',0,'2019-07-26 00:50:58'),('julia.mcqueen','julia123','Julia','McQueen','Product Designer',1,'julia.mcqueen.png',0,'2019-07-26 00:40:30'),('karl.berry','karl123','Karl','Berry','Intern',2,'karl.berry.png',0,'2019-07-26 00:52:07'),('kirsten.paltrow','kirsten123','Kirsten','Paltrow','Software Developer',1,'kirsten.paltrow.png',0,'2019-07-26 00:40:30'),('natalie.hopkins','natalie123','Natalie','Hopkins','HR',3,'natalie.hopkins.png',0,'2019-07-26 00:52:37'),('sandra.kilmer','sandra123','Sandra','Kilmer','HR',3,'sandra.kilmer.png',0,'2019-07-26 00:52:56'),('sandra.peck','sandra123','Sandra','Peck','Accountant',1,'sandra.peck.png',0,'2019-07-26 00:53:25'),('vivien.bergen','vivien123','Vivien','Bergen','HR',1,'vivien.bergen.png',0,'2019-07-26 00:53:48');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-26  6:54:28
