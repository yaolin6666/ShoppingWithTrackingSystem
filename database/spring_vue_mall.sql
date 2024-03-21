/*
 Navicat Premium Data Transfer

 Source Server         : Mysql_Local_VirtualLinux
 Source Server Type    : MySQL
 Source Server Version : 80300 (8.3.0)
 Source Host           : 192.168.42.131:3306
 Source Schema         : spring_vue_mall

 Target Server Type    : MySQL
 Target Server Version : 80300 (8.3.0)
 File Encoding         : 65001

 Date: 21/03/2024 23:04:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `customer_id` int NOT NULL AUTO_INCREMENT COMMENT '默认备注',
  `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '默认备注',
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '默认备注',
  `role` tinyint NOT NULL DEFAULT 0 COMMENT '默认备注',
  `customer_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `signatures` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `phone` bigint NULL DEFAULT NULL COMMENT '默认备注',
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `age` int NULL DEFAULT NULL COMMENT '默认备注',
  `sex` char(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `avatar` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `wallet` decimal(8, 2) NOT NULL DEFAULT 0.00 COMMENT '默认备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '默认备注',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '默认备注',
  PRIMARY KEY (`customer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '用户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (40, 'admin', 'admin', 1, '管理员', '员', 15615615611, '15615615611@qq.com', 25, '男', 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/7556a9e6-cef3-4143-81ee-4fb9ce645168.jpg', 0.00, '2023-03-19 09:44:07', '2024-03-07 20:26:17');
INSERT INTO `account` VALUES (41, 'zs123', '123456', 0, 'zs', 'zs', 1507171170, '3289948684@qq.com', 19, '男', 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/f3aa70d8-c286-4e14-a6ca-1dcbdcc50b10.jpg', 0.00, '2023-03-25 14:12:15', '2024-03-19 13:07:19');
INSERT INTO `account` VALUES (42, 'zs1234', '123456', 0, 'zs123', 'zs123', 15073171170, '3289948684@qq.com', 18, '男', 'http://localhost:8888/files/5965c0dfe6ed424eadcb33daf60d9ff6', 0.00, '2023-03-25 15:36:50', '2023-03-25 15:38:07');
INSERT INTO `account` VALUES (43, 'test', '123456', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, '2024-03-17 13:18:21', '2024-03-20 11:05:37');
INSERT INTO `account` VALUES (44, 'test1', '123456', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, '2024-03-20 19:19:39', '2024-03-21 11:37:20');

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `address_id` int NOT NULL AUTO_INCREMENT,
  `address_name` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '默认备注',
  `address_describe` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '默认备注',
  `address_phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '默认备注',
  `create_time` datetime NOT NULL COMMENT '默认备注',
  `update_time` datetime NOT NULL COMMENT '默认备注',
  `customer_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`address_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (64, '广西南宁', '张三', '15278504794', '2023-12-05 16:54:17', '2023-12-09 22:29:12', 11);
INSERT INTO `address` VALUES (65, '广西桂林', '李四', '15278504794', '2023-12-05 17:12:24', '2023-12-09 22:29:27', 26);
INSERT INTO `address` VALUES (66, '广东深圳', '优优', '15278504796', '2023-12-08 19:14:40', '2023-12-09 22:30:05', 11);
INSERT INTO `address` VALUES (67, '湖南长沙', '大姨', '12345678998', '2023-12-08 19:18:44', '2023-12-09 22:30:17', 26);
INSERT INTO `address` VALUES (68, '湖北武汉', '大牛', '15278504793', '2023-12-08 19:19:31', '2023-12-09 22:30:34', 11);
INSERT INTO `address` VALUES (72, '广西南宁', 'xx', '1917639172', '2023-12-11 17:27:27', '2023-12-11 17:27:27', 22);
INSERT INTO `address` VALUES (73, '测试地址', '测试', '15615615611', '2023-03-17 21:45:16', '2023-03-17 21:45:16', 37);
INSERT INTO `address` VALUES (74, '2222', '22', '22', '2023-03-19 09:34:19', '2023-03-19 09:34:19', 39);
INSERT INTO `address` VALUES (75, '源码乐园http://code51.cn', '源码乐园', '15615615611', '2023-03-19 09:45:41', '2023-03-19 09:45:41', 40);
INSERT INTO `address` VALUES (76, 'xxxxxxx', 'zs1234', '15073171170', '2023-03-25 15:38:26', '2023-03-25 15:38:26', 42);
INSERT INTO `address` VALUES (77, '123', '123', '3333', '2024-03-16 18:10:18', '2024-03-16 18:10:18', 40);
INSERT INTO `address` VALUES (78, '测试地址', '测试收货人', '11111', '2024-03-16 20:28:43', '2024-03-16 20:28:43', 40);
INSERT INTO `address` VALUES (79, '测试地址', '测试收货人', '12345678901', '2024-03-19 13:08:06', '2024-03-19 13:08:06', 41);

-- ----------------------------
-- Table structure for arginfo
-- ----------------------------
DROP TABLE IF EXISTS `arginfo`;
CREATE TABLE `arginfo`  (
  `arginfo_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NULL DEFAULT NULL,
  `shop_id` int NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`arginfo_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of arginfo
-- ----------------------------

-- ----------------------------
-- Table structure for arginfo_origin
-- ----------------------------
DROP TABLE IF EXISTS `arginfo_origin`;
CREATE TABLE `arginfo_origin`  (
  `arginfo_origin_id` int NOT NULL AUTO_INCREMENT,
  `arginfo_id` int NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`arginfo_origin_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of arginfo_origin
-- ----------------------------

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection`  (
  `collection_id` int NOT NULL AUTO_INCREMENT COMMENT '默认备注',
  `product_id` int NULL DEFAULT NULL COMMENT '默认备注',
  `product_name` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `create_time` datetime NOT NULL COMMENT '默认备注',
  `update_time` datetime NOT NULL COMMENT '默认备注',
  `product_price` int NULL DEFAULT NULL,
  `product_image` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `product_color` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `customer_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`collection_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '收藏夹' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of collection
-- ----------------------------
INSERT INTO `collection` VALUES (43, 121, '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', '2024-03-21 17:47:53', '2024-03-21 17:47:53', 5000, 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', NULL, 40);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NULL DEFAULT NULL,
  `customer_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `comment_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `comment_nr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `product_color` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `product_num` int NULL DEFAULT NULL,
  `comment_pf` int NULL DEFAULT NULL,
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 79 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '评价' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for deliverinfo_origin
-- ----------------------------
DROP TABLE IF EXISTS `deliverinfo_origin`;
CREATE TABLE `deliverinfo_origin`  (
  `deliverinfo_origin_id` int NOT NULL AUTO_INCREMENT,
  `deliverinfo_id` int NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`deliverinfo_origin_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of deliverinfo_origin
-- ----------------------------

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`  (
  `feedback_id` int NOT NULL AUTO_INCREMENT,
  `feedback_bt` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `feedback_ny` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`feedback_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback` VALUES (1, '购物车问题', '不好', '2023-03-09 01:18:29', '2023-12-09 22:33:24');
INSERT INTO `feedback` VALUES (3, '界面', '不够好看', '2023-03-09 01:41:50', '2023-12-09 22:32:59');
INSERT INTO `feedback` VALUES (4, '价格', '有点贵', '2023-03-09 01:42:28', '2023-12-09 22:33:16');
INSERT INTO `feedback` VALUES (5, '我的问题', '自己的问题', '2023-03-09 01:45:11', '2023-03-09 01:45:11');
INSERT INTO `feedback` VALUES (6, '商品', '有点少', '2023-03-09 15:20:14', '2023-12-09 22:33:50');
INSERT INTO `feedback` VALUES (24, '发货', '太慢', '2023-12-09 22:34:29', '2023-12-09 22:34:29');
INSERT INTO `feedback` VALUES (25, '111', '111', '2023-03-17 20:34:51', '2023-03-17 20:34:51');

-- ----------------------------
-- Table structure for img
-- ----------------------------
DROP TABLE IF EXISTS `img`;
CREATE TABLE `img`  (
  `img_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `product_image` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `product_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` date NOT NULL,
  `update_time` date NOT NULL,
  `product_price` int NULL DEFAULT NULL,
  `customer_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`img_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 634 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '浏览历史表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of img
-- ----------------------------
INSERT INTO `img` VALUES (610, 121, 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', '2024-03-20', '2024-03-20', 5000, 40);
INSERT INTO `img` VALUES (611, 121, 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', '2024-03-20', '2024-03-20', 5000, 40);
INSERT INTO `img` VALUES (612, 121, 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', '2024-03-20', '2024-03-20', 5000, 40);
INSERT INTO `img` VALUES (613, 121, 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', '2024-03-20', '2024-03-20', 5000, 40);
INSERT INTO `img` VALUES (614, 121, 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', '2024-03-20', '2024-03-20', 5000, 40);
INSERT INTO `img` VALUES (615, 121, 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', '2024-03-20', '2024-03-20', 5000, 40);
INSERT INTO `img` VALUES (616, 121, 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', '2024-03-20', '2024-03-20', 5000, 40);
INSERT INTO `img` VALUES (617, 121, 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', '2024-03-20', '2024-03-20', 5000, 40);
INSERT INTO `img` VALUES (618, 121, 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', '2024-03-20', '2024-03-20', 5000, 40);
INSERT INTO `img` VALUES (619, 121, 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', '2024-03-20', '2024-03-20', 5000, 40);
INSERT INTO `img` VALUES (620, 121, 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', '2024-03-20', '2024-03-20', 5000, 40);
INSERT INTO `img` VALUES (621, 121, 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', '2024-03-20', '2024-03-20', 5000, 40);
INSERT INTO `img` VALUES (622, 121, 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', '2024-03-20', '2024-03-20', 5000, 40);
INSERT INTO `img` VALUES (623, 121, 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', '2024-03-20', '2024-03-20', 5000, 40);
INSERT INTO `img` VALUES (624, 121, 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', '2024-03-20', '2024-03-20', 5000, 40);
INSERT INTO `img` VALUES (625, 121, 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', '2024-03-20', '2024-03-20', 5000, 40);
INSERT INTO `img` VALUES (626, 121, 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', '2024-03-20', '2024-03-20', 5000, 40);
INSERT INTO `img` VALUES (627, 121, 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', '2024-03-21', '2024-03-21', 5000, 40);
INSERT INTO `img` VALUES (628, 121, 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', '2024-03-21', '2024-03-21', 5000, 40);
INSERT INTO `img` VALUES (629, 121, 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', '2024-03-21', '2024-03-21', 5000, 40);
INSERT INTO `img` VALUES (630, 121, 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', '2024-03-21', '2024-03-21', 5000, 40);
INSERT INTO `img` VALUES (631, 121, 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', '2024-03-21', '2024-03-21', 5000, 40);
INSERT INTO `img` VALUES (632, 121, 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', '2024-03-21', '2024-03-21', 5000, 40);
INSERT INTO `img` VALUES (633, 121, 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', '2024-03-21', '2024-03-21', 5000, 40);

-- ----------------------------
-- Table structure for master
-- ----------------------------
DROP TABLE IF EXISTS `master`;
CREATE TABLE `master`  (
  `order_id` int NOT NULL AUTO_INCREMENT COMMENT '默认备注',
  `order_sn` bigint NULL DEFAULT NULL COMMENT '默认备注',
  `customer_id` int NULL DEFAULT NULL COMMENT '默认备注',
  `shipping_user` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `province` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `city` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `district` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `payment_method` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `order_money` decimal(8, 2) NULL DEFAULT 0.00 COMMENT '默认备注',
  `shipping_money` decimal(8, 2) NULL DEFAULT 0.00 COMMENT '默认备注',
  `payment_money` decimal(8, 2) NULL DEFAULT 0.00 COMMENT '默认备注',
  `shipping_comp_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `shipping_sn` bigint NULL DEFAULT NULL COMMENT '默认备注',
  `shipping_time` datetime NULL DEFAULT NULL COMMENT '默认备注',
  `receive_time` datetime NULL DEFAULT NULL COMMENT '默认备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '默认备注',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '默认备注',
  `product_image` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `product_color` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `product_name` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `product_phones` bigint NULL DEFAULT NULL COMMENT '默认备注',
  `product_message` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `product_price` int NULL DEFAULT NULL COMMENT '默认备注',
  `product_num` int NULL DEFAULT NULL COMMENT '默认备注',
  `product_id` int NULL DEFAULT NULL,
  `product_erm` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL COMMENT '状态 100 101  201 202 203 ',
  `discount` int NULL DEFAULT 0,
  `shop_id` int NULL DEFAULT NULL,
  `team_id` int NULL DEFAULT NULL COMMENT '参与团购团队',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 232 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '订单主表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of master
-- ----------------------------
INSERT INTO `master` VALUES (217, NULL, 40, '源码乐园', NULL, NULL, NULL, '源码乐园http://code51.cn', '匿名付款', 0.00, 0.00, 0.00, NULL, NULL, NULL, NULL, '2024-03-18 21:20:10', '2024-03-20 19:28:11', 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '银色', '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', 15615615611, NULL, 5000, 1, 121, 'http://localhost:8888/files/b5be97cce6394928b9f195cca8fe0c60', 300, 0, 40, NULL);
INSERT INTO `master` VALUES (229, NULL, 41, '测试收货人', NULL, NULL, NULL, '测试地址', NULL, 0.00, 0.00, 0.00, NULL, NULL, NULL, NULL, '2024-03-19 13:08:13', '2024-03-19 15:01:19', 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '银色', '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', 12345678901, NULL, 5000, 1, 121, 'http://localhost:8888/files/b5be97cce6394928b9f195cca8fe0c60', 300, 0, 40, NULL);
INSERT INTO `master` VALUES (231, NULL, 40, '源码乐园', NULL, NULL, NULL, '源码乐园http://code51.cn', '匿名付款', 0.00, 0.00, 0.00, '111', 111, '2024-03-21 22:12:11', '2024-03-21 22:12:20', '2024-03-20 19:30:27', '2024-03-21 14:26:53', 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '银色', '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', 15615615611, NULL, 5000, 1, 121, 'http://localhost:8888/files/b5be97cce6394928b9f195cca8fe0c60', 101, 0, 43, NULL);

-- ----------------------------
-- Table structure for order_origin
-- ----------------------------
DROP TABLE IF EXISTS `order_origin`;
CREATE TABLE `order_origin`  (
  `order_origin_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`order_origin_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_origin
-- ----------------------------

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `product_id` int NOT NULL AUTO_INCREMENT COMMENT '默认备注',
  `product_code` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `product_name` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '默认备注',
  `brand_id` int NULL DEFAULT NULL COMMENT '默认备注',
  `one_category_id` int NULL DEFAULT NULL COMMENT '默认备注',
  `two_category_id` int NULL DEFAULT NULL COMMENT '默认备注',
  `product_price` int NOT NULL COMMENT '默认备注',
  `weight` int NOT NULL COMMENT '默认备注',
  `three_category_id` int NULL DEFAULT NULL,
  `production_date` datetime NULL DEFAULT NULL COMMENT '默认备注',
  `validity_date` int NULL DEFAULT NULL COMMENT '默认备注',
  `descript` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '默认备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '默认备注',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '默认备注',
  `product_color` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `product_num` int NULL DEFAULT 1 COMMENT '默认备注',
  `product_image` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `product_cs` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '默认备注',
  `product_cd` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '默认备注',
  `product_cf` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '默认备注',
  `product_cg` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '默认备注',
  `product_im` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '默认备注',
  `product_imd` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '默认备注',
  `product_imf` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '默认备注',
  `product_img` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '默认备注',
  `product_mnum` int NOT NULL,
  `product_home` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `product_md` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `product_mf` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `product_mg` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `product_erm` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `customer_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 367 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '商品信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (121, NULL, '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', 114, 1, 100, 5000, 50, 1, NULL, NULL, '商品具有中国强制11性产品认证（CCC）编号，符合国家CCC认证标准。', '2023-03-18 15:53:25', '2024-03-21 21:53:30', NULL, 1, 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '黑色', '银色', '白色', '蓝色', 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/6b4f6965-985c-4960-84ee-4329acc04a0e.jpg', 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/c358e3c3-6e06-4dc2-a435-37530aa04f5d.jpg', 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/ffdea54c-9f04-4462-a9bb-100aad212173.jpg', 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/07526c20-44a1-4de8-8533-0108ef0d3add.jpg', 500, NULL, 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/f74e3bc1-7d44-4273-bed4-39e814f6ee83.jpg', 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/fcb9e4bf-d37a-4b05-ad83-ecdd66285dce.jpg', 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/67bbceab-fe77-42a7-94eb-af4abc2fbe86.png', 'http://localhost:8888/files/b5be97cce6394928b9f195cca8fe0c60', 43);

-- ----------------------------
-- Table structure for promptly
-- ----------------------------
DROP TABLE IF EXISTS `promptly`;
CREATE TABLE `promptly`  (
  `order_detail_id` int NOT NULL AUTO_INCREMENT COMMENT '默认备注',
  `order_id` int NULL DEFAULT NULL COMMENT '默认备注',
  `product_id` int NOT NULL COMMENT '默认备注',
  `product_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `product_num` int NULL DEFAULT NULL COMMENT '默认备注',
  `product_price` decimal(8, 2) NULL DEFAULT NULL COMMENT '默认备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '默认备注',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '默认备注',
  `product_image` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `product_color` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `address` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `product_phones` bigint NULL DEFAULT NULL COMMENT '默认备注',
  `payment_method` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `product_message` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `shipping_user` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `order_money` int NULL DEFAULT NULL COMMENT '默认备注',
  `shipping_money` int NULL DEFAULT NULL COMMENT '默认备注',
  `payment_money` int NULL DEFAULT NULL COMMENT '默认备注',
  `shipping_comp_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  PRIMARY KEY (`order_detail_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '订单详情表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of promptly
-- ----------------------------

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
  `record_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `record_nr` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`record_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 159 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES (103, 'test', '退出后台', '2024-03-17 13:21:01', '2024-03-17 13:21:01');
INSERT INTO `record` VALUES (104, 'test', '登录后台', '2024-03-17 13:24:46', '2024-03-17 13:24:46');
INSERT INTO `record` VALUES (105, 'admin', '登录后台', '2024-03-17 22:32:09', '2024-03-17 22:32:09');
INSERT INTO `record` VALUES (106, 'admin', '登录前台', '2024-03-17 22:46:42', '2024-03-17 22:46:42');
INSERT INTO `record` VALUES (107, 'admin', '登录后台', '2024-03-18 14:36:21', '2024-03-18 14:36:21');
INSERT INTO `record` VALUES (108, 'admin', '退出后台', '2024-03-18 15:50:23', '2024-03-18 15:50:23');
INSERT INTO `record` VALUES (109, 'admin', '登录后台', '2024-03-18 15:52:38', '2024-03-18 15:52:38');
INSERT INTO `record` VALUES (110, 'admin', '登录前台', '2024-03-19 00:14:08', '2024-03-19 00:14:08');
INSERT INTO `record` VALUES (111, 'admin', '登录后台', '2024-03-19 13:08:27', '2024-03-19 13:08:27');
INSERT INTO `record` VALUES (112, 'admin', '退出后台', '2024-03-19 13:12:01', '2024-03-19 13:12:01');
INSERT INTO `record` VALUES (113, 'test', '登录后台', '2024-03-19 13:12:08', '2024-03-19 13:12:08');
INSERT INTO `record` VALUES (114, 'test', '退出后台', '2024-03-19 13:17:33', '2024-03-19 13:17:33');
INSERT INTO `record` VALUES (115, 'admin', '登录后台', '2024-03-19 13:17:39', '2024-03-19 13:17:39');
INSERT INTO `record` VALUES (116, 'zs123', '登录前台', '2024-03-19 18:40:52', '2024-03-19 18:40:52');
INSERT INTO `record` VALUES (117, 'admin', '退出后台', '2024-03-19 20:27:41', '2024-03-19 20:27:41');
INSERT INTO `record` VALUES (118, 'test', '登录后台', '2024-03-19 20:27:48', '2024-03-19 20:27:48');
INSERT INTO `record` VALUES (119, 'test', '退出后台', '2024-03-19 20:44:31', '2024-03-19 20:44:31');
INSERT INTO `record` VALUES (120, 'admin', '登录后台', '2024-03-19 20:44:36', '2024-03-19 20:44:36');
INSERT INTO `record` VALUES (121, 'admin', '退出后台', '2024-03-19 22:40:40', '2024-03-19 22:40:40');
INSERT INTO `record` VALUES (122, 'test', '登录后台', '2024-03-19 22:40:47', '2024-03-19 22:40:47');
INSERT INTO `record` VALUES (123, 'test', '退出后台', '2024-03-19 22:58:50', '2024-03-19 22:58:50');
INSERT INTO `record` VALUES (124, 'admin', '登录后台', '2024-03-19 22:59:01', '2024-03-19 22:59:01');
INSERT INTO `record` VALUES (125, 'admin', '登录后台', '2024-03-20 18:36:13', '2024-03-20 18:36:13');
INSERT INTO `record` VALUES (126, 'admin', '退出后台', '2024-03-20 18:51:30', '2024-03-20 18:51:30');
INSERT INTO `record` VALUES (127, 'test', '登录后台', '2024-03-20 18:51:46', '2024-03-20 18:51:46');
INSERT INTO `record` VALUES (128, 'test', '退出后台', '2024-03-20 18:52:03', '2024-03-20 18:52:03');
INSERT INTO `record` VALUES (129, 'admin', '登录后台', '2024-03-20 18:52:08', '2024-03-20 18:52:08');
INSERT INTO `record` VALUES (130, 'admin', '退出后台', '2024-03-20 19:05:17', '2024-03-20 19:05:17');
INSERT INTO `record` VALUES (131, 'test', '登录后台', '2024-03-20 19:05:23', '2024-03-20 19:05:23');
INSERT INTO `record` VALUES (132, 'test', '退出后台', '2024-03-20 19:05:38', '2024-03-20 19:05:38');
INSERT INTO `record` VALUES (133, 'test', '登录后台', '2024-03-20 19:05:46', '2024-03-20 19:05:46');
INSERT INTO `record` VALUES (134, 'test', '退出后台', '2024-03-20 19:08:31', '2024-03-20 19:08:31');
INSERT INTO `record` VALUES (135, 'admin', '登录后台', '2024-03-20 19:08:36', '2024-03-20 19:08:36');
INSERT INTO `record` VALUES (136, 'admin', '退出后台', '2024-03-20 19:18:23', '2024-03-20 19:18:23');
INSERT INTO `record` VALUES (137, 'admin', '登录后台', '2024-03-20 19:18:31', '2024-03-20 19:18:31');
INSERT INTO `record` VALUES (138, 'admin', '退出后台', '2024-03-20 19:19:32', '2024-03-20 19:19:32');
INSERT INTO `record` VALUES (139, 'admin', '登录后台', '2024-03-20 19:20:08', '2024-03-20 19:20:08');
INSERT INTO `record` VALUES (140, 'admin', '退出后台', '2024-03-20 21:50:10', '2024-03-20 21:50:10');
INSERT INTO `record` VALUES (141, 'test1', '登录后台', '2024-03-20 21:50:18', '2024-03-20 21:50:18');
INSERT INTO `record` VALUES (142, 'test1', '退出后台', '2024-03-20 23:08:39', '2024-03-20 23:08:39');
INSERT INTO `record` VALUES (143, 'admin', '登录后台', '2024-03-20 23:08:46', '2024-03-20 23:08:46');
INSERT INTO `record` VALUES (144, 'admin', '退出后台', '2024-03-20 23:11:51', '2024-03-20 23:11:51');
INSERT INTO `record` VALUES (145, 'test1', '登录后台', '2024-03-20 23:12:00', '2024-03-20 23:12:00');
INSERT INTO `record` VALUES (146, 'admin', '登录后台', '2024-03-21 18:22:49', '2024-03-21 18:22:49');
INSERT INTO `record` VALUES (147, 'admin', '退出后台', '2024-03-21 18:40:07', '2024-03-21 18:40:07');
INSERT INTO `record` VALUES (148, 'test1', '登录后台', '2024-03-21 18:40:15', '2024-03-21 18:40:15');
INSERT INTO `record` VALUES (149, 'test1', '退出后台', '2024-03-21 18:42:54', '2024-03-21 18:42:54');
INSERT INTO `record` VALUES (150, 'admin', '登录后台', '2024-03-21 18:42:59', '2024-03-21 18:42:59');
INSERT INTO `record` VALUES (151, 'admin', '退出后台', '2024-03-21 21:05:40', '2024-03-21 21:05:40');
INSERT INTO `record` VALUES (152, 'test', '登录后台', '2024-03-21 21:05:56', '2024-03-21 21:05:56');
INSERT INTO `record` VALUES (153, 'test', '退出后台', '2024-03-21 21:07:39', '2024-03-21 21:07:39');
INSERT INTO `record` VALUES (154, 'test1', '登录后台', '2024-03-21 21:07:46', '2024-03-21 21:07:46');
INSERT INTO `record` VALUES (155, 'test1', '退出后台', '2024-03-21 21:13:02', '2024-03-21 21:13:02');
INSERT INTO `record` VALUES (156, 'admin', '登录后台', '2024-03-21 21:13:07', '2024-03-21 21:13:07');
INSERT INTO `record` VALUES (157, 'admin', '退出后台', '2024-03-21 21:52:45', '2024-03-21 21:52:45');
INSERT INTO `record` VALUES (158, 'test', '登录后台', '2024-03-21 21:52:51', '2024-03-21 21:52:51');

-- ----------------------------
-- Table structure for refund
-- ----------------------------
DROP TABLE IF EXISTS `refund`;
CREATE TABLE `refund`  (
  `refund_id` int NOT NULL AUTO_INCREMENT COMMENT '默认备注',
  `order_sn` bigint NULL DEFAULT NULL COMMENT '默认备注',
  `customer_id` int NULL DEFAULT NULL COMMENT '默认备注',
  `shipping_user` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `province` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `city` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `district` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `payment_method` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `order_money` decimal(8, 2) NULL DEFAULT 0.00 COMMENT '默认备注',
  `shipping_money` decimal(8, 2) NULL DEFAULT 0.00 COMMENT '默认备注',
  `payment_money` decimal(8, 2) NULL DEFAULT 0.00 COMMENT '默认备注',
  `shipping_comp_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `shipping_sn` bigint NULL DEFAULT NULL COMMENT '默认备注',
  `shipping_time` datetime NULL DEFAULT NULL COMMENT '默认备注',
  `receive_time` datetime NULL DEFAULT NULL COMMENT '默认备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '默认备注',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '默认备注',
  `product_image` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `product_color` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `product_name` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `product_phones` bigint NULL DEFAULT NULL COMMENT '默认备注',
  `product_message` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `product_price` int NULL DEFAULT NULL COMMENT '默认备注',
  `product_num` int NULL DEFAULT NULL COMMENT '默认备注',
  `product_id` int NULL DEFAULT NULL,
  `product_erm` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `refund_nr` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `refund_tx` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `refund_ls` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `refund_sm` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `refund_zt` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `refund_img` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `shop_id` int NULL DEFAULT NULL,
  `discount` int NULL DEFAULT NULL,
  PRIMARY KEY (`refund_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 152 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '订单主表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of refund
-- ----------------------------
INSERT INTO `refund` VALUES (150, NULL, 41, '测试收货人', NULL, NULL, NULL, '测试地址', '匿名付款', 0.00, 0.00, 0.00, NULL, NULL, NULL, NULL, '2024-03-19 19:33:30', '2024-03-19 12:00:22', 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '银色', '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', 12345678901, NULL, 5000, 1, 121, 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '仅退款', NULL, '111111111111', '商品有损坏', '未收到货', NULL, NULL, NULL);
INSERT INTO `refund` VALUES (151, NULL, 41, '测试收货人', NULL, NULL, NULL, '测试地址', NULL, 0.00, 0.00, 0.00, NULL, NULL, NULL, NULL, '2024-03-19 20:03:56', '2024-03-19 20:03:56', 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '银色', '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', 12345678901, NULL, 5000, 1, 121, 'http://localhost:8888/files/b5be97cce6394928b9f195cca8fe0c60', '退款退货', NULL, '1111111', '不喜欢', '未收到货', 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/aeb2477c-713a-451d-ac5f-36104df1534b.jpg', 40, 0);

-- ----------------------------
-- Table structure for register
-- ----------------------------
DROP TABLE IF EXISTS `register`;
CREATE TABLE `register`  (
  `register_id` int NOT NULL AUTO_INCREMENT,
  `account_id` int NULL DEFAULT NULL,
  `certification_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `img1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `img2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `img3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`register_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of register
-- ----------------------------
INSERT INTO `register` VALUES (5, 44, 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/4ecacba0-4692-48bf-b451-67c987d21674.jpg', 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/0420cae2-1858-454c-ab31-8ed14d659c9a.jpg', 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/bcbc71ed-ce15-42bb-b98d-a86df5c107fd.jpg', 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/26f546a1-c61b-403b-b656-cb7922aa33d3.jpg', '2024-03-21 21:08:01', '2024-03-21 21:08:01');

-- ----------------------------
-- Table structure for shopping
-- ----------------------------
DROP TABLE IF EXISTS `shopping`;
CREATE TABLE `shopping`  (
  `order_detail_id` int NOT NULL AUTO_INCREMENT COMMENT '默认备注',
  `order_id` int NULL DEFAULT NULL COMMENT '默认备注',
  `product_id` int NULL DEFAULT NULL COMMENT '默认备注',
  `product_name` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '默认备注',
  `product_num` int NOT NULL COMMENT '默认备注',
  `product_price` decimal(8, 2) NOT NULL COMMENT '默认备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '默认备注',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '默认备注',
  `product_image` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '默认备注',
  `product_color` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '默认备注',
  `address` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `product_phones` bigint NULL DEFAULT NULL COMMENT '默认备注',
  `payment_method` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `product_message` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `shipping_user` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `order_money` int NULL DEFAULT NULL COMMENT '默认备注',
  `shipping_money` int NULL DEFAULT NULL COMMENT '默认备注',
  `payment_money` int NULL DEFAULT NULL COMMENT '默认备注',
  `shipping_comp_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '默认备注',
  `product_mnum` int NOT NULL,
  `product_erm` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `customer_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`order_detail_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 273 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '购物车' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of shopping
-- ----------------------------
INSERT INTO `shopping` VALUES (272, NULL, 121, '【今日立省200 赢耳机】vivo iQOO U3千元5G大电池学生游戏智能老人手机爱酷官方旗舰店vivoiqoou3 vivou3', 1, 5000.00, '2024-03-21 19:45:53', '2024-03-21 19:45:53', 'https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/e3dd777e-d6d4-40d5-a90a-19d424a9e968.jpg', '银色', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 500, 'http://localhost:8888/files/b5be97cce6394928b9f195cca8fe0c60', 40);

-- ----------------------------
-- Table structure for team
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team`  (
  `team_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NULL DEFAULT NULL,
  `shop_id` int NULL DEFAULT NULL,
  `team_now` int NULL DEFAULT NULL,
  `team_max` int NULL DEFAULT NULL,
  `team_need` int NULL DEFAULT NULL,
  `team_attend_id` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `team_attend_name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `expire_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`team_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '参与拼单人员' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of team
-- ----------------------------

-- ----------------------------
-- Table structure for template
-- ----------------------------
DROP TABLE IF EXISTS `template`;
CREATE TABLE `template`  (
  `template_id` int NOT NULL AUTO_INCREMENT,
  `shop_id` int NULL DEFAULT NULL,
  `team_need` int NULL DEFAULT NULL,
  `team_max` int NULL DEFAULT NULL,
  `discount` int NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`template_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '折扣模版' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of template
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
