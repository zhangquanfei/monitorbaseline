/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.7.17-log : Database - db_ssm
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_ssm` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_ssm`;

/*Table structure for table `board` */

DROP TABLE IF EXISTS `board`;

CREATE TABLE `board` (
  `username` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `usertime` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `checkoutdata` */

DROP TABLE IF EXISTS `checkoutdata`;

CREATE TABLE `checkoutdata` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ip` varchar(200) DEFAULT NULL,
  `DATA` varchar(21000) DEFAULT NULL,
  `TIME` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `copyfile` */

DROP TABLE IF EXISTS `copyfile`;

CREATE TABLE `copyfile` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ip` varchar(200) DEFAULT NULL,
  `originaldir` varchar(200) DEFAULT NULL,
  `copydir` varchar(200) DEFAULT NULL,
  `TIME` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;

/*Table structure for table `eventdata` */

DROP TABLE IF EXISTS `eventdata`;

CREATE TABLE `eventdata` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ip` varchar(200) DEFAULT NULL,
  `EVENT` varchar(200) DEFAULT NULL,
  `direction` varchar(200) DEFAULT NULL,
  `TIME` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Table structure for table `fei` */

DROP TABLE IF EXISTS `fei`;

CREATE TABLE `fei` (
  `name` varchar(200) DEFAULT NULL,
  `age` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `md5data` */

DROP TABLE IF EXISTS `md5data`;

CREATE TABLE `md5data` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ip` varchar(200) DEFAULT NULL,
  `direction` varchar(200) DEFAULT NULL,
  `data` varchar(200) DEFAULT NULL,
  `masterdir` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=532 DEFAULT CHARSET=utf8;

/*Table structure for table `monitor` */

DROP TABLE IF EXISTS `monitor`;

CREATE TABLE `monitor` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `directory` varchar(200) DEFAULT NULL,
  `event` varchar(200) DEFAULT NULL,
  `exclude` varchar(200) DEFAULT NULL,
  `thread` varchar(200) DEFAULT NULL,
  `ip` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Table structure for table `nifidata` */

DROP TABLE IF EXISTS `nifidata`;

CREATE TABLE `nifidata` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `data1` float DEFAULT NULL,
  `data2` int(11) DEFAULT NULL,
  `data3` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `user_phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `user_email` varchar(255) DEFAULT NULL COMMENT '邮箱地址',
  `user_pwd` varchar(32) DEFAULT NULL COMMENT '加盐后用户密码',
  `pwd_salt` varchar(6) DEFAULT NULL COMMENT 'MD5盐',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `is_delete` tinyint(4) DEFAULT NULL COMMENT '是否删除，0-未删除；1-已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户登录表';

/*Table structure for table `username_password` */

DROP TABLE IF EXISTS `username_password`;

CREATE TABLE `username_password` (
  `username` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  PRIMARY KEY (`username`,`password`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `zookeeperdata` */

DROP TABLE IF EXISTS `zookeeperdata`;

CREATE TABLE `zookeeperdata` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ip` varchar(200) DEFAULT NULL,
  `DATA` varchar(21000) DEFAULT NULL,
  `TIME` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
