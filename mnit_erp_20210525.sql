-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: mnit_erp
-- ------------------------------------------------------
-- Server version	5.7.19

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
-- Table structure for table `admissionType`
--

DROP TABLE IF EXISTS `admissionType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admissionType` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `abbreviation` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admissionType`
--

LOCK TABLES `admissionType` WRITE;
/*!40000 ALTER TABLE `admissionType` DISABLE KEYS */;
INSERT INTO `admissionType` VALUES (1,'FT','FULL TIME',1),(2,'OFC','OFF CAMPUS',1),(3,'PT','PART TIME',1),(4,'STF','STAFF',1);
/*!40000 ALTER TABLE `admissionType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `branch` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `abbreviation` varchar(45) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `active` tinyint(4) DEFAULT NULL,
  `departmentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `departmentId_idx` (`departmentId`),
  CONSTRAINT `departmentId` FOREIGN KEY (`departmentId`) REFERENCES `department` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES (1,'','ARCHITECTURE AND PLANNING','A',1,1),(2,'','CENTRE FOR ENERGY AND ENVIRONMENT','A',1,2),(3,'','CHEMICAL ENGINEERING','A',1,3),(4,'','CHEMISTRY','A',1,4),(5,'','CIVIL ENGINEERING','A',1,5),(6,'','COMPUTER SCIENCE AND ENGINEERING','A',1,6),(7,'','ELECTRICAL ENGINEERING','A',1,7),(8,'','ELECTRONICS AND COMMUNICATION ENGINEERING','A',1,8),(9,'','HUMANITIES AND SOCIAL SCIENCE','A',1,9),(10,'','INFORMATION TECHNOLOGY','A',1,10),(11,'','MANAGEMENT STUDIES','A',1,11),(12,'','MATERIAL RESEARCH CENTER','A',1,12),(13,'','MATHEMATICS','A',1,13),(14,'','MECHANICAL ENGINEERING','A',1,14),(15,'','METALLURGICAL AND MATERIALS ENGINEERING','A',1,15),(16,'','NATIONAL CENTRE FOR DISASTER MITIGATION AND MANAGEMENT','A',1,16),(17,'','PHYSICS','A',1,17);
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branch_degree_mapping`
--

DROP TABLE IF EXISTS `branch_degree_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `branch_degree_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `branchId` int(11) DEFAULT NULL,
  `degreeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch_degree_mapping`
--

LOCK TABLES `branch_degree_mapping` WRITE;
/*!40000 ALTER TABLE `branch_degree_mapping` DISABLE KEYS */;
INSERT INTO `branch_degree_mapping` VALUES (1,1,11),(2,1,13),(3,1,17),(4,2,15),(5,2,17),(6,3,12),(7,3,15),(8,3,17),(9,4,14),(10,4,17),(11,5,12),(12,5,15),(13,5,17),(14,6,12),(15,6,15),(16,6,17),(17,7,12),(18,7,15),(19,7,17),(20,8,12),(21,8,15),(22,8,17),(23,9,17),(24,10,12),(25,11,16),(26,11,17),(27,12,15),(28,12,17),(29,13,14),(30,13,17),(31,14,12),(32,14,15),(33,14,17),(34,15,12),(35,15,15),(36,15,17),(37,16,15),(38,16,17),(39,17,14),(40,17,17);
/*!40000 ALTER TABLE `branch_degree_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `abbreviation` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'EWS','Economically Weaker Section',1),(2,'GEN','General',1),(3,'OBC','Other Backward Class',1),(4,'SC','Scheduled Castes',1),(5,'ST','Scheduled Tribes',1);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `committee`
--

DROP TABLE IF EXISTS `committee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `committee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `abbreviation` varchar(45) DEFAULT NULL,
  `name` varchar(128) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  `constitutionPolicy` varchar(2048) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `committee`
--

LOCK TABLES `committee` WRITE;
/*!40000 ALTER TABLE `committee` DISABLE KEYS */;
/*!40000 ALTER TABLE `committee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `id` int(11) NOT NULL,
  `code` varchar(45) DEFAULT NULL,
  `name` varchar(128) DEFAULT NULL,
  `active` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'AF','Afghanistan',1),(2,'AX','Ã…land Islands',1),(3,'AL','Albania',1),(4,'DZ','Algeria',1),(5,'AS','American Samoa',1),(6,'AD','Andorra',1),(7,'AO','Angola',1),(8,'AI','Anguilla',1),(9,'AQ','Antarctica',1),(10,'AG','Antigua and Barbuda',1),(11,'AR','Argentina',1),(12,'AM','Armenia',1),(13,'AW','Aruba',1),(14,'AU','Australia',1),(15,'AT','Austria',1),(16,'AZ','Azerbaijan',1),(17,'BS','Bahamas',1),(18,'BH','Bahrain',1),(19,'BD','Bangladesh',1),(20,'BB','Barbados',1),(21,'BY','Belarus',1),(22,'BE','Belgium',1),(23,'BZ','Belize',1),(24,'BJ','Benin',1),(25,'BM','Bermuda',1),(26,'BT','Bhutan',1),(27,'BO','Bolivia, Plurinational State of',1),(28,'BQ','Bonaire, Sint Eustatius and Saba',1),(29,'BA','Bosnia and Herzegovina',1),(30,'BW','Botswana',1),(31,'BV','Bouvet Island',1),(32,'BR','Brazil',1),(33,'IO','British Indian Ocean Territory',1),(34,'BN','Brunei Darussalam',1),(35,'BG','Bulgaria',1),(36,'BF','Burkina Faso',1),(37,'BI','Burundi',1),(38,'KH','Cambodia',1),(39,'CM','Cameroon',1),(40,'CA','Canada',1),(41,'CV','Cape Verde',1),(42,'KY','Cayman Islands',1),(43,'CF','Central African Republic',1),(44,'TD','Chad',1),(45,'CL','Chile',1),(46,'CN','China',1),(47,'CX','Christmas Island',1),(48,'CC','Cocos (Keeling) Islands',1),(49,'CO','Colombia',1),(50,'KM','Comoros',1),(51,'CG','Congo',1),(52,'CD','Congo, the Democratic Republic of the',1),(53,'CK','Cook Islands',1),(54,'CR','Costa Rica',1),(55,'CI','CÃ´te d\'Ivoire',1),(56,'HR','Croatia',1),(57,'CU','Cuba',1),(58,'CW','CuraÃ§ao',1),(59,'CY','Cyprus',1),(60,'CZ','Czech Republic',1),(61,'DK','Denmark',1),(62,'DJ','Djibouti',1),(63,'DM','Dominica',1),(64,'DO','Dominican Republic',1),(65,'EC','Ecuador',1),(66,'EG','Egypt',1),(67,'SV','El Salvador',1),(68,'GQ','Equatorial Guinea',1),(69,'ER','Eritrea',1),(70,'EE','Estonia',1),(71,'ET','Ethiopia',1),(72,'FK','Falkland Islands (Malvinas)',1),(73,'FO','Faroe Islands',1),(74,'FJ','Fiji',1),(75,'FI','Finland',1),(76,'FR','France',1),(77,'GF','French Guiana',1),(78,'PF','French Polynesia',1),(79,'TF','French Southern Territories',1),(80,'GA','Gabon',1),(81,'GM','Gambia',1),(82,'GE','Georgia',1),(83,'DE','Germany',1),(84,'GH','Ghana',1),(85,'GI','Gibraltar',1),(86,'GR','Greece',1),(87,'GL','Greenland',1),(88,'GD','Grenada',1),(89,'GP','Guadeloupe',1),(90,'GU','Guam',1),(91,'GT','Guatemala',1),(92,'GG','Guernsey',1),(93,'GN','Guinea',1),(94,'GW','Guinea-Bissau',1),(95,'GY','Guyana',1),(96,'HT','Haiti',1),(97,'HM','Heard Island and McDonald Islands',1),(98,'VA','Holy See (Vatican City State)',1),(99,'HN','Honduras',1),(100,'HK','Hong Kong',1),(101,'HU','Hungary',1),(102,'IS','Iceland',1),(103,'IN','India',1),(104,'ID','Indonesia',1),(105,'IR','Iran, Islamic Republic of',1),(106,'IQ','Iraq',1),(107,'IE','Ireland',1),(108,'IM','Isle of Man',1),(109,'IL','Israel',1),(110,'IT','Italy',1),(111,'JM','Jamaica',1),(112,'JP','Japan',1),(113,'JE','Jersey',1),(114,'JO','Jordan',1),(115,'KZ','Kazakhstan',1),(116,'KE','Kenya',1),(117,'KI','Kiribati',1),(118,'KP','Korea, Democratic People\'s Republic of',1),(119,'KR','Korea, Republic of',1),(120,'KW','Kuwait',1),(121,'KG','Kyrgyzstan',1),(122,'LA','Lao People\'s Democratic Republic',1),(123,'LV','Latvia',1),(124,'LB','Lebanon',1),(125,'LS','Lesotho',1),(126,'LR','Liberia',1),(127,'LY','Libya',1),(128,'LI','Liechtenstein',1),(129,'LT','Lithuania',1),(130,'LU','Luxembourg',1),(131,'MO','Macao',1),(132,'MK','Macedonia, the Former Yugoslav Republic of',1),(133,'MG','Madagascar',1),(134,'MW','Malawi',1),(135,'MY','Malaysia',1),(136,'MV','Maldives',1),(137,'ML','Mali',1),(138,'MT','Malta',1),(139,'MH','Marshall Islands',1),(140,'MQ','Martinique',1),(141,'MR','Mauritania',1),(142,'MU','Mauritius',1),(143,'YT','Mayotte',1),(144,'MX','Mexico',1),(145,'FM','Micronesia, Federated States of',1),(146,'MD','Moldova, Republic of',1),(147,'MC','Monaco',1),(148,'MN','Mongolia',1),(149,'ME','Montenegro',1),(150,'MS','Montserrat',1),(151,'MA','Morocco',1),(152,'MZ','Mozambique',1),(153,'MM','Myanmar',1),(154,'NA','Namibia',1),(155,'NR','Nauru',1),(156,'NP','Nepal',1),(157,'NL','Netherlands',1),(158,'NC','New Caledonia',1),(159,'NZ','New Zealand',1),(160,'NI','Nicaragua',1),(161,'NE','Niger',1),(162,'NG','Nigeria',1),(163,'NU','Niue',1),(164,'NF','Norfolk Island',1),(165,'MP','Northern Mariana Islands',1),(166,'NO','Norway',1),(167,'OM','Oman',1),(168,'PK','Pakistan',1),(169,'PW','Palau',1),(170,'PS','Palestine, State of',1),(171,'PA','Panama',1),(172,'PG','Papua New Guinea',1),(173,'PY','Paraguay',1),(174,'PE','Peru',1),(175,'PH','Philippines',1),(176,'PN','Pitcairn',1),(177,'PL','Poland',1),(178,'PT','Portugal',1),(179,'PR','Puerto Rico',1),(180,'QA','Qatar',1),(181,'RE','RÃ©union',1),(182,'RO','Romania',1),(183,'RU','Russian Federation',1),(184,'RW','Rwanda',1),(185,'BL','Saint BarthÃ©lemy',1),(186,'SH','Saint Helena, Ascension and Tristan da Cunha',1),(187,'KN','Saint Kitts and Nevis',1),(188,'LC','Saint Lucia',1),(189,'MF','Saint Martin (French part)',1),(190,'PM','Saint Pierre and Miquelon',1),(191,'VC','Saint Vincent and the Grenadines',1),(192,'WS','Samoa',1),(193,'SM','San Marino',1),(194,'ST','Sao Tome and Principe',1),(195,'SA','Saudi Arabia',1),(196,'SN','Senegal',1),(197,'RS','Serbia',1),(198,'SC','Seychelles',1),(199,'SL','Sierra Leone',1),(200,'SG','Singapore',1),(201,'SX','Sint Maarten (Dutch part)',1),(202,'SK','Slovakia',1),(203,'SI','Slovenia',1),(204,'SB','Solomon Islands',1),(205,'SO','Somalia',1),(206,'ZA','South Africa',1),(207,'GS','South Georgia and the South Sandwich Islands',1),(208,'SS','South Sudan',1),(209,'ES','Spain',1),(210,'LK','Sri Lanka',1),(211,'SD','Sudan',1),(212,'SR','Suriname',1),(213,'SJ','Svalbard and Jan Mayen',1),(214,'SZ','Swaziland',1),(215,'SE','Sweden',1),(216,'CH','Switzerland',1),(217,'SY','Syrian Arab Republic',1),(218,'TW','Taiwan, Province of China',1),(219,'TJ','Tajikistan',1),(220,'TZ','Tanzania, United Republic of',1),(221,'TH','Thailand',1),(222,'TL','Timor-Leste',1),(223,'TG','Togo',1),(224,'TK','Tokelau',1),(225,'TO','Tonga',1),(226,'TT','Trinidad and Tobago',1),(227,'TN','Tunisia',1),(228,'TR','Turkey',1),(229,'TM','Turkmenistan',1),(230,'TC','Turks and Caicos Islands',1),(231,'TV','Tuvalu',1),(232,'UG','Uganda',1),(233,'UA','Ukraine',1),(234,'AE','United Arab Emirates',1),(235,'GB','United Kingdom',1),(236,'US','United States',1),(237,'UM','United States Minor Outlying Islands',1),(238,'UY','Uruguay',1),(239,'UZ','Uzbekistan',1),(240,'VU','Vanuatu',1),(241,'VE','Venezuela, Bolivarian Republic of',1),(242,'VN','Viet Nam',1),(243,'VG','Virgin Islands, British',1),(244,'VI','Virgin Islands, U.S.',1),(245,'WF','Wallis and Futuna',1),(246,'EH','Western Sahara',1),(247,'YE','Yemen',1),(248,'ZM','Zambia',1),(249,'ZW','Zimbabwe',1);
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `degree`
--

DROP TABLE IF EXISTS `degree`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `degree` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `abbreviation` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `programId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `programId_idx` (`programId`),
  CONSTRAINT `programId` FOREIGN KEY (`programId`) REFERENCES `program` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `degree`
--

LOCK TABLES `degree` WRITE;
/*!40000 ALTER TABLE `degree` DISABLE KEYS */;
INSERT INTO `degree` VALUES (11,'B.Arch','Bachelor of Architecture',1),(12,'B.Tech','Bachelor of Technology',1),(13,'M.Plan','Master of Planning',2),(14,'M.Sc','Master of Science',2),(15,'M.Tech','Master of Technology',2),(16,'MBA','Master of Business Administration',2),(17,'Ph.D','Doctor of Philoshopy',3);
/*!40000 ALTER TABLE `degree` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `active` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'ARCHITECTURE AND PLANNING','A',1),(2,'CENTRE FOR ENERGY AND ENVIRONMENT','A',1),(3,'CHEMICAL ENGINEERING','A',1),(4,'CHEMISTRY','A',1),(5,'CIVIL ENGINEERING','A',1),(6,'COMPUTER SCIENCE AND ENGINEERING','A',1),(7,'ELECTRICAL ENGINEERING','A',1),(8,'ELECTRONICS AND COMMUNICATION ENGINEERING','A',1),(9,'HUMANITIES AND SOCIAL SCIENCE','A',1),(10,'INFORMATION TECHNOLOGY','A',1),(11,'MANAGEMENT STUDIES','A',1),(12,'MATERIAL RESEARCH CENTER','A',1),(13,'MATHEMATICS','A',1),(14,'MECHANICAL ENGINEERING','A',1),(15,'METALLURGICAL AND MATERIALS ENGINEERING','A',1),(16,'NATIONAL CENTRE FOR DISASTER MITIGATION AND MANAGEMENT','A',1),(17,'PHYSICS','A',1),(18,'ACADEMIC SECTION','O',1),(19,'ESTABLISHMENT','O',1),(20,'ACCOUNTS SECTION','O',1),(21,'RESEARCH AND CONSULTANCY','O',1),(22,'STUDENT WELFARE','O',1),(23,'TRAINING AND PLACEMENT','O',1),(24,'ESTATE','O',1),(25,'STORE AND PURCHASE','O',1);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department_degree_mapping`
--

DROP TABLE IF EXISTS `department_degree_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department_degree_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `departmentId` int(11) DEFAULT NULL,
  `degreeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `departmentId_idx` (`departmentId`),
  KEY `degreeId_idx` (`degreeId`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department_degree_mapping`
--

LOCK TABLES `department_degree_mapping` WRITE;
/*!40000 ALTER TABLE `department_degree_mapping` DISABLE KEYS */;
INSERT INTO `department_degree_mapping` VALUES (1,1,11),(2,1,13),(3,1,17),(4,2,15),(5,2,17),(6,3,12),(7,3,15),(8,3,17),(9,4,14),(10,4,17),(11,5,12),(12,5,15),(13,5,17),(14,6,12),(15,6,15),(16,6,17),(17,7,12),(18,7,15),(19,7,17),(20,8,12),(21,8,15),(22,8,17),(23,9,17),(24,10,12),(25,11,16),(26,11,17),(27,12,15),(28,12,17),(29,13,14),(30,13,17),(31,14,12),(32,14,15),(33,14,17),(34,15,12),(35,15,15),(36,15,17),(37,16,15),(38,16,17),(39,17,14),(40,17,17);
/*!40000 ALTER TABLE `department_degree_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `designation`
--

DROP TABLE IF EXISTS `designation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `designation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `abbreviation` varchar(45) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `designation`
--

LOCK TABLES `designation` WRITE;
/*!40000 ALTER TABLE `designation` DISABLE KEYS */;
/*!40000 ALTER TABLE `designation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `familyIncomeCategory`
--

DROP TABLE IF EXISTS `familyIncomeCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `familyIncomeCategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `minIncome` double DEFAULT NULL,
  `maxIncome` double DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `familyIncomeCategory`
--

LOCK TABLES `familyIncomeCategory` WRITE;
/*!40000 ALTER TABLE `familyIncomeCategory` DISABLE KEYS */;
INSERT INTO `familyIncomeCategory` VALUES (1,'Below 1 Lac','',0,100000,1),(2,'1 Lac to 5 Lac','',100001,500000,1),(3,'Above 5 Lac','',500001,99999999,1);
/*!40000 ALTER TABLE `familyIncomeCategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (74);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `holiday`
--

DROP TABLE IF EXISTS `holiday`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `holiday` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `holidayDate` date DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `holiday`
--

LOCK TABLES `holiday` WRITE;
/*!40000 ALTER TABLE `holiday` DISABLE KEYS */;
INSERT INTO `holiday` VALUES (66,'2021-05-14','Eid','0',1),(67,'2021-08-24','RAKHI','1',1),(68,'2021-08-24','RAKHI','1',1),(69,'2021-08-24','RAKHI','1',1),(70,'2021-08-24','RAKHI','1',1),(71,'2021-05-15','Eid','0',1);
/*!40000 ALTER TABLE `holiday` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `program`
--

DROP TABLE IF EXISTS `program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `program` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `abbreviation` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `program`
--

LOCK TABLES `program` WRITE;
/*!40000 ALTER TABLE `program` DISABLE KEYS */;
INSERT INTO `program` VALUES (1,'UG','Under Graduate'),(2,'PG','Post Graduate'),(3,'PhD','Doctor of Philosapy');
/*!40000 ALTER TABLE `program` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section`
--

DROP TABLE IF EXISTS `section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `section` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(2) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section`
--

LOCK TABLES `section` WRITE;
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
INSERT INTO `section` VALUES (1,'A',1),(2,'B',1),(3,'C',1),(4,'D',1),(5,'E',1),(6,'F',1),(7,'G',1),(8,'H',1),(9,'I',1),(10,'J',1);
/*!40000 ALTER TABLE `section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `selectionBoardType`
--

DROP TABLE IF EXISTS `selectionBoardType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `selectionBoardType` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `abbreviation` varchar(45) DEFAULT NULL,
  `name` varchar(128) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `selectionBoardType`
--

LOCK TABLES `selectionBoardType` WRITE;
/*!40000 ALTER TABLE `selectionBoardType` DISABLE KEYS */;
INSERT INTO `selectionBoardType` VALUES (1,'CIWG','Children of Indian Workers in Gulf Countries',1),(2,'NONSAARC','NONSAARC',1),(3,'SAARC','SAARC',1),(4,'EDUCATION','EDUCATION',1),(5,'WELFARE','WELFARE',1);
/*!40000 ALTER TABLE `selectionBoardType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `selectionBoardTypeMapping`
--

DROP TABLE IF EXISTS `selectionBoardTypeMapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `selectionBoardTypeMapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `selectionBoardId` int(11) DEFAULT NULL,
  `selectionBoardTypeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `selectionBoardId_idx` (`selectionBoardId`),
  KEY `selectionBoardTypeId_idx` (`selectionBoardTypeId`),
  CONSTRAINT `selectionBoardId` FOREIGN KEY (`selectionBoardId`) REFERENCES `selectionboard` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `selectionBoardTypeId` FOREIGN KEY (`selectionBoardTypeId`) REFERENCES `selectionBoardType` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `selectionBoardTypeMapping`
--

LOCK TABLES `selectionBoardTypeMapping` WRITE;
/*!40000 ALTER TABLE `selectionBoardTypeMapping` DISABLE KEYS */;
INSERT INTO `selectionBoardTypeMapping` VALUES (1,5,1),(2,5,2),(3,5,3),(4,8,2),(5,8,3),(6,10,4),(7,10,3),(8,10,5);
/*!40000 ALTER TABLE `selectionBoardTypeMapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `selectionboard`
--

DROP TABLE IF EXISTS `selectionboard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `selectionboard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `abbreviation` varchar(45) DEFAULT NULL,
  `name` varchar(128) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `selectionboard`
--

LOCK TABLES `selectionboard` WRITE;
/*!40000 ALTER TABLE `selectionboard` DISABLE KEYS */;
INSERT INTO `selectionboard` VALUES (1,'CCB','Central Counselling Board',0),(2,'CCMN','Centralised Counselling for M.Sc.',1),(3,'CCMT','Centralized Counselling for MTech / MArch/ MPlan',1),(4,'CSAB','Central Seat Allocation Board',1),(5,'DASA','Direct Admission of Students Abroad',1),(6,'DEE','DEPARTMENTAL ENTRANCE EXAM',1),(7,'GATE SCORE','GATE SCORE',1),(8,'ICCR','Indian Council for Cultural Relations',1),(9,'JRF','Junior Research Fellowship',1),(10,'MEA','Ministry of External Affairs',1),(11,'QIP','Qualified Institutional Placement',1),(12,'QIP(POLY)','Qualified Institutional Placement (Poly)',1),(13,'SPONSORED','SPONSORED',1),(14,'TEQIP-III','Technical Education Quality Improvement Programme',1);
/*!40000 ALTER TABLE `selectionboard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semester`
--

DROP TABLE IF EXISTS `semester`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `semester` (
  `id` int(11) NOT NULL,
  `semester` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester`
--

LOCK TABLES `semester` WRITE;
/*!40000 ALTER TABLE `semester` DISABLE KEYS */;
INSERT INTO `semester` VALUES (1,1,'First','ODD',NULL,1),(2,2,'Second','EVEN',NULL,1),(3,3,'Third','ODD',NULL,1),(4,4,'Fourth','EVEN',NULL,1),(5,5,'Fifth','ODD',NULL,1),(6,6,'Sixth','EVEN',NULL,1),(7,7,'Seventh','ODD',NULL,1),(8,8,'Eighth','EVEN',NULL,1),(9,9,'Nineth','ODD',NULL,1),(10,10,'Tenth','EVEN',NULL,1),(11,11,'Eleventh','ODD',NULL,1),(12,12,'Twelveth','EVEN',NULL,1),(13,13,'Thirteenth','ODD',NULL,1),(14,14,'Fourteenth','EVEN',NULL,1);
/*!40000 ALTER TABLE `semester` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specialization`
--

DROP TABLE IF EXISTS `specialization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `specialization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `abbreviation` varchar(45) DEFAULT NULL,
  `name` varchar(128) DEFAULT NULL,
  `branchId` int(11) DEFAULT NULL,
  `active` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `branchId_idx` (`branchId`),
  CONSTRAINT `branchId` FOREIGN KEY (`branchId`) REFERENCES `branch` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialization`
--

LOCK TABLES `specialization` WRITE;
/*!40000 ALTER TABLE `specialization` DISABLE KEYS */;
INSERT INTO `specialization` VALUES (1,'','CHEMICAL ENGINEERING',3,1),(2,'','CHEMISTRY',4,1),(3,'','CIVIL ENGINEERING (DISASTER ASSESSMENT AND MITIGATION)',5,1),(4,'','COMPUTER ENGINEERING',6,1),(5,'','COMPUTER ENGINEERING AND INFORMATION SECURITY',6,1),(6,'','DESIGN ENGINEERING',14,1),(7,'','EARTHQUAKE ENGINEERING',16,1),(8,'','ELECTRONICS & COMMUNICATION ENGINEERING',8,1),(9,'','EMBEDDED SYSTEMS',8,1),(10,'','ENVIRONMENTAL ENGINEERING',5,1),(11,'','INDUSTRIAL ENGINEERING',14,1),(12,'','MANAGEMENT STUDIES',11,1),(13,'','MATERIALS SCIENCE AND ENGINEERING',12,1),(14,'','MATHEMATICS',13,1),(15,'','METALLURGICAL & MATERIALS ENGINEERING',15,1),(16,'','PHYSICS',17,1),(17,'','POWER ELECTRONICS AND DRIVES',7,1),(18,'','POWER SYSTEMS',7,1),(19,'','POWER SYSTEMS MANAGEMENT',7,1),(20,'','PRODUCTION ENGINEERING',14,1),(21,'','RENEWABLE ENERGY',2,1),(22,'','STRUCTURAL ENGINEERING',5,1),(23,'','THERMAL ENGINEERING',14,1),(24,'','TRANSPORTATION ENGINEERING',5,1),(25,'','URBAN PLANNING',1,1),(26,'','VLSI DESIGN',8,1),(27,'','WATER RESOURCES ENGINEERING',5,1),(28,'','WIRELESS AND OPTICAL COMMUNICATION',8,1);
/*!40000 ALTER TABLE `specialization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `state`
--

DROP TABLE IF EXISTS `state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `state` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `countryId` int(11) DEFAULT NULL,
  `active` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `countryId_idx` (`countryId`),
  CONSTRAINT `countryId` FOREIGN KEY (`countryId`) REFERENCES `country` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `state`
--

LOCK TABLES `state` WRITE;
/*!40000 ALTER TABLE `state` DISABLE KEYS */;
INSERT INTO `state` (id,name,country_id,active) VALUES (1,'Andhra Pradesh',103,1),(2,'Arunachal Pradesh',103,1),(3,'Assam',103,1),(4,'Bihar',103,1),(5,'Chhattisgarh',103,1),(6,'Goa',103,1),(7,'Gujarat',103,1),(8,'Haryana',103,1),(9,'Himachal Pradesh',103,1),(10,'Jammu and Kashmir',103,1),(11,'Jharkhand',103,1),(12,'Karnataka',103,1),(13,'Kerala',103,1),(14,'Madhya Pradesh',103,1),(15,'Maharashtra',103,1),(16,'Manipur',103,1),(17,'Meghalaya',103,1),(18,'Mizoram',103,1),(19,'Nagaland',103,1),(20,'Odisha',103,1),(21,'Punjab',103,1),(22,'Rajasthan',103,1),(23,'Sikkim',103,1),(24,'Tamil Nadu',103,1),(25,'Telangana',103,1),(26,'Tripura',103,1),(27,'Uttarakhand',103,1),(28,'Uttar Pradesh',103,1),(29,'West Bengal',103,1),(30,'Andaman and Nicobar Islands',103,1),(31,'Chandigarh',103,1),(32,'Dadra and Nagar Haveli',103,1),(33,'Daman and Diu',103,1),(34,'Delhi',103,1),(35,'Lakshadweep',103,1),(36,'Puducherry',103,1);
/*!40000 ALTER TABLE `state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studentStatus`
--

DROP TABLE IF EXISTS `studentStatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `studentStatus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `abbreviation` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `admissionStatus` tinyint(4) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  `active` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentStatus`
--

LOCK TABLES `studentStatus` WRITE;
/*!40000 ALTER TABLE `studentStatus` DISABLE KEYS */;
INSERT INTO `studentStatus` VALUES (1,'A','Active',1,'Student is active',1),(2,'C','Cancelled',0,'Admission Cancelled before first semester',1),(3,'D','Demised',1,'Student is no more',1),(4,'I','Inactive',1,'Student will have limited access',1),(5,'P','Passed Out',1,'Completed the course',1),(6,'T','Terminated',1,'Student terminated by institute',1),(7,'W','Withdrawn',1,'Student withdrawn his/her admission',1),(8,'M','Migrated',0,'Student migrated to other institute before first semester',1);
/*!40000 ALTER TABLE `studentStatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `mobile` varchar(10) DEFAULT NULL,
  `addedOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `addedBy` int(11) DEFAULT NULL,
  `activated` tinyint(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'harigyan','8a3485f0be943ce7b70d33ed2ac9cef3','9509109693','2021-02-23 06:06:03',NULL,1,'harigyan',1),(2,'testuser-01','7a65fcef-fee0-41df-83f3-d827c652414f','9549650136',NULL,NULL,NULL,'hari.cse@mnit.ac.in',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userRole`
--

DROP TABLE IF EXISTS `userRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userRole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `abbreviation` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userRole`
--

LOCK TABLES `userRole` WRITE;
/*!40000 ALTER TABLE `userRole` DISABLE KEYS */;
INSERT INTO `userRole` VALUES (45,NULL,NULL,NULL),(46,'DS-AS','Dealing Staff - Academic Section','NA');
/*!40000 ALTER TABLE `userRole` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-25 13:06:01
