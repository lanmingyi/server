/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50723 (5.7.23)
 Source Host           : localhost:3306
 Source Schema         : server

 Target Server Type    : MySQL
 Target Server Version : 50723 (5.7.23)
 File Encoding         : 65001

 Date: 24/05/2023 16:37:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_depart
-- ----------------------------
DROP TABLE IF EXISTS `sys_depart`;
CREATE TABLE `sys_depart`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父机构ID',
  `depart_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机构/部门名称',
  `depart_name_en` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文名',
  `depart_name_abbr` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缩写',
  `depart_order` int(11) NULL DEFAULT 0 COMMENT '排序',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `org_category` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '机构类别 1公司，2组织机构，2岗位',
  `org_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构类型 1一级部门 2子部门',
  `org_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机构编码',
  `mobile` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `fax` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '传真',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `memo` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态（1启用，0不启用）',
  `del_flag` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除状态（0，正常，1已删除）',
  `qywx_identifier` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对接企业微信的ID',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_depart_org_code`(`org_code`) USING BTREE,
  INDEX `idx_sd_parent_id`(`parent_id`) USING BTREE,
  INDEX `idx_sd_depart_order`(`depart_order`) USING BTREE,
  INDEX `idx_sd_org_code`(`org_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组织机构表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_depart
-- ----------------------------
INSERT INTO `sys_depart` VALUES ('0822964310654dc3a4da94fc9281b3da', 'c6d7cb4deeac411cb3384b1b31278596', '人力资源行政中心', NULL, NULL, 0, NULL, '2', '2', 'A01A08', NULL, NULL, NULL, NULL, NULL, '0', NULL, 'admin', '2023-01-30 16:20:08', NULL, NULL);
INSERT INTO `sys_depart` VALUES ('1460c5b4696e4c3284be0e5aafa3b5ff', 'c6d7cb4deeac411cb3384b1b31278596', '财务中心', NULL, NULL, 0, NULL, '2', '2', 'A01A07', NULL, NULL, NULL, NULL, NULL, '0', NULL, 'admin', '2023-01-30 16:19:51', NULL, NULL);
INSERT INTO `sys_depart` VALUES ('b93212e813234c23a2c8745587def638', 'c6d7cb4deeac411cb3384b1b31278596', '研发中心', NULL, NULL, 0, NULL, '2', '2', 'A01A06', NULL, NULL, NULL, NULL, NULL, '0', NULL, 'admin', '2023-01-30 16:17:20', NULL, NULL);
INSERT INTO `sys_depart` VALUES ('c6d7cb4deeac411cb3384b1b31278596', '', '山东明成科技有限公司', NULL, NULL, 0, NULL, '1', '1', 'A01', NULL, NULL, NULL, NULL, NULL, '0', NULL, 'admin', '2019-02-11 14:21:51', 'admin', '2020-05-02 18:21:27');

-- ----------------------------
-- Table structure for sys_depart_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_depart_role`;
CREATE TABLE `sys_depart_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `depart_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门id',
  `role_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门角色名称',
  `role_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门角色编码',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_depart_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_depart_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_depart_role_permission`;
CREATE TABLE `sys_depart_role_permission`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `depart_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门id',
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `permission_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限id',
  `data_rule_ids` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据权限ids',
  `operate_date` datetime NULL DEFAULT NULL COMMENT '操作时间',
  `operate_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作ip',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_sdrp_role_per_id`(`role_id`, `permission_id`) USING BTREE,
  INDEX `idx_sdrp_role_id`(`role_id`) USING BTREE,
  INDEX `idx_sdrp_per_id`(`permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门角色权限表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_depart_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for sys_depart_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_depart_role_user`;
CREATE TABLE `sys_depart_role_user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `drole_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门角色用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_depart_role_user
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dict_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典名称',
  `dict_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典编码',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `del_flag` int(1) NULL DEFAULT NULL COMMENT '删除状态',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `type` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '字典类型0为string,1为number',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_sd_dict_code`(`dict_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dict_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典id',
  `item_text` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典项文本',
  `item_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典项值',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `sort_order` int(10) NULL DEFAULT NULL COMMENT '排序',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态（1启用 0不启用）',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_sdi_role_dict_id`(`dict_id`) USING BTREE,
  INDEX `idx_sdi_role_sort_order`(`sort_order`) USING BTREE,
  INDEX `idx_sdi_status`(`status`) USING BTREE,
  INDEX `idx_sdi_dict_val`(`dict_id`, `item_value`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `log_type` int(2) NULL DEFAULT NULL COMMENT '日志类型（1登录日志，2操作日志）',
  `log_content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志内容',
  `operate_type` int(2) NULL DEFAULT NULL COMMENT '操作类型',
  `userid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户账号',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户名称',
  `ip` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP',
  `method` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求java方法',
  `request_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求路径',
  `request_param` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求参数',
  `request_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求类型',
  `cost_time` bigint(20) NULL DEFAULT NULL COMMENT '耗时',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_sl_userid`(`userid`) USING BTREE,
  INDEX `idx_sl_log_type`(`log_type`) USING BTREE,
  INDEX `idx_sl_operate_type`(`operate_type`) USING BTREE,
  INDEX `idx_sl_create_time`(`create_time`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统日志表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1647521208421330946', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '192.168.1.10', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-16 16:44:00', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1647524387645087745', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-16 16:56:38', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1647524490208403457', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-16 16:57:03', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1647524647368974337', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-16 16:57:40', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1647525538981535745', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-16 17:01:13', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1647531811953238017', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-16 17:26:08', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1650532826373713921', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-25 00:11:06', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1650867519480729602', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-25 22:21:03', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1650875941940957186', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-25 22:54:31', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1650876097167953922', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-25 22:55:08', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1650880593931603969', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-25 23:13:00', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1650880856318873602', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-25 23:14:03', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1650883818474770433', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-25 23:25:49', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1650885542707007489', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-25 23:32:40', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1650887681227427841', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-25 23:41:10', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1650888334137954306', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-25 23:43:46', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1650888640406032385', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-25 23:44:59', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1650888775290654722', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-25 23:45:31', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1651215383272648705', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-26 21:23:20', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1651215687175139330', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-26 21:24:33', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1651218388856369153', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-26 21:35:17', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1651218488450117633', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-26 21:35:40', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1651218895335354369', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-26 21:37:17', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1651219043608195073', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-26 21:37:53', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1651219230686736386', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-26 21:38:37', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1651219332616712194', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-26 21:39:02', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1651219798012489729', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-26 21:40:53', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1651220065986572290', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-26 21:41:57', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1651220150531158018', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-26 21:42:17', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1651220984149078017', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-26 21:45:35', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1651221191851012097', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-26 21:46:25', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1651221425431801857', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-26 21:47:21', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1651247031435423745', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-26 23:29:06', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1651587968913166338', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-27 22:03:51', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1651917715354472450', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-28 19:54:09', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1651919097734156289', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-28 19:59:39', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1651920016248983553', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-28 20:03:18', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1651922389772062722', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-28 20:12:44', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1652134754611888129', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-29 10:16:35', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1652205432308498434', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-04-29 14:57:26', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1653991153998594050', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-05-04 13:13:15', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1660494062135336961', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-05-22 11:53:30', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1660560649836060674', 2, '修改角色ID: f6817f48af4fb3af11b9e8bf182f618b 的权限配置，操作人： admin', 2, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-05-22 16:18:05', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1660560890945626113', 2, '修改角色ID: f6817f48af4fb3af11b9e8bf182f618b 的权限配置，操作人： admin', 2, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-05-22 16:19:03', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1660560917210357761', 2, '修改角色ID: f6817f48af4fb3af11b9e8bf182f618b 的权限配置，操作人： admin', 2, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-05-22 16:19:09', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1660561393637154818', 2, '修改角色ID: f6817f48af4fb3af11b9e8bf182f618b 的权限配置，操作人： admin', 2, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-05-22 16:21:03', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1660562507619131394', 2, '修改角色ID: f6817f48af4fb3af11b9e8bf182f618b 的权限配置，操作人： admin', 2, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-05-22 16:25:28', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1660562767154274305', 2, '修改角色ID: f6817f48af4fb3af11b9e8bf182f618b 的权限配置，操作人： admin', 2, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-05-22 16:26:30', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1660563089750777858', 2, '修改角色ID: f6817f48af4fb3af11b9e8bf182f618b 的权限配置，操作人： admin', 2, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-05-22 16:27:47', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1660563163436310530', 2, '修改角色ID: f6817f48af4fb3af11b9e8bf182f618b 的权限配置，操作人： admin', 2, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-05-22 16:28:05', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1660563338657554434', 2, '修改角色ID: f6817f48af4fb3af11b9e8bf182f618b 的权限配置，操作人： admin', 2, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-05-22 16:28:46', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1660563926833192962', 2, '修改角色ID: f6817f48af4fb3af11b9e8bf182f618b 的权限配置，操作人： admin', 2, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-05-22 16:31:07', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1660564429046571009', 2, '修改角色ID: f6817f48af4fb3af11b9e8bf182f618b 的权限配置，操作人： admin', 2, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-05-22 16:33:06', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1660564498923675650', 2, '修改角色ID: f6817f48af4fb3af11b9e8bf182f618b 的权限配置，操作人： admin', 2, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-05-22 16:33:23', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1660809861639200770', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-05-23 08:48:22', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1660909242895851521', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-05-23 15:23:16', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1661213537142857730', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-05-24 11:32:26', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1661237929918881793', 2, '修改角色ID: f6817f48af4fb3af11b9e8bf182f618b 的权限配置，操作人： admin', 2, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-05-24 13:09:21', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1661261574468820994', 1, '用户名: admin,登录成功！', NULL, 'admin', '兰明易', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-05-24 14:43:19', NULL, NULL);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单标题',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件',
  `component_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件名字',
  `redirect` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '一级菜单跳转地址',
  `menu_type` int(11) NULL DEFAULT NULL COMMENT '菜单类型(0:一级菜单; 1:子菜单:2:按钮权限)',
  `perms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单权限编码',
  `perms_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '权限策略1显示2禁用',
  `sort_no` double(8, 2) NULL DEFAULT NULL COMMENT '菜单排序',
  `always_show` tinyint(1) NULL DEFAULT NULL COMMENT '聚合子路由: 1是0否',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `is_route` tinyint(1) NULL DEFAULT 1 COMMENT '是否路由菜单: 0:不是  1:是（默认值1）',
  `is_leaf` tinyint(1) NULL DEFAULT NULL COMMENT '是否叶子节点:    1:是   0:不是',
  `keep_alive` tinyint(1) NULL DEFAULT NULL COMMENT '是否缓存该页面:    1:是   0:不是',
  `hidden` tinyint(1) NULL DEFAULT 0 COMMENT '是否隐藏路由: 0否,1是',
  `hide_tab` tinyint(1) NULL DEFAULT NULL COMMENT '是否隐藏tab: 0否,1是',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(1) NULL DEFAULT 0 COMMENT '删除状态 0正常 1已删除',
  `rule_flag` int(3) NULL DEFAULT 0 COMMENT '是否添加数据权限1是0否',
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '按钮权限状态(0无效1有效)',
  `internal_or_external` tinyint(1) NULL DEFAULT NULL COMMENT '外链菜单打开方式 0/内部打开 1/外部打开',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_sp_parent_id`(`parent_id`) USING BTREE,
  INDEX `idx_sp_is_route`(`is_route`) USING BTREE,
  INDEX `idx_sp_is_leaf`(`is_leaf`) USING BTREE,
  INDEX `idx_sp_sort_no`(`sort_no`) USING BTREE,
  INDEX `idx_sp_del_flag`(`del_flag`) USING BTREE,
  INDEX `idx_sp_menu_type`(`menu_type`) USING BTREE,
  INDEX `idx_sp_hidden`(`hidden`) USING BTREE,
  INDEX `idx_sp_status`(`status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('00a2a0ae65cdca5e93209cdbde97cbe6', '2e42e3835c2b44ec9f7bc26c146ee531', '成功', '/result/Success', 'result/Success', NULL, NULL, 1, NULL, NULL, 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2023-01-29 11:18:10', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('020b06793e4de2eee0007f603000c769', 'f0675b52d89100ee88472b6800754a08', 'ViserChartDemo', '/custom/report/ViserChartDemo', 'custom/report/ViserChartDemo', NULL, NULL, 1, NULL, NULL, 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-04-03 19:08:53', 'admin', '2023-01-02 13:30:33', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('024f1fd1283dc632458976463d8984e1', '700b7f95165c46cc7a78bf227aa8fed3', 'Tomcat信息', '/modules/monitor/TomcatInfo', 'modules/monitor/TomcatInfo', NULL, NULL, 1, NULL, NULL, 4.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-04-02 09:44:29', 'admin', '2023-01-01 21:43:39', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('043780fa095ff1b2bec4dc406d76f023', '1610527584667201537', '表格合计', '/table/TableTotal', 'table/TableTotal', NULL, NULL, 1, NULL, '1', 9.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-08-14 10:28:46', 'admin', '2023-01-04 16:31:48', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('05b3c82ddb2536a4a5ee1a4c46b5abef', '540a2936940846cb98114ffb0d145cb8', '用户列表', '/examples/list/UserList', 'examples/list/UserList', NULL, NULL, 1, NULL, NULL, 3.00, 0, NULL, 1, 1, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2023-01-02 13:28:36', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('0620e402857b8c5b605e1ad9f4b89350', '1610527584667201537', '异步树列表', '/table/TreeTableDemo', 'table/TreeTableDemo', NULL, NULL, 1, NULL, '0', 8.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-05-13 17:30:30', 'admin', '2023-01-04 16:29:40', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('078f9558cdeab239aecb2bda1a8ed0d1', 'fb07ca05a3e13674dbf6d3245956da2e', '文章', '/list/search/article', 'examples/list/EditableTable', NULL, NULL, 1, NULL, NULL, 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-02-12 14:00:34', 'admin', '2023-01-28 16:21:44', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('08e6b9dc3c04489c8e1ff2ce6f105aa4', '', '系统监控', '/dashboard3', 'layouts/RouteView', NULL, NULL, 0, NULL, NULL, 71.00, 0, 'dashboard', 1, 0, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2022-12-17 17:47:46', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('1166535831146504193', '1601084565314310145', '对象存储', '/document/oss/OSSFileList', 'document/oss/OSSFileList', NULL, NULL, 1, NULL, '1', 2.00, 0, '', 1, 1, 0, 0, 0, NULL, 'admin', '2019-08-28 02:19:50', 'admin', '2023-01-06 09:33:53', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1174506953255182338', 'd7d6e2e4e2934f2c9385a623fd98c6f3', '职务管理', '/system/PositionList', 'system/PositionList', NULL, NULL, 1, NULL, '1', 2.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-09-19 10:14:13', 'admin', '2023-01-01 10:43:31', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1174590283938041857', 'd7d6e2e4e2934f2c9385a623fd98c6f3', '通讯录', '/system/AddressList', 'system/AddressList', NULL, NULL, 1, NULL, '1', 3.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-09-19 15:45:21', 'admin', '2023-01-01 10:36:54', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1192318987661234177', 'e41b69c57a941a3bbcce45032fe57605', '系统编码规则', '/system/SysFillRuleList', 'system/SysFillRuleList', NULL, NULL, 1, NULL, '1', 3.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-11-07 13:52:53', 'admin', '2022-12-17 17:52:13', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1205097455226462210', '', '报表设计', '/big/screen', 'layouts/RouteView', NULL, NULL, 0, NULL, '1', 50.00, 0, 'area-chart', 1, 0, 0, 0, 0, NULL, 'admin', '2019-12-12 20:09:58', 'admin', '2022-12-17 17:44:11', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1205098241075453953', '1205097455226462210', '生产销售监控', '{{ window._CONFIG[\'domainURL\'] }}/test/bigScreen/templat/index1', 'layouts/IframePageView', NULL, NULL, 1, NULL, '1', 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-12-12 20:13:05', 'admin', '2022-12-17 11:50:28', 0, 0, '1', 1);
INSERT INTO `sys_permission` VALUES ('1205306106780364802', '1205097455226462210', '智慧物流监控', '{{ window._CONFIG[\'domainURL\'] }}/test/bigScreen/templat/index2', 'layouts/IframePageView', NULL, NULL, 1, NULL, '1', 2.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-12-13 09:59:04', 'admin', '2022-12-17 17:32:25', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1209731624921534465', 'e41b69c57a941a3bbcce45032fe57605', '多数据源管理', '/system/SysDataSourceList', 'system/SysDataSourceList', NULL, NULL, 1, NULL, '1', 6.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-12-25 15:04:30', 'admin', '2022-12-17 17:52:26', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1224641973866467330', 'e41b69c57a941a3bbcce45032fe57605', '系统校验规则', '/system/SysCheckRuleList', 'system/SysCheckRuleList', NULL, NULL, 1, NULL, '1', 5.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-11-07 13:52:53', 'admin', '2022-12-17 17:52:19', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1260928341675982849', '3f915b2769fc80648e92d04e84ca059d', '添加按钮', NULL, NULL, NULL, NULL, 2, 'user:add', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, NULL, 'admin', '2020-05-14 21:41:58', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1260929666434318338', '3f915b2769fc80648e92d04e84ca059d', '用户编辑', NULL, NULL, NULL, NULL, 2, 'user:edit', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, NULL, 'admin', '2020-05-14 21:47:14', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1260931366557696001', '3f915b2769fc80648e92d04e84ca059d', '表单性别可见', '', NULL, NULL, NULL, 2, 'user:sex', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, NULL, 'admin', '2020-05-14 21:53:59', 'admin', '2020-05-14 21:57:00', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1260933542969458689', '3f915b2769fc80648e92d04e84ca059d', '禁用生日字段', NULL, NULL, NULL, NULL, 2, 'user:form:birthday', '2', 1.00, 0, NULL, 1, 1, 0, 0, NULL, NULL, 'admin', '2020-05-14 22:02:38', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1265162119913824258', '08e6b9dc3c04489c8e1ff2ce6f105aa4', '路由网关', '/system/GatewayRouteList', 'system/GatewayRouteList', NULL, NULL, 1, NULL, '1', 2.00, 0, NULL, 1, 1, 0, 0, 0, NULL, NULL, '2020-05-26 14:05:30', 'admin', '2023-01-01 21:47:17', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1280464606292099074', '1603666184591998978', '图片裁剪', '/custom/ImagCropper', 'custom/ImagCropper', NULL, NULL, 1, NULL, '1', 4.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2020-07-07 19:32:06', 'admin', '2022-12-16 16:20:27', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1287715272999944193', '1610527584667201537', 'Vxe表格', '/custom/SurelyTable', 'layouts/RouteView', NULL, NULL, 1, NULL, '1', 11.00, 0, '', 1, 0, 0, 0, 0, NULL, 'admin', '2020-07-27 19:43:40', 'admin', '2023-01-04 16:39:04', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1287715783966834689', '1287715272999944193', '普通示例', '/table/SurelyTableDemo', 'table/SurelyTableDemo', NULL, NULL, 1, NULL, '1', 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2020-07-27 19:45:42', 'admin', '2023-01-04 16:42:34', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1287716451494510593', '1287715272999944193', '布局模板', '/table/layout-demo/Index', 'table/layout-demo/Index', NULL, NULL, 1, NULL, '1', 2.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2020-07-27 19:48:21', 'admin', '2023-01-04 16:44:25', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1287718919049691137', '1287715272999944193', '即时保存', '/table/demo/JSBCDemo', 'table/demo/JSBCDemo', NULL, NULL, 1, NULL, '1', 3.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2020-07-27 19:57:36', 'admin', '2023-01-04 16:44:36', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1287718938179911682', '1287715272999944193', '弹出子表', '/table/demo/PopupSubTable', 'table/demo/PopupSubTable', NULL, NULL, 1, NULL, '1', 4.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2020-07-27 19:57:41', 'admin', '2023-01-04 16:44:48', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1287718956957810689', '1287715272999944193', '无痕刷新', '/table/demo/SocketReload', 'table/demo/SocketReload', NULL, NULL, 1, NULL, '1', 5.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2020-07-27 19:57:44', 'admin', '2023-01-04 16:44:59', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('13212d3416eb690c2e1d5033166ff47a', '2e42e3835c2b44ec9f7bc26c146ee531', '失败', '/result/Error', 'result/Error', NULL, NULL, 1, NULL, NULL, 2.00, 0, NULL, 1, 1, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2023-01-29 11:18:21', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('1335960713267093506', '1205097455226462210', '积木报表设计', '{{ window._CONFIG[\'domainURL\'] }}/jmreport/list?token=${token}', 'layouts/IframePageView', NULL, NULL, 1, NULL, '1', 3.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2020-12-07 22:53:50', 'admin', '2022-12-17 17:32:29', 0, 0, '1', 1);
INSERT INTO `sys_permission` VALUES ('1352200630711652354', 'f0675b52d89100ee88472b6800754a08', 'Redis监控', '{{ window._CONFIG[\'domainURL\'] }}/jmreport/view/1352160857479581696?token=${token}', 'layouts/IframePageView', NULL, NULL, 1, NULL, '1', 3.00, 0, '', 1, 1, 0, 0, 0, NULL, 'admin', '2021-01-21 18:25:28', 'admin', '2022-12-17 17:30:18', 0, 0, '1', 1);
INSERT INTO `sys_permission` VALUES ('1365187528377102337', '1610527584667201537', '一对多Vxe表格', '/table/TableListForVxeTable', 'table/TableListForVxeTable', NULL, NULL, 1, NULL, '1', 5.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2021-02-26 14:30:45', 'admin', '2023-01-04 16:00:59', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1367a93f2c410b169faa7abcbad2f77c', '6e73eb3c26099c191bf03852ee1310a1', '基本设置', '/account/settings/BaseSetting', 'account/settings/BaseSetting', 'account-settings-base', NULL, 1, 'BaseSettings', NULL, NULL, 0, NULL, 1, 1, 0, 1, 0, NULL, NULL, '2018-12-26 18:58:35', 'admin', '2023-01-01 21:11:27', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('1387612436586065922', '1603667153006460930', '第三方APP消息测试', '/custom/ThirdAppMessageTest', 'custom/ThirdAppMessageTest', NULL, NULL, 1, NULL, '1', 3.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2021-04-29 11:39:20', 'admin', '2022-12-16 16:24:08', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1400726868091015170', '4148ec82b6acd69f470bea75fe41c357', 'demo添加功能', NULL, NULL, NULL, NULL, 2, 'demo:add', '1', NULL, 0, NULL, 1, 1, 0, 0, NULL, NULL, 'admin', '2021-06-04 16:11:24', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1404684556047024130', '08e6b9dc3c04489c8e1ff2ce6f105aa4', '在线用户', '/system/UserOnlineList', 'system/UserOnlineList', NULL, NULL, 1, NULL, '1', 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2021-06-15 14:17:51', 'admin', '2023-01-01 21:46:23', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1494641317580423169', '1610527584667201537', '表格合计New', '/table/TableTotalNew', 'table/TableTotalNew', NULL, NULL, 1, NULL, '1', 10.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-02-18 19:53:54', 'admin', '2023-01-04 16:32:14', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1563449103292620801', '', '地球视觉', '/vision/earth', 'layouts/RouteView', NULL, NULL, 0, NULL, '1', 20.00, 0, 'global', 1, 0, 0, 0, 0, NULL, 'admin', '2022-08-27 16:51:28', 'admin', '2022-12-16 10:46:44', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1565232925109776386', '1603666184591998978', '组织架构图', '/system/OrgChart', 'system/OrgChart', NULL, NULL, 1, NULL, '1', 5.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-09-01 14:59:45', 'admin', '2022-12-16 16:25:17', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1565579914460655617', '1603666184591998978', '组织架构图测试', '/system/OrgChartTest', 'system/OrgChartTest', NULL, NULL, 1, NULL, '1', 6.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-09-02 13:58:33', 'admin', '2022-12-16 16:25:32', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1572423290645192705', '', '预警系统', '/earth/warning', 'layouts/RouteView', NULL, NULL, 0, NULL, '1', 21.00, 0, 'bell', 1, 0, 0, 0, 0, NULL, 'admin', '2022-09-21 11:11:41', 'admin', '2022-12-16 11:25:11', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1572479217918353410', '1572423290645192705', '台风预警系统', '/earth/warning/TyphoonWarningSystem', 'earth/warning/TyphoonWarningSystem', NULL, NULL, 1, NULL, '1', 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-09-21 14:53:55', 'admin', '2022-12-16 11:25:39', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1574207524774191106', '', '系统管理', '/system', 'Layout', NULL, NULL, 0, NULL, '1', 1.00, 0, 'environment', 1, 0, 0, 0, 0, NULL, 'admin', '2022-09-26 09:21:36', 'admin', '2023-05-24 15:07:17', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1575662879864889345', '', '计算机视觉', '/vision/computer', 'layouts/RouteView', NULL, NULL, 0, NULL, '1', 2.00, 0, 'camera', 1, 0, 0, 0, 0, NULL, 'admin', '2022-09-30 09:44:40', 'admin', '2023-05-24 15:07:35', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1575664633943793665', '1574207524774191106', '菜单管理', '/system/menu', 'system/menu/index', NULL, NULL, 1, NULL, '1', 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-09-30 09:51:38', 'admin', '2022-12-31 17:56:40', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1578930711367081986', '1575662879864889345', '目标检测', '/demo/form-design', 'demo/FormDesign', NULL, NULL, 1, NULL, '1', 2.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-10-09 10:09:51', 'admin', '2023-04-29 16:35:13', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1578935030602620930', '1575662879864889345', '风格迁移', '/vision/computer/StyleTransfer', 'vision/computer/StyleTransfer', NULL, NULL, 1, NULL, '1', 12.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-10-09 10:27:01', 'admin', '2022-12-16 11:01:47', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1578939092404363265', '1575662879864889345', '人脸识别', '/vision/computer/FaceRecognition', 'vision/computer/FaceRecognition', NULL, NULL, 1, NULL, '1', 10.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-10-09 10:43:10', 'admin', '2022-12-16 11:06:46', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1578943012690288642', '1575662879864889345', '姿态估计', '/vision/computer/PoseDetection', 'vision/computer/PoseDetection', NULL, NULL, 1, NULL, '1', 11.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-10-09 10:58:44', 'admin', '2022-12-16 11:07:18', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1581889121679065089', '1575662879864889345', '图像处理', '/demo/workflow', 'demo/workflow', NULL, NULL, 1, NULL, '1', 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-10-17 14:05:31', 'admin', '2023-04-29 10:35:21', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1584876317008211969', '1563449103292620801', '滑坡检测', '/vision/earth/LandslideDetection', 'vision/earth/LandslideDetection', NULL, NULL, 1, NULL, '1', 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-10-25 19:55:34', 'admin', '2023-05-22 14:33:01', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1593159248304910337', 'e41b69c57a941a3bbcce45032fe57605', '移动开发', '/system/sysAppBasics/index', 'system/sysAppBasics/index', NULL, NULL, 1, NULL, '1', 2.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-11-17 16:28:59', 'admin', '2022-12-17 17:53:39', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1600401879309492226', '', '移动应用', '/APP', 'APP', NULL, NULL, 0, NULL, '1', 100.00, 0, 'mobile', 1, 0, 0, 1, 0, NULL, 'admin', '2022-12-07 16:08:37', 'admin', '2022-12-07 22:08:09', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1600405828720664577', '1600401879309492226', '包罗万象', '/TabsView', 'TabsView', NULL, NULL, 1, NULL, '1', 1.00, 0, NULL, 1, 0, 0, 0, 0, NULL, 'admin', '2022-12-07 16:24:18', 'admin', '2022-12-07 23:12:00', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1600407326720532481', '1600405828720664577', '考勤打卡', '/../application/submission/mileageSubmit', '../application/submission/mileageSubmit', NULL, NULL, 1, NULL, '1', 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-12-07 16:30:15', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1600481964395401218', '1600401879309492226', '常用服务', '/TabsView', 'TabsView', NULL, NULL, 1, NULL, '1', 2.00, 0, NULL, 1, 0, 0, 0, 0, NULL, 'admin', '2022-12-07 21:26:50', 'admin', '2022-12-07 23:27:37', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1600491157110198274', '1600401879309492226', '财务专区', '/TabsView', 'TabsView', NULL, NULL, 1, NULL, '1', 4.00, 0, NULL, 1, 0, 0, 0, 0, NULL, 'admin', '2022-12-07 22:03:22', 'admin', '2022-12-07 23:41:07', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1600491234256031746', '1600401879309492226', '办公专区', '/TabsView', 'TabsView', NULL, NULL, 1, NULL, '1', 3.00, 0, NULL, 1, 0, 0, 0, 0, NULL, 'admin', '2022-12-07 22:03:41', 'admin', '2022-12-07 23:41:11', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1600491744866406401', '1600491157110198274', '财务管理', '/../application/financial/financial', '../application/financial/financial', NULL, NULL, 1, NULL, '1', 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-12-07 22:05:42', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1600491844065890305', '1600491157110198274', '财务审核', '/../application/financial/financial_approval', '../application/financial/financial_approval', NULL, NULL, 1, NULL, '1', 2.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-12-07 22:06:06', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1600492187956875265', '1600405828720664577', '更多', '/../function/function', '../function/function', NULL, NULL, 1, NULL, '1', 10.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-12-07 22:07:28', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1600512638653370370', '1600481964395401218', '工作日志', '/../application/journal/journal', '../application/journal/journal', NULL, NULL, 1, NULL, '1', 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-12-07 23:28:44', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1600512762196594690', '1600491234256031746', '新闻', '/../application/notice/news', '../application/notice/news', NULL, NULL, 1, NULL, '1', 3.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-12-07 23:29:13', 'admin', '2022-12-07 23:30:15', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1600513531704578049', '1600481964395401218', '我的日程', '/../application/scheduler/index', '../application/scheduler/index', NULL, NULL, 1, NULL, '1', 11.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-12-07 23:32:17', 'admin', '2022-12-08 08:48:54', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1600513628593000450', '1600481964395401218', '写邮件', '/../application/emails/emails', '../application/emails/emails', NULL, NULL, 1, NULL, '1', 2.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-12-07 23:32:40', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1600513706917433345', '1600481964395401218', '写日志', '/../application/journal/index/index', '../application/journal/index/index', NULL, NULL, 1, NULL, '1', 3.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-12-07 23:32:58', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1600513792892276737', '1600481964395401218', '我的待办', '/../application/task/task?id=3', '../application/task/task?id=3', NULL, NULL, 1, NULL, '1', 4.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-12-07 23:33:19', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1600513868712710146', '1600481964395401218', '我的预定', '/../application/metting/metting_reserveofmine', '../application/metting/metting_reserveofmine', NULL, NULL, 1, NULL, '1', 5.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-12-07 23:33:37', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1600513948098301953', '1600481964395401218', '我的会议', '/../application/metting/metting_joinofmine', '../application/metting/metting_joinofmine', NULL, NULL, 1, NULL, '1', 6.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-12-07 23:33:56', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1600514019946729473', '1600481964395401218', '待办传阅', '/../application/circulate/circulate?id=1', '../application/circulate/circulate?id=1', NULL, NULL, 1, NULL, '1', 7.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-12-07 23:34:13', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1600514090562031617', '1600481964395401218', '已办传阅', '/../application/circulate/circulate?id=2', '../application/circulate/circulate?id=2', NULL, NULL, 1, NULL, '1', 8.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-12-07 23:34:30', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1600514161013755905', '1600481964395401218', '公文管理', '/../application/document/document', '../application/document/document', NULL, NULL, 1, NULL, '1', 9.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-12-07 23:34:47', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1600514444519346177', '1600481964395401218', '会议室预定', '/../application/metting/index', '../application/metting/index', NULL, NULL, 1, NULL, '1', 10.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-12-07 23:35:54', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1600514529277841409', '1600491157110198274', '借款申请', '/../application/financial/approval_add?index=0', '../application/financial/approval_add?index=0', NULL, NULL, 1, NULL, '1', 3.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-12-07 23:36:15', 'admin', '2022-12-07 23:37:16', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1600514611859492866', '1600491157110198274', '报销申请', '/../application/financial/approval_add?index=2', '../application/financial/approval_add?index=2', NULL, NULL, 1, NULL, '1', 4.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-12-07 23:36:34', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1600514671540244481', '1600491157110198274', '付款申请', '/../application/financial/approval_add?index=1', '../application/financial/approval_add?index=1', NULL, NULL, 1, NULL, '1', 5.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-12-07 23:36:48', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1600514992127676417', '1600491234256031746', '发起流程', '/../application/flow/flowStart', '../application/flow/flowStart', NULL, NULL, 1, NULL, '1', 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-12-07 23:38:05', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1600515061358858242', '1600491234256031746', '流程审批', '/../application/flow/flow', '../application/flow/flow', NULL, NULL, 1, NULL, '1', 2.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-12-07 23:38:21', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1600515153641934850', '1600491234256031746', '通知公告', '/../application/notice/notice', '../application/notice/notice', NULL, NULL, 1, NULL, '1', 3.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-12-07 23:38:43', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1600515229357510658', '1600491234256031746', '参与任务', '/../application/task/task?id=1', '../application/task/task?id=1', NULL, NULL, 1, NULL, '1', 4.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-12-07 23:39:01', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1600515291789725698', '1600491234256031746', '电子签名', '/../application/sign/sign', '../application/sign/sign', NULL, NULL, 1, NULL, '1', 5.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-12-07 23:39:16', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1600515368176390145', '1600491234256031746', '电子印章', '/../application/seal/seal', '../application/seal/seal', NULL, NULL, 1, NULL, '1', 6.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-12-07 23:39:35', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1601084565314310145', '', '文档管理', '/document', 'layouts/RouteView', NULL, NULL, 0, NULL, '1', 32.00, 0, 'file', 1, 0, 0, 0, 0, NULL, 'admin', '2022-12-09 13:21:22', 'admin', '2022-12-17 17:41:24', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1601085440464228353', '1601084565314310145', '文件管理', '/document/fileManage', 'layouts/RouteView', NULL, NULL, 1, NULL, '1', 1.00, 0, NULL, 1, 0, 0, 0, 0, NULL, 'admin', '2022-12-09 13:24:50', 'admin', '2023-01-07 11:05:24', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1601088640764317697', '1601085440464228353', '文件分类管理', '/document/category/index', 'document/category/index', NULL, NULL, 1, NULL, '1', 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-12-09 13:37:33', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1601088759228239874', '1601085440464228353', '本地文件管理', '/document/archive/index', 'document/archive/index', NULL, NULL, 1, NULL, '1', 2.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-12-09 13:38:02', 'admin', '2023-01-07 10:42:19', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1601088864790482946', '1601085440464228353', '阿里云文件管理', '/document/oss/index', 'document/oss/index', NULL, NULL, 1, NULL, '1', 3.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-12-09 13:38:27', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1601088948240355330', '1601085440464228353', '分布式文件管理', '/document/minio/index', 'document/minio/index', NULL, NULL, 1, NULL, '1', 4.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2022-12-09 13:38:47', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1603663701748580354', '2a470fc0c3954d9dbb61de6d80846549', '常用页面', '/CommonPage', 'layouts/RouteView', NULL, NULL, 1, NULL, '1', 4.00, 0, NULL, 1, 0, 0, 0, 0, NULL, 'admin', '2022-12-16 16:09:56', 'admin', '2023-05-24 15:06:19', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1603666184591998978', '2a470fc0c3954d9dbb61de6d80846549', '图片', '/CommonPhoto', 'layouts/RouteView', NULL, NULL, 1, NULL, '1', 5.00, 0, NULL, 1, 0, 0, 0, 0, NULL, 'admin', '2022-12-16 16:19:48', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1603667153006460930', '2a470fc0c3954d9dbb61de6d80846549', '测试', '/CommonTest', 'layouts/Routeview', NULL, NULL, 1, NULL, '1', 6.00, 0, NULL, 1, 0, 0, 0, 0, NULL, 'admin', '2022-12-16 16:23:39', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1610527584667201537', '', '高级表格', '/table', 'layouts/RouteView', NULL, NULL, 0, NULL, '1', 31.00, 0, 'table', 1, 0, 0, 0, 0, NULL, 'admin', '2023-01-04 14:44:33', 'admin', '2023-01-04 14:45:16', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1660495711465693185', '1563449103292620801', 'openlayers', '/vision/earth/openlayers', 'vision/earth/map/Openlayers', NULL, NULL, 1, NULL, '0', 2.00, 0, 'Menu', 1, 1, 0, 0, 0, NULL, 'admin', '2023-05-22 12:00:03', 'admin', '2023-05-24 11:44:09', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1661237852886294530', '1563449103292620801', '台风预警系统', '/vision/earth/map/typhoon-warning-system', 'vision/earth/map/TyphoonWarningSystem', NULL, NULL, 1, NULL, '0', 3.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2023-05-24 13:09:03', NULL, NULL, 0, 0, '', 0);
INSERT INTO `sys_permission` VALUES ('190c2b43bec6a5f7a4194a85db67d96a', '1574207524774191106', '角色管理', '/system/role', 'system/role/index', NULL, NULL, 1, NULL, NULL, 2.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-04-17 15:13:56', 'admin', '2023-05-22 15:03:59', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('1a0811914300741f4e11838ff37a1d3a', '3f915b2769fc80648e92d04e84ca059d', '手机号禁用', NULL, NULL, NULL, NULL, 2, 'user:form:phone', '2', 1.00, 0, NULL, 0, 1, NULL, 0, NULL, NULL, 'admin', '2019-05-11 17:19:30', 'admin', '2019-05-11 18:00:22', 0, 0, '1', NULL);
INSERT INTO `sys_permission` VALUES ('200006f0edf145a2b50eacca07585451', 'fb07ca05a3e13674dbf6d3245956da2e', '应用', '/list/search/application', 'examples/list/EditableTable', NULL, NULL, 1, NULL, NULL, 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-02-12 14:02:51', 'admin', '2023-01-28 16:21:50', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('265de841c58907954b8877fb85212622', '1603666184591998978', '图片拖拽排序', '/custom/ImgDragSort', 'custom/ImgDragSort', NULL, NULL, 1, NULL, NULL, 2.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-04-25 10:43:08', 'admin', '2023-01-02 13:26:55', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('277bfabef7d76e89b33062b16a9a5020', 'e3c13679c73a4f829bcff2aba8fd68b1', '基础表单', '/examples/form/BasicForm', 'examples/form/BasicForm', NULL, NULL, 1, NULL, NULL, 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2023-01-02 13:28:06', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('2a470fc0c3954d9dbb61de6d80846549', '', '常见案例', '/custom', 'layouts/RouteView', NULL, NULL, 0, NULL, NULL, 19.00, 0, 'qrcode', 1, 0, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2023-05-24 15:07:51', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('2aeddae571695cd6380f6d6d334d6e7d', 'f0675b52d89100ee88472b6800754a08', '布局统计报表', '/custom/report/ArchivesStatisticst', 'custom/report/ArchivesStatisticst', NULL, NULL, 1, NULL, NULL, 2.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-04-03 18:32:48', 'admin', '2023-01-02 13:30:38', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('2dbbafa22cda07fa5d169d741b81fe12', '08e6b9dc3c04489c8e1ff2ce6f105aa4', '在线文档', '{{ window._CONFIG[\'domainURL\'] }}/doc.html', 'layouts/IframePageView', NULL, NULL, 1, NULL, NULL, 10.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-01-30 10:00:01', 'admin', '2022-12-17 12:27:41', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('2e42e3835c2b44ec9f7bc26c146ee531', '1603663701748580354', '结果页', '/result', 'layouts/PageView', NULL, NULL, 1, NULL, NULL, 4.00, 0, 'check-circle-o', 1, 0, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2023-01-28 11:23:30', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('339329ed54cf255e1f9392e84f136901', '2a470fc0c3954d9dbb61de6d80846549', '表单开发', '/demo/form-design', 'demo/FormDesign', NULL, NULL, 1, NULL, NULL, 2.00, 0, NULL, 1, 1, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2023-05-24 15:08:10', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('3f915b2769fc80648e92d04e84ca059d', 'd7d6e2e4e2934f2c9385a623fd98c6f3', '用户管理', '/system/UserList', 'system/UserList', NULL, NULL, 1, NULL, NULL, 1.10, 0, NULL, 0, 0, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2023-04-28 20:43:08', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('3fac0d3c9cd40fa53ab70d4c583821f8', '1603667153006460930', '分屏', '/custom/SplitPanel', 'custom/SplitPanel', NULL, NULL, 1, NULL, NULL, 4.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-04-25 16:27:06', 'admin', '2023-01-02 13:26:37', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('4148ec82b6acd69f470bea75fe41c357', '1610527584667201537', '单表模型', '/table/Table', 'table/Table', 'DemoList', NULL, 1, NULL, NULL, 1.00, 0, NULL, 1, 0, 0, 0, 0, NULL, NULL, '2018-12-28 15:57:30', 'admin', '2023-01-30 14:12:29', 0, 1, NULL, 0);
INSERT INTO `sys_permission` VALUES ('4356a1a67b564f0988a484f5531fd4d9', '1610527584667201537', '内嵌Table', '/table/TableExpandeSub', 'table/TableExpandeSub', NULL, NULL, 1, NULL, NULL, 2.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-04-04 22:48:13', 'admin', '2023-01-04 15:47:45', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('45c966826eeff4c99b8f8ebfe74511fc', 'd7d6e2e4e2934f2c9385a623fd98c6f3', '部门管理', '/system/DepartList', 'system/DepartList', NULL, NULL, 1, NULL, NULL, 1.40, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-01-29 18:47:40', 'admin', '2023-01-01 10:36:35', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('4875ebe289344e14844d8e3ea1edd73f', '1603663701748580354', '详情页', '/profile', 'layouts/RouteView', NULL, NULL, 1, NULL, NULL, 3.00, 0, 'profile', 1, 0, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2022-12-16 16:11:26', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('4f66409ef3bbd69c1d80469d6e2a885e', '6e73eb3c26099c191bf03852ee1310a1', '账户绑定', '/account/settings/Binding', 'account/settings/Binding', NULL, NULL, 1, 'BindingSettings', NULL, NULL, 0, NULL, 1, 1, 0, 0, 0, NULL, NULL, '2018-12-26 19:01:20', 'admin', '2023-01-01 21:11:32', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('4f84f9400e5e92c95f05b554724c2b58', '540a2936940846cb98114ffb0d145cb8', '角色列表', '/examples/list/RoleList', 'examples/list/RoleList', NULL, NULL, 1, NULL, NULL, 4.00, 0, NULL, 1, 1, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2023-01-02 13:28:41', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('53a9230444d33de28aa11cc108fb1dba', '5c8042bd6c601270b2bbd9b20bccc68b', '我的消息', '/system/UserAnnouncementList', 'system/UserAnnouncementList', NULL, NULL, 1, NULL, NULL, 3.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-04-19 10:16:00', 'admin', '2022-12-17 10:34:00', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('540a2936940846cb98114ffb0d145cb8', '1603663701748580354', '列表页', '/list', 'layouts/PageView', NULL, '/list/query-list', 1, NULL, NULL, 1.00, 0, 'table', 1, 0, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2023-01-28 14:33:01', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('54dd5457a3190740005c1bfec55b1c34', 'd7d6e2e4e2934f2c9385a623fd98c6f3', '菜单管理', '/system/PermissionList', 'system/PermissionList', NULL, NULL, 1, NULL, NULL, 0.00, 0, NULL, 1, 1, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2023-01-01 10:36:12', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('58857ff846e61794c69208e9d3a85466', '08e6b9dc3c04489c8e1ff2ce6f105aa4', '日志管理', '/system/LogList', 'system/LogList', NULL, NULL, 1, NULL, NULL, 5.00, 0, '', 1, 1, 0, 0, 0, NULL, NULL, '2018-12-26 10:11:18', 'admin', '2022-12-17 12:50:44', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('58b9204feaf07e47284ddb36cd2d8468', '1603666184591998978', '图片翻页', '/custom/ImgTurnPage', 'custom/ImgTurnPage', NULL, NULL, 1, NULL, NULL, 3.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-04-25 11:36:42', 'admin', '2023-01-02 13:27:02', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('5c2f42277948043026b7a14692456828', 'd7d6e2e4e2934f2c9385a623fd98c6f3', '我的部门', '/system/DepartUserList', 'system/DepartUserList', NULL, NULL, 1, NULL, NULL, 2.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-04-17 15:12:24', 'admin', '2023-01-01 10:36:48', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('5c8042bd6c601270b2bbd9b20bccc68b', '', '消息中心', '/message', 'layouts/RouteView', NULL, NULL, 0, NULL, NULL, 80.00, 0, 'message', 1, 0, 0, 0, 0, NULL, 'admin', '2019-04-09 11:05:04', 'admin', '2022-12-17 17:36:50', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('6531cf3421b1265aeeeabaab5e176e6d', 'e3c13679c73a4f829bcff2aba8fd68b1', '分步表单', '/examples/form/stepForm/StepForm', 'examples/form/stepForm/StepForm', NULL, NULL, 1, NULL, NULL, 2.00, 0, NULL, 1, 1, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2023-01-02 13:28:11', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('655563cd64b75dcf52ef7bcdd4836953', '1603666184591998978', '图片预览', '/custom/ImagPreview', 'custom/ImagPreview', NULL, NULL, 1, NULL, NULL, 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-04-17 11:18:45', 'admin', '2022-12-16 16:20:00', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('65a8f489f25a345836b7f44b1181197a', 'c65321e57b7949b7a975313220de0422', '403', '/exception/403', 'exception/403', NULL, NULL, 1, NULL, NULL, 1.00, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, '2018-12-25 20:34:38', NULL, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES ('6ad53fd1b220989a8b71ff482d683a5a', '1610527584667201537', '一对多erp风格', '/table/TableListForERP', 'table/TableListForERP', NULL, NULL, 1, NULL, NULL, 6.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-02-20 14:45:09', 'admin', '2023-01-04 16:15:54', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('6e73eb3c26099c191bf03852ee1310a1', '717f6bee46f44a3897eca9abd6e2ec44', '个人设置', '/account/settings/Index', 'account/settings/Index', NULL, NULL, 1, NULL, NULL, 2.00, 0, NULL, 1, 0, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2023-01-01 21:13:23', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('700b7f95165c46cc7a78bf227aa8fed3', '08e6b9dc3c04489c8e1ff2ce6f105aa4', '性能监控', '/monitor', 'layouts/RouteView', NULL, NULL, 1, NULL, NULL, 7.00, 0, NULL, 1, 0, 0, 0, 0, NULL, 'admin', '2019-04-02 11:34:34', 'admin', '2022-12-17 17:13:21', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('717f6bee46f44a3897eca9abd6e2ec44', '', '个人页', '/account', 'layouts/RouteView', NULL, NULL, 0, NULL, NULL, 81.00, 0, 'user', 1, 0, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2022-12-17 17:39:58', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('73678f9daa45ed17a3674131b03432fb', '540a2936940846cb98114ffb0d145cb8', '权限列表', '/examples/list/PermissionList', 'examples/list/PermissionList', NULL, NULL, 1, NULL, NULL, 5.00, 0, NULL, 1, 1, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2023-01-02 13:28:47', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('7960961b0063228937da5fa8dd73d371', '1610527584667201537', '可编辑表格', '/table/EditableTable', 'table/EditableTable', NULL, NULL, 1, NULL, NULL, 3.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-03-22 15:22:18', 'admin', '2023-01-04 15:50:42', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('7ac9eb9ccbde2f7a033cd4944272bf1e', '540a2936940846cb98114ffb0d145cb8', '卡片列表', '/examples/list/CardList', 'examples/list/CardList', NULL, NULL, 1, NULL, NULL, 7.00, 0, NULL, 1, 1, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2023-01-02 13:28:53', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('841057b8a1bef8f6b4b20f9a618a7fa6', '08e6b9dc3c04489c8e1ff2ce6f105aa4', '数据日志', '/system/DataLogList', 'system/DataLogList', NULL, NULL, 1, NULL, NULL, 6.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-03-11 19:26:49', 'admin', '2023-01-01 21:45:06', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('882a73768cfd7f78f3a37584f7299656', '6e73eb3c26099c191bf03852ee1310a1', '个性化设置', '/account/settings/Custom', 'account/settings/Custom', NULL, NULL, 1, 'CustomSettings', NULL, NULL, 0, NULL, 1, 1, 0, 0, 0, NULL, NULL, '2018-12-26 19:00:46', 'admin', '2023-01-01 21:11:37', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('8b3bff2eee6f1939147f5c68292a1642', '700b7f95165c46cc7a78bf227aa8fed3', '服务器信息', '/modules/monitor/SystemInfo', 'modules/monitor/SystemInfo', NULL, NULL, 1, NULL, NULL, 4.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-04-02 11:39:19', 'admin', '2023-01-01 21:43:32', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('8d1ebd663688965f1fd86a2f0ead3416', '700b7f95165c46cc7a78bf227aa8fed3', 'Redis监控', '/modules/monitor/RedisInfo', 'modules/monitor/RedisInfo', NULL, NULL, 1, NULL, NULL, 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-04-02 13:11:33', 'admin', '2023-01-01 21:43:45', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('8fb8172747a78756c11916216b8b8066', '717f6bee46f44a3897eca9abd6e2ec44', '工作台', '/dashboard/Workplace', 'dashboard/Workplace', NULL, NULL, 1, NULL, NULL, 3.00, 0, NULL, 1, 1, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2023-01-01 20:17:40', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('944abf0a8fc22fe1f1154a389a574154', '5c8042bd6c601270b2bbd9b20bccc68b', '消息管理', '/modules/message/MessageList', 'modules/message/MessageList', NULL, NULL, 1, NULL, NULL, 2.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-04-09 11:27:53', 'admin', '2023-01-01 21:22:37', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('9502685863ab87f0ad1134142788a385', '', '首页', '/dashboard/analysis', 'dashboard/Analysis', NULL, NULL, 0, NULL, NULL, 0.00, 0, 'home', 1, 1, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2023-01-30 11:53:22', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('97c8629abc7848eccdb6d77c24bb3ebb', '700b7f95165c46cc7a78bf227aa8fed3', '磁盘监控', '/modules/monitor/DiskMonitoring', 'modules/monitor/DiskMonitoring', NULL, NULL, 1, NULL, NULL, 6.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-04-25 14:30:06', 'admin', '2023-01-01 21:43:09', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('9a90363f216a6a08f32eecb3f0bf12a3', '2a470fc0c3954d9dbb61de6d80846549', '自定义组件', '/custom/CustomComponent', 'custom/CustomComponent', NULL, NULL, 1, NULL, NULL, 0.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-03-19 11:19:05', 'admin', '2022-12-16 11:40:20', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('9cb91b8851db0cf7b19d7ecc2a8193dd', '1939e035e803a99ceecb6f5563570fb2', '我的任务表单', '/modules/bpm/task/form/FormModule', 'modules/bpm/task/form/FormModule', NULL, NULL, 1, NULL, NULL, 1.00, 0, NULL, 1, 1, NULL, 0, NULL, NULL, 'admin', '2019-03-08 16:49:05', 'admin', '2019-03-08 18:37:56', 0, 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES ('a400e4f4d54f79bf5ce160ae432231af', '2a470fc0c3954d9dbb61de6d80846549', '流程开发', '/demo/workflow', 'demo/workflow', NULL, NULL, 1, NULL, NULL, 3.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-01-29 19:44:06', 'admin', '2023-05-24 15:08:15', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('ae4fed059f67086fd52a73d913cf473d', '540a2936940846cb98114ffb0d145cb8', '可编辑表格', '/examples/list/EditableTable', 'examples/list/EditableTable', NULL, NULL, 1, NULL, NULL, 2.00, 0, NULL, 1, 1, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2023-01-28 14:33:39', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('aedbf679b5773c1f25e9f7b10111da73', '08e6b9dc3c04489c8e1ff2ce6f105aa4', 'SQL监控', '{{ window._CONFIG[\'domainURL\'] }}/druid/', 'layouts/IframePageView', NULL, NULL, 1, NULL, NULL, 4.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-01-30 09:43:22', 'admin', '2022-12-17 12:28:23', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('b1cb0a3fedf7ed0e4653cb5a229837ee', '08e6b9dc3c04489c8e1ff2ce6f105aa4', '定时任务', '/system/QuartzJobList', 'system/QuartzJobList', NULL, NULL, 1, NULL, NULL, 3.00, 0, NULL, 1, 1, 0, 0, 0, NULL, NULL, '2019-01-03 09:38:52', 'admin', '2022-12-17 12:35:28', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('b3c824fc22bd953e2eb16ae6914ac8f9', '4875ebe289344e14844d8e3ea1edd73f', '高级详情页', '/examples/profile/advanced/Advanced', 'examples/profile/advanced/Advanced', NULL, NULL, 1, NULL, NULL, 2.00, 0, NULL, 1, 1, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2023-01-02 13:27:54', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('b4dfc7d5dd9e8d5b6dd6d4579b1aa559', 'c65321e57b7949b7a975313220de0422', '500', '/exception/500', 'exception/500', NULL, NULL, 1, NULL, NULL, 3.00, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, '2018-12-25 20:34:38', NULL, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES ('b6bcee2ccc854052d3cc3e9c96d90197', '71102b3b87fb07e5527bbd2c530dd90a', '加班申请', '/modules/extbpm/joa/JoaOvertimeList', 'modules/extbpm/joa/JoaOvertimeList', NULL, NULL, 1, NULL, NULL, 1.00, 0, NULL, 1, 1, NULL, 0, NULL, NULL, 'admin', '2019-04-03 15:33:10', 'admin', '2019-04-03 15:34:48', 0, 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES ('c431130c0bc0ec71b0a5be37747bb36a', '1610527584667201537', '一对多可编辑表格', '/table/TableListForEditableTable', 'table/TableListForEditableTable', NULL, NULL, 1, NULL, NULL, 4.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-03-29 10:51:59', 'admin', '2023-01-04 16:00:54', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('c65321e57b7949b7a975313220de0422', '1603663701748580354', '异常页', '/exception', 'layouts/RouteView', NULL, NULL, 1, NULL, NULL, 5.00, 0, 'warning', 1, 0, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2022-12-16 16:13:50', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('c6cf95444d80435eb37b2f9db3971ae6', '1603667153006460930', '数据回执模拟', '/custom/InterfaceTest', 'custom/InterfaceTest', NULL, NULL, 1, NULL, NULL, 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-02-19 16:02:23', 'admin', '2022-12-16 16:23:54', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('cc50656cf9ca528e6f2150eba4714ad2', '4875ebe289344e14844d8e3ea1edd73f', '基础详情页', '/examples/profile/basic/Index', 'examples/profile/basic/Index', NULL, NULL, 1, NULL, NULL, 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2023-01-02 13:27:48', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('d07a2c87a451434c99ab06296727ec4f', '700b7f95165c46cc7a78bf227aa8fed3', 'JVM信息', '/modules/monitor/JvmInfo', 'modules/monitor/JvmInfo', NULL, NULL, 1, NULL, NULL, 4.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-04-01 23:07:48', 'admin', '2023-01-01 21:43:26', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('d2bbf9ebca5a8fa2e227af97d2da7548', 'c65321e57b7949b7a975313220de0422', '404', '/exception/404', 'exception/404', NULL, NULL, 1, NULL, NULL, 2.00, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, '2018-12-25 20:34:38', NULL, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES ('d7d6e2e4e2934f2c9385a623fd98c6f3', '', '系统管理', '/isystem', 'layouts/RouteView', NULL, NULL, 0, NULL, NULL, 70.00, 0, 'setting', 1, 0, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2022-12-17 17:47:40', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('d86f58e7ab516d3bc6bfb1fe10585f97', '717f6bee46f44a3897eca9abd6e2ec44', '个人中心', '/account/center/Index', 'account/center/Index', 'account-center', NULL, 1, NULL, NULL, 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2023-01-13 11:55:37', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('de13e0f6328c069748de7399fcc1dbbd', 'fb07ca05a3e13674dbf6d3245956da2e', '项目', '/list/search/project', 'examples/list/EditableTable', NULL, NULL, 1, NULL, NULL, 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-02-12 14:01:40', 'admin', '2023-01-28 16:21:56', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('e08cb190ef230d5d4f03824198773950', 'd7d6e2e4e2934f2c9385a623fd98c6f3', '系统通告', '/system/AnnouncementList', 'system/AnnouncementList', NULL, NULL, 1, 'annountCement', NULL, 6.00, 0, '', 1, 1, 0, 0, 0, NULL, NULL, '2019-01-02 17:23:01', 'admin', '2023-01-01 10:47:57', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('e1979bb53e9ea51cecc74d86fd9d2f64', '1601084565314310145', 'PDF预览', '/custom/PdfView', 'custom/PdfView', NULL, NULL, 1, NULL, NULL, 3.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-04-25 10:39:35', 'admin', '2023-01-06 09:33:57', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('e3c13679c73a4f829bcff2aba8fd68b1', '1603663701748580354', '表单页', '/form', 'layouts/PageView', NULL, NULL, 1, NULL, NULL, 2.00, 0, 'form', 1, 0, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2022-12-16 16:11:16', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('e41b69c57a941a3bbcce45032fe57605', '', '在线开发', '/online', 'layouts/RouteView', NULL, NULL, 0, NULL, NULL, 33.00, 0, 'cloud', 1, 0, 0, 0, 0, NULL, 'admin', '2019-03-08 10:43:10', 'admin', '2022-12-17 17:54:34', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('e5973686ed495c379d829ea8b2881fc6', 'e3c13679c73a4f829bcff2aba8fd68b1', '高级表单', '/examples/form/advancedForm/AdvancedForm', 'examples/form/advancedForm/AdvancedForm', NULL, NULL, 1, NULL, NULL, 3.00, 0, NULL, 1, 1, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2023-01-02 13:28:15', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('e6bfd1fcabfd7942fdd05f076d1dad38', '1603667153006460930', '打印测试', '/custom/PrintDemo', 'custom/PrintDemo', NULL, NULL, 1, NULL, NULL, 2.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-02-19 15:58:48', 'admin', '2022-12-16 16:24:01', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('ebb9d82ea16ad864071158e0c449d186', 'd7d6e2e4e2934f2c9385a623fd98c6f3', '分类字典', '/system/CategoryList', 'system/CategoryList', NULL, NULL, 1, NULL, '1', 5.20, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-05-29 18:48:07', 'admin', '2023-01-01 10:48:04', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('ec8d607d0156e198b11853760319c646', '6e73eb3c26099c191bf03852ee1310a1', '安全设置', '/account/settings/Security', 'account/settings/Security', NULL, NULL, 1, 'SecuritySettings', NULL, NULL, 0, NULL, 1, 1, 0, 0, 0, NULL, NULL, '2018-12-26 18:59:52', 'admin', '2023-01-01 21:11:42', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('f0675b52d89100ee88472b6800754a08', '', '统计报表', '/report', 'layouts/RouteView', NULL, NULL, 0, NULL, NULL, 51.00, 0, 'bar-chart', 1, 0, 0, 0, 0, NULL, 'admin', '2019-04-03 18:32:02', 'admin', '2022-12-17 17:44:17', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('f1cb187abf927c88b89470d08615f5ac', 'd7d6e2e4e2934f2c9385a623fd98c6f3', '数据字典', '/system/DictList', 'system/DictList', NULL, NULL, 1, NULL, NULL, 5.00, 0, NULL, 1, 1, 0, 0, 0, NULL, NULL, '2018-12-28 13:54:43', 'admin', '2023-01-01 10:37:02', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('f23d9bfff4d9aa6b68569ba2cff38415', '540a2936940846cb98114ffb0d145cb8', '标准列表', '/examples/list/StandardList', 'examples/list/StandardList', NULL, NULL, 1, NULL, NULL, 6.00, 0, NULL, 1, 1, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2023-01-02 13:29:02', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('f780d0d3083d849ccbdb1b1baee4911d', '5c8042bd6c601270b2bbd9b20bccc68b', '模板管理', '/modules/message/MessageTemplateList', 'modules/message/MessageTemplateList', NULL, NULL, 1, NULL, NULL, 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-04-09 11:50:31', 'admin', '2023-01-01 21:22:21', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('fb07ca05a3e13674dbf6d3245956da2e', '540a2936940846cb98114ffb0d145cb8', '搜索列表', '/examples/list/search/SearchLayout', 'examples/list/search/SearchLayout', NULL, '/list/search/article', 1, NULL, NULL, 8.00, 0, NULL, 1, 0, 0, 0, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2023-01-02 13:29:08', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('fb367426764077dcf94640c843733985', '1610527584667201537', '一对多示例', '/table/TableList', 'table/TableList', NULL, NULL, 1, NULL, NULL, 7.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-02-15 16:24:11', 'admin', '2023-01-04 16:18:18', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('fc810a2267dd183e4ef7c71cc60f4670', '700b7f95165c46cc7a78bf227aa8fed3', '请求追踪', '/modules/monitor/HttpTrace', 'modules/monitor/HttpTrace', NULL, NULL, 1, NULL, NULL, 4.00, 0, NULL, 1, 1, 0, 0, 0, NULL, 'admin', '2019-04-02 09:46:19', 'admin', '2023-01-01 21:43:18', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('fedfbf4420536cacc0218557d263dfea', '6e73eb3c26099c191bf03852ee1310a1', '新消息通知', '/account/settings/Notification', 'account/settings/Notification', NULL, NULL, 1, 'NotificationSettings', NULL, NULL, 0, '', 1, 1, 0, 0, 0, NULL, NULL, '2018-12-26 19:02:05', 'admin', '2023-01-01 21:11:48', 0, 0, NULL, 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `role_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色编码',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_sys_role_role_code`(`role_code`) USING BTREE,
  INDEX `idx_sr_role_code`(`role_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('f6817f48af4fb3af11b9e8bf182f618b', '管理员', 'admin', '管理员', NULL, '2018-12-21 18:03:39', 'admin', '2019-05-20 11:40:26');

-- ----------------------------
-- Table structure for sys_role_index
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_index`;
CREATE TABLE `sys_role_index`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色编码',
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件',
  `is_route` tinyint(1) NULL DEFAULT 1 COMMENT '是否路由菜单: 0:不是  1:是（默认值1）',
  `priority` int(11) NULL DEFAULT 0 COMMENT '优先级',
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态0:无效 1:有效',
  `create_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人登录名称',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人登录名称',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新日期',
  `sys_org_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属部门',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色首页表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_index
-- ----------------------------
INSERT INTO `sys_role_index` VALUES ('1549658299929718786', 'admin', '222', '22', 1, 0, '0', 'admin', '2022-07-20 15:31:45', 'admin', '2022-07-20 15:31:55', 'A01');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `permission_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限id',
  `data_rule_ids` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据权限ids',
  `operate_date` datetime NULL DEFAULT NULL COMMENT '操作时间',
  `operate_ip` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作ip',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_srp_role_per_id`(`role_id`, `permission_id`) USING BTREE,
  INDEX `idx_srp_role_id`(`role_id`) USING BTREE,
  INDEX `idx_srp_permission_id`(`permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('00b0748f04d3ea52c8cfa179c1c9d384', '52b0cf022ac4187b2a70dfa4f8b2d940', 'd7d6e2e4e2934f2c9385a623fd98c6f3', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('00b82058779cca5106fbb84783534c9b', 'f6817f48af4fb3af11b9e8bf182f618b', '4148ec82b6acd69f470bea75fe41c357', '', NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('09bd4fc30ffe88c4a44ed3868f442719', 'f6817f48af4fb3af11b9e8bf182f618b', 'e6bfd1fcabfd7942fdd05f076d1dad38', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('0c2d2db76ee3aa81a4fe0925b0f31365', 'f6817f48af4fb3af11b9e8bf182f618b', '024f1fd1283dc632458976463d8984e1', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('0c6b8facbb1cc874964c87a8cf01e4b1', 'f6817f48af4fb3af11b9e8bf182f618b', '841057b8a1bef8f6b4b20f9a618a7fa6', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('0c6e1075e422972083c3e854d9af7851', 'f6817f48af4fb3af11b9e8bf182f618b', '08e6b9dc3c04489c8e1ff2ce6f105aa4', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('0e1469997af2d3b97fff56a59ee29eeb', 'f6817f48af4fb3af11b9e8bf182f618b', 'e41b69c57a941a3bbcce45032fe57605', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('0f861cb988fdc639bb1ab943471f3a72', 'f6817f48af4fb3af11b9e8bf182f618b', '97c8629abc7848eccdb6d77c24bb3ebb', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('115a6673ae6c0816d3f60de221520274', '21c5a3187763729408b40afb0d0fdfa8', '63b551e81c5956d5c861593d366d8c57', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('1185039870491439105', 'f6817f48af4fb3af11b9e8bf182f618b', '1174506953255182338', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('1185039870529187841', 'f6817f48af4fb3af11b9e8bf182f618b', '1174590283938041857', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('1185039870537576450', 'f6817f48af4fb3af11b9e8bf182f618b', '1166535831146504193', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('1197431682208206850', 'f6817f48af4fb3af11b9e8bf182f618b', '1192318987661234177', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('1209423530518761473', 'f6817f48af4fb3af11b9e8bf182f618b', '1205097455226462210', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('1209423530594258945', 'f6817f48af4fb3af11b9e8bf182f618b', '1205098241075453953', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('1209423530606841858', 'f6817f48af4fb3af11b9e8bf182f618b', '1205306106780364802', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('1209423580355481602', 'f6817f48af4fb3af11b9e8bf182f618b', '190c2b43bec6a5f7a4194a85db67d96a', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('1231590078632955905', 'f6817f48af4fb3af11b9e8bf182f618b', '1224641973866467330', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('1231590078658121729', 'f6817f48af4fb3af11b9e8bf182f618b', '1209731624921534465', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('1260928399955836929', 'f6817f48af4fb3af11b9e8bf182f618b', '1260928341675982849', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('1269526122208522241', 'f6817f48af4fb3af11b9e8bf182f618b', '1267412134208319489', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('126ea9faebeec2b914d6d9bef957afb6', 'f6817f48af4fb3af11b9e8bf182f618b', 'f1cb187abf927c88b89470d08615f5ac', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('1281494164945625089', 'f6817f48af4fb3af11b9e8bf182f618b', '1280464606292099074', NULL, '2020-07-10 15:43:13', '127.0.0.1');
INSERT INTO `sys_role_permission` VALUES ('1281494684632473602', 'f6817f48af4fb3af11b9e8bf182f618b', '1265162119913824258', NULL, '2020-07-10 15:45:16', '127.0.0.1');
INSERT INTO `sys_role_permission` VALUES ('1303585080082485250', 'f6817f48af4fb3af11b9e8bf182f618b', '1287715272999944193', NULL, '2020-09-09 14:44:37', '127.0.0.1');
INSERT INTO `sys_role_permission` VALUES ('1303585080103456769', 'f6817f48af4fb3af11b9e8bf182f618b', '1287715783966834689', NULL, '2020-09-09 14:44:37', '127.0.0.1');
INSERT INTO `sys_role_permission` VALUES ('1303585080116039682', 'f6817f48af4fb3af11b9e8bf182f618b', '1287716451494510593', NULL, '2020-09-09 14:44:37', '127.0.0.1');
INSERT INTO `sys_role_permission` VALUES ('1303585080124428290', 'f6817f48af4fb3af11b9e8bf182f618b', '1287718919049691137', NULL, '2020-09-09 14:44:37', '127.0.0.1');
INSERT INTO `sys_role_permission` VALUES ('1303585080128622593', 'f6817f48af4fb3af11b9e8bf182f618b', '1287718938179911682', NULL, '2020-09-09 14:44:37', '127.0.0.1');
INSERT INTO `sys_role_permission` VALUES ('1303585080141205506', 'f6817f48af4fb3af11b9e8bf182f618b', '1287718956957810689', NULL, '2020-09-09 14:44:37', '127.0.0.1');
INSERT INTO `sys_role_permission` VALUES ('1335960787783098369', 'f6817f48af4fb3af11b9e8bf182f618b', '1335960713267093506', NULL, '2020-12-07 22:54:07', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1372092783227596802', 'f6817f48af4fb3af11b9e8bf182f618b', '1352200630711652354', NULL, '2021-03-17 15:49:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1372092783240179713', 'f6817f48af4fb3af11b9e8bf182f618b', '1365187528377102337', NULL, '2021-03-17 15:49:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1400734738941505537', 'f6817f48af4fb3af11b9e8bf182f618b', '1400726868091015170', NULL, '2021-06-04 16:42:41', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1427184491288727554', 'f6817f48af4fb3af11b9e8bf182f618b', '1404684556047024130', NULL, '2021-08-16 16:24:33', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1427184491297116161', 'f6817f48af4fb3af11b9e8bf182f618b', '1387612436586065922', NULL, '2021-08-16 16:24:33', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1496429874513534978', 'f6817f48af4fb3af11b9e8bf182f618b', '1494641317580423169', NULL, '2022-02-23 18:20:59', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1522909726999498754', 'f6817f48af4fb3af11b9e8bf182f618b', '121008911426059sc9473', NULL, '2022-05-07 20:02:28', '127.0.0.1');
INSERT INTO `sys_role_permission` VALUES ('1522909726999498755', 'f6817f48af4fb3af11b9e8bf182f618b', '121008911426059sc9472', NULL, '2022-05-07 20:02:28', '127.0.0.1');
INSERT INTO `sys_role_permission` VALUES ('1522909726999498756', 'f6817f48af4fb3af11b9e8bf182f618b', '121008911426059sc9471', NULL, '2022-05-07 20:02:28', '127.0.0.1');
INSERT INTO `sys_role_permission` VALUES ('1522910722202980354', 'f6817f48af4fb3af11b9e8bf182f618b', '121008911426059sc94722', NULL, '2022-05-07 20:06:25', '127.0.0.1');
INSERT INTO `sys_role_permission` VALUES ('1523502432196915202', 'f6817f48af4fb3af11b9e8bf182f618b', '121008911426059sc94711', NULL, '2022-05-09 11:17:40', '127.0.0.1');
INSERT INTO `sys_role_permission` VALUES ('154edd0599bd1dc2c7de220b489cd1e2', 'f6817f48af4fb3af11b9e8bf182f618b', '7ac9eb9ccbde2f7a033cd4944272bf1e', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('1563449387339276289', 'f6817f48af4fb3af11b9e8bf182f618b', '1563449103292620801', NULL, '2022-08-27 16:52:36', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1565235365284540417', 'f6817f48af4fb3af11b9e8bf182f618b', '1565232925109776386', NULL, '2022-09-01 15:09:26', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1565579972144918530', 'f6817f48af4fb3af11b9e8bf182f618b', '1565579914460655617', NULL, '2022-09-02 13:58:47', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1566665181084061698', 'f6817f48af4fb3af11b9e8bf182f618b', '1260929666434318338', NULL, '2022-09-05 13:51:01', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1566665181096644610', 'f6817f48af4fb3af11b9e8bf182f618b', '1260931366557696001', NULL, '2022-09-05 13:51:01', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1566665181096644611', 'f6817f48af4fb3af11b9e8bf182f618b', '1260933542969458689', NULL, '2022-09-05 13:51:01', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1566665181096644612', 'f6817f48af4fb3af11b9e8bf182f618b', '1a0811914300741f4e11838ff37a1d3a', NULL, '2022-09-05 13:51:01', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1566665181105033218', 'f6817f48af4fb3af11b9e8bf182f618b', '277bfabef7d76e89b33062b16a9a5020', NULL, '2022-09-05 13:51:01', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1572429680268877826', 'f6817f48af4fb3af11b9e8bf182f618b', '1572423290645192705', NULL, '2022-09-21 11:37:05', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1572479553999544321', 'f6817f48af4fb3af11b9e8bf182f618b', '1572479217918353410', NULL, '2022-09-21 14:55:16', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1574207596396126209', 'f6817f48af4fb3af11b9e8bf182f618b', '1574207524774191106', NULL, '2022-09-26 09:21:53', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1575663149688659970', 'f6817f48af4fb3af11b9e8bf182f618b', '1575662879864889345', NULL, '2022-09-30 09:45:44', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1575665648080363521', 'f6817f48af4fb3af11b9e8bf182f618b', '1575664633943793665', NULL, '2022-09-30 09:55:40', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1578930773245648897', 'f6817f48af4fb3af11b9e8bf182f618b', '1578930711367081986', NULL, '2022-10-09 10:10:06', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1578935249570455553', 'f6817f48af4fb3af11b9e8bf182f618b', '1578935030602620930', NULL, '2022-10-09 10:27:53', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1578939279189303298', 'f6817f48af4fb3af11b9e8bf182f618b', '1578939092404363265', NULL, '2022-10-09 10:43:54', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1578943206148366338', 'f6817f48af4fb3af11b9e8bf182f618b', '1578943012690288642', NULL, '2022-10-09 10:59:30', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1581889376923435009', 'f6817f48af4fb3af11b9e8bf182f618b', '1581889121679065089', NULL, '2022-10-17 14:06:32', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1584876359437791233', 'f6817f48af4fb3af11b9e8bf182f618b', '1584876317008211969', NULL, '2022-10-25 19:55:44', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1593159377854377986', 'f6817f48af4fb3af11b9e8bf182f618b', '1593159248304910337', NULL, '2022-11-17 16:29:30', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600393601041047554', 'f6817f48af4fb3af11b9e8bf182f618b', '5c8042bd6c601270b2bbd9b20bccc68b', NULL, '2022-12-07 15:35:43', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600393601062019073', 'f6817f48af4fb3af11b9e8bf182f618b', '944abf0a8fc22fe1f1154a389a574154', NULL, '2022-12-07 15:35:43', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600393601062019074', 'f6817f48af4fb3af11b9e8bf182f618b', 'f780d0d3083d849ccbdb1b1baee4911d', NULL, '2022-12-07 15:35:43', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600393601062019075', 'f6817f48af4fb3af11b9e8bf182f618b', '53a9230444d33de28aa11cc108fb1dba', NULL, '2022-12-07 15:35:43', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600401930920402945', 'f6817f48af4fb3af11b9e8bf182f618b', '1600401879309492226', NULL, '2022-12-07 16:08:49', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600406005732876290', 'f6817f48af4fb3af11b9e8bf182f618b', '1600405828720664577', NULL, '2022-12-07 16:25:01', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600407376901185537', 'f6817f48af4fb3af11b9e8bf182f618b', '1600407326720532481', NULL, '2022-12-07 16:30:27', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600492453296934913', 'f6817f48af4fb3af11b9e8bf182f618b', '1600492187956875265', NULL, '2022-12-07 22:08:31', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600492453313712130', 'f6817f48af4fb3af11b9e8bf182f618b', '1600481964395401218', NULL, '2022-12-07 22:08:31', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600492453313712132', 'f6817f48af4fb3af11b9e8bf182f618b', '1600491157110198274', NULL, '2022-12-07 22:08:31', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600492453313712133', 'f6817f48af4fb3af11b9e8bf182f618b', '1600491744866406401', NULL, '2022-12-07 22:08:31', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600492453322100737', 'f6817f48af4fb3af11b9e8bf182f618b', '1600491844065890305', NULL, '2022-12-07 22:08:31', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600492453322100738', 'f6817f48af4fb3af11b9e8bf182f618b', '1600491234256031746', NULL, '2022-12-07 22:08:31', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600513199234682882', 'f6817f48af4fb3af11b9e8bf182f618b', '1600512638653370370', NULL, '2022-12-07 23:30:57', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600513199243071490', 'f6817f48af4fb3af11b9e8bf182f618b', '1600512762196594690', NULL, '2022-12-07 23:30:57', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600514223462748161', 'f6817f48af4fb3af11b9e8bf182f618b', '1600513531704578049', NULL, '2022-12-07 23:35:02', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600514223471136770', 'f6817f48af4fb3af11b9e8bf182f618b', '1600513628593000450', NULL, '2022-12-07 23:35:02', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600514223471136771', 'f6817f48af4fb3af11b9e8bf182f618b', '1600513706917433345', NULL, '2022-12-07 23:35:02', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600514223471136772', 'f6817f48af4fb3af11b9e8bf182f618b', '1600513792892276737', NULL, '2022-12-07 23:35:02', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600514223471136773', 'f6817f48af4fb3af11b9e8bf182f618b', '1600513868712710146', NULL, '2022-12-07 23:35:02', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600514223471136774', 'f6817f48af4fb3af11b9e8bf182f618b', '1600513948098301953', NULL, '2022-12-07 23:35:02', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600514223471136775', 'f6817f48af4fb3af11b9e8bf182f618b', '1600514019946729473', NULL, '2022-12-07 23:35:02', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600514223471136776', 'f6817f48af4fb3af11b9e8bf182f618b', '1600514090562031617', NULL, '2022-12-07 23:35:02', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600514223471136777', 'f6817f48af4fb3af11b9e8bf182f618b', '1600514161013755905', NULL, '2022-12-07 23:35:02', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600514728700219393', 'f6817f48af4fb3af11b9e8bf182f618b', '1600514444519346177', NULL, '2022-12-07 23:37:02', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600514728708608001', 'f6817f48af4fb3af11b9e8bf182f618b', '1600514611859492866', NULL, '2022-12-07 23:37:02', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600514728708608002', 'f6817f48af4fb3af11b9e8bf182f618b', '1600514671540244481', NULL, '2022-12-07 23:37:02', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600514728708608003', 'f6817f48af4fb3af11b9e8bf182f618b', '1600514529277841409', NULL, '2022-12-07 23:37:02', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600515465782038530', 'f6817f48af4fb3af11b9e8bf182f618b', '1600514992127676417', NULL, '2022-12-07 23:39:58', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600515465790427138', 'f6817f48af4fb3af11b9e8bf182f618b', '1600515061358858242', NULL, '2022-12-07 23:39:58', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600515465790427139', 'f6817f48af4fb3af11b9e8bf182f618b', '1600515153641934850', NULL, '2022-12-07 23:39:58', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600515465790427140', 'f6817f48af4fb3af11b9e8bf182f618b', '1600515229357510658', NULL, '2022-12-07 23:39:58', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600515465790427141', 'f6817f48af4fb3af11b9e8bf182f618b', '1600515291789725698', NULL, '2022-12-07 23:39:58', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1600515465790427142', 'f6817f48af4fb3af11b9e8bf182f618b', '1600515368176390145', NULL, '2022-12-07 23:39:58', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1601084835217772545', 'f6817f48af4fb3af11b9e8bf182f618b', '1601084565314310145', NULL, '2022-12-09 13:22:26', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1601089014434861058', 'f6817f48af4fb3af11b9e8bf182f618b', '1601085440464228353', NULL, '2022-12-09 13:39:02', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1601089014443249665', 'f6817f48af4fb3af11b9e8bf182f618b', '1601088640764317697', NULL, '2022-12-09 13:39:02', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1601089014443249666', 'f6817f48af4fb3af11b9e8bf182f618b', '1601088759228239874', NULL, '2022-12-09 13:39:02', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1601089014443249667', 'f6817f48af4fb3af11b9e8bf182f618b', '1601088864790482946', NULL, '2022-12-09 13:39:02', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1601089014447443969', 'f6817f48af4fb3af11b9e8bf182f618b', '1601088948240355330', NULL, '2022-12-09 13:39:02', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1603664806540836866', 'f6817f48af4fb3af11b9e8bf182f618b', '1603663701748580354', NULL, '2022-12-16 16:14:19', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1603667721343041539', 'f6817f48af4fb3af11b9e8bf182f618b', '1603666184591998978', NULL, '2022-12-16 16:25:54', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1603667721343041540', 'f6817f48af4fb3af11b9e8bf182f618b', '1603667153006460930', NULL, '2022-12-16 16:25:54', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1610528331031015426', 'f6817f48af4fb3af11b9e8bf182f618b', '1610527584667201537', NULL, '2023-01-04 14:47:31', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('165acd6046a0eaf975099f46a3c898ea', 'f6817f48af4fb3af11b9e8bf182f618b', '4f66409ef3bbd69c1d80469d6e2a885e', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('1660564495199133698', 'f6817f48af4fb3af11b9e8bf182f618b', '9502685863ab87f0ad1134142788a385', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495199133699', 'f6817f48af4fb3af11b9e8bf182f618b', '1575662879864889345', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495199133700', 'f6817f48af4fb3af11b9e8bf182f618b', '1581889121679065089', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495199133701', 'f6817f48af4fb3af11b9e8bf182f618b', '1578930711367081986', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495199133702', 'f6817f48af4fb3af11b9e8bf182f618b', '1578939092404363265', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495199133703', 'f6817f48af4fb3af11b9e8bf182f618b', '1578943012690288642', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495199133704', 'f6817f48af4fb3af11b9e8bf182f618b', '1578935030602620930', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495199133705', 'f6817f48af4fb3af11b9e8bf182f618b', '1563449103292620801', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495199133706', 'f6817f48af4fb3af11b9e8bf182f618b', '1584876317008211969', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495199133707', 'f6817f48af4fb3af11b9e8bf182f618b', '1660495711465693185', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495199133708', 'f6817f48af4fb3af11b9e8bf182f618b', '1572423290645192705', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495199133709', 'f6817f48af4fb3af11b9e8bf182f618b', '1572479217918353410', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495199133710', 'f6817f48af4fb3af11b9e8bf182f618b', '1574207524774191106', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495199133711', 'f6817f48af4fb3af11b9e8bf182f618b', '1575664633943793665', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495199133712', 'f6817f48af4fb3af11b9e8bf182f618b', '190c2b43bec6a5f7a4194a85db67d96a', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495199133713', 'f6817f48af4fb3af11b9e8bf182f618b', '1610527584667201537', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495199133714', 'f6817f48af4fb3af11b9e8bf182f618b', '4148ec82b6acd69f470bea75fe41c357', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495199133715', 'f6817f48af4fb3af11b9e8bf182f618b', '1400726868091015170', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495199133716', 'f6817f48af4fb3af11b9e8bf182f618b', '4356a1a67b564f0988a484f5531fd4d9', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495199133717', 'f6817f48af4fb3af11b9e8bf182f618b', '7960961b0063228937da5fa8dd73d371', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495199133718', 'f6817f48af4fb3af11b9e8bf182f618b', 'c431130c0bc0ec71b0a5be37747bb36a', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495199133719', 'f6817f48af4fb3af11b9e8bf182f618b', '1365187528377102337', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495199133720', 'f6817f48af4fb3af11b9e8bf182f618b', '6ad53fd1b220989a8b71ff482d683a5a', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495199133721', 'f6817f48af4fb3af11b9e8bf182f618b', 'fb367426764077dcf94640c843733985', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495199133722', 'f6817f48af4fb3af11b9e8bf182f618b', '0620e402857b8c5b605e1ad9f4b89350', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495199133723', 'f6817f48af4fb3af11b9e8bf182f618b', '043780fa095ff1b2bec4dc406d76f023', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495199133724', 'f6817f48af4fb3af11b9e8bf182f618b', '1494641317580423169', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495199133725', 'f6817f48af4fb3af11b9e8bf182f618b', '1287715272999944193', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242562', 'f6817f48af4fb3af11b9e8bf182f618b', '1287715783966834689', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242563', 'f6817f48af4fb3af11b9e8bf182f618b', '1287716451494510593', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242564', 'f6817f48af4fb3af11b9e8bf182f618b', '1287718919049691137', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242565', 'f6817f48af4fb3af11b9e8bf182f618b', '1287718938179911682', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242566', 'f6817f48af4fb3af11b9e8bf182f618b', '1287718956957810689', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242567', 'f6817f48af4fb3af11b9e8bf182f618b', '1601084565314310145', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242568', 'f6817f48af4fb3af11b9e8bf182f618b', '1601085440464228353', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242569', 'f6817f48af4fb3af11b9e8bf182f618b', '1601088640764317697', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242570', 'f6817f48af4fb3af11b9e8bf182f618b', '1601088759228239874', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242571', 'f6817f48af4fb3af11b9e8bf182f618b', '1601088864790482946', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242572', 'f6817f48af4fb3af11b9e8bf182f618b', '1601088948240355330', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242573', 'f6817f48af4fb3af11b9e8bf182f618b', '1166535831146504193', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242574', 'f6817f48af4fb3af11b9e8bf182f618b', 'e1979bb53e9ea51cecc74d86fd9d2f64', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242575', 'f6817f48af4fb3af11b9e8bf182f618b', 'e41b69c57a941a3bbcce45032fe57605', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242576', 'f6817f48af4fb3af11b9e8bf182f618b', '1593159248304910337', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242577', 'f6817f48af4fb3af11b9e8bf182f618b', '1192318987661234177', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242578', 'f6817f48af4fb3af11b9e8bf182f618b', '1224641973866467330', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242579', 'f6817f48af4fb3af11b9e8bf182f618b', '1209731624921534465', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242580', 'f6817f48af4fb3af11b9e8bf182f618b', '1205097455226462210', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242581', 'f6817f48af4fb3af11b9e8bf182f618b', '1205098241075453953', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242582', 'f6817f48af4fb3af11b9e8bf182f618b', '1205306106780364802', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242583', 'f6817f48af4fb3af11b9e8bf182f618b', '1335960713267093506', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242584', 'f6817f48af4fb3af11b9e8bf182f618b', 'f0675b52d89100ee88472b6800754a08', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242585', 'f6817f48af4fb3af11b9e8bf182f618b', '020b06793e4de2eee0007f603000c769', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242586', 'f6817f48af4fb3af11b9e8bf182f618b', '2aeddae571695cd6380f6d6d334d6e7d', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242587', 'f6817f48af4fb3af11b9e8bf182f618b', '1352200630711652354', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242588', 'f6817f48af4fb3af11b9e8bf182f618b', '2a470fc0c3954d9dbb61de6d80846549', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242589', 'f6817f48af4fb3af11b9e8bf182f618b', '9a90363f216a6a08f32eecb3f0bf12a3', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242590', 'f6817f48af4fb3af11b9e8bf182f618b', '1603663701748580354', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242591', 'f6817f48af4fb3af11b9e8bf182f618b', '540a2936940846cb98114ffb0d145cb8', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242592', 'f6817f48af4fb3af11b9e8bf182f618b', 'ae4fed059f67086fd52a73d913cf473d', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242593', 'f6817f48af4fb3af11b9e8bf182f618b', '05b3c82ddb2536a4a5ee1a4c46b5abef', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242594', 'f6817f48af4fb3af11b9e8bf182f618b', '4f84f9400e5e92c95f05b554724c2b58', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242595', 'f6817f48af4fb3af11b9e8bf182f618b', '73678f9daa45ed17a3674131b03432fb', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242596', 'f6817f48af4fb3af11b9e8bf182f618b', 'f23d9bfff4d9aa6b68569ba2cff38415', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242597', 'f6817f48af4fb3af11b9e8bf182f618b', '7ac9eb9ccbde2f7a033cd4944272bf1e', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242598', 'f6817f48af4fb3af11b9e8bf182f618b', 'fb07ca05a3e13674dbf6d3245956da2e', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242599', 'f6817f48af4fb3af11b9e8bf182f618b', '078f9558cdeab239aecb2bda1a8ed0d1', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242600', 'f6817f48af4fb3af11b9e8bf182f618b', '200006f0edf145a2b50eacca07585451', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242601', 'f6817f48af4fb3af11b9e8bf182f618b', 'de13e0f6328c069748de7399fcc1dbbd', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242602', 'f6817f48af4fb3af11b9e8bf182f618b', 'e3c13679c73a4f829bcff2aba8fd68b1', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242603', 'f6817f48af4fb3af11b9e8bf182f618b', '277bfabef7d76e89b33062b16a9a5020', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242604', 'f6817f48af4fb3af11b9e8bf182f618b', '6531cf3421b1265aeeeabaab5e176e6d', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242605', 'f6817f48af4fb3af11b9e8bf182f618b', 'e5973686ed495c379d829ea8b2881fc6', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242606', 'f6817f48af4fb3af11b9e8bf182f618b', '4875ebe289344e14844d8e3ea1edd73f', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242607', 'f6817f48af4fb3af11b9e8bf182f618b', 'cc50656cf9ca528e6f2150eba4714ad2', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242608', 'f6817f48af4fb3af11b9e8bf182f618b', 'b3c824fc22bd953e2eb16ae6914ac8f9', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242609', 'f6817f48af4fb3af11b9e8bf182f618b', '2e42e3835c2b44ec9f7bc26c146ee531', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242610', 'f6817f48af4fb3af11b9e8bf182f618b', '00a2a0ae65cdca5e93209cdbde97cbe6', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242611', 'f6817f48af4fb3af11b9e8bf182f618b', '13212d3416eb690c2e1d5033166ff47a', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242612', 'f6817f48af4fb3af11b9e8bf182f618b', 'c65321e57b7949b7a975313220de0422', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242613', 'f6817f48af4fb3af11b9e8bf182f618b', '65a8f489f25a345836b7f44b1181197a', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242614', 'f6817f48af4fb3af11b9e8bf182f618b', 'd2bbf9ebca5a8fa2e227af97d2da7548', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242615', 'f6817f48af4fb3af11b9e8bf182f618b', 'b4dfc7d5dd9e8d5b6dd6d4579b1aa559', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242616', 'f6817f48af4fb3af11b9e8bf182f618b', '1603666184591998978', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242617', 'f6817f48af4fb3af11b9e8bf182f618b', '655563cd64b75dcf52ef7bcdd4836953', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242618', 'f6817f48af4fb3af11b9e8bf182f618b', '265de841c58907954b8877fb85212622', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242619', 'f6817f48af4fb3af11b9e8bf182f618b', '58b9204feaf07e47284ddb36cd2d8468', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242620', 'f6817f48af4fb3af11b9e8bf182f618b', '1280464606292099074', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242621', 'f6817f48af4fb3af11b9e8bf182f618b', '1565232925109776386', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242622', 'f6817f48af4fb3af11b9e8bf182f618b', '1565579914460655617', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242623', 'f6817f48af4fb3af11b9e8bf182f618b', '1603667153006460930', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242624', 'f6817f48af4fb3af11b9e8bf182f618b', 'c6cf95444d80435eb37b2f9db3971ae6', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242625', 'f6817f48af4fb3af11b9e8bf182f618b', 'e6bfd1fcabfd7942fdd05f076d1dad38', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242626', 'f6817f48af4fb3af11b9e8bf182f618b', '1387612436586065922', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242627', 'f6817f48af4fb3af11b9e8bf182f618b', '3fac0d3c9cd40fa53ab70d4c583821f8', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242628', 'f6817f48af4fb3af11b9e8bf182f618b', 'a400e4f4d54f79bf5ce160ae432231af', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242629', 'f6817f48af4fb3af11b9e8bf182f618b', '339329ed54cf255e1f9392e84f136901', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242630', 'f6817f48af4fb3af11b9e8bf182f618b', 'd7d6e2e4e2934f2c9385a623fd98c6f3', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242631', 'f6817f48af4fb3af11b9e8bf182f618b', '54dd5457a3190740005c1bfec55b1c34', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242632', 'f6817f48af4fb3af11b9e8bf182f618b', '3f915b2769fc80648e92d04e84ca059d', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242633', 'f6817f48af4fb3af11b9e8bf182f618b', '1260928341675982849', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242634', 'f6817f48af4fb3af11b9e8bf182f618b', '1260929666434318338', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242635', 'f6817f48af4fb3af11b9e8bf182f618b', '1260931366557696001', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242636', 'f6817f48af4fb3af11b9e8bf182f618b', '1260933542969458689', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242637', 'f6817f48af4fb3af11b9e8bf182f618b', '1a0811914300741f4e11838ff37a1d3a', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242638', 'f6817f48af4fb3af11b9e8bf182f618b', '45c966826eeff4c99b8f8ebfe74511fc', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242639', 'f6817f48af4fb3af11b9e8bf182f618b', '1174506953255182338', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242640', 'f6817f48af4fb3af11b9e8bf182f618b', '5c2f42277948043026b7a14692456828', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242641', 'f6817f48af4fb3af11b9e8bf182f618b', '1174590283938041857', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242642', 'f6817f48af4fb3af11b9e8bf182f618b', 'f1cb187abf927c88b89470d08615f5ac', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242643', 'f6817f48af4fb3af11b9e8bf182f618b', 'ebb9d82ea16ad864071158e0c449d186', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242644', 'f6817f48af4fb3af11b9e8bf182f618b', 'e08cb190ef230d5d4f03824198773950', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242645', 'f6817f48af4fb3af11b9e8bf182f618b', '08e6b9dc3c04489c8e1ff2ce6f105aa4', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242646', 'f6817f48af4fb3af11b9e8bf182f618b', '1404684556047024130', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242647', 'f6817f48af4fb3af11b9e8bf182f618b', '1265162119913824258', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242648', 'f6817f48af4fb3af11b9e8bf182f618b', 'b1cb0a3fedf7ed0e4653cb5a229837ee', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242649', 'f6817f48af4fb3af11b9e8bf182f618b', 'aedbf679b5773c1f25e9f7b10111da73', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242650', 'f6817f48af4fb3af11b9e8bf182f618b', '58857ff846e61794c69208e9d3a85466', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242651', 'f6817f48af4fb3af11b9e8bf182f618b', '841057b8a1bef8f6b4b20f9a618a7fa6', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242652', 'f6817f48af4fb3af11b9e8bf182f618b', '700b7f95165c46cc7a78bf227aa8fed3', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242653', 'f6817f48af4fb3af11b9e8bf182f618b', '8d1ebd663688965f1fd86a2f0ead3416', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242654', 'f6817f48af4fb3af11b9e8bf182f618b', '024f1fd1283dc632458976463d8984e1', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242655', 'f6817f48af4fb3af11b9e8bf182f618b', '8b3bff2eee6f1939147f5c68292a1642', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242656', 'f6817f48af4fb3af11b9e8bf182f618b', 'd07a2c87a451434c99ab06296727ec4f', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242657', 'f6817f48af4fb3af11b9e8bf182f618b', 'fc810a2267dd183e4ef7c71cc60f4670', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242658', 'f6817f48af4fb3af11b9e8bf182f618b', '97c8629abc7848eccdb6d77c24bb3ebb', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242659', 'f6817f48af4fb3af11b9e8bf182f618b', '2dbbafa22cda07fa5d169d741b81fe12', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242660', 'f6817f48af4fb3af11b9e8bf182f618b', '5c8042bd6c601270b2bbd9b20bccc68b', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242661', 'f6817f48af4fb3af11b9e8bf182f618b', 'f780d0d3083d849ccbdb1b1baee4911d', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242662', 'f6817f48af4fb3af11b9e8bf182f618b', '944abf0a8fc22fe1f1154a389a574154', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242663', 'f6817f48af4fb3af11b9e8bf182f618b', '53a9230444d33de28aa11cc108fb1dba', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242664', 'f6817f48af4fb3af11b9e8bf182f618b', '717f6bee46f44a3897eca9abd6e2ec44', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242665', 'f6817f48af4fb3af11b9e8bf182f618b', 'd86f58e7ab516d3bc6bfb1fe10585f97', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242666', 'f6817f48af4fb3af11b9e8bf182f618b', '6e73eb3c26099c191bf03852ee1310a1', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242667', 'f6817f48af4fb3af11b9e8bf182f618b', '1367a93f2c410b169faa7abcbad2f77c', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242668', 'f6817f48af4fb3af11b9e8bf182f618b', '4f66409ef3bbd69c1d80469d6e2a885e', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242669', 'f6817f48af4fb3af11b9e8bf182f618b', '882a73768cfd7f78f3a37584f7299656', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242670', 'f6817f48af4fb3af11b9e8bf182f618b', 'ec8d607d0156e198b11853760319c646', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242671', 'f6817f48af4fb3af11b9e8bf182f618b', 'fedfbf4420536cacc0218557d263dfea', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242672', 'f6817f48af4fb3af11b9e8bf182f618b', '8fb8172747a78756c11916216b8b8066', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242673', 'f6817f48af4fb3af11b9e8bf182f618b', '1600401879309492226', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242674', 'f6817f48af4fb3af11b9e8bf182f618b', '1600405828720664577', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242675', 'f6817f48af4fb3af11b9e8bf182f618b', '1600407326720532481', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242676', 'f6817f48af4fb3af11b9e8bf182f618b', '1600492187956875265', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242677', 'f6817f48af4fb3af11b9e8bf182f618b', '1600481964395401218', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242678', 'f6817f48af4fb3af11b9e8bf182f618b', '1600512638653370370', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242679', 'f6817f48af4fb3af11b9e8bf182f618b', '1600513628593000450', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242680', 'f6817f48af4fb3af11b9e8bf182f618b', '1600513706917433345', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242681', 'f6817f48af4fb3af11b9e8bf182f618b', '1600513792892276737', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242682', 'f6817f48af4fb3af11b9e8bf182f618b', '1600513868712710146', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242683', 'f6817f48af4fb3af11b9e8bf182f618b', '1600513948098301953', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242684', 'f6817f48af4fb3af11b9e8bf182f618b', '1600514019946729473', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242685', 'f6817f48af4fb3af11b9e8bf182f618b', '1600514090562031617', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242686', 'f6817f48af4fb3af11b9e8bf182f618b', '1600514161013755905', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242687', 'f6817f48af4fb3af11b9e8bf182f618b', '1600514444519346177', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242688', 'f6817f48af4fb3af11b9e8bf182f618b', '1600513531704578049', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242689', 'f6817f48af4fb3af11b9e8bf182f618b', '1600491234256031746', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242690', 'f6817f48af4fb3af11b9e8bf182f618b', '1600514992127676417', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242691', 'f6817f48af4fb3af11b9e8bf182f618b', '1600515061358858242', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242692', 'f6817f48af4fb3af11b9e8bf182f618b', '1600512762196594690', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242693', 'f6817f48af4fb3af11b9e8bf182f618b', '1600515153641934850', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242694', 'f6817f48af4fb3af11b9e8bf182f618b', '1600515229357510658', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242695', 'f6817f48af4fb3af11b9e8bf182f618b', '1600515291789725698', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242696', 'f6817f48af4fb3af11b9e8bf182f618b', '1600515368176390145', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242697', 'f6817f48af4fb3af11b9e8bf182f618b', '1600491157110198274', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242698', 'f6817f48af4fb3af11b9e8bf182f618b', '1600491744866406401', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242699', 'f6817f48af4fb3af11b9e8bf182f618b', '1600491844065890305', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242700', 'f6817f48af4fb3af11b9e8bf182f618b', '1600514529277841409', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242701', 'f6817f48af4fb3af11b9e8bf182f618b', '1600514611859492866', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1660564495266242702', 'f6817f48af4fb3af11b9e8bf182f618b', '1600514671540244481', NULL, '2023-05-22 16:33:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882817', 'f6817f48af4fb3af11b9e8bf182f618b', '9502685863ab87f0ad1134142788a385', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882818', 'f6817f48af4fb3af11b9e8bf182f618b', '1575662879864889345', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882819', 'f6817f48af4fb3af11b9e8bf182f618b', '1581889121679065089', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882820', 'f6817f48af4fb3af11b9e8bf182f618b', '1578930711367081986', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882821', 'f6817f48af4fb3af11b9e8bf182f618b', '1578939092404363265', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882822', 'f6817f48af4fb3af11b9e8bf182f618b', '1578943012690288642', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882823', 'f6817f48af4fb3af11b9e8bf182f618b', '1578935030602620930', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882824', 'f6817f48af4fb3af11b9e8bf182f618b', '1563449103292620801', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882825', 'f6817f48af4fb3af11b9e8bf182f618b', '1584876317008211969', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882826', 'f6817f48af4fb3af11b9e8bf182f618b', '1660495711465693185', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882827', 'f6817f48af4fb3af11b9e8bf182f618b', '1661237852886294530', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882828', 'f6817f48af4fb3af11b9e8bf182f618b', '1572423290645192705', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882829', 'f6817f48af4fb3af11b9e8bf182f618b', '1572479217918353410', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882830', 'f6817f48af4fb3af11b9e8bf182f618b', '1574207524774191106', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882831', 'f6817f48af4fb3af11b9e8bf182f618b', '1575664633943793665', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882832', 'f6817f48af4fb3af11b9e8bf182f618b', '190c2b43bec6a5f7a4194a85db67d96a', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882833', 'f6817f48af4fb3af11b9e8bf182f618b', '1610527584667201537', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882834', 'f6817f48af4fb3af11b9e8bf182f618b', '4148ec82b6acd69f470bea75fe41c357', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882835', 'f6817f48af4fb3af11b9e8bf182f618b', '1400726868091015170', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882836', 'f6817f48af4fb3af11b9e8bf182f618b', '4356a1a67b564f0988a484f5531fd4d9', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882837', 'f6817f48af4fb3af11b9e8bf182f618b', '7960961b0063228937da5fa8dd73d371', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882838', 'f6817f48af4fb3af11b9e8bf182f618b', 'c431130c0bc0ec71b0a5be37747bb36a', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882839', 'f6817f48af4fb3af11b9e8bf182f618b', '1365187528377102337', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882840', 'f6817f48af4fb3af11b9e8bf182f618b', '6ad53fd1b220989a8b71ff482d683a5a', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882841', 'f6817f48af4fb3af11b9e8bf182f618b', 'fb367426764077dcf94640c843733985', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882842', 'f6817f48af4fb3af11b9e8bf182f618b', '0620e402857b8c5b605e1ad9f4b89350', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882843', 'f6817f48af4fb3af11b9e8bf182f618b', '043780fa095ff1b2bec4dc406d76f023', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882844', 'f6817f48af4fb3af11b9e8bf182f618b', '1494641317580423169', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882845', 'f6817f48af4fb3af11b9e8bf182f618b', '1287715272999944193', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882846', 'f6817f48af4fb3af11b9e8bf182f618b', '1287715783966834689', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882847', 'f6817f48af4fb3af11b9e8bf182f618b', '1287716451494510593', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882848', 'f6817f48af4fb3af11b9e8bf182f618b', '1287718919049691137', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882849', 'f6817f48af4fb3af11b9e8bf182f618b', '1287718938179911682', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882850', 'f6817f48af4fb3af11b9e8bf182f618b', '1287718956957810689', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882851', 'f6817f48af4fb3af11b9e8bf182f618b', '1601084565314310145', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882852', 'f6817f48af4fb3af11b9e8bf182f618b', '1601085440464228353', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882853', 'f6817f48af4fb3af11b9e8bf182f618b', '1601088640764317697', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882854', 'f6817f48af4fb3af11b9e8bf182f618b', '1601088759228239874', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882855', 'f6817f48af4fb3af11b9e8bf182f618b', '1601088864790482946', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882856', 'f6817f48af4fb3af11b9e8bf182f618b', '1601088948240355330', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882857', 'f6817f48af4fb3af11b9e8bf182f618b', '1166535831146504193', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882858', 'f6817f48af4fb3af11b9e8bf182f618b', 'e1979bb53e9ea51cecc74d86fd9d2f64', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882859', 'f6817f48af4fb3af11b9e8bf182f618b', 'e41b69c57a941a3bbcce45032fe57605', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882860', 'f6817f48af4fb3af11b9e8bf182f618b', '1593159248304910337', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882861', 'f6817f48af4fb3af11b9e8bf182f618b', '1192318987661234177', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882862', 'f6817f48af4fb3af11b9e8bf182f618b', '1224641973866467330', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882863', 'f6817f48af4fb3af11b9e8bf182f618b', '1209731624921534465', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882864', 'f6817f48af4fb3af11b9e8bf182f618b', '1205097455226462210', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882865', 'f6817f48af4fb3af11b9e8bf182f618b', '1205098241075453953', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882866', 'f6817f48af4fb3af11b9e8bf182f618b', '1205306106780364802', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882867', 'f6817f48af4fb3af11b9e8bf182f618b', '1335960713267093506', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882868', 'f6817f48af4fb3af11b9e8bf182f618b', 'f0675b52d89100ee88472b6800754a08', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882869', 'f6817f48af4fb3af11b9e8bf182f618b', '020b06793e4de2eee0007f603000c769', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882870', 'f6817f48af4fb3af11b9e8bf182f618b', '2aeddae571695cd6380f6d6d334d6e7d', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882871', 'f6817f48af4fb3af11b9e8bf182f618b', '1352200630711652354', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882872', 'f6817f48af4fb3af11b9e8bf182f618b', '2a470fc0c3954d9dbb61de6d80846549', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882873', 'f6817f48af4fb3af11b9e8bf182f618b', '9a90363f216a6a08f32eecb3f0bf12a3', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882874', 'f6817f48af4fb3af11b9e8bf182f618b', '1603663701748580354', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882875', 'f6817f48af4fb3af11b9e8bf182f618b', '540a2936940846cb98114ffb0d145cb8', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882876', 'f6817f48af4fb3af11b9e8bf182f618b', 'ae4fed059f67086fd52a73d913cf473d', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882877', 'f6817f48af4fb3af11b9e8bf182f618b', '05b3c82ddb2536a4a5ee1a4c46b5abef', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882878', 'f6817f48af4fb3af11b9e8bf182f618b', '4f84f9400e5e92c95f05b554724c2b58', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924910882879', 'f6817f48af4fb3af11b9e8bf182f618b', '73678f9daa45ed17a3674131b03432fb', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991682', 'f6817f48af4fb3af11b9e8bf182f618b', 'f23d9bfff4d9aa6b68569ba2cff38415', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991683', 'f6817f48af4fb3af11b9e8bf182f618b', '7ac9eb9ccbde2f7a033cd4944272bf1e', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991684', 'f6817f48af4fb3af11b9e8bf182f618b', 'fb07ca05a3e13674dbf6d3245956da2e', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991685', 'f6817f48af4fb3af11b9e8bf182f618b', '078f9558cdeab239aecb2bda1a8ed0d1', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991686', 'f6817f48af4fb3af11b9e8bf182f618b', '200006f0edf145a2b50eacca07585451', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991687', 'f6817f48af4fb3af11b9e8bf182f618b', 'de13e0f6328c069748de7399fcc1dbbd', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991688', 'f6817f48af4fb3af11b9e8bf182f618b', 'e3c13679c73a4f829bcff2aba8fd68b1', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991689', 'f6817f48af4fb3af11b9e8bf182f618b', '277bfabef7d76e89b33062b16a9a5020', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991690', 'f6817f48af4fb3af11b9e8bf182f618b', '6531cf3421b1265aeeeabaab5e176e6d', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991691', 'f6817f48af4fb3af11b9e8bf182f618b', 'e5973686ed495c379d829ea8b2881fc6', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991692', 'f6817f48af4fb3af11b9e8bf182f618b', '4875ebe289344e14844d8e3ea1edd73f', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991693', 'f6817f48af4fb3af11b9e8bf182f618b', 'cc50656cf9ca528e6f2150eba4714ad2', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991694', 'f6817f48af4fb3af11b9e8bf182f618b', 'b3c824fc22bd953e2eb16ae6914ac8f9', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991695', 'f6817f48af4fb3af11b9e8bf182f618b', '2e42e3835c2b44ec9f7bc26c146ee531', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991696', 'f6817f48af4fb3af11b9e8bf182f618b', '00a2a0ae65cdca5e93209cdbde97cbe6', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991697', 'f6817f48af4fb3af11b9e8bf182f618b', '13212d3416eb690c2e1d5033166ff47a', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991698', 'f6817f48af4fb3af11b9e8bf182f618b', 'c65321e57b7949b7a975313220de0422', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991699', 'f6817f48af4fb3af11b9e8bf182f618b', '65a8f489f25a345836b7f44b1181197a', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991700', 'f6817f48af4fb3af11b9e8bf182f618b', 'd2bbf9ebca5a8fa2e227af97d2da7548', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991701', 'f6817f48af4fb3af11b9e8bf182f618b', 'b4dfc7d5dd9e8d5b6dd6d4579b1aa559', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991702', 'f6817f48af4fb3af11b9e8bf182f618b', '1603666184591998978', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991703', 'f6817f48af4fb3af11b9e8bf182f618b', '655563cd64b75dcf52ef7bcdd4836953', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991704', 'f6817f48af4fb3af11b9e8bf182f618b', '265de841c58907954b8877fb85212622', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991705', 'f6817f48af4fb3af11b9e8bf182f618b', '58b9204feaf07e47284ddb36cd2d8468', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991706', 'f6817f48af4fb3af11b9e8bf182f618b', '1280464606292099074', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991707', 'f6817f48af4fb3af11b9e8bf182f618b', '1565232925109776386', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991708', 'f6817f48af4fb3af11b9e8bf182f618b', '1565579914460655617', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991709', 'f6817f48af4fb3af11b9e8bf182f618b', '1603667153006460930', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991710', 'f6817f48af4fb3af11b9e8bf182f618b', 'c6cf95444d80435eb37b2f9db3971ae6', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991711', 'f6817f48af4fb3af11b9e8bf182f618b', 'e6bfd1fcabfd7942fdd05f076d1dad38', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991712', 'f6817f48af4fb3af11b9e8bf182f618b', '1387612436586065922', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991713', 'f6817f48af4fb3af11b9e8bf182f618b', '3fac0d3c9cd40fa53ab70d4c583821f8', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991714', 'f6817f48af4fb3af11b9e8bf182f618b', 'a400e4f4d54f79bf5ce160ae432231af', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991715', 'f6817f48af4fb3af11b9e8bf182f618b', '339329ed54cf255e1f9392e84f136901', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991716', 'f6817f48af4fb3af11b9e8bf182f618b', 'd7d6e2e4e2934f2c9385a623fd98c6f3', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991717', 'f6817f48af4fb3af11b9e8bf182f618b', '54dd5457a3190740005c1bfec55b1c34', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991718', 'f6817f48af4fb3af11b9e8bf182f618b', '3f915b2769fc80648e92d04e84ca059d', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991719', 'f6817f48af4fb3af11b9e8bf182f618b', '1260928341675982849', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991720', 'f6817f48af4fb3af11b9e8bf182f618b', '1260929666434318338', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991721', 'f6817f48af4fb3af11b9e8bf182f618b', '1260931366557696001', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991722', 'f6817f48af4fb3af11b9e8bf182f618b', '1260933542969458689', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991723', 'f6817f48af4fb3af11b9e8bf182f618b', '1a0811914300741f4e11838ff37a1d3a', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991724', 'f6817f48af4fb3af11b9e8bf182f618b', '45c966826eeff4c99b8f8ebfe74511fc', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991725', 'f6817f48af4fb3af11b9e8bf182f618b', '1174506953255182338', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991726', 'f6817f48af4fb3af11b9e8bf182f618b', '5c2f42277948043026b7a14692456828', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991727', 'f6817f48af4fb3af11b9e8bf182f618b', '1174590283938041857', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991728', 'f6817f48af4fb3af11b9e8bf182f618b', 'f1cb187abf927c88b89470d08615f5ac', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991729', 'f6817f48af4fb3af11b9e8bf182f618b', 'ebb9d82ea16ad864071158e0c449d186', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991730', 'f6817f48af4fb3af11b9e8bf182f618b', 'e08cb190ef230d5d4f03824198773950', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991731', 'f6817f48af4fb3af11b9e8bf182f618b', '08e6b9dc3c04489c8e1ff2ce6f105aa4', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991732', 'f6817f48af4fb3af11b9e8bf182f618b', '1404684556047024130', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991733', 'f6817f48af4fb3af11b9e8bf182f618b', '1265162119913824258', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991734', 'f6817f48af4fb3af11b9e8bf182f618b', 'b1cb0a3fedf7ed0e4653cb5a229837ee', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991735', 'f6817f48af4fb3af11b9e8bf182f618b', 'aedbf679b5773c1f25e9f7b10111da73', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991736', 'f6817f48af4fb3af11b9e8bf182f618b', '58857ff846e61794c69208e9d3a85466', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991737', 'f6817f48af4fb3af11b9e8bf182f618b', '841057b8a1bef8f6b4b20f9a618a7fa6', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991738', 'f6817f48af4fb3af11b9e8bf182f618b', '700b7f95165c46cc7a78bf227aa8fed3', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991739', 'f6817f48af4fb3af11b9e8bf182f618b', '8d1ebd663688965f1fd86a2f0ead3416', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991740', 'f6817f48af4fb3af11b9e8bf182f618b', '024f1fd1283dc632458976463d8984e1', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991741', 'f6817f48af4fb3af11b9e8bf182f618b', '8b3bff2eee6f1939147f5c68292a1642', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991742', 'f6817f48af4fb3af11b9e8bf182f618b', 'd07a2c87a451434c99ab06296727ec4f', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991743', 'f6817f48af4fb3af11b9e8bf182f618b', 'fc810a2267dd183e4ef7c71cc60f4670', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991744', 'f6817f48af4fb3af11b9e8bf182f618b', '97c8629abc7848eccdb6d77c24bb3ebb', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991745', 'f6817f48af4fb3af11b9e8bf182f618b', '2dbbafa22cda07fa5d169d741b81fe12', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991746', 'f6817f48af4fb3af11b9e8bf182f618b', '5c8042bd6c601270b2bbd9b20bccc68b', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991747', 'f6817f48af4fb3af11b9e8bf182f618b', 'f780d0d3083d849ccbdb1b1baee4911d', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991748', 'f6817f48af4fb3af11b9e8bf182f618b', '944abf0a8fc22fe1f1154a389a574154', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991749', 'f6817f48af4fb3af11b9e8bf182f618b', '53a9230444d33de28aa11cc108fb1dba', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991750', 'f6817f48af4fb3af11b9e8bf182f618b', '717f6bee46f44a3897eca9abd6e2ec44', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991751', 'f6817f48af4fb3af11b9e8bf182f618b', 'd86f58e7ab516d3bc6bfb1fe10585f97', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991752', 'f6817f48af4fb3af11b9e8bf182f618b', '6e73eb3c26099c191bf03852ee1310a1', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991753', 'f6817f48af4fb3af11b9e8bf182f618b', '1367a93f2c410b169faa7abcbad2f77c', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991754', 'f6817f48af4fb3af11b9e8bf182f618b', '4f66409ef3bbd69c1d80469d6e2a885e', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991755', 'f6817f48af4fb3af11b9e8bf182f618b', '882a73768cfd7f78f3a37584f7299656', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991756', 'f6817f48af4fb3af11b9e8bf182f618b', 'ec8d607d0156e198b11853760319c646', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991757', 'f6817f48af4fb3af11b9e8bf182f618b', 'fedfbf4420536cacc0218557d263dfea', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991758', 'f6817f48af4fb3af11b9e8bf182f618b', '8fb8172747a78756c11916216b8b8066', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991759', 'f6817f48af4fb3af11b9e8bf182f618b', '1600401879309492226', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991760', 'f6817f48af4fb3af11b9e8bf182f618b', '1600405828720664577', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991761', 'f6817f48af4fb3af11b9e8bf182f618b', '1600407326720532481', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991762', 'f6817f48af4fb3af11b9e8bf182f618b', '1600492187956875265', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991763', 'f6817f48af4fb3af11b9e8bf182f618b', '1600481964395401218', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991764', 'f6817f48af4fb3af11b9e8bf182f618b', '1600512638653370370', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991765', 'f6817f48af4fb3af11b9e8bf182f618b', '1600513628593000450', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991766', 'f6817f48af4fb3af11b9e8bf182f618b', '1600513706917433345', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991767', 'f6817f48af4fb3af11b9e8bf182f618b', '1600513792892276737', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991768', 'f6817f48af4fb3af11b9e8bf182f618b', '1600513868712710146', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991769', 'f6817f48af4fb3af11b9e8bf182f618b', '1600513948098301953', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991770', 'f6817f48af4fb3af11b9e8bf182f618b', '1600514019946729473', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991771', 'f6817f48af4fb3af11b9e8bf182f618b', '1600514090562031617', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991772', 'f6817f48af4fb3af11b9e8bf182f618b', '1600514161013755905', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991773', 'f6817f48af4fb3af11b9e8bf182f618b', '1600514444519346177', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991774', 'f6817f48af4fb3af11b9e8bf182f618b', '1600513531704578049', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991775', 'f6817f48af4fb3af11b9e8bf182f618b', '1600491234256031746', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991776', 'f6817f48af4fb3af11b9e8bf182f618b', '1600514992127676417', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991777', 'f6817f48af4fb3af11b9e8bf182f618b', '1600515061358858242', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991778', 'f6817f48af4fb3af11b9e8bf182f618b', '1600512762196594690', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991779', 'f6817f48af4fb3af11b9e8bf182f618b', '1600515153641934850', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991780', 'f6817f48af4fb3af11b9e8bf182f618b', '1600515229357510658', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991781', 'f6817f48af4fb3af11b9e8bf182f618b', '1600515291789725698', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991782', 'f6817f48af4fb3af11b9e8bf182f618b', '1600515368176390145', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991783', 'f6817f48af4fb3af11b9e8bf182f618b', '1600491157110198274', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991784', 'f6817f48af4fb3af11b9e8bf182f618b', '1600491744866406401', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991785', 'f6817f48af4fb3af11b9e8bf182f618b', '1600491844065890305', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991786', 'f6817f48af4fb3af11b9e8bf182f618b', '1600514529277841409', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991787', 'f6817f48af4fb3af11b9e8bf182f618b', '1600514611859492866', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1661237924977991788', 'f6817f48af4fb3af11b9e8bf182f618b', '1600514671540244481', NULL, '2023-05-24 13:09:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_permission` VALUES ('1664b92dff13e1575e3a929caa2fa14d', 'f6817f48af4fb3af11b9e8bf182f618b', 'd2bbf9ebca5a8fa2e227af97d2da7548', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('17ead5b7d97ed365398ab20009a69ea3', '52b0cf022ac4187b2a70dfa4f8b2d940', 'e08cb190ef230d5d4f03824198773950', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('1ac1688ef8456f384091a03d88a89ab1', '52b0cf022ac4187b2a70dfa4f8b2d940', '693ce69af3432bd00be13c3971a57961', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('1c1dbba68ef1817e7fb19c822d2854e8', 'f6817f48af4fb3af11b9e8bf182f618b', 'fb367426764077dcf94640c843733985', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('1e47db875601fd97723254046b5bba90', 'f6817f48af4fb3af11b9e8bf182f618b', 'baf16b7174bd821b6bab23fa9abb200d', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('1fe4d408b85f19618c15bcb768f0ec22', '1750a8fb3e6d90cb7957c02de1dc8e59', '9502685863ab87f0ad1134142788a385', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('20e53c87a785688bdc0a5bb6de394ef1', 'f6817f48af4fb3af11b9e8bf182f618b', '540a2936940846cb98114ffb0d145cb8', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('248d288586c6ff3bd14381565df84163', '52b0cf022ac4187b2a70dfa4f8b2d940', '3f915b2769fc80648e92d04e84ca059d', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('25491ecbd5a9b34f09c8bc447a10ede1', 'f6817f48af4fb3af11b9e8bf182f618b', 'd07a2c87a451434c99ab06296727ec4f', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('27489816708b18859768dfed5945c405', 'a799c3b1b12dd3ed4bd046bfaef5fe6e', '9502685863ab87f0ad1134142788a385', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('2779cdea8367fff37db26a42c1a1f531', 'f6817f48af4fb3af11b9e8bf182f618b', 'fef097f3903caf3a3c3a6efa8de43fbb', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('296f9c75ca0e172ae5ce4c1022c996df', '646c628b2b8295fbdab2d34044de0354', '732d48f8e0abe99fe6a23d18a3171cd1', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('29fb6b0ad59a7e911c8d27e0bdc42d23', 'f6817f48af4fb3af11b9e8bf182f618b', '9a90363f216a6a08f32eecb3f0bf12a3', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('2ad37346c1b83ddeebc008f6987b2227', 'f6817f48af4fb3af11b9e8bf182f618b', '8d1ebd663688965f1fd86a2f0ead3416', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('2c462293cbb0eab7e8ae0a3600361b5f', '52b0cf022ac4187b2a70dfa4f8b2d940', '9502685863ab87f0ad1134142788a385', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('2fdaed22dfa4c8d4629e44ef81688c6a', '52b0cf022ac4187b2a70dfa4f8b2d940', 'aedbf679b5773c1f25e9f7b10111da73', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('300c462b7fec09e2ff32574ef8b3f0bd', '52b0cf022ac4187b2a70dfa4f8b2d940', '2a470fc0c3954d9dbb61de6d80846549', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('35ac7cae648de39eb56213ca1b649713', '52b0cf022ac4187b2a70dfa4f8b2d940', 'b1cb0a3fedf7ed0e4653cb5a229837ee', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('38a2e55db0960262800576e34b3af44c', 'f6817f48af4fb3af11b9e8bf182f618b', '5c2f42277948043026b7a14692456828', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('3b1886f727ac503c93fecdd06dcb9622', 'f6817f48af4fb3af11b9e8bf182f618b', 'c431130c0bc0ec71b0a5be37747bb36a', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('3de2a60c7e42a521fecf6fcc5cb54978', 'f6817f48af4fb3af11b9e8bf182f618b', '2d83d62bd2544b8994c8f38cf17b0ddf', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('3e4e38f748b8d87178dd62082e5b7b60', 'f6817f48af4fb3af11b9e8bf182f618b', '7960961b0063228937da5fa8dd73d371', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('3e563751942b0879c88ca4de19757b50', '1750a8fb3e6d90cb7957c02de1dc8e59', '58857ff846e61794c69208e9d3a85466', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('3f1d04075e3c3254666a4138106a4e51', 'f6817f48af4fb3af11b9e8bf182f618b', '3fac0d3c9cd40fa53ab70d4c583821f8', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('412e2de37a35b3442d68db8dd2f3c190', '52b0cf022ac4187b2a70dfa4f8b2d940', 'f1cb187abf927c88b89470d08615f5ac', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('4204f91fb61911ba8ce40afa7c02369f', 'f6817f48af4fb3af11b9e8bf182f618b', '3f915b2769fc80648e92d04e84ca059d', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('445656dd187bd8a71605f4bbab1938a3', 'f6817f48af4fb3af11b9e8bf182f618b', '020b06793e4de2eee0007f603000c769', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('455cdb482457f529b79b479a2ff74427', 'f6817f48af4fb3af11b9e8bf182f618b', 'e1979bb53e9ea51cecc74d86fd9d2f64', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('4dab5a06acc8ef3297889872caa74747', 'f6817f48af4fb3af11b9e8bf182f618b', 'ffb423d25cc59dcd0532213c4a518261', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('4e0a37ed49524df5f08fc6593aee875c', 'f6817f48af4fb3af11b9e8bf182f618b', 'f23d9bfff4d9aa6b68569ba2cff38415', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('4ea403fc1d19feb871c8bdd9f94a4ecc', 'f6817f48af4fb3af11b9e8bf182f618b', '2e42e3835c2b44ec9f7bc26c146ee531', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('4f254549d9498f06f4cc9b23f3e2c070', 'f6817f48af4fb3af11b9e8bf182f618b', '93d5cfb4448f11e9916698e7f462b4b6', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('4faad8ff93cb2b5607cd3d07c1b624ee', 'a799c3b1b12dd3ed4bd046bfaef5fe6e', '70b8f33da5f39de1981bf89cf6c99792', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('504e326de3f03562cdd186748b48a8c7', 'f6817f48af4fb3af11b9e8bf182f618b', '027aee69baee98a0ed2e01806e89c891', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('520b5989e6fe4a302a573d4fee12a40a', 'f6817f48af4fb3af11b9e8bf182f618b', '6531cf3421b1265aeeeabaab5e176e6d', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('54fdf85e52807bdb32ce450814abc256', 'f6817f48af4fb3af11b9e8bf182f618b', 'cc50656cf9ca528e6f2150eba4714ad2', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('57c0b3a547b815ea3ec8e509b08948b3', '1750a8fb3e6d90cb7957c02de1dc8e59', '3f915b2769fc80648e92d04e84ca059d', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('593ee05c4fe4645c7826b7d5e14f23ec', '52b0cf022ac4187b2a70dfa4f8b2d940', '8fb8172747a78756c11916216b8b8066', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('5d230e6cd2935c4117f6cb9a7a749e39', 'f6817f48af4fb3af11b9e8bf182f618b', 'fc810a2267dd183e4ef7c71cc60f4670', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('5de6871fadb4fe1cdd28989da0126b07', 'f6817f48af4fb3af11b9e8bf182f618b', 'a400e4f4d54f79bf5ce160a3432231af', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('5e4015a9a641cbf3fb5d28d9f885d81a', 'f6817f48af4fb3af11b9e8bf182f618b', '2dbbafa22cda07fa5d169d741b81fe12', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('5fc194b709336d354640fe29fefd65a3', 'a799c3b1b12dd3ed4bd046bfaef5fe6e', '9ba60e626bf2882c31c488aba62b89f0', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('60eda4b4db138bdb47edbe8e10e71675', 'f6817f48af4fb3af11b9e8bf182f618b', 'fb07ca05a3e13674dbf6d3245956da2e', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('61835e48f3e675f7d3f5c9dd3a10dcf3', 'f6817f48af4fb3af11b9e8bf182f618b', 'f0675b52d89100ee88472b6800754a08', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('660fbc40bcb1044738f7cabdf1708c28', 'f6817f48af4fb3af11b9e8bf182f618b', 'b3c824fc22bd953e2eb16ae6914ac8f9', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('66b202f8f84fe766176b3f51071836ef', 'f6817f48af4fb3af11b9e8bf182f618b', '1367a93f2c410b169faa7abcbad2f77c', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('6c74518eb6bb9a353f6a6c459c77e64b', 'f6817f48af4fb3af11b9e8bf182f618b', 'b4dfc7d5dd9e8d5b6dd6d4579b1aa559', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('6daddafacd7eccb91309530c17c5855d', 'f6817f48af4fb3af11b9e8bf182f618b', 'edfa74d66e8ea63ea432c2910837b150', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('6fb4c2142498dd6d5b6c014ef985cb66', 'f6817f48af4fb3af11b9e8bf182f618b', '6e73eb3c26099c191bf03852ee1310a1', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('7413acf23b56c906aedb5a36fb75bd3a', 'f6817f48af4fb3af11b9e8bf182f618b', 'a4fc7b64b01a224da066bb16230f9c5a', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('75002588591820806', '16457350655250432', '5129710648430592', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('75002588604403712', '16457350655250432', '5129710648430593', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('75002588612792320', '16457350655250432', '40238597734928384', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('75002588625375232', '16457350655250432', '57009744761589760', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('75002588633763840', '16457350655250432', '16392452747300864', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('75002588637958144', '16457350655250432', '16392767785668608', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('75002588650541056', '16457350655250432', '16439068543946752', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('76a54a8cc609754360bf9f57e7dbb2db', 'f6817f48af4fb3af11b9e8bf182f618b', 'c65321e57b7949b7a975313220de0422', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277779875336192', '496138616573952', '5129710648430592', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780043108352', '496138616573952', '5129710648430593', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780055691264', '496138616573952', '15701400130424832', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780064079872', '496138616573952', '16678126574637056', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780072468480', '496138616573952', '15701915807518720', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780076662784', '496138616573952', '15708892205944832', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780085051392', '496138616573952', '16678447719911424', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780089245696', '496138616573952', '25014528525733888', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780097634304', '496138616573952', '56898976661639168', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780135383040', '496138616573952', '40238597734928384', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780139577344', '496138616573952', '45235621697949696', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780147965952', '496138616573952', '45235787867885568', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780156354560', '496138616573952', '45235939278065664', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780164743168', '496138616573952', '43117268627886080', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780168937472', '496138616573952', '45236734832676864', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780181520384', '496138616573952', '45237010692050944', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780189908992', '496138616573952', '45237170029465600', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780198297600', '496138616573952', '57009544286441472', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780206686208', '496138616573952', '57009744761589760', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780215074816', '496138616573952', '57009981228060672', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780219269120', '496138616573952', '56309618086776832', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780227657728', '496138616573952', '57212882168844288', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780236046336', '496138616573952', '61560041605435392', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780244434944', '496138616573952', '61560275261722624', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780257017856', '496138616573952', '61560480518377472', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780265406464', '496138616573952', '44986029924421632', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780324126720', '496138616573952', '45235228800716800', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780332515328', '496138616573952', '45069342940860416', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780340903937', '496138616573952', '5129710648430594', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780349292544', '496138616573952', '16687383932047360', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780357681152', '496138616573952', '16689632049631232', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780366069760', '496138616573952', '16689745006432256', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780370264064', '496138616573952', '16689883993083904', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780374458369', '496138616573952', '16690313745666048', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780387041280', '496138616573952', '5129710648430595', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780395429888', '496138616573952', '16694861252005888', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780403818496', '496138616573952', '16695107491205120', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780412207104', '496138616573952', '16695243126607872', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780420595712', '496138616573952', '75002207560273920', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780428984320', '496138616573952', '76215889006956544', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780433178624', '496138616573952', '76216071333351424', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780441567232', '496138616573952', '76216264070008832', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780449955840', '496138616573952', '76216459709124608', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780458344448', '496138616573952', '76216594207870976', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780466733056', '496138616573952', '76216702639017984', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780475121664', '496138616573952', '58480609315524608', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780483510272', '496138616573952', '61394706252173312', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780491898880', '496138616573952', '61417744146370560', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780496093184', '496138616573952', '76606430504816640', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780504481792', '496138616573952', '76914082455752704', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780508676097', '496138616573952', '76607201262702592', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780517064704', '496138616573952', '39915540965232640', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780525453312', '496138616573952', '41370251991977984', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780538036224', '496138616573952', '45264987354042368', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780546424832', '496138616573952', '45265487029866496', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780554813440', '496138616573952', '45265762415284224', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780559007744', '496138616573952', '45265886315024384', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780567396352', '496138616573952', '45266070000373760', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780571590656', '496138616573952', '41363147411427328', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780579979264', '496138616573952', '41363537456533504', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780588367872', '496138616573952', '41364927394353152', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780596756480', '496138616573952', '41371711400054784', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780605145088', '496138616573952', '41469219249852416', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780613533696', '496138616573952', '39916171171991552', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780621922304', '496138616573952', '39918482854252544', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780630310912', '496138616573952', '41373430515240960', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780718391296', '496138616573952', '41375330996326400', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780722585600', '496138616573952', '63741744973352960', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780730974208', '496138616573952', '42082442672082944', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780739362816', '496138616573952', '41376192166629376', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780747751424', '496138616573952', '41377034236071936', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780756140032', '496138616573952', '56911328312299520', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780764528640', '496138616573952', '41378916912336896', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780768722944', '496138616573952', '63482475359244288', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780772917249', '496138616573952', '64290663792906240', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780785500160', '496138616573952', '66790433014943744', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780789694464', '496138616573952', '42087054753927168', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780798083072', '496138616573952', '67027338952445952', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780806471680', '496138616573952', '67027909637836800', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780810665985', '496138616573952', '67042515441684480', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780823248896', '496138616573952', '67082402312228864', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780827443200', '496138616573952', '16392452747300864', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780835831808', '496138616573952', '16392767785668608', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780840026112', '496138616573952', '16438800255291392', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780844220417', '496138616573952', '16438962738434048', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277780852609024', '496138616573952', '16439068543946752', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860062040064', '496138616573953', '5129710648430592', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860070428672', '496138616573953', '5129710648430593', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860078817280', '496138616573953', '40238597734928384', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860091400192', '496138616573953', '43117268627886080', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860099788800', '496138616573953', '57009744761589760', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860112371712', '496138616573953', '56309618086776832', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860120760320', '496138616573953', '44986029924421632', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860129148928', '496138616573953', '5129710648430594', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860141731840', '496138616573953', '5129710648430595', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860150120448', '496138616573953', '75002207560273920', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860158509056', '496138616573953', '58480609315524608', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860162703360', '496138616573953', '76606430504816640', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860171091968', '496138616573953', '76914082455752704', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860179480576', '496138616573953', '76607201262702592', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860187869184', '496138616573953', '39915540965232640', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860196257792', '496138616573953', '41370251991977984', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860204646400', '496138616573953', '41363147411427328', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860208840704', '496138616573953', '41371711400054784', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860213035009', '496138616573953', '39916171171991552', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860221423616', '496138616573953', '39918482854252544', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860225617920', '496138616573953', '41373430515240960', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860234006528', '496138616573953', '41375330996326400', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860242395136', '496138616573953', '63741744973352960', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860250783744', '496138616573953', '42082442672082944', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860254978048', '496138616573953', '41376192166629376', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860263366656', '496138616573953', '41377034236071936', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860271755264', '496138616573953', '56911328312299520', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860313698304', '496138616573953', '41378916912336896', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860322086912', '496138616573953', '63482475359244288', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860326281216', '496138616573953', '64290663792906240', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860334669824', '496138616573953', '66790433014943744', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860343058432', '496138616573953', '42087054753927168', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860347252736', '496138616573953', '67027338952445952', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860351447041', '496138616573953', '67027909637836800', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860359835648', '496138616573953', '67042515441684480', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860364029952', '496138616573953', '67082402312228864', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860368224256', '496138616573953', '16392452747300864', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860372418560', '496138616573953', '16392767785668608', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860376612865', '496138616573953', '16438800255291392', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860385001472', '496138616573953', '16438962738434048', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('77277860389195776', '496138616573953', '16439068543946752', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('7a5d31ba48fe3fb1266bf186dc5f7ba7', '52b0cf022ac4187b2a70dfa4f8b2d940', '58857ff846e61794c69208e9d3a85466', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('7ca833caa5eac837b7200d8b6de8b2e3', 'f6817f48af4fb3af11b9e8bf182f618b', 'fedfbf4420536cacc0218557d263dfea', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('7de42bdc0b8c5446b7d428c66a7abc12', '52b0cf022ac4187b2a70dfa4f8b2d940', '54dd5457a3190740005c1bfec55b1c34', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('7e19d90cec0dd87aaef351b9ff8f4902', '646c628b2b8295fbdab2d34044de0354', 'f9d3f4f27653a71c52faa9fb8070fbe7', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('84eac2f113c23737128fb099d1d1da89', 'f6817f48af4fb3af11b9e8bf182f618b', '03dc3d93261dda19fc86dd7ca486c6cf', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('86060e2867a5049d8a80d9fe5d8bc28b', 'f6817f48af4fb3af11b9e8bf182f618b', '765dd244f37b804e3d00f475fd56149b', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('884f147c20e003cc80ed5b7efa598cbe', 'f6817f48af4fb3af11b9e8bf182f618b', 'e5973686ed495c379d829ea8b2881fc6', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('8a60df8d8b4c9ee5fa63f48aeee3ec00', '1750a8fb3e6d90cb7957c02de1dc8e59', 'd7d6e2e4e2934f2c9385a623fd98c6f3', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('8b09925bdc194ab7f3559cd3a7ea0507', 'f6817f48af4fb3af11b9e8bf182f618b', 'ebb9d82ea16ad864071158e0c449d186', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('8ce1022dac4e558ff9694600515cf510', '1750a8fb3e6d90cb7957c02de1dc8e59', '08e6b9dc3c04489c8e1ff2ce6f105aa4', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('8d154c2382a8ae5c8d1b84bd38df2a93', 'f6817f48af4fb3af11b9e8bf182f618b', 'd86f58e7ab516d3bc6bfb1fe10585f97', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('8d848ca7feec5b7ebb3ecb32b2c8857a', '52b0cf022ac4187b2a70dfa4f8b2d940', '4148ec82b6acd69f470bea75fe41c357', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('8dd64f65a1014196078d0882f767cd85', 'f6817f48af4fb3af11b9e8bf182f618b', 'e3c13679c73a4f829bcff2aba8fd68b1', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('8e3dc1671abad4f3c83883b194d2e05a', 'f6817f48af4fb3af11b9e8bf182f618b', 'b1cb0a3fedf7ed0e4653cb5a229837ee', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('905bf419332ebcb83863603b3ebe30f0', 'f6817f48af4fb3af11b9e8bf182f618b', '8fb8172747a78756c11916216b8b8066', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('9264104cee9b10c96241d527b2d0346d', '1750a8fb3e6d90cb7957c02de1dc8e59', '54dd5457a3190740005c1bfec55b1c34', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('9380121ca9cfee4b372194630fce150e', 'f6817f48af4fb3af11b9e8bf182f618b', '65a8f489f25a345836b7f44b1181197a', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('94911fef73a590f6824105ebf9b6cab3', 'f6817f48af4fb3af11b9e8bf182f618b', '8b3bff2eee6f1939147f5c68292a1642', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('9700d20dbc1ae3cbf7de1c810b521fe6', 'f6817f48af4fb3af11b9e8bf182f618b', 'ec8d607d0156e198b11853760319c646', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('980171fda43adfe24840959b1d048d4d', 'f6817f48af4fb3af11b9e8bf182f618b', 'd7d6e2e4e2934f2c9385a623fd98c6f3', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('987c23b70873bd1d6dca52f30aafd8c2', 'f6817f48af4fb3af11b9e8bf182f618b', '00a2a0ae65cdca5e93209cdbde97cbe6', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('9b2ad767f9861e64a20b097538feafd3', 'f6817f48af4fb3af11b9e8bf182f618b', '73678f9daa45ed17a3674131b03432fb', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('9d8772c310b675ae43eacdbc6c7fa04a', 'a799c3b1b12dd3ed4bd046bfaef5fe6e', '1663f3faba244d16c94552f849627d84', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('9d980ec0489040e631a9c24a6af42934', 'f6817f48af4fb3af11b9e8bf182f618b', '05b3c82ddb2536a4a5ee1a4c46b5abef', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('9f8311ecccd44e079723098cf2ffe1cc', '1750a8fb3e6d90cb7957c02de1dc8e59', '693ce69af3432bd00be13c3971a57961', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('a034ed7c38c996b880d3e78f586fe0ae', 'f6817f48af4fb3af11b9e8bf182f618b', 'c89018ea6286e852b424466fd92a2ffc', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('a307a9349ad64a2eff8ab69582fa9be4', 'f6817f48af4fb3af11b9e8bf182f618b', '0620e402857b8c5b605e1ad9f4b89350', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('acacce4417e5d7f96a9c3be2ded5b4be', 'f6817f48af4fb3af11b9e8bf182f618b', 'f9d3f4f27653a71c52faa9fb8070fbe7', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('aefc8c22e061171806e59cd222f6b7e1', '52b0cf022ac4187b2a70dfa4f8b2d940', 'e8af452d8948ea49d37c934f5100ae6a', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('af60ac8fafd807ed6b6b354613b9ccbc', 'f6817f48af4fb3af11b9e8bf182f618b', '58857ff846e61794c69208e9d3a85466', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('b0c8a20800b8bf1ebdd7be473bceb44f', 'f6817f48af4fb3af11b9e8bf182f618b', '58b9204feaf07e47284ddb36cd2d8468', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('b128ebe78fa5abb54a3a82c6689bdca3', 'f6817f48af4fb3af11b9e8bf182f618b', 'aedbf679b5773c1f25e9f7b10111da73', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('b21b07951bb547b09cc85624a841aea0', 'f6817f48af4fb3af11b9e8bf182f618b', '4356a1a67b564f0988a484f5531fd4d9', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('b64c4ab9cd9a2ea8ac1e9db5fb7cf522', 'f6817f48af4fb3af11b9e8bf182f618b', '2aeddae571695cd6380f6d6d334d6e7d', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('bbec16ad016efec9ea2def38f4d3d9dc', 'f6817f48af4fb3af11b9e8bf182f618b', '13212d3416eb690c2e1d5033166ff47a', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('bea2986432079d89203da888d99b3f16', 'f6817f48af4fb3af11b9e8bf182f618b', '54dd5457a3190740005c1bfec55b1c34', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('c09373ebfc73fb5740db5ff02cba4f91', 'f6817f48af4fb3af11b9e8bf182f618b', '339329ed54cf255e1f9392e84f136901', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('c56fb1658ee5f7476380786bf5905399', 'f6817f48af4fb3af11b9e8bf182f618b', 'de13e0f6328c069748de7399fcc1dbbd', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('c6fee38d293b9d0596436a0cbd205070', 'f6817f48af4fb3af11b9e8bf182f618b', '4f84f9400e5e92c95f05b554724c2b58', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('c90b0b01c7ca454d2a1cb7408563e696', 'f6817f48af4fb3af11b9e8bf182f618b', '882a73768cfd7f78f3a37584f7299656', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('cf1feb1bf69eafc982295ad6c9c8d698', 'f6817f48af4fb3af11b9e8bf182f618b', 'a2b11669e98c5fe54a53c3e3c4f35d14', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('cf2ef620217673e4042f695743294f01', 'f6817f48af4fb3af11b9e8bf182f618b', '717f6bee46f44a3897eca9abd6e2ec44', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('cf43895aef7fc684669483ab00ef2257', 'f6817f48af4fb3af11b9e8bf182f618b', '700b7f95165c46cc7a78bf227aa8fed3', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('d37ad568e26f46ed0feca227aa9c2ffa', 'f6817f48af4fb3af11b9e8bf182f618b', '9502685863ab87f0ad1134142788a385', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('d3ddcacee1acdfaa0810618b74e38ef2', 'f6817f48af4fb3af11b9e8bf182f618b', 'c6cf95444d80435eb37b2f9db3971ae6', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('d3fe195d59811531c05d31d8436f5c8b', '1750a8fb3e6d90cb7957c02de1dc8e59', 'e8af452d8948ea49d37c934f5100ae6a', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('d8a5c9079df12090e108e21be94b4fd7', 'f6817f48af4fb3af11b9e8bf182f618b', '078f9558cdeab239aecb2bda1a8ed0d1', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('dbc5dd836d45c5bc7bc94b22596ab956', 'f6817f48af4fb3af11b9e8bf182f618b', '1939e035e803a99ceecb6f5563570fb2', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('dc83bb13c0e8c930e79d28b2db26f01f', 'f6817f48af4fb3af11b9e8bf182f618b', '63b551e81c5956d5c861593d366d8c57', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('dc8fd3f79bd85bd832608b42167a1c71', 'f6817f48af4fb3af11b9e8bf182f618b', '91c23960fab49335831cf43d820b0a61', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('de82e89b8b60a3ea99be5348f565c240', 'f6817f48af4fb3af11b9e8bf182f618b', '56ca78fe0f22d815fabc793461af67b8', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('de8f43229e351d34af3c95b1b9f0a15d', 'f6817f48af4fb3af11b9e8bf182f618b', 'a400e4f4d54f79bf5ce160ae432231af', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('e3e922673f4289b18366bb51b6200f17', '52b0cf022ac4187b2a70dfa4f8b2d940', '45c966826eeff4c99b8f8ebfe74511fc', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('e7467726ee72235baaeb47df04a35e73', 'f6817f48af4fb3af11b9e8bf182f618b', 'e08cb190ef230d5d4f03824198773950', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('eaef4486f1c9b0408580bbfa2037eb66', 'f6817f48af4fb3af11b9e8bf182f618b', '2a470fc0c3954d9dbb61de6d80846549', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('ec4bc97829ab56afd83f428b6dc37ff6', 'f6817f48af4fb3af11b9e8bf182f618b', '200006f0edf145a2b50eacca07585451', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('ec846a3f85fdb6813e515be71f11b331', 'f6817f48af4fb3af11b9e8bf182f618b', '732d48f8e0abe99fe6a23d18a3171cd1', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('ec93bb06f5be4c1f19522ca78180e2ef', 'f6817f48af4fb3af11b9e8bf182f618b', '265de841c58907954b8877fb85212622', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('ecdd72fe694e6bba9c1d9fc925ee79de', 'f6817f48af4fb3af11b9e8bf182f618b', '45c966826eeff4c99b8f8ebfe74511fc', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('edefd8d468f5727db465cf1b860af474', 'f6817f48af4fb3af11b9e8bf182f618b', '6ad53fd1b220989a8b71ff482d683a5a', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('ef8bdd20d29447681ec91d3603e80c7b', 'f6817f48af4fb3af11b9e8bf182f618b', 'ae4fed059f67086fd52a73d913cf473d', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('f177acac0276329dc66af0c9ad30558a', 'f6817f48af4fb3af11b9e8bf182f618b', 'c2c356bf4ddd29975347a7047a062440', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('f17ab8ad1e71341140857ef4914ef297', '21c5a3187763729408b40afb0d0fdfa8', '732d48f8e0abe99fe6a23d18a3171cd1', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('f99f99cc3bc27220cdd4f5aced33b7d7', 'f6817f48af4fb3af11b9e8bf182f618b', '655563cd64b75dcf52ef7bcdd4836953', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('fafe73c4448b977fe42880a6750c3ee8', 'f6817f48af4fb3af11b9e8bf182f618b', '9cb91b8851db0cf7b19d7ecc2a8193dd', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('fced905c7598973b970d42d833f73474', 'f6817f48af4fb3af11b9e8bf182f618b', '4875ebe289344e14844d8e3ea1edd73f', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('fd97963dc5f144d3aecfc7045a883427', 'f6817f48af4fb3af11b9e8bf182f618b', '043780fa095ff1b2bec4dc406d76f023', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('fed41a4671285efb266cd404f24dd378', '52b0cf022ac4187b2a70dfa4f8b2d940', '00a2a0ae65cdca5e93209cdbde97cbe6', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录账号',
  `realname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'md5密码盐',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `birthday` datetime NULL DEFAULT NULL COMMENT '生日',
  `sex` tinyint(1) NULL DEFAULT NULL COMMENT '性别(0-默认未知,1-男,2-女)',
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `org_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录会话的机构编码',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '性别(1-正常,2-冻结)',
  `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除状态(0-正常,1-已删除)',
  `third_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第三方登录的唯一标识',
  `third_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第三方类型',
  `activiti_sync` tinyint(1) NULL DEFAULT NULL COMMENT '同步工作流引擎(1-同步,0-不同步)',
  `work_no` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工号，唯一键',
  `post` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职务，关联职务表',
  `telephone` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '座机号',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `user_identity` tinyint(1) NULL DEFAULT NULL COMMENT '身份（1普通成员 2上级）',
  `depart_ids` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '负责部门',
  `client_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_sys_user_work_no`(`work_no`) USING BTREE,
  UNIQUE INDEX `uniq_sys_user_username`(`username`) USING BTREE,
  UNIQUE INDEX `uniq_sys_user_phone`(`phone`) USING BTREE,
  UNIQUE INDEX `uniq_sys_user_email`(`email`) USING BTREE,
  INDEX `idx_su_username`(`username`) USING BTREE,
  INDEX `idx_su_status`(`status`) USING BTREE,
  INDEX `idx_su_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('e9ca23d68d884d4ebb19d07889727dae', 'admin', '兰明易', 'cb362cfeefbf3d8d', 'RCGTeGiH', 'temp/account_gray_48dp_1661579636680.png', '2022-09-01 00:00:00', 1, '1972399206@qq.com', '15552435237', 'A01A06', 1, 0, NULL, NULL, 1, '00001', '总工程师', NULL, NULL, '2019-06-21 17:54:10', 'admin', '2023-01-30 16:18:19', 2, 'b93212e813234c23a2c8745587def638', NULL);

-- ----------------------------
-- Table structure for sys_user_depart
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_depart`;
CREATE TABLE `sys_user_depart`  (
  `ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `dep_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门id',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `idx_sud_user_id`(`user_id`) USING BTREE,
  INDEX `idx_sud_dep_id`(`dep_id`) USING BTREE,
  INDEX `idx_sud_user_dep_id`(`user_id`, `dep_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_depart
-- ----------------------------
INSERT INTO `sys_user_depart` VALUES ('1567750775570792449', '1567750775176527874', 'c6d7cb4deeac411cb3384b1b31278596');
INSERT INTO `sys_user_depart` VALUES ('1567750969343442945', '1567750969293111298', 'c6d7cb4deeac411cb3384b1b31278596');
INSERT INTO `sys_user_depart` VALUES ('1567751001228541954', '1567751001178210305', 'c6d7cb4deeac411cb3384b1b31278596');
INSERT INTO `sys_user_depart` VALUES ('1567751117024886785', '1567751116974555137', 'c6d7cb4deeac411cb3384b1b31278596');
INSERT INTO `sys_user_depart` VALUES ('1619973264341434370', 'e9ca23d68d884d4ebb19d07889727dae', 'b93212e813234c23a2c8745587def638');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_sur_user_id`(`user_id`) USING BTREE,
  INDEX `idx_sur_role_id`(`role_id`) USING BTREE,
  INDEX `idx_sur_user_role_id`(`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('b3ffd9311a1ca296c44e2409b547384f', '01b802058ea94b978a2c96f4807f6b48', '1');
INSERT INTO `sys_user_role` VALUES ('1619980585884942338', '1619978211321675778', 'f6817f48af4fb3af11b9e8bf182f618b');
INSERT INTO `sys_user_role` VALUES ('1619980585851387906', '1619978721185464321', 'f6817f48af4fb3af11b9e8bf182f618b');
INSERT INTO `sys_user_role` VALUES ('f2922a38ba24fb53749e45a0c459adb3', '439ae3e9bcf7418583fcd429cadb1d72', '1');
INSERT INTO `sys_user_role` VALUES ('ee45d0343ecec894b6886effc92cb0b7', '4d8fef4667574b24a9ccfedaf257810c', 'f6817f48af4fb3af11b9e8bf182f618b');
INSERT INTO `sys_user_role` VALUES ('1619973264303685633', 'e9ca23d68d884d4ebb19d07889727dae', 'f6817f48af4fb3af11b9e8bf182f618b');

SET FOREIGN_KEY_CHECKS = 1;
