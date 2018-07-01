create database carwash;

use carwash;

CREATE TABLE IF NOT EXISTS `all_users` (
  `email` varchar(50) UNIQUE NOT NULL,
  `role` varchar(50) NOT NULL
) ;

CREATE TABLE IF NOT EXISTS `customer` (
  `c_email` varchar(50),
  `password` varchar(1000) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `phone` varchar(10),
  `door_number` int(5) NOT NULL,
  `street` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `province` varchar(50) NOT NULL,
  `postal_code` varchar(50) NOT NULL,
  PRIMARY KEY (`c_email`)
);

CREATE TABLE IF NOT EXISTS `userpassword` (
  `email` varchar(50) UNIQUE NOT NULL,
  `UserPassword` varchar(10000) NOT NULL,
  `Salt` varchar(10000) DEFAULT NULL,
  PRIMARY KEY (`email`)
);


CREATE TABLE IF NOT EXISTS `owner` (
  `o_email` varchar(50) UNIQUE NOT NULL,
  `password` varchar(1000) NOT NULL,
  `car_wash_name` varchar(50) NOT NULL,  
  `phone` varchar(10) NOT NULL,
  `door_number` int(5) NOT NULL,
  `street` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `province` varchar(50) NOT NULL,
  `postal_code` varchar(50) NOT NULL,
  PRIMARY KEY (`o_email`)
)  AUTO_INCREMENT = 5000;

CREATE TABLE IF NOT EXISTS `admin` (
  `email` varchar(50),
  `password` varchar(1000) NOT NULL,
  PRIMARY KEY (`email`)
)  ;


CREATE TABLE IF NOT EXISTS `slots` (
  `slot_id` int(11) AUTO_INCREMENT,
  `time` varchar(50)  NOT NULL,
  PRIMARY KEY (`slot_id`)
) AUTO_INCREMENT = 100;

CREATE TABLE IF NOT EXISTS `washes` (
  `wash_id` int(11) AUTO_INCREMENT,
  `wash_type` varchar(50)  NOT NULL,
  PRIMARY KEY (`wash_id`)
) AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `price` (
  `price_id` int(3) AUTO_INCREMENT,
  `o_email` varchar(50) NOT NULL,
  `wash_id` int(11) NOT NULL,
  `price` float DEFAULT NULL,
  PRIMARY KEY (`price_id`),
  FOREIGN KEY (o_email) REFERENCES owner(o_email),
  FOREIGN KEY (wash_id) REFERENCES washes(wash_id)    
) AUTO_INCREMENT = 2000;

CREATE TABLE IF NOT EXISTS `orders` (
  `order_id` int(11) AUTO_INCREMENT,
  `o_email` varchar(50) NOT NULL,
  `c_email` varchar(50) NOT NULL,
  `wash_id` int(11) NOT NULL,
  `slot_id` int(11) NOT NULL,
  `order_date` date DEFAULT NULL,  
  `price` float NOT NULL,
  PRIMARY KEY (`order_id`),
  FOREIGN KEY (c_email) REFERENCES customer(c_email),
  FOREIGN KEY (o_email) REFERENCES owner(o_email),
  FOREIGN KEY (wash_id) REFERENCES washes(wash_id),
  FOREIGN KEY (slot_id) REFERENCES slots(slot_id)
) AUTO_INCREMENT = 100000;


insert into washes(wash_type) values('Internal Wash');
insert into washes(wash_type)  values('External Wash');
insert into washes(wash_type)  values('Full Body Wash');

insert into slots values(100,'10:00');
insert into slots values(101,'10:30');
insert into slots values(102,'11:00');
insert into slots values(103,'11:30');
insert into slots values(104,'12:00');
insert into slots values(105,'12:30');
insert into slots values(106,'13:00');
insert into slots values(107,'13:30');
insert into slots values(108,'14:00');
insert into slots values(109,'14:30');
insert into slots values(110,'15:00');
insert into slots values(111,'15:30');
insert into slots values(112,'16:00');
insert into slots values(113,'16:30');
insert into slots values(114,'17:00');
insert into slots values(115,'17:30');
insert into slots values(116,'18:00');
insert into slots values(117,'18:30');


use carwash;
select * from customer;
Select * from owner;
select * from admin;
select * from orders;
select * from slots;
select * from washes;
select * from price;


