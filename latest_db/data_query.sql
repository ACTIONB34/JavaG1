-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: moviereservation
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
-- Table structure for table `cinemas`
--

DROP TABLE IF EXISTS `cinemas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cinemas` (
  `cinema_id` int(11) NOT NULL,
  `movie_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`cinema_id`),
  KEY `movieC_id_idx` (`movie_id`),
  CONSTRAINT `movieC_id` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`movie_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cinemas`
--

LOCK TABLES `cinemas` WRITE;
/*!40000 ALTER TABLE `cinemas` DISABLE KEYS */;
INSERT INTO `cinemas` VALUES (1,1),(2,2),(3,3),(4,4);
/*!40000 ALTER TABLE `cinemas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `customers` (
  `customer_id` int(11) NOT NULL,
  `customer_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'Johnny Test');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movies`
--

DROP TABLE IF EXISTS `movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `movies` (
  `movie_id` int(11) NOT NULL,
  `movie_name` varchar(40) DEFAULT NULL,
  `movie_genre` varchar(20) DEFAULT NULL,
  `movie_rating` varchar(20) DEFAULT NULL,
  `movie_director` varchar(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`movie_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies`
--

LOCK TABLES `movies` WRITE;
/*!40000 ALTER TABLE `movies` DISABLE KEYS */;
INSERT INTO `movies` VALUES (1,'Harry Potter','Adventure','R','You',1),(2,'One Piece','Anime','R','Me',1),(3,'Up','Cartoon','R','We',1),(4,'Avengers','Action','R','He',1);
/*!40000 ALTER TABLE `movies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `reservations` (
  `reservation_id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `cinema_id` int(11) DEFAULT NULL,
  `time` varchar(20) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `total_payment` int(11) DEFAULT NULL,
  `no_of_kid` int(11) DEFAULT NULL,
  `no_of_adult` int(11) DEFAULT NULL,
  `no_of_senior` int(11) DEFAULT NULL,
  `timeslot_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`reservation_id`),
  KEY `cinemaR_id_idx` (`cinema_id`),
  KEY `customerR_id_idx` (`customer_id`),
  KEY `timeslotR_id_idx` (`timeslot_id`),
  CONSTRAINT `cinemaR_id` FOREIGN KEY (`cinema_id`) REFERENCES `cinemas` (`cinema_id`),
  CONSTRAINT `customerR_id` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`),
  CONSTRAINT `timeslotR_id` FOREIGN KEY (`timeslot_id`) REFERENCES `timeslots` (`timeslot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
INSERT INTO `reservations` VALUES (1,'2020-03-31',1,'12:00PM',1,150,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seats`
--

DROP TABLE IF EXISTS `seats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `seats` (
  `seat_id` int(11) NOT NULL,
  `seat_number` int(11) DEFAULT NULL,
  `cinema_id` int(11) DEFAULT NULL,
  `reservation_id` int(11) DEFAULT NULL,
  `timeslot_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`seat_id`),
  KEY `cinemaS_id_idx` (`cinema_id`),
  KEY `reservationS_id_idx` (`reservation_id`),
  KEY `timeslotS_id_idx` (`timeslot_id`),
  CONSTRAINT `cinemaS_id` FOREIGN KEY (`cinema_id`) REFERENCES `cinemas` (`cinema_id`),
  CONSTRAINT `reservationS_id` FOREIGN KEY (`reservation_id`) REFERENCES `reservations` (`reservation_id`),
  CONSTRAINT `timeslotS_id` FOREIGN KEY (`timeslot_id`) REFERENCES `timeslots` (`timeslot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seats`
--

LOCK TABLES `seats` WRITE;
/*!40000 ALTER TABLE `seats` DISABLE KEYS */;
INSERT INTO `seats` VALUES (1,1,1,1,1),(2,2,1,NULL,1),(3,3,1,NULL,1),(4,4,1,NULL,1),(5,5,1,NULL,1),(6,6,1,NULL,1),(7,7,1,NULL,1),(8,8,1,NULL,1),(9,9,1,NULL,1),(10,10,1,NULL,1),(11,11,1,NULL,1),(12,12,1,NULL,1),(13,13,1,NULL,1),(14,14,1,NULL,1),(15,15,1,NULL,1),(16,16,1,NULL,1),(17,17,1,NULL,1),(18,18,1,NULL,1),(19,19,1,NULL,1),(20,20,1,NULL,1),(21,21,1,NULL,1),(22,22,1,NULL,1),(23,23,1,NULL,1),(24,24,1,NULL,1),(25,25,1,NULL,1),(26,26,1,NULL,1),(27,27,1,NULL,1),(28,28,1,NULL,1),(29,29,1,NULL,1),(30,30,1,NULL,1),(31,31,1,NULL,1),(32,32,1,NULL,1),(33,33,1,NULL,1),(34,34,1,NULL,1),(35,35,1,NULL,1),(36,36,1,NULL,1),(37,37,1,NULL,1),(38,38,1,NULL,1),(39,39,1,NULL,1),(40,40,1,NULL,1),(41,1,2,NULL,4),(42,2,2,NULL,4),(43,3,2,NULL,4),(44,4,2,NULL,4),(45,5,2,NULL,4),(46,6,2,NULL,4),(47,7,2,NULL,4),(48,8,2,NULL,4),(49,9,2,NULL,4),(50,10,2,NULL,4),(51,11,2,NULL,4),(52,12,2,NULL,4),(53,13,2,NULL,4),(54,14,2,NULL,4),(55,15,2,NULL,4),(56,16,2,NULL,4),(57,17,2,NULL,4),(58,18,2,NULL,4),(59,19,2,NULL,4),(60,20,2,NULL,4),(61,21,2,NULL,4),(62,22,2,NULL,4),(63,23,2,NULL,4),(64,24,2,NULL,4),(65,25,2,NULL,4),(66,26,2,NULL,4),(67,27,2,NULL,4),(68,28,2,NULL,4),(69,29,2,NULL,4),(70,30,2,NULL,4),(71,31,2,NULL,4),(72,32,2,NULL,4),(73,33,2,NULL,4),(74,34,2,NULL,4),(75,35,2,NULL,4),(76,36,2,NULL,4),(77,37,2,NULL,4),(78,38,2,NULL,4),(79,39,2,NULL,4),(80,40,2,NULL,4),(81,1,3,NULL,NULL),(82,2,3,NULL,NULL),(83,3,3,NULL,NULL),(84,4,3,NULL,NULL),(85,5,3,NULL,NULL),(86,6,3,NULL,NULL),(87,7,3,NULL,NULL),(88,8,3,NULL,NULL),(89,9,3,NULL,NULL),(90,10,3,NULL,NULL),(91,11,3,NULL,NULL),(92,12,3,NULL,NULL),(93,13,3,NULL,NULL),(94,14,3,NULL,NULL),(95,15,3,NULL,NULL),(96,16,3,NULL,NULL),(97,17,3,NULL,NULL),(98,18,3,NULL,NULL),(99,19,3,NULL,NULL),(100,20,3,NULL,NULL),(101,21,3,NULL,NULL),(102,22,3,NULL,NULL),(103,23,3,NULL,NULL),(104,24,3,NULL,NULL),(105,25,3,NULL,NULL),(106,26,3,NULL,NULL),(107,27,3,NULL,NULL),(108,28,3,NULL,NULL),(109,29,3,NULL,NULL),(110,30,3,NULL,NULL),(111,31,3,NULL,NULL),(112,32,3,NULL,NULL),(113,33,3,NULL,NULL),(114,34,3,NULL,NULL),(115,35,3,NULL,NULL),(116,36,3,NULL,NULL),(117,37,3,NULL,NULL),(118,38,3,NULL,NULL),(119,39,3,NULL,NULL),(120,40,3,NULL,NULL),(121,1,4,NULL,NULL),(122,2,4,NULL,NULL),(123,3,4,NULL,NULL),(124,4,4,NULL,NULL),(125,5,4,NULL,NULL),(126,6,4,NULL,NULL),(127,7,4,NULL,NULL),(128,8,4,NULL,NULL),(129,9,4,NULL,NULL),(130,10,4,NULL,NULL),(131,11,4,NULL,NULL),(132,12,4,NULL,NULL),(133,13,4,NULL,NULL),(134,14,4,NULL,NULL),(135,15,4,NULL,NULL),(136,16,4,NULL,NULL),(137,17,4,NULL,NULL),(138,18,4,NULL,NULL),(139,19,4,NULL,NULL),(140,20,4,NULL,NULL),(141,21,4,NULL,NULL),(142,22,4,NULL,NULL),(143,23,4,NULL,NULL),(144,24,4,NULL,NULL),(145,25,4,NULL,NULL),(146,26,4,NULL,NULL),(147,27,4,NULL,NULL),(148,28,4,NULL,NULL),(149,29,4,NULL,NULL),(150,30,4,NULL,NULL),(151,31,4,NULL,NULL),(152,32,4,NULL,NULL),(153,33,4,NULL,NULL),(154,34,4,NULL,NULL),(155,35,4,NULL,NULL),(156,36,4,NULL,NULL),(157,37,4,NULL,NULL),(158,38,4,NULL,NULL),(159,39,4,NULL,NULL),(160,40,4,NULL,NULL);
/*!40000 ALTER TABLE `seats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timeslots`
--

DROP TABLE IF EXISTS `timeslots`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `timeslots` (
  `timeslot_id` int(11) NOT NULL,
  `time_start` varchar(20) DEFAULT NULL,
  `time_end` varchar(20) DEFAULT NULL,
  `movie_id` int(11) DEFAULT NULL,
  `cinema_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`timeslot_id`),
  KEY `movieT_id_idx` (`movie_id`),
  KEY `cinemaT_id_idx` (`cinema_id`),
  CONSTRAINT `cinemaT_id` FOREIGN KEY (`cinema_id`) REFERENCES `cinemas` (`cinema_id`),
  CONSTRAINT `movieT_id` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`movie_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timeslots`
--

LOCK TABLES `timeslots` WRITE;
/*!40000 ALTER TABLE `timeslots` DISABLE KEYS */;
INSERT INTO `timeslots` VALUES (1,'12:00PM',NULL,1,1),(2,'3:30PM',NULL,1,1),(3,'7:00PM',NULL,1,1),(4,'12:00PM',NULL,2,2),(5,'3:30PM',NULL,2,2),(6,'7:00PM',NULL,2,2),(7,'12:00PM',NULL,3,3),(8,'3:30PM',NULL,3,3),(9,'7:00PM',NULL,3,3),(10,'12:00PM',NULL,4,4),(11,'3:30PM',NULL,4,4),(12,'7:00PM',NULL,4,4);
/*!40000 ALTER TABLE `timeslots` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'moviereservation'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-01 14:57:32
