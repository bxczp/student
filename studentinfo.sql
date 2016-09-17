/*
Navicat MySQL Data Transfer

Source Server         : root-ASUS
Source Server Version : 50624
Source Host           : 127.0.0.1:3306
Source Database       : studentinfo

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-09-17 16:21:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_class
-- ----------------------------
DROP TABLE IF EXISTS `t_class`;
CREATE TABLE `t_class` (
  `classId` int(11) NOT NULL AUTO_INCREMENT,
  `className` varchar(20) DEFAULT NULL,
  `gradeId` int(11) DEFAULT NULL,
  `classDesc` text,
  PRIMARY KEY (`classId`),
  KEY `FK_t_class` (`gradeId`),
  CONSTRAINT `FK_t_class` FOREIGN KEY (`gradeId`) REFERENCES `t_grade` (`gradeId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_class
-- ----------------------------
INSERT INTO `t_class` VALUES ('3', '09计本', '2', '22');
INSERT INTO `t_class` VALUES ('4', '08计本', '1', '1112222');
INSERT INTO `t_class` VALUES ('7', '10计本', '3', '10年级计算机本科');
INSERT INTO `t_class` VALUES ('10', '10网本', '3', '222');
INSERT INTO `t_class` VALUES ('11', '08网本', '1', null);

-- ----------------------------
-- Table structure for t_datadic
-- ----------------------------
DROP TABLE IF EXISTS `t_datadic`;
CREATE TABLE `t_datadic` (
  `ddId` int(11) NOT NULL AUTO_INCREMENT,
  `ddTypeId` int(11) DEFAULT NULL,
  `ddValue` varchar(20) DEFAULT NULL,
  `ddDesc` text,
  PRIMARY KEY (`ddId`),
  KEY `FK_t_datadic` (`ddTypeId`),
  CONSTRAINT `FK_t_datadic` FOREIGN KEY (`ddTypeId`) REFERENCES `t_datadictype` (`ddTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=347 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_datadic
-- ----------------------------
INSERT INTO `t_datadic` VALUES ('1', '1', '男', '1');
INSERT INTO `t_datadic` VALUES ('3', '2', '中共党员', null);
INSERT INTO `t_datadic` VALUES ('4', '2', '中共预备党员', null);
INSERT INTO `t_datadic` VALUES ('5', '2', '共青团员', null);
INSERT INTO `t_datadic` VALUES ('6', '2', '民革党员', null);
INSERT INTO `t_datadic` VALUES ('7', '2', '民盟盟员', null);
INSERT INTO `t_datadic` VALUES ('8', '2', '民建会员', null);
INSERT INTO `t_datadic` VALUES ('9', '2', '民进会员', null);
INSERT INTO `t_datadic` VALUES ('10', '2', '农工党党员', null);
INSERT INTO `t_datadic` VALUES ('11', '2', '致公党党员', null);
INSERT INTO `t_datadic` VALUES ('12', '2', '九三学社社员', null);
INSERT INTO `t_datadic` VALUES ('13', '2', '台盟盟员', null);
INSERT INTO `t_datadic` VALUES ('14', '2', '无党派人士', null);
INSERT INTO `t_datadic` VALUES ('15', '2', '普通公民', null);
INSERT INTO `t_datadic` VALUES ('16', '2', '港澳同胞', null);
INSERT INTO `t_datadic` VALUES ('17', '2', '叛徒', null);
INSERT INTO `t_datadic` VALUES ('18', '2', '反革命分子', null);
INSERT INTO `t_datadic` VALUES ('334', '3', '汉族', null);
INSERT INTO `t_datadic` VALUES ('335', '3', '蒙古族', null);
INSERT INTO `t_datadic` VALUES ('336', '3', '回族', null);
INSERT INTO `t_datadic` VALUES ('337', '3', '藏族', null);
INSERT INTO `t_datadic` VALUES ('338', '3', '维吾尔族', null);
INSERT INTO `t_datadic` VALUES ('339', '3', '门巴族', null);
INSERT INTO `t_datadic` VALUES ('340', '3', '外国血统', null);
INSERT INTO `t_datadic` VALUES ('342', '3', '哈哈族', '11');
INSERT INTO `t_datadic` VALUES ('343', '1', '女变男', '1234');
INSERT INTO `t_datadic` VALUES ('345', '2', '我任务二', '认为');
INSERT INTO `t_datadic` VALUES ('346', '1', '女', null);

-- ----------------------------
-- Table structure for t_datadictype
-- ----------------------------
DROP TABLE IF EXISTS `t_datadictype`;
CREATE TABLE `t_datadictype` (
  `ddTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `ddTypeName` varchar(20) DEFAULT NULL,
  `ddTypeDesc` text,
  PRIMARY KEY (`ddTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_datadictype
-- ----------------------------
INSERT INTO `t_datadictype` VALUES ('1', '性别', 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxx');
INSERT INTO `t_datadictype` VALUES ('2', '政治面貌', 'zzzzzzzzzzzzzzz');
INSERT INTO `t_datadictype` VALUES ('3', '名族', 'mmmmmmmmmmmmmmmmmm');
INSERT INTO `t_datadictype` VALUES ('26', '玩儿玩儿', '玩儿玩儿玩儿2222');
INSERT INTO `t_datadictype` VALUES ('27', '是否33333333333333', '的所发生的3333333333');

-- ----------------------------
-- Table structure for t_grade
-- ----------------------------
DROP TABLE IF EXISTS `t_grade`;
CREATE TABLE `t_grade` (
  `gradeId` int(11) NOT NULL AUTO_INCREMENT,
  `gradeName` varchar(20) DEFAULT NULL,
  `gradeDesc` text,
  PRIMARY KEY (`gradeId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_grade
-- ----------------------------
INSERT INTO `t_grade` VALUES ('1', '08级', '111');
INSERT INTO `t_grade` VALUES ('2', '09级', '222333');
INSERT INTO `t_grade` VALUES ('3', '10级', '33333');
INSERT INTO `t_grade` VALUES ('6', '阿达', '啥的');
INSERT INTO `t_grade` VALUES ('8', '大大', '阿萨德44433');

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `studentId` varchar(40) NOT NULL,
  `stuNo` varchar(20) DEFAULT NULL,
  `stuName` varchar(20) DEFAULT NULL,
  `stuSex` varchar(10) DEFAULT NULL,
  `stuBirthday` date DEFAULT NULL,
  `stuRxsj` date DEFAULT NULL,
  `stuNation` varchar(20) DEFAULT NULL,
  `stuZzmm` varchar(20) DEFAULT NULL,
  `classId` int(11) DEFAULT NULL,
  `stuDesc` text,
  `stuPic` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`studentId`),
  KEY `FK_t_student` (`classId`),
  CONSTRAINT `FK_t_student` FOREIGN KEY (`classId`) REFERENCES `t_class` (`classId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES ('16725e3c-8868-479f-8a9e-d5dcc16f4ba9', '1232', '覆盖', '男', '2016-04-13', '2016-04-13', '汉族', '中共预备党员', '4', 'wert为vwrgs', '20160413082206.jpg');
INSERT INTO `t_student` VALUES ('16cb9251-9c4e-47a2-8084-95a1a236495b', '001', '美女1', '女', '2014-05-14', '2014-06-06', '汉族', '中共党员', '3', '美女12', '20160313190202.jpg');
INSERT INTO `t_student` VALUES ('19', '小三', '21', '女', '2014-05-14', '2014-05-14', '汉族', '中共党员', '4', '是', '20140502030158.jpg');
INSERT INTO `t_student` VALUES ('192ce390-3140-42cb-a49a-77b469ca9c67', '1232', '二', '女变男', '2012-03-13', '2016-03-13', '汉族', '中共预备党员', '11', '为儿女', '20160313164534.jpg');
INSERT INTO `t_student` VALUES ('2b95124a-27e2-47cd-9451-a06ebfc3e724', '436534', '问问', '男', '2016-03-13', '2016-03-13', '蒙古族', '共青团员', '11', '是非常到位噶尔V体改委一天GV和我公司的GV', '');
INSERT INTO `t_student` VALUES ('3397a33f-95fd-4028-af43-901a5d5f0a8f', '324234', '喂喂喂', '男', '2012-03-13', '2016-03-13', '藏族', '民建会员', '11', '阿道夫擦擦擦', '');
INSERT INTO `t_student` VALUES ('4', '11', '212', '男', '2014-04-08', '2014-04-05', '蒙古族', '中共预备党员', '4', '21', '20140429052236.JPG');
INSERT INTO `t_student` VALUES ('5', '11', '21', '男', '2014-04-08', '2014-04-05', '蒙古族', '中共预备党员', '4', '21', '20140429052433.JPG');
INSERT INTO `t_student` VALUES ('6', '21', '2', '男', '2014-04-08', '2014-04-05', '蒙古族', null, '4', null, null);
INSERT INTO `t_student` VALUES ('66dd12bd-2ad7-4178-97dc-7ca02e9ca8be', '436534', '问问', '男', '2016-03-13', '2016-03-13', '蒙古族', '共青团员', '11', '问问吾问无为谓', '');
INSERT INTO `t_student` VALUES ('a1cf592d-d9d4-4de1-b8ff-0db7e9852411', 'sss22', 'ss2', '女', '2014-05-08', '2014-05-10', '蒙古族', '共青团员', '3', '大2', '20140508022528.jpg');
INSERT INTO `t_student` VALUES ('a3d492b1-051d-455e-84a6-f7e39e88044a', '436534', '问问', '男', '2016-03-13', '2016-03-13', '蒙古族', '共青团员', '11', '是非常到位噶尔V', '20160313181724.jpg');
INSERT INTO `t_student` VALUES ('a687f776-0630-46f6-b27c-364c8f5fc2c1', '324234', '喂喂喂', '男', '2012-03-13', '2016-03-13', '藏族', '民建会员', '11', '阿道夫', '');
INSERT INTO `t_student` VALUES ('c1bb2e34-489f-46e5-967f-da83713d0b89', '568576', '表单是否', '女变男', '2016-03-13', '2016-03-13', '门巴族', '民进会员', '10', '', '');
INSERT INTO `t_student` VALUES ('d3ee2bc8-46d7-4593-847c-27177ae610b5', '21313czp', 'czp', '男', '2012-03-01', '2014-03-13', '维吾尔族', '共青团员', '4', '', '');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '11', '123456');
