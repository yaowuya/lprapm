/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50168
Source Host           : localhost:3306
Source Database       : lprapm

Target Server Type    : MYSQL
Target Server Version : 50168
File Encoding         : 65001

Date: 2017-03-15 17:46:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `action`
-- ----------------------------
DROP TABLE IF EXISTS `action`;
CREATE TABLE `action` (
  `action_id` int(11) NOT NULL AUTO_INCREMENT,
  `action_name` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `action_desc` varchar(400) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`action_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='权限表';

-- ----------------------------
-- Records of action
-- ----------------------------
INSERT INTO `action` VALUES ('1', '查看首页', '查看首页，普通用户的权限，也是系统最低权限');
INSERT INTO `action` VALUES ('2', '查询菜单', '超级管理员的权限');
INSERT INTO `action` VALUES ('3', '订单查询', '市场部门所有员工包括领导都具有该权限');
INSERT INTO `action` VALUES ('4', '订单审核', '市场部门领导具有该权限');

-- ----------------------------
-- Table structure for `car`
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car` (
  `car_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `car_license` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `car_type` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `car_volume` double DEFAULT NULL,
  `car_weight` double DEFAULT NULL,
  `km_price` double DEFAULT NULL,
  `is_free` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`car_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='车辆信息';

-- ----------------------------
-- Records of car
-- ----------------------------
INSERT INTO `car` VALUES ('2', '3', '34567', '轿车', '45', '210', '344', '是');

-- ----------------------------
-- Table structure for `car_need`
-- ----------------------------
DROP TABLE IF EXISTS `car_need`;
CREATE TABLE `car_need` (
  `carn_id` int(11) NOT NULL,
  `carn_type` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `carn_num` int(11) DEFAULT NULL,
  `carn_exam_state` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `car_exam_person` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `carn_exam_dept` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `car_ids` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`carn_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='车辆需求信息';

-- ----------------------------
-- Records of car_need
-- ----------------------------

-- ----------------------------
-- Table structure for `car_plan`
-- ----------------------------
DROP TABLE IF EXISTS `car_plan`;
CREATE TABLE `car_plan` (
  `carplan_id` int(11) NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `carn_id` int(11) DEFAULT NULL,
  `all_weight` double DEFAULT NULL,
  `all_number` double DEFAULT NULL,
  `all_volume` double DEFAULT NULL,
  `carplan_dept` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `carplan_person` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `carplan_desrciption` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `carplan_time` date DEFAULT NULL,
  PRIMARY KEY (`carplan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='配车方案';

-- ----------------------------
-- Records of car_plan
-- ----------------------------

-- ----------------------------
-- Table structure for `car_type`
-- ----------------------------
DROP TABLE IF EXISTS `car_type`;
CREATE TABLE `car_type` (
  `ct_id` int(11) NOT NULL,
  `ct_type` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ct_volume` double DEFAULT NULL,
  `ct_weight` double DEFAULT NULL,
  `km_price` double DEFAULT NULL,
  PRIMARY KEY (`ct_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='车类型管理';

-- ----------------------------
-- Records of car_type
-- ----------------------------

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dept_desc` varchar(400) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='部门管理';

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '市场部', '管理123订单');
INSERT INTO `department` VALUES ('2', '运输部', '管理运输');

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `goods_number` double DEFAULT NULL,
  `goods_volume` double DEFAULT NULL,
  `goods_perweight` double DEFAULT NULL,
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='货物';

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('5', '穿山甲', '344', '23', '55');
INSERT INTO `goods` VALUES ('6', '大鱼', '1231', '23', '5');
INSERT INTO `goods` VALUES ('7', '冰', '12', '234', '12');
INSERT INTO `goods` VALUES ('8', '鳄鱼', '32333', '324', '12');
INSERT INTO `goods` VALUES ('9', '飞熊', '33', '556', '1000');
INSERT INTO `goods` VALUES ('10', '飞熊', '33', '556', '1000');
INSERT INTO `goods` VALUES ('17', '大厦', '33', '33', null);
INSERT INTO `goods` VALUES ('18', '蟠桃', '55', '44', '666');
INSERT INTO `goods` VALUES ('20', '昆吾', '7', '44', '88');

-- ----------------------------
-- Table structure for `log_price`
-- ----------------------------
DROP TABLE IF EXISTS `log_price`;
CREATE TABLE `log_price` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `log_dept` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `log_person` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `log_price` double DEFAULT NULL,
  `log_state` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='物流报价';

-- ----------------------------
-- Records of log_price
-- ----------------------------
INSERT INTO `log_price` VALUES ('2', null, null, null, '否');
INSERT INTO `log_price` VALUES ('3', null, null, null, '否');
INSERT INTO `log_price` VALUES ('4', null, null, null, '否');
INSERT INTO `log_price` VALUES ('5', null, null, null, '否');
INSERT INTO `log_price` VALUES ('6', '市场部', '换换', '12', '已出发');
INSERT INTO `log_price` VALUES ('7', null, null, null, '否');
INSERT INTO `log_price` VALUES ('14', null, null, null, '否');
INSERT INTO `log_price` VALUES ('15', null, null, null, '否');
INSERT INTO `log_price` VALUES ('17', null, null, null, '否');

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `menu_i_class` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `menu_href` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='菜单管理表';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '首页', 'fa-home', '/redirect/page/home');
INSERT INTO `menu` VALUES ('2', '菜单管理', 'fa-list', '/redirect/page/menu');
INSERT INTO `menu` VALUES ('3', '部门管理', 'fa-link', '/redirect/page/department');
INSERT INTO `menu` VALUES ('4', '用户管理', 'fa-user-o', '/redirect/page/user');
INSERT INTO `menu` VALUES ('5', '角色管理', 'fa-cog', '/redirect/page/roleAction');
INSERT INTO `menu` VALUES ('9', '角色配置', 'fa-ticket', '/redirect/page/roleSet');
INSERT INTO `menu` VALUES ('10', '采购订单管理', 'fa-cab', '/redirect/page/purchaseOrder');
INSERT INTO `menu` VALUES ('11', '物流订单管理', 'fa-truck', '/redirect/page/logisticsOrder');
INSERT INTO `menu` VALUES ('12', '采购价格展示', 'fa-tree', '/redirect/page/purchase');
INSERT INTO `menu` VALUES ('13', '采购计划管理', 'fa-envira', '/redirect/page/purchasePlan');
INSERT INTO `menu` VALUES ('15', '物流审核管理', 'fa-check', '/redirect/page/logExamine');
INSERT INTO `menu` VALUES ('16', '物流计费管理', 'fa-cny', '/redirect/page/logCharge');
INSERT INTO `menu` VALUES ('17', '车辆信息管理', 'fa-bus', '/redirect/page/vehicleInfo');
INSERT INTO `menu` VALUES ('18', '车辆需求管理', 'fa-truck', '/redirect/page/vehicleDemand');

-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) DEFAULT NULL,
  `oe_id` int(11) DEFAULT NULL,
  `receipt_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `pur_id` int(11) DEFAULT NULL,
  `log_id` int(11) DEFAULT NULL,
  `user_name` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_pur` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_ask_pur` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_ask_log` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_sure` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `end_time` date DEFAULT NULL,
  `order_address` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='订单';

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('3', '5', '2', '2', '11', '2', '2', '换换', '是', '是', '否', '否', '2017-02-08', '2017-02-27', '阿萨德开房间辣圣诞节快拉');
INSERT INTO `orders` VALUES ('4', '6', '3', '3', '11', '3', '3', '换换', '是', '是', '否', '否', '2017-02-08', '2017-03-10', '类似的看法拉萨的李开复');
INSERT INTO `orders` VALUES ('5', '7', '4', '4', '11', '4', '4', '换换', '是', '是', '是', '否', '2017-02-08', '2017-03-03', '大声点开了房间卡拉斯的减肥路上打卡机福利卡电视机房里看见俺说的可浪费了斯柯达解放啦圣诞节疯狂了大数据法拉盛打发');
INSERT INTO `orders` VALUES ('6', '8', null, '5', '11', '5', '5', '换换', '否', '否', '否', '', '2017-03-01', '2017-03-31', '撒的发生发发试试事实上');
INSERT INTO `orders` VALUES ('7', '9', '6', '6', '11', '6', '6', '换换', '否', '否', '是', '是', '2017-03-01', '2017-04-09', '撒旦法师法师打发的说法是的发生');
INSERT INTO `orders` VALUES ('8', '10', '7', '7', '11', '7', '7', '换换', '否', '否', '否', '否', '2017-03-01', '2017-04-09', '撒旦法师法师打发的说法是的发生');
INSERT INTO `orders` VALUES ('15', '17', null, '14', '11', '14', '14', '换换', '否', '否', '否', '', '2017-03-01', null, '');
INSERT INTO `orders` VALUES ('16', '18', null, '15', '11', '15', '15', '换换', '是', '否', '否', '', '2017-03-07', '2017-04-09', '广东省广州市同和握山石决南街三巷八号');
INSERT INTO `orders` VALUES ('18', '20', null, '17', '11', '17', '17', '换换', '是', '否', '否', '', '2017-03-07', '2017-03-29', '啊是的浪费空间');

-- ----------------------------
-- Table structure for `order_exam`
-- ----------------------------
DROP TABLE IF EXISTS `order_exam`;
CREATE TABLE `order_exam` (
  `oe_id` int(11) NOT NULL AUTO_INCREMENT,
  `oe_state` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `oe_person` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `oe_dept` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `oe_reason` varchar(400) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`oe_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='市场部--订单审核';

-- ----------------------------
-- Records of order_exam
-- ----------------------------
INSERT INTO `order_exam` VALUES ('2', '否', null, null, null);
INSERT INTO `order_exam` VALUES ('3', '否', null, null, null);
INSERT INTO `order_exam` VALUES ('4', '否', '123', '123', '213');
INSERT INTO `order_exam` VALUES ('5', '否', null, null, null);
INSERT INTO `order_exam` VALUES ('6', '通过', '换换', '市场审核部', '123');
INSERT INTO `order_exam` VALUES ('7', '否', null, null, null);
INSERT INTO `order_exam` VALUES ('14', '否', null, null, null);
INSERT INTO `order_exam` VALUES ('18', '否', null, null, null);
INSERT INTO `order_exam` VALUES ('19', '否', null, null, null);
INSERT INTO `order_exam` VALUES ('20', '否', null, null, null);
INSERT INTO `order_exam` VALUES ('21', '否', null, null, null);
INSERT INTO `order_exam` VALUES ('22', '否', null, null, null);
INSERT INTO `order_exam` VALUES ('23', '否', null, null, null);
INSERT INTO `order_exam` VALUES ('24', '否', null, null, null);
INSERT INTO `order_exam` VALUES ('25', '否', null, null, null);
INSERT INTO `order_exam` VALUES ('26', '否', null, null, null);

-- ----------------------------
-- Table structure for `position_tracking`
-- ----------------------------
DROP TABLE IF EXISTS `position_tracking`;
CREATE TABLE `position_tracking` (
  `position_id` int(11) NOT NULL,
  `withcar_id` int(11) DEFAULT NULL,
  `repo_id` int(11) DEFAULT NULL,
  `track_status` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`position_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='位置管理';

-- ----------------------------
-- Records of position_tracking
-- ----------------------------

-- ----------------------------
-- Table structure for `purchase_plan`
-- ----------------------------
DROP TABLE IF EXISTS `purchase_plan`;
CREATE TABLE `purchase_plan` (
  `pp_id` int(11) NOT NULL,
  `pg_id` int(11) DEFAULT NULL,
  `pp_state` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pp_exam` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pp_exam_person` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pp_exam_dept` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pp_exam_reason` varchar(400) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pp_start_time` datetime DEFAULT NULL,
  `pp_end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`pp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='采购计划';

-- ----------------------------
-- Records of purchase_plan
-- ----------------------------

-- ----------------------------
-- Table structure for `purchase_price_manage`
-- ----------------------------
DROP TABLE IF EXISTS `purchase_price_manage`;
CREATE TABLE `purchase_price_manage` (
  `ppm_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `goods_price` double DEFAULT NULL,
  `ppm_time` date DEFAULT NULL,
  PRIMARY KEY (`ppm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='采购物品的市场价格';

-- ----------------------------
-- Records of purchase_price_manage
-- ----------------------------
INSERT INTO `purchase_price_manage` VALUES ('3', '须知3', '33', '2017-03-15');
INSERT INTO `purchase_price_manage` VALUES ('4', '蚂蚁', '23', '2017-04-09');
INSERT INTO `purchase_price_manage` VALUES ('5', '松鼠12', '0', '2017-04-01');

-- ----------------------------
-- Table structure for `pur_price`
-- ----------------------------
DROP TABLE IF EXISTS `pur_price`;
CREATE TABLE `pur_price` (
  `pur_id` int(11) NOT NULL AUTO_INCREMENT,
  `pur_dept` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pur_person` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pur_price` double DEFAULT NULL,
  `pur_state` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`pur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='采购报价';

-- ----------------------------
-- Records of pur_price
-- ----------------------------
INSERT INTO `pur_price` VALUES ('2', '采购部', '换换', '44', '采购完成');
INSERT INTO `pur_price` VALUES ('3', '采购部', '换换', '77', '采购中');
INSERT INTO `pur_price` VALUES ('4', '采购部', '换换', '88', '已回复');
INSERT INTO `pur_price` VALUES ('5', null, null, null, '否');
INSERT INTO `pur_price` VALUES ('6', null, null, null, '否');
INSERT INTO `pur_price` VALUES ('7', null, null, null, '否');
INSERT INTO `pur_price` VALUES ('14', null, null, null, '否');
INSERT INTO `pur_price` VALUES ('15', null, null, null, '否');
INSERT INTO `pur_price` VALUES ('17', null, null, null, '否');

-- ----------------------------
-- Table structure for `receipt`
-- ----------------------------
DROP TABLE IF EXISTS `receipt`;
CREATE TABLE `receipt` (
  `receipt_id` int(11) NOT NULL AUTO_INCREMENT,
  `receipt_name` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `receipt_phone` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  `receipt_address` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `receipt_state` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `receipt_time` date DEFAULT NULL,
  PRIMARY KEY (`receipt_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='收货人信息';

-- ----------------------------
-- Records of receipt
-- ----------------------------
INSERT INTO `receipt` VALUES ('2', '佳节123', '18814569878', '法律框架爱上邓丽君按时打卡了房间', '否', null);
INSERT INTO `receipt` VALUES ('3', '小小', '18845236598', '阿斯顿发送到发送到发送到发送到发送到发多少', '否', null);
INSERT INTO `receipt` VALUES ('4', '小啊', '15541234587', '盛大发售的疯狂拉升的风景', '否', null);
INSERT INTO `receipt` VALUES ('5', '白小春', '15541023123', '阿斯顿发反反复复凤飞飞', '否', null);
INSERT INTO `receipt` VALUES ('6', '李青候', '18845698789', '阿斯顿发的说法都是发发', '否', null);
INSERT INTO `receipt` VALUES ('7', '李青候', '18845698789', '阿斯顿发的说法都是发发', '否', null);
INSERT INTO `receipt` VALUES ('14', '', '', '', '否', null);
INSERT INTO `receipt` VALUES ('15', '中岳', '18845698789', '中国香港', '否', null);
INSERT INTO `receipt` VALUES ('17', '霓裳', '15578986569', '撒点粉', '否', null);

-- ----------------------------
-- Table structure for `repertory`
-- ----------------------------
DROP TABLE IF EXISTS `repertory`;
CREATE TABLE `repertory` (
  `repo_id` int(11) NOT NULL,
  `areaid` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `area` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cityid` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `city` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `provinceid` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `province` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `repo_address` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`repo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='仓库';

-- ----------------------------
-- Records of repertory
-- ----------------------------

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `role_desc` varchar(400) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色信息';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '普通用户', '普通用户');
INSERT INTO `role` VALUES ('2', '超级管理员', '拥有最高权限');
INSERT INTO `role` VALUES ('4', '市场部门员工', '市场部门普通员工');

-- ----------------------------
-- Table structure for `role_action`
-- ----------------------------
DROP TABLE IF EXISTS `role_action`;
CREATE TABLE `role_action` (
  `ra_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `action_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ra_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色权限映射表';

-- ----------------------------
-- Records of role_action
-- ----------------------------
INSERT INTO `role_action` VALUES ('1', '1', '1');
INSERT INTO `role_action` VALUES ('2', '2', '2');
INSERT INTO `role_action` VALUES ('3', '3', '4');
INSERT INTO `role_action` VALUES ('5', '4', '3');

-- ----------------------------
-- Table structure for `role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `rm_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`rm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色-菜单映射表';

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('1', '1', '1');
INSERT INTO `role_menu` VALUES ('2', '2', '2');
INSERT INTO `role_menu` VALUES ('3', '2', '3');
INSERT INTO `role_menu` VALUES ('4', '2', '4');
INSERT INTO `role_menu` VALUES ('5', '2', '5');
INSERT INTO `role_menu` VALUES ('8', '4', '6');
INSERT INTO `role_menu` VALUES ('13', '2', '9');
INSERT INTO `role_menu` VALUES ('14', '1', '10');
INSERT INTO `role_menu` VALUES ('15', '1', '11');
INSERT INTO `role_menu` VALUES ('16', '1', '12');
INSERT INTO `role_menu` VALUES ('17', '1', '13');
INSERT INTO `role_menu` VALUES ('19', '1', '15');
INSERT INTO `role_menu` VALUES ('20', '1', '16');
INSERT INTO `role_menu` VALUES ('21', '1', '17');
INSERT INTO `role_menu` VALUES ('22', '1', '18');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_true_name` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_password` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_email` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_phone` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_birthday` date DEFAULT NULL,
  `user_sex` varchar(6) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_company` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_dept` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_dept_phone` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_dept_desc` varchar(400) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户信息';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'amin', '12345', '00@00', '13640433893', '2016-12-25', 'man', null, null, null, null);
INSERT INTO `user` VALUES ('2', '小明', '12345', '001@001', '18813838998', '2017-01-10', 'man', null, null, null, null);
INSERT INTO `user` VALUES ('3', '小红红', '12345', '00033@qq.com', '18224848959', '2017-01-03', 'woman', '', '', '12132', '');
INSERT INTO `user` VALUES ('4', '小赵', '12345', '004@003', '18828282837', '2017-01-10', 'man', null, null, null, null);
INSERT INTO `user` VALUES ('11', '换换', '12345', '111@112', '18838383838', '2017-01-23', 'woman', '', '', '', '');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `ur_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户角色映射表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '2');
INSERT INTO `user_role` VALUES ('2', '2', '1');
INSERT INTO `user_role` VALUES ('3', '3', '3');
INSERT INTO `user_role` VALUES ('4', '4', '4');
INSERT INTO `user_role` VALUES ('10', '11', '1');
