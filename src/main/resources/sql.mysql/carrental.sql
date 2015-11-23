/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50611
Source Host           : localhost:3306
Source Database       : carrental

Target Server Type    : MYSQL
Target Server Version : 50611
File Encoding         : 65001

Date: 2015-11-23 20:14:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car` (
  `id` int(11) NOT NULL,
  `is_rentaling` varchar(255) NOT NULL,
  `shop_id` int(11) NOT NULL,
  `car_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `feedback_time` datetime NOT NULL,
  `finish_time` datetime NOT NULL,
  `content` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `car_id` int(11) NOT NULL,
  `out_time` datetime NOT NULL,
  `back_time` datetime NOT NULL,
  `is_feedback` int(255) DEFAULT NULL,
  `order_satus` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `id` int(11) NOT NULL,
  `position` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `phone_name` varchar(255) NOT NULL,
  `head` varchar(255) DEFAULT NULL,
  `card_class` varchar(255) DEFAULT NULL,
  `card_num` varchar(255) DEFAULT NULL,
  `is_rentaling` varchar(255) DEFAULT NULL,
  `is_loging` varchar(255) DEFAULT NULL,
  `register_time` datetime DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
