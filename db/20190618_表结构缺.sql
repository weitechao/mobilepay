-- MySQL dump 10.14  Distrib 5.5.63-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: xmlpei
-- ------------------------------------------------------
-- Server version	5.5.63-MariaDB-1ubuntu0.14.04.1

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
-- Table structure for table `add_friend`
--

DROP TABLE IF EXISTS `add_friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `add_friend` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `imei` varchar(50) NOT NULL,
  `m_imei` varchar(50) DEFAULT NULL,
  `update_time` varchar(30) DEFAULT NULL,
  `belong_project` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `address_common`
--

DROP TABLE IF EXISTS `address_common`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address_common` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `smdid` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `belong_project` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `api_applog`
--

DROP TABLE IF EXISTS `api_applog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `api_applog` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `req` text,
  `resp` text,
  `imei` varchar(128) DEFAULT NULL,
  `rstatus` int(4) NOT NULL DEFAULT '0',
  `rmsg` text,
  `time` int(11) NOT NULL DEFAULT '0',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `appuserinfo`
--

DROP TABLE IF EXISTS `appuserinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appuserinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `nick_name` varchar(50) DEFAULT NULL,
  `age` varchar(50) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` char(1) DEFAULT '1',
  `lastlogin_time` datetime DEFAULT NULL,
  `head` varchar(200) DEFAULT NULL,
  `height` int(3) DEFAULT NULL,
  `weight` int(3) DEFAULT NULL,
  `belong_project` int(11) DEFAULT NULL COMMENT '所属哪个项目',
  `bind_count` varchar(11) DEFAULT '0',
  `prefix` char(2) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `is_binding` int(2) DEFAULT '0',
  `country_code` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=243 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `business_error1_cx_info`
--

DROP TABLE IF EXISTS `business_error1_cx_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `business_error1_cx_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(128) NOT NULL,
  `order_id` varchar(128) NOT NULL,
  `charge_acct` varchar(11) NOT NULL,
  `charge_cash` int(11) NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `error_code` int(5) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `ret_url` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `business_error2_cx_info`
--

DROP TABLE IF EXISTS `business_error2_cx_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `business_error2_cx_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(128) NOT NULL,
  `order_id` varchar(128) NOT NULL,
  `charge_acct` varchar(11) NOT NULL,
  `charge_cash` int(11) NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `error_code` int(5) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `ret_url` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `business_error3_cx_info`
--

DROP TABLE IF EXISTS `business_error3_cx_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `business_error3_cx_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(128) NOT NULL,
  `order_id` varchar(128) NOT NULL,
  `charge_acct` varchar(11) NOT NULL,
  `charge_cash` int(11) NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `error_code` int(5) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `ret_url` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `business_error_cx_info`
--

DROP TABLE IF EXISTS `business_error_cx_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `business_error_cx_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(128) NOT NULL,
  `order_id` varchar(128) NOT NULL,
  `charge_acct` varchar(11) NOT NULL,
  `charge_cash` int(11) NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `error_code` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `business_recharge_cx_info`
--

DROP TABLE IF EXISTS `business_recharge_cx_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `business_recharge_cx_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(128) NOT NULL,
  `order_id` varchar(128) NOT NULL,
  `charge_acct` varchar(11) NOT NULL,
  `charge_cash` int(11) NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `error_code` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `business_recharge_record_info`
--

DROP TABLE IF EXISTS `business_recharge_record_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `business_recharge_record_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(128) NOT NULL,
  `order_id` varchar(128) NOT NULL,
  `charge_acct` varchar(11) NOT NULL,
  `charge_cash` int(5) NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `business_user_info`
--

DROP TABLE IF EXISTS `business_user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `business_user_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(128) NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `avatar` text,
  `nickname` varchar(128) DEFAULT NULL,
  `use_status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '1有效',
  `balance` int(11) NOT NULL DEFAULT '0',
  `updatetime` timestamp NULL DEFAULT NULL,
  `shangyou_type` int(11) DEFAULT NULL,
  `shangyou_content` varchar(128) DEFAULT NULL,
  `scret` varchar(128) DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `buy_card_info`
--

DROP TABLE IF EXISTS `buy_card_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buy_card_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(128) DEFAULT '1',
  `beizhu` varchar(128) DEFAULT NULL,
  `add_blance` int(7) NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `url` varchar(256) DEFAULT NULL COMMENT '凭证',
  `status` char(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `call_info`
--

DROP TABLE IF EXISTS `call_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `call_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `imei` varchar(50) DEFAULT NULL,
  `nick_name` varchar(50) DEFAULT NULL,
  `type` char(2) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `use_time` varchar(20) DEFAULT NULL,
  `call_time` varchar(50) DEFAULT NULL,
  `b_g` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `channelinfo`
--

DROP TABLE IF EXISTS `channelinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `channelinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `channel_no` varchar(20) DEFAULT '' COMMENT '渠道编号',
  `channel_name` varchar(50) DEFAULT '' COMMENT '渠道名称',
  `company_id` int(11) DEFAULT NULL COMMENT '公司ID',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `remark` text COMMENT '备注',
  `status` char(1) DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `check_code_info`
--

DROP TABLE IF EXISTS `check_code_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `check_code_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ID_code` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `checkinfo`
--

DROP TABLE IF EXISTS `checkinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `checkinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `package_name` varchar(50) NOT NULL,
  `version_name` varchar(50) NOT NULL,
  `version_code` varchar(50) NOT NULL,
  `download_path` varchar(200) NOT NULL,
  `function_cap` text,
  `belong_project` int(11) DEFAULT NULL,
  `up_user` varchar(50) DEFAULT NULL,
  `ID_code_id` varchar(11) DEFAULT NULL COMMENT '识别码表中id',
  `up_type` char(1) DEFAULT NULL COMMENT '0正常，1强制，2建议',
  `up_platform` char(1) DEFAULT NULL COMMENT '上传平台，0安卓',
  `status` char(1) DEFAULT NULL COMMENT '0禁用，1启用',
  `remarks` text,
  `version_type` char(1) DEFAULT NULL COMMENT '0内部，1外部',
  `upload_time` datetime DEFAULT NULL,
  `er_weima` char(1) DEFAULT NULL COMMENT '0无1有',
  `function_cape` varchar(200) DEFAULT NULL,
  `quan_xian` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `clock_info`
--

DROP TABLE IF EXISTS `clock_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clock_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `did` int(11) DEFAULT NULL COMMENT '设备id',
  `clock` varchar(15) DEFAULT NULL COMMENT '闹钟',
  `type` smallint(6) DEFAULT NULL COMMENT '类型',
  `remain_type` char(1) DEFAULT NULL COMMENT '提醒类型,0响铃,1震动,2两者都有',
  `statu` char(1) DEFAULT NULL COMMENT '0开,1关',
  `alarmId` varchar(32) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `repeatTimes` varchar(10) DEFAULT NULL,
  `vibrate` int(2) DEFAULT NULL,
  `ring` int(2) DEFAULT NULL,
  `ringId` int(11) DEFAULT NULL,
  `vol` int(11) DEFAULT NULL,
  `input_time` datetime DEFAULT NULL,
  `zu` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1017 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `company_server`
--

DROP TABLE IF EXISTS `company_server`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company_server` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(20) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `companyinfo`
--

DROP TABLE IF EXISTS `companyinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `companyinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL COMMENT '公司编号',
  `secret_key` varchar(100) DEFAULT NULL COMMENT '公司名称',
  `company_name` varchar(100) DEFAULT NULL COMMENT '渠道编号',
  `contain_type` varchar(100) DEFAULT NULL COMMENT '1移动2联通3电信',
  `returl` varchar(128) DEFAULT NULL COMMENT '1',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `remark` text COMMENT '备注',
  `status` char(1) DEFAULT '1' COMMENT '状态',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `data_switch_infomation`
--

DROP TABLE IF EXISTS `data_switch_infomation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_switch_infomation` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(50) NOT NULL,
  `location_switch` char(2) NOT NULL COMMENT '0关1开',
  `beizhu` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `device_active_history`
--

DROP TABLE IF EXISTS `device_active_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device_active_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serie_no` varchar(50) DEFAULT NULL,
  `belong_project` int(11) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT '',
  `user_id` varchar(11) DEFAULT '',
  `nick_name` varchar(50) DEFAULT '',
  `status` char(1) DEFAULT '',
  `date_time` datetime DEFAULT NULL,
  `unbind_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1889 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `device_active_info`
--

DROP TABLE IF EXISTS `device_active_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device_active_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_phone` varchar(50) NOT NULL DEFAULT '0' COMMENT '设备电话号码',
  `device_imei` varchar(20) NOT NULL DEFAULT '000000000000000' COMMENT '设备的imei号',
  `device_update_time` datetime DEFAULT NULL COMMENT '设备上传时间',
  `device_disable` varchar(2) DEFAULT '1' COMMENT '设备是否禁用,0表示禁用,1(默认)表示不禁用',
  `device_name` varchar(10) NOT NULL DEFAULT '0' COMMENT '设备名称',
  `user_id` varchar(11) NOT NULL DEFAULT '0' COMMENT '关联的用户id',
  `device_head` varchar(225) DEFAULT '0' COMMENT '设备头像地址',
  `device_sex` varchar(2) NOT NULL DEFAULT '0' COMMENT '设备性别,0男1女',
  `device_age` varchar(20) NOT NULL DEFAULT '2015-12-12 23:59:59' COMMENT '设备年龄,默认6岁',
  `device_height` varchar(5) NOT NULL DEFAULT '170' COMMENT '设备身高,默认170',
  `device_weight` varchar(5) NOT NULL DEFAULT '100' COMMENT '设备体重',
  `listen_type` char(1) DEFAULT '0',
  `count` varchar(11) DEFAULT '1',
  `belong_project` int(11) DEFAULT NULL COMMENT '所属哪个项目ID',
  `firm` varchar(50) DEFAULT NULL COMMENT '固件版本号',
  `first` varchar(50) NOT NULL DEFAULT '2015-09-18 00:00:00' COMMENT '首次激活时间',
  `device_status` char(1) DEFAULT '1',
  `short_number` char(9) DEFAULT NULL COMMENT '短号',
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `phsaddress` varchar(50) DEFAULT NULL,
  `phsname` varchar(50) DEFAULT NULL,
  `phsphone` varchar(50) DEFAULT NULL,
  `allergies` varchar(50) DEFAULT NULL,
  `addnotes` varchar(50) DEFAULT NULL,
  `balance` varchar(50) DEFAULT '0' COMMENT '公交余额',
  `step` varchar(50) DEFAULT '10000',
  `close_status` char(2) DEFAULT '2' COMMENT '2是关闭1打开',
  `dianL` varchar(20) DEFAULT NULL,
  `active_device` char(2) DEFAULT '0',
  `run_model` varchar(10) DEFAULT NULL,
  `show_first` char(2) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=268 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `device_login`
--

DROP TABLE IF EXISTS `device_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device_login` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `device_imei` varchar(20) NOT NULL COMMENT '设备imei',
  `device_phone` varchar(20) DEFAULT NULL COMMENT '设别电话号码',
  `device_version` varchar(50) DEFAULT NULL COMMENT '固件版本',
  `device_status` char(1) NOT NULL DEFAULT '0' COMMENT '设备状态(0表示解绑空闲,1表示出厂,2表示绑定)',
  `date_time` datetime DEFAULT '2015-09-17 00:00:00' COMMENT '签到时间',
  `belong_project` int(11) DEFAULT NULL COMMENT '项目ID',
  `count_number` int(10) DEFAULT NULL,
  `major_version` varchar(20) DEFAULT NULL,
  `minor_version` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32796 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `device_phone_info`
--

DROP TABLE IF EXISTS `device_phone_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device_phone_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_phone` varchar(15) NOT NULL,
  `device_imsi` varchar(20) DEFAULT NULL,
  `device_name` varchar(10) NOT NULL,
  `belong_project` int(11) DEFAULT NULL,
  `firm` varchar(50) DEFAULT NULL,
  `country` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `directive`
--

DROP TABLE IF EXISTS `directive`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `directive` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serie_no` varchar(50) NOT NULL,
  `distrub` char(1) DEFAULT '0',
  `mdistime` varchar(200) DEFAULT NULL,
  `tdistime` varchar(200) DEFAULT NULL,
  `wdistime` varchar(200) DEFAULT NULL,
  `lowelectricity` varchar(3) DEFAULT NULL,
  `isLow` char(1) DEFAULT '0',
  `clock` varchar(255) DEFAULT NULL,
  `sleep` varchar(255) DEFAULT NULL,
  `thdistime` varchar(200) DEFAULT NULL,
  `fdistime` varchar(200) DEFAULT NULL,
  `sdistime` varchar(200) DEFAULT NULL,
  `sudistime` varchar(200) DEFAULT NULL,
  `distrubChange` char(1) DEFAULT '0',
  `alarmChange` char(1) DEFAULT '0',
  `sleepChange` char(1) DEFAULT '0',
  `belong_project` int(11) DEFAULT NULL COMMENT '所属哪个项目',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `disturb_info`
--

DROP TABLE IF EXISTS `disturb_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `disturb_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `deviceId` int(20) DEFAULT NULL,
  `serieNo` varchar(20) DEFAULT NULL,
  `repeat` varchar(7) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dynamic_menu`
--

DROP TABLE IF EXISTS `dynamic_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dynamic_menu` (
  `id` int(22) NOT NULL AUTO_INCREMENT,
  `nc_name` varchar(100) DEFAULT NULL COMMENT '昵称',
  `mName` varchar(100) DEFAULT NULL,
  `superId` varchar(50) DEFAULT NULL COMMENT '父',
  `menuLeave` varchar(25) DEFAULT NULL COMMENT '菜单级别',
  `menuRank` varchar(10) DEFAULT NULL COMMENT '菜单排序',
  `submenuNumber` int(20) DEFAULT NULL COMMENT '子菜单个数',
  `effect` char(1) DEFAULT NULL COMMENT '是否生效',
  `add_time` datetime DEFAULT NULL COMMENT '录入时间',
  `m_describe` varchar(200) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dynamic_menu_copy`
--

DROP TABLE IF EXISTS `dynamic_menu_copy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dynamic_menu_copy` (
  `id` int(22) NOT NULL AUTO_INCREMENT,
  `nc_name` varchar(100) DEFAULT NULL COMMENT '昵称',
  `mName` varchar(100) DEFAULT NULL,
  `superId` int(20) DEFAULT NULL COMMENT '父',
  `menuLeave` varchar(25) DEFAULT NULL COMMENT '菜单级别',
  `menuRank` varchar(10) DEFAULT NULL COMMENT '菜单排序',
  `submenuNumber` int(20) DEFAULT NULL COMMENT '子菜单个数',
  `effect` char(1) DEFAULT NULL COMMENT '是否生效',
  `add_time` datetime DEFAULT NULL COMMENT '录入时间',
  `m_describe` varchar(200) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `environment_info`
--

DROP TABLE IF EXISTS `environment_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `environment_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_id` varchar(20) DEFAULT NULL,
  `power` varchar(2) DEFAULT NULL,
  `cur_temp` varchar(20) DEFAULT NULL,
  `target_temp` varchar(20) DEFAULT NULL,
  `temp_unit` varchar(10) DEFAULT NULL,
  `humidity` varchar(20) DEFAULT NULL,
  `run_model` varchar(20) DEFAULT NULL,
  `wind_speed` varchar(20) DEFAULT NULL,
  `value` varchar(20) DEFAULT NULL,
  `err_code` varchar(20) DEFAULT NULL,
  `pipe_type` varchar(20) DEFAULT NULL,
  `smart_aod` varchar(20) DEFAULT NULL,
  `in_air_quality` varchar(20) DEFAULT NULL,
  `auto_clean` varchar(20) DEFAULT NULL,
  `so2` varchar(20) DEFAULT NULL,
  `no2` varchar(20) DEFAULT NULL,
  `co` varchar(20) DEFAULT NULL,
  `co2` varchar(20) DEFAULT NULL,
  `nh3` varchar(20) DEFAULT NULL,
  `o3` varchar(20) DEFAULT NULL,
  `ch2o` varchar(20) DEFAULT NULL,
  `c6h6` varchar(20) DEFAULT NULL,
  `c7h8` varchar(20) DEFAULT NULL,
  `c8h10` varchar(20) DEFAULT NULL,
  `bap` varchar(20) DEFAULT NULL,
  `pm10d0` varchar(20) DEFAULT NULL,
  `pm2d5` varchar(20) DEFAULT NULL,
  `tvoc` varchar(20) DEFAULT NULL,
  `rn222` varchar(20) DEFAULT NULL,
  `app_version` varchar(20) DEFAULT NULL,
  `firm_ver` varchar(20) DEFAULT NULL,
  `date_time` datetime DEFAULT NULL,
  `type` char(2) DEFAULT NULL,
  `inair_data` text,
  `all_info` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `feedbackinfo`
--

DROP TABLE IF EXISTS `feedbackinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedbackinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(11) NOT NULL DEFAULT '',
  `user_feedback_content` text NOT NULL,
  `date_time` datetime NOT NULL,
  `belong_project` int(11) DEFAULT NULL COMMENT '所属哪个项目',
  `feedback_status` char(5) DEFAULT '0',
  `processing_content` varbinary(200) DEFAULT NULL,
  `processing_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `firm_device`
--

DROP TABLE IF EXISTS `firm_device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `firm_device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version_name` varchar(50) DEFAULT NULL,
  `version_code` varchar(50) DEFAULT NULL,
  `function_cap` text,
  `upload_time` datetime DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `belong_project` int(11) DEFAULT NULL,
  `package_name` varchar(50) DEFAULT NULL,
  `download_path` varchar(200) DEFAULT NULL,
  `remarks` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `funcinfo`
--

DROP TABLE IF EXISTS `funcinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcinfo` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `funcCode` varchar(50) DEFAULT NULL,
  `funcName` varchar(50) NOT NULL,
  `funcDesc` varchar(50) DEFAULT NULL,
  `superCode` varchar(20) DEFAULT NULL,
  `levels` int(2) NOT NULL,
  `funcSort` int(2) NOT NULL,
  `statu` varchar(2) NOT NULL,
  `funcDo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=192 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `heart_rate_info`
--

DROP TABLE IF EXISTS `heart_rate_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `heart_rate_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `imei` varchar(20) DEFAULT NULL,
  `heart_rate` varchar(10) DEFAULT NULL,
  `time` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `huoyue_info`
--

DROP TABLE IF EXISTS `huoyue_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `huoyue_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) DEFAULT NULL,
  `lianjie` varchar(100) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `socket_name` varchar(20) DEFAULT NULL,
  `lian_key` varchar(50) DEFAULT NULL,
  `b_g` varchar(20) DEFAULT NULL,
  `huoyue` varchar(20) DEFAULT NULL,
  `liang` varchar(20) DEFAULT NULL,
  `xingneng` varchar(20) DEFAULT NULL,
  `client_ip` varchar(20) DEFAULT NULL,
  `client_port` varchar(20) DEFAULT NULL,
  `dianL` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60022 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `locationinfo`
--

DROP TABLE IF EXISTS `locationinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `locationinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serie_no` varchar(50) DEFAULT NULL,
  `battery` int(3) DEFAULT NULL,
  `longitude` varchar(50) DEFAULT NULL,
  `latitude` varchar(50) DEFAULT NULL,
  `location_type` char(1) DEFAULT NULL COMMENT '1GPS0基站',
  `accuracy` int(5) DEFAULT NULL,
  `upload_time` datetime DEFAULT NULL,
  `change_longitude` varchar(50) DEFAULT NULL,
  `change_latitude` varchar(50) DEFAULT NULL,
  `belong_project` int(11) DEFAULT NULL,
  `s_t` char(1) DEFAULT '1',
  `e_t` datetime DEFAULT NULL,
  `fall` char(1) DEFAULT '0',
  `step_no` int(11) DEFAULT '0',
  `roll_no` int(11) DEFAULT '0',
  `t_alarm` int(11) DEFAULT '0',
  `t_status` int(11) DEFAULT '0',
  `cause` varchar(50) DEFAULT NULL,
  `map` varchar(50) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `radius` int(11) DEFAULT NULL,
  `imei` varchar(50) DEFAULT NULL,
  `gps_lng` varchar(50) DEFAULT NULL,
  `gps_lat` varchar(50) DEFAULT NULL,
  `mcc` varchar(50) DEFAULT NULL,
  `driver_status` char(2) DEFAULT '0',
  `driver_count` int(10) DEFAULT '1',
  `altitude` varchar(50) DEFAULT NULL,
  `satellite` varchar(50) DEFAULT NULL,
  `speed` varchar(50) DEFAULT NULL,
  `cell_id` varchar(50) DEFAULT NULL,
  `is_gps` varchar(50) DEFAULT NULL,
  `angle` varchar(50) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `device` varchar(50) DEFAULT NULL,
  `sport_type` char(2) DEFAULT NULL,
  `lat_q` varchar(10) DEFAULT NULL,
  `lon_q` varchar(10) DEFAULT NULL,
  `wifi_info` text,
  `lbs_info` text,
  `cityCode` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `locationinfo_backup`
--

DROP TABLE IF EXISTS `locationinfo_backup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `locationinfo_backup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serie_no` varchar(50) DEFAULT NULL,
  `battery` int(3) DEFAULT NULL,
  `longitude` varchar(50) DEFAULT NULL,
  `latitude` varchar(50) DEFAULT NULL,
  `location_type` char(1) DEFAULT NULL,
  `accuracy` int(5) DEFAULT NULL,
  `upload_time` datetime DEFAULT NULL,
  `change_longitude` varchar(50) DEFAULT NULL,
  `change_latitude` varchar(50) DEFAULT NULL,
  `belong_project` int(11) DEFAULT NULL,
  `s_t` char(1) DEFAULT NULL,
  `e_t` datetime DEFAULT '1970-01-01 00:00:00',
  `fall` char(1) DEFAULT NULL,
  `step_no` int(11) DEFAULT NULL,
  `roll_no` int(11) DEFAULT NULL,
  `t_alarm` int(11) DEFAULT NULL,
  `t_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1170334 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mediainfo`
--

DROP TABLE IF EXISTS `mediainfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mediainfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `msg_content` varchar(200) DEFAULT NULL,
  `from_id` varchar(20) DEFAULT NULL,
  `to_id` varchar(20) DEFAULT NULL,
  `send_type` char(1) DEFAULT '0',
  `send_time` datetime DEFAULT NULL,
  `status` char(1) DEFAULT '0',
  `time_length` varchar(11) DEFAULT '0',
  `belong_project` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `monitorinfo`
--

DROP TABLE IF EXISTS `monitorinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monitorinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `cost_time` int(11) NOT NULL,
  `function` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `function_href` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reason` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31944 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `monitorinfo_beifen`
--

DROP TABLE IF EXISTS `monitorinfo_beifen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monitorinfo_beifen` (
  `id` int(11) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `cost_time` int(11) DEFAULT NULL,
  `function` varchar(765) DEFAULT NULL,
  `function_href` varchar(765) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `msg_info`
--

DROP TABLE IF EXISTS `msg_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `msg_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `msg_level` char(1) DEFAULT '0' COMMENT '消息级别',
  `from_id` varchar(20) DEFAULT '0' COMMENT '哪个用户发出的id',
  `to_id` varchar(20) DEFAULT '0' COMMENT '给哪个用户发',
  `is_handler` char(1) DEFAULT '0' COMMENT '默认没有处理,1表示处理',
  `msg_content` text COMMENT '消息内容',
  `msg_handler_date` datetime DEFAULT '2015-01-01 00:00:00' COMMENT '消息处理时间',
  `belong_project` int(11) DEFAULT NULL COMMENT '所属哪个项目',
  `msg_occur_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `order_error_info`
--

DROP TABLE IF EXISTS `order_error_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_error_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(128) NOT NULL,
  `order_id` varchar(128) NOT NULL,
  `order_statu_int` int(5) NOT NULL,
  `order_statu_text` varchar(128) DEFAULT '' COMMENT '1有效',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `phone_charge_info`
--

DROP TABLE IF EXISTS `phone_charge_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phone_charge_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `device_phone` varchar(20) DEFAULT NULL,
  `device_imei` varchar(20) DEFAULT NULL,
  `device_update_time` datetime DEFAULT NULL,
  `huafei` varchar(10) DEFAULT NULL,
  `liuliang` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `phone_country_info`
--

DROP TABLE IF EXISTS `phone_country_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phone_country_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `MCC` varchar(5) NOT NULL,
  `ISO` varchar(5) NOT NULL,
  `Country` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `phone_manage`
--

DROP TABLE IF EXISTS `phone_manage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phone_manage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `input_time` datetime DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `count_num` varchar(20) DEFAULT NULL,
  `mini_num` varchar(20) DEFAULT NULL,
  `max_num` varchar(20) DEFAULT NULL,
  `type` char(1) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `phoneinfo`
--

DROP TABLE IF EXISTS `phoneinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phoneinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serie_no` varchar(50) DEFAULT NULL,
  `product_model` varchar(50) DEFAULT NULL,
  `firmware_edition` varchar(100) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `alarm_bell_type` char(1) DEFAULT '',
  `input_time` datetime DEFAULT NULL,
  `upload_time` datetime DEFAULT NULL,
  `status` char(1) DEFAULT '0',
  `shutdown` char(1) DEFAULT '0',
  `repellent` char(1) DEFAULT '0',
  `heart` varchar(5) DEFAULT '0',
  `belong_project` int(11) DEFAULT NULL COMMENT '所属哪个项目',
  `phone_manage_id` int(11) DEFAULT '1',
  `relative_call_status` char(1) DEFAULT '0',
  `devicePort` varchar(10) DEFAULT NULL,
  `centerNum` varchar(20) DEFAULT NULL,
  `assistantNum` varchar(20) DEFAULT NULL,
  `reporteTime` double DEFAULT NULL,
  `deviceLanguage` varchar(20) DEFAULT NULL,
  `timeZone` varchar(30) DEFAULT NULL,
  `satelliteNum` int(11) DEFAULT NULL,
  `GSMSignal` float DEFAULT NULL,
  `LEDSwitch` varchar(30) DEFAULT NULL,
  `short_number` varchar(9) DEFAULT NULL COMMENT '短号',
  `dianL` varchar(20) DEFAULT NULL,
  `on_line` varchar(20) DEFAULT NULL,
  `ack_num` varchar(20) DEFAULT NULL,
  `feng_su` varchar(20) DEFAULT NULL,
  `wen_du` varchar(20) DEFAULT NULL,
  `shi_du` varchar(20) DEFAULT NULL,
  `run_model` varchar(20) DEFAULT NULL,
  `energy_fangshi` varchar(20) DEFAULT NULL,
  `seq_num` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `photo_info`
--

DROP TABLE IF EXISTS `photo_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `photo_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL,
  `photo_href` varchar(200) DEFAULT NULL,
  `nick_name` varchar(20) DEFAULT NULL,
  `photo_name` varchar(20) DEFAULT NULL,
  `shuo_ming` varchar(200) DEFAULT NULL,
  `belong_project` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `head2` varchar(200) DEFAULT NULL,
  `head3` varchar(200) DEFAULT NULL,
  `head4` varchar(200) DEFAULT NULL,
  `head5` varchar(200) DEFAULT NULL,
  `head6` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `playiteminfo`
--

DROP TABLE IF EXISTS `playiteminfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playiteminfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serie_no` varchar(50) DEFAULT NULL,
  `product_model` varchar(50) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `type` char(1) DEFAULT NULL,
  `control_type` char(1) DEFAULT NULL,
  `belong_project` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `project_userinfo`
--

DROP TABLE IF EXISTS `project_userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_no` varchar(200) DEFAULT '',
  `project_name` varchar(200) DEFAULT '',
  `channel_id` int(11) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `remark` text,
  `status` char(1) DEFAULT '1',
  `need` char(1) DEFAULT NULL,
  `heart_s` varchar(200) DEFAULT NULL,
  `map_typec` int(1) DEFAULT '0',
  `map_typeo` int(1) DEFAULT '0',
  `project_type` char(1) DEFAULT '3',
  `data_sourcec` int(1) DEFAULT '0',
  `data_sourceo` int(1) DEFAULT '0',
  `socket_way` int(1) DEFAULT '0',
  `adTitle` varchar(200) DEFAULT NULL,
  `adDetail` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `projectimage`
--

DROP TABLE IF EXISTS `projectimage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projectimage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectId` int(11) DEFAULT NULL,
  `downloadpath1` varchar(200) DEFAULT NULL,
  `downloadpath2` varchar(200) DEFAULT NULL,
  `downloadpath3` varchar(200) DEFAULT NULL,
  `downloadpath4` varchar(200) DEFAULT NULL,
  `downloadpath5` varchar(200) DEFAULT NULL,
  `downloadstatus` char(1) DEFAULT '0',
  `hideline` char(1) DEFAULT '1',
  `update_time` datetime DEFAULT NULL,
  `shuo_ming` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `projectinfo`
--

DROP TABLE IF EXISTS `projectinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projectinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_no` varchar(200) DEFAULT '',
  `project_name` varchar(200) DEFAULT '',
  `channel_id` varchar(200) DEFAULT NULL,
  `company_id` varchar(200) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `remark` text,
  `status` char(1) DEFAULT '1',
  `need` char(1) DEFAULT NULL,
  `heart_s` varchar(200) DEFAULT NULL,
  `map_typec` int(1) DEFAULT '0',
  `map_typeo` int(1) DEFAULT '0',
  `project_type` char(1) DEFAULT '3',
  `data_sourcec` int(1) DEFAULT '0',
  `data_sourceo` int(1) DEFAULT '0',
  `socket_way` int(1) DEFAULT '0',
  `adTitle` varchar(200) DEFAULT NULL,
  `adDetail` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `projectinfo_bf`
--

DROP TABLE IF EXISTS `projectinfo_bf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projectinfo_bf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_no` varchar(20) DEFAULT '',
  `project_name` varchar(50) DEFAULT '',
  `channel_id` int(11) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  `status` char(1) DEFAULT '1',
  `add_time` datetime DEFAULT NULL,
  `remark` text,
  `need` char(1) DEFAULT NULL,
  `heart_s` varchar(50) DEFAULT NULL,
  `map_typec` int(1) DEFAULT '0',
  `map_typeo` int(1) DEFAULT '0',
  `project_type` char(1) DEFAULT '3',
  `data_sourcec` int(1) DEFAULT '0',
  `data_sourceo` int(1) DEFAULT '0',
  `socket_way` int(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `protectinfo`
--

DROP TABLE IF EXISTS `protectinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `protectinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(50) DEFAULT NULL,
  `project_status` char(1) DEFAULT NULL COMMENT '0关1开',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `relativecallinfo`
--

DROP TABLE IF EXISTS `relativecallinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relativecallinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serie_no` varchar(50) DEFAULT NULL,
  `product_model` varchar(50) DEFAULT NULL,
  `phone_number` varchar(11) DEFAULT NULL,
  `relative_type` char(1) DEFAULT NULL,
  `nick_name` varchar(50) DEFAULT '',
  `status` char(1) DEFAULT '',
  `user_id` varchar(11) NOT NULL DEFAULT '0',
  `add_time` datetime DEFAULT NULL,
  `belong_project` int(11) DEFAULT NULL COMMENT '所属哪个项目',
  `short_number` varchar(6) DEFAULT '0',
  `icon` int(2) DEFAULT '0',
  `type` varchar(50) DEFAULT '0',
  `zu` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `return_success_info`
--

DROP TABLE IF EXISTS `return_success_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `return_success_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` varchar(128) NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rolefuncinfo`
--

DROP TABLE IF EXISTS `rolefuncinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rolefuncinfo` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `roleCode` varchar(25) NOT NULL,
  `funcCode` varchar(50) NOT NULL,
  `userCode` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=272 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roleinfo`
--

DROP TABLE IF EXISTS `roleinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roleinfo` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(25) NOT NULL,
  `roleDesc` varchar(50) DEFAULT NULL,
  `roleCode` varchar(25) NOT NULL,
  `roleType` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roleprojectfuncinfo`
--

DROP TABLE IF EXISTS `roleprojectfuncinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roleprojectfuncinfo` (
  `idP` int(20) NOT NULL AUTO_INCREMENT,
  `roleCodeP` varchar(50) DEFAULT NULL,
  `mName` varchar(50) DEFAULT NULL,
  `projectCode` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idP`)
) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `safe_area`
--

DROP TABLE IF EXISTS `safe_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `safe_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) DEFAULT '',
  `seri_no` varchar(20) DEFAULT NULL,
  `longitude` varchar(50) DEFAULT NULL,
  `latitude` varchar(50) DEFAULT NULL,
  `safe_range` int(50) DEFAULT NULL,
  `area_name` varchar(200) DEFAULT NULL,
  `area_effect_time` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `safe_address` varchar(120) DEFAULT '0',
  `status` char(2) DEFAULT '0',
  `belong_project` varchar(11) DEFAULT '0' COMMENT '所属哪个项目',
  `zu` varchar(20) DEFAULT NULL,
  `safe_type` varchar(20) DEFAULT NULL,
  `switch_status` varchar(20) DEFAULT NULL,
  `start_t` varchar(20) DEFAULT NULL,
  `end_t` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `saledevicelogin`
--

DROP TABLE IF EXISTS `saledevicelogin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `saledevicelogin` (
  `id` int(11) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `imei` varchar(20) DEFAULT NULL,
  `imsi` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `phone_model` varchar(50) DEFAULT NULL,
  `sys_version` varchar(50) DEFAULT NULL,
  `ip` varchar(50) DEFAULT NULL,
  `province` varchar(100) DEFAULT NULL,
  `belong_project` int(3) DEFAULT NULL,
  `date_time` datetime DEFAULT NULL,
  `app_version` varchar(50) DEFAULT NULL,
  `device_type` char(2) DEFAULT NULL,
  `server_version` varchar(50) DEFAULT NULL,
  `bt_times` int(10) DEFAULT NULL,
  `bt_name` varchar(50) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `saleinfo`
--

DROP TABLE IF EXISTS `saleinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `saleinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL,
  `imei` varchar(50) DEFAULT NULL,
  `imsi` varchar(15) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `phone_model` varchar(50) DEFAULT NULL,
  `sys_version` varchar(50) DEFAULT NULL,
  `province` varchar(50) DEFAULT NULL,
  `belong_project` int(11) DEFAULT NULL COMMENT '所属哪个项目',
  `date_time` datetime DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `app_version` varchar(50) DEFAULT NULL,
  `device_type` char(2) DEFAULT '0',
  `server_version` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `settinginfo`
--

DROP TABLE IF EXISTS `settinginfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `settinginfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serie_no` varchar(50) DEFAULT NULL,
  `volume` int(3) DEFAULT NULL,
  `map` char(1) DEFAULT NULL,
  `fallOn` char(1) DEFAULT NULL,
  `light` char(2) DEFAULT NULL,
  `gps_on` char(2) DEFAULT NULL,
  `light_sensor` char(1) DEFAULT NULL,
  `fall` char(1) DEFAULT NULL,
  `belong_project` int(11) DEFAULT NULL COMMENT '所属哪个项目',
  `shutdown` char(1) DEFAULT '0',
  `repellent` char(1) DEFAULT '0',
  `heart` varchar(5) DEFAULT '0',
  `seac` char(1) DEFAULT '0',
  `listen` char(1) DEFAULT '0',
  `rest` char(1) DEFAULT '0',
  `res` char(1) DEFAULT '0',
  `veri` varchar(50) DEFAULT '0',
  `listen_type` char(2) DEFAULT NULL,
  `high` varchar(50) DEFAULT '170',
  `weight` varchar(50) DEFAULT '45',
  `sex` char(1) DEFAULT '1',
  `stepd` varchar(50) DEFAULT '40',
  `b_g` varchar(10) DEFAULT NULL,
  `phone` varchar(50) DEFAULT '0',
  `nick_name` varchar(50) DEFAULT '0',
  `birthday` varchar(50) DEFAULT '2015-12-12',
  `auto_mute` char(1) DEFAULT NULL,
  `power_off` char(1) DEFAULT NULL,
  `is_off` char(1) DEFAULT '0',
  `type` char(1) DEFAULT '0',
  `levels` varchar(5) DEFAULT NULL,
  `degree` varchar(5) DEFAULT NULL,
  `device` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `share_info`
--

DROP TABLE IF EXISTS `share_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `share_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `to_user_id` varchar(20) NOT NULL DEFAULT '' COMMENT '要分享给哪个用户',
  `device_imei` varchar(20) NOT NULL DEFAULT '0' COMMENT '设备的imei',
  `is_priority` char(1) NOT NULL DEFAULT '1' COMMENT '1表示是主设备,0表示是分享的设备',
  `share_date` datetime NOT NULL DEFAULT '2015-01-01 00:00:00' COMMENT '分享的时间',
  `belong_project` int(11) DEFAULT NULL COMMENT '所属哪个项目',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `socket_info`
--

DROP TABLE IF EXISTS `socket_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `socket_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) DEFAULT NULL,
  `lianjie` varchar(100) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `socket_name` varchar(20) DEFAULT NULL,
  `lian_key` varchar(50) DEFAULT NULL,
  `b_g` varchar(20) DEFAULT NULL,
  `huoyue` varchar(5) DEFAULT NULL,
  `liang` varchar(20) DEFAULT NULL,
  `xingneng` varchar(20) DEFAULT NULL,
  `client_ip` varchar(20) DEFAULT NULL,
  `client_port` varchar(20) DEFAULT NULL,
  `dianL` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sport_info`
--

DROP TABLE IF EXISTS `sport_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sport_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `imei` varchar(20) DEFAULT NULL,
  `sport_type` char(2) DEFAULT NULL,
  `energy` varchar(10) DEFAULT NULL,
  `distance` varchar(20) DEFAULT NULL,
  `heart_rate` varchar(10) DEFAULT NULL,
  `step` varchar(20) DEFAULT NULL,
  `use_time` varchar(20) DEFAULT NULL,
  `upload_time` datetime DEFAULT NULL,
  `b_g` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `switch_info`
--

DROP TABLE IF EXISTS `switch_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `switch_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `visit_s` char(2) DEFAULT NULL,
  `moni_s` char(2) NOT NULL,
  `device_s` char(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sysloginfo`
--

DROP TABLE IF EXISTS `sysloginfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sysloginfo` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userName` varchar(25) NOT NULL,
  `logDate` datetime NOT NULL,
  `logs` varchar(1024) NOT NULL,
  `ip` varchar(15) DEFAULT NULL,
  `outDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2383 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sysloginfo_beifen`
--

DROP TABLE IF EXISTS `sysloginfo_beifen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sysloginfo_beifen` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userName` varchar(25) NOT NULL,
  `logDate` datetime NOT NULL DEFAULT '1800-00-00 00:00:00',
  `logs` varchar(1024) NOT NULL,
  `ip` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `talk_group`
--

DROP TABLE IF EXISTS `talk_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `talk_group` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `device_imei` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_bind_device`
--

DROP TABLE IF EXISTS `user_bind_device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_bind_device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(100) DEFAULT NULL,
  `device_id` varchar(100) DEFAULT NULL,
  `bind_time` datetime DEFAULT NULL,
  `priorty` varchar(20) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `userinfo`
--

DROP TABLE IF EXISTS `userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userinfo` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userCode` varchar(25) NOT NULL DEFAULT '',
  `userName` varchar(50) NOT NULL,
  `passWrd` varchar(50) NOT NULL,
  `passWrd1` varchar(50) NOT NULL,
  `tag` int(2) NOT NULL DEFAULT '0',
  `createDate` datetime NOT NULL DEFAULT '1800-00-00 00:00:00',
  `updateDate` datetime DEFAULT '1800-00-00 00:00:00',
  `groupCode` varchar(11) NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `addUser` varchar(20) DEFAULT NULL,
  `phoneNo` varchar(18) DEFAULT '',
  `apply_status` char(1) DEFAULT '0',
  `company_id` varchar(200) DEFAULT '0',
  `isInApply` char(1) DEFAULT '1',
  `applyReason` varchar(200) DEFAULT '',
  `project_id` varchar(200) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `userlogininfo`
--

DROP TABLE IF EXISTS `userlogininfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userlogininfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `imei` varchar(20) DEFAULT '',
  `imsi` varchar(20) DEFAULT '',
  `phone_model` varchar(50) DEFAULT '',
  `phone_version` varchar(50) DEFAULT '',
  `app_version` varchar(50) DEFAULT '',
  `ip` varchar(20) DEFAULT '',
  `province` varchar(50) DEFAULT '',
  `belong_project` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19023 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `visit`
--

DROP TABLE IF EXISTS `visit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(20) NOT NULL COMMENT '访问者的电话号码',
  `function` varchar(20) NOT NULL DEFAULT '' COMMENT '访问接口',
  `function_href` varchar(200) NOT NULL DEFAULT '' COMMENT '具体接口',
  `belong_project` int(11) NOT NULL COMMENT '所属项目',
  `start_time` datetime NOT NULL COMMENT '访问时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `cost_time` int(11) DEFAULT NULL COMMENT '访问时间',
  `type` char(1) DEFAULT '0' COMMENT '类型,0表示app,1表示device',
  `len` mediumint(6) NOT NULL DEFAULT '10',
  `reason` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `visit_backup`
--

DROP TABLE IF EXISTS `visit_backup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visit_backup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(20) NOT NULL,
  `FUNCTION` varchar(20) NOT NULL,
  `function_href` varchar(200) NOT NULL,
  `belong_project` int(11) NOT NULL,
  `start_time` datetime NOT NULL,
  `TYPE` char(1) NOT NULL,
  `end_time` datetime DEFAULT NULL,
  `cost_time` int(11) DEFAULT NULL,
  `len` smallint(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1539242 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `watchxml`
--

DROP TABLE IF EXISTS `watchxml`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `watchxml` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_no` varchar(200) DEFAULT '',
  `project_name` varchar(200) DEFAULT '',
  `channel_id` varchar(200) DEFAULT NULL,
  `company_id` varchar(200) DEFAULT NULL,
  `status` char(1) DEFAULT '1',
  `add_time` datetime DEFAULT NULL,
  `remark` text,
  `need` char(1) DEFAULT NULL,
  `heart_s` varchar(200) DEFAULT NULL,
  `map_typec` int(1) DEFAULT '0',
  `map_typeo` int(1) DEFAULT '0',
  `project_type` char(1) DEFAULT '3',
  `data_sourcec` int(1) DEFAULT '0',
  `data_sourceo` int(1) DEFAULT '0',
  `socket_way` int(1) DEFAULT '0',
  `adTitle` varchar(200) DEFAULT NULL,
  `adDetail` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `white_list`
--

DROP TABLE IF EXISTS `white_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `white_list` (
  `acid` varchar(50) DEFAULT NULL,
  `acphone` varchar(50) DEFAULT NULL,
  `acname` varchar(50) DEFAULT NULL,
  `device_imei` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `yong_neng_info`
--

DROP TABLE IF EXISTS `yong_neng_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yong_neng_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_id` varchar(22) DEFAULT NULL,
  `plan_num` varchar(10) DEFAULT NULL,
  `cur_num` varchar(10) DEFAULT NULL,
  `autoor_define` varchar(5) DEFAULT NULL,
  `plan_name_len` varchar(10) DEFAULT NULL,
  `plan_name` varchar(20) DEFAULT NULL,
  `start_date` varchar(20) DEFAULT NULL,
  `end_date` varchar(20) DEFAULT NULL,
  `week_mode` varchar(20) DEFAULT NULL,
  `energy_list_num` varchar(20) DEFAULT NULL,
  `start_time` varchar(20) DEFAULT NULL,
  `end_time` varchar(20) DEFAULT NULL,
  `power` varchar(5) DEFAULT NULL,
  `run_mode` varchar(5) DEFAULT NULL,
  `target_temp` varchar(10) DEFAULT NULL,
  `temp_unit` varchar(10) DEFAULT NULL,
  `wind_speed` varchar(5) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `all_info` text,
  `type` char(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-18 16:41:15
