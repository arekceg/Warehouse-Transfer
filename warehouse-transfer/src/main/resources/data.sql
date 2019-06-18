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


--
-- Table structure for table `TransferContent`
LOCK TABLES `TransferContent` WRITE;
/*!40000 ALTER TABLE `TransferContent` DISABLE KEYS */;
INSERT INTO `TransferContent` VALUES (2,50,1,NULL),(4,10,1,NULL),(5,10,2,NULL);
/*!40000 ALTER TABLE `TransferContent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;
LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1,'Item1'),(2,'Item2'),(3,'Item3'),(4,'Item_nowy');
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;
LOCK TABLES `stocks` WRITE;
/*!40000 ALTER TABLE `stocks` DISABLE KEYS */;
INSERT INTO `stocks` VALUES (1,550,'AVAILABLE',1,1),(2,50,'AVAILABLE',2,1),(3,300,'AVAILABLE',3,1),(4,190,'AVAILABLE',1,2),(5,20,'AVAILABLE',3,3),(6,65,'AVAILABLE',1,3),(7,-20,'RESERVED',2,1),(8,10,'AVAILABLE',2,2),(9,0,'RESERVED',1,1),(10,0,'RESERVED',1,2),(11,0,'RESERVED',2,2),(12,10,'AVAILABLE',2,3),(13,0,'RESERVED',1,3),(14,0,'RESERVED',3,3),(15,50,'AVAILABLE',3,4),(16,100,'AVAILABLE',4,4);
/*!40000 ALTER TABLE `stocks` ENABLE KEYS */;
UNLOCK TABLES;
LOCK TABLES `transfers` WRITE;
/*!40000 ALTER TABLE `transfers` DISABLE KEYS */;
INSERT INTO `transfers` VALUES (2,'2019-06-07',NULL,'2019-06-07',_binary '',_binary '\0',NULL,3,1),(4,'2019-06-07',NULL,'2019-06-07',_binary '',_binary '\0',NULL,3,2);
/*!40000 ALTER TABLE `transfers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40000 ALTER TABLE `transfers_transferContents` DISABLE KEYS */;
INSERT INTO `transfers_transferContents` VALUES (2,2),(4,4),(4,5);
/*!40000 ALTER TABLE `transfers_transferContents` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(2,2),(3,2),(4,2),(5,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,1,'admin','$2a$10$M/uYzo3dN45rp8LxVKfMcem4YBLAKGFlTXfipW.9NoARBo9Bupzlm','admin',1),(2,1,'aa-name','$2a$10$THTlXCX27GNC8212UD8HBeSvYB7t5/cnAs3KtBZv8ASvR.3qOtEA2','aa',2),(3,1,'bb','$2a$10$s9Nr2tdz9TvpDmIROT0yQ./530NGyrAoIAsRtp8UVOQspykMsJfQS','bb',3),(4,1,'cc','$2a$10$/NiJNkpEuVzsBtRMmRFifef529/ZDs35nlZJqJ.rGAWRfNQvo/KYi','cc',NULL),(5,1,'ff','$2a$10$ZR7FkGkC9sQJT/Mjg5yFh.o4FPhbBpG2uodIJ1.4byTK0g4sEi1/a','ff',4);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
LOCK TABLES `warehouses` WRITE;
/*!40000 ALTER TABLE `warehouses` DISABLE KEYS */;
INSERT INTO `warehouses` VALUES (1,'admin-warehouse',1),(2,'aa-warehouse',2),(3,'bb_warehouse',3),(4,'ff_warehouse',5);
/*!40000 ALTER TABLE `warehouses` ENABLE KEYS */;
UNLOCK TABLES;
