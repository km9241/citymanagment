-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: smartcity
-- ------------------------------------------------------
-- Server version	8.0.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bus`
--

DROP TABLE IF EXISTS `bus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bus` (
  `bus_id` int NOT NULL,
  `capacity` int DEFAULT NULL,
  `route_id` int DEFAULT NULL,
  PRIMARY KEY (`bus_id`),
  KEY `route_id` (`route_id`),
  CONSTRAINT `bus_ibfk_1` FOREIGN KEY (`route_id`) REFERENCES `bus_route` (`route_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus`
--

LOCK TABLES `bus` WRITE;
/*!40000 ALTER TABLE `bus` DISABLE KEYS */;
INSERT INTO `bus` VALUES (201,50,1),(202,40,2);
/*!40000 ALTER TABLE `bus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_route`
--

DROP TABLE IF EXISTS `bus_route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bus_route` (
  `route_id` int NOT NULL,
  `start_point` varchar(50) DEFAULT NULL,
  `end_point` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`route_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_route`
--

LOCK TABLES `bus_route` WRITE;
/*!40000 ALTER TABLE `bus_route` DISABLE KEYS */;
INSERT INTO `bus_route` VALUES (1,'Station','Mall'),(2,'Airport','City Center');
/*!40000 ALTER TABLE `bus_route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `citizen`
--

DROP TABLE IF EXISTS `citizen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `citizen` (
  `citizen_id` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `dob` date DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `zone_id` int DEFAULT NULL,
  PRIMARY KEY (`citizen_id`),
  KEY `zone_id` (`zone_id`),
  CONSTRAINT `citizen_ibfk_1` FOREIGN KEY (`zone_id`) REFERENCES `zone` (`zone_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `citizen`
--

LOCK TABLES `citizen` WRITE;
/*!40000 ALTER TABLE `citizen` DISABLE KEYS */;
INSERT INTO `citizen` VALUES (101,'Rahul Sharma','2002-05-10','Male','9876543210','rahul@gmail.com',1),(102,'Anita Verma','2001-08-15','Female','9123456780','anita@gmail.com',2),(2424,'keshav','2006-01-13','male','9110300300145','km@gmail',1),(7251,'dino','2026-01-13','male','9110496382','dino@gmail.com',1),(94637,'saranya','2006-01-11','male','91103003004','arun@gmail.com',2),(1776794052,'KESHAV MAHESWARI','2026-04-06','Male','911030010047','km9241@srmist.edu.in',NULL);
/*!40000 ALTER TABLE `citizen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `complaint`
--

DROP TABLE IF EXISTS `complaint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `complaint` (
  `complaint_id` int NOT NULL,
  `citizen_id` int DEFAULT NULL,
  `complaint_type` varchar(50) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  `zone_id` int DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`complaint_id`),
  KEY `citizen_id` (`citizen_id`),
  KEY `zone_id` (`zone_id`),
  CONSTRAINT `complaint_ibfk_1` FOREIGN KEY (`citizen_id`) REFERENCES `citizen` (`citizen_id`),
  CONSTRAINT `complaint_ibfk_2` FOREIGN KEY (`zone_id`) REFERENCES `zone` (`zone_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaint`
--

LOCK TABLES `complaint` WRITE;
/*!40000 ALTER TABLE `complaint` DISABLE KEYS */;
INSERT INTO `complaint` VALUES (601,101,'Road Issue','Potholes on Main Street','Resolved',1,NULL),(602,102,'Water Issue','No Supply','Resolved',2,NULL),(42299,2424,'road','poor road','Pending',1,NULL);
/*!40000 ALTER TABLE `complaint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `electricity_usage`
--

DROP TABLE IF EXISTS `electricity_usage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `electricity_usage` (
  `usage_id` int NOT NULL,
  `citizen_id` int DEFAULT NULL,
  `units_consumed` int DEFAULT NULL,
  `bill_amount` decimal(8,2) DEFAULT NULL,
  `month` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`usage_id`),
  KEY `citizen_id` (`citizen_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `electricity_usage`
--

LOCK TABLES `electricity_usage` WRITE;
/*!40000 ALTER TABLE `electricity_usage` DISABLE KEYS */;
INSERT INTO `electricity_usage` VALUES (401,101,150,1200.00,'March'),(402,102,200,1600.00,'March'),(15322,2424,7,88888.00,'2006-01');
/*!40000 ALTER TABLE `electricity_usage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hospital`
--

DROP TABLE IF EXISTS `hospital`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hospital` (
  `hospital_id` int NOT NULL,
  `hospital_name` varchar(100) DEFAULT NULL,
  `zone_id` int DEFAULT NULL,
  PRIMARY KEY (`hospital_id`),
  KEY `zone_id` (`zone_id`),
  CONSTRAINT `hospital_ibfk_1` FOREIGN KEY (`zone_id`) REFERENCES `zone` (`zone_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hospital`
--

LOCK TABLES `hospital` WRITE;
/*!40000 ALTER TABLE `hospital` DISABLE KEYS */;
INSERT INTO `hospital` VALUES (1,'City Hospital',1),(2,'Green Care',2);
/*!40000 ALTER TABLE `hospital` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `payment_id` int NOT NULL,
  `citizen_id` int DEFAULT NULL,
  `service_type` varchar(50) DEFAULT NULL,
  `amount` decimal(8,2) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `citizen_id` (`citizen_id`),
  CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`citizen_id`) REFERENCES `citizen` (`citizen_id`),
  CONSTRAINT `chk_amount` CHECK ((`amount` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (701,101,'Electricity',1200.00,'2026-03-05'),(702,102,'Water',400.00,'2026-03-06'),(79790,2424,'water',50000.00,'2006-01-13');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `police_station`
--

DROP TABLE IF EXISTS `police_station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `police_station` (
  `station_id` int NOT NULL,
  `station_name` varchar(100) DEFAULT NULL,
  `zone_id` int DEFAULT NULL,
  PRIMARY KEY (`station_id`),
  KEY `zone_id` (`zone_id`),
  CONSTRAINT `police_station_ibfk_1` FOREIGN KEY (`zone_id`) REFERENCES `zone` (`zone_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `police_station`
--

LOCK TABLES `police_station` WRITE;
/*!40000 ALTER TABLE `police_station` DISABLE KEYS */;
INSERT INTO `police_station` VALUES (1,'Central Police',1),(2,'South Police',2);
/*!40000 ALTER TABLE `police_station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `power_station`
--

DROP TABLE IF EXISTS `power_station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `power_station` (
  `station_id` int NOT NULL,
  `capacity` int DEFAULT NULL,
  `zone_id` int DEFAULT NULL,
  PRIMARY KEY (`station_id`),
  KEY `zone_id` (`zone_id`),
  CONSTRAINT `power_station_ibfk_1` FOREIGN KEY (`zone_id`) REFERENCES `zone` (`zone_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `power_station`
--

LOCK TABLES `power_station` WRITE;
/*!40000 ALTER TABLE `power_station` DISABLE KEYS */;
INSERT INTO `power_station` VALUES (1,500,1),(2,700,2);
/*!40000 ALTER TABLE `power_station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `road`
--

DROP TABLE IF EXISTS `road`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `road` (
  `road_id` int NOT NULL,
  `road_name` varchar(50) DEFAULT NULL,
  `condition_status` varchar(30) DEFAULT NULL,
  `zone_id` int DEFAULT NULL,
  PRIMARY KEY (`road_id`),
  KEY `zone_id` (`zone_id`),
  CONSTRAINT `road_ibfk_1` FOREIGN KEY (`zone_id`) REFERENCES `zone` (`zone_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `road`
--

LOCK TABLES `road` WRITE;
/*!40000 ALTER TABLE `road` DISABLE KEYS */;
INSERT INTO `road` VALUES (1,'Main Street','Good',1),(2,'Lake Road','Damaged',2);
/*!40000 ALTER TABLE `road` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `ticket_id` int NOT NULL,
  `citizen_id` int DEFAULT NULL,
  `bus_id` int DEFAULT NULL,
  `fare` decimal(6,2) DEFAULT NULL,
  `travel_date` date DEFAULT NULL,
  PRIMARY KEY (`ticket_id`),
  KEY `citizen_id` (`citizen_id`),
  KEY `bus_id` (`bus_id`),
  CONSTRAINT `ticket_ibfk_2` FOREIGN KEY (`bus_id`) REFERENCES `bus` (`bus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (301,101,201,25.50,'2026-03-01'),(302,102,202,30.00,'2026-03-02');
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `waste_collection`
--

DROP TABLE IF EXISTS `waste_collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `waste_collection` (
  `collection_id` int NOT NULL,
  `zone_id` int DEFAULT NULL,
  `waste_type` varchar(50) DEFAULT NULL,
  `collection_date` date DEFAULT NULL,
  PRIMARY KEY (`collection_id`),
  KEY `zone_id` (`zone_id`),
  CONSTRAINT `waste_collection_ibfk_1` FOREIGN KEY (`zone_id`) REFERENCES `zone` (`zone_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `waste_collection`
--

LOCK TABLES `waste_collection` WRITE;
/*!40000 ALTER TABLE `waste_collection` DISABLE KEYS */;
INSERT INTO `waste_collection` VALUES (1,1,'Dry Waste','2026-03-01'),(2,2,'Wet Waste','2026-03-02');
/*!40000 ALTER TABLE `waste_collection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `water_tank`
--

DROP TABLE IF EXISTS `water_tank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `water_tank` (
  `tank_id` int NOT NULL,
  `capacity` int DEFAULT NULL,
  `zone_id` int DEFAULT NULL,
  PRIMARY KEY (`tank_id`),
  KEY `zone_id` (`zone_id`),
  CONSTRAINT `water_tank_ibfk_1` FOREIGN KEY (`zone_id`) REFERENCES `zone` (`zone_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `water_tank`
--

LOCK TABLES `water_tank` WRITE;
/*!40000 ALTER TABLE `water_tank` DISABLE KEYS */;
INSERT INTO `water_tank` VALUES (1,1000,1),(2,1500,2);
/*!40000 ALTER TABLE `water_tank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `water_usage`
--

DROP TABLE IF EXISTS `water_usage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `water_usage` (
  `usage_id` int NOT NULL,
  `citizen_id` int DEFAULT NULL,
  `liters_used` int DEFAULT NULL,
  `bill_amount` decimal(8,2) DEFAULT NULL,
  `month` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`usage_id`),
  KEY `citizen_id` (`citizen_id`),
  CONSTRAINT `water_usage_ibfk_1` FOREIGN KEY (`citizen_id`) REFERENCES `citizen` (`citizen_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `water_usage`
--

LOCK TABLES `water_usage` WRITE;
/*!40000 ALTER TABLE `water_usage` DISABLE KEYS */;
INSERT INTO `water_usage` VALUES (501,101,500,300.00,'March'),(502,102,650,400.00,'March');
/*!40000 ALTER TABLE `water_usage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zone`
--

DROP TABLE IF EXISTS `zone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zone` (
  `zone_id` int NOT NULL,
  `zone_name` varchar(50) NOT NULL,
  `area` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`zone_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zone`
--

LOCK TABLES `zone` WRITE;
/*!40000 ALTER TABLE `zone` DISABLE KEYS */;
INSERT INTO `zone` VALUES (1,'North Zone','Urban'),(2,'South Zone','Residential');
/*!40000 ALTER TABLE `zone` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-01  3:20:45
