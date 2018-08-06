/*
Navicat MySQL Data Transfer

Source Server         : java
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : modelactivity

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-08-06 14:32:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动id',
  `type` char(255) DEFAULT NULL COMMENT '活动类型',
  `title` varchar(255) DEFAULT NULL COMMENT '活动title',
  `name` varchar(255) DEFAULT NULL COMMENT '活动名称',
  `introduction` varchar(255) DEFAULT NULL COMMENT '活动简介',
  `content` mediumtext COMMENT '活动内容',
  `min_price_point` int(11) DEFAULT NULL COMMENT '活动价格区间下限',
  `max_price_point` int(11) DEFAULT NULL COMMENT '活动价格区间上限',
  `begin_date` datetime DEFAULT NULL COMMENT '活动开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '结束时间',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `cover_photo` varchar(255) DEFAULT NULL,
  `is_carousel` char(1) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone_num` varchar(255) DEFAULT NULL,
  `look_num` int(11) NOT NULL,
  `user_num` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES ('1', '1', '1', '1', '1', '1', null, null, null, null, null, '1528787016005.png', '0', '北京sad喀斯柯达', '10', '0', '1');
INSERT INTO `activity` VALUES ('2', '2', '1', '1', '1', '<p>1</p>', '1', '1', null, null, null, '1528787016005.png', '0', '北京sad喀斯柯达', '10', '0', '2');
INSERT INTO `activity` VALUES ('3', '1', '1', '1', '1', '<p>11</p>', '11', '11', null, null, null, '1528787016005.png', '0', '北京sad喀斯柯达', '10', '0', '3');
INSERT INTO `activity` VALUES ('4', '1', '1', '1', '1', '<p>1</p>', '1', '1', '2014-01-01 00:00:00', '2014-01-01 00:00:00', '2018-06-05 16:32:25', '1528787016005.png', '1', '北京sad喀斯柯达', '10', '0', '412');
INSERT INTO `activity` VALUES ('5', '1', '1', '1', '1', '<p>1</p>', '1', '1', '2018-06-05 16:39:34', '2018-06-05 16:39:36', '2018-06-05 16:39:39', '1528787016005.png', '1', '北京sad喀斯柯达', '10', '0', '2');
INSERT INTO `activity` VALUES ('6', '1', '111', '111', '11', '<p>sdsadsd</p>', '11', '121', '2018-06-12 11:09:26', '2018-06-12 11:09:28', '2018-06-12 11:09:33', '1528787016005.png', '1', '北京sad喀斯柯达', '10', '0', '312');
INSERT INTO `activity` VALUES ('7', '1', '11', '11', '111', '<p>111</p>', '11', '11', '2018-06-12 11:13:56', '2018-06-12 11:13:58', '2018-06-12 11:14:02', '1528787016005.png', '0', '北京sad喀斯柯达', '10', '0', '233');
INSERT INTO `activity` VALUES ('8', '2', '111', '111', '111', '<p>22222</p>', '11', '11', '2018-06-12 11:16:37', '2018-06-12 11:16:39', '2018-06-12 11:16:44', '1528787016005.png', '0', '北京sad喀斯柯达', '10', '0', '2');
INSERT INTO `activity` VALUES ('9', '2', '11', '11', '11', '<p>111</p>', '11', '11', '2018-06-12 11:17:29', '2018-06-12 11:17:31', '2018-06-12 11:17:34', '1528787016005.png', '0', '北京sad喀斯柯达', '10', '0', '2322');
INSERT INTO `activity` VALUES ('10', '2', '1111', '111', '11', '<p>111</p>', '11', '11', '2018-06-12 15:07:14', '2018-06-12 15:07:16', '2018-06-12 15:07:19', '1528787016005.png', '0', '北京sad喀斯柯达', '10', '0', '32');

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('2');

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `id` int(11) NOT NULL COMMENT '记录id',
  `activityId` int(11) DEFAULT NULL COMMENT '活动id',
  `staffId` varchar(255) DEFAULT NULL COMMENT '人员id (参与此次活动的多个逗号分隔)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of record
-- ----------------------------

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
  `id` int(11) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `graduated_school` varchar(255) DEFAULT NULL,
  `height` double NOT NULL,
  `hobbies` varchar(255) DEFAULT NULL,
  `introduction` varchar(255) DEFAULT NULL,
  `introduction_photo` varchar(255) DEFAULT NULL,
  `measurements` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` char(1) NOT NULL,
  `sex` char(1) NOT NULL,
  `weight` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES ('1', '12', 'https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=870145516,3961246635&fm=173&app=25&f=JPEG?w=218&h=146&s=2B75138B00038AF054DD3F870300F006', '2012-01-01', '111', '11', '11', '111', '111', '11', '1111', 'admin', '670b14728ad9902aecba32e22fa4f6bd', 'a', 'm', '123');
