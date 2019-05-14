/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : scs

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2019-05-14 16:34:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for biz_networkrecord
-- ----------------------------
DROP TABLE IF EXISTS `biz_networkrecord`;
CREATE TABLE `biz_networkrecord` (
  `record_id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `group_id` varchar(32) DEFAULT '' COMMENT '组id',
  `station_id` varchar(50) DEFAULT NULL COMMENT '站点编号',
  `state` varchar(8) DEFAULT NULL COMMENT '状态',
  `counter` int(1) DEFAULT '0' COMMENT '计数器',
  `restart_counter` int(1) DEFAULT '0' COMMENT '工控机重启计数器',
  `restart_flag` int(1) DEFAULT '0' COMMENT '重启定时次数',
  `at` bigint(20) DEFAULT NULL COMMENT '时间戳',
  `content` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
