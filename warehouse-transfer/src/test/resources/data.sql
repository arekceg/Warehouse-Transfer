-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: 35.228.103.224    Database: warehouse_management
-- ------------------------------------------------------
-- Server version	5.7.14-google-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED='466f1ddc-8076-11e9-ace2-42010aa6000e:1-1564788';

--
-- Table structure for table `TransferContent`
--

DROP TABLE IF EXISTS `TransferContent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TransferContent` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` int(11) NOT NULL,
  `item_id` bigint(20) NOT NULL,
  `transfer_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKal9ayu8pkmgjddhq2h5obihth` (`item_id`),
  KEY `FK4uf2hb53pnj4ffad4h4pdj70f` (`transfer_id`),
  CONSTRAINT `FK4uf2hb53pnj4ffad4h4pdj70f` FOREIGN KEY (`transfer_id`) REFERENCES `transfers` (`id`),
  CONSTRAINT `FKal9ayu8pkmgjddhq2h5obihth` FOREIGN KEY (`item_id`) REFERENCES `items` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TransferContent`
--

LOCK TABLES `TransferContent` WRITE;
/*!40000 ALTER TABLE `TransferContent` DISABLE KEYS */;
INSERT INTO `TransferContent` VALUES (2,50,1,NULL),(4,10,1,NULL),(5,10,2,NULL);
/*!40000 ALTER TABLE `TransferContent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authorities` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `items` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1,'Item1'),(2,'Item2'),(3,'Item3'),(4,'Item_nowy');
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stocks`
--

DROP TABLE IF EXISTS `stocks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stocks` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `itemStock` int(11) NOT NULL,
  `stockType` varchar(255) NOT NULL,
  `item_id` bigint(20) NOT NULL,
  `warehouse_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKko9qvmybxt0opqoibsqrmivh8` (`item_id`),
  KEY `FKjftt43i266337pt7y8b291hpx` (`warehouse_id`),
  CONSTRAINT `FKjftt43i266337pt7y8b291hpx` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouses` (`id`),
  CONSTRAINT `FKko9qvmybxt0opqoibsqrmivh8` FOREIGN KEY (`item_id`) REFERENCES `items` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stocks`
--

LOCK TABLES `stocks` WRITE;
/*!40000 ALTER TABLE `stocks` DISABLE KEYS */;
INSERT INTO `stocks` VALUES (1,550,'AVAILABLE',1,1),(2,50,'AVAILABLE',2,1),(3,300,'AVAILABLE',3,1),(4,190,'AVAILABLE',1,2),(5,20,'AVAILABLE',3,3),(6,65,'AVAILABLE',1,3),(7,-20,'RESERVED',2,1),(8,10,'AVAILABLE',2,2),(9,0,'RESERVED',1,1),(10,0,'RESERVED',1,2),(11,0,'RESERVED',2,2),(12,10,'AVAILABLE',2,3),(13,0,'RESERVED',1,3),(14,0,'RESERVED',3,3),(15,50,'AVAILABLE',3,4),(16,100,'AVAILABLE',4,4);
/*!40000 ALTER TABLE `stocks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transfers`
--

DROP TABLE IF EXISTS `transfers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transfers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `acceptedDate` date DEFAULT NULL,
  `challengedDate` date DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  `isAccepted` bit(1) NOT NULL,
  `isChallenged` bit(1) NOT NULL,
  `updatedDate` date DEFAULT NULL,
  `destinationWarehouse_id` bigint(20) NOT NULL,
  `sourceWarehouse_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb2p8pq2fnyft6mhjf54uenbyt` (`destinationWarehouse_id`),
  KEY `FK73whp1ysj4shp6f6nc7fos51y` (`sourceWarehouse_id`),
  CONSTRAINT `FK73whp1ysj4shp6f6nc7fos51y` FOREIGN KEY (`sourceWarehouse_id`) REFERENCES `warehouses` (`id`),
  CONSTRAINT `FKb2p8pq2fnyft6mhjf54uenbyt` FOREIGN KEY (`destinationWarehouse_id`) REFERENCES `warehouses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transfers`
--

LOCK TABLES `transfers` WRITE;
/*!40000 ALTER TABLE `transfers` DISABLE KEYS */;
INSERT INTO `transfers` VALUES (2,'2019-06-07',NULL,'2019-06-07',_binary '',_binary '\0',NULL,3,1),(4,'2019-06-07',NULL,'2019-06-07',_binary '',_binary '\0',NULL,3,2);
/*!40000 ALTER TABLE `transfers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transfers_transferContents`
--

DROP TABLE IF EXISTS `transfers_transferContents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transfers_transferContents` (
  `Transfer_id` bigint(20) NOT NULL,
  `transferContents_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_p06kudcbqsu5sq1chyg4hs1od` (`transferContents_id`),
  KEY `FKhnugeh6rak3fu658fuyg5pta5` (`Transfer_id`),
  CONSTRAINT `FKfdyx1yif7mihh0gp88kx51wrp` FOREIGN KEY (`transferContents_id`) REFERENCES `TransferContent` (`id`),
  CONSTRAINT `FKhnugeh6rak3fu658fuyg5pta5` FOREIGN KEY (`Transfer_id`) REFERENCES `transfers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transfers_transferContents`
--

LOCK TABLES `transfers_transferContents` WRITE;
/*!40000 ALTER TABLE `transfers_transferContents` DISABLE KEYS */;
INSERT INTO `transfers_transferContents` VALUES (2,2),(4,4),(4,5);
/*!40000 ALTER TABLE `transfers_transferContents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK95oa6ae8l75auh3uwggwirr32` (`role_id`),
  CONSTRAINT `FK95oa6ae8l75auh3uwggwirr32` FOREIGN KEY (`role_id`) REFERENCES `authorities` (`role_id`),
  CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(2,2),(3,2),(4,2),(5,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enabled` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `warehouse_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`),
  KEY `FKpfeijlxk6hivbyuvedan09sqk` (`warehouse_id`),
  CONSTRAINT `FKpfeijlxk6hivbyuvedan09sqk` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,1,'admin','$2a$10$M/uYzo3dN45rp8LxVKfMcem4YBLAKGFlTXfipW.9NoARBo9Bupzlm','admin',1),(2,1,'aa-name','$2a$10$THTlXCX27GNC8212UD8HBeSvYB7t5/cnAs3KtBZv8ASvR.3qOtEA2','aa',2),(3,1,'bb','$2a$10$s9Nr2tdz9TvpDmIROT0yQ./530NGyrAoIAsRtp8UVOQspykMsJfQS','bb',3),(4,1,'cc','$2a$10$/NiJNkpEuVzsBtRMmRFifef529/ZDs35nlZJqJ.rGAWRfNQvo/KYi','cc',NULL),(5,1,'ff','$2a$10$ZR7FkGkC9sQJT/Mjg5yFh.o4FPhbBpG2uodIJ1.4byTK0g4sEi1/a','ff',4);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehouses`
--

DROP TABLE IF EXISTS `warehouses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warehouses` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `manager_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg7kbfcmunhbgv2ssj5ruj93v4` (`manager_id`),
  CONSTRAINT `FKg7kbfcmunhbgv2ssj5ruj93v4` FOREIGN KEY (`manager_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouses`
--

LOCK TABLES `warehouses` WRITE;
/*!40000 ALTER TABLE `warehouses` DISABLE KEYS */;
INSERT INTO `warehouses` VALUES (1,'admin-warehouse',1),(2,'aa-warehouse',2),(3,'bb_warehouse',3),(4,'ff_warehouse',5);
/*!40000 ALTER TABLE `warehouses` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-14 19:28:04
