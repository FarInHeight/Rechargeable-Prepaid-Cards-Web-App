CREATE DATABASE  IF NOT EXISTS `web-cards-db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `web-cards-db`;
-- MySQL dump 10.13  Distrib 8.0.29, for macos12 (x86_64)
--
-- Host: localhost    Database: web-cards-db
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `cards`
--

DROP TABLE IF EXISTS `cards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cards` (
  `id` int NOT NULL AUTO_INCREMENT,
  `blocked` bit(1) NOT NULL,
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `credit` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cards`
--

LOCK TABLES `cards` WRITE;
/*!40000 ALTER TABLE `cards` DISABLE KEYS */;
INSERT INTO `cards` VALUES (1,_binary '','2023-06-29 14:18:56',90),(2,_binary '\0','2023-06-29 14:18:56',40),(3,_binary '','2023-06-29 14:18:56',10),(4,_binary '\0','2023-06-30 05:31:43',50),(5,_binary '','2023-06-30 05:33:49',70),(6,_binary '\0','2023-06-30 05:34:08',100),(7,_binary '\0','2023-06-30 05:35:45',70),(8,_binary '','2023-06-30 05:36:16',40),(9,_binary '\0','2023-06-30 05:37:01',75),(10,_binary '','2023-06-30 13:53:36',61),(11,_binary '\0','2023-06-30 13:53:54',35),(12,_binary '\0','2023-07-01 12:42:34',30),(13,_binary '','2023-07-01 13:02:12',98),(14,_binary '\0','2023-07-01 13:03:10',29.02),(15,_binary '\0','2023-07-01 13:03:19',10),(16,_binary '','2023-07-03 12:04:23',30),(17,_binary '\0','2023-07-03 12:04:36',25);
/*!40000 ALTER TABLE `cards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ofx66keruapi6vyqpv6f2or37` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'admin'),(2,'merchant');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transactions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `credit` float NOT NULL,
  `description` varchar(255) NOT NULL,
  `card_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjxdscq0bxpy0pl465vvsqc89j` (`card_id`),
  KEY `FKqwv7rmvc8va8rep7piikrojds` (`user_id`),
  CONSTRAINT `FKjxdscq0bxpy0pl465vvsqc89j` FOREIGN KEY (`card_id`) REFERENCES `cards` (`id`),
  CONSTRAINT `FKqwv7rmvc8va8rep7piikrojds` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (1,'2023-07-03 11:35:19',-35,'Concert ticket',11,3),(2,'2023-07-03 11:36:49',20,'Recharge',7,3),(3,'2023-07-03 11:38:27',-15,'Football match ticket',2,5),(4,'2023-07-03 11:39:32',-10,'Balloon pump',12,3),(5,'2023-07-03 11:42:01',-70,'Headphones',9,5),(6,'2023-07-03 11:43:25',-70,'Earphones',14,5),(7,'2023-07-03 11:44:09',30,'Recharge',4,5),(8,'2023-07-03 11:49:59',-81.99,'Projector',14,4),(9,'2023-07-03 11:50:26',11.01,'Recharge',14,4),(10,'2023-07-03 11:52:43',-45,'Electric Shaver',6,4),(11,'2023-07-03 11:56:11',-59,'Joystick',10,6),(12,'2023-07-03 11:57:12',-30,'Hair dryer',15,6),(13,'2023-07-03 11:57:53',-5,'Iphone cover',2,6),(14,'2023-07-03 12:01:26',-110,'Smartphone',15,5),(15,'2023-07-03 12:02:15',10,'Recharge',4,5),(16,'2023-07-03 12:19:30',30,'Recharge',2,7);
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `blocked` bit(1) NOT NULL,
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `email` varchar(320) NOT NULL,
  `password` varchar(64) NOT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,_binary '\0','2023-06-28 07:56:12','user1@something.com','$2a$10$9u9R.r8npECjDoq7E11azed8B1/VkzStgxDGjBaJNdNoZ5LZ86ClK','user1'),(2,_binary '\0','2023-06-28 07:58:39','user2@something.com','$2a$10$NDQVlbkZ3OGZL8XcZpg02ubrbEKg6paHDwTONPnMcVYzlIoQzq/6q','user2'),(3,_binary '\0','2023-06-28 07:59:17','user3@something.com','$2a$10$c7cfVHpzTUbEM9O5sBqrt.u7xd9Rz.6f5/iGcESH7ksPEDmM3UDDG','user3'),(4,_binary '\0','2023-06-28 08:00:12','user4@something.com','$2a$10$Zwk7o2PGVVkRA2nwLCq89uRr/H5KL8U0LdluQynqGJ8XT99MElDIO','user4'),(5,_binary '\0','2023-07-01 11:19:10','user5@something.com','$2a$10$KUEbIHQlS6Cbv2/F8f2wUu3HxxRRwCcLa6H/6DA4c0uPud6ojeI.W','user5'),(6,_binary '','2023-07-01 11:22:30','user6@something.com','$2a$10$Djhp/6K0lDzxi1Yn49jpBeLiqdh7f/9c9K9e5mzREXm0LRBFqmxJC','user6'),(7,_binary '\0','2023-07-03 12:17:51','user7@something.com','$2a$10$zmiR.Y5VLb/80JPLDYNTLuEeaepAYJbeYnoQ4aKib3r4xlCNfLQcO','user7');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`),
  CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,1),(2,1),(3,2),(4,2),(5,2),(6,2),(7,2);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-08  9:53:47
