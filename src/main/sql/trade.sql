SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `trade` DEFAULT CHARACTER SET utf8 ;
USE `trade`;

-- ----------------------------
-- Table structure for `trade_market`
-- ----------------------------
DROP TABLE IF EXISTS `trade_market`;
CREATE TABLE `trade_market` (
  `market_id` int(10) NOT NULL DEFAULT '0',
  `label` varchar(60) DEFAULT NULL,
  `primary_currency_code` varchar(60) DEFAULT NULL,
  `primary_currency_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`market_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trade_market
-- ----------------------------
INSERT INTO `trade_market` VALUES ('3', 'LTC/BTC', 'LTC', 'LiteCoin');
INSERT INTO `trade_market` VALUES ('5', 'FTC/BTC', 'FTC', 'FeatherCoin');
INSERT INTO `trade_market` VALUES ('7', 'MNC/BTC', 'MNC', 'MinCoin');
INSERT INTO `trade_market` VALUES ('8', 'CNC/BTC', 'CNC', 'CHNCoin');
INSERT INTO `trade_market` VALUES ('10', 'BQC/BTC', 'BQC', 'BBQCoin');
INSERT INTO `trade_market` VALUES ('11', 'YAC/BTC', 'YAC', 'YaCoin');
INSERT INTO `trade_market` VALUES ('12', 'ELC/BTC', 'ELC', 'ElaCoin');
INSERT INTO `trade_market` VALUES ('13', 'NVC/BTC', 'NVC', 'NovaCoin');
INSERT INTO `trade_market` VALUES ('14', 'WDC/BTC', 'WDC', 'WorldCoin');
INSERT INTO `trade_market` VALUES ('23', 'BTB/BTC', 'BTB', 'BitBar');
INSERT INTO `trade_market` VALUES ('24', 'PWC/BTC', 'PWC', 'PowerCoin');
INSERT INTO `trade_market` VALUES ('26', 'DGC/BTC', 'DGC', 'DigitalCoin');
INSERT INTO `trade_market` VALUES ('27', 'TRC/BTC', 'TRC', 'TerraCoin');
INSERT INTO `trade_market` VALUES ('28', 'PPC/BTC', 'PPC', 'PPCoin');
INSERT INTO `trade_market` VALUES ('29', 'NMC/BTC', 'NMC', 'NameCoin');
INSERT INTO `trade_market` VALUES ('31', 'PXC/BTC', 'PXC', 'PhenixCoin');
INSERT INTO `trade_market` VALUES ('32', 'NBL/BTC', 'NBL', 'Nibble');
INSERT INTO `trade_market` VALUES ('33', 'FRK/BTC', 'FRK', 'Franko');
INSERT INTO `trade_market` VALUES ('34', 'LKY/BTC', 'LKY', 'LuckyCoin');
INSERT INTO `trade_market` VALUES ('35', 'JKC/LTC', 'JKC', 'JunkCoin');
INSERT INTO `trade_market` VALUES ('36', 'GLD/LTC', 'GLD', 'GLDCoin');
INSERT INTO `trade_market` VALUES ('37', 'RYC/LTC', 'RYC', 'RoyalCoin');
INSERT INTO `trade_market` VALUES ('38', 'IXC/BTC', 'IXC', 'IXCoin');
INSERT INTO `trade_market` VALUES ('39', 'FRC/BTC', 'FRC', 'FreiCoin');
INSERT INTO `trade_market` VALUES ('40', 'DVC/BTC', 'DVC', 'DevCoin');
INSERT INTO `trade_market` VALUES ('41', 'MEM/BTC', 'MEM', 'MemeCoin');
INSERT INTO `trade_market` VALUES ('42', 'HYC/BTC', 'HYC', 'HyperCoin');
INSERT INTO `trade_market` VALUES ('43', 'AMC/BTC', 'AMC', 'AmericanCoin');
INSERT INTO `trade_market` VALUES ('44', 'FST/BTC', 'FST', 'FastCoin');
INSERT INTO `trade_market` VALUES ('45', 'MEC/BTC', 'MEC', 'MegaCoin');
INSERT INTO `trade_market` VALUES ('46', 'DBL/LTC', 'DBL', 'Doubloons');
INSERT INTO `trade_market` VALUES ('47', 'EZC/BTC', 'EZC', 'EZCoin');
INSERT INTO `trade_market` VALUES ('48', 'ARG/BTC', 'ARG', 'Argentum');
INSERT INTO `trade_market` VALUES ('49', 'BTE/BTC', 'BTE', 'ByteCoin');

-- ----------------------------
-- Table structure for `trade_properties`
-- ----------------------------
DROP TABLE IF EXISTS `trade_properties`;
CREATE TABLE `trade_properties` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL,
  `value` varchar(300) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trade_properties
-- ----------------------------
INSERT INTO `trade_properties` VALUES ('5', '_HSK_LOCATION', '\"C:/Program Files (x86)/Oray/PhDDNS/PhDDNS.exe\"', null);

-- ----------------------------
-- Table structure for `trade_user`
-- ----------------------------
DROP TABLE IF EXISTS `trade_user`;
CREATE TABLE `trade_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(60) DEFAULT NULL,
  `password` varchar(60) DEFAULT NULL,
  `key` varchar(200) DEFAULT NULL,
  `secret` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trade_user
-- ----------------------------
INSERT INTO `trade_user` VALUES ('1', 'zxw', '96e79218965eb72c92a549dd5a330112', '916f13117227c5fea82950874b824d7af5990c54', '6bd6079674a664d02e3beec80f89d240b5f8bce4464b260a6f6fe91096a72e0d1abc0e1c07beee63');
