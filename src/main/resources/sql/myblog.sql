/*
 Navicat Premium Data Transfer

 Source Server         : new
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : myblog

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 05/03/2020 12:31:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hi_blog
-- ----------------------------
DROP TABLE IF EXISTS `hi_blog`;
CREATE TABLE `hi_blog`  (
  `id` bigint(20) NOT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `first_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `is_appreciate` bit(1) NULL DEFAULT NULL,
  `is_comment` bit(1) NULL DEFAULT NULL,
  `is_open_copyright` bit(1) NULL DEFAULT NULL,
  `is_published` bit(1) NULL DEFAULT NULL,
  `is_recommend` bit(1) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `view_counts` int(11) NULL DEFAULT NULL,
  `type_id` bigint(20) NULL DEFAULT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK80li70dmd3lisf1p0iy7k7c56`(`type_id`) USING BTREE,
  INDEX `FKcctisqo1yxcshhip06u36s0o2`(`user_id`) USING BTREE,
  CONSTRAINT `FK80li70dmd3lisf1p0iy7k7c56` FOREIGN KEY (`type_id`) REFERENCES `hi_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKcctisqo1yxcshhip06u36s0o2` FOREIGN KEY (`user_id`) REFERENCES `hi_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hi_blog_tags
-- ----------------------------
DROP TABLE IF EXISTS `hi_blog_tags`;
CREATE TABLE `hi_blog_tags`  (
  `blogs_id` bigint(20) NOT NULL,
  `tags_id` bigint(20) NOT NULL,
  INDEX `FKl8uygg9fmk1cpykrfj38ia7bq`(`tags_id`) USING BTREE,
  INDEX `FKk4fjii2dnbwp7oungch9fbk3t`(`blogs_id`) USING BTREE,
  CONSTRAINT `FKk4fjii2dnbwp7oungch9fbk3t` FOREIGN KEY (`blogs_id`) REFERENCES `hi_blog` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKl8uygg9fmk1cpykrfj38ia7bq` FOREIGN KEY (`tags_id`) REFERENCES `hi_tag` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hi_comment
-- ----------------------------
DROP TABLE IF EXISTS `hi_comment`;
CREATE TABLE `hi_comment`  (
  `id` bigint(20) NOT NULL,
  `admin_comment` bit(1) NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `head_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `blog_id` bigint(20) NULL DEFAULT NULL,
  `parent_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK171qgvsi9abx022bpghjk8qgm`(`blog_id`) USING BTREE,
  INDEX `FKjh4kdlx27pv8j30mstg2w35lm`(`parent_id`) USING BTREE,
  CONSTRAINT `FK171qgvsi9abx022bpghjk8qgm` FOREIGN KEY (`blog_id`) REFERENCES `hi_blog` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKjh4kdlx27pv8j30mstg2w35lm` FOREIGN KEY (`parent_id`) REFERENCES `hi_comment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hi_tag
-- ----------------------------
DROP TABLE IF EXISTS `hi_tag`;
CREATE TABLE `hi_tag`  (
  `id` bigint(20) NOT NULL,
  `tag_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hi_type
-- ----------------------------
DROP TABLE IF EXISTS `hi_type`;
CREATE TABLE `hi_type`  (
  `id` bigint(20) NOT NULL,
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hi_user
-- ----------------------------
DROP TABLE IF EXISTS `hi_user`;
CREATE TABLE `hi_user`  (
  `id` bigint(20) NOT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `head_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

