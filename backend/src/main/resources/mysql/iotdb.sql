

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for connection
-- ----------------------------
DROP TABLE IF EXISTS `connection`;
CREATE TABLE `connection`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `host` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `port` int(11) NOT NULL,
  `username` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `password` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `alias` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of connection
-- ----------------------------

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `host` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  `creator` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  `create_time` int(11) NULL DEFAULT NULL,
  `device_name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device
-- ----------------------------

-- ----------------------------
-- Table structure for measurement
-- ----------------------------
DROP TABLE IF EXISTS `measurement`;
CREATE TABLE `measurement`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `measurement_name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  `host` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of measurement
-- ----------------------------

-- ----------------------------
-- Table structure for metricsgroup
-- ----------------------------
DROP TABLE IF EXISTS `metricsgroup`;
CREATE TABLE `metricsgroup`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `view_mode_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of metricsgroup
-- ----------------------------
INSERT INTO `metricsgroup` VALUES (1, 'jvm_thread', 1);
INSERT INTO `metricsgroup` VALUES (2, 'jvm_gc', 1);
INSERT INTO `metricsgroup` VALUES (3, 'jvm_mem', 1);
INSERT INTO `metricsgroup` VALUES (4, 'jvm_classes', 1);
INSERT INTO `metricsgroup` VALUES (5, 'cpu', 1);
INSERT INTO `metricsgroup` VALUES (6, 'mem', 1);
INSERT INTO `metricsgroup` VALUES (7, 'disk', 1);
INSERT INTO `metricsgroup` VALUES (8, 'insert_sql', 1);
INSERT INTO `metricsgroup` VALUES (9, 'select_sql', 1);

-- ----------------------------
-- Table structure for query
-- ----------------------------
DROP TABLE IF EXISTS `query`;
CREATE TABLE `query`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `query_name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `connection_id` int(11) NOT NULL,
  `sqls` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of query
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `host` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `port` int(11) NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for storage_group
-- ----------------------------
DROP TABLE IF EXISTS `storage_group`;
CREATE TABLE `storage_group`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  `creator` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `create_time` int(11) NOT NULL,
  `group_name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `host` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of storage_group
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `password` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'root', '$2a$10$Ct096uGB7FDpHaQ19ia6FetgOMJa4K92SrGdvn43pVdi4WP5JVS6m');

-- ----------------------------
-- Table structure for view_mode
-- ----------------------------
DROP TABLE IF EXISTS `view_mode`;
CREATE TABLE `view_mode`  (
  `id` int(11) NOT NULL,
  `name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of view_mode
-- ----------------------------
INSERT INTO `view_mode` VALUES (1, 'all_mode');
INSERT INTO `view_mode` VALUES (2, 'operator_mode');
INSERT INTO `view_mode` VALUES (3, 'dev_mode');

SET FOREIGN_KEY_CHECKS = 1;
