CREATE DATABASE  IF NOT EXISTS `cooltalkdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cooltalkdb`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: cooltalkdb
-- ------------------------------------------------------
-- Server version	8.4.2

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
-- Table structure for table `cooltalk_scrollwindow`
--

DROP TABLE IF EXISTS `cooltalk_scrollwindow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cooltalk_scrollwindow` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sc_id` varchar(45) NOT NULL COMMENT '滚动ID',
  `sc_date` date NOT NULL COMMENT '滚动日期',
  `sc_state` enum('-1','0','1') NOT NULL COMMENT '滚动状态',
  `create_time` time DEFAULT NULL COMMENT '创建时间',
  `del_time` time DEFAULT NULL COMMENT '删除时间',
  `publish_time` time DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sc_id_UNIQUE` (`sc_id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cooltalk_scrollwindow`
--

LOCK TABLES `cooltalk_scrollwindow` WRITE;
/*!40000 ALTER TABLE `cooltalk_scrollwindow` DISABLE KEYS */;
INSERT INTO `cooltalk_scrollwindow` VALUES (1,'1','2024-10-13','1','18:59:11',NULL,NULL);
/*!40000 ALTER TABLE `cooltalk_scrollwindow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cooltalk_scrollwindow_content`
--

DROP TABLE IF EXISTS `cooltalk_scrollwindow_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cooltalk_scrollwindow_content` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content_id` varchar(45) NOT NULL COMMENT '滚动内容ID',
  `sc_id` varchar(45) NOT NULL COMMENT '滚动ID',
  `content_text` varchar(45) NOT NULL COMMENT '滚动文字',
  `content_imageurl` varchar(45) NOT NULL COMMENT '滚动照片Url',
  `content_state` enum('-1','0','1') NOT NULL COMMENT '滚动状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `content_id_UNIQUE` (`content_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cooltalk_scrollwindow_content`
--

LOCK TABLES `cooltalk_scrollwindow_content` WRITE;
/*!40000 ALTER TABLE `cooltalk_scrollwindow_content` DISABLE KEYS */;
INSERT INTO `cooltalk_scrollwindow_content` VALUES (1,'1','1','过年了，来吃饺子','awdawd','1'),(2,'2','1','酷淘喜迎第一个春节！！','awdawd','1'),(3,'3','1','酷淘等你来','awdawd','1');
/*!40000 ALTER TABLE `cooltalk_scrollwindow_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cooltalk_user`
--

DROP TABLE IF EXISTS `cooltalk_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cooltalk_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account` varchar(255) NOT NULL COMMENT '璐︽埛鍚�',
  `password` varchar(255) NOT NULL COMMENT '瀵嗙爜',
  `gender` enum('male','female','unknown') NOT NULL DEFAULT 'unknown' COMMENT '鎬у埆',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_UNIQUE` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cooltalk_user`
--

LOCK TABLES `cooltalk_user` WRITE;
/*!40000 ALTER TABLE `cooltalk_user` DISABLE KEYS */;
INSERT INTO `cooltalk_user` VALUES (1,'123','123','unknown'),(2,'555','555','male'),(3,'666','666','female'),(5,'test','9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08','male'),(15,'2334557728','e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855','male');
/*!40000 ALTER TABLE `cooltalk_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-13 20:20:49
