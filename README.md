# customer-app
bank-app



-------------------------------------------------------------

-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: java
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `person`
--

CREATE SCHEMA `java` ;

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
`id` int DEFAULT NULL,
`name` varchar(30) DEFAULT NULL,
`family` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'darya','sharifi'),(2,'diba','rahimi');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person_2`
--

DROP TABLE IF EXISTS `person_2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person_2` (
`id` int DEFAULT NULL,
`name` varchar(20) DEFAULT NULL,
`family` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person_2`
--

LOCK TABLES `person_2` WRITE;
/*!40000 ALTER TABLE `person_2` DISABLE KEYS */;
INSERT INTO `person_2` VALUES (1,'delaram','sharifi'),(2,'darya','sharifi'),(3,'kaveh','sharifi'),(4,'javaher','rahimi');
/*!40000 ALTER TABLE `person_2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person_person`
--

DROP TABLE IF EXISTS `person_person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person_person` (
`id` int DEFAULT NULL,
`name` varchar(20) DEFAULT NULL,
`family` varchar(20) DEFAULT NULL,
`mobileno` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person_person`
--

LOCK TABLES `person_person` WRITE;
/*!40000 ALTER TABLE `person_person` DISABLE KEYS */;
INSERT INTO `person_person` VALUES (1,'deli','sharifi',123457000),(2,'kaveh','sharifi',12357000);
/*!40000 ALTER TABLE `person_person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblaccountinformation`
--

DROP TABLE IF EXISTS `tblaccountinformation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblaccountinformation` (
`id` int NOT NULL AUTO_INCREMENT,
`customerid` int DEFAULT NULL,
`accounttypeid` int DEFAULT NULL,
`cashamount` int DEFAULT NULL,
`withdrewamount` int DEFAULT NULL,
`depositamount` int DEFAULT NULL,
`withdrewdate` timestamp NULL DEFAULT NULL,
`depositdate` timestamp NULL DEFAULT NULL,
`comment` varchar(100) DEFAULT NULL,
PRIMARY KEY (`id`),
KEY `tblcustomer_custmerid_fk_idx` (`customerid`),
CONSTRAINT `tblcustomer_custmerid_fk` FOREIGN KEY (`customerid`) REFERENCES `tblcustomer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblaccountinformation`
--

LOCK TABLES `tblaccountinformation` WRITE;
/*!40000 ALTER TABLE `tblaccountinformation` DISABLE KEYS */;
INSERT INTO `tblaccountinformation` VALUES (20,3,3,2500,NULL,2500,NULL,'2022-03-04 19:09:32',''),(21,2,2,160000,NULL,160000,NULL,'2022-03-04 19:11:45',''),(22,2,2,125000,NULL,125000,NULL,'2022-03-04 19:16:03',''),(23,3,3,1400,1100,NULL,'2022-03-04 19:18:38',NULL,'for buy car'),(24,2,2,1985000,NULL,1700000,NULL,'2022-03-04 19:23:24','for new home'),(25,1,1,123000,NULL,123000,NULL,'2022-03-04 20:18:45',''),(26,1,2,221000,NULL,221000,NULL,'2022-03-04 20:19:01',''),(27,1,3,147000,NULL,147000,NULL,'2022-03-04 20:19:11',''),(28,1,1,373000,NULL,250000,NULL,'2022-03-04 20:19:37','for travel'),(29,1,2,233000,NULL,12000,NULL,'2022-03-04 20:20:31','for rent'),(30,1,1,1600,NULL,1600,NULL,'2022-03-05 15:18:19',''),(31,1,3,250000,NULL,250000,NULL,'2022-03-05 15:19:25',''),(32,2,2,500000,NULL,500000,NULL,'2022-03-05 16:30:57','for buy house'),(33,1,1,274600,100000,NULL,'2022-03-05 16:32:36',NULL,'for buy car'),(34,1,1,474600,NULL,200000,NULL,'2022-03-05 16:34:11','-');
/*!40000 ALTER TABLE `tblaccountinformation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblcustomer`
--

DROP TABLE IF EXISTS `tblcustomer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblcustomer` (
`id` int NOT NULL AUTO_INCREMENT,
`name` varchar(45) DEFAULT NULL,
`family` varchar(45) DEFAULT NULL,
`nationalcode` varchar(10) DEFAULT NULL,
`mobilenumber` varchar(11) DEFAULT NULL,
`password` varchar(100) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblcustomer`
--

LOCK TABLES `tblcustomer` WRITE;
/*!40000 ALTER TABLE `tblcustomer` DISABLE KEYS */;
INSERT INTO `tblcustomer` VALUES (1,'diba','sharifi','2210008719','09101090511','AE7BE26CDAA742CA148068D5AC90EACA'),(2,'nusha','ghanbari','0075175266','2219032278','7C661225B5A6222E09546228DFB436A6'),(3,'omid','ashprinia','0075175267','09126607351','81DC9BDB52D04DC20036DBD8313ED055'),(4,'karim','limaei','0087165391','22190398461','6B5885D689EB26EE68814EE3EE639672'),(7,'zahra','mashreghi','0005753120','09214526987','82097BFDD288FCEA7F5696EFB3148CA9'),(9,'maryam','musavi','2213650079','09126607589','BC4DD17698FF5A1C33A9AA2CC6335968'),(10,'nazanin','abassi','2213695557','09152465789','BC4DD17698FF5A1C33A9AA2CC6335968'),(11,'dorsa','amjezi','2219042278','09391648777','BC4DD17698FF5A1C33A9AA2CC6335968'),(12,'saba','mohammadi','2215653245','09378887898','BC4DD17698FF5A1C33A9AA2CC6335968'),(13,'saghar','hatami','2220008574','093547895','BC4DD17698FF5A1C33A9AA2CC6335968'),(15,'masoumeh','alizadeh','2213005478','09394448776','AE7BE26CDAA742CA148068D5AC90EACA'),(18,'arshia','mohammadi','2210008716','09125246898','8A8BB7CD343AA2AD99B7D762030857A2');
/*!40000 ALTER TABLE `tblcustomer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-05 20:56:15



------------------------------------------------------
