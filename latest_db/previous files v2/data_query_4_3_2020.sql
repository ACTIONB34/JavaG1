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
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `reservation_id` int(11) NOT NULL AUTO_INCREMENT,
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `cinema_id` int(11) DEFAULT NULL,
  `time` varchar(20) DEFAULT NULL,
  `customer_name` varchar(20) DEFAULT NULL,
  `total_payment` int(11) DEFAULT NULL,
  `no_of_kid` int(11) DEFAULT NULL,
  `no_of_adult` int(11) DEFAULT NULL,
  `no_of_senior` int(11) DEFAULT NULL,
  `timeslot_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`reservation_id`),
  KEY `cinemaR_id_idx` (`cinema_id`),
  KEY `timeslotR_id_idx` (`timeslot_id`),
  KEY `customerR_id_idx` (`customer_name`),
  CONSTRAINT `cinemaR_id` FOREIGN KEY (`cinema_id`) REFERENCES `cinemas` (`cinema_id`),
  CONSTRAINT `timeslotR_id` FOREIGN KEY (`timeslot_id`) REFERENCES `timeslots` (`timeslot_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
INSERT INTO `reservations` VALUES (1000,'2020-04-02 15:50:01',1,'12:00PM','Johnny Test',150,0,1,0,1),(1001,'2020-04-02 15:50:21',1,'12:00PM','Channie Test',150,0,1,0,1);
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
  KEY `timeslotS_id_idx` (`timeslot_id`),
  KEY `reservationS_id_idx` (`reservation_id`),
  CONSTRAINT `cinemaS_id` FOREIGN KEY (`cinema_id`) REFERENCES `cinemas` (`cinema_id`),
  CONSTRAINT `reservatioS_id` FOREIGN KEY (`reservation_id`) REFERENCES `reservations` (`reservation_id`),
  CONSTRAINT `timeslotS_id` FOREIGN KEY (`timeslot_id`) REFERENCES `timeslots` (`timeslot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seats`
--

LOCK TABLES `seats` WRITE;
/*!40000 ALTER TABLE `seats` DISABLE KEYS */;
INSERT INTO `seats` VALUES (1,1,1,1000,1),(2,2,1,1001,1),(3,3,1,NULL,1),(4,4,1,NULL,1),(5,5,1,NULL,1),(6,6,1,NULL,1),(7,7,1,NULL,1),(8,8,1,NULL,1),(9,9,1,NULL,1),(10,10,1,NULL,1),(11,1,2,NULL,4),(12,2,2,NULL,4),(13,3,2,NULL,4),(14,4,2,NULL,4),(15,5,2,NULL,4),(16,6,2,NULL,4),(17,7,2,NULL,4),(18,8,2,NULL,4),(19,9,2,NULL,4),(20,10,2,NULL,4),(21,1,3,NULL,7),(22,2,3,NULL,7),(23,3,3,NULL,7),(24,4,3,NULL,7),(25,5,3,NULL,7),(26,6,3,NULL,7),(27,7,3,NULL,7),(28,8,3,NULL,7),(29,9,3,NULL,7),(30,10,3,NULL,7),(31,1,4,NULL,10),(32,2,4,NULL,10),(33,3,4,NULL,10),(34,4,4,NULL,10),(35,5,4,NULL,10),(36,6,4,NULL,10),(37,7,4,NULL,10),(38,8,4,NULL,10),(39,9,4,NULL,10),(40,10,4,NULL,10);
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
INSERT INTO `timeslots` VALUES (1,'12:00PM',1,1),(2,'3:30PM',1,1),(3,'7:00PM',1,1),(4,'12:00PM',2,2),(5,'3:30PM',2,2),(6,'7:00PM',2,2),(7,'12:00PM',3,3),(8,'3:30PM',3,3),(9,'7:00PM',3,3),(10,'12:00PM',4,4),(11,'3:30PM',4,4),(12,'7:00PM',4,4);
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

-- Dump completed on 2020-04-02 23:57:33
