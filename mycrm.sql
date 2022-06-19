/*
Navicat MySQL Data Transfer

Source Server         : hsy
Source Server Version : 50631
Source Host           : 127.0.0.1:3306
Source Database       : mycrm

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2021-12-14 00:23:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_dict
-- ----------------------------
DROP TABLE IF EXISTS `base_dict`;
CREATE TABLE `base_dict` (
  `dict_id` int(5) NOT NULL AUTO_INCREMENT,
  `dict_type` varchar(50) NOT NULL,
  `dict_item` varchar(20) NOT NULL,
  `dict_value` varchar(50) NOT NULL,
  `dict_is_editable` int(5) NOT NULL,
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_dict
-- ----------------------------
INSERT INTO `base_dict` VALUES ('1', '客户等级', '战略合作伙伴', '1', '0');
INSERT INTO `base_dict` VALUES ('2', '客户等级', '合作伙伴', '2', '0');
INSERT INTO `base_dict` VALUES ('3', '客户等级', '大客户', '3', '0');
INSERT INTO `base_dict` VALUES ('4', '客户等级', '普通客户', '4', '0');
INSERT INTO `base_dict` VALUES ('5', '服务类型', '咨询', '咨询', '1');
INSERT INTO `base_dict` VALUES ('6', '服务类型', '投诉', '投诉', '1');
INSERT INTO `base_dict` VALUES ('7', '服务类型', '建议', '建议', '1');

-- ----------------------------
-- Table structure for cst_activity
-- ----------------------------
DROP TABLE IF EXISTS `cst_activity`;
CREATE TABLE `cst_activity` (
  `atv_id` int(5) NOT NULL AUTO_INCREMENT,
  `atv_cust_no` varchar(17) DEFAULT NULL,
  `atv_cust_name` varchar(100) DEFAULT NULL,
  `atv_date` datetime DEFAULT NULL,
  `atv_place` varchar(200) NOT NULL,
  `atv_title` varchar(500) NOT NULL,
  `atv_desc` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`atv_id`),
  KEY `atv_cust_no` (`atv_cust_no`),
  CONSTRAINT `cst_activity_ibfk_1` FOREIGN KEY (`atv_cust_no`) REFERENCES `cst_customer` (`cust_no`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cst_activity
-- ----------------------------
INSERT INTO `cst_activity` VALUES ('1', 'cs102410001', '长沙科技城', '2021-11-30 14:09:12', '华天大酒店', '签订合同', '举行会谈签订合同');
INSERT INTO `cst_activity` VALUES ('2', 'cs102410001', '长沙科技城', '2021-11-25 14:10:57', '公司', '展览会', '邀请参加新品发布会');
INSERT INTO `cst_activity` VALUES ('3', 'cs102410001', '长沙科技城', '2021-12-01 09:17:56', '维也纳酒店', '电子产品展览', '参观新兴电子产品');
INSERT INTO `cst_activity` VALUES ('4', 'cs102410006', '长沙图灵', '2021-11-26 14:14:38', '长沙图灵科技学院', '电脑产品选配', '挑选学生学习用电子产品');
INSERT INTO `cst_activity` VALUES ('5', 'hn102410005', '湖南科技股份有限', '2021-11-27 14:16:18', '公司', '签订合同', '卖出笔记本电脑');
INSERT INTO `cst_activity` VALUES ('6', 'sh102410004', '上海科技学院', '2021-11-26 14:18:05', '上海科技学院', '电脑产品卖出', '卖出学习用笔记本');
INSERT INTO `cst_activity` VALUES ('7', 'sz102410002', '深圳科技股份有限公司', '2021-11-22 14:19:28', '深圳大酒店', '签订合同', '签订电脑产品买入合同');
INSERT INTO `cst_activity` VALUES ('8', 'yz102410003', '永州市政府采购处', '2021-12-11 03:52:48', '公司', '签订合同', '签订电脑购买合同');

-- ----------------------------
-- Table structure for cst_customer
-- ----------------------------
DROP TABLE IF EXISTS `cst_customer`;
CREATE TABLE `cst_customer` (
  `cust_no` varchar(17) NOT NULL,
  `cust_name` varchar(100) NOT NULL,
  `cust_region` varchar(50) DEFAULT NULL,
  `cust_manager_id` int(5) DEFAULT NULL,
  `cust_manager_name` varchar(50) DEFAULT NULL,
  `cust_level` int(5) DEFAULT NULL,
  `cust_satisfy` int(5) DEFAULT NULL,
  `cust_credit` int(5) DEFAULT NULL,
  `cust_addr` varchar(300) DEFAULT NULL,
  `cust_zip` varchar(10) DEFAULT NULL,
  `cust_tel` varchar(50) DEFAULT NULL,
  `cust_fax` varchar(50) DEFAULT NULL,
  `cust_website` varchar(50) DEFAULT NULL,
  `cust_licence_no` varchar(50) DEFAULT NULL,
  `cust_chieftain` varchar(50) DEFAULT NULL,
  `cust_bankroll` int(5) DEFAULT NULL,
  `cust_turnover` int(5) DEFAULT NULL,
  `cust_bank` varchar(200) DEFAULT NULL,
  `cust_bank_account` varchar(50) DEFAULT NULL,
  `cust_local_tax_no` varchar(50) DEFAULT NULL,
  `cust_national_tax_no` varchar(50) DEFAULT NULL,
  `cust_status` varchar(1) NOT NULL,
  PRIMARY KEY (`cust_no`),
  KEY `cust_manager_id` (`cust_manager_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cst_customer
-- ----------------------------
INSERT INTO `cst_customer` VALUES ('cs102410001', '长沙科技城', '华中', '102420001', '张三', '2', '100', '8', '湖南省长沙市岳麓区', 'zghn10001', '134252323', '', 'www.csylcskjc.com', 'zghncs10001', '张雷', '7000000', '30000000', '邮政', '6217995650032342', '', '', '2');
INSERT INTO `cst_customer` VALUES ('cs102410006', '长沙图灵', '华中', '102420006', 'tom', '3', '100', '9', '湖南省长沙市', 'zghn10006', '19836536346', null, 'www.changshatuling.com', 'zghncs10006', 'admin', null, null, null, null, null, null, '2');
INSERT INTO `cst_customer` VALUES ('hn102410005', '湖南科技股份有限', '华中', '102420005', '张斌', '1', '90', '8', '湖南省', 'zghn10005', '1975252522', null, null, 'zghnXX10005', '何英', null, null, null, null, null, null, '2');
INSERT INTO `cst_customer` VALUES ('sh102410004', '上海科技学院', '华东', '102420004', '周成', '2', '70', '7', '上海市', 'zgsh10004', '1783534552', '', 'www.shanghaikejixueyuan.com', 'zgshXX10004', '黄玲', null, '18000000', '农业', '6909345145235325', '', '', '1');
INSERT INTO `cst_customer` VALUES ('sz102410002', '深圳科技股份有限公司', '华南', '102420002', '王五', '3', '80', '9', '广东省深圳市', 'zgsz10002', '1246364363', null, null, 'zggdsz10002', '张春华', '10000000', '35000000', '工商', '6399676342535235', null, null, '1');
INSERT INTO `cst_customer` VALUES ('yz102410003', '永州市政府采购处', '华中', '102420003', '李莉', '1', '80', '8', '湖南省永州市', 'zgyz10003', '184525525235', null, 'www.yonzhoushizhengfucaigouchu.com', 'zghnyz10003', '徐志', null, null, null, null, null, null, '1');

-- ----------------------------
-- Table structure for cst_linkman
-- ----------------------------
DROP TABLE IF EXISTS `cst_linkman`;
CREATE TABLE `cst_linkman` (
  `lkm_id` int(5) NOT NULL AUTO_INCREMENT,
  `lkm_cust_no` varchar(17) NOT NULL,
  `lkm_cust_name` varchar(100) DEFAULT NULL,
  `lkm_name` varchar(50) NOT NULL,
  `lkm_sex` varchar(10) DEFAULT NULL,
  `lkm_postion` varchar(50) DEFAULT NULL,
  `lkm_tel` varchar(50) NOT NULL,
  `lkm_mobile` varchar(50) DEFAULT NULL,
  `lkm_memo` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`lkm_id`),
  KEY `lkm_cust_no` (`lkm_cust_no`),
  CONSTRAINT `cst_linkman_ibfk_1` FOREIGN KEY (`lkm_cust_no`) REFERENCES `cst_customer` (`cust_no`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cst_linkman
-- ----------------------------
INSERT INTO `cst_linkman` VALUES ('1', 'cs102410001', '长沙科技城', '张三', '男', '总经理', '0731-8998754', '14654764547', null);
INSERT INTO `cst_linkman` VALUES ('2', 'cs102410001', '长沙科技城', '徐斌', '男', '总监', '0731-8998776', '14566743557', null);
INSERT INTO `cst_linkman` VALUES ('3', 'cs102410001', '长沙科技城', '王楠', '男', '产品经理', '0731-8998798', '19543536335', null);
INSERT INTO `cst_linkman` VALUES ('4', 'cs102410006', '长沙图灵', 'tom', '男', '校长', '0524-5769342', '12723453285', null);
INSERT INTO `cst_linkman` VALUES ('5', 'hn102410005', '湖南科技股份有限', '张斌', '男', '总经理', '0856-1342353', null, null);
INSERT INTO `cst_linkman` VALUES ('6', 'sh102410004', '上海科技学院', '周成', '男', '校长', '0564-2356334', '16477456645', null);
INSERT INTO `cst_linkman` VALUES ('7', 'sz102410002', '深圳科技股份有限公司', '王五', '男', '副总经理', '0352-8674625', '17457568557', null);
INSERT INTO `cst_linkman` VALUES ('8', 'yz102410003', '永州市政府采购处', '李莉', '女', '总监', '0687-3252673', '19874574578', null);

-- ----------------------------
-- Table structure for cst_lost
-- ----------------------------
DROP TABLE IF EXISTS `cst_lost`;
CREATE TABLE `cst_lost` (
  `lst_id` int(5) NOT NULL AUTO_INCREMENT,
  `lst_cust_no` varchar(17) NOT NULL,
  `lst_cust_name` varchar(100) NOT NULL,
  `lst_cust_manager_id` int(5) NOT NULL,
  `lst_cust_manager_name` varchar(50) NOT NULL,
  `lst_last_order_date` datetime DEFAULT NULL,
  `lst_lost_date` datetime DEFAULT NULL,
  `lst_delay` varchar(4000) DEFAULT NULL,
  `lst_reason` varchar(2000) DEFAULT NULL,
  `lst_status` varchar(10) NOT NULL,
  PRIMARY KEY (`lst_id`),
  KEY `lst_cust_no` (`lst_cust_no`),
  KEY `lst_cust_manager_id` (`lst_cust_manager_id`),
  CONSTRAINT `cst_lost_ibfk_1` FOREIGN KEY (`lst_cust_no`) REFERENCES `cst_customer` (`cust_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cst_lost_ibfk_2` FOREIGN KEY (`lst_cust_manager_id`) REFERENCES `cst_customer` (`cust_manager_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cst_lost
-- ----------------------------
INSERT INTO `cst_lost` VALUES ('1', 'sz102410002', '深圳科技股份有限公司', '102420002', '王五', '2021-12-01 10:49:19', null, '已与客户沟通', '产品意见不符', '3');
INSERT INTO `cst_lost` VALUES ('2', 'yz102410003', '永州市政府采购处', '102420003', '李莉', '2021-12-01 22:48:29', null, '和客户进行了交流<br/>\n已进行进一步了解', null, '2');
INSERT INTO `cst_lost` VALUES ('3', 'sh102410004', '上海科技学院', '102420004', '周成', '2021-12-01 22:50:29', null, null, null, '1');

-- ----------------------------
-- Table structure for cst_service
-- ----------------------------
DROP TABLE IF EXISTS `cst_service`;
CREATE TABLE `cst_service` (
  `svr_id` int(5) NOT NULL AUTO_INCREMENT,
  `svr_type` varchar(20) NOT NULL,
  `svr_title` varchar(500) NOT NULL,
  `svr_cust_no` varchar(17) DEFAULT NULL,
  `svr_cust_name` varchar(100) NOT NULL,
  `svr_status` varchar(10) NOT NULL,
  `svr_request` varchar(3000) NOT NULL,
  `svr_create_id` int(5) NOT NULL,
  `svr_create_by` varchar(50) NOT NULL,
  `svr_create_date` datetime NOT NULL,
  `svr_due_id` int(5) DEFAULT NULL,
  `svr_due_to` varchar(50) DEFAULT NULL,
  `svr_due_date` datetime DEFAULT NULL,
  `svr_deal` varchar(3000) DEFAULT NULL,
  `svr_deal_id` int(5) DEFAULT NULL,
  `svr_deal_by` varchar(50) DEFAULT NULL,
  `svr_deal_date` datetime DEFAULT NULL,
  `svr_result` varchar(500) DEFAULT NULL,
  `svr_satisfy` int(5) DEFAULT NULL,
  PRIMARY KEY (`svr_id`),
  KEY `svr_due_id` (`svr_due_id`),
  KEY `svr_due_to` (`svr_due_to`),
  KEY `svr_cust_no` (`svr_cust_no`),
  KEY `svr_create_id` (`svr_create_id`),
  KEY `svr_deal_id` (`svr_deal_id`),
  KEY `svr_deal_by` (`svr_deal_by`),
  CONSTRAINT `cst_service_ibfk_1` FOREIGN KEY (`svr_due_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cst_service_ibfk_2` FOREIGN KEY (`svr_due_to`) REFERENCES `sys_user` (`user_name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cst_service_ibfk_3` FOREIGN KEY (`svr_cust_no`) REFERENCES `cst_customer` (`cust_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cst_service_ibfk_4` FOREIGN KEY (`svr_create_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cst_service_ibfk_5` FOREIGN KEY (`svr_deal_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cst_service_ibfk_6` FOREIGN KEY (`svr_deal_by`) REFERENCES `sys_user` (`user_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cst_service
-- ----------------------------
INSERT INTO `cst_service` VALUES ('1', '咨询', '采购笔记本意向', 'cs102410001', '长沙科技城', '已归档', '想要购买大量笔记本用于办公', '1', 'hsy', '2020-12-22 21:46:07', '3', 'hgh', '2021-12-09 15:31:24', '向客户推荐了笔记本', '3', 'hgh', '2021-11-27 00:19:30', '还不错', '90');
INSERT INTO `cst_service` VALUES ('2', '咨询', '咨询台式电脑、笔记本大批采购的价格', 'sh102410004', '上海科技学院', '已归档', '想要购买大量台式电脑和笔记本电脑', '1', 'hsy', '2019-11-22 21:49:00', '2', 'zk', '2021-11-25 14:53:02', '已向客户说明电脑价格，并告知公司没有台式电脑卖', '2', 'zk', '2021-12-02 16:17:14', '非常好', '100');
INSERT INTO `cst_service` VALUES ('3', '咨询', '想要电脑桌购买意向', 'sz102410002', '深圳科技股份有限公司', '已分配', '询问有没有电脑桌卖', '2', 'zk', '2021-11-22 21:50:49', null, null, null, null, null, null, null, null, null);
INSERT INTO `cst_service` VALUES ('4', '建议', '建议多进货游戏本', 'yz102410003', '永州市政府采购处', '已反馈', '希望多进货一点游戏本', '2', 'zk', '2018-11-22 21:53:18', '3', 'hgh', '2021-11-26 00:00:00', '已进货更多的游戏本', '1', 'hsy', '2021-12-10 14:57:03', '非常满意，进货了更多的游戏本', '100');
INSERT INTO `cst_service` VALUES ('5', '建议', '建议可以进货一点台式电脑', 'hn102410005', '湖南科技股份有限', '已处理', '希望我们能进货一点台式电脑', '3', 'hgh', '2021-11-24 10:14:17', '3', 'hgh', '2021-12-09 15:33:35', '已进货台式电脑', '1', 'hsy', '2021-12-11 01:16:34', null, null);
INSERT INTO `cst_service` VALUES ('6', '投诉', '质量问题', 'cs102410006', '长沙图灵', '新创建', '购买的商品出现质量问题', '1', 'hsy', '2021-12-09 15:23:34', null, null, null, null, null, null, null, null, null);
INSERT INTO `cst_service` VALUES ('8', '投诉', '质量问题', 'cs102410006', '长沙图灵', '新创建', 'dasdsa', '1', 'hsy', '2021-12-03 10:38:40', null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `odr_id` int(5) NOT NULL,
  `odr_customer` varchar(100) NOT NULL,
  `odr_date` datetime NOT NULL,
  `odr_addr` varchar(200) DEFAULT NULL,
  `odr_status` varchar(1) NOT NULL,
  KEY `odr_id` (`odr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1024040001', '长沙科技城', '2021-11-30 14:26:07', '长沙华天大酒店', '1');
INSERT INTO `orders` VALUES ('1024040002', '长沙图灵', '2021-11-30 14:28:29', '长沙图灵科技学院', '1');
INSERT INTO `orders` VALUES ('1024040003', '湖南科技股份有限', '2021-11-27 14:29:10', '公司', '1');
INSERT INTO `orders` VALUES ('1024040004', '上海科技学院', '2021-11-27 14:30:12', '上海科技学院', '2');
INSERT INTO `orders` VALUES ('1024040005', '长沙科技城', '2021-12-01 14:30:56', '长沙科技城', '2');
INSERT INTO `orders` VALUES ('1024040006', '永州市政府采购处', '2021-11-27 14:31:56', '永州市', '1');
INSERT INTO `orders` VALUES ('1024040007', '深圳科技股份有限公司', '2021-11-26 14:32:42', '深圳大酒店', '1');
INSERT INTO `orders` VALUES ('1024040008', '长沙科技城', '2021-11-20 15:55:02', '长沙维也纳酒店', '1');
INSERT INTO `orders` VALUES ('1024040009', '长沙科技城', '2021-12-04 15:56:21', '长沙大酒店', '2');

-- ----------------------------
-- Table structure for orders_line
-- ----------------------------
DROP TABLE IF EXISTS `orders_line`;
CREATE TABLE `orders_line` (
  `odd_id` int(5) NOT NULL AUTO_INCREMENT,
  `odd_order_id` int(5) NOT NULL,
  `odd_prod_id` int(5) NOT NULL,
  `odd_count` int(5) NOT NULL,
  `odd_unit` varchar(10) DEFAULT NULL,
  `odd_price` decimal(7,2) DEFAULT NULL,
  PRIMARY KEY (`odd_id`),
  KEY `odd_prod_id` (`odd_prod_id`),
  KEY `odd_order_id` (`odd_order_id`),
  CONSTRAINT `orders_line_ibfk_2` FOREIGN KEY (`odd_prod_id`) REFERENCES `product` (`prod_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orders_line_ibfk_3` FOREIGN KEY (`odd_order_id`) REFERENCES `orders` (`odr_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders_line
-- ----------------------------
INSERT INTO `orders_line` VALUES ('1', '1024040001', '1', '30', '台', '8999.00');
INSERT INTO `orders_line` VALUES ('2', '1024040002', '9', '60', '台', '2999.00');
INSERT INTO `orders_line` VALUES ('3', '1024040003', '3', '20', '台', '8900.00');
INSERT INTO `orders_line` VALUES ('4', '1024040004', '10', '40', '台', '2999.00');
INSERT INTO `orders_line` VALUES ('5', '1024040005', '2', '30', '台', '7999.00');
INSERT INTO `orders_line` VALUES ('6', '1024040006', '8', '20', '台', '5799.00');
INSERT INTO `orders_line` VALUES ('7', '1024040007', '6', '40', '台', '9999.00');
INSERT INTO `orders_line` VALUES ('8', '1024040008', '7', '5', '台', '37999.00');
INSERT INTO `orders_line` VALUES ('9', '1024040009', '5', '20', '台', '7999.00');
INSERT INTO `orders_line` VALUES ('10', '1024040001', '8', '20', '台', '5799.00');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `prod_id` int(5) NOT NULL AUTO_INCREMENT,
  `prod_name` varchar(200) NOT NULL,
  `prod_type` varchar(100) NOT NULL,
  `prod_batch` varchar(100) DEFAULT NULL,
  `prod_unit` varchar(10) DEFAULT NULL,
  `prod_price` decimal(7,2) DEFAULT NULL,
  `prod_memo` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`prod_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '联想电脑', 'X1 Nano', '1', '台', '8999.00', null);
INSERT INTO `product` VALUES ('2', '联想电脑', 'X1 Carbon', '1', '台', '7999.00', null);
INSERT INTO `product` VALUES ('3', '联想电脑', '15p', '1', '台', '8900.00', null);
INSERT INTO `product` VALUES ('4', '联想', '13x', '1', '台', '6999.00', null);
INSERT INTO `product` VALUES ('5', 'iPhone', 'MacBook Air', '1', '台', '7999.00', null);
INSERT INTO `product` VALUES ('6', 'iPhone', 'MacBook Pro', '1', '台', '9999.00', null);
INSERT INTO `product` VALUES ('7', '外星人电脑', 'x15', '1', '台', '37999.00', null);
INSERT INTO `product` VALUES ('8', '华硕', 'Pro14', '1', '台', '5799.00', null);
INSERT INTO `product` VALUES ('9', '酷睿', 'i7', '1', '台', '2999.00', null);
INSERT INTO `product` VALUES ('10', '惠普', '14青春版', '1', '台', '2999.00', null);

-- ----------------------------
-- Table structure for sal_chance
-- ----------------------------
DROP TABLE IF EXISTS `sal_chance`;
CREATE TABLE `sal_chance` (
  `chc_id` int(5) NOT NULL AUTO_INCREMENT,
  `chc_source` varchar(50) DEFAULT NULL,
  `chc_cust_name` varchar(100) NOT NULL,
  `chc_title` varchar(200) NOT NULL,
  `chc_rate` int(5) NOT NULL,
  `chc_linkman` varchar(50) DEFAULT NULL,
  `chc_tel` varchar(50) DEFAULT NULL,
  `chc_desc` varchar(2000) NOT NULL,
  `chc_create_id` int(5) NOT NULL,
  `chc_create_by` varchar(50) NOT NULL,
  `chc_create_date` datetime NOT NULL,
  `chc_due_id` int(5) DEFAULT NULL,
  `chc_due_to` varchar(50) DEFAULT NULL,
  `chc_due_date` datetime DEFAULT NULL,
  `chc_status` int(10) NOT NULL,
  PRIMARY KEY (`chc_id`),
  KEY `chc_create_id` (`chc_create_id`),
  KEY `chc_due_id` (`chc_due_id`) USING BTREE,
  KEY `sal_chance_ibfk_2` (`chc_due_to`),
  CONSTRAINT `sal_chance_ibfk_1` FOREIGN KEY (`chc_due_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sal_chance_ibfk_2` FOREIGN KEY (`chc_due_to`) REFERENCES `sys_user` (`user_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sal_chance
-- ----------------------------
INSERT INTO `sal_chance` VALUES ('1', '张三介绍', '长沙科技城', '采购笔记本意向', '80', '张经理', '13729239239', 'qweqwertyuiop', '1', 'hsy', '2021-11-22 21:46:07', '2', 'zk', '2021-12-13 00:00:00', '2');
INSERT INTO `sal_chance` VALUES ('2', '李四介绍', '上海科技学院', '台式电脑、笔记本大批采购', '90', '宋校长', '15876497565', 'ljajkihfahcfoifcoinhavcunv', '1', 'hsy', '2021-11-22 21:49:00', '2', 'zk', '2021-11-25 14:53:02', '2');
INSERT INTO `sal_chance` VALUES ('3', '王五介绍', '深圳科技股份有限公司', '电脑桌购买意向', '60', '王主任', '18698709654', 'hfhiuqcnjqwncijkenqijnckjw', '2', 'zk', '2021-11-22 21:50:49', '1', 'hsy', '2021-11-25 14:53:05', '4');
INSERT INTO `sal_chance` VALUES ('4', '公司同事amin介绍', '永州市政府采购处', '游戏笔记本', '80', 'tom', '10235352524', 'suahdiosmclkamssajk', '2', 'zk', '2021-11-22 21:53:18', '3', 'hgh', '2021-11-26 00:00:00', '1');
INSERT INTO `sal_chance` VALUES ('5', '网上找来的', '湖南科技股份有限', '笔记本电脑', '80', 'admin', '12124142342', 'efwfwferwvfwfjk', '3', 'hgh', '2021-11-24 10:14:17', null, null, null, '1');
INSERT INTO `sal_chance` VALUES ('6', '朋友介绍', '长沙图灵', 'scavdsnvdsvn', '80', '13213', '1943594353', 'acjanciajnscijancja', '1', 'hsy', '2021-11-24 00:00:00', '3', 'hgh', '2021-11-30 00:00:00', '2');

-- ----------------------------
-- Table structure for sal_plan
-- ----------------------------
DROP TABLE IF EXISTS `sal_plan`;
CREATE TABLE `sal_plan` (
  `pla_id` int(5) NOT NULL AUTO_INCREMENT,
  `pla_chc_id` int(5) NOT NULL,
  `pla_date` datetime NOT NULL,
  `pla_todo` varchar(500) NOT NULL,
  `pla_result` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`pla_id`),
  KEY `sal_plan_ibfk_1` (`pla_chc_id`),
  CONSTRAINT `sal_plan_ibfk_1` FOREIGN KEY (`pla_chc_id`) REFERENCES `sal_chance` (`chc_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sal_plan
-- ----------------------------
INSERT INTO `sal_plan` VALUES ('1', '1', '2021-11-22 21:55:30', '初步接触，了解客户意向，已', '对我方表示非常满意');
INSERT INTO `sal_plan` VALUES ('2', '1', '2021-11-22 21:57:11', '根据客户需求推荐产品', '对方表示产品优点不够明确');
INSERT INTO `sal_plan` VALUES ('3', '2', '2021-11-22 22:26:15', '初步接触，已了解客户对产品需求意向', '对方对产品需求非常明确');
INSERT INTO `sal_plan` VALUES ('4', '3', '2021-11-22 22:27:36', '初步接触，客户对产品需求还不太明确', '对方有明确购买意向');
INSERT INTO `sal_plan` VALUES ('5', '3', '2021-11-22 22:29:11', '已向客户推荐符合需求产品', '对方对推荐产品表示非常满意');
INSERT INTO `sal_plan` VALUES ('6', '1', '2021-11-25 00:00:00', '收尾工作', '非常');

-- ----------------------------
-- Table structure for storage
-- ----------------------------
DROP TABLE IF EXISTS `storage`;
CREATE TABLE `storage` (
  `stk_id` int(5) NOT NULL AUTO_INCREMENT,
  `stk_prod_id` int(5) NOT NULL,
  `stk_warehouse` varchar(50) NOT NULL,
  `stk_ware` varchar(50) NOT NULL,
  `stk_count` int(5) NOT NULL,
  `stk_memo` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`stk_id`),
  KEY `storage_ibfk_1` (`stk_prod_id`),
  CONSTRAINT `storage_ibfk_1` FOREIGN KEY (`stk_prod_id`) REFERENCES `product` (`prod_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of storage
-- ----------------------------
INSERT INTO `storage` VALUES ('1', '1', '长沙-河西', 'A-100', '100', null);
INSERT INTO `storage` VALUES ('2', '2', '长沙-河西', 'A-100', '80', null);
INSERT INTO `storage` VALUES ('3', '3', '长沙-河西', 'A-100', '80', null);
INSERT INTO `storage` VALUES ('4', '4', '长沙-河西', 'B-80', '90', null);
INSERT INTO `storage` VALUES ('5', '5', '长沙-河西', 'B-80', '60', null);
INSERT INTO `storage` VALUES ('6', '6', '长沙-河西', 'C-60', '80', null);
INSERT INTO `storage` VALUES ('7', '7', '长沙-河西', 'C-60', '40', null);
INSERT INTO `storage` VALUES ('8', '8', '长沙-岳麓区', 'A-100', '100', null);
INSERT INTO `storage` VALUES ('9', '9', '长沙-岳麓区', 'B-80', '80', null);
INSERT INTO `storage` VALUES ('10', '10', '长沙-岳麓区', 'C-60', '70', null);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `right_id` int(50) NOT NULL AUTO_INCREMENT,
  `right_parent_id` varchar(50) DEFAULT NULL,
  `right_type` tinyint(4) DEFAULT NULL,
  `right_text` varchar(50) DEFAULT NULL,
  `right_url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`right_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(5) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  `role_desc` varchar(50) DEFAULT NULL,
  `role_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `rf_role_id` int(5) NOT NULL,
  `rf_right_id` int(5) NOT NULL,
  PRIMARY KEY (`rf_role_id`,`rf_right_id`),
  KEY `sys_role_menu_ibfk_2` (`rf_right_id`),
  CONSTRAINT `sys_role_menu_ibfk_1` FOREIGN KEY (`rf_role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_role_menu_ibfk_2` FOREIGN KEY (`rf_right_id`) REFERENCES `sys_menu` (`right_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(5) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `user_password` varchar(50) NOT NULL,
  `user_role_id` int(100) DEFAULT NULL,
  `user_flag` tinyint(5) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `user_name` (`user_name`),
  KEY `sys_user_ibfk_1` (`user_role_id`),
  CONSTRAINT `sys_user_ibfk_1` FOREIGN KEY (`user_role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'hsy', '1024', null, '1');
INSERT INTO `sys_user` VALUES ('2', 'zk', '1234', null, '1');
INSERT INTO `sys_user` VALUES ('3', 'hgh', '123', null, '1');
