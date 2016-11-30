DROP TABLE IF EXISTS `offer`;
DROP TABLE IF EXISTS `room_status`;
DROP TABLE IF EXISTS `booking_detail`;
DROP TABLE IF EXISTS `booking`;
DROP TABLE IF EXISTS `room`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `manager`;
DROP TABLE IF EXISTS `hotel`;
DROP TABLE IF EXISTS `peakperiod`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `nickname` varchar(30) DEFAULT NULL,
  `firstname` varchar(30) DEFAULT NULL,
  `lastname` varchar(30) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `credit_card_type` varchar(45) DEFAULT NULL,
  `credit_card_number` varchar(45) DEFAULT NULL,
  `credit_card_exp_month` varchar(45) DEFAULT NULL,
  `credit_card_exp_year` varchar(45) DEFAULT NULL,
  `credit_card_cvv` varchar(45) DEFAULT NULL,
  `email_verification` int(11) DEFAULT '0',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `iduser_UNIQUE` (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO user (username, password, email,email_verification) VALUES ('super', MD5('root'), 'expmple@sss.com', 1);
INSERT INTO user (username, password, email) VALUES ('changma', MD5('hiroot'), 'liuhaonan1@qq.com');
INSERT INTO user (username, password, email) VALUES ('rua', MD5('hiroot'), 'liuhaonan2@qq.com');



CREATE TABLE `hotel` (
  `hotel_id` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_name` varchar(50) DEFAULT NULL,
  `hotel_address` varchar(100) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`hotel_id`),
  UNIQUE KEY `hotel_id_UNIQUE` (`hotel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO hotel (hotel_name, hotel_address, city) VALUES ('Rampage-SYD-1', 'Sydney Road, NSW', 'Sydney');
INSERT INTO hotel (hotel_name, hotel_address, city) VALUES ('Rampage-SYD-2', 'Parramatta Road, NSW', 'Sydney');
INSERT INTO hotel (hotel_name, hotel_address, city) VALUES ('Rampage-MEL-1', 'Melbourne Road, VIC', 'Melbourne');
INSERT INTO hotel (hotel_name, hotel_address, city) VALUES ('Rampage-MEL-2', 'La Trobe Road, VIC', 'Melbourne');
INSERT INTO hotel (hotel_name, hotel_address, city) VALUES ('Rampage-BRI-1', 'Brisbane Road, QLD', 'Brisbane');
INSERT INTO hotel (hotel_name, hotel_address, city) VALUES ('Rampage-ADE-1', 'Adelaide Road, SA', 'Adelaide');
INSERT INTO hotel (hotel_name, hotel_address, city) VALUES ('Rampage-PER-1', 'Perth Street, WA', 'Perth');
INSERT INTO hotel (hotel_name, hotel_address, city) VALUES ('Rampage-HOB-1', 'Hobart Crescent, TAS', 'Hobart');

CREATE TABLE `manager` (
  `manager_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `hotel_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`manager_id`),
  KEY `hotel_id_manager_idx` (`hotel_id`),
  CONSTRAINT `hotel_id_manager` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `manager` WRITE;
UNLOCK TABLES;

INSERT INTO manager (username, password,hotel_id) 
VALUES ('manager1', MD5('root'), 1),
('manager2', MD5('root'), 2),
('manager3', MD5('root'), 3),
('manager4', MD5('root'), 4),
('manager5', MD5('root'), 5),
('manager6', MD5('root'), 6),
('manager7', MD5('root'), 7);



CREATE TABLE `room` (
  `room_id` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_id` int(11) NOT NULL,
  `room_type` varchar(50) DEFAULT NULL,
  `room_no` varchar(45) DEFAULT NULL,
  `normal_price` float DEFAULT NULL,
  `room_description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`room_id`),
  UNIQUE KEY `room_id_UNIQUE` (`room_id`),
  KEY `hotel_id_idx` (`hotel_id`),
  KEY `room_type_idx` (`room_type`),
  CONSTRAINT `hotel_id` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
UNLOCK TABLES;

INSERT INTO room (hotel_id, room_type, room_no, normal_price, room_description) VALUES (1, 'Single', '101', 80, 'One single bed.'), (1, 'Single', '102', 80, 'One single bed.'),
(1, 'Twin Bed', '201', 130, 'Two single beds.'), (1, 'Twin Bed', '202', 130, 'Two single beds.'), (1, 'Queen', '301', 150, 'One double bed.'), (1, 'Queen', '302', 150, 'One double bed.'),
(1, 'Executive', '401', 200, 'Double bed with extra facilities.'), (1, 'Suite', '501', 320, 'Two double beds with luxury facilities.');

INSERT INTO room (hotel_id, room_type, room_no, normal_price, room_description) VALUES (2, 'Single', '101', 80, 'One single bed.'), (2, 'Single', '102', 80, 'One single bed.'),
(2, 'Twin Bed', '201', 130, 'Two single beds.'), (2, 'Twin Bed', '202', 130, 'Two single beds.'), (2, 'Queen', '301', 150, 'One double bed.'), (2, 'Queen', '302', 150, 'One double bed.'),
(2, 'Executive', '401', 200, 'Double bed with extra facilities.'), (2, 'Suite', '501', 320, 'Two double beds with luxury facilities.');

INSERT INTO room (hotel_id, room_type, room_no, normal_price, room_description) VALUES (3, 'Single', '101', 80, 'One single bed.'), (3, 'Single', '102', 80, 'One single bed.'),
(3, 'Twin Bed', '201', 130, 'Two single beds.'), (3, 'Twin Bed', '202', 130, 'Two single beds.'), (3, 'Queen', '301', 150, 'One double bed.'), (3, 'Queen', '302', 150, 'One double bed.'),
(3, 'Executive', '401', 200, 'Double bed with extra facilities.'), (3, 'Suite', '501', 320, 'Two double beds with luxury facilities.');

INSERT INTO room (hotel_id, room_type, room_no, normal_price, room_description) VALUES (4, 'Single', '101', 80, 'One single bed.'), (4, 'Single', '102', 80, 'One single bed.'),
(4, 'Twin Bed', '201', 130, 'Two single beds.'), (4, 'Twin Bed', '202', 130, 'Two single beds.'), (4, 'Queen', '301', 150, 'One double bed.'), (4, 'Queen', '302', 150, 'One double bed.'),
(4, 'Executive', '401', 200, 'Double bed with extra facilities.'), (4, 'Suite', '501', 320, 'Two double beds with luxury facilities.');

INSERT INTO room (hotel_id, room_type, room_no, normal_price, room_description) VALUES (5, 'Single', '101', 80, 'One single bed.'), (5, 'Single', '102', 80, 'One single bed.'),
(5, 'Twin Bed', '201', 130, 'Two single beds.'), (5, 'Twin Bed', '202', 130, 'Two single beds.'), (5, 'Queen', '301', 150, 'One double bed.'), (5, 'Queen', '302', 150, 'One double bed.'),
(5, 'Executive', '401', 200, 'Double bed with extra facilities.'), (5, 'Suite', '501', 320, 'Two double beds with luxury facilities.');

INSERT INTO room (hotel_id, room_type, room_no, normal_price, room_description) VALUES (6, 'Single', '101', 80, 'One single bed.'), (6, 'Single', '102', 80, 'One single bed.'),
(6, 'Twin Bed', '201', 130, 'Two single beds.'), (6, 'Twin Bed', '202', 130, 'Two single beds.'), (6, 'Queen', '301', 150, 'One double bed.'), (6, 'Queen', '302', 150, 'One double bed.'),
(6, 'Executive', '401', 200, 'Double bed with extra facilities.'), (6, 'Suite', '501', 320, 'Two double beds with luxury facilities.');

INSERT INTO room (hotel_id, room_type, room_no, normal_price, room_description) VALUES (7, 'Single', '101', 80, 'One single bed.'), (7, 'Single', '102', 80, 'One single bed.'),
(7, 'Twin Bed', '201', 130, 'Two single beds.'), (7, 'Twin Bed', '202', 130, 'Two single beds.'), (7, 'Queen', '301', 150, 'One double bed.'), (7, 'Queen', '302', 150, 'One double bed.'),
(7, 'Executive', '401', 200, 'Double bed with extra facilities.'), (7, 'Suite', '501', 320, 'Two double beds with luxury facilities.');

INSERT INTO room (hotel_id, room_type, room_no, normal_price, room_description) VALUES (8, 'Single', '101', 80, 'One single bed.'), (8, 'Single', '102', 80, 'One single bed.'),
(8, 'Twin Bed', '201', 130, 'Two single beds.'), (8, 'Twin Bed', '202', 130, 'Two single beds.'), (8, 'Queen', '301', 150, 'One double bed.'), (8, 'Queen', '302', 150, 'One double bed.'),
(8, 'Executive', '401', 200, 'Double bed with extra facilities.'), (8, 'Suite', '501', 320, 'Two double beds with luxury facilities.');

CREATE TABLE `booking` (
  `booking_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `checkin` date DEFAULT NULL,
  `checkout` date DEFAULT NULL,
  `pin` varchar(50) DEFAULT NULL,
  `number_of_room` int DEFAULT NULL,
  `total_price` float DEFAULT NULL,
  
  PRIMARY KEY (`booking_id`),
  UNIQUE KEY `booking_id_UNIQUE` (`booking_id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `user_id_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
--
-- Dumping data for table `booking`
--
LOCK TABLES `booking` WRITE;
UNLOCK TABLES;

INSERT INTO booking (user_id,checkin,checkout,total_price,number_of_room) 
VALUES (1,'2000-07-28','2100-07-30', 0,1);
INSERT INTO booking (user_id,checkin,checkout,total_price,number_of_room) 
VALUES (2,'2016-07-28','2016-07-30', 200,2);
INSERT INTO booking (user_id,checkin,checkout,total_price,number_of_room) 
VALUES (3,'2016-05-10','2016-07-10', 200,2);
INSERT INTO booking (user_id,checkin,checkout,total_price,number_of_room) 
VALUES (1,'2016-05-10','2016-05-20', 800,1);

CREATE TABLE `booking_detail` (
  `booking_detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `booking_id` int(11) DEFAULT NULL,
  `hotel_id` int(11) DEFAULT NULL,
  `room_type` varchar(45) DEFAULT NULL,
  `num_of_room` int(11) DEFAULT NULL,
  `extra_bed` int(11) DEFAULT NULL,
  `checkin` date DEFAULT NULL,
  `checkout` date DEFAULT NULL,
  `assign` int DEFAULT 0,
  PRIMARY KEY (`booking_detail_id`),
  UNIQUE KEY `booking_detail_id_UNIQUE` (`booking_detail_id`),
  KEY `booking_id_detail_idx` (`booking_id`),
  KEY `hotel_id_detail_idx` (`hotel_id`),
  CONSTRAINT `booking_id_detail` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`booking_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `hotel_id_detail` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `booking_detail` WRITE;
UNLOCK TABLES;
--
-- Dumping data for table `booking`
--

INSERT INTO `booking_detail` (`booking_id`, `hotel_id`, `room_type`, `num_of_room`, `extra_bed`, `assign`,`checkin`,`checkout`) 
VALUES (2, 1, 'Queen', 1, 1,0,'2016-07-28','2016-07-30'),
(2, 1, 'Single', 1, 0,0,'2016-07-28','2016-07-30'),
(3, 2, 'Twin Bed', 1, 0,0,'2016-05-10','2016-07-10'),
(3, 2, 'Queen', 1, 0,0,'2016-05-10','2016-07-10'),
(4, 2, 'Single', 1, 0,1,'2016-05-10','2016-05-20');


CREATE TABLE `room_status` (
  `status_id` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_id` int(11) DEFAULT NULL,
  `room_id` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `booking_price` float DEFAULT NULL,
  PRIMARY KEY (`status_id`),
  UNIQUE KEY `status_id_UNIQUE` (`status_id`),
  KEY `hotel_id2_idx` (`hotel_id`),
  KEY `room_id2_idx` (`room_id`),
  CONSTRAINT `hotel_id2` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `room_id2` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
  

) ENGINE=InnoDB DEFAULT CHARSET=latin1;
--
-- Dumping data for table `room_status`
--
LOCK TABLES `room_status` WRITE;
UNLOCK TABLES;

INSERT INTO room_status (hotel_id, room_id, status, start_date, end_date, booking_price) VALUES
(1,1,'repair', '2016-07-28','2016-07-30','100');
INSERT INTO room_status (hotel_id, room_id, status, start_date, end_date, booking_price) VALUES
(1,3,'repair', '2016-07-28','2016-07-30','100');
INSERT INTO room_status (hotel_id, room_id, status, start_date, end_date, booking_price) VALUES
(2,10,'occupied', '2016-05-10','2016-05-20','800');

CREATE TABLE `offer` (
  `offer_id` int(11) NOT NULL AUTO_INCREMENT,
  `offer_price` float DEFAULT NULL,
  `hotel_id` int(11) DEFAULT NULL,
  `room_type` varchar(50) DEFAULT NULL,
  `start` date DEFAULT NULL,
  `end` date DEFAULT NULL,
  PRIMARY KEY (`offer_id`),
  KEY `hotel_id3_idx` (`hotel_id`),
  KEY `room_type3_idx` (`room_type`),
  CONSTRAINT `hotel_id3` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `room_type3` FOREIGN KEY (`room_type`) REFERENCES `room` (`room_type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
--
-- Dumping data for table `offer`
--
LOCK TABLES `offer` WRITE;
UNLOCK TABLES;

INSERT INTO offer (offer_price, hotel_id, room_type,start, end) VALUES
(50,1,'Queen', '2016-07-28','2016-07-30');

CREATE TABLE `peakperiod` (
`period_id` int(11) NOT NULL AUTO_INCREMENT,
`period_name` varchar(50) DEFAULT NULL,
`price_increase` int(11) DEFAULT NULL,
`start` date DEFAULT NULL,
`end` date DEFAULT NULL,
PRIMARY KEY (`period_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `offer` WRITE;
UNLOCK TABLES;

