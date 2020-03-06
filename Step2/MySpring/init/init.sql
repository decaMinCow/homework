CREATE TABLE `account` (
  `cardNo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(256) DEFAULT NULL,
  `money` int(11) DEFAULT NULL,
  PRIMARY KEY (`cardNo`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;