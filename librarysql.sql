/*
 Navicat Premium Data Transfer

 Source Server         : libraryjsp
 Source Server Type    : MySQL
 Source Server Version : 80040 (8.0.40)
 Source Host           : localhost:3306
 Source Schema         : librarysql

 Target Server Type    : MySQL
 Target Server Version : 80040 (8.0.40)
 File Encoding         : 65001

 Date: 18/05/2025 16:02:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for books
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books`  (
  `book_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `genre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `room_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_borrowed` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`book_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of books
-- ----------------------------
INSERT INTO `books` VALUES ('2025051701', '平凡的世界', '路遥', '小说', '1', 0);
INSERT INTO `books` VALUES ('2025051702', '围城', '钱钟书', '小说', '2', 0);
INSERT INTO `books` VALUES ('2025051703', '活着', '余华', '小说', '1', 1);
INSERT INTO `books` VALUES ('2025051704', '红楼梦', '曹雪芹', '小说', '3', 0);
INSERT INTO `books` VALUES ('2025051705', '三体', '刘慈欣', '科幻', '2', 1);
INSERT INTO `books` VALUES ('2025051706', '百年孤独', '加西亚·马尔克斯', '小说', '1', 0);
INSERT INTO `books` VALUES ('2025051707', '追风筝的人', '卡勒德·胡赛尼', '小说', '3', 0);
INSERT INTO `books` VALUES ('2025051708', '白鹿原', '陈忠实', '小说', '2', 1);
INSERT INTO `books` VALUES ('2025051709', '射雕英雄传', '金庸', '武侠', '1', 0);
INSERT INTO `books` VALUES ('2025051710', '解忧杂货店', '东野圭吾', '小说', '3', 1);
INSERT INTO `books` VALUES ('2025051801', 'helloworld', 'pinocchio', '小说', '06', 0);

-- ----------------------------
-- Table structure for user_login
-- ----------------------------
DROP TABLE IF EXISTS `user_login`;
CREATE TABLE `user_login`  (
  `userid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `userpassword` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `userrole` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_login
-- ----------------------------
INSERT INTO `user_login` VALUES ('admin', '123456', 'admin');
INSERT INTO `user_login` VALUES ('spy', '123456', 'user');

SET FOREIGN_KEY_CHECKS = 1;
