-- MySQL dump 10.14  Distrib 5.5.52-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: android_sms
-- ------------------------------------------------------
-- Server version	5.5.52-MariaDB

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
-- Table structure for table `feedback_customers`
--

DROP TABLE IF EXISTS `feedback_customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback_customers` (
  `ticker_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) DEFAULT NULL,
  `pincode` int(6) DEFAULT NULL,
  `feedback_image` blob,
  `status` int(11) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `closed_at` date DEFAULT NULL,
  PRIMARY KEY (`ticker_id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback_customers`
--

LOCK TABLES `feedback_customers` WRITE;
/*!40000 ALTER TABLE `feedback_customers` DISABLE KEYS */;
INSERT INTO `feedback_customers` VALUES (75,86,556633,'http://poolana9027.cloudapp.net/android_sms/Img_Upload/1.png',1,'2017-07-30','2017-07-30'),(76,89,666666,'http://poolana9027.cloudapp.net/android_sms/Img_Upload/76.png',0,'2017-07-30',NULL),(77,91,557565,'http://poolana9027.cloudapp.net/android_sms/Img_Upload/77.png',0,'2017-07-30',NULL),(78,86,556633,'http://poolana9027.cloudapp.net/android_sms/Img_Upload/78.png',1,'2017-07-30','2017-07-30');
/*!40000 ALTER TABLE `feedback_customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sms_codes`
--

DROP TABLE IF EXISTS `sms_codes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sms_codes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `code` varchar(6) NOT NULL,
  `status` int(1) NOT NULL DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `account_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `sms_codes_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sms_codes`
--

LOCK TABLES `sms_codes` WRITE;
/*!40000 ALTER TABLE `sms_codes` DISABLE KEYS */;
INSERT INTO `sms_codes` VALUES (42,88,'729150',1,'2017-07-30 05:50:15',2),(44,89,'581807',1,'2017-07-30 05:53:19',2),(45,90,'220587',1,'2017-07-30 05:54:40',1),(47,92,'570045',0,'2017-07-30 10:59:33',2),(48,94,'348949',1,'2017-07-30 11:00:15',2),(59,93,'287934',0,'2017-07-30 14:17:57',2),(63,91,'488026',1,'2017-07-30 14:22:46',2),(64,95,'961878',1,'2017-07-30 14:25:57',1),(68,87,'423754',1,'2017-07-31 04:55:56',1),(70,96,'324372',1,'2017-07-31 05:00:01',2),(72,86,'302170',0,'2017-07-31 05:06:26',2);
/*!40000 ALTER TABLE `sms_codes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `survey_data`
--

DROP TABLE IF EXISTS `survey_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `survey_data` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Aadhar_no` varchar(100) DEFAULT NULL,
  `Voter_no` varchar(100) DEFAULT NULL,
  `Booth_no` varchar(100) DEFAULT NULL,
  `Assembly_name` varchar(100) DEFAULT NULL,
  `Constituency_name` varchar(100) DEFAULT NULL,
  `Voter_name` varchar(100) DEFAULT NULL,
  `Voter_father_name` varchar(100) DEFAULT NULL,
  `Voter_mobile` varchar(100) DEFAULT NULL,
  `Voter_contact` varchar(100) DEFAULT NULL,
  `Voter_email` varchar(100) DEFAULT NULL,
  `Voter_Fb_id` varchar(100) DEFAULT NULL,
  `Voter_DOB` varchar(100) DEFAULT NULL,
  `Voter_income` varchar(100) DEFAULT NULL,
  `Voter_gender` varchar(100) DEFAULT NULL,
  `Voter_Address` varchar(100) DEFAULT NULL,
  `Voter_Address_lin1` varchar(100) DEFAULT NULL,
  `Voter_locality_name` varchar(100) DEFAULT NULL,
  `Voter_Area_Name` varchar(100) DEFAULT NULL,
  `District` varchar(100) DEFAULT NULL,
  `State` varchar(100) DEFAULT NULL,
  `Postal_Code` varchar(100) DEFAULT NULL,
  `Country` varchar(100) DEFAULT NULL,
  `Voter_GPS` varchar(100) DEFAULT NULL,
  `No_of_voters` varchar(100) DEFAULT NULL,
  `Voter_Education` varchar(100) DEFAULT NULL,
  `Voter_Occupation` varchar(100) DEFAULT NULL,
  `Voter_caste` varchar(100) DEFAULT NULL,
  `Voter_subcaste` varchar(100) DEFAULT NULL,
  `Education` varchar(100) DEFAULT NULL,
  `Employment` varchar(100) DEFAULT NULL,
  `Health` varchar(100) DEFAULT NULL,
  `Electricity` varchar(100) DEFAULT NULL,
  `Water` varchar(100) DEFAULT NULL,
  `Sever` varchar(100) DEFAULT NULL,
  `Sanitation` varchar(100) DEFAULT NULL,
  `Law_and_order` varchar(100) DEFAULT NULL,
  `Corruption` varchar(100) DEFAULT NULL,
  `Roads` varchar(100) DEFAULT NULL,
  `Women_Security` varchar(100) DEFAULT NULL,
  `Public_Ideas` varchar(100) DEFAULT NULL,
  `Public_Information_notices` varchar(100) DEFAULT NULL,
  `Local_Events` varchar(100) DEFAULT NULL,
  `Development_work_projects` varchar(100) DEFAULT NULL,
  `About_MLA_MP` varchar(100) DEFAULT NULL,
  `admin_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey_data`
--

LOCK TABLES `survey_data` WRITE;
/*!40000 ALTER TABLE `survey_data` DISABLE KEYS */;
INSERT INTO `survey_data` VALUES (16,'g','f','f','f','b','g','','','','','','','','','','','','','x','c','855555','t','','','','','','','','','','','','','','','','','','','','','','',87),(17,'x','x','xx','x','x','x','x','','','','','','','','','','','','x','x','888888','x','','','','','','','','','','','','','','','','','','','','','','',90);
/*!40000 ALTER TABLE `survey_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `email` varchar(250) NOT NULL,
  `mobile` varchar(10) NOT NULL,
  `pincode` int(6) NOT NULL,
  `apikey` varchar(32) NOT NULL,
  `status` int(1) NOT NULL DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `admin` varchar(50) DEFAULT NULL,
  `account_id` int(11) DEFAULT NULL,
  `voter_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (86,'v','g','9655337570',556633,'6ea8a5f5ed520f1da7badb2f2bbc8770',1,'2017-07-30 05:37:52','j',2,'55'),(87,'h','gg','9655337570',556633,'d0f15ef476a50a8287757f0bd7b3a2af',1,'2017-07-30 05:38:58','h',1,'5'),(88,'e','f','26',555555,'b71fcc6391d92585615354f27be68da6',1,'2017-07-30 05:50:15','u',2,'5'),(89,'g','gjj','6',666666,'afd15a15f6bf717f3db61ee586fcf342',1,'2017-07-30 05:53:19','h',2,'5'),(90,'g','f','222',222222,'008fa593fe5a1c91ec0b5230a5071420',1,'2017-07-30 05:54:40','g',1,'5'),(91,'hdhdj','gga','9688458069',557565,'759290886f41f34e4ae0ab012ac2cf1d',1,'2017-07-30 10:59:33','hsjs',2,'54524'),(92,'hdhdj','gga','9688458069',557565,'e7eff3e1caf08e4aee170dce0fb376da',0,'2017-07-30 10:59:33','hsjs',2,'54524'),(93,'hdhdj','gga','9742238038',557565,'7597d6fc518869b888435661d88dd9bd',0,'2017-07-30 11:00:15','hsjs',2,'54524'),(94,'hdhdj','gga','9742238038',557565,'589aad4a92a3f2e0d31604401c9862e2',1,'2017-07-30 11:00:15','hsjs',2,'54524'),(95,'fff','vgg','9688458069',607302,'d3618a0d6e6d40702f5827554f7f29c6',1,'2017-07-30 14:25:57','tt',1,'52'),(96,'himmat','hpadode@gmail.com','9513334407',220011,'2b3c2c2034452513183be61d5bd96848',1,'2017-07-31 05:00:01','gurgaon',2,'525');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-31  5:29:37
