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
INSERT INTO `seats` VALUES (1,1,1,1000,1),(2,2,1,1001,1),(3,3,1,NULL,1),(4,4,1,NULL,1),(5,5,1,NULL,1),(6,6,1,NULL,1),(7,7,1,NULL,1),(8,8,1,NULL,1),(9,9,1,NULL,1),(10,10,1,NULL,1),(11,11,1,NULL,1),(12,12,1,NULL,1),(13,13,1,NULL,1),(14,14,1,NULL,1),(15,15,1,NULL,1),(16,16,1,NULL,1),(17,17,1,NULL,1),(18,18,1,NULL,1),(19,19,1,NULL,1),(20,20,1,NULL,1),(21,21,1,NULL,1),(22,22,1,NULL,1),(23,23,1,NULL,1),(24,24,1,NULL,1),(25,25,1,NULL,1),(26,26,1,NULL,1),(27,27,1,NULL,1),(28,28,1,NULL,1),(29,29,1,NULL,1),(30,30,1,NULL,1),(31,31,1,NULL,1),(32,32,1,NULL,1),(33,33,1,NULL,1),(34,34,1,NULL,1),(35,35,1,NULL,1),(36,36,1,NULL,1),(37,37,1,NULL,1),(38,38,1,NULL,1),(39,39,1,NULL,1),(40,40,1,NULL,1),(41,1,1,NULL,2),(42,2,1,NULL,2),(43,3,1,NULL,2),(44,4,1,NULL,2),(45,5,1,NULL,2),(46,6,1,NULL,2),(47,7,1,NULL,2),(48,8,1,NULL,2),(49,9,1,NULL,2),(50,10,1,NULL,2),(51,11,1,NULL,2),(52,12,1,NULL,2),(53,13,1,NULL,2),(54,14,1,NULL,2),(55,15,1,NULL,2),(56,16,1,NULL,2),(57,17,1,NULL,2),(58,18,1,NULL,2),(59,19,1,NULL,2),(60,20,1,NULL,2),(61,21,1,NULL,2),(62,22,1,NULL,2),(63,23,1,NULL,2),(64,24,1,NULL,2),(65,25,1,NULL,2),(66,26,1,NULL,2),(67,27,1,NULL,2),(68,28,1,NULL,2),(69,29,1,NULL,2),(70,30,1,NULL,2),(71,31,1,NULL,2),(72,32,1,NULL,2),(73,33,1,NULL,2),(74,34,1,NULL,2),(75,35,1,NULL,2),(76,36,1,NULL,2),(77,37,1,NULL,2),(78,38,1,NULL,2),(79,39,1,NULL,2),(80,40,1,NULL,2),(81,1,1,NULL,3),(82,2,1,NULL,3),(83,3,1,NULL,3),(84,4,1,NULL,3),(85,5,1,NULL,3),(86,6,1,NULL,3),(87,7,1,NULL,3),(88,8,1,NULL,3),(89,9,1,NULL,3),(90,10,1,NULL,3),(91,11,1,NULL,3),(92,12,1,NULL,3),(93,13,1,NULL,3),(94,14,1,NULL,3),(95,15,1,NULL,3),(96,16,1,NULL,3),(97,17,1,NULL,3),(98,18,1,NULL,3),(99,19,1,NULL,3),(100,20,1,NULL,3),(101,21,1,NULL,3),(102,22,1,NULL,3),(103,23,1,NULL,3),(104,24,1,NULL,3),(105,25,1,NULL,3),(106,26,1,NULL,3),(107,27,1,NULL,3),(108,28,1,NULL,3),(109,29,1,NULL,3),(110,30,1,NULL,3),(111,31,1,NULL,3),(112,32,1,NULL,3),(113,33,1,NULL,3),(114,34,1,NULL,3),(115,35,1,NULL,3),(116,36,1,NULL,3),(117,37,1,NULL,3),(118,38,1,NULL,3),(119,39,1,NULL,3),(120,40,1,NULL,3),(121,1,2,NULL,4),(122,2,2,NULL,4),(123,3,2,NULL,4),(124,4,2,NULL,4),(125,5,2,NULL,4),(126,6,2,NULL,4),(127,7,2,NULL,4),(128,8,2,NULL,4),(129,9,2,NULL,4),(130,10,2,NULL,4),(131,11,2,NULL,4),(132,12,2,NULL,4),(133,13,2,NULL,4),(134,14,2,NULL,4),(135,15,2,NULL,4),(136,16,2,NULL,4),(137,17,2,NULL,4),(138,18,2,NULL,4),(139,19,2,NULL,4),(140,20,2,NULL,4),(141,21,2,NULL,4),(142,22,2,NULL,4),(143,23,2,NULL,4),(144,24,2,NULL,4),(145,25,2,NULL,4),(146,26,2,NULL,4),(147,27,2,NULL,4),(148,28,2,NULL,4),(149,29,2,NULL,4),(150,30,2,NULL,4),(151,31,2,NULL,4),(152,32,2,NULL,4),(153,33,2,NULL,4),(154,34,2,NULL,4),(155,35,2,NULL,4),(156,36,2,NULL,4),(157,37,2,NULL,4),(158,38,2,NULL,4),(159,39,2,NULL,4),(160,40,2,NULL,4),(161,1,2,NULL,5),(162,2,2,NULL,5),(163,3,2,NULL,5),(164,4,2,NULL,5),(165,5,2,NULL,5),(166,6,2,NULL,5),(167,7,2,NULL,5),(168,8,2,NULL,5),(169,9,2,NULL,5),(170,10,2,NULL,5),(171,11,2,NULL,5),(172,12,2,NULL,5),(173,13,2,NULL,5),(174,14,2,NULL,5),(175,15,2,NULL,5),(176,16,2,NULL,5),(177,17,2,NULL,5),(178,18,2,NULL,5),(179,19,2,NULL,5),(180,20,2,NULL,5),(181,21,2,NULL,5),(182,22,2,NULL,5),(183,23,2,NULL,5),(184,24,2,NULL,5),(185,25,2,NULL,5),(186,26,2,NULL,5),(187,27,2,NULL,5),(188,28,2,NULL,5),(189,29,2,NULL,5),(190,30,2,NULL,5),(191,31,2,NULL,5),(192,32,2,NULL,5),(193,33,2,NULL,5),(194,34,2,NULL,5),(195,35,2,NULL,5),(196,36,2,NULL,5),(197,37,2,NULL,5),(198,38,2,NULL,5),(199,39,2,NULL,5),(200,40,2,NULL,5),(201,1,2,NULL,6),(202,2,2,NULL,6),(203,3,2,NULL,6),(204,4,2,NULL,6),(205,5,2,NULL,6),(206,6,2,NULL,6),(207,7,2,NULL,6),(208,8,2,NULL,6),(209,9,2,NULL,6),(210,10,2,NULL,6),(211,11,2,NULL,6),(212,12,2,NULL,6),(213,13,2,NULL,6),(214,14,2,NULL,6),(215,15,2,NULL,6),(216,16,2,NULL,6),(217,17,2,NULL,6),(218,18,2,NULL,6),(219,19,2,NULL,6),(220,20,2,NULL,6),(221,21,2,NULL,6),(222,22,2,NULL,6),(223,23,2,NULL,6),(224,24,2,NULL,6),(225,25,2,NULL,6),(226,26,2,NULL,6),(227,27,2,NULL,6),(228,28,2,NULL,6),(229,29,2,NULL,6),(230,30,2,NULL,6),(231,31,2,NULL,6),(232,32,2,NULL,6),(233,33,2,NULL,6),(234,34,2,NULL,6),(235,35,2,NULL,6),(236,36,2,NULL,6),(237,37,2,NULL,6),(238,38,2,NULL,6),(239,39,2,NULL,6),(240,40,2,NULL,6),(241,1,3,NULL,7),(242,2,3,NULL,7),(243,3,3,NULL,7),(244,4,3,NULL,7),(245,5,3,NULL,7),(246,6,3,NULL,7),(247,7,3,NULL,7),(248,8,3,NULL,7),(249,9,3,NULL,7),(250,10,3,NULL,7),(251,11,3,NULL,7),(252,12,3,NULL,7),(253,13,3,NULL,7),(254,14,3,NULL,7),(255,15,3,NULL,7),(256,16,3,NULL,7),(257,17,3,NULL,7),(258,18,3,NULL,7),(259,19,3,NULL,7),(260,20,3,NULL,7),(261,21,3,NULL,7),(262,22,3,NULL,7),(263,23,3,NULL,7),(264,24,3,NULL,7),(265,25,3,NULL,7),(266,26,3,NULL,7),(267,27,3,NULL,7),(268,28,3,NULL,7),(269,29,3,NULL,7),(270,30,3,NULL,7),(271,31,3,NULL,7),(272,32,3,NULL,7),(273,33,3,NULL,7),(274,34,3,NULL,7),(275,35,3,NULL,7),(276,36,3,NULL,7),(277,37,3,NULL,7),(278,38,3,NULL,7),(279,39,3,NULL,7),(280,40,3,NULL,7),(281,1,3,NULL,8),(282,2,3,NULL,8),(283,3,3,NULL,8),(284,4,3,NULL,8),(285,5,3,NULL,8),(286,6,3,NULL,8),(287,7,3,NULL,8),(288,8,3,NULL,8),(289,9,3,NULL,8),(290,10,3,NULL,8),(291,11,3,NULL,8),(292,12,3,NULL,8),(293,13,3,NULL,8),(294,14,3,NULL,8),(295,15,3,NULL,8),(296,16,3,NULL,8),(297,17,3,NULL,8),(298,18,3,NULL,8),(299,19,3,NULL,8),(300,20,3,NULL,8),(301,21,3,NULL,8),(302,22,3,NULL,8),(303,23,3,NULL,8),(304,24,3,NULL,8),(305,25,3,NULL,8),(306,26,3,NULL,8),(307,27,3,NULL,8),(308,28,3,NULL,8),(309,29,3,NULL,8),(310,30,3,NULL,8),(311,31,3,NULL,8),(312,32,3,NULL,8),(313,33,3,NULL,8),(314,34,3,NULL,8),(315,35,3,NULL,8),(316,36,3,NULL,8),(317,37,3,NULL,8),(318,38,3,NULL,8),(319,39,3,NULL,8),(320,40,3,NULL,8),(321,1,3,NULL,9),(322,2,3,NULL,9),(323,3,3,NULL,9),(324,4,3,NULL,9),(325,5,3,NULL,9),(326,6,3,NULL,9),(327,7,3,NULL,9),(328,8,3,NULL,9),(329,9,3,NULL,9),(330,10,3,NULL,9),(331,11,3,NULL,9),(332,12,3,NULL,9),(333,13,3,NULL,9),(334,14,3,NULL,9),(335,15,3,NULL,9),(336,16,3,NULL,9),(337,17,3,NULL,9),(338,18,3,NULL,9),(339,19,3,NULL,9),(340,20,3,NULL,9),(341,21,3,NULL,9),(342,22,3,NULL,9),(343,23,3,NULL,9),(344,24,3,NULL,9),(345,25,3,NULL,9),(346,26,3,NULL,9),(347,27,3,NULL,9),(348,28,3,NULL,9),(349,29,3,NULL,9),(350,30,3,NULL,9),(351,31,3,NULL,9),(352,32,3,NULL,9),(353,33,3,NULL,9),(354,34,3,NULL,9),(355,35,3,NULL,9),(356,36,3,NULL,9),(357,37,3,NULL,9),(358,38,3,NULL,9),(359,39,3,NULL,9),(360,40,3,NULL,9),(361,1,4,NULL,10),(362,2,4,NULL,10),(363,3,4,NULL,10),(364,4,4,NULL,10),(365,5,4,NULL,10),(366,6,4,NULL,10),(367,7,4,NULL,10),(368,8,4,NULL,10),(369,9,4,NULL,10),(370,10,4,NULL,10),(371,11,4,NULL,10),(372,12,4,NULL,10),(373,13,4,NULL,10),(374,14,4,NULL,10),(375,15,4,NULL,10),(376,16,4,NULL,10),(377,17,4,NULL,10),(378,18,4,NULL,10),(379,19,4,NULL,10),(380,20,4,NULL,10),(381,21,4,NULL,10),(382,22,4,NULL,10),(383,23,4,NULL,10),(384,24,4,NULL,10),(385,25,4,NULL,10),(386,26,4,NULL,10),(387,27,4,NULL,10),(388,28,4,NULL,10),(389,29,4,NULL,10),(390,30,4,NULL,10),(391,31,4,NULL,10),(392,32,4,NULL,10),(393,33,4,NULL,10),(394,34,4,NULL,10),(395,35,4,NULL,10),(396,36,4,NULL,10),(397,37,4,NULL,10),(398,38,4,NULL,10),(399,39,4,NULL,10),(400,40,4,NULL,10),(401,1,4,NULL,11),(402,2,4,NULL,11),(403,3,4,NULL,11),(404,4,4,NULL,11),(405,5,4,NULL,11),(406,6,4,NULL,11),(407,7,4,NULL,11),(408,8,4,NULL,11),(409,9,4,NULL,11),(410,10,4,NULL,11),(411,11,4,NULL,11),(412,12,4,NULL,11),(413,13,4,NULL,11),(414,14,4,NULL,11),(415,15,4,NULL,11),(416,16,4,NULL,11),(417,17,4,NULL,11),(418,18,4,NULL,11),(419,19,4,NULL,11),(420,20,4,NULL,11),(421,21,4,NULL,11),(422,22,4,NULL,11),(423,23,4,NULL,11),(424,24,4,NULL,11),(425,25,4,NULL,11),(426,26,4,NULL,11),(427,27,4,NULL,11),(428,28,4,NULL,11),(429,29,4,NULL,11),(430,30,4,NULL,11),(431,31,4,NULL,11),(432,32,4,NULL,11),(433,33,4,NULL,11),(434,34,4,NULL,11),(435,35,4,NULL,11),(436,36,4,NULL,11),(437,37,4,NULL,11),(438,38,4,NULL,11),(439,39,4,NULL,11),(440,40,4,NULL,11),(441,1,4,NULL,12),(442,2,4,NULL,12),(443,3,4,NULL,12),(444,4,4,NULL,12),(445,5,4,NULL,12),(446,6,4,NULL,12),(447,7,4,NULL,12),(448,8,4,NULL,12),(449,9,4,NULL,12),(450,10,4,NULL,12),(451,11,4,NULL,12),(452,12,4,NULL,12),(453,13,4,NULL,12),(454,14,4,NULL,12),(455,15,4,NULL,12),(456,16,4,NULL,12),(457,17,4,NULL,12),(458,18,4,NULL,12),(459,19,4,NULL,12),(460,20,4,NULL,12),(461,21,4,NULL,12),(462,22,4,NULL,12),(463,23,4,NULL,12),(464,24,4,NULL,12),(465,25,4,NULL,12),(466,26,4,NULL,12),(467,27,4,NULL,12),(468,28,4,NULL,12),(469,29,4,NULL,12),(470,30,4,NULL,12),(471,31,4,NULL,12),(472,32,4,NULL,12),(473,33,4,NULL,12),(474,34,4,NULL,12),(475,35,4,NULL,12),(476,36,4,NULL,12),(477,37,4,NULL,12),(478,38,4,NULL,12),(479,39,4,NULL,12),(480,40,4,NULL,12);
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

-- Dump completed on 2020-04-04 22:35:56
