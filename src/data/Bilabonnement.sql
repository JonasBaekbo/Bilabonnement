CREATE DATABASE  IF NOT EXISTS `bilabonnement` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `bilabonnement`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: bilabonnementwhatever.mysql.database.azure.com    Database: bilabonnement
-- ------------------------------------------------------
-- Server version	5.6.47.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `car_colour`
--

DROP TABLE IF EXISTS `car_colour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car_colour` (
  `colour_id` int(11) NOT NULL AUTO_INCREMENT,
  `colour` varchar(45) NOT NULL,
  PRIMARY KEY (`colour_id`),
  UNIQUE KEY `farve_id_UNIQUE` (`colour_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_colour`
--

LOCK TABLES `car_colour` WRITE;
/*!40000 ALTER TABLE `car_colour` DISABLE KEYS */;
INSERT INTO `car_colour` VALUES (1,'Blå'),(2,'Brun'),(3,'Grå'),(4,'Grøn'),(5,'Gul'),(6,'Hvid'),(7,'Lilla'),(8,'Orange'),(9,'Rød'),(10,'Sort'),(11,'Sølv');
/*!40000 ALTER TABLE `car_colour` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car_models`
--

DROP TABLE IF EXISTS `car_models`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car_models` (
  `car_model_id` int(11) NOT NULL AUTO_INCREMENT,
  `manufacturer_id` int(11) NOT NULL,
  `model` varchar(45) NOT NULL,
  `price_per_month` int(45) NOT NULL,
  PRIMARY KEY (`car_model_id`),
  KEY `producent_idx` (`manufacturer_id`),
  CONSTRAINT `producent` FOREIGN KEY (`manufacturer_id`) REFERENCES `manufacturer` (`manufacturer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_models`
--

LOCK TABLES `car_models` WRITE;
/*!40000 ALTER TABLE `car_models` DISABLE KEYS */;
INSERT INTO `car_models` VALUES (1,4,'7 Performance Line Pack 130HK',5999),(2,1,'3008 GT 130HK AUT.',4999),(3,1,'3008 Allure Pack 130 HK AUT',4999),(4,3,'Crossland Sport 130 HK AUT.',4299),(5,1,'2008 Allure PACK 130 HK LTD.',3999),(6,3,'Crossland Sport 110 HK',5999),(7,3,'Crossland Sport 83 HK',4299),(8,3,'C3 Triumph Benzin 83 HK',6999),(9,1,'208 Active Pack 75 HK',3099),(10,2,'C3 Le Mans PureTech 83 HK',3199),(11,2,'Grand C4 Spacetourer COOL 130 HK',4999),(12,1,'2008 Allure Pack AUT. 130 HK',4999),(13,3,'Corsa Sport Ltd. 75 HK',3299);
/*!40000 ALTER TABLE `car_models` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car_status`
--

DROP TABLE IF EXISTS `car_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car_status` (
  `car_status_id` int(11) NOT NULL AUTO_INCREMENT,
  `car_status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`car_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_status`
--

LOCK TABLES `car_status` WRITE;
/*!40000 ALTER TABLE `car_status` DISABLE KEYS */;
INSERT INTO `car_status` VALUES (1,'Udlejet-Limited'),(2,'Udlejet-Unlimited'),(3,'Skadet'),(4,'Hjemme'),(5,'Udgået af flåden');
/*!40000 ALTER TABLE `car_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cars`
--

DROP TABLE IF EXISTS `cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cars` (
  `car_id` int(11) NOT NULL AUTO_INCREMENT,
  `vin_number` varchar(20) NOT NULL,
  `licence_plate` varchar(20) DEFAULT NULL,
  `car_model_id` int(11) DEFAULT NULL,
  `fuel_type_id` int(11) DEFAULT NULL,
  `colour_id` int(11) DEFAULT NULL,
  `gear_type_id` int(11) DEFAULT NULL,
  `car_status_id` int(11) DEFAULT NULL,
  `current_leasing_id` int(11) DEFAULT NULL,
  `car_added` datetime NOT NULL,
  `steel_price` decimal(10,2) DEFAULT NULL,
  `co2_emission_g_per_km` decimal(3,2) DEFAULT NULL,
  `registration_fee` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`car_id`),
  UNIQUE KEY `stelnummer_UNIQUE` (`vin_number`),
  KEY `bilmodel_idx` (`car_model_id`),
  KEY `brændstoftype_idx` (`fuel_type_id`),
  KEY `farve_idx` (`colour_id`),
  KEY `geartype_idx` (`gear_type_id`),
  KEY `udlejet_idx` (`car_status_id`),
  KEY `leasing-aftale_idx` (`current_leasing_id`),
  CONSTRAINT `car_model` FOREIGN KEY (`car_model_id`) REFERENCES `car_models` (`car_model_id`),
  CONSTRAINT `car_status` FOREIGN KEY (`car_status_id`) REFERENCES `car_status` (`car_status_id`),
  CONSTRAINT `farve` FOREIGN KEY (`colour_id`) REFERENCES `car_colour` (`colour_id`),
  CONSTRAINT `fuel_type` FOREIGN KEY (`fuel_type_id`) REFERENCES `fuel_types` (`fuel_type_id`),
  CONSTRAINT `gear_type` FOREIGN KEY (`gear_type_id`) REFERENCES `gear_type` (`gear_type_id`),
  CONSTRAINT `leasing` FOREIGN KEY (`current_leasing_id`) REFERENCES `leasing` (`leasing_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars`
--

LOCK TABLES `cars` WRITE;
/*!40000 ALTER TABLE `cars` DISABLE KEYS */;
INSERT INTO `cars` VALUES (1,'ABC12345678910116','DD26451',2,4,4,1,4,NULL,'2022-01-01 00:00:00',NULL,NULL,800.00),(2,'BCD23456789101167','AC15679',7,1,1,1,1,2,'2022-01-01 00:00:00',NULL,NULL,800.00),(3,'CDE34567891011677','AC15680',9,3,2,1,4,NULL,'2022-02-06 00:00:00',NULL,NULL,750.00),(4,'DEF45678910116779','AC15678',8,2,5,2,3,7,'2022-06-01 00:00:00',NULL,NULL,900.00),(5,'EFG67891011677900','AC26048',1,1,3,2,1,8,'2022-06-03 00:00:00',NULL,NULL,1200.00),(6,'FGH78910116779008','AB12345',5,1,1,2,3,12,'2022-03-03 00:00:00',NULL,NULL,600.00),(7,'GHK89101167790089','AB12346',6,2,5,2,4,NULL,'2022-02-15 00:00:00',NULL,NULL,600.00),(8,'HKH10116779008900','DD26452',8,3,3,1,1,4,'2022-03-23 00:00:00',NULL,NULL,750.00),(9,'FFH11677900890098','AC15680',9,1,8,1,4,0,'2022-04-18 00:00:00',NULL,NULL,800.00),(10,'WVK12694564798946','AC15681',10,1,9,1,4,NULL,'2022-05-21 00:00:00',NULL,NULL,800.00),(11,'WVZ15688000167902','AZ12345',11,2,10,2,4,NULL,'2022-01-30 00:00:00',NULL,NULL,700.00),(12,'AAB26003489259724','AC26049',12,1,10,2,3,6,'2022-04-18 00:00:00',NULL,NULL,1100.00),(13,'AAB26003489259733','BC98711',2,4,1,1,1,11,'1022-03-15 00:00:00',NULL,NULL,1100.00),(14,'HJG16357962345975','LP12345',3,3,4,2,4,NULL,'2022-05-21 00:00:00',NULL,NULL,800.00),(15,'AAK16853269753463','',11,5,6,2,4,NULL,'2022-05-21 00:00:00',NULL,NULL,NULL),(16,'PTB26008923668991','',10,1,1,1,4,NULL,'2022-05-21 00:00:00',NULL,NULL,NULL),(17,'HHP00065237951657','LP77777',7,4,5,2,2,14,'2022-05-21 00:00:00',NULL,NULL,950.00),(18,'SKL26846511233699','LP12346',4,1,6,2,4,NULL,'2022-05-21 00:00:00',NULL,NULL,600.00),(19,'CDD33352679513257','',13,1,1,1,4,NULL,'2022-05-22 00:00:00',NULL,NULL,NULL);
/*!40000 ALTER TABLE `cars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `damages`
--

DROP TABLE IF EXISTS `damages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `damages` (
  `damages_id` int(11) NOT NULL AUTO_INCREMENT,
  `car_id` int(11) NOT NULL,
  `damage_description` varchar(200) NOT NULL,
  `damages_cost_kr` decimal(11,2) DEFAULT NULL,
  `claimant` varchar(45) DEFAULT NULL,
  `damage_date` datetime NOT NULL,
  `damage_closed` datetime DEFAULT NULL,
  `damage_added` datetime NOT NULL,
  PRIMARY KEY (`damages_id`),
  KEY `bil_idx` (`car_id`),
  CONSTRAINT `car` FOREIGN KEY (`car_id`) REFERENCES `cars` (`car_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `damages`
--

LOCK TABLES `damages` WRITE;
/*!40000 ALTER TABLE `damages` DISABLE KEYS */;
INSERT INTO `damages` VALUES (1,2,'bule i højre side dør',1200.00,NULL,'2021-01-06 00:00:00','2022-05-20 00:00:00','2022-05-12 11:03:13'),(2,2,'venstre sidespejl skadet',1200.00,'FDM','2021-02-06 00:00:00','2022-05-17 00:00:00','2022-05-12 11:06:31'),(3,9,'ridse ved højre baghjul',600.00,'FDM','2021-10-06 00:00:00','2022-05-17 00:00:00','2022-05-12 12:28:31'),(4,10,'bule i dør -føreside',2000.00,'FDM','2021-06-17 00:00:00','2022-05-20 00:00:00','2022-05-17 13:17:01'),(5,10,'pletter på indtræk bagsæde',2000.00,'FDM','2021-06-17 00:00:00','2022-05-20 00:00:00','2022-05-17 13:22:39'),(6,10,'stenslag i forrude',999.00,'FDM','2021-06-17 00:00:00','2022-05-21 00:00:00','2022-05-17 13:26:54'),(7,10,'venstre forlygte virker ikke',2000.00,'FDM','2021-06-17 00:00:00','2022-05-21 00:00:00','2022-05-17 13:30:38'),(8,10,'nye vinduesviskere',250.00,'FDM','2021-06-17 00:00:00','2022-05-20 00:00:00','2022-05-17 13:34:40'),(9,10,'bule i dør -føreside',2000.00,'FDM','2021-06-17 00:00:00','2022-05-20 00:00:00','2022-05-17 13:37:05'),(10,10,'skrammer på bagsmæk',2000.00,'FDM','2021-06-17 00:00:00','2022-05-21 00:00:00','2022-05-17 13:40:32'),(11,10,'AC virker ikke',2000.00,'FDM','2021-06-17 00:00:00','2022-05-22 00:00:00','2022-05-17 13:43:03'),(12,5,'venstre side er ridset',1500.00,'eget værksted','2021-06-17 00:00:00','2022-05-20 00:00:00','2022-05-17 15:42:53'),(13,1,'stenslag forrude',550.00,'fdm','2022-05-20 00:00:00','2022-05-20 00:00:00','2022-05-20 12:36:06'),(14,6,'mislyd i motoren',5510.00,'eget værksted','2022-05-20 00:00:00',NULL,'2022-05-20 12:46:20'),(15,4,'stenslag venstre side af forrude',550.00,'fdm','2022-05-12 00:00:00',NULL,'2022-05-20 12:50:12'),(16,1,'ridse i dør',500.00,'fdm','2022-05-18 00:00:00','2022-05-23 00:00:00','2022-05-20 12:52:15'),(17,2,'magler en vinduesvisker',60.50,'fdm','2022-05-18 00:00:00','2022-05-19 00:00:00','2022-05-20 12:55:18'),(18,1,'Mangler højre sidespejl',1500.00,'fdm','2022-05-21 00:00:00','2022-05-23 00:00:00','2022-05-20 14:00:06'),(19,12,'døren vil ikke lukke',42.00,'theo','2022-05-20 00:00:00',NULL,'2022-05-21 17:04:09'),(20,1,'den lugter af cigaretter',200.00,'eget værksted','2022-05-22 00:00:00',NULL,'2022-05-22 18:25:40'),(21,3,'bilen lugter',123.00,'eget værkste','2022-05-21 00:00:00','2022-05-22 00:00:00','2022-05-22 19:29:01'),(22,3,'Batteri dræner for hurtigt',3000.00,'FDM','2022-05-21 00:00:00','2022-05-22 00:00:00','2022-05-22 19:31:52');
/*!40000 ALTER TABLE `damages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `extra_equipment_description`
--

DROP TABLE IF EXISTS `extra_equipment_description`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `extra_equipment_description` (
  `extra_equipment_id` int(11) NOT NULL AUTO_INCREMENT,
  `extra_equipment_description` varchar(45) NOT NULL,
  PRIMARY KEY (`extra_equipment_id`),
  UNIQUE KEY `ekstra_equipemnt_id_UNIQUE` (`extra_equipment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extra_equipment_description`
--

LOCK TABLES `extra_equipment_description` WRITE;
/*!40000 ALTER TABLE `extra_equipment_description` DISABLE KEYS */;
INSERT INTO `extra_equipment_description` VALUES (1,'Apple CarPlay'),(2,'Aut. klimaanlæg'),(3,'Aut. klimaanlæg m. 2 zoner\''),(4,'Alcantara indtræk'),(5,'Adaptive fartpilot'),(6,'Bakkamera'),(7,'180° Bakkamera'),(8,'Driver Assistance Pack'),(9,'3D navigation med 10\" skærm\''),(10,'7\" touchskærm'),(11,'12\" Touch skærm'),(12,'P-sensor'),(13,'Panoramaglastag'),(14,'Panoramaforrude');
/*!40000 ALTER TABLE `extra_equipment_description` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `extra_equipment_m2m`
--

DROP TABLE IF EXISTS `extra_equipment_m2m`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `extra_equipment_m2m` (
  `extra_equipment_id` int(11) NOT NULL,
  `car_id` int(11) NOT NULL,
  UNIQUE KEY `unique_together` (`extra_equipment_id`,`car_id`),
  KEY `ekstra_equipment_id_idx` (`extra_equipment_id`),
  KEY `car_id_idx` (`car_id`),
  CONSTRAINT `car_id` FOREIGN KEY (`car_id`) REFERENCES `cars` (`car_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `extra_equipmentid` FOREIGN KEY (`extra_equipment_id`) REFERENCES `extra_equipment_description` (`extra_equipment_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extra_equipment_m2m`
--

LOCK TABLES `extra_equipment_m2m` WRITE;
/*!40000 ALTER TABLE `extra_equipment_m2m` DISABLE KEYS */;
INSERT INTO `extra_equipment_m2m` VALUES (1,1),(1,4),(1,5),(1,7),(1,8),(1,9),(1,10),(1,11),(1,13),(2,1),(2,4),(2,12),(2,19),(3,1),(3,4),(3,6),(4,2),(5,2),(5,4),(5,19);
/*!40000 ALTER TABLE `extra_equipment_m2m` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fuel_types`
--

DROP TABLE IF EXISTS `fuel_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fuel_types` (
  `fuel_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `fuel_type` varchar(45) NOT NULL,
  PRIMARY KEY (`fuel_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fuel_types`
--

LOCK TABLES `fuel_types` WRITE;
/*!40000 ALTER TABLE `fuel_types` DISABLE KEYS */;
INSERT INTO `fuel_types` VALUES (1,'Benzin'),(2,'Disel'),(3,'Plug_in_hybrid'),(4,'Hybrid'),(5,'El');
/*!40000 ALTER TABLE `fuel_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gear_type`
--

DROP TABLE IF EXISTS `gear_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gear_type` (
  `gear_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `gear_type` varchar(45) NOT NULL,
  PRIMARY KEY (`gear_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gear_type`
--

LOCK TABLES `gear_type` WRITE;
/*!40000 ALTER TABLE `gear_type` DISABLE KEYS */;
INSERT INTO `gear_type` VALUES (1,'Automatisk'),(2,'Manuelt');
/*!40000 ALTER TABLE `gear_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leasing`
--

DROP TABLE IF EXISTS `leasing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `leasing` (
  `leasing_id` int(11) NOT NULL AUTO_INCREMENT,
  `car_id` int(11) NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `included_km` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `leasing_added` datetime NOT NULL,
  PRIMARY KEY (`leasing_id`),
  KEY `bil_id_idx` (`car_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leasing`
--

LOCK TABLES `leasing` WRITE;
/*!40000 ALTER TABLE `leasing` DISABLE KEYS */;
INSERT INTO `leasing` VALUES (1,3,'2022-05-11 00:00:00','2022-05-11 00:00:00',500,5,'2022-05-11 12:40:34'),(2,3,'2022-05-11 00:00:00','2022-05-11 00:00:00',500,5,'2022-05-11 12:53:12'),(3,3,'2022-05-11 00:00:00','2022-05-11 00:00:00',500,5,'2022-05-11 20:50:25'),(4,8,'2011-11-11 00:00:00','2012-12-12 00:00:00',8000,510510,'2022-05-12 11:40:45'),(5,1,'2022-05-16 00:00:00','2022-08-25 00:00:00',200,123,'2022-05-16 10:28:50'),(6,12,'2022-05-22 00:00:00','2022-07-30 00:00:00',2000,5,'2022-05-19 15:02:28'),(7,4,'2022-05-20 00:00:00','2022-09-30 00:00:00',3000,6,'2022-05-19 15:09:27'),(8,5,'2022-05-19 00:00:00','2022-05-22 00:00:00',120,1000,'2022-05-19 21:13:58'),(9,13,'2022-05-20 00:00:00','2022-05-29 00:00:00',128,1200,'2022-05-19 21:16:41'),(10,5,'2022-05-02 00:00:00','2022-05-04 00:00:00',19,9,'2022-05-19 21:18:19'),(11,13,'2022-05-17 00:00:00','2022-05-22 00:00:00',9,9,'2022-05-19 21:21:16'),(12,6,'2022-05-09 00:00:00','2022-05-10 00:00:00',9,8,'2022-05-19 21:25:44'),(13,16,'2022-05-20 17:16:00','2022-05-22 18:17:00',77,45,'2022-05-21 14:23:22'),(14,17,'2022-05-27 20:25:00','2022-05-29 20:49:00',123,234,'2022-05-21 15:44:27'),(15,5,'2022-05-22 22:00:00','2022-06-25 21:00:00',700,56,'2022-05-21 17:06:15');
/*!40000 ALTER TABLE `leasing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manufacturer`
--

DROP TABLE IF EXISTS `manufacturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manufacturer` (
  `manufacturer_id` int(11) NOT NULL AUTO_INCREMENT,
  `manufacturer` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`manufacturer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manufacturer`
--

LOCK TABLES `manufacturer` WRITE;
/*!40000 ALTER TABLE `manufacturer` DISABLE KEYS */;
INSERT INTO `manufacturer` VALUES (1,'Peugeot'),(2,'Citroën'),(3,'Opel'),(4,'DS');
/*!40000 ALTER TABLE `manufacturer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(64) NOT NULL,
  `role` varchar(45) NOT NULL,
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'namhm','$2a$10$XptfskLsT1l/bRTLRiiCgejHqOpgXFreUnNUa35gJdCr2v2QbVFzu','ROLE_USER',1),(2,'admin','$2a$10$zxvEq8XzYEYtNjbkRsJEbukHeRx3XS6MDXHMu8cNuNsRfZJWwswDy','ROLE_ADMIN',1),(3,'MoMoney','$2a$12$9D6HEsLoZTKRLdCRhtD/Z.8pG7lC5taGk/YhiLpFxOYx3oF5XZqJa','ROLE_BUISNESS',1),(4,'IControlDamages','$2a$12$cOP/o3LrwqGJNxYzkEunYOjFb8qi6lFG1cUX8QCYELuLTd6QWqVNC','ROLE_DAMAGE',1),(5,'DataIsTheBest','$2a$12$.b4XbxQD05uPlr.37HG3Y.gy/E6rYTP4B9lxnfyyoCCeJ.FQY./SW','ROLE_DATA',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-26 10:41:32
