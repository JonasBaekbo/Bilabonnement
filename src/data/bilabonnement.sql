-- MySQL dump 10.13  Distrib 8.0.22, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: Bilabonnement
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `biler`
--

DROP TABLE IF EXISTS `biler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `biler` (
  `bil_id` int NOT NULL AUTO_INCREMENT,
  `stelnummer` int NOT NULL,
  `registeringsnummer` varchar(45) DEFAULT NULL,
  `model` int DEFAULT NULL,
  `brændstroftype` int DEFAULT NULL,
  `farve` int DEFAULT NULL,
  `geartype` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `registerings-afgift` varchar(45) DEFAULT NULL,
  `udlejet-fra` datetime DEFAULT NULL,
  `udlejet til` datetime DEFAULT NULL,
  `skader` int DEFAULT NULL,
  PRIMARY KEY (`bil_id`),
  UNIQUE KEY `stelnummer_UNIQUE` (`stelnummer`),
  KEY `bilmodel_idx` (`model`),
  KEY `brændstoftype_idx` (`brændstroftype`),
  KEY `farve_idx` (`farve`),
  KEY `geartype_idx` (`geartype`),
  KEY `udlejet_idx` (`status`),
  CONSTRAINT `bilmodel` FOREIGN KEY (`model`) REFERENCES `bilmodeller` (`id_bilmodeller`),
  CONSTRAINT `brændstoftype` FOREIGN KEY (`brændstroftype`) REFERENCES `brændstoftype` (`id_brændstoftype`),
  CONSTRAINT `farve` FOREIGN KEY (`farve`) REFERENCES `farve` (`id_farve`),
  CONSTRAINT `geartype` FOREIGN KEY (`geartype`) REFERENCES `geartype` (`id_geartype`),
  CONSTRAINT `udlejet` FOREIGN KEY (`status`) REFERENCES `status` (`id_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `biler`
--

LOCK TABLES `biler` WRITE;
/*!40000 ALTER TABLE `biler` DISABLE KEYS */;
/*!40000 ALTER TABLE `biler` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bilmodeller`
--

DROP TABLE IF EXISTS `bilmodeller`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bilmodeller` (
  `id_bilmodeller` int NOT NULL AUTO_INCREMENT,
  `producent` int NOT NULL,
  `bilmodel` varchar(45) NOT NULL,
  PRIMARY KEY (`id_bilmodeller`),
  KEY `producent_idx` (`producent`),
  CONSTRAINT `producent` FOREIGN KEY (`producent`) REFERENCES `producent` (`id_producent`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bilmodeller`
--

LOCK TABLES `bilmodeller` WRITE;
/*!40000 ALTER TABLE `bilmodeller` DISABLE KEYS */;
INSERT INTO `bilmodeller` VALUES (1,4,'7 Performance Line Pack 130HK'),(2,1,'3008 GT 130HK AUT.'),(3,1,' 3008 Allure Pack 130 HK AUT'),(4,3,'Crossland Sport 130 HK AUT.'),(5,1,'2008 Allure PACK 130 HK LTD.'),(6,3,'Crossland Sport 110 HK'),(7,3,'Crossland Sport 83 HK'),(8,3,'C3 Triumph Benzin 83 HK'),(9,1,'208 Active Pack 75 HK'),(10,2,'C3 Le Mans PureTech 83 HK'),(11,2,'Grand C4 Spacetourer COOL 130 HK'),(12,1,'2008 Allure Pack AUT. 130 HK');
/*!40000 ALTER TABLE `bilmodeller` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brændstoftype`
--

DROP TABLE IF EXISTS `brændstoftype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brændstoftype` (
  `id_brændstoftype` int NOT NULL AUTO_INCREMENT,
  `brændstoftype` varchar(45) NOT NULL,
  PRIMARY KEY (`id_brændstoftype`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brændstoftype`
--

LOCK TABLES `brændstoftype` WRITE;
/*!40000 ALTER TABLE `brændstoftype` DISABLE KEYS */;
INSERT INTO `brændstoftype` VALUES (1,'Benzin'),(2,'Disel'),(3,'plug_in_hybrid'),(4,'hybrid');
/*!40000 ALTER TABLE `brændstoftype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `farve`
--

DROP TABLE IF EXISTS `farve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `farve` (
  `id_farve` int NOT NULL AUTO_INCREMENT,
  `farve` varchar(45) NOT NULL,
  PRIMARY KEY (`id_farve`),
  UNIQUE KEY `farve_id_UNIQUE` (`id_farve`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `farve`
--

LOCK TABLES `farve` WRITE;
/*!40000 ALTER TABLE `farve` DISABLE KEYS */;
INSERT INTO `farve` VALUES (1,'sort'),(2,'rød'),(3,'hvid'),(4,'sølv'),(5,'gul'),(6,'orange'),(7,'blå'),(8,'grøn'),(9,'brun'),(10,'grå');
/*!40000 ALTER TABLE `farve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `geartype`
--

DROP TABLE IF EXISTS `geartype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `geartype` (
  `id_geartype` int NOT NULL AUTO_INCREMENT,
  `geartype` varchar(45) NOT NULL,
  PRIMARY KEY (`id_geartype`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `geartype`
--

LOCK TABLES `geartype` WRITE;
/*!40000 ALTER TABLE `geartype` DISABLE KEYS */;
INSERT INTO `geartype` VALUES (1,'automatisk'),(2,'manuelt');
/*!40000 ALTER TABLE `geartype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producent`
--

DROP TABLE IF EXISTS `producent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producent` (
  `id_producent` int NOT NULL AUTO_INCREMENT,
  `prducent` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_producent`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producent`
--

LOCK TABLES `producent` WRITE;
/*!40000 ALTER TABLE `producent` DISABLE KEYS */;
INSERT INTO `producent` VALUES (1,'Peugeot'),(2,'Citroën'),(3,'Opel'),(4,'DS');
/*!40000 ALTER TABLE `producent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skader`
--

DROP TABLE IF EXISTS `skader`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `skader` (
  `id_skade` int NOT NULL,
  `id_bil` int NOT NULL,
  `beskrivelse` varchar(45) NOT NULL,
  `omkostning_kr` int DEFAULT NULL,
  `anmelder` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_skade`),
  KEY `bil_idx` (`id_bil`),
  CONSTRAINT `bil` FOREIGN KEY (`id_bil`) REFERENCES `biler` (`bil_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skader`
--

LOCK TABLES `skader` WRITE;
/*!40000 ALTER TABLE `skader` DISABLE KEYS */;
/*!40000 ALTER TABLE `skader` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `id_status` int NOT NULL AUTO_INCREMENT,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_status`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'udlejet'),(2,'hjemme'),(3,'skadet');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-05 21:47:31
