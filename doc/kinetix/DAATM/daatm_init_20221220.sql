/*
 Navicat Premium Data Transfer

 Source Server         : 10.235
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : 172.16.10.235:3306
 Source Schema         : daatmuat

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 07/12/2022 10:07:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for atm_action_log
-- ----------------------------
DROP TABLE IF EXISTS `atm_action_log`;
CREATE TABLE `atm_action_log`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `device_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备IMEI',
  `log_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志内容',
  `upload_batchid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '上传批次号',
  `upload_time` datetime NOT NULL COMMENT '上传时间',
  `has_exception` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否异常，atm_isornot，0：否，1：是',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `atm_action_log_deviceid`(`device_id`) USING BTREE,
  INDEX `atm_action_log_hasexception`(`has_exception`) USING BTREE,
  INDEX `atm_action_log_uploadtime`(`upload_time`) USING BTREE,
  INDEX `atm_action_log_companycode`(`companycode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of atm_action_log
-- ----------------------------

-- ----------------------------
-- Table structure for atm_advertisements
-- ----------------------------
DROP TABLE IF EXISTS `atm_advertisements`;
CREATE TABLE `atm_advertisements`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '廣告名稱',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '廣告內容',
  `showtime` int(11) NOT NULL COMMENT '播放间隔',
  `sortno` int(11) NOT NULL COMMENT '廣告排序',
  `state` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1' COMMENT '廣告狀態，atm_advertisements_state,1：新建，2：發布，3：停用',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `atm_adv_name`(`name`) USING BTREE,
  INDEX `atm_adv_state`(`state`) USING BTREE,
  INDEX `atm_adv_companycode`(`companycode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '广告信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of atm_advertisements
-- ----------------------------

-- ----------------------------
-- Table structure for atm_box_reset_record
-- ----------------------------
DROP TABLE IF EXISTS `atm_box_reset_record`;
CREATE TABLE `atm_box_reset_record`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `device_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备IMEI',
  `box_money_count` int(11) NOT NULL COMMENT '清空张数',
  `box_money` decimal(18, 0) NOT NULL COMMENT '清空金额',
  `reset_time` datetime NOT NULL COMMENT '清空時間',
  `reset_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '清空人',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `atm_brr_deviceid`(`device_id`) USING BTREE,
  INDEX `atm_brr_reset_time`(`reset_time`) USING BTREE,
  INDEX `atm_brr_reset_by`(`reset_by`) USING BTREE,
  INDEX `atm_brr_companycode`(`companycode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '钱箱清空记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of atm_box_reset_record
-- ----------------------------

-- ----------------------------
-- Table structure for atm_busi_log
-- ----------------------------
DROP TABLE IF EXISTS `atm_busi_log`;
CREATE TABLE `atm_busi_log`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `device_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备IMEI',
  `log_type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志类型，atm_busi_log_logtype，1：交易日志，2：钱包日志，3：在线支付日志，4：其他日志',
  `log_level` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志级别，atm_busi_log_loglevel，1：操作记录，2：校验错误，3：程序异常',
  `log_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志名称',
  `log_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志内容',
  `is_exception` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否异常',
  `transactions_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '交易记录ID',
  `is_sendmail` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '已发送邮件，atm_isornot,0:否，1：是',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `atm_busi_log_deviceid`(`device_id`) USING BTREE,
  INDEX `atm_busi_log_log_type`(`log_type`) USING BTREE,
  INDEX `atm_busi_log_log_level`(`log_level`) USING BTREE,
  INDEX `atm_busi_log_log_name`(`log_name`) USING BTREE,
  INDEX `atm_busi_log_is_exception`(`is_exception`) USING BTREE,
  INDEX `atm_busi_log_transactions_id`(`transactions_id`) USING BTREE,
  INDEX `atm_busi_log_is_sendmail`(`is_sendmail`) USING BTREE,
  INDEX `atm_busi_log_companycode`(`companycode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '业务日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of atm_busi_log
-- ----------------------------

-- ----------------------------
-- Table structure for atm_current_price
-- ----------------------------
DROP TABLE IF EXISTS `atm_current_price`;
CREATE TABLE `atm_current_price`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `symbol` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '價格類型',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '價格名稱',
  `price` decimal(22, 8) NOT NULL COMMENT '價格',
  `reptime` datetime NOT NULL COMMENT '响应的时间',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '當前價格' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of atm_current_price
-- ----------------------------
INSERT INTO `atm_current_price` VALUES ('BTC', 'BTC', 'Bitcoin', 132007.39387270, '2022-11-18 10:39:09', NULL, 'job', '2022-11-18 10:33:31', 'job', '2022-11-18 10:40:01');
INSERT INTO `atm_current_price` VALUES ('ETH', 'ETH', 'Ethereum', 9503.79460127, '2022-11-18 10:39:39', NULL, 'job', '2022-11-18 10:33:31', 'job', '2022-11-18 10:40:01');
INSERT INTO `atm_current_price` VALUES ('USDT', 'USDT', 'Tether', 7.82116490, '2022-11-18 10:39:40', NULL, 'job', '2022-11-18 10:33:31', 'job', '2022-11-18 10:40:02');

-- ----------------------------
-- Table structure for atm_device_advertisements
-- ----------------------------
DROP TABLE IF EXISTS `atm_device_advertisements`;
CREATE TABLE `atm_device_advertisements`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `device_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备IMEI',
  `advertisements_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '廣告ID',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `atm_device_adv_device_id`(`device_id`) USING BTREE,
  INDEX `atm_device_adv_advertisements_id`(`advertisements_id`) USING BTREE,
  INDEX `atm_device_adv_companycode`(`companycode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '设备广告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of atm_device_advertisements
-- ----------------------------

-- ----------------------------
-- Table structure for atm_device_config
-- ----------------------------
DROP TABLE IF EXISTS `atm_device_config`;
CREATE TABLE `atm_device_config`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `device_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备ID',
  `param_state` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '参数是否需同步，0：否，1：是',
  `product_state` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '产品是否需同步，0：否，1：是',
  `device_products_state` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '設備產品是否需同步，0：否，1：是',
  `device_config_state` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '設備配置是否需同步，0：否，1：是',
  `advertisement_state` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '廣告是否需同步，0：否，1：是',
  `device_advertisement_state` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '設備廣告是否需同步，0：否，1：是',
  `fix_state` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '3' COMMENT '设备維護状态，atm_device_config_fixstate,1：正常，2：維護中，3：无需同步',
  `has_send` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '是否需發送指令，0：否，1：是',
  `reset_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '清空人',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `atm_device_cf_device_id`(`device_id`) USING BTREE,
  INDEX `atm_device_cf_has_send`(`has_send`) USING BTREE,
  INDEX `atm_device_cf_companycode`(`companycode`) USING BTREE,
  INDEX `atm_device_cf_fix_state`(`fix_state`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '设备指令表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of atm_device_config
-- ----------------------------

-- ----------------------------
-- Table structure for atm_device_config_log
-- ----------------------------
DROP TABLE IF EXISTS `atm_device_config_log`;
CREATE TABLE `atm_device_config_log`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `device_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备IMEI',
  `operate_type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作類型，atm_device_config_log_type,1:同步指令，2：維護指令，3：清空指令',
  `operate_desc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作內容',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `atm_device_cfglog_device_id`(`device_id`) USING BTREE,
  INDEX `atm_device_cfglog_companycode`(`companycode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '设备指令記錄表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of atm_device_config_log
-- ----------------------------

-- ----------------------------
-- Table structure for atm_device_param
-- ----------------------------
DROP TABLE IF EXISTS `atm_device_param`;
CREATE TABLE `atm_device_param`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `device_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备IMEI',
  `grouptype` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1' COMMENT 'atm_device_param_grouptye,1：普通參數',
  `paramid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '參數編碼',
  `paramvalue` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '參數值',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '參數描述',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `atm_device_param_device_id`(`device_id`) USING BTREE,
  INDEX `atm_device_param_grouptype`(`grouptype`) USING BTREE,
  INDEX `atm_device_param_paramid`(`paramid`) USING BTREE,
  INDEX `atm_device_param_companycode`(`companycode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '設備參數表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of atm_device_param
-- ----------------------------
INSERT INTO `atm_device_param` VALUES ('1552487569868820482', '0', '1', 'box_max_money_count', '2000', '錢箱最大張數', 'admin', '2022-07-28 10:54:15', NULL, NULL, '0');
INSERT INTO `atm_device_param` VALUES ('1552487901378220034', '0', '1', 'box_money_count_warn', '1400', '錢箱預警張數', 'admin', '2022-07-28 10:55:34', NULL, NULL, '0');

-- ----------------------------
-- Table structure for atm_device_products
-- ----------------------------
DROP TABLE IF EXISTS `atm_device_products`;
CREATE TABLE `atm_device_products`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `device_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备IMEI',
  `products_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品ID',
  `state` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1' COMMENT '设备产品状态，atm_device_products_state，1:新建，2：启用，3：停用',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `atm_device_prd_device_id`(`device_id`) USING BTREE,
  INDEX `atm_device_prd_products_id`(`products_id`) USING BTREE,
  INDEX `atm_device_prd_companycode`(`companycode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '设备产品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of atm_device_products
-- ----------------------------

-- ----------------------------
-- Table structure for atm_device_status
-- ----------------------------
DROP TABLE IF EXISTS `atm_device_status`;
CREATE TABLE `atm_device_status`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `device_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备IMEI码',
  `connect_state` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '连接状态，atm_device_status_connectstate，1：在线，2：离线',
  `fix_state` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '3' COMMENT '維護状态，atm_device_config_fixstate,1：正常，2：維護中，3：无需同步',
  `operate_state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '交互頁面',
  `box_money_count` int(11) NOT NULL COMMENT '钱箱张数',
  `box_money` decimal(18, 0) NOT NULL COMMENT '钱箱金额',
  `device_addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备地址',
  `elock_state` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电子锁状态，atm_device_status_elockstate, 0：关闭，1：打开',
  `app_version` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'APP版本号',
  `device_info` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备信息',
  `geocode` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备经纬度',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `atm_device_status_device_id`(`device_id`) USING BTREE,
  INDEX `atm_device_status_connect_state`(`connect_state`) USING BTREE,
  INDEX `atm_device_status_fix_state`(`fix_state`) USING BTREE,
  INDEX `atm_device_status_operate_state`(`operate_state`) USING BTREE,
  INDEX `atm_device_status_app_version`(`app_version`) USING BTREE,
  INDEX `atm_device_status_companycode`(`companycode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '设备状态表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of atm_device_status
-- ----------------------------

-- ----------------------------
-- Table structure for atm_device_status_ext
-- ----------------------------
DROP TABLE IF EXISTS `atm_device_status_ext`;
CREATE TABLE `atm_device_status_ext`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `device_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备IMEI码',
  `connect_state` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '连接状态，atm_device_status_connectstate，1：在线，2：离线',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `atm_device_statusext_device_id`(`device_id`) USING BTREE,
  INDEX `atm_device_statusext_connect_state`(`connect_state`) USING BTREE,
  INDEX `atm_device_statusext_companycode`(`companycode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '设备状态扩展表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of atm_device_status_ext
-- ----------------------------

-- ----------------------------
-- Table structure for atm_device_status_his
-- ----------------------------
DROP TABLE IF EXISTS `atm_device_status_his`;
CREATE TABLE `atm_device_status_his`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `device_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备ID',
  `connect_state` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '连接状态，atm_device_status_connectstate，1：在线，2：离线',
  `fix_state` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '3' COMMENT '維護状态，atm_device_config_fixstate,1：正常，2：維護中，3：无需同步',
  `operate_state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '交互状态',
  `box_money_count` int(11) NOT NULL COMMENT '钱箱张数',
  `box_money` decimal(18, 0) NOT NULL COMMENT '钱箱金额',
  `device_addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备地址',
  `elock_state` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电子锁状态，atm_device_status_elockstate, 0：关闭，1：打开',
  `app_version` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'APP版本号',
  `device_info` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备信息',
  `geocode` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备经纬度',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '设备状态表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of atm_device_status_his
-- ----------------------------

-- ----------------------------
-- Table structure for atm_onlinepay_res
-- ----------------------------
DROP TABLE IF EXISTS `atm_onlinepay_res`;
CREATE TABLE `atm_onlinepay_res`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `transactions_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '交易记录ID',
  `paymode` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1' COMMENT '支付方式，atm_transaction_paymode，1：现金，2：微信，3：支付宝，4：银联，5：PayMe',
  `mid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `tid` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `respcode` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '响应码',
  `refno` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ercrfn` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `amount` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `traceno` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `cardno` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `transtype` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `transdate` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `transtime` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `couponamt` int(11) NULL DEFAULT NULL,
  `netamt` int(11) NULL DEFAULT NULL,
  `offsetamt` int(11) NULL DEFAULT NULL,
  `transid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `payid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `approvedno` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `transactionamount` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `cardholder_currencycode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `pan` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `voucherno` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `atm_onlinepay_res_transactions_id`(`transactions_id`) USING BTREE,
  INDEX `atm_onlinepay_res_paymode`(`paymode`) USING BTREE,
  INDEX `atm_onlinepay_res_companycode`(`companycode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '在线支付响应表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of atm_onlinepay_res
-- ----------------------------

-- ----------------------------
-- Table structure for atm_paymode_config
-- ----------------------------
DROP TABLE IF EXISTS `atm_paymode_config`;
CREATE TABLE `atm_paymode_config`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品分类，atm_products_type,1：Gift cards，2：Digital Assets，3：NFT & Gaming',
  `paymode` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1' COMMENT '支付方式，atm_transaction_paymode，1：现金，2：微信，3：支付宝，4：银联，5：PayMe',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `atm_paym_cfg_type`(`type`) USING BTREE,
  INDEX `atm_paym_cfg_paymode`(`paymode`) USING BTREE,
  INDEX `atm_paym_cfg_companycode`(`companycode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付方式配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of atm_paymode_config
-- ----------------------------

-- ----------------------------
-- Table structure for atm_product_detailtype
-- ----------------------------
DROP TABLE IF EXISTS `atm_product_detailtype`;
CREATE TABLE `atm_product_detailtype`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品分类，atm_product_detailtype_type,1：Gift cards，2：Digital Assets，3：NFT & Gaming',
  `detailtypecode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品类型编码',
  `detailtypename` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品类型名称',
  `sortno` int(11) NULL DEFAULT NULL COMMENT '排序',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `atm_product_detailtype_code`(`detailtypecode`) USING BTREE,
  INDEX `atm_product_detailtype_type`(`type`) USING BTREE,
  INDEX `atm_product_detailtype_detailtypename`(`detailtypename`) USING BTREE,
  INDEX `atm_product_detailtype_companycode`(`companycode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '产品类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of atm_product_detailtype
-- ----------------------------
INSERT INTO `atm_product_detailtype` VALUES ('1544493541466386434', '2', 'BTC', '比特幣', 2, NULL, 'feynman', '2022-07-06 09:28:50', 'feynman', '2022-07-25 16:58:31', '0A01A04');
INSERT INTO `atm_product_detailtype` VALUES ('1548827995339235330', '2', 'ETH', '以太幣', 3, NULL, 'atmtest', '2022-07-18 08:32:25', 'atmtest', '2022-07-25 12:09:15', '0A01A04');
INSERT INTO `atm_product_detailtype` VALUES ('1551418894426603522', '2', 'Polygon', 'Polygon', 1, '', 'atmtest', '2022-07-25 12:07:43', 'feynman', '2022-07-25 14:33:59', '0A01A04');
INSERT INTO `atm_product_detailtype` VALUES ('1551419560310112257', '2', 'AXS', 'AXS', 4, NULL, 'atmtest', '2022-07-25 12:10:22', 'atmtest', '2022-07-25 12:43:10', '0A01A04');
INSERT INTO `atm_product_detailtype` VALUES ('1551419813004345345', '2', 'DOGC', 'Dogcoin', 5, NULL, 'atmtest', '2022-07-25 12:11:22', 'atmtest', '2022-07-25 12:42:04', '0A01A04');
INSERT INTO `atm_product_detailtype` VALUES ('1551420525805338626', '2', 'SOLRNR', 'SOLRNR', 6, NULL, 'atmtest', '2022-07-25 12:14:12', 'atmtest', '2022-07-25 12:40:10', '0A01A04');
INSERT INTO `atm_product_detailtype` VALUES ('1551420659960152066', '2', 'USDT', 'USDT', 7, NULL, 'atmtest', '2022-07-25 12:14:44', NULL, NULL, '0A01A04');
INSERT INTO `atm_product_detailtype` VALUES ('1551426994546180097', '2', 'SLP', 'SLP', 8, NULL, 'atmtest', '2022-07-25 12:39:54', NULL, NULL, '0A01A04');
INSERT INTO `atm_product_detailtype` VALUES ('1551428041146662913', '1', 'GGP', 'Goole Play', 1, NULL, 'atmtest', '2022-07-25 12:44:04', 'atmtest', '2022-08-04 14:37:16', '0A01A04');
INSERT INTO `atm_product_detailtype` VALUES ('1551435560774565890', '1', 'FBK', 'FBK', 2, NULL, 'atmtest', '2022-07-25 13:13:57', NULL, NULL, '0A01A04');
INSERT INTO `atm_product_detailtype` VALUES ('1551435758766686209', '1', 'SPT', 'SPT', 3, NULL, 'atmtest', '2022-07-25 13:14:44', NULL, NULL, '0A01A04');
INSERT INTO `atm_product_detailtype` VALUES ('1551436103685275650', '1', 'STM', 'STM', 4, NULL, 'atmtest', '2022-07-25 13:16:06', NULL, NULL, '0A01A04');
INSERT INTO `atm_product_detailtype` VALUES ('1551436330995580929', '1', 'MOV', 'MOV', 5, NULL, 'atmtest', '2022-07-25 13:17:00', NULL, NULL, '0A01A04');
INSERT INTO `atm_product_detailtype` VALUES ('1551436786429886466', '1', 'XBOX', 'XBOX', 6, NULL, 'atmtest', '2022-07-25 13:18:49', NULL, NULL, '0A01A04');
INSERT INTO `atm_product_detailtype` VALUES ('1551436910484815873', '1', 'PSS', 'PSS', 7, NULL, 'atmtest', '2022-07-25 13:19:18', NULL, NULL, '0A01A04');
INSERT INTO `atm_product_detailtype` VALUES ('1551437083743125505', '1', 'APPLE', 'APPLE', 8, NULL, 'atmtest', '2022-07-25 13:20:00', NULL, NULL, '0A01A04');
INSERT INTO `atm_product_detailtype` VALUES ('1551437341193699329', '3', 'ART', 'ART', 1, NULL, 'atmtest', '2022-07-25 13:21:01', NULL, NULL, '0A01A04');
INSERT INTO `atm_product_detailtype` VALUES ('1551437492213809154', '3', 'SBOX', 'SBOX', 2, NULL, 'atmtest', '2022-07-25 13:21:37', NULL, NULL, '0A01A04');
INSERT INTO `atm_product_detailtype` VALUES ('1551437630332239873', '3', 'AXIE', 'AXIE', 3, NULL, 'atmtest', '2022-07-25 13:22:10', NULL, NULL, '0A01A04');
INSERT INTO `atm_product_detailtype` VALUES ('1551437765657264130', '3', 'MEP', 'MEP', 4, NULL, 'atmtest', '2022-07-25 13:22:42', NULL, NULL, '0A01A04');
INSERT INTO `atm_product_detailtype` VALUES ('1551437907600900097', '3', 'MLD', 'MLD', 5, NULL, 'atmtest', '2022-07-25 13:23:16', NULL, NULL, '0A01A04');

-- ----------------------------
-- Table structure for atm_product_stock
-- ----------------------------
DROP TABLE IF EXISTS `atm_product_stock`;
CREATE TABLE `atm_product_stock`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `batchid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '批次號',
  `products_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品ID',
  `blockchain` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '区块链',
  `contract` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '合約ID',
  `serial_number` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '序列號',
  `img_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '圖片地址',
  `outer_img_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '外部图片地址',
  `price` decimal(22, 4) NULL DEFAULT NULL COMMENT '價格',
  `name` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'NFT名称',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'NFT描述',
  `jsonurl` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'NFT JSON地址',
  `is_vip` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否需VIP特权，0：否，1：是',
  `state` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1' COMMENT '狀態，atm_product_stock_state，1：新建，2：在售，3：鎖定，4:已售',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `atm_product_stock_jsonurl`(`jsonurl`) USING BTREE,
  INDEX `atm_product_stock_batchid`(`batchid`) USING BTREE,
  INDEX `atm_product_stock_productsid`(`products_id`) USING BTREE,
  INDEX `atm_product_stock_serialnumber`(`serial_number`) USING BTREE,
  INDEX `atm_product_stock_state`(`state`) USING BTREE,
  INDEX `atm_product_stock_is_vip`(`is_vip`) USING BTREE,
  INDEX `atm_product_stock_companycode`(`companycode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '產品庫存表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of atm_product_stock
-- ----------------------------

-- ----------------------------
-- Table structure for atm_product_stock_json
-- ----------------------------
DROP TABLE IF EXISTS `atm_product_stock_json`;
CREATE TABLE `atm_product_stock_json`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `product_stock_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品库存ID',
  `json_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'NFTJSON',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `atm_product_stock_productsid`(`product_stock_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'NFTJSON表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of atm_product_stock_json
-- ----------------------------

-- ----------------------------
-- Table structure for atm_products
-- ----------------------------
DROP TABLE IF EXISTS `atm_products`;
CREATE TABLE `atm_products`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品分类，atm_products_type,1：Gift cards，2：Digital Assets，3：NFT & Gaming',
  `product_detailtype_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品类型',
  `factory` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '厂家，atm_products_factory，1：mmatrix',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品编码',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `buyprice` decimal(18, 2) NULL DEFAULT NULL COMMENT '进货价',
  `price` decimal(18, 2) NULL DEFAULT NULL COMMENT '售价',
  `unit` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单位，atm_products_unit，1：张',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '产品描述',
  `is_vip` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否需VIP特权，0：否，1：是',
  `state` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1' COMMENT '产品状态，atm_products_state，1：新建，2：在售，3：停售',
  `handling_fee` decimal(8, 4) NULL DEFAULT 0.0000 COMMENT '手續費（整數金額，如100）',
  `markup` decimal(5, 4) NULL DEFAULT 0.0000 COMMENT '漲價率（百分比）',
  `product_stock` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '產品庫存,atm_products_stock,1：無需庫存，2：內部庫存，3：外部庫存',
  `third_product_id` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '第三方產品ID',
  `tag` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '產品標籤',
  `sortno` int(11) NULL DEFAULT NULL COMMENT '排序',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `atm_products_type`(`type`) USING BTREE,
  INDEX `atm_products_detailtype_id`(`product_detailtype_id`) USING BTREE,
  INDEX `atm_products_code`(`code`) USING BTREE,
  INDEX `atm_products_name`(`name`) USING BTREE,
  INDEX `atm_products_is_vip`(`is_vip`) USING BTREE,
  INDEX `atm_products_state`(`state`) USING BTREE,
  INDEX `atm_products_companycode`(`companycode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '产品信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of atm_products
-- ----------------------------
INSERT INTO `atm_products` VALUES ('1542414967951405058', '2', '1544493541466386434', '1', 'P20001', 'Bitcoin', NULL, NULL, '2', NULL, '0', '1', 100.0000, 0.0750, '1', '', NULL, 1, NULL, 'atmtest', '2022-06-30 15:49:20', 'atmadmin', '2022-11-18 11:21:42', '0A01A04');
INSERT INTO `atm_products` VALUES ('1551453774178979842', '2', '1548827995339235330', '1', 'P20002', '以太幣', NULL, NULL, '2', NULL, '0', '1', 100.0000, 0.0750, '1', '', NULL, 2, NULL, 'atmtest', '2022-07-25 14:26:19', 'atmadmin', '2022-11-18 11:21:32', '0A01A04');
INSERT INTO `atm_products` VALUES ('1551459433335263234', '1', '1551428041146662913', '1', 'P10001', 'HKD200', 100.00, 200.00, '1', '', '0', '1', 0.0000, 0.0000, '3', NULL, NULL, 1, NULL, 'atmtest', '2022-07-25 14:48:48', 'atmtest', '2022-09-22 08:21:53', '0A01A04');
INSERT INTO `atm_products` VALUES ('1551470044358152194', '1', '1551435560774565890', '1', 'P10002', 'FaceBook', 100.00, 200.00, '1', NULL, '0', '1', 0.0000, 0.0000, '3', NULL, NULL, 2, NULL, 'atmtest', '2022-07-25 15:30:58', 'alex', '2022-09-19 14:25:15', '0A01A04');
INSERT INTO `atm_products` VALUES ('1551470408557957121', '1', '1551435758766686209', '1', 'P10003', 'Spotify', 200.00, 380.00, '1', NULL, '0', '1', 0.0000, 0.0000, '3', NULL, NULL, 3, NULL, 'atmtest', '2022-07-25 15:32:25', 'alex', '2022-09-15 09:42:18', '0A01A04');
INSERT INTO `atm_products` VALUES ('1551471308169056258', '3', '1551437341193699329', '1', 'P30001', 'Art gallery', NULL, NULL, NULL, NULL, '0', '1', 0.0000, 0.0000, '2', '', NULL, 1, NULL, 'atmtest', '2022-07-25 15:35:59', 'atmadmin', '2022-11-18 11:22:48', '0A01A04');
INSERT INTO `atm_products` VALUES ('1551472249186324481', '3', '1551437492213809154', '1', 'P30002', 'Sandbox', NULL, NULL, NULL, NULL, '0', '1', 0.0000, 0.0000, '2', '', NULL, 2, NULL, 'atmtest', '2022-07-25 15:39:44', 'atmadmin', '2022-11-18 11:23:12', '0A01A04');
INSERT INTO `atm_products` VALUES ('1551472471727706114', '3', '1551437630332239873', '1', 'P30003', 'Axie Infinity', NULL, NULL, NULL, NULL, '0', '1', 0.0000, 0.0000, '2', '', NULL, 3, NULL, 'atmtest', '2022-07-25 15:40:37', 'atmadmin', '2022-11-18 11:23:23', '0A01A04');
INSERT INTO `atm_products` VALUES ('1555081071595388930', '1', '1551428041146662913', '1', 'P10004', 'HKD500', 250.00, 500.00, '1', NULL, '0', '1', 0.0000, 0.0000, '3', NULL, NULL, 5, NULL, 'atmtest', '2022-08-04 14:39:54', 'alex', '2022-09-15 09:35:35', '0A01A04');
INSERT INTO `atm_products` VALUES ('1557988796906205185', '1', '1551437083743125505', '1', 'P10005', '$100 itunes', 10.00, 100.00, '1', NULL, '0', '1', 0.0000, 0.0000, '3', NULL, NULL, 4, NULL, 'feynman', '2022-08-12 15:14:10', 'alex', '2022-09-15 09:42:10', '0A01A04');
INSERT INTO `atm_products` VALUES ('1569925506715901954', '2', '1551420659960152066', '1', 'P20004', 'USDT', NULL, NULL, '2', NULL, '0', '1', 100.0000, 0.0750, '1', NULL, NULL, 3, NULL, 'atmtest', '2022-09-14 13:46:23', 'atmtest', '2022-09-19 09:33:50', '0A01A04');

-- ----------------------------
-- Table structure for atm_products_his
-- ----------------------------
DROP TABLE IF EXISTS `atm_products_his`;
CREATE TABLE `atm_products_his`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品分类，atm_products_type,1：Gift cards，2：Digital Assets，3：NFT & Gaming',
  `product_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '產品ID',
  `product_detailtype_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品类型',
  `factory` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '厂家，atm_products_factory，1：mmatrix',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品编码',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `buyprice` decimal(18, 2) NULL DEFAULT NULL COMMENT '进货价',
  `price` decimal(18, 2) NULL DEFAULT NULL COMMENT '售价',
  `unit` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单位，atm_products_unit，1：张',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '产品描述',
  `is_vip` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '是否需VIP特权，0：否，1：是',
  `state` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品状态，atm_products_state，1：可售卖，2：不可售卖',
  `handling_fee` decimal(8, 4) NULL DEFAULT 0.0000 COMMENT '手續費（整數金額，如100）',
  `markup` decimal(5, 4) NULL DEFAULT 0.0000 COMMENT '漲價率（百分比）',
  `product_stock` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '產品庫存,atm_products_stock,1：無需庫存，2：內部庫存，3：外部庫存',
  `third_product_id` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '第三方產品ID',
  `tag` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '產品標籤',
  `sortno` int(11) NULL DEFAULT NULL COMMENT '排序',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `atm_productshis_productid`(`product_id`) USING BTREE,
  INDEX `atm_productshis_companycode`(`companycode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '产品信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of atm_products_his
-- ----------------------------

-- ----------------------------
-- Table structure for atm_products_promotion_code
-- ----------------------------
DROP TABLE IF EXISTS `atm_products_promotion_code`;
CREATE TABLE `atm_products_promotion_code`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `products_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品ID',
  `promotion_code_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '优惠码ID',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `atm_prdpromotioncode_productsid`(`products_id`) USING BTREE,
  INDEX `atm_prdpromotioncode_codeid`(`promotion_code_id`) USING BTREE,
  INDEX `atm_prdpromotioncode_companycode`(`companycode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '临时结构表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of atm_products_promotion_code
-- ----------------------------

-- ----------------------------
-- Table structure for atm_promotion_code
-- ----------------------------
DROP TABLE IF EXISTS `atm_promotion_code`;
CREATE TABLE `atm_promotion_code`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '优惠码编号',
  `prom_markup` decimal(5, 4) NOT NULL DEFAULT 0.0000 COMMENT '減免漲價率',
  `prom_handling_fee` decimal(10, 4) NOT NULL DEFAULT 0.0000 COMMENT '減免手續費',
  `is_vip` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '是否需VIP特权，0：否，1：是',
  `eff_date` date NOT NULL COMMENT '生效日期',
  `exp_date` date NOT NULL COMMENT '失效日期',
  `prom_type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '優惠類型，atm_promotioncode_promtype,1:指定產品，2：不指定產品',
  `prom_desc` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '優惠說明',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '临时结构表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of atm_promotion_code
-- ----------------------------

-- ----------------------------
-- Table structure for atm_third_products
-- ----------------------------
DROP TABLE IF EXISTS `atm_third_products`;
CREATE TABLE `atm_third_products`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '第三方產品ID',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '產品名稱',
  `status` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '產品狀態，atm_third_products_status，1：Available,2：NotAvailable',
  `platforms` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '平臺',
  `region` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '區域',
  `quantity` int(11) NULL DEFAULT NULL COMMENT '數量',
  `costprice_usd` decimal(10, 4) NULL DEFAULT NULL COMMENT '成本價（美元）',
  `current_sellingprice_hkd` decimal(10, 4) NOT NULL COMMENT '售價（港幣）',
  `full_sellingprice_hkd` decimal(10, 4) NULL DEFAULT NULL COMMENT '全價（港幣）',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '第三方產品信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of atm_third_products
-- ----------------------------

-- ----------------------------
-- Table structure for atm_transactions
-- ----------------------------
DROP TABLE IF EXISTS `atm_transactions`;
CREATE TABLE `atm_transactions`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `orderid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单号',
  `device_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备ID',
  `userid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备使用人',
  `products_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品ID',
  `product_type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '產品分類',
  `detailtypeid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '產品類型ID',
  `detailtypecode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '產品類型編碼',
  `detailtypename` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '產品類型名稱',
  `paymode` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1' COMMENT '支付方式，atm_transaction_paymode，1：现金',
  `currency` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1' COMMENT '货币类型，atm_currency，1：港币',
  `transaction_id` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '第三方交易ID',
  `transaction_time` datetime NULL DEFAULT NULL COMMENT '交易时间',
  `wallet_address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '钱包地址',
  `atmprice` decimal(22, 8) NULL DEFAULT NULL COMMENT 'ATM购买价格',
  `atmcount` decimal(22, 8) NULL DEFAULT NULL COMMENT 'ATM购买数量',
  `price` decimal(22, 8) NULL DEFAULT NULL COMMENT '购买单价',
  `count` decimal(22, 8) NULL DEFAULT NULL COMMENT '购买数量',
  `markup` decimal(5, 4) NULL DEFAULT 0.0000 COMMENT '漲價率（百分比）',
  `handling_fee` decimal(10, 4) NULL DEFAULT 0.0000 COMMENT '手續費',
  `promotioncode` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '優惠碼ID',
  `prom_markup` decimal(5, 4) NULL DEFAULT 0.0000 COMMENT '減免漲價率',
  `prom_handling_fee` decimal(10, 4) NULL DEFAULT 0.0000 COMMENT '減免手續費',
  `total_amount` decimal(22, 4) NULL DEFAULT NULL COMMENT '應收金額',
  `receipt_amount` decimal(22, 2) NULL DEFAULT NULL COMMENT '实收金額',
  `state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '交易状态，atm_transaction_state，0：交易取消，1：进钱中，2：预交易，3：交易成功，4：交易失败，',
  `process_conclusion` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '處理結論，atm_process_conclusion，1：處理完成，2：處理中，3:无法处理',
  `ic_cardno` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IC卡号',
  `stock_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '库存ID',
  `serial_number` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '序列號',
  `url_check` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '查驗交易',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `atm_transactions_orderid`(`orderid`) USING BTREE,
  INDEX `atm_transactions_device_id`(`device_id`) USING BTREE,
  INDEX `atm_transactions_userid`(`userid`) USING BTREE,
  INDEX `atm_transactions_products_id`(`products_id`) USING BTREE,
  INDEX `atm_transactions_product_type`(`product_type`) USING BTREE,
  INDEX `atm_transactions_detailtypeid`(`detailtypeid`) USING BTREE,
  INDEX `atm_transactions_paymode`(`paymode`) USING BTREE,
  INDEX `atm_transactions_transaction_time`(`transaction_time`) USING BTREE,
  INDEX `atm_transactions_wallet_address`(`wallet_address`) USING BTREE,
  INDEX `atm_transactions_state`(`state`) USING BTREE,
  INDEX `atm_transactions_stock_id`(`stock_id`) USING BTREE,
  INDEX `atm_transactions_serial_number`(`serial_number`) USING BTREE,
  INDEX `atm_transactions_companycode`(`companycode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '交易记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of atm_transactions
-- ----------------------------

-- ----------------------------
-- Table structure for atm_transactions_detail
-- ----------------------------
DROP TABLE IF EXISTS `atm_transactions_detail`;
CREATE TABLE `atm_transactions_detail`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `transactions_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '交易记录ID',
  `device_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备IMEI',
  `billaccept_time` datetime NOT NULL COMMENT '进钱时间',
  `billcount` int(11) NOT NULL COMMENT '进钱数量',
  `parvalue` decimal(18, 2) NOT NULL COMMENT '货币面值',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `atm_transactionsdetail_transactions_id`(`transactions_id`) USING BTREE,
  INDEX `atm_transactionsdetail_device_id`(`device_id`) USING BTREE,
  INDEX `atm_transactionsdetail_billaccept_time`(`billaccept_time`) USING BTREE,
  INDEX `atm_transactionsdetail_companycode`(`companycode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '交易明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of atm_transactions_detail
-- ----------------------------

-- ----------------------------
-- Table structure for atm_transactions_processes
-- ----------------------------
DROP TABLE IF EXISTS `atm_transactions_processes`;
CREATE TABLE `atm_transactions_processes`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `transactions_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '交易记录ID',
  `after_process_state` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '處理結果，交易成功，交易失败',
  `back_money` int(11) NOT NULL COMMENT '退錢金額',
  `process_reason` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '处理說明',
  `process_time` datetime NOT NULL COMMENT '处理时间',
  `process_man` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '处理人',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `atm_traprocesses_transactions_id`(`transactions_id`) USING BTREE,
  INDEX `atm_traprocesses_afterstate`(`after_process_state`) USING BTREE,
  INDEX `atm_traprocesses_processtime`(`process_time`) USING BTREE,
  INDEX `atm_traprocesses_processman`(`process_man`) USING BTREE,
  INDEX `atm_traprocesses_companycode`(`companycode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '交易失败处理记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of atm_transactions_processes
-- ----------------------------

-- ----------------------------
-- Table structure for atm_transactions_processes_his
-- ----------------------------
DROP TABLE IF EXISTS `atm_transactions_processes_his`;
CREATE TABLE `atm_transactions_processes_his`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `processes_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '處理記錄ID',
  `transactions_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '交易记录ID',
  `before_process_state` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '處理前交易狀態',
  `after_process_state` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '處理結果，交易成功，交易失败',
  `back_money` int(11) NOT NULL COMMENT '退錢金額',
  `process_reason` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '处理說明',
  `process_time` datetime NOT NULL COMMENT '处理时间',
  `process_man` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '处理人',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `atm_tranprocesseshis_processes_id`(`processes_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '交易失败处理记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of atm_transactions_processes_his
-- ----------------------------

-- ----------------------------
-- Table structure for atm_wallet_transfer
-- ----------------------------
DROP TABLE IF EXISTS `atm_wallet_transfer`;
CREATE TABLE `atm_wallet_transfer`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `device_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备IMEI码',
  `cardid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '卡号',
  `wallet_type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '钱包类型，atm_wallet_transfer_wallettype，1：BTC，2：ETH',
  `from_wallet_address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '原钱包地址',
  `from_wallet_balance` decimal(22, 8) NOT NULL COMMENT '原钱包余额',
  `to_wallet_address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '转入钱包地址',
  `to_wallet_balance` decimal(22, 8) NOT NULL COMMENT '转入钱包余额',
  `txid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '转移交易ID',
  `url_check` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '查询URL',
  `state` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1' COMMENT '状态，atm_wallet_transfer_state，1：失败，2：成功',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `atm_wallet_transfer_device_id`(`device_id`) USING BTREE,
  INDEX `atm_wallet_transfer_cardid`(`cardid`) USING BTREE,
  INDEX `atm_wallet_transfer_wallet_type`(`wallet_type`) USING BTREE,
  INDEX `atm_wallet_transfer_fromaddress`(`from_wallet_address`) USING BTREE,
  INDEX `atm_wallet_transfer_toaddress`(`to_wallet_address`) USING BTREE,
  INDEX `atm_wallet_transfer_state`(`state`) USING BTREE,
  INDEX `atm_wallet_transfer_companycode`(`companycode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '钱包余额转移记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of atm_wallet_transfer
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `BLOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'InnoDB free: 504832 kB; (`SCHED_NAME` `TRIGGER_NAME` `TRIGGE' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `CRON_EXPRESSION` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'InnoDB free: 504832 kB; (`SCHED_NAME` `TRIGGER_NAME` `TRIGGE' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('quartzScheduler', 'com.easyway.modules.daatm.inf.job.ExportActionLogAndUploadOss', 'DEFAULT', '1 10 11 * * ? *', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('quartzScheduler', 'com.easyway.modules.daatm.inf.job.GetCoinPriceTask', 'DEFAULT', '0/30 * * * * ? *', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('quartzScheduler', 'com.easyway.modules.daatm.inf.job.SaveDeviceMoneyBoxWarnTask', 'DEFAULT', '0 0,5,10,15,20,25,30,35,40,45,50,55 * * * ? *', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('quartzScheduler', 'com.easyway.modules.daatm.inf.job.SaveDeviceStatusWarnTask', 'DEFAULT', '0/30 * * * * ? *', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('quartzScheduler', 'com.easyway.modules.daatm.inf.job.sendAtmBusiLogEmailTask', 'DEFAULT', '59/59 * * * * ? *', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('quartzScheduler', 'org.jeecg.modules.message.job.TestJob', 'DEFAULT', '0/30 * * * * ? *', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('quartzScheduler', 'org.jeecg.modules.quartz.job.SampleJob', 'DEFAULT', '0/30 * * * * ? *', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for QRTZ_FIRED_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
CREATE TABLE `QRTZ_FIRED_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_FIRED_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('quartzScheduler', 'com.easyway.modules.daatm.inf.job.ExportActionLogAndUploadOss', 'DEFAULT', NULL, 'com.easyway.modules.daatm.inf.job.ExportActionLogAndUploadOss', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C77080000001000000001740009706172616D65746572707800);
INSERT INTO `qrtz_job_details` VALUES ('quartzScheduler', 'com.easyway.modules.daatm.inf.job.GetCoinPriceTask', 'DEFAULT', NULL, 'com.easyway.modules.daatm.inf.job.GetCoinPriceTask', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C77080000001000000001740009706172616D65746572707800);
INSERT INTO `qrtz_job_details` VALUES ('quartzScheduler', 'com.easyway.modules.daatm.inf.job.SaveDeviceMoneyBoxWarnTask', 'DEFAULT', NULL, 'com.easyway.modules.daatm.inf.job.SaveDeviceMoneyBoxWarnTask', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C77080000001000000001740009706172616D65746572707800);
INSERT INTO `qrtz_job_details` VALUES ('quartzScheduler', 'com.easyway.modules.daatm.inf.job.SaveDeviceStatusWarnTask', 'DEFAULT', NULL, 'com.easyway.modules.daatm.inf.job.SaveDeviceStatusWarnTask', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C77080000001000000001740009706172616D65746572707800);
INSERT INTO `qrtz_job_details` VALUES ('quartzScheduler', 'com.easyway.modules.daatm.inf.job.sendAtmBusiLogEmailTask', 'DEFAULT', NULL, 'com.easyway.modules.daatm.inf.job.sendAtmBusiLogEmailTask', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C77080000001000000001740009706172616D65746572707800);
INSERT INTO `qrtz_job_details` VALUES ('quartzScheduler', 'org.jeecg.modules.message.job.TestJob', 'DEFAULT', NULL, 'org.jeecg.modules.message.job.TestJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C77080000001000000001740009706172616D65746572707800);
INSERT INTO `qrtz_job_details` VALUES ('quartzScheduler', 'org.jeecg.modules.quartz.job.SampleJob', 'DEFAULT', NULL, 'org.jeecg.modules.quartz.job.SampleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C77080000001000000001740009706172616D65746572707800);

-- ----------------------------
-- Table structure for QRTZ_LOCKS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_LOCKS`;
CREATE TABLE `QRTZ_LOCKS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_LOCKS
-- ----------------------------
INSERT INTO `QRTZ_LOCKS` VALUES ('quartzScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'InnoDB free: 504832 kB; (`SCHED_NAME` `TRIGGER_NAME` `TRIGGE' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `INT_PROP_1` int(11) NULL DEFAULT NULL,
  `INT_PROP_2` int(11) NULL DEFAULT NULL,
  `LONG_PROP_1` bigint(20) NULL DEFAULT NULL,
  `LONG_PROP_2` bigint(20) NULL DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) NULL DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) NULL DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'InnoDB free: 504832 kB; (`SCHED_NAME` `TRIGGER_NAME` `TRIGGE' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
CREATE TABLE `QRTZ_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PRIORITY` int(11) NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'InnoDB free: 504832 kB; (`SCHED_NAME` `JOB_NAME` `JOB_GROUP`' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_TRIGGERS
-- ----------------------------
INSERT INTO `QRTZ_TRIGGERS` VALUES ('quartzScheduler', 'com.easyway.modules.daatm.inf.job.ExportActionLogAndUploadOss', 'DEFAULT', 'com.easyway.modules.daatm.inf.job.ExportActionLogAndUploadOss', 'DEFAULT', NULL, 1669691401000, 1669605001000, 5, 'PAUSED', 'CRON', 1669604404000, 0, NULL, 0, '');
INSERT INTO `QRTZ_TRIGGERS` VALUES ('quartzScheduler', 'com.easyway.modules.daatm.inf.job.GetCoinPriceTask', 'DEFAULT', 'com.easyway.modules.daatm.inf.job.GetCoinPriceTask', 'DEFAULT', NULL, 1668739230000, 1668739200000, 5, 'PAUSED', 'CRON', 1663135904000, 0, NULL, 0, '');
INSERT INTO `QRTZ_TRIGGERS` VALUES ('quartzScheduler', 'com.easyway.modules.daatm.inf.job.SaveDeviceMoneyBoxWarnTask', 'DEFAULT', 'com.easyway.modules.daatm.inf.job.SaveDeviceMoneyBoxWarnTask', 'DEFAULT', NULL, 1668739500000, 1668739200000, 5, 'PAUSED', 'CRON', 1668501639000, 0, NULL, 0, '');
INSERT INTO `QRTZ_TRIGGERS` VALUES ('quartzScheduler', 'com.easyway.modules.daatm.inf.job.SaveDeviceStatusWarnTask', 'DEFAULT', 'com.easyway.modules.daatm.inf.job.SaveDeviceStatusWarnTask', 'DEFAULT', NULL, 1668739230000, 1668739200000, 5, 'PAUSED', 'CRON', 1668061283000, 0, NULL, 0, '');
INSERT INTO `QRTZ_TRIGGERS` VALUES ('quartzScheduler', 'com.easyway.modules.daatm.inf.job.sendAtmBusiLogEmailTask', 'DEFAULT', 'com.easyway.modules.daatm.inf.job.sendAtmBusiLogEmailTask', 'DEFAULT', NULL, 1668506339000, 1668506279000, 5, 'PAUSED', 'CRON', 1668501662000, 0, NULL, 0, '');
INSERT INTO `QRTZ_TRIGGERS` VALUES ('quartzScheduler', 'org.jeecg.modules.message.job.TestJob', 'DEFAULT', 'org.jeecg.modules.message.job.TestJob', 'DEFAULT', NULL, 1658974980000, 1658974950000, 5, 'ERROR', 'CRON', 1658974937000, 0, NULL, 0, '');
INSERT INTO `QRTZ_TRIGGERS` VALUES ('quartzScheduler', 'org.jeecg.modules.quartz.job.SampleJob', 'DEFAULT', 'org.jeecg.modules.quartz.job.SampleJob', 'DEFAULT', NULL, 1658916120000, 1658916090000, 5, 'PAUSED', 'CRON', 1658916081000, 0, NULL, 0, '');

-- ----------------------------
-- Table structure for sys_announcement
-- ----------------------------
DROP TABLE IF EXISTS `sys_announcement`;
CREATE TABLE `sys_announcement`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `titile` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `msg_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `sender` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发布人',
  `priority` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '优先级（L低，M中，H高）',
  `msg_category` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '2' COMMENT '消息类型1:通知公告2:系统消息',
  `msg_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通告对象类型（USER:指定用户，ALL:全体用户）',
  `send_status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发布状态（0未发布，1已发布，2已撤销）',
  `send_time` datetime NULL DEFAULT NULL COMMENT '发布时间',
  `cancel_time` datetime NULL DEFAULT NULL COMMENT '撤销时间',
  `del_flag` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '删除状态（0，正常，1已删除）',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `user_ids` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '指定用户',
  `companycode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机构编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统通告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_announcement
-- ----------------------------

-- ----------------------------
-- Table structure for sys_announcement_send
-- ----------------------------
DROP TABLE IF EXISTS `sys_announcement_send`;
CREATE TABLE `sys_announcement_send`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `annt_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通告ID',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `read_flag` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '阅读状态（0未读，1已读）',
  `read_time` datetime NULL DEFAULT NULL COMMENT '阅读时间',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户通告阅读标记表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_announcement_send
-- ----------------------------

-- ----------------------------
-- Table structure for sys_attachment
-- ----------------------------
DROP TABLE IF EXISTS `sys_attachment`;
CREATE TABLE `sys_attachment`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `moudlecode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模块编码',
  `moudlename` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模块名称',
  `busitype` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `busitypename` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务类型名称',
  `busino` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务编号，如单据号，流水号',
  `businame` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务名称',
  `childbusino` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务子编号，如标准编码',
  `childname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '子名称',
  `leafbusino` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务子编号，如标准项编码',
  `leafname` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '叶子名称',
  `busidesc` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务描述',
  `filetype` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件类型',
  `filename` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件名称',
  `filepath` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件路径',
  `filesize` int(11) NULL DEFAULT NULL COMMENT '附件大小',
  `filesavetype` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件存储方式，1：阿里云，2：文件服务器',
  `mobilefilepath` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '移动设备附件路径',
  `expand1` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段1',
  `expand2` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  `expand3` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段3',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `attachment_busino`(`busino`) USING BTREE,
  INDEX `attachment_childbusino`(`childbusino`) USING BTREE,
  INDEX `attachment_expand1`(`expand1`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '附件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_attachment
-- ----------------------------

-- ----------------------------
-- Table structure for sys_busi_number
-- ----------------------------
DROP TABLE IF EXISTS `sys_busi_number`;
CREATE TABLE `sys_busi_number`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '业务ID',
  `current_number` int(11) NOT NULL COMMENT '业务号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '业务编号表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_busi_number
-- ----------------------------

-- ----------------------------
-- Table structure for sys_category
-- ----------------------------
DROP TABLE IF EXISTS `sys_category`;
CREATE TABLE `sys_category`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `pid` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级节点',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型名称',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型编码',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新日期',
  `sys_org_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属部门',
  `has_child` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否有子节点',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_category
-- ----------------------------

-- ----------------------------
-- Table structure for sys_data_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_data_log`;
CREATE TABLE `sys_data_log`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人登录名称',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人登录名称',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新日期',
  `data_table` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表名',
  `data_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据ID',
  `data_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '数据内容',
  `data_version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sindex`(`data_table`, `data_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_data_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_depart
-- ----------------------------
DROP TABLE IF EXISTS `sys_depart`;
CREATE TABLE `sys_depart`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `parent_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父机构ID',
  `depart_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '机构/部门名称',
  `depart_nametw` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机构/部门名称繁体',
  `depart_nameeg` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机构/部门名称英文',
  `depart_name_en` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '英文名',
  `depart_name_abbr` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '缩写',
  `depart_order` int(11) NULL DEFAULT 0 COMMENT '排序',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '描述',
  `org_category` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1' COMMENT '机构类别 1组织机构，2岗位',
  `org_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机构类型 1一级部门 2子部门',
  `org_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '机构编码',
  `mobile` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `fax` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '传真',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `memo` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态（1启用，0不启用）',
  `del_flag` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '删除状态（0，正常，1已删除）',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新日期',
  `fake_org_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '机构编码',
  `mnemonic_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '助记码',
  `expend1` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机构特性，1：改造小组',
  `expend2` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展2',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_depart_parent_id`(`parent_id`) USING BTREE,
  INDEX `index_depart_depart_order`(`depart_order`) USING BTREE,
  INDEX `index_depart_org_code`(`org_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '组织机构表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_depart
-- ----------------------------
INSERT INTO `sys_depart` VALUES ('16fb593249ab4505a2e96ea8da93e152', '', '集团总部', '集團總部', '集團總部', NULL, NULL, 0, NULL, '1', '0', '0', NULL, NULL, NULL, '系统级', NULL, '0', 'admin', '2019-12-11 10:23:00', 'admin', '2021-10-22 15:41:05', 'JTZB', 'JTZB', NULL, NULL);
INSERT INTO `sys_depart` VALUES ('4d2d4e9e230e4f009fa46234849c0acc', 'f9b0355f56ca4c7bb42d6f8a9d8246f4', 'ATM', 'ATM', 'ATM', NULL, NULL, 0, NULL, '1', '3', '0A01A04A03', NULL, NULL, NULL, NULL, NULL, '0', 'atmtest', '2022-06-28 10:34:23', NULL, NULL, 'DA01003', NULL, '1', NULL);
INSERT INTO `sys_depart` VALUES ('68a63481331e422cba05417f1cf7683c', 'f9b0355f56ca4c7bb42d6f8a9d8246f4', '技术部', '技術部', 'technology', NULL, NULL, 0, NULL, '1', '3', '0A01A04A02', NULL, NULL, NULL, NULL, NULL, '0', 'feynman', '2022-06-28 10:31:30', NULL, NULL, 'DA01002', NULL, NULL, NULL);
INSERT INTO `sys_depart` VALUES ('c19ea1fc2b0e48249e8992a6eeadc0a8', 'f9b0355f56ca4c7bb42d6f8a9d8246f4', '信息部', '信息部', 'IT', '', NULL, 0, NULL, '1', '3', '0A01A04A01', NULL, NULL, NULL, NULL, NULL, '0', 'admin', '2022-05-23 15:17:17', 'atmtest', '2022-06-09 17:22:45', 'DA01001', NULL, '1', NULL);
INSERT INTO `sys_depart` VALUES ('ebb4aa026a4f4706ac66db98dee3a943', '16fb593249ab4505a2e96ea8da93e152', '总公司', '總公司', '總公司', NULL, NULL, 0, NULL, '2', '1', '0A01', NULL, NULL, NULL, NULL, NULL, '0', 'admin', '2019-12-19 17:13:19', 'admin', '2022-05-30 14:59:12', 'ZGS', 'CCJT', NULL, NULL);
INSERT INTO `sys_depart` VALUES ('f9b0355f56ca4c7bb42d6f8a9d8246f4', 'ebb4aa026a4f4706ac66db98dee3a943', 'DAATM', 'DAATM', 'DAATM', NULL, NULL, 4, NULL, '1', '2', '0A01A04', NULL, NULL, NULL, NULL, NULL, '0', 'admin', '2022-05-23 15:16:51', NULL, NULL, 'DA01', NULL, '0', NULL);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `dict_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典名称',
  `dict_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典编码',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `del_flag` int(1) NULL DEFAULT NULL COMMENT '删除状态',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `type` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '字典类型0为string,1为number',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `indextable_dict_code`(`dict_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('0b5d19e1fce4b2e6647e6b4a17760c14', '通告类型', 'msg_category', '消息类型1:通知公告2:系统消息', 0, 'admin', '2019-04-22 18:01:35', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1174509082208395266', '职务职级', 'position_rank', '职务表职级字典', 0, 'admin', '2019-09-19 10:22:41', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1174511106530525185', '机构类型', 'org_category', '机构类型 1组织机构，2岗位', 0, 'admin', '2019-09-19 10:30:43', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1178295274528845826', '表单权限策略', 'form_perms_type', '', 0, 'admin', '2019-09-29 21:07:39', 'admin', '2019-09-29 21:08:26', NULL);
INSERT INTO `sys_dict` VALUES ('1209287037493080066', '系统模块', 'navigation_bar', '', 0, 'admin', '2019-12-24 09:37:52', 'admin', '2020-04-09 13:59:26', 0);
INSERT INTO `sys_dict` VALUES ('1242009427422511105', '应用平台', 'Application_platform', '', 0, 'Jervan', '2020-03-23 16:44:58', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1274989494605012993', '标准分类', 'emp_template_type', '新增标准时使用的标准分类', 0, 'admin', '2020-06-22 16:55:58', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1274989885258293249', '标准的适用场景', 'emp_template_flag', '新增标准时选择的适用场景', 0, 'admin', '2020-06-22 16:57:31', 'admin', '2020-06-22 16:57:52', 0);
INSERT INTO `sys_dict` VALUES ('1275705148589035522', '标准业务类型', 'emp_templateblock_busitype', '', 0, 'admin', '2020-06-24 16:19:44', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1275705567432232962', '标准启用停用状态', 'emp_templateblock_state', '', 0, 'admin', '2020-06-24 16:21:23', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1275709149845999617', '标准项是否拍照', 'emp_templateitem_istakepictures', '', 0, 'admin', '2020-06-24 16:35:38', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1275709250433798146', '标准项是否记录', 'emp_templateitem_isrecord', '', 0, 'admin', '2020-06-24 16:36:01', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1275709411583152129', '标准项是否必填项', 'emp_templateitem_isrequired', '', 0, 'admin', '2020-06-24 16:36:40', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1275709541921148930', '标准项类型', 'emp_templateitem_type', '', 0, 'admin', '2020-06-24 16:37:11', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1275710362465423362', '标准项是否隐患项', 'emp_templateitem_troubleflag', '', 0, 'admin', '2020-06-24 16:40:27', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1303135621148942337', '设备状态', 'emp_mobiledevice_status', '移动管理的设备状态', 0, 'admin', '2020-09-08 08:58:38', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1303137357607575553', '应用授权设备状态', 'emp_deviceapp_status', 'App授权给设备的状态', 0, 'admin', '2020-09-08 09:05:32', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1415208860840955905', '设备应用状态', 'device_app_status', '', 0, 'kevin', '2021-07-14 15:17:40', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1427918804695732226', '机构特性', 'dept_type', '创建机构时选择的机构类型', 0, 'billy', '2021-08-18 17:02:27', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1527583839587909633', '是', '0', '', 1, 'wh005', '2022-05-20 17:35:43', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1541251551633092609', '产品类型', 'atm_products_type', '出售产品的类型', 0, 'admin', '2022-06-27 10:46:20', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1541252446802423809', '产品单位', 'atm_products_unit', '', 0, 'admin', '2022-06-27 10:49:53', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1541252766211256321', '产品状态', 'atm_products_state', '', 0, 'admin', '2022-06-27 10:51:09', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1541254134909120513', '收费标准', 'atm_products_chargestandard', '', 1, 'admin', '2022-06-27 10:56:36', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1542402744424148993', '支付方式', 'atm_transaction_paymode', '', 0, 'feynman', '2022-06-30 15:00:45', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1542403037668913153', '貨幣類型', 'atm_currency', '', 0, 'feynman', '2022-06-30 15:01:55', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1542403518810107905', '交易狀態', 'atm_transaction_state', '', 0, 'feynman', '2022-06-30 15:03:50', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1542406271984807937', '是否', 'atm_isornot', '', 0, 'feynman', '2022-06-30 15:14:46', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1542411159816974337', '连接状态', 'atm_device_status_connectstate', '', 0, 'feynman', '2022-06-30 15:34:12', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1544491463067119618', '產品類型', 'atm_product_detailtype_type', '', 0, 'admin', '2022-07-06 09:20:35', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1544564433462571010', '電子鎖狀態', 'atm_device_status_elockstate', '', 0, 'feynman', '2022-07-06 14:10:32', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1544565300735586306', '設備維護狀態', 'atm_device_status_fixstate', '', 0, 'admin', '2022-07-06 14:13:59', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1544976589563723778', '廠家', 'atm_products_factory', '', 0, 'admin', '2022-07-07 17:28:18', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1547137044025470977', '優惠類型', 'atm_promotioncode_promtype', '', 0, 'admin', '2022-07-13 16:33:10', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1550047184152227842', '设备维护状态_录入', 'fixState', '', 0, 'admin', '2022-07-21 17:17:02', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1551828973229264898', '处理后状态', 'atm_after_process_state', '', 0, 'admin', '2022-07-26 15:17:13', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1552490756419948545', '參數分類', 'atm_device_param_grouptye', '', 0, 'admin', '2022-07-28 11:06:55', 'admin', '2022-07-28 11:07:10', 0);
INSERT INTO `sys_dict` VALUES ('1552910011011551234', '操作類型', 'atm_device_config_log_type', '', 0, 'admin', '2022-07-29 14:52:53', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1554641626351984642', '库存状态', 'atm_product_stock_state', '狀態，atm_product_stock_state，1：新建，2：在售，3：鎖定，4:已售', 0, 'atmtest', '2022-08-03 09:33:42', 'admin', '2022-08-09 10:36:25', 0);
INSERT INTO `sys_dict` VALUES ('1554730111058415618', '廣告狀態', 'atm_advertisements_state', '1：新建，2：發布，3：停用', 0, 'admin', '2022-08-03 15:25:19', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1556830920969646081', '產品庫存', 'atm_products_stock', '1：無需庫存，2：內部庫存，3：外部庫存', 0, 'admin', '2022-08-09 10:33:11', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1557974367369793538', '產品狀態', 'atm_third_products_status', '產品狀態，atm_third_products_status，1：Available,2：NotAvailable', 0, 'admin', '2022-08-12 14:16:50', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1579011844264132610', '设备日志类型', 'atm_busi_log_logtype', '日志类型，atm_busi_log_logtype，1：交易日志，2：钱包日志，3：在线支付日志，4：其他日志', 0, 'admin', '2022-10-09 15:32:15', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1579390579903557633', '日志級別', 'atm_busi_log_loglevel', '日志级别，atm_busi_log_loglevel，1：操作记录，2：校验错误，3：程序异常', 0, 'admin', '2022-10-10 16:37:13', 'admin', '2022-10-10 16:57:49', 0);
INSERT INTO `sys_dict` VALUES ('1586999526583783425', '設備維護狀態_顯示', 'show_fixState', '', 0, 'feynman', '2022-10-31 16:32:27', 'admin', '2022-10-31 16:33:10', 0);
INSERT INTO `sys_dict` VALUES ('1587647194343522305', '錢包類型', 'atm_wallet_transfer_wallettype', '', 0, 'admin', '2022-11-02 11:26:03', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('1598250826002329601', '餘額轉移狀態', 'atm_wallet_transfer_state', '', 0, 'admin', '2022-12-01 17:41:06', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('236e8a4baff0db8c62c00dd95632834f', '同步工作流引擎', 'activiti_sync', '同步工作流引擎', 0, 'admin', '2019-05-15 15:27:33', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('2e02df51611a4b9632828ab7e5338f00', '权限策略', 'perms_type', '权限策略', 0, 'admin', '2019-04-26 18:26:55', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('2f0320997ade5dd147c90130f7218c3e', '推送类别', 'msg_type', '', 0, 'admin', '2019-03-17 21:21:32', 'admin', '2019-03-26 19:57:45', 0);
INSERT INTO `sys_dict` VALUES ('3486f32803bb953e7155dab3513dc68b', '删除状态', 'del_flag', NULL, 0, 'admin', '2019-01-18 21:46:26', 'admin', '2019-03-30 11:17:11', 0);
INSERT INTO `sys_dict` VALUES ('3d9a351be3436fbefb1307d4cfb49bf2', '性别', 'sex', NULL, 0, NULL, '2019-01-04 14:56:32', 'admin', '2019-03-30 11:28:27', 1);
INSERT INTO `sys_dict` VALUES ('4274efc2292239b6f000b153f50823ff', '全局权限策略', 'global_perms_type', '全局权限策略', 0, 'admin', '2019-05-10 17:54:05', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('4c03fca6bf1f0299c381213961566349', 'Online图表展示模板', 'online_graph_display_template', 'Online图表展示模板', 0, 'admin', '2019-04-12 17:28:50', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('4c753b5293304e7a445fd2741b46529d', '字典状态', 'dict_item_status', NULL, 0, 'admin', '2020-06-18 23:18:42', 'admin', '2019-03-30 19:33:52', 1);
INSERT INTO `sys_dict` VALUES ('4d7fec1a7799a436d26d02325eff295e', '优先级', 'priority', '优先级', 0, 'admin', '2019-03-16 17:03:34', 'admin', '2019-04-16 17:39:23', 0);
INSERT INTO `sys_dict` VALUES ('4e4602b3e3686f0911384e188dc7efb4', '条件规则', 'rule_conditions', '', 0, 'admin', '2019-04-01 10:15:03', 'admin', '2019-04-01 10:30:47', 0);
INSERT INTO `sys_dict` VALUES ('4f69be5f507accea8d5df5f11346181a', '发送消息类型', 'msgType', NULL, 0, 'admin', '2019-04-11 14:27:09', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('68168534ff5065a152bfab275c2136f8', '有效无效状态', 'valid_status', '有效无效状态', 0, 'admin', '2020-09-26 19:21:14', 'admin', '2019-04-26 19:21:23', 0);
INSERT INTO `sys_dict` VALUES ('72cce0989df68887546746d8f09811aa', 'Online表单类型', 'cgform_table_type', '', 0, 'admin', '2019-01-27 10:13:02', 'admin', '2019-03-30 11:37:36', 0);
INSERT INTO `sys_dict` VALUES ('78bda155fe380b1b3f175f1e88c284c6', '流程状态', 'bpm_status', '流程状态', 0, 'admin', '2019-05-09 16:31:52', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('83bfb33147013cc81640d5fd9eda030c', '日志类型', 'log_type', NULL, 0, 'admin', '2019-03-18 23:22:19', NULL, NULL, 1);
INSERT INTO `sys_dict` VALUES ('880a895c98afeca9d9ac39f29e67c13e', '操作类型', 'operate_type', '操作类型', 0, 'admin', '2019-07-22 10:54:29', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('8dfe32e2d29ea9430a988b3b558bf233', '发布状态', 'send_status', '发布状态', 0, 'admin', '2019-04-16 17:40:42', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('a9d9942bd0eccb6e89de92d130ec4c4a', '消息发送状态', 'msgSendStatus', NULL, 0, 'admin', '2019-04-12 18:18:17', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('bd1b8bc28e65d6feefefb6f3c79f42fd', 'Online图表数据类型', 'online_graph_data_type', 'Online图表数据类型', 0, 'admin', '2019-04-12 17:24:24', 'admin', '2019-04-12 17:24:57', 0);
INSERT INTO `sys_dict` VALUES ('c36169beb12de8a71c8683ee7c28a503', '部门状态', 'depart_status', NULL, 0, 'admin', '2019-03-18 21:59:51', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('c5a14c75172783d72cbee6ee7f5df5d1', 'Online图表类型', 'online_graph_type', 'Online图表类型', 0, 'admin', '2019-04-12 17:04:06', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('fc6cd58fde2e8481db10d3a1e68ce70c', '用户状态', 'user_status', NULL, 0, 'admin', '2019-03-18 21:57:25', 'admin', '2019-03-18 23:11:58', 1);

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `dict_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典id',
  `item_text` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典项文本简体',
  `item_texttw` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典项文本繁体',
  `item_texten` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典项文本英文',
  `item_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典项值',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `sort_order` int(10) NULL DEFAULT NULL COMMENT '排序',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态（1启用 0不启用）',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公司编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_table_dict_id`(`dict_id`) USING BTREE,
  INDEX `index_table_sort_order`(`sort_order`) USING BTREE,
  INDEX `index_table_dict_status`(`status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典值' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES ('0072d115e07c875d76c9b022e2179128', '4d7fec1a7799a436d26d02325eff295e', '低', '低', '低', 'L', '低', 3, 1, 'admin', '2019-04-16 17:04:59', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('05a2e732ce7b00aa52141ecc3e330b4e', '3486f32803bb953e7155dab3513dc68b', '已删除', '已刪除', '已刪除', '1', NULL, NULL, 1, 'admin', '2025-10-18 21:46:56', 'admin', '2019-03-28 22:23:20', '0');
INSERT INTO `sys_dict_item` VALUES ('096c2e758d823def3855f6376bc736fb', 'bd1b8bc28e65d6feefefb6f3c79f42fd', 'SQL', 'SQL', 'SQL', 'sql', NULL, 1, 1, 'admin', '2019-04-12 17:26:26', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('0c9532916f5cd722017b46bc4d953e41', '2f0320997ade5dd147c90130f7218c3e', '指定用户', '指定用戶', '指定用戶', 'USER', NULL, NULL, 1, 'admin', '2019-03-17 21:22:19', 'admin', '2019-03-17 21:22:28', '0');
INSERT INTO `sys_dict_item` VALUES ('0ca4beba9efc4f9dd54af0911a946d5c', '72cce0989df68887546746d8f09811aa', '附表', '附表', '附表', '3', NULL, 3, 1, 'admin', '2019-03-27 10:13:43', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1174509601047994369', '1174509082208395266', '员级', '員級', '員級', '1', '', 1, 1, 'admin', '2019-09-19 10:24:45', 'admin', '2019-09-23 11:46:39', '0');
INSERT INTO `sys_dict_item` VALUES ('1174509667297026049', '1174509082208395266', '助级', '助級', '助級', '2', '', 2, 1, 'admin', '2019-09-19 10:25:01', 'admin', '2019-09-23 11:46:47', '0');
INSERT INTO `sys_dict_item` VALUES ('1174509713568587777', '1174509082208395266', '中级', '中級', '中級', '3', '', 3, 1, 'admin', '2019-09-19 10:25:12', 'admin', '2019-09-23 11:46:56', '0');
INSERT INTO `sys_dict_item` VALUES ('1174509788361416705', '1174509082208395266', '副高级', '副高級', '副高級', '4', '', 4, 1, 'admin', '2019-09-19 10:25:30', 'admin', '2019-09-23 11:47:06', '0');
INSERT INTO `sys_dict_item` VALUES ('1174509835803189250', '1174509082208395266', '正高级', '正高級', '正高級', '5', '', 5, 1, 'admin', '2019-09-19 10:25:41', 'admin', '2019-09-23 11:47:12', '0');
INSERT INTO `sys_dict_item` VALUES ('1174511197735665665', '1174511106530525185', '组织机构', '組織機構', '組織機構', '1', '组织机构', 1, 1, 'admin', '2019-09-19 10:31:05', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1174511244036587521', '1174511106530525185', '岗位', '崗位', '崗位', '2', '岗位', 1, 1, 'admin', '2019-09-19 10:31:16', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1178295553450061826', '1178295274528845826', '可编辑(未授权禁用)', '可編輯(未授權禁用)', '可編輯(未授權禁用)', '2', '', 2, 1, 'admin', '2019-09-29 21:08:46', 'admin', '2019-09-29 21:09:18', '0');
INSERT INTO `sys_dict_item` VALUES ('1178295639554928641', '1178295274528845826', '可见(未授权不可见)', '可見(未授權不可見)', '可見(未授權不可見)', '1', '', 1, 1, 'admin', '2019-09-29 21:09:06', 'admin', '2019-09-29 21:09:24', '0');
INSERT INTO `sys_dict_item` VALUES ('1209291794362298369', '1209287037493080066', '统计报表', '統計報表', '統計報表', '3', '', 3, 1, 'admin', '2019-12-24 09:56:46', 'admin', '2022-07-04 16:02:18', '0');
INSERT INTO `sys_dict_item` VALUES ('1209291877396934657', '1209287037493080066', '系统管理', '系統管理', '系統管理', '1', '', 1, 1, 'admin', '2019-12-24 09:57:06', 'admin', '2022-07-04 16:02:08', '0');
INSERT INTO `sys_dict_item` VALUES ('1209291948326809601', '1209287037493080066', '移动管理', '移動管理', '移动管理', '2', '', 2, 1, 'admin', '2019-12-24 09:57:23', 'admin', '2022-07-14 08:45:05', '0');
INSERT INTO `sys_dict_item` VALUES ('1242009521114873857', '1242009427422511105', 'Android', 'Android', 'Android', '0', '', 1, 1, 'Jervan', '2020-03-23 16:45:20', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1242009542786842626', '1242009427422511105', 'IOS', 'IOS', 'IOS', '1', '', 1, 1, 'Jervan', '2020-03-23 16:45:25', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1275705624428630017', '1275705567432232962', '启用', '啟用', '啟用', '1', '', 1, 1, 'admin', '2020-06-24 16:21:37', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1275705651804852226', '1275705567432232962', '停用', '停用', '停用', '0', '', 1, 1, 'admin', '2020-06-24 16:21:44', 'admin', '2020-06-24 16:22:01', '0');
INSERT INTO `sys_dict_item` VALUES ('1275709621931692033', '1275709541921148930', '单行文本', '單行文本', '單行文本', '1', '', 1, 1, 'admin', '2020-06-24 16:37:30', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1275709657084153858', '1275709541921148930', '数字文本', '數字文本', '數字文本', '2', '', 1, 1, 'admin', '2020-06-24 16:37:38', 'admin', '2020-06-29 18:12:09', '0');
INSERT INTO `sys_dict_item` VALUES ('1275709691850739713', '1275709541921148930', '日期文本', '日期文本', '日期文本', '3', '', 1, 1, 'admin', '2020-06-24 16:37:47', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1275709719466037249', '1275709541921148930', '多行文本', '多行文本', '多行文本', '4', '', 1, 1, 'admin', '2020-06-24 16:37:53', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1275709747672731650', '1275709541921148930', '单选按钮', '單選按鈕', '單選按鈕', '5', '', 1, 1, 'admin', '2020-06-24 16:38:00', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1275709773136351233', '1275709541921148930', '复选按钮', '複選按鈕', '複選按鈕', '6', '', 1, 1, 'admin', '2020-06-24 16:38:06', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1275709815695953921', '1275709541921148930', '日期时间', '日期時間', '日期時間', '7', '', 1, 1, 'admin', '2020-06-24 16:38:16', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1275709850206687234', '1275709541921148930', '文本描述', '文本描述', '文本描述', '8', '', 1, 1, 'admin', '2020-06-24 16:38:24', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1275709955974451202', '1275709411583152129', '是', '是', '是', '1', '', 1, 1, 'admin', '2020-06-24 16:38:50', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1275709981807169538', '1275709411583152129', '否', '否', '否', '0', '', 1, 1, 'admin', '2020-06-24 16:38:56', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1275710111654432769', '1275709250433798146', '是', '是', '是', '1', '', 1, 1, 'admin', '2020-06-24 16:39:27', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1275710136333717506', '1275709250433798146', '否', '否', '否', '0', '', 1, 1, 'admin', '2020-06-24 16:39:33', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1275710206852550657', '1275709149845999617', '是', '是', '是', '1', '', 1, 1, 'admin', '2020-06-24 16:39:50', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1275710232291004417', '1275709149845999617', '否', '否', '否', '0', '', 1, 1, 'admin', '2020-06-24 16:39:56', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1275710437438607362', '1275710362465423362', '是', '是', '是', '1', '', 1, 1, 'admin', '2020-06-24 16:40:44', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1275710458624036866', '1275710362465423362', '否', '否', '否', '0', '', 1, 1, 'admin', '2020-06-24 16:40:50', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1303135805165641729', '1303135621148942337', '启用', '啟用', '啟用', '1', '设备启用状态', 1, 1, 'admin', '2020-09-08 08:59:22', 'admin', '2022-08-01 18:25:56', '0');
INSERT INTO `sys_dict_item` VALUES ('1303135913449988097', '1303135621148942337', '禁用', '禁用', '禁用', '2', '设备禁用状态', 2, 1, 'admin', '2020-09-08 08:59:48', 'admin', '2022-08-01 18:26:03', '0');
INSERT INTO `sys_dict_item` VALUES ('1303137552609157121', '1303137357607575553', '已授权', '已授權', '已授權', '1', '已经授权，可以使用', 1, 1, 'admin', '2020-09-08 09:06:18', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1303137650491629569', '1303137357607575553', '未授权', '未授權', '未授權', '2', '还没有授权，不能使用', 2, 1, 'admin', '2020-09-08 09:06:42', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1424623209663303681', '1415208860840955905', '启用', '啟用', '啟用', '1', '', 1, 1, 'feynman', '2021-08-09 14:46:56', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1424623318941700097', '1415208860840955905', '禁用', '禁用', '禁用', '0', '', 1, 1, 'feynman', '2021-08-09 14:47:22', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1427918893392678914', '1427918804695732226', 'ATM', 'ATM', 'ATM', '1', 'ATM用户', 2, 1, 'billy', '2021-08-18 17:02:48', 'admin', '2022-06-09 17:06:11', '0');
INSERT INTO `sys_dict_item` VALUES ('1451069142250582017', '1427918804695732226', '分公司', '分公司', '分公司', '0', '', 1, 1, 'admin', '2021-10-21 14:13:38', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1458988136926453761', '1458987821103751170', '待处理', '待处理', '待处理', '1', '', 1, 1, 'admin', '2021-11-12 10:40:53', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1458988238738989058', '1458987821103751170', '处理完成', '处理完成', '处理完成', '2', '', 1, 1, 'admin', '2021-11-12 10:41:17', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1458988294300934146', '1458987821103751170', '处理失败', '处理失败', '处理失败', '3', '', 1, 1, 'admin', '2021-11-12 10:41:31', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1523511085347573761', '1523510558681403393', '否', '否', '否', '0', '', 1, 1, 'feynman', '2022-05-09 11:52:03', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1523511137310806018', '1523510558681403393', '是', '是', '是', '1', '', 2, 1, 'feynman', '2022-05-09 11:52:15', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1523511191505408002', '1523510681255743490', '否', '否', '否', '0', '', 1, 1, 'feynman', '2022-05-09 11:52:28', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1523511239454691329', '1523510681255743490', '是', '是', '是', '1', '', 2, 1, 'feynman', '2022-05-09 11:52:40', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1523512793586929666', '1523512554809397250', '否', '否', '否', '0', '', 1, 1, 'feynman', '2022-05-09 11:58:50', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1523512840881901569', '1523512554809397250', '是', '是', '是', '1', '', 2, 1, 'feynman', '2022-05-09 11:59:01', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1523512893864349697', '1523512742609358850', '否', '否', '否', '0', '', 1, 1, 'feynman', '2022-05-09 11:59:14', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1523512937787101186', '1523512742609358850', '是', '是', '是', '1', '', 2, 1, 'feynman', '2022-05-09 11:59:25', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1523556808147587074', '1523556507592151042', '否', '否', '否', '0', '', 1, 1, 'feynman', '2022-05-09 14:53:44', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1523556855815852034', '1523556507592151042', '是', '是', '是', '1', '', 2, 1, 'feynman', '2022-05-09 14:53:55', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1523556912321515522', '1523556609157222402', '否', '否', '否', '0', '', 1, 1, 'feynman', '2022-05-09 14:54:09', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1523556961210322945', '1523556609157222402', '是', '是', '是', '1', '', 2, 1, 'feynman', '2022-05-09 14:54:21', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1523557025123127298', '1523556698374262786', '否', '否', '否', '0', '', 1, 1, 'feynman', '2022-05-09 14:54:36', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1523557069318508546', '1523556698374262786', '是', '是', '是', '1', '', 2, 1, 'feynman', '2022-05-09 14:54:46', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1541251759553130498', '1541251551633092609', '虚拟币', '虛擬幣', 'Digital Asset', '2', '', 2, 1, 'admin', '2022-06-27 10:47:09', 'admin', '2022-07-04 17:50:18', '0');
INSERT INTO `sys_dict_item` VALUES ('1541252081851838465', '1541251551633092609', '礼物卡', '禮物卡', 'Gift cards', '1', '', 1, 1, 'admin', '2022-06-27 10:48:26', 'admin', '2022-07-04 17:50:08', '0');
INSERT INTO `sys_dict_item` VALUES ('1541252157127012353', '1541251551633092609', 'NFT & 游戏卡', 'NFT & 遊戲卡', 'NFT & Gaming', '3', '', 3, 1, 'admin', '2022-06-27 10:48:44', 'admin', '2022-07-04 17:51:05', '0');
INSERT INTO `sys_dict_item` VALUES ('1541252604206264322', '1541252446802423809', '张', '張', 'piece', '1', '', 1, 1, 'admin', '2022-06-27 10:50:31', 'admin', '2022-07-06 09:27:40', '0');
INSERT INTO `sys_dict_item` VALUES ('1541252927012483074', '1541252766211256321', '在售', '在售', 'On Sell', '2', '', 2, 1, 'admin', '2022-06-27 10:51:48', 'admin', '2022-07-15 17:28:57', '0');
INSERT INTO `sys_dict_item` VALUES ('1541253131245727745', '1541252766211256321', '停售', '停售', 'Off Sell', '3', '', 3, 1, 'admin', '2022-06-27 10:52:36', 'admin', '2022-07-15 17:28:48', '0');
INSERT INTO `sys_dict_item` VALUES ('1541254374521319425', '1541254134909120513', '不收费', '不收費', 'Free', '0', '', 1, 1, 'admin', '2022-06-27 10:57:33', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1541254571322257410', '1541254134909120513', '固定费用', '固定費用', 'Fixed fee', '1', '', 1, 1, 'admin', '2022-06-27 10:58:20', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1541254753996779522', '1541254134909120513', '百分比', '百分比', 'Percentage', '2', '', 1, 1, 'admin', '2022-06-27 10:59:03', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1542402889521901569', '1542402744424148993', '现金', '現金', 'cash', '1', '', 1, 1, 'feynman', '2022-06-30 15:01:20', 'admin', '2022-07-06 09:27:06', '0');
INSERT INTO `sys_dict_item` VALUES ('1542403329378562050', '1542403037668913153', '港币', '港幣', 'Hongkong dollar', '1', '', 1, 1, 'feynman', '2022-06-30 15:03:05', 'admin', '2022-07-06 09:26:53', '0');
INSERT INTO `sys_dict_item` VALUES ('1542403849530978305', '1542403518810107905', '取消交易', '取消交易', 'cancel a transaction', '0', '', 1, 1, 'feynman', '2022-06-30 15:05:09', 'feynman', '2022-06-30 15:12:56', '0');
INSERT INTO `sys_dict_item` VALUES ('1542404505117470721', '1542403518810107905', '进钱中', '進錢中', 'insert money', '1', '', 2, 1, 'feynman', '2022-06-30 15:07:45', 'feynman', '2022-06-30 15:13:01', '0');
INSERT INTO `sys_dict_item` VALUES ('1542405721788592130', '1542403518810107905', '交易成功', '交易成功', 'trade successfully', '3', '', 4, 1, 'feynman', '2022-06-30 15:12:35', 'feynman', '2022-06-30 15:13:10', '0');
INSERT INTO `sys_dict_item` VALUES ('1542406161330679809', '1542403518810107905', '交易失败', '交易失敗', 'failure of transaction', '4', '', 5, 1, 'feynman', '2022-06-30 15:14:20', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1542406410241650690', '1542406271984807937', '否', '否', 'no', '0', '', 1, 1, 'feynman', '2022-06-30 15:15:19', 'admin', '2022-07-06 09:26:39', '0');
INSERT INTO `sys_dict_item` VALUES ('1542406486267604993', '1542406271984807937', '是', '是', 'yes', '1', '', 2, 1, 'feynman', '2022-06-30 15:15:38', 'admin', '2022-07-06 09:26:43', '0');
INSERT INTO `sys_dict_item` VALUES ('1542411359495204866', '1542411159816974337', '在线', '在線', 'on line', '1', '', 1, 1, 'feynman', '2022-06-30 15:34:59', 'admin', '2022-07-06 09:26:12', '0');
INSERT INTO `sys_dict_item` VALUES ('1542411503049453569', '1542411159816974337', '离线', '離線', 'off line', '2', '', 1, 1, 'feynman', '2022-06-30 15:35:34', 'admin', '2022-07-06 09:26:17', '0');
INSERT INTO `sys_dict_item` VALUES ('1542416363987742721', '1541252446802423809', '个', '個', 'BTC', '2', '比特幣單位', 2, 1, 'atmtest', '2022-06-30 15:54:53', 'admin', '2022-07-06 09:27:45', '0');
INSERT INTO `sys_dict_item` VALUES ('1543867966377025537', '1209287037493080066', '交易中心', '交易中心', '交易中心', '4', '', 4, 1, 'admin', '2022-07-04 16:03:02', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1543868069766619137', '1209287037493080066', '资源管理', '资源管理', '资源管理', '5', '', 5, 1, 'admin', '2022-07-04 16:03:26', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1543fe7e5e26fb97cdafe4981bedc0c8', '4c03fca6bf1f0299c381213961566349', '单排布局', '單排佈局', '單排佈局', 'single', NULL, 2, 1, 'admin', '2022-07-12 17:43:39', 'admin', '2019-04-12 17:43:57', '0');
INSERT INTO `sys_dict_item` VALUES ('1544491699630059521', '1544491463067119618', '礼物卡', '禮物卡', 'Gift cards', '1', '', 1, 1, 'admin', '2022-07-06 09:21:31', 'admin', '2022-08-08 17:34:34', '0');
INSERT INTO `sys_dict_item` VALUES ('1544491998826541057', '1544491463067119618', '虚拟币', '虛擬幣', 'Digital Asset', '2', '', 2, 1, 'admin', '2022-07-06 09:22:42', 'admin', '2022-08-08 17:34:19', '0');
INSERT INTO `sys_dict_item` VALUES ('1544492139860013057', '1544491463067119618', 'NFT & 游戏卡', 'NFT & 遊戲卡', 'NFT & Gaming', '3', '', 3, 1, 'admin', '2022-07-06 09:23:16', 'admin', '2022-08-08 17:34:43', '0');
INSERT INTO `sys_dict_item` VALUES ('1544564938536464385', '1544564433462571010', '关闭', '關閉', 'close', '0', '', 1, 1, 'admin', '2022-07-06 14:12:33', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1544565069612658689', '1544564433462571010', '打开', '打開', 'open', '1', '', 2, 1, 'admin', '2022-07-06 14:13:04', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1544565554272874498', '1544565300735586306', '正常', '正常', 'normal', '1', '', 1, 1, 'admin', '2022-07-06 14:14:59', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1544565824729985025', '1544565300735586306', '维护中', '維護中', 'maintaining', '2', '', 1, 1, 'admin', '2022-07-06 14:16:04', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1544976916803321858', '1544976589563723778', 'mmatrix', 'mmatrix', 'mmatrix', '1', '', 1, 1, 'admin', '2022-07-07 17:29:36', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1547137463225184258', '1547137044025470977', '指定产品', '指定產品', 'specified product', '1', '', 1, 1, 'admin', '2022-07-13 16:34:50', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1547137605378535426', '1547137044025470977', '不指定产品', '不指定產品', 'no specified product', '2', '', 2, 1, 'admin', '2022-07-13 16:35:24', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1547875960704417793', '1541252766211256321', '新建', '新建', 'New', '1', '', 1, 1, 'admin', '2022-07-15 17:29:22', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1549999250740170753', '1544565300735586306', '无需同步', '無需同步', 'dispense with synchronization', '3', '', 3, 1, 'admin', '2022-07-21 14:06:34', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1550047311071866881', '1550047184152227842', '正常', '正常', 'normal', '1', '', 1, 1, 'admin', '2022-07-21 17:17:32', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1550047484535697410', '1550047184152227842', '维护中', '維護中', 'maintaining', '2', '', 2, 1, 'admin', '2022-07-21 17:18:13', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1551746772603363329', '1209287037493080066', 'ATM参数', 'ATM參數', 'ATM參數', '6', '', 6, 1, 'admin', '2022-07-26 09:50:35', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1551829282362052609', '1551828973229264898', '交易成功', '交易成功', 'trade successfully', '3', '', 1, 1, 'admin', '2022-07-26 15:18:27', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1551829504538529794', '1551828973229264898', '交易失败', '交易失敗', 'failure of transaction', '4', '', 2, 1, 'admin', '2022-07-26 15:19:20', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1552491057973628929', '1552490756419948545', 'ATM参数', 'ATM參數', 'common param', '1', '', 1, 1, 'admin', '2022-07-28 11:08:07', 'admin', '2022-07-29 17:04:47', '0');
INSERT INTO `sys_dict_item` VALUES ('1552910630271176705', '1552910011011551234', '同步指令', '同步指令', 'Synchronous Command', '1', '', 1, 1, 'admin', '2022-07-29 14:55:21', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1552911025638854657', '1552910011011551234', '维护指令', '維護指令', 'maintenance Command', '2', '', 2, 1, 'admin', '2022-07-29 14:56:55', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1552911185638969345', '1552910011011551234', '清空指令', '清空指令', 'Emptying Command', '3', '', 3, 1, 'admin', '2022-07-29 14:57:33', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1552943278249668609', '1552490756419948545', '其他参数', '其他參數', 'other param', '2', '', 2, 1, 'admin', '2022-07-29 17:05:04', 'admin', '2022-11-16 09:41:50', '0');
INSERT INTO `sys_dict_item` VALUES ('1554641891088064514', '1554641626351984642', '新建', '新建', 'New', '1', '', 1, 1, 'atmtest', '2022-08-03 09:34:45', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1554643823097733121', '1554641626351984642', '在售', '在售', 'Sale', '2', '', 1, 1, 'atmtest', '2022-08-03 09:42:26', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1554643959110623233', '1554641626351984642', '锁定', '鎖定', 'Lock', '3', '', 1, 1, 'atmtest', '2022-08-03 09:42:58', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1554644161833918466', '1554641626351984642', '已售', '已售', ' Sold', '4', '', 1, 1, 'atmtest', '2022-08-03 09:43:47', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1554730647853830145', '1554730111058415618', '新建', '新建', 'New', '1', '新建', 1, 1, 'admin', '2022-08-03 15:27:27', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1554730929648144386', '1554730111058415618', '发布', '發佈', 'Publish', '2', '发布', 2, 1, 'admin', '2022-08-03 15:28:34', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1554731058409082881', '1554730111058415618', '禁用', '禁用', 'Stop', '3', '禁用', 3, 1, 'admin', '2022-08-03 15:29:04', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1556832081114132482', '1556830920969646081', '无需库存', '無需庫存', 'No Stock', '1', '', 1, 1, 'admin', '2022-08-09 10:37:47', 'admin', '2022-08-09 10:39:08', '0');
INSERT INTO `sys_dict_item` VALUES ('1556832394135040001', '1556830920969646081', '内部库存', '內部庫存', 'In Stock', '2', '', 2, 1, 'admin', '2022-08-09 10:39:02', 'admin', '2022-08-09 10:39:36', '0');
INSERT INTO `sys_dict_item` VALUES ('1556832521520246786', '1556830920969646081', '外部库存', '外部庫存', 'Out Stock', '3', '', 3, 1, 'admin', '2022-08-09 10:39:32', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1557974544340062209', '1557974367369793538', '可用', '可用', 'Available', '1', '', 1, 1, 'admin', '2022-08-12 14:17:32', 'admin', '2022-08-12 14:17:39', '0');
INSERT INTO `sys_dict_item` VALUES ('1557974683330908161', '1557974367369793538', '不可用', '不可用', 'NotAvailable', '2', '', 1, 1, 'admin', '2022-08-12 14:18:05', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1564533722613391362', '1550047184152227842', '维护（导航栏）', '維護（導航欄）', 'maintain（navigation bar）', '4', '', 3, 1, 'admin', '2022-08-30 16:41:22', 'admin', '2022-08-31 10:05:13', '0');
INSERT INTO `sys_dict_item` VALUES ('1564796791801319426', '1544565300735586306', '维护（导航栏）', '維護（導航欄）', 'maintain（navigation bar）', '4', '', 4, 1, 'admin', '2022-08-31 10:06:42', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1570605637723496450', '1550047184152227842', '清空钱箱', '清空錢箱', 'empty cashbox', '5', '', 4, 1, 'admin', '2022-09-16 10:48:59', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1570606022945153026', '1544565300735586306', '清空钱箱', '清空錢箱', 'empty cashbox', '5', '', 5, 1, 'admin', '2022-09-16 10:50:31', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1578635099522445313', '1542402744424148993', '微信', '微信', 'WechatPay', '2', '微信支付', 2, 1, 'admin', '2022-10-08 14:35:12', 'admin', '2022-10-08 15:28:15', '0');
INSERT INTO `sys_dict_item` VALUES ('1578635262550847489', '1542402744424148993', '支付宝', '支付寶', 'AliPay', '3', '支付寶支付', 3, 1, 'admin', '2022-10-08 14:35:51', 'admin', '2022-10-08 15:28:24', '0');
INSERT INTO `sys_dict_item` VALUES ('1578648769899991041', '1542402744424148993', '银联', '銀聯', 'UnionPay', '4', '银联支付', 4, 1, 'admin', '2022-10-08 15:29:31', 'admin', '2022-10-08 15:29:43', '0');
INSERT INTO `sys_dict_item` VALUES ('1578652951084208129', '1542402744424148993', 'PayMe', 'PayMe', 'PayMe', '5', 'PayMe', 5, 1, 'admin', '2022-10-08 15:46:08', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1579012085428224001', '1579011844264132610', '交易日志', '交易日志', ' Transaction Log', '1', '交易日志', 1, 1, 'admin', '2022-10-09 15:33:12', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1579012266303389698', '1579011844264132610', '钱包日志', '錢包日志', 'Wallet Log', '2', '钱包日志', 2, 1, 'admin', '2022-10-09 15:33:56', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1579012544020840450', '1579011844264132610', '在线支付日志', '在綫支付日志', ' Online Payment Log', '3', '在线支付日志', 3, 1, 'admin', '2022-10-09 15:35:02', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1579012647842447362', '1579011844264132610', '其他日志', '其他日志', 'Other Log', '4', '其他日志', 4, 1, 'admin', '2022-10-09 15:35:27', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1579390930253770753', '1579390579903557633', '操作记录', '操作記錄', 'Operation Record', '1', '', 1, 1, 'admin', '2022-10-10 16:38:36', 'admin', '2022-10-11 09:45:19', '0');
INSERT INTO `sys_dict_item` VALUES ('1579391139918639105', '1579390579903557633', '校验错误', '校驗錯誤', 'Verification Error', '2', '', 2, 1, 'admin', '2022-10-10 16:39:26', 'admin', '2022-10-11 09:45:21', '0');
INSERT INTO `sys_dict_item` VALUES ('1579391297595109378', '1579390579903557633', '程序异常', '程序異常', 'Program Exception', '3', '', 3, 1, 'admin', '2022-10-10 16:40:04', 'admin', '2022-10-11 09:45:23', '0');
INSERT INTO `sys_dict_item` VALUES ('1586999803688865794', '1586999526583783425', '正常', '正常', '正常', '1', '', 1, 1, 'admin', '2022-10-31 16:33:33', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1586999992742924289', '1586999526583783425', '维护中', '維護中', 'maintaining', '2', '', 2, 1, 'admin', '2022-10-31 16:34:18', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1587647318251651073', '1587647194343522305', 'BTC', 'BTC', 'BTC', '1', '', 1, 1, 'admin', '2022-11-02 11:26:32', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1587647422811455489', '1587647194343522305', 'ETH', 'ETH', 'ETH', '2', '', 2, 1, 'admin', '2022-11-02 11:26:57', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1587747283388911618', '1579011844264132610', '预警日志', '預警日誌', 'warn log', '5', '', 5, 1, 'admin', '2022-11-02 18:03:46', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1587747444148195330', '1579390579903557633', 'ATM状态', 'ATM狀態', 'ATM status', '4', '', 4, 1, 'admin', '2022-11-02 18:04:24', 'admin', '2022-11-02 18:06:24', '0');
INSERT INTO `sys_dict_item` VALUES ('1587747770133696513', '1579390579903557633', '钱箱预警', '錢箱預警', 'money box warn', '5', '', 5, 1, 'admin', '2022-11-02 18:05:42', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1593165252432564225', '1587647194343522305', 'ETH&USDT', 'ETH&USDT', 'ETH&USDT', '3', '', 3, 1, 'admin', '2022-11-17 16:52:50', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1594977266213695490', '1550047184152227842', 'APP升级', 'APP升級', 'APP upgrade', '6', '', 6, 1, 'admin', '2022-11-22 16:53:08', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1594977434677915650', '1544565300735586306', 'APP升级', 'APP升級', 'APP upgrade', '6', '', 6, 1, 'admin', '2022-11-22 16:53:48', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1598251006382567425', '1598250826002329601', '成功', '成功', 'success', '2', '', 2, 1, 'admin', '2022-12-01 17:41:49', 'admin', '2022-12-01 17:42:49', '0');
INSERT INTO `sys_dict_item` VALUES ('1598251219763589121', '1598250826002329601', '失败', '失敗', 'fail', '1', '', 1, 1, 'admin', '2022-12-01 17:42:40', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('1db531bcff19649fa82a644c8a939dc4', '4c03fca6bf1f0299c381213961566349', '组合布局', '組合佈局', '組合佈局', 'combination', '', 4, 1, 'admin', '2019-05-11 16:07:08', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('222705e11ef0264d4214affff1fb4ff9', '4f69be5f507accea8d5df5f11346181a', '短信', '短信', '短信', '1', '', 1, 1, 'admin', '2023-02-28 10:50:36', 'admin', '2019-04-28 10:58:11', '0');
INSERT INTO `sys_dict_item` VALUES ('23a5bb76004ed0e39414e928c4cde155', '4e4602b3e3686f0911384e188dc7efb4', '不等于', '不等於', '不等於', '!=', '不等于', 3, 1, 'admin', '2019-04-01 16:46:15', 'admin', '2019-04-01 17:48:40', '0');
INSERT INTO `sys_dict_item` VALUES ('25847e9cb661a7c711f9998452dc09e6', '4e4602b3e3686f0911384e188dc7efb4', '小于等于', '小於等於', '小於等於', '<=', '小于等于', 6, 1, 'admin', '2019-04-01 16:44:34', 'admin', '2019-04-01 17:49:10', '0');
INSERT INTO `sys_dict_item` VALUES ('2d51376643f220afdeb6d216a8ac2c01', '68168534ff5065a152bfab275c2136f8', '有效', '有效', '有效', '1', '有效', 2, 1, 'admin', '2019-04-26 19:22:01', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('308c8aadf0c37ecdde188b97ca9833f5', '8dfe32e2d29ea9430a988b3b558bf233', '已发布', '已發佈', '已發佈', '1', '已发布', 2, 1, 'admin', '2019-04-16 17:41:24', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('333e6b2196e01ef9a5f76d74e86a6e33', '8dfe32e2d29ea9430a988b3b558bf233', '未发布', '未發佈', '未發佈', '0', '未发布', 1, 1, 'admin', '2019-04-16 17:41:12', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('337ea1e401bda7233f6258c284ce4f50', 'bd1b8bc28e65d6feefefb6f3c79f42fd', 'JSON', 'JSON', 'JSON', 'json', NULL, 1, 1, 'admin', '2019-04-12 17:26:33', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('33bc9d9f753cf7dc40e70461e50fdc54', 'a9d9942bd0eccb6e89de92d130ec4c4a', '发送失败', '發送失敗', '發送失敗', '2', NULL, 3, 1, 'admin', '2019-04-12 18:20:02', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('3fbc03d6c994ae06d083751248037c0e', '78bda155fe380b1b3f175f1e88c284c6', '已完成', '已完成', '已完成', '3', '已完成', 3, 1, 'admin', '2019-05-09 16:33:25', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('41d7aaa40c9b61756ffb1f28da5ead8e', '0b5d19e1fce4b2e6647e6b4a17760c14', '通知公告', '通知公告', '通知公告', '1', NULL, 1, 1, 'admin', '2019-04-22 18:01:57', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('41fa1e9571505d643aea87aeb83d4d76', '4e4602b3e3686f0911384e188dc7efb4', '等于', '等於', '等於', '=', '等于', 4, 1, 'admin', '2019-04-01 16:45:24', 'admin', '2019-04-01 17:49:00', '0');
INSERT INTO `sys_dict_item` VALUES ('4f05fb5376f4c61502c5105f52e4dd2b', '83bfb33147013cc81640d5fd9eda030c', '操作日志', '操作日誌', '操作日誌', '2', NULL, NULL, 1, 'admin', '2019-03-18 23:22:49', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('538fca35afe004972c5f3947c039e766', '2e02df51611a4b9632828ab7e5338f00', '显示', '顯示', '顯示', '1', '显示', 1, 1, 'admin', '2025-03-26 18:27:13', 'admin', '2019-04-26 18:39:07', '0');
INSERT INTO `sys_dict_item` VALUES ('5584c21993bde231bbde2b966f2633ac', '4e4602b3e3686f0911384e188dc7efb4', '自定义SQL表达式', '自定義SQL表達式', '自定義SQL表達式', 'USE_SQL_RULES', '自定义SQL表达式', 9, 1, 'admin', '2019-04-01 10:45:24', 'admin', '2019-04-01 17:49:27', '0');
INSERT INTO `sys_dict_item` VALUES ('58b73b344305c99b9d8db0fc056bbc0a', '72cce0989df68887546746d8f09811aa', '主表', '主表', '主表', '2', NULL, 2, 1, 'admin', '2019-03-27 10:13:36', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('5b65a88f076b32e8e69d19bbaadb52d5', '2f0320997ade5dd147c90130f7218c3e', '全体用户', '全體用戶', '全體用戶', 'ALL', NULL, NULL, 1, 'admin', '2020-10-17 21:22:43', 'admin', '2019-03-28 22:17:09', '0');
INSERT INTO `sys_dict_item` VALUES ('5d833f69296f691843ccdd0c91212b6b', '880a895c98afeca9d9ac39f29e67c13e', '修改', '修改', '修改', '3', '', 3, 1, 'admin', '2019-07-22 10:55:07', 'admin', '2019-07-22 10:55:41', '0');
INSERT INTO `sys_dict_item` VALUES ('5d84a8634c8fdfe96275385075b105c9', '3d9a351be3436fbefb1307d4cfb49bf2', '女', '女', '女', '2', NULL, 2, 1, NULL, '2019-01-04 14:56:56', NULL, '2019-01-04 17:38:12', '0');
INSERT INTO `sys_dict_item` VALUES ('66c952ae2c3701a993e7db58f3baf55e', '4e4602b3e3686f0911384e188dc7efb4', '大于', '大於', '大於', '>', '大于', 1, 1, 'admin', '2019-04-01 10:45:46', 'admin', '2019-04-01 17:48:29', '0');
INSERT INTO `sys_dict_item` VALUES ('69cacf64e244100289ddd4aa9fa3b915', 'a9d9942bd0eccb6e89de92d130ec4c4a', '未发送', '未發送', '未發送', '0', NULL, 1, 1, 'admin', '2019-04-12 18:19:23', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('6a7a9e1403a7943aba69e54ebeff9762', '4f69be5f507accea8d5df5f11346181a', '邮件', '郵件', '郵件', '2', '', 2, 1, 'admin', '2031-02-28 10:50:44', 'admin', '2019-04-28 10:59:03', '0');
INSERT INTO `sys_dict_item` VALUES ('6c682d78ddf1715baf79a1d52d2aa8c2', '72cce0989df68887546746d8f09811aa', '单表', '單表', '單表', '1', NULL, 1, 1, 'admin', '2019-03-27 10:13:29', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('6d404fd2d82311fbc87722cd302a28bc', '4e4602b3e3686f0911384e188dc7efb4', '模糊', '模糊', '模糊', 'LIKE', '模糊', 7, 1, 'admin', '2019-04-01 16:46:02', 'admin', '2019-04-01 17:49:20', '0');
INSERT INTO `sys_dict_item` VALUES ('6d4e26e78e1a09699182e08516c49fc4', '4d7fec1a7799a436d26d02325eff295e', '高', '高', '高', 'H', '高', 1, 1, 'admin', '2019-04-16 17:04:24', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('7050c1522702bac3be40e3b7d2e1dfd8', 'c5a14c75172783d72cbee6ee7f5df5d1', '柱状图', '柱狀圖', '柱狀圖', 'bar', NULL, 1, 1, 'admin', '2019-04-12 17:05:17', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('75b260d7db45a39fc7f21badeabdb0ed', 'c36169beb12de8a71c8683ee7c28a503', '不启用', '不啟用', '不啟用', '0', NULL, NULL, 1, 'admin', '2019-03-18 23:29:41', 'admin', '2019-03-18 23:29:54', '0');
INSERT INTO `sys_dict_item` VALUES ('7688469db4a3eba61e6e35578dc7c2e5', 'c36169beb12de8a71c8683ee7c28a503', '启用', '啟用', '啟用', '1', NULL, NULL, 1, 'admin', '2019-03-18 23:29:28', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('78ea6cadac457967a4b1c4eb7aaa418c', 'fc6cd58fde2e8481db10d3a1e68ce70c', '正常', '正常', '正常', '1', NULL, NULL, 1, 'admin', '2019-03-18 23:30:28', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('81fb2bb0e838dc68b43f96cc309f8257', 'fc6cd58fde2e8481db10d3a1e68ce70c', '冻结', '凍結', '凍結', '2', NULL, NULL, 1, 'admin', '2019-03-18 23:30:37', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('83250269359855501ec4e9c0b7e21596', '4274efc2292239b6f000b153f50823ff', '可见/可访问(授权后可见/可访问)', '可見/可訪問(授權後可見/可訪問)', '可見/可訪問(授權後可見/可訪問)', '1', '', 1, 1, 'admin', '2019-05-10 17:54:51', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('84778d7e928bc843ad4756db1322301f', '4e4602b3e3686f0911384e188dc7efb4', '大于等于', '大於等於', '大於等於', '>=', '大于等于', 5, 1, 'admin', '2019-04-01 10:46:02', 'admin', '2019-04-01 17:49:05', '0');
INSERT INTO `sys_dict_item` VALUES ('848d4da35ebd93782029c57b103e5b36', 'c5a14c75172783d72cbee6ee7f5df5d1', '饼图', '餅圖', '餅圖', 'pie', NULL, 3, 1, 'admin', '2019-04-12 17:05:49', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('84dfc178dd61b95a72900fcdd624c471', '78bda155fe380b1b3f175f1e88c284c6', '处理中', '處理中', '處理中', '2', '处理中', 2, 1, 'admin', '2019-05-09 16:33:01', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('8c618902365ca681ebbbe1e28f11a548', '4c753b5293304e7a445fd2741b46529d', '启用', '啟用', '啟用', '1', '', 0, 1, 'admin', '2020-07-18 23:19:27', 'admin', '2019-05-17 14:51:18', '0');
INSERT INTO `sys_dict_item` VALUES ('8cdf08045056671efd10677b8456c999', '4274efc2292239b6f000b153f50823ff', '可编辑(未授权时禁用)', '可編輯(未授權時禁用)', '可編輯(未授權時禁用)', '2', '', 2, 1, 'admin', '2019-05-10 17:55:38', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('8ff48e657a7c5090d4f2a59b37d1b878', '4d7fec1a7799a436d26d02325eff295e', '中', '中', '中', 'M', '中', 2, 1, 'admin', '2019-04-16 17:04:40', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('948923658baa330319e59b2213cda97c', '880a895c98afeca9d9ac39f29e67c13e', '添加', '添加', '添加', '2', '', 2, 1, 'admin', '2019-07-22 10:54:59', 'admin', '2019-07-22 10:55:36', '0');
INSERT INTO `sys_dict_item` VALUES ('9a96c4a4e4c5c9b4e4d0cbf6eb3243cc', '4c753b5293304e7a445fd2741b46529d', '不启用', '不啟用', '不啟用', '0', NULL, 1, 1, 'admin', '2019-03-18 23:19:53', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('a1e7d1ca507cff4a480c8caba7c1339e', '880a895c98afeca9d9ac39f29e67c13e', '导出', '導出', '導出', '6', '', 6, 1, 'admin', '2019-07-22 12:06:50', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('a2be752dd4ec980afaec1efd1fb589af', '8dfe32e2d29ea9430a988b3b558bf233', '已撤销', '已撤銷', '已撤銷', '2', '已撤销', 3, 1, 'admin', '2019-04-16 17:41:39', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('adcf2a1fe93bb99a84833043f475fe0b', '4e4602b3e3686f0911384e188dc7efb4', '包含', '包含', '包含', 'IN', '包含', 8, 1, 'admin', '2019-04-01 16:45:47', 'admin', '2019-04-01 17:49:24', '0');
INSERT INTO `sys_dict_item` VALUES ('b029a41a851465332ee4ee69dcf0a4c2', '0b5d19e1fce4b2e6647e6b4a17760c14', '系统消息', '系統消息', '系統消息', '2', NULL, 1, 1, 'admin', '2019-02-22 18:02:08', 'admin', '2019-04-22 18:02:13', '0');
INSERT INTO `sys_dict_item` VALUES ('b2a8b4bb2c8e66c2c4b1bb086337f393', '3486f32803bb953e7155dab3513dc68b', '正常', '正常', '正常', '0', NULL, NULL, 1, 'admin', '2022-10-18 21:46:48', 'admin', '2019-03-28 22:22:20', '0');
INSERT INTO `sys_dict_item` VALUES ('b5f3bd5f66bb9a83fecd89228c0d93d1', '68168534ff5065a152bfab275c2136f8', '无效', '無效', '無效', '0', '无效', 1, 1, 'admin', '2019-04-26 19:21:49', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('b9fbe2a3602d4a27b45c100ac5328484', '78bda155fe380b1b3f175f1e88c284c6', '待提交', '待提交', '待提交', '1', '待提交', 1, 1, 'admin', '2019-05-09 16:32:35', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('ba27737829c6e0e582e334832703d75e', '236e8a4baff0db8c62c00dd95632834f', '同步', '同步', '同步', '1', '同步', 1, 1, 'admin', '2019-05-15 15:28:15', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('bcec04526b04307e24a005d6dcd27fd6', '880a895c98afeca9d9ac39f29e67c13e', '导入', '導入', '導入', '5', '', 5, 1, 'admin', '2019-07-22 12:06:41', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('c53da022b9912e0aed691bbec3c78473', '880a895c98afeca9d9ac39f29e67c13e', '查询', '查詢', '查詢', '1', '', 1, 1, 'admin', '2019-07-22 10:54:51', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('cbfcc5b88fc3a90975df23ffc8cbe29c', 'c5a14c75172783d72cbee6ee7f5df5d1', '曲线图', '曲線圖', '曲線圖', 'line', NULL, 2, 1, 'admin', '2019-05-12 17:05:30', 'admin', '2019-04-12 17:06:06', '0');
INSERT INTO `sys_dict_item` VALUES ('d217592908ea3e00ff986ce97f24fb98', 'c5a14c75172783d72cbee6ee7f5df5d1', '数据列表', '數據列表', '數據列表', 'table', NULL, 4, 1, 'admin', '2019-04-12 17:05:56', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('df168368dcef46cade2aadd80100d8aa', '3d9a351be3436fbefb1307d4cfb49bf2', '男', '男', '男', '1', NULL, 1, 1, NULL, '2027-08-04 14:56:49', 'admin', '2019-03-23 22:44:44', '0');
INSERT INTO `sys_dict_item` VALUES ('e6329e3a66a003819e2eb830b0ca2ea0', '4e4602b3e3686f0911384e188dc7efb4', '小于', '小於', '小於', '<', '小于', 2, 1, 'admin', '2019-04-01 16:44:15', 'admin', '2019-04-01 17:48:34', '0');
INSERT INTO `sys_dict_item` VALUES ('e94eb7af89f1dbfa0d823580a7a6e66a', '236e8a4baff0db8c62c00dd95632834f', '不同步', '不同步', '不同步', '0', '不同步', 2, 1, 'admin', '2019-05-15 15:28:28', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('f16c5706f3ae05c57a53850c64ce7c45', 'a9d9942bd0eccb6e89de92d130ec4c4a', '发送成功', '發送成功', '發送成功', '1', NULL, 2, 1, 'admin', '2019-04-12 18:19:43', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('f37f90c496ec9841c4c326b065e00bb2', '83bfb33147013cc81640d5fd9eda030c', '登录日志', '登錄日誌', '登錄日誌', '1', NULL, NULL, 1, 'admin', '2019-03-18 23:22:37', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('f753aff60ff3931c0ecb4812d8b5e643', '4c03fca6bf1f0299c381213961566349', '双排布局', '雙排佈局', '雙排佈局', 'double', NULL, 3, 1, 'admin', '2019-04-12 17:43:51', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('f80a8f6838215753b05e1a5ba3346d22', '880a895c98afeca9d9ac39f29e67c13e', '删除', '刪除', '刪除', '4', '', 4, 1, 'admin', '2019-07-22 10:55:14', 'admin', '2019-07-22 10:55:30', '0');
INSERT INTO `sys_dict_item` VALUES ('fcec03570f68a175e1964808dc3f1c91', '4c03fca6bf1f0299c381213961566349', 'Tab风格', 'Tab風格', 'Tab風格', 'tab', NULL, 1, 1, 'admin', '2019-04-12 17:43:31', NULL, NULL, '0');
INSERT INTO `sys_dict_item` VALUES ('fe50b23ae5e68434def76f67cef35d2d', '78bda155fe380b1b3f175f1e88c284c6', '已作废', '已作廢', '已作廢', '4', '已作废', 4, 1, 'admin', '2021-09-09 16:33:43', 'admin', '2019-05-09 16:34:40', '0');

-- ----------------------------
-- Table structure for sys_fill_rule
-- ----------------------------
DROP TABLE IF EXISTS `sys_fill_rule`;
CREATE TABLE `sys_fill_rule`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `rule_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规则名称',
  `rule_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规则Code',
  `rule_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规则实现类',
  `rule_params` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规则参数',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_sys_fill_rule_code`(`rule_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_fill_rule
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `log_type` int(2) NULL DEFAULT NULL COMMENT '日志类型（1登录日志，2操作日志）',
  `log_content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志内容',
  `operate_type` int(2) NULL DEFAULT NULL COMMENT '操作类型',
  `userid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作用户账号',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作用户名称',
  `ip` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP',
  `method` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求java方法',
  `request_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求路径',
  `request_param` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '请求参数',
  `request_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求类型',
  `cost_time` bigint(20) NULL DEFAULT NULL COMMENT '耗时',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_table_userid`(`userid`) USING BTREE,
  INDEX `index_operate_type`(`operate_type`) USING BTREE,
  INDEX `index_log_ype`(`log_type`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `parent_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单标题',
  `nametw` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单标题繁体',
  `nameen` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单标题英文',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路径',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件',
  `component_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件名字',
  `redirect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '一级菜单跳转地址',
  `menu_type` int(11) NULL DEFAULT NULL COMMENT '菜单类型(0:一级菜单; 1:子菜单:2:按钮权限)',
  `perms` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单权限编码',
  `perms_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '权限策略1显示2禁用',
  `sort_no` double(8, 2) NULL DEFAULT NULL COMMENT '菜单排序',
  `always_show` tinyint(1) NULL DEFAULT NULL COMMENT '聚合子路由: 1是0否',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `is_route` tinyint(1) NULL DEFAULT 1 COMMENT '是否路由菜单: 0:不是  1:是（默认值1）',
  `is_leaf` tinyint(1) NULL DEFAULT NULL COMMENT '是否叶子节点:    1:是   0:不是',
  `keep_alive` tinyint(1) NULL DEFAULT NULL COMMENT '是否缓存该页面:    1:是   0:不是',
  `hidden` int(2) NULL DEFAULT 0 COMMENT '是否隐藏路由: 0否,1是',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(1) NULL DEFAULT 0 COMMENT '删除状态 0正常 1已删除',
  `rule_flag` int(3) NULL DEFAULT 0 COMMENT '是否添加数据权限1是0否',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '按钮权限状态(0无效1有效)',
  `internal_or_external` tinyint(1) NULL DEFAULT NULL COMMENT '外链菜单打开方式 0/内部打开 1/外部打开',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_prem_pid`(`parent_id`) USING BTREE,
  INDEX `index_prem_is_route`(`is_route`) USING BTREE,
  INDEX `index_prem_is_leaf`(`is_leaf`) USING BTREE,
  INDEX `index_prem_sort_no`(`sort_no`) USING BTREE,
  INDEX `index_prem_del_flag`(`del_flag`) USING BTREE,
  INDEX `index_menu_type`(`menu_type`) USING BTREE,
  INDEX `index_menu_hidden`(`hidden`) USING BTREE,
  INDEX `index_menu_status`(`status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('024f1fd1283dc632458976463d8984e1', '700b7f95165c46cc7a78bf227aa8fed3', 'Tomcat信息', 'Tomcat信息', 'Tomcat Information', '/monitor/TomcatInfo', 'modules/monitor/TomcatInfo', NULL, NULL, 1, NULL, NULL, 4.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2019-04-02 09:44:29', 'admin', '2022-06-09 10:36:41', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('08e6b9dc3c04489c8e1ff2ce6f105aa4', '', '系统监控', '系統監控', 'System Monitoring', '/dashboard3', 'layouts/RouteView', NULL, NULL, 0, NULL, NULL, 106.00, 0, 'dashboard', 1, 0, 1, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2022-06-09 10:35:08', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('1202490201665445889', '1568099740965437442', '菜单授权', '菜單授權', 'Menu Authorization', '/isystem/menuAuthorizationList', 'system/MenuAuthorizationList', NULL, NULL, 1, NULL, '1', 10.00, 0, NULL, 1, 1, 1, 0, NULL, 'admin', '2019-12-05 15:29:40', 'admin', '2022-09-09 12:59:44', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1205382161782677505', '1568100525069598721', '参数配置', '參數配置', 'Parameter Configuration', '/isystem/SysSysconfigvalueList', 'system/SysSysconfigvalueList', NULL, NULL, 1, NULL, '1', 11.00, 0, NULL, 1, 1, 1, 0, NULL, 'admin', '2019-12-13 15:01:17', 'admin', '2022-09-09 12:59:54', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1218756887330787329', '1568100525069598721', '刷新页', '刷新頁', 'Refresh', '/refresh', 'Refresh', NULL, NULL, 1, NULL, '1', 1.00, 0, NULL, 1, 1, 1, 0, NULL, 'admin', '2020-01-19 12:47:40', 'admin', '2022-09-09 12:57:29', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1238376839743078401', '', '移动管理', '移動管理', 'Mobile manager', '/esom', 'layouts/RouteView', NULL, NULL, 0, NULL, '1', 3.00, 0, 'codepen', 1, 0, 1, 0, NULL, 'admin', '2020-03-13 16:10:21', 'admin', '2022-06-09 10:12:23', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1238377045649850369', '1245237838706941953', '应用管理', '應用管理', 'Application Manage', '/emm/tEmAppList', 'emm/TEmAppList', NULL, NULL, 1, NULL, '1', 1.00, 0, NULL, 1, 1, 1, 0, NULL, 'admin', '2020-03-13 16:11:10', 'admin', '2022-06-09 10:13:59', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1242293957467336705', '1245519248885874689', '设备管理', '設備管理', 'Devices Manage', '/emm/mobileDevice', 'emm/MobileDeviceList', NULL, NULL, 1, NULL, '1', 1.00, 0, NULL, 1, 1, 1, 0, NULL, 'admin', '2020-03-24 11:35:35', 'admin', '2022-06-09 10:17:14', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1242655908936572930', '1245519248885874689', '设备审核', '設備審核', 'Equipment Audit', '/emm/reviewMobileDevice', 'emm/ReviewMobileDevice', NULL, NULL, 1, NULL, '1', 1.00, 0, NULL, 1, 1, 1, 0, NULL, 'admin', '2020-03-25 11:33:51', 'admin', '2022-06-09 10:17:40', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1242749541727641602', '1245519248885874689', '审核日志', '審核日誌', 'Audit Logs', '/emm/reviewMobileDeviceList', 'emm/ReviewMobileDeviceList', NULL, NULL, 1, NULL, '1', 1.00, 0, NULL, 1, 1, 1, 0, NULL, 'admin', '2020-03-25 17:45:55', 'admin', '2022-06-09 10:17:56', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1245237838706941953', '1238376839743078401', '移动应用', '移動應用', 'Mobile Application', '/emma', 'layouts/RouteView', NULL, NULL, 1, NULL, '1', 1.00, 0, 'windows', 1, 0, 1, 0, NULL, 'admin', '2020-04-01 14:33:31', 'admin', '2022-06-09 10:13:32', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1245519248885874689', '1238376839743078401', '移动设备', '移動設備', 'Mobile Devices', '/emmp', 'layouts/RouteView', NULL, NULL, 1, NULL, '1', 1.00, 0, 'skype', 1, 0, 1, 0, NULL, 'admin', '2020-04-02 09:11:44', 'admin', '2022-06-09 10:16:49', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1245595619741204481', '1245237838706941953', '应用列表', '應用列表', 'Application List', '/emm/appCardList', 'emm/AppCardList', NULL, NULL, 1, NULL, '1', 1.00, 0, NULL, 1, 1, 1, 0, NULL, 'admin', '2020-04-02 14:15:12', 'admin', '2022-06-09 10:14:20', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1247409352122843137', '1245237838706941953', '版本授权', '版本授權', 'Version Authorized', '/emm/packageAuthorization', 'emm/PackageAuthorization', NULL, NULL, 1, NULL, '1', 1.00, 0, NULL, 1, 1, 1, 0, NULL, 'admin', '2020-04-07 14:22:20', 'admin', '2022-06-09 10:15:05', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1250665255173427201', '1245237838706941953', '应用授权', '應用授權', 'Application Authorized', '/emm/appPermission', 'emm/AppPermission', NULL, NULL, 1, NULL, '1', 1.00, 0, NULL, 1, 1, 1, 0, NULL, 'admin', '2020-04-16 14:00:08', 'admin', '2022-06-09 10:15:29', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1541714993840230401', '', '交易中心', '交易中心', 'Trading center', '/trading', 'layouts/RouteView', NULL, NULL, 0, NULL, '1', 1.00, 0, 'bank', 1, 0, 1, 0, NULL, 'admin', '2022-06-28 17:27:53', 'admin', '2022-11-16 10:01:37', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1541715684793094146', '', '资源管理', '資源管理', 'Resource Management', '/resource', 'layouts/RouteView', NULL, NULL, 0, NULL, '1', 2.00, 0, 'cluster', 1, 0, 1, 0, NULL, 'admin', '2022-06-28 17:30:38', 'admin', '2022-11-18 10:41:01', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1541716518507483138', '1541714993840230401', '交易管理', '交易管理', 'Transaction Management', '/transaction', 'layouts/RouteView', NULL, NULL, 1, NULL, '1', 1.00, 0, 'file-protect', 1, 0, 1, 0, NULL, 'admin', '2022-06-28 17:33:56', 'admin', '2022-11-16 10:01:55', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1541960327533191169', '1541982656780947458', '产品信息', '產品信息', 'Product Info', '/resource/AtmProductsList', 'resource/AtmProductsList', NULL, NULL, 1, NULL, '1', 2.00, 0, NULL, 1, 0, 1, 0, NULL, 'admin', '2022-06-29 09:42:45', 'admin', '2022-07-15 09:41:00', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1541981529586913281', '1541716518507483138', '交易查询', '交易查詢', 'transaction query', '/transactions/AtmTransactionsList', 'transactions/AtmTransactionsList', NULL, NULL, 1, NULL, '1', 1.00, 0, NULL, 1, 0, 1, 0, NULL, 'admin', '2022-06-29 11:07:00', 'admin', '2022-06-29 11:53:56', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1541982656780947458', '1541715684793094146', '产品管理', '產品管理', 'product management', '/product', 'layouts/RouteView', NULL, NULL, 1, NULL, '1', 1.00, 0, 'shop', 1, 0, 0, 0, NULL, 'admin', '2022-06-29 11:11:29', 'admin', '2022-06-29 11:43:11', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1541983677699706882', '1541715684793094146', '广告管理', '廣告管理', 'advertising management', '/advertising', 'layouts/RouteView', NULL, NULL, 1, NULL, '1', 2.00, 0, 'flag', 1, 0, 0, 0, NULL, 'admin', '2022-06-29 11:15:32', 'admin', '2022-07-06 11:27:51', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1541984093359427586', '1541714993840230401', '异常交易', '異常交易', 'unusual transaction', '/unusual', 'layouts/RouteView', NULL, NULL, 1, NULL, '1', 2.00, 0, 'file-unknown', 1, 0, 0, 0, NULL, 'admin', '2022-06-29 11:17:11', 'admin', '2022-06-29 11:41:22', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1541984572600602626', '1541714993840230401', '交易统计', '交易統計', 'transaction statistics', '/statistics', 'layouts/RouteView', NULL, NULL, 1, NULL, '1', 4.00, 0, 'bar-chart', 1, 0, 0, 0, NULL, 'admin', '2022-06-29 11:19:05', 'admin', '2022-07-14 17:56:27', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1541986405746331649', '1541984093359427586', '交易处理', '交易處理', 'transaction processing', '/transactions/TransactionsDisposeList', 'transactions/TransactionsDisposeList', NULL, NULL, 1, NULL, '1', 1.00, 0, NULL, 1, 0, 1, 0, NULL, 'admin', '2022-06-29 11:26:23', 'admin', '2022-07-25 16:22:13', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1541986872568172545', '1541984093359427586', '处理结果', '處理結果', 'processing result', '/transactions/TransactionsDisposeResultList', 'transactions/TransactionsDisposeResultList', NULL, NULL, 1, NULL, '1', 2.00, 0, NULL, 1, 0, 1, 0, NULL, 'admin', '2022-06-29 11:28:14', 'admin', '2022-07-28 15:48:34', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1544490631722512385', '1541982656780947458', '产品类型', '產品類型', 'Product Type Maintain', '/resource/AtmProductDetailtypeList', 'resource/AtmProductDetailtypeList', NULL, NULL, 1, NULL, '1', 1.00, 0, NULL, 1, 0, 1, 0, NULL, 'admin', '2022-07-06 09:17:16', 'admin', '2022-07-15 09:37:15', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1544523429292359681', '1238376839743078401', 'ATM维护', 'ATM維護', 'ATM Management', '/equipment', 'layouts/RouteView', NULL, NULL, 1, NULL, '1', 3.00, 0, 'desktop', 1, 0, 1, 0, NULL, 'admin', '2022-07-06 11:27:36', 'admin', '2022-11-18 10:40:54', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1544524339766706177', '1544523429292359681', 'ATM指令', 'ATM指令', 'Equipment Order', '/resource/AtmDeviceConfigList', 'resource/AtmDeviceConfigList', NULL, NULL, 1, NULL, '1', 4.00, 0, NULL, 1, 0, 1, 0, NULL, 'admin', '2022-07-06 11:31:13', 'admin', '2022-07-27 17:54:51', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1544524780617416706', '1544523429292359681', 'ATM产品', 'ATM產品', 'Equipment Product', '/resource/AtmDeviceProductsList', 'resource/AtmDeviceProductsList', NULL, NULL, 1, NULL, '1', 2.00, 0, NULL, 1, 0, 1, 0, NULL, 'admin', '2022-07-06 11:32:58', 'admin', '2022-07-15 09:50:27', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1544525169764941826', '1544523429292359681', 'ATM状态', 'ATM狀態', 'Equipment Status', '/resource/AtmDeviceStatusList', 'resource/AtmDeviceStatusList', NULL, NULL, 1, NULL, '1', 1.00, 0, NULL, 1, 1, 1, 0, NULL, 'admin', '2022-07-06 11:34:31', 'admin', '2022-07-15 09:50:00', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1547126546232102913', '1541982656780947458', '優惠码', '優惠碼', 'promotion management', '/resource/AtmPromotionCodeList', 'resource/AtmPromotionCodeList', NULL, NULL, 1, NULL, '1', 3.00, 0, NULL, 1, 0, 1, 0, NULL, 'admin', '2022-07-13 15:51:27', 'admin', '2022-07-15 09:39:04', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1547520284091559937', '1544523429292359681', 'ATM日誌', 'ATM日誌', 'ATM log', '/transactions/AtmActionLogList', 'transactions/AtmActionLogList', NULL, NULL, 1, NULL, '1', 6.00, 0, NULL, 1, 0, 1, 0, NULL, 'admin', '2022-07-14 17:56:02', 'admin', '2022-07-27 17:42:11', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1547761038531772418', '1544523429292359681', 'ATM廣告', 'ATM廣告', 'ATM Advertisement', '/resource/AtmDeviceAdvertisementsList', 'resource/AtmDeviceAdvertisementsList', NULL, NULL, 1, NULL, '1', 3.00, 0, NULL, 1, 0, 1, 0, NULL, 'admin', '2022-07-15 09:52:42', 'admin', '2022-08-22 09:27:56', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1552227778936283137', '1544523429292359681', 'ATM参数', 'ATM參數', 'ATM param', '/resource/AtmDeviceParamList', 'resource/AtmDeviceParamList', NULL, NULL, 1, NULL, '1', 5.00, 0, NULL, 1, 0, 1, 0, NULL, 'admin', '2022-07-27 17:41:56', 'admin', '2022-07-27 17:42:24', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1554379452425662465', '1541982656780947458', '产品库存', '產品庫存', 'Product Stock', '/resource/AtmProductStockList', 'resource/AtmProductStockList', NULL, NULL, 1, NULL, '1', 4.00, 0, NULL, 1, 0, 1, 0, NULL, 'admin', '2022-08-02 16:11:55', 'admin', '2022-10-08 14:57:24', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1554657411501756417', '1541983677699706882', '广告信息', '廣告信息', 'Advertisements Info', '/resource/AtmAdvertisementsList', 'resource/AtmAdvertisementsList', NULL, NULL, 1, NULL, '1', 1.00, 0, NULL, 1, 0, 1, 0, NULL, 'admin', '2022-08-03 10:36:26', 'admin', '2022-08-03 10:37:10', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562277568197750785', '1544490631722512385', '新增产品类型', '新增產品類型', 'Add Product Type', NULL, NULL, NULL, NULL, 2, 'product:type:add', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 11:16:13', 'admin', '2022-08-24 11:27:19', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562281559732318209', '1544490631722512385', '编辑产品类型', '編輯產品類型', 'Edit Product Type', NULL, NULL, NULL, NULL, 2, 'product:type:edit', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 11:32:04', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562281834643779585', '1544490631722512385', '删除产品类型', '刪除產品類型', 'Delete Product Type', NULL, NULL, NULL, NULL, 2, 'product:type:delete', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 11:33:10', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562292822789124097', '1554379452425662465', '发售', '發售', 'Sale', NULL, NULL, NULL, NULL, 2, 'product:stock:sale', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 12:16:50', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562293218106470401', '1554379452425662465', '按批发售', '按批發售', 'Sold By Wholesale', NULL, NULL, NULL, NULL, 2, 'product:stock:whole:sale', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 12:18:24', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562293374340100097', '1554379452425662465', '删除', '刪除', 'Delete', NULL, NULL, NULL, NULL, 2, 'product:stock:delete', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 12:19:01', 'admin', '2022-08-24 12:19:36', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562293769032495105', '1554379452425662465', '导出', '導出', 'export', NULL, NULL, NULL, NULL, 2, 'product:stock:export', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 12:20:35', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562294072154845185', '1554379452425662465', '模板导出', '模板導出', 'Template Export', NULL, NULL, NULL, NULL, 2, 'product:stock:template:export', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 12:21:47', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562294293853171714', '1554379452425662465', '导入', '導入', 'import', NULL, NULL, NULL, NULL, 2, 'product:stock:import', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 12:22:40', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562315750079524865', '1541960327533191169', '新增产品', '新增產品', 'Add Product', NULL, NULL, NULL, NULL, 2, 'product:add', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 13:47:56', 'admin', '2022-08-24 13:51:10', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562315934658260994', '1541960327533191169', '编辑产品', '編輯產品', 'Edit Product', NULL, NULL, NULL, NULL, 2, 'product:edit', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 13:48:40', 'admin', '2022-08-24 13:51:23', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562316163222663169', '1541960327533191169', '删除产品', '刪除產品', 'Delete Product', NULL, NULL, NULL, NULL, 2, 'product:delete', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 13:49:34', 'admin', '2022-08-24 13:51:47', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562316480882470913', '1541960327533191169', '在售', '在售', 'Sale', NULL, NULL, NULL, NULL, 2, 'product:sale', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 13:50:50', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562317330237419521', '1541960327533191169', '停售', '停售', 'No Sale', NULL, NULL, NULL, NULL, 2, 'product:nosale', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 13:54:13', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562317594424045569', '1541960327533191169', '历史记录', '歷史記錄', 'History Record', NULL, NULL, NULL, NULL, 2, 'product:hisrecord', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 13:55:16', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562317789991858177', '1541960327533191169', '外部产品', '外部產品', 'Third Product', '/resource/AtmThirdProductsModal', NULL, NULL, NULL, 2, 'product:third', '1', 1.00, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2022-08-24 13:56:02', 'admin', '2022-08-24 15:23:08', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562323814581071873', '1547126546232102913', '新增优惠码', '新增優惠碼', 'Add Promotion Code', NULL, NULL, NULL, NULL, 2, 'promotion:code:add', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 14:19:59', 'admin', '2022-08-24 14:21:38', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562324041157373954', '1547126546232102913', '编辑优惠码', '編輯優惠碼', 'Edit Promotion Code', NULL, NULL, NULL, NULL, 2, 'promotion:code:edit', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 14:20:53', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562324496495210497', '1547126546232102913', '删除优惠码', '刪除優惠碼', 'Delete Promotion Code', NULL, NULL, NULL, NULL, 2, 'promotion:code:delete', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 14:22:41', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562324949224189953', '1547126546232102913', '二维码', '二維碼', 'QR Code', NULL, NULL, NULL, NULL, 2, 'Promotion:code:qrcode', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 14:24:29', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562325227876970498', '1547126546232102913', '关联产品', '關聯產品', 'Relevant Product', NULL, NULL, NULL, NULL, 2, 'Promotion:code:relevant:product', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 14:25:36', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562330226434568193', '1554657411501756417', '新增广告', '新增廣告', 'Add Advertisement', NULL, NULL, NULL, NULL, 2, 'advertisement:add', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 14:45:27', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562330402054270977', '1554657411501756417', '删除广告', '刪除廣告', 'Delete Advertisement', NULL, NULL, NULL, NULL, 2, 'advertisement:delete', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 14:46:09', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562330680778354690', '1554657411501756417', '编辑广告', '編輯廣告', 'Edit Advertisement', NULL, NULL, NULL, NULL, 2, 'advertisement:edit', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 14:47:16', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562330991366565890', '1554657411501756417', '发布广告', '發佈廣告', 'Publish Advertisement', NULL, NULL, NULL, NULL, 2, 'advertisement:publish ', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 14:48:30', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562331226243395585', '1554657411501756417', '停用广告', '停用廣告', 'Stop Advertisement', NULL, NULL, NULL, NULL, 2, 'advertisement:stop', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 14:49:26', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562332561168723969', '1544524780617416706', '关联产品', '關聯產品', 'Relevant Product', NULL, NULL, NULL, NULL, 2, 'product:relevant', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 14:54:44', 'admin', '2022-08-24 14:57:18', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562333050874687489', '1547761038531772418', '关联广告', '關聯廣告', 'Relevant Advertisement', NULL, NULL, NULL, NULL, 2, 'advertisement:relevant', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 14:56:41', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562333634210099201', '1544524339766706177', '同步指令', '同步指令', 'Synchronous Order', NULL, NULL, NULL, NULL, 2, 'order:synchronous', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 14:59:00', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562333896765140994', '1544524339766706177', '控制指令', '控制指令', 'Control Order', NULL, NULL, NULL, NULL, 2, 'order:control', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 15:00:02', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562334193969328130', '1544524339766706177', '指令记录', '指令記錄', 'Order Log', NULL, NULL, NULL, NULL, 2, 'order:log', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 15:01:13', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562336321848799234', '1541981529586913281', '进钱明细', '進錢明細', 'enter money detail', NULL, NULL, NULL, NULL, 2, 'transaction:query:detail', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 15:09:41', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562336654729736194', '1541981529586913281', '交易查询导出', '交易查詢導出', 'Transaction Query Export', NULL, NULL, NULL, NULL, 2, 'transaction:query:export', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 15:11:00', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562337323750584322', '1541986405746331649', '交易处理', '交易處理', 'Transaction Dispose', NULL, NULL, NULL, NULL, 2, 'transaction:dispose', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 15:13:39', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562340088220545026', '1562317789991858177', '同步外部产品', '同步外部產品', 'Synchronous Third Product', NULL, NULL, NULL, NULL, 2, 'third:product:synchronous', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 15:24:39', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562354132046376961', '1541981529586913281', '查询', '查詢', 'query', NULL, NULL, NULL, NULL, 2, 'query', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 16:20:27', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562354797804056578', '1541986872568172545', '查看处理结果', '查看處理結果', 'Check Dispose Result', NULL, NULL, NULL, NULL, 2, 'transaction:dispose:result', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 16:23:06', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562354905811578881', '1541986405746331649', '查询', '查詢', 'query', NULL, NULL, NULL, NULL, 2, 'query', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 16:23:31', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562355024187420674', '1541986872568172545', '查询', '查詢', 'query', NULL, NULL, NULL, NULL, 2, 'query', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 16:24:00', 'admin', '2022-08-24 16:24:18', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562355209974116353', '1544490631722512385', '查询', '查詢', 'query', NULL, NULL, NULL, NULL, 2, 'query', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 16:24:44', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562355300910821378', '1554379452425662465', '查询', '查詢', 'query', NULL, NULL, NULL, NULL, 2, 'query', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 16:25:06', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562355444460875778', '1541960327533191169', '查询', '查詢', 'query', NULL, NULL, NULL, NULL, 2, 'query', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 16:25:40', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562355579995615233', '1562317789991858177', '查询', '查詢', 'query', '', NULL, NULL, NULL, 2, 'query', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 16:26:12', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562355676229726210', '1547126546232102913', '查询', '查詢', 'query', NULL, NULL, NULL, NULL, 2, 'query', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 16:26:35', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562355883227017217', '1554657411501756417', '查询', '查詢', 'query', NULL, NULL, NULL, NULL, 2, 'query', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 16:27:24', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562355985496731650', '1544524780617416706', '查询', '查詢', 'query', NULL, NULL, NULL, NULL, 2, 'query', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 16:27:49', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562356064047656962', '1547761038531772418', '查询', '查詢', 'query', NULL, NULL, NULL, NULL, 2, 'query', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 16:28:07', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562356151305957378', '1544524339766706177', '查询', '查詢', 'query', NULL, NULL, NULL, NULL, 2, 'query', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 16:28:28', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562359424725348354', '1552227778936283137', '新增参数', '新增參數', 'Add Param', NULL, NULL, NULL, NULL, 2, 'param:add', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 16:41:29', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562359609601880066', '1552227778936283137', '初始化参数', '初始化參數', 'Initialize Param', NULL, NULL, NULL, NULL, 2, 'param:initialize', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 16:42:13', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562359784898621442', '1552227778936283137', '查询', '查詢', 'query', NULL, NULL, NULL, NULL, 2, 'query', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 16:42:55', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562359933309874178', '1547520284091559937', '日志详情', '日志詳情', 'Log Details', NULL, NULL, NULL, NULL, 2, 'log:detail', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 16:43:30', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1562360092966055937', '1547520284091559937', '查询', '查詢', 'query', NULL, NULL, NULL, NULL, 2, 'query', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-24 16:44:08', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1564183320634163202', '1541986872568172545', '再处理', '再處理', 'repeat dispose', NULL, NULL, NULL, NULL, 2, 'transaction:redispose', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-29 17:28:59', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1564183557696225282', '1541986872568172545', '处理历史', '處理歷史', 'processes history', NULL, NULL, NULL, NULL, 2, 'log:processes:history', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2022-08-29 17:29:56', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1568099740965437442', 'd7d6e2e4e2934f2c9385a623fd98c6f3', '组织权限', '組織權限', 'Access Permission', '/systemrights', 'layouts/RouteView', NULL, NULL, 1, NULL, '1', 1.00, 0, 'team', 1, 0, 1, 0, NULL, 'admin', '2022-09-09 12:51:27', 'admin', '2022-11-17 15:21:26', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1568100525069598721', 'd7d6e2e4e2934f2c9385a623fd98c6f3', '系统维护', '系統維護', 'System Maintenance', '/systemmgr', 'layouts/RouteView', NULL, NULL, 1, NULL, '1', 2.00, 0, 'setting', 1, 0, 1, 0, NULL, 'admin', '2022-09-09 12:54:34', 'admin', '2022-11-17 15:21:44', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1570667594480623618', '1544523429292359681', 'ATM清钱记录', 'ATM清錢記錄', 'ATM box reset record', '/resource/AtmBoxResetRecordList', 'resource/AtmBoxResetRecordList', NULL, NULL, 1, NULL, '1', 6.00, 0, NULL, 1, 1, 1, 0, NULL, 'admin', '2022-09-16 14:55:11', 'admin', '2022-09-16 17:50:51', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1578639048786817026', '1541982656780947458', '产品支付方式', '產品支付方式', 'Mode Of Payment', '/resource/AtmPaymodeConfigList', 'resource/AtmPaymodeConfigList', NULL, NULL, 1, NULL, '1', 5.00, 0, NULL, 1, 1, 1, 0, NULL, 'admin', '2022-10-08 14:50:54', 'admin', '2022-10-08 14:57:34', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1579008347489665025', '1544523429292359681', 'ATM业务日志', 'ATM業務日志', 'Business Log', '/resource/AtmBusiLogList', 'resource/AtmBusiLogList', NULL, NULL, 1, NULL, '1', 8.00, 0, NULL, 1, 1, 1, 0, NULL, 'admin', '2022-10-09 15:18:21', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1587337002368110594', '1541984572600602626', '收钱日报', '收錢日報', 'daily income', '/report/CollectMoneyDayReport', 'report/CollectMoneyDayReport', NULL, NULL, 1, NULL, '1', 2.00, 0, NULL, 1, 1, 1, 0, NULL, 'admin', '2022-11-01 14:53:27', 'admin', '2022-11-17 15:38:36', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1587646674090442754', '1541716518507483138', '余额转移记录', '餘額轉移記錄', 'Balance transfer record', '/transactions/AtmWalletTransferList', 'transactions/AtmWalletTransferList', NULL, NULL, 1, NULL, '1', 2.00, 0, NULL, 1, 1, 1, 0, NULL, 'admin', '2022-11-02 11:23:59', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1589513012312358913', '1541984572600602626', '收钱月报', '收錢月報', 'monthly income', '/report/CollectMoneyMonthReport', 'report/CollectMoneyMonthReport', NULL, NULL, 1, NULL, '1', 3.00, 0, NULL, 1, 1, 1, 0, NULL, 'admin', '2022-11-07 15:00:09', 'admin', '2022-11-17 15:37:03', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1590183775042351106', '1541984572600602626', '交易统计表', '交易統計表', 'transactions statistic table', '/report/TransactionsReport', 'report/TransactionsReport', NULL, NULL, 1, NULL, '1', 1.00, 0, NULL, 1, 1, 1, 0, NULL, 'admin', '2022-11-09 11:25:31', 'admin', '2022-11-16 10:05:52', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('1a0811914300741f4e11838ff37a1d3a', '3f915b2769fc80648e92d04e84ca059d', '手机号禁用', '手機號禁用', 'Phone Number Disabled', NULL, NULL, NULL, NULL, 2, 'user:form:phone', '2', 1.00, 0, NULL, 0, 1, 0, 0, NULL, 'admin', '2019-05-11 17:19:30', 'admin', '2022-06-09 10:30:59', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('2dbbafa22cda07fa5d169d741b81fe12', '08e6b9dc3c04489c8e1ff2ce6f105aa4', '在线文档', '在線文檔', 'Online Documentation', '{{ window._CONFIG[\'domianURL\'] }}/doc.html', 'layouts/IframePageView', NULL, NULL, 1, NULL, NULL, 3.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2019-01-30 10:00:01', 'admin', '2022-06-09 10:38:53', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('3f915b2769fc80648e92d04e84ca059d', '1568099740965437442', '操作员管理', '操作員管理', 'Operator Management', '/isystem/user', 'system/UserList', NULL, NULL, 1, NULL, NULL, 1.00, 0, NULL, 1, 0, 1, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2022-09-09 12:55:31', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('45c966826eeff4c99b8f8ebfe74511fc', '1568099740965437442', '组织机构', '組織機構', 'Organization', '/isystem/depart', 'system/DepartList', NULL, NULL, 1, NULL, NULL, 1.00, 0, NULL, 1, 1, 1, 0, NULL, 'admin', '2019-01-29 18:47:40', 'admin', '2022-09-09 12:57:54', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('54dd5457a3190740005c1bfec55b1c34', '1568099740965437442', '菜单管理', '菜單管理', 'Menu Management', '/isystem/permission', 'system/PermissionList', NULL, NULL, 1, NULL, NULL, 3.00, 0, NULL, 1, 1, 1, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2022-09-09 12:58:55', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('58857ff846e61794c69208e9d3a85466', '08e6b9dc3c04489c8e1ff2ce6f105aa4', '日志管理', '日誌管理', 'Log Management', '/isystem/log', 'system/LogList', NULL, NULL, 1, NULL, NULL, 1.00, 0, '', 1, 1, 0, 0, NULL, NULL, '2018-12-26 10:11:18', 'admin', '2022-06-09 10:37:58', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('700b7f95165c46cc7a78bf227aa8fed3', '08e6b9dc3c04489c8e1ff2ce6f105aa4', '性能监控', '性能監控', 'Performance Monitoring', '/monitor', 'layouts/RouteView', NULL, NULL, 1, NULL, NULL, 0.00, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2019-04-02 11:34:34', 'admin', '2022-06-09 10:35:30', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('7593c9e3523a17bca83b8d7fe8a34e58', '3f915b2769fc80648e92d04e84ca059d', '添加用户按钮', '添加用戶按鈕', 'Add User Button', '', NULL, NULL, NULL, 2, 'user:add', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2019-03-16 11:20:33', 'admin', '2022-06-09 10:31:19', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('841057b8a1bef8f6b4b20f9a618a7fa6', '08e6b9dc3c04489c8e1ff2ce6f105aa4', '数据日志', '數據日誌', 'Data Log', '/sys/dataLog-list', 'system/DataLogList', NULL, NULL, 1, NULL, NULL, 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2019-03-11 19:26:49', 'admin', '2022-06-09 10:38:19', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('8b3bff2eee6f1939147f5c68292a1642', '700b7f95165c46cc7a78bf227aa8fed3', '服务器信息', '服務器信息', 'Server Information', '/monitor/SystemInfo', 'modules/monitor/SystemInfo', NULL, NULL, 1, NULL, NULL, 4.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2019-04-02 11:39:19', 'admin', '2022-06-09 10:36:31', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('8d1ebd663688965f1fd86a2f0ead3416', '700b7f95165c46cc7a78bf227aa8fed3', 'Redis监控', 'Redis監控', 'Redis Monitoring', '/monitor/redis/info', 'modules/monitor/RedisInfo', NULL, NULL, 1, NULL, NULL, 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2019-04-02 13:11:33', 'admin', '2022-06-09 10:35:52', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('9502685863ab87f0ad1134142788a385', '', '首页', '首頁', 'Home', '/dashboard/analysis', 'dashboard/Analysis', NULL, NULL, 0, NULL, NULL, 0.00, 0, 'home', 1, 1, 1, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2022-06-09 10:11:47', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('97c8629abc7848eccdb6d77c24bb3ebb', '700b7f95165c46cc7a78bf227aa8fed3', '磁盘监控', '磁盤監控', 'Disk Monitoring', '/monitor/Disk', 'modules/monitor/DiskMonitoring', NULL, NULL, 1, NULL, NULL, 6.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2019-04-25 14:30:06', 'admin', '2022-06-09 10:37:36', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('9cb91b8851db0cf7b19d7ecc2a8193dd', '1939e035e803a99ceecb6f5563570fb2', '我的任务表单', '我的任務表單', '我的任務表單', '/modules/bpm/task/form/FormModule', 'modules/bpm/task/form/FormModule', NULL, NULL, 1, NULL, NULL, 1.00, 0, NULL, 1, 1, NULL, 0, NULL, 'admin', '2019-03-08 16:49:05', 'admin', '2019-03-08 18:37:56', 0, 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES ('aedbf679b5773c1f25e9f7b10111da73', '08e6b9dc3c04489c8e1ff2ce6f105aa4', 'SQL监控', 'SQL監控', 'SQL Monitor', '{{ window._CONFIG[\'domianURL\'] }}/druid/', 'layouts/IframePageView', NULL, NULL, 1, NULL, NULL, 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2019-01-30 09:43:22', 'admin', '2022-06-09 10:38:35', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('b1cb0a3fedf7ed0e4653cb5a229837ee', '08e6b9dc3c04489c8e1ff2ce6f105aa4', '定时任务', '定時任務', 'Timing Task', '/isystem/QuartzJobList', 'system/QuartzJobList', NULL, NULL, 1, NULL, NULL, 3.00, 0, NULL, 1, 1, 0, 0, NULL, NULL, '2019-01-03 09:38:52', 'admin', '2022-06-09 10:39:12', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('b6bcee2ccc854052d3cc3e9c96d90197', '71102b3b87fb07e5527bbd2c530dd90a', '加班申请', '加班申請', '加班申請', '/modules/extbpm/joa/JoaOvertimeList', 'modules/extbpm/joa/JoaOvertimeList', NULL, NULL, 1, NULL, NULL, 1.00, 0, NULL, 1, 1, NULL, 0, NULL, 'admin', '2019-04-03 15:33:10', 'admin', '2019-04-03 15:34:48', 0, 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES ('d07a2c87a451434c99ab06296727ec4f', '700b7f95165c46cc7a78bf227aa8fed3', 'JVM信息', 'JVM信息', 'JVM Information', '/monitor/JvmInfo', 'modules/monitor/JvmInfo', NULL, NULL, 1, NULL, NULL, 4.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2019-04-01 23:07:48', 'admin', '2022-06-09 10:36:58', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('d7d6e2e4e2934f2c9385a623fd98c6f3', '', '系统管理', '系統管理', 'System Management', '/isystem', 'layouts/RouteView', NULL, NULL, 0, NULL, NULL, 80.00, 0, 'setting', 1, 0, 1, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2022-06-09 10:29:36', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('e08cb190ef230d5d4f03824198773950', '1568100525069598721', '系统通告', '系統公告', 'System Announcement', '/isystem/annountCement', 'system/SysAnnouncementList', NULL, NULL, 1, 'annountCement', NULL, 6.00, 0, '', 1, 1, 1, 0, NULL, NULL, '2019-01-02 17:23:01', 'admin', '2022-09-09 12:59:32', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('e8af452d8948ea49d37c934f5100ae6a', '1568099740965437442', '角色管理', '角色管理', 'Role Management', '/isystem/role', 'system/RoleList', NULL, NULL, 1, NULL, NULL, 2.00, 0, NULL, 1, 1, 1, 0, NULL, NULL, '2018-12-25 20:34:38', 'admin', '2022-09-09 12:58:23', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('ebb9d82ea16ad864071158e0c449d186', '1568100525069598721', '分类字典', '分類字典', 'Classification Dictionary', '/isys/category', 'system/SysCategoryList', NULL, NULL, 1, NULL, '1', 5.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2019-05-29 18:48:07', 'admin', '2022-09-09 12:59:11', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES ('f1cb187abf927c88b89470d08615f5ac', '1568100525069598721', '数据字典', '數據字典', 'Data Dictionary', '/isystem/dict', 'system/DictList', NULL, NULL, 1, NULL, NULL, 5.00, 0, NULL, 1, 1, 1, 0, NULL, NULL, '2018-12-28 13:54:43', 'admin', '2022-09-09 12:59:21', 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES ('fc810a2267dd183e4ef7c71cc60f4670', '700b7f95165c46cc7a78bf227aa8fed3', '请求追踪', '請求追蹤', 'Request Tracking', '/monitor/HttpTrace', 'modules/monitor/HttpTrace', NULL, NULL, 1, NULL, NULL, 4.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2019-04-02 09:46:19', 'admin', '2022-06-09 10:37:17', 0, 0, NULL, 0);

-- ----------------------------
-- Table structure for sys_permission_data_rule
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_data_rule`;
CREATE TABLE `sys_permission_data_rule`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `permission_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单ID',
  `rule_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规则名称',
  `rule_column` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字段',
  `rule_conditions` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '条件',
  `rule_value` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规则值',
  `status` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限有效状态1有0否',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_fucntionid`(`permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据权限规则' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission_data_rule
-- ----------------------------

-- ----------------------------
-- Table structure for sys_permission_grant
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_grant`;
CREATE TABLE `sys_permission_grant`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `permission_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单ID',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公司编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单授权表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission_grant
-- ----------------------------
INSERT INTO `sys_permission_grant` VALUES ('1208942637628358657', '1367a93f2c410b169faa7abcbad2f77c', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637628358658', '4f66409ef3bbd69c1d80469d6e2a885e', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637628358659', '882a73768cfd7f78f3a37584f7299656', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637628358660', 'ec8d607d0156e198b11853760319c646', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135874', 'fedfbf4420536cacc0218557d263dfea', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135875', '700b7f95165c46cc7a78bf227aa8fed3', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135876', '9502685863ab87f0ad1134142788a385', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135877', '1166535831146504193', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135878', '00a2a0ae65cdca5e93209cdbde97cbe6', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135879', '078f9558cdeab239aecb2bda1a8ed0d1', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135880', '0ac2ad938963b6c6d1af25477d5b8b51', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135881', '190c2b43bec6a5f7a4194a85db67d96a', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135882', '1a0811914300741f4e11838ff37a1d3a', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135883', '200006f0edf145a2b50eacca07585451', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135884', '277bfabef7d76e89b33062b16a9a5020', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135885', '2aeddae571695cd6380f6d6d334d6e7d', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135886', '3f915b2769fc80648e92d04e84ca059d', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135887', '4148ec82b6acd69f470bea75fe41c357', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135888', '418964ba087b90a84897b62474496b93', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135889', '4356a1a67b564f0988a484f5531fd4d9', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135890', '45c966826eeff4c99b8f8ebfe74511fc', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135891', '53a9230444d33de28aa11cc108fb1dba', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135892', '58857ff846e61794c69208e9d3a85466', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135893', '5c2f42277948043026b7a14692456828', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135894', '655563cd64b75dcf52ef7bcdd4836953', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135895', '65a8f489f25a345836b7f44b1181197a', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135896', '7593c9e3523a17bca83b8d7fe8a34e58', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135897', '841057b8a1bef8f6b4b20f9a618a7fa6', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135898', '8d1ebd663688965f1fd86a2f0ead3416', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135899', '8d4683aacaa997ab86b966b464360338', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135900', '944abf0a8fc22fe1f1154a389a574154', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135901', '9a90363f216a6a08f32eecb3f0bf12a3', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135902', '9cb91b8851db0cf7b19d7ecc2a8193dd', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135903', 'aedbf679b5773c1f25e9f7b10111da73', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135904', 'b6bcee2ccc854052d3cc3e9c96d90197', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135905', 'cc50656cf9ca528e6f2150eba4714ad2', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135906', 'd86f58e7ab516d3bc6bfb1fe10585f97', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135907', 'de13e0f6328c069748de7399fcc1dbbd', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135908', 'f0675b52d89100ee88472b6800754a08', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135909', 'f2849d3814fc97993bfc519ae6bbf049', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135910', 'f780d0d3083d849ccbdb1b1baee4911d', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135911', '1174506953255182338', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135912', '13212d3416eb690c2e1d5033166ff47a', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135913', '6531cf3421b1265aeeeabaab5e176e6d', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135914', '6ad53fd1b220989a8b71ff482d683a5a', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135915', '6e73eb3c26099c191bf03852ee1310a1', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135916', 'ae4fed059f67086fd52a73d913cf473d', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135917', 'b3c824fc22bd953e2eb16ae6914ac8f9', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135918', 'd2bbf9ebca5a8fa2e227af97d2da7548', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135919', 'e8af452d8948ea49d37c934f5100ae6a', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135920', 'fb367426764077dcf94640c843733985', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135921', '1174590283938041857', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135922', '1192318987661234177', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135923', '020b06793e4de2eee0007f603000c769', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135924', '043780fa095ff1b2bec4dc406d76f023', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135925', '05b3c82ddb2536a4a5ee1a4c46b5abef', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135926', '0620e402857b8c5b605e1ad9f4b89350', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135927', '2dbbafa22cda07fa5d169d741b81fe12', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135928', '54dd5457a3190740005c1bfec55b1c34', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135929', '8fb8172747a78756c11916216b8b8066', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135930', 'b1cb0a3fedf7ed0e4653cb5a229837ee', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135931', 'b4dfc7d5dd9e8d5b6dd6d4579b1aa559', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135932', 'c431130c0bc0ec71b0a5be37747bb36a', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135933', 'e1979bb53e9ea51cecc74d86fd9d2f64', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135934', 'e5973686ed495c379d829ea8b2881fc6', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135935', 'e6bfd1fcabfd7942fdd05f076d1dad38', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135936', '024f1fd1283dc632458976463d8984e1', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135937', '265de841c58907954b8877fb85212622', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135938', '339329ed54cf255e1f9392e84f136901', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135939', '4f84f9400e5e92c95f05b554724c2b58', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135940', '58b9204feaf07e47284ddb36cd2d8468', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135941', '8b3bff2eee6f1939147f5c68292a1642', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135942', 'a400e4f4d54f79bf5ce160ae432231af', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135943', 'd07a2c87a451434c99ab06296727ec4f', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135944', 'd7d6e2e4e2934f2c9385a623fd98c6f3', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135945', 'fc810a2267dd183e4ef7c71cc60f4670', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135946', '73678f9daa45ed17a3674131b03432fb', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135947', 'e41b69c57a941a3bbcce45032fe57605', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135948', 'ebb9d82ea16ad864071158e0c449d186', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135949', 'f1cb187abf927c88b89470d08615f5ac', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135950', '08e6b9dc3c04489c8e1ff2ce6f105aa4', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135951', '3fac0d3c9cd40fa53ab70d4c583821f8', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135952', '5c8042bd6c601270b2bbd9b20bccc68b', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135953', '97c8629abc7848eccdb6d77c24bb3ebb', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135954', 'c6cf95444d80435eb37b2f9db3971ae6', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135955', 'e08cb190ef230d5d4f03824198773950', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135956', 'f23d9bfff4d9aa6b68569ba2cff38415', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135957', '2a470fc0c3954d9dbb61de6d80846549', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135958', '7960961b0063228937da5fa8dd73d371', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637645135959', '7ac9eb9ccbde2f7a033cd4944272bf1e', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637699661825', '2e42e3835c2b44ec9f7bc26c146ee531', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637699661826', '4875ebe289344e14844d8e3ea1edd73f', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637699661827', 'c65321e57b7949b7a975313220de0422', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637699661828', 'fb07ca05a3e13674dbf6d3245956da2e', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637699661829', '22d6a3d39a59dd7ea9a30acfa6bfb0a5', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637699661830', '54097c6a3cf50fad0793a34beff1efdf', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637699661831', '540a2936940846cb98114ffb0d145cb8', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637699661832', '717f6bee46f44a3897eca9abd6e2ec44', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637699661833', 'e3c13679c73a4f829bcff2aba8fd68b1', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637699661834', 'fba41089766888023411a978d13c0aa4', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637699661835', '1202490201665445889', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637699661836', '1205382161782677505', '0');
INSERT INTO `sys_permission_grant` VALUES ('1208942637699661837', '1170592628746878978', '0');
INSERT INTO `sys_permission_grant` VALUES ('1528636722991071234', '9502685863ab87f0ad1134142788a385', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1528636723012042755', 'd7d6e2e4e2934f2c9385a623fd98c6f3', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1528636723020431361', '1470610705708236802', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1528636723024625667', '45c966826eeff4c99b8f8ebfe74511fc', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1528636723024625668', 'e8af452d8948ea49d37c934f5100ae6a', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1528636723024625669', '54dd5457a3190740005c1bfec55b1c34', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1528636723024625671', 'f1cb187abf927c88b89470d08615f5ac', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1528636723024625673', '1202490201665445889', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1528636723024625674', '1205382161782677505', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1539883036652007425', '1539879051081740289', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1539883036689756162', '1539879474106658818', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1540251128653651969', '1540251072772939777', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1541717478881464322', '1541714993840230401', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1541717478889852930', '1541715684793094146', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1541717478898241537', '1541716518507483138', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1541960807218962434', '1541960327533191169', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1541988571894632450', '1541982656780947458', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1541988571907215363', '1541983677699706882', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1541988571907215364', '1541981529586913281', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1541988571907215365', '1541985629191917569', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1541988571915603970', '1541984093359427586', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1541988571915603971', '1541986405746331649', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1541988571915603972', '1541986872568172545', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1544490735128883201', '1544490631722512385', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1547126862893666305', '1547126546232102913', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1547520425783537666', '1547519980830797826', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1552576761391190018', '1552576548052111362', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1557989994547769346', '1554657411501756417', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1557989994547769347', '1554379452425662465', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557276286977', '1562336321848799234', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557297258498', '1562336654729736194', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557297258499', '1562337323750584322', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557297258500', '1562337876593405954', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557297258501', '1561550749500801025', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557297258502', '1561551863675715585', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557297258503', '1561552239116255234', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557297258504', '1561552707729063938', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557297258505', '1561552987531083777', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557297258506', '1562277568197750785', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557297258507', '1562281559732318209', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557297258508', '1562281834643779585', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557297258509', '1562292822789124097', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557297258510', '1562293218106470401', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557297258511', '1562293374340100097', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557297258512', '1562293769032495105', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557326618626', '1562294072154845185', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557326618627', '1562294293853171714', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557326618628', '1562315750079524865', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557326618629', '1562315934658260994', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557326618630', '1562316163222663169', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557339201537', '1562316480882470913', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557339201539', '1562317594424045569', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557343395841', '1562317789991858177', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557343395842', '1562340088220545026', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557343395843', '1562323814581071873', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557343395844', '1562324041157373954', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557343395845', '1562324496495210497', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557343395846', '1562324949224189953', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557343395847', '1562325227876970498', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557343395848', '1562330226434568193', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557343395849', '1562330402054270977', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557343395850', '1562330680778354690', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557343395851', '1562330991366565890', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1562352557343395852', '1562331226243395585', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1563022731335712770', '1562354797804056578', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1563022731339907074', '1562355024187420674', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1563022731348295684', '1562355209974116353', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1563022731348295685', '1562355300910821378', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1563022731348295686', '1562355579995615233', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1563022731348295687', '1562355444460875778', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1563022731348295688', '1562355676229726210', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1563022731348295689', '1562355883227017217', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1563022731356684289', '1562354132046376961', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1563022731356684290', '1562354905811578881', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1564797388766330882', '1564183320634163202', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1564797388766330883', '1564183557696225282', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1568099740965437442', '1568099740965437442', '0');
INSERT INTO `sys_permission_grant` VALUES ('1568100525069598721', '1568100525069598721', '0');
INSERT INTO `sys_permission_grant` VALUES ('1568100899465756674', '1568099740965437442', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1568102384840769537', '1568100525069598721', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1568141016624562177', '1562317330237419521', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1579328856284737538', '1578639048786817026', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1587341813954506753', '1347459252333084673', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1587341813975478273', '1364096733661274113', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1590591985824071682', '1587646674090442754', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1590591996473409537', '1587646674090442754', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1590591996473409538', '1590183775042351106', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1590591996473409539', '1587337002368110594', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1590591996473409540', '1589513012312358913', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1590592120096325633', '1541984572600602626', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434203970027521', '3f915b2769fc80648e92d04e84ca059d', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434203978416129', '1a0811914300741f4e11838ff37a1d3a', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434203978416130', '7593c9e3523a17bca83b8d7fe8a34e58', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551820435458', '1238376839743078401', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551820435459', '1245237838706941953', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551828824065', '1238377045649850369', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551828824066', '1245595619741204481', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551828824067', '1247409352122843137', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551828824068', '1250665255173427201', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551828824069', '1245519248885874689', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551828824070', '1242293957467336705', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551828824071', '1242655908936572930', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551833018370', '1242749541727641602', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551833018371', '1544523429292359681', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551833018372', '1544525169764941826', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551833018373', '1544524780617416706', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551837212674', '1562332561168723969', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551837212675', '1562355985496731650', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551837212676', '1547761038531772418', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551837212677', '1562333050874687489', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551837212678', '1562356064047656962', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551837212679', '1544524339766706177', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551837212680', '1562333634210099201', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551837212681', '1562333896765140994', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551837212682', '1562334193969328130', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551837212683', '1562356151305957378', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551837212684', '1552227778936283137', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551837212685', '1562359424725348354', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551837212686', '1562359609601880066', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551845601281', '1562359784898621442', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551845601282', '1547520284091559937', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551845601283', '1562359933309874178', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551845601284', '1562360092966055937', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551845601285', '1570667594480623618', '0A01A04');
INSERT INTO `sys_permission_grant` VALUES ('1593434551845601286', '1579008347489665025', '0A01A04');

-- ----------------------------
-- Table structure for sys_position
-- ----------------------------
DROP TABLE IF EXISTS `sys_position`;
CREATE TABLE `sys_position`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职务编码',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职务名称',
  `post_rank` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职级',
  `company_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司id',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_position
-- ----------------------------

-- ----------------------------
-- Table structure for sys_quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_quartz_job`;
CREATE TABLE `sys_quartz_job`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `del_flag` int(1) NULL DEFAULT NULL COMMENT '删除状态',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `job_class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务类名',
  `cron_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `parameter` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态 0正常 -1停止',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_quartz_job
-- ----------------------------
INSERT INTO `sys_quartz_job` VALUES ('1552221881516904449', 'admin', '2022-07-27 17:18:30', 0, 'admin', '2022-11-18 10:40:17', 'com.easyway.modules.daatm.inf.job.GetCoinPriceTask', '0/30 * * * * ? *', NULL, '定時獲取虛擬幣價格', -1);
INSERT INTO `sys_quartz_job` VALUES ('1590588504044240897', 'admin', '2022-11-10 14:13:46', 0, 'admin', '2022-11-18 10:40:15', 'com.easyway.modules.daatm.inf.job.SaveDeviceStatusWarnTask', '0/30 * * * * ? *', NULL, '定時任務獲取ATM状态預警', -1);
INSERT INTO `sys_quartz_job` VALUES ('1590589204006469634', 'admin', '2022-11-10 14:16:33', 0, 'admin', '2022-11-18 10:40:13', 'com.easyway.modules.daatm.inf.job.SaveDeviceMoneyBoxWarnTask', '0 0,5,10,15,20,25,30,35,40,45,50,55 * * * ? *', NULL, '定時任務獲取錢箱預警', -1);
INSERT INTO `sys_quartz_job` VALUES ('1590589850726203394', 'admin', '2022-11-10 14:19:07', 0, 'admin', '2022-11-15 17:58:01', 'com.easyway.modules.daatm.inf.job.sendAtmBusiLogEmailTask', '59/59 * * * * ? *', NULL, '定時任務发Email', -1);
INSERT INTO `sys_quartz_job` VALUES ('1597062364207583233', 'admin', '2022-11-28 10:58:34', 0, 'admin', '2022-11-28 11:16:12', 'com.easyway.modules.daatm.inf.job.ExportActionLogAndUploadOss', '1 10 11 * * ? *', NULL, '定時任務導出ATM日誌並且上傳OSS', -1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `role_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编码',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司编码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_sys_role_role_code`(`role_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1219068446800203777', '分公司管理员', 'cadmin', '分公司管理员', 'admin', '2020-01-20 09:25:42', 'admin', '2022-11-18 10:42:10', '0A01A04');
INSERT INTO `sys_role` VALUES ('f6817f48af4fb3af11b9e8bf182f618b', '管理员', 'admin', '管理员', NULL, '2018-12-21 18:03:39', 'admin', '2019-05-20 11:40:26', '0');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `permission_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限id',
  `data_rule_ids` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_group_role_per_id`(`role_id`, `permission_id`) USING BTREE,
  INDEX `index_group_role_id`(`role_id`) USING BTREE,
  INDEX `index_group_per_id`(`permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1202490201665445889', 'f6817f48af4fb3af11b9e8bf182f618b', '1202490201665445889', NULL);
INSERT INTO `sys_role_permission` VALUES ('1205382161782677505', 'f6817f48af4fb3af11b9e8bf182f618b', '1205382161782677505', NULL);
INSERT INTO `sys_role_permission` VALUES ('1218019520923709441', 'f6817f48af4fb3af11b9e8bf182f618b', '5c2f42277948043026b7a14692456828', NULL);
INSERT INTO `sys_role_permission` VALUES ('1218019520923709442', 'f6817f48af4fb3af11b9e8bf182f618b', '53a9230444d33de28aa11cc108fb1dba', NULL);
INSERT INTO `sys_role_permission` VALUES ('1218019520948875267', 'f6817f48af4fb3af11b9e8bf182f618b', '190c2b43bec6a5f7a4194a85db67d96a', NULL);
INSERT INTO `sys_role_permission` VALUES ('1218019520948875268', 'f6817f48af4fb3af11b9e8bf182f618b', '1174506953255182338', NULL);
INSERT INTO `sys_role_permission` VALUES ('1218019520957263873', 'f6817f48af4fb3af11b9e8bf182f618b', '1174590283938041857', NULL);
INSERT INTO `sys_role_permission` VALUES ('1218019520982429698', 'f6817f48af4fb3af11b9e8bf182f618b', '9502685863ab87f0ad1134142788a385', NULL);
INSERT INTO `sys_role_permission` VALUES ('1218019521032761347', 'f6817f48af4fb3af11b9e8bf182f618b', '08e6b9dc3c04489c8e1ff2ce6f105aa4', NULL);
INSERT INTO `sys_role_permission` VALUES ('1218019521036955649', 'f6817f48af4fb3af11b9e8bf182f618b', '700b7f95165c46cc7a78bf227aa8fed3', NULL);
INSERT INTO `sys_role_permission` VALUES ('1218019521045344257', 'f6817f48af4fb3af11b9e8bf182f618b', '8d1ebd663688965f1fd86a2f0ead3416', NULL);
INSERT INTO `sys_role_permission` VALUES ('1218019521049538561', 'f6817f48af4fb3af11b9e8bf182f618b', 'fc810a2267dd183e4ef7c71cc60f4670', NULL);
INSERT INTO `sys_role_permission` VALUES ('1218019521049538562', 'f6817f48af4fb3af11b9e8bf182f618b', 'd07a2c87a451434c99ab06296727ec4f', NULL);
INSERT INTO `sys_role_permission` VALUES ('1218019521057927170', 'f6817f48af4fb3af11b9e8bf182f618b', '8b3bff2eee6f1939147f5c68292a1642', NULL);
INSERT INTO `sys_role_permission` VALUES ('1218019521057927171', 'f6817f48af4fb3af11b9e8bf182f618b', '024f1fd1283dc632458976463d8984e1', NULL);
INSERT INTO `sys_role_permission` VALUES ('1218019521057927172', 'f6817f48af4fb3af11b9e8bf182f618b', '97c8629abc7848eccdb6d77c24bb3ebb', NULL);
INSERT INTO `sys_role_permission` VALUES ('1218019521070510082', 'f6817f48af4fb3af11b9e8bf182f618b', '58857ff846e61794c69208e9d3a85466', NULL);
INSERT INTO `sys_role_permission` VALUES ('1218019521070510083', 'f6817f48af4fb3af11b9e8bf182f618b', '841057b8a1bef8f6b4b20f9a618a7fa6', NULL);
INSERT INTO `sys_role_permission` VALUES ('1218019521078898690', 'f6817f48af4fb3af11b9e8bf182f618b', 'aedbf679b5773c1f25e9f7b10111da73', NULL);
INSERT INTO `sys_role_permission` VALUES ('1218019521078898691', 'f6817f48af4fb3af11b9e8bf182f618b', '2dbbafa22cda07fa5d169d741b81fe12', NULL);
INSERT INTO `sys_role_permission` VALUES ('1218019521087287297', 'f6817f48af4fb3af11b9e8bf182f618b', 'b1cb0a3fedf7ed0e4653cb5a229837ee', NULL);
INSERT INTO `sys_role_permission` VALUES ('1219069514649030658', '1219068446800203777', '3f915b2769fc80648e92d04e84ca059d', NULL);
INSERT INTO `sys_role_permission` VALUES ('1219069514653224962', '1219068446800203777', '7593c9e3523a17bca83b8d7fe8a34e58', NULL);
INSERT INTO `sys_role_permission` VALUES ('1219069514653224963', '1219068446800203777', '1a0811914300741f4e11838ff37a1d3a', NULL);
INSERT INTO `sys_role_permission` VALUES ('1219069514661613569', '1219068446800203777', '45c966826eeff4c99b8f8ebfe74511fc', NULL);
INSERT INTO `sys_role_permission` VALUES ('1219069514670002178', '1219068446800203777', 'e8af452d8948ea49d37c934f5100ae6a', NULL);
INSERT INTO `sys_role_permission` VALUES ('1219069514674196481', '1219068446800203777', '1174506953255182338', NULL);
INSERT INTO `sys_role_permission` VALUES ('1219069514674196482', '1219068446800203777', '54dd5457a3190740005c1bfec55b1c34', NULL);
INSERT INTO `sys_role_permission` VALUES ('1219069514674196483', '1219068446800203777', 'f1cb187abf927c88b89470d08615f5ac', NULL);
INSERT INTO `sys_role_permission` VALUES ('1219069514690973698', '1219068446800203777', '1202490201665445889', NULL);
INSERT INTO `sys_role_permission` VALUES ('1219069514699362306', '1219068446800203777', '1205382161782677505', NULL);
INSERT INTO `sys_role_permission` VALUES ('1219069514703556609', '1219068446800203777', 'd7d6e2e4e2934f2c9385a623fd98c6f3', NULL);
INSERT INTO `sys_role_permission` VALUES ('1540251229681852417', '1219068446800203777', '1540251072772939777', NULL);
INSERT INTO `sys_role_permission` VALUES ('1541717627473072130', '1219068446800203777', '1541714993840230401', NULL);
INSERT INTO `sys_role_permission` VALUES ('1541717627481460738', '1219068446800203777', '1541716518507483138', NULL);
INSERT INTO `sys_role_permission` VALUES ('1541717627489849345', '1219068446800203777', '1541715684793094146', NULL);
INSERT INTO `sys_role_permission` VALUES ('1541988854833991681', '1219068446800203777', '1541983677699706882', NULL);
INSERT INTO `sys_role_permission` VALUES ('1541988854842380290', '1219068446800203777', '1541982656780947458', NULL);
INSERT INTO `sys_role_permission` VALUES ('1541988854842380292', '1219068446800203777', '1541960327533191169', NULL);
INSERT INTO `sys_role_permission` VALUES ('1541988854850768897', '1219068446800203777', '1541981529586913281', NULL);
INSERT INTO `sys_role_permission` VALUES ('1541988854850768898', '1219068446800203777', '1541985629191917569', NULL);
INSERT INTO `sys_role_permission` VALUES ('1541988854850768899', '1219068446800203777', '1541984093359427586', NULL);
INSERT INTO `sys_role_permission` VALUES ('1541988854859157505', '1219068446800203777', '1541986405746331649', NULL);
INSERT INTO `sys_role_permission` VALUES ('1541988854859157506', '1219068446800203777', '1541986872568172545', NULL);
INSERT INTO `sys_role_permission` VALUES ('1541988854867546114', '1219068446800203777', '9502685863ab87f0ad1134142788a385', NULL);
INSERT INTO `sys_role_permission` VALUES ('1544490809871380482', '1219068446800203777', '1544490631722512385', NULL);
INSERT INTO `sys_role_permission` VALUES ('1547126945290768385', '1219068446800203777', '1547126546232102913', NULL);
INSERT INTO `sys_role_permission` VALUES ('1547520467256815617', '1219068446800203777', '1547519980830797826', NULL);
INSERT INTO `sys_role_permission` VALUES ('1557990129231065090', '1219068446800203777', '1554657411501756417', NULL);
INSERT INTO `sys_role_permission` VALUES ('1557990129507889154', '1219068446800203777', '1554379452425662465', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510283141121', '1219068446800203777', '1562317594424045569', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510291529730', '1219068446800203777', '1562316163222663169', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510291529731', '1219068446800203777', '1562317330237419521', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510291529732', '1219068446800203777', '1562315934658260994', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510291529733', '1219068446800203777', '1562316480882470913', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510291529734', '1219068446800203777', '1562317789991858177', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510291529735', '1219068446800203777', '1562355579995615233', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510299918338', '1219068446800203777', '1562340088220545026', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510299918339', '1219068446800203777', '1562315750079524865', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510299918340', '1219068446800203777', '1562355444460875778', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510299918341', '1219068446800203777', '1562277568197750785', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510299918342', '1219068446800203777', '1562281834643779585', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510304112642', '1219068446800203777', '1562355209974116353', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510304112643', '1219068446800203777', '1562281559732318209', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510304112644', '1219068446800203777', '1562294293853171714', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510304112645', '1219068446800203777', '1562293218106470401', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510304112646', '1219068446800203777', '1562294072154845185', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510304112647', '1219068446800203777', '1562292822789124097', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510304112648', '1219068446800203777', '1562355300910821378', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510304112649', '1219068446800203777', '1562293769032495105', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510304112650', '1219068446800203777', '1562293374340100097', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510312501250', '1219068446800203777', '1562325227876970498', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510312501251', '1219068446800203777', '1562323814581071873', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510312501252', '1219068446800203777', '1562324949224189953', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510312501253', '1219068446800203777', '1562324496495210497', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510312501254', '1219068446800203777', '1562355676229726210', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510312501255', '1219068446800203777', '1562324041157373954', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510312501256', '1219068446800203777', '1562355883227017217', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510312501257', '1219068446800203777', '1562330402054270977', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510312501258', '1219068446800203777', '1562331226243395585', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510312501259', '1219068446800203777', '1562330226434568193', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510312501260', '1219068446800203777', '1562330991366565890', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510320889863', '1219068446800203777', '1562354132046376961', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510320889864', '1219068446800203777', '1562336654729736194', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510320889865', '1219068446800203777', '1562336321848799234', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510325084162', '1219068446800203777', '1562354905811578881', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510325084163', '1219068446800203777', '1562337323750584322', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510325084164', '1219068446800203777', '1564183557696225282', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510325084165', '1219068446800203777', '1564183320634163202', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510325084166', '1219068446800203777', '1562355024187420674', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510325084167', '1219068446800203777', '1562354797804056578', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510325084168', '1219068446800203777', '1561550749500801025', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510325084169', '1219068446800203777', '1561551863675715585', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510325084170', '1219068446800203777', '1561552239116255234', NULL);
INSERT INTO `sys_role_permission` VALUES ('1567403510329278466', '1219068446800203777', '1562330680778354690', NULL);
INSERT INTO `sys_role_permission` VALUES ('1568099740965437442', 'f6817f48af4fb3af11b9e8bf182f618b', '1568099740965437442', NULL);
INSERT INTO `sys_role_permission` VALUES ('1568100525069598721', 'f6817f48af4fb3af11b9e8bf182f618b', '1568100525069598721', NULL);
INSERT INTO `sys_role_permission` VALUES ('1568101006496006146', '1219068446800203777', '1568099740965437442', NULL);
INSERT INTO `sys_role_permission` VALUES ('1568137309744824321', '1219068446800203777', '1568100525069598721', NULL);
INSERT INTO `sys_role_permission` VALUES ('1579328924643504130', '1219068446800203777', '1578639048786817026', NULL);
INSERT INTO `sys_role_permission` VALUES ('1587341874931298305', '1219068446800203777', '1347459252333084673', NULL);
INSERT INTO `sys_role_permission` VALUES ('1587341875212316674', '1219068446800203777', '1364096733661274113', NULL);
INSERT INTO `sys_role_permission` VALUES ('1590592075976441857', '1219068446800203777', '1587646674090442754', NULL);
INSERT INTO `sys_role_permission` VALUES ('1590592155970207746', '1219068446800203777', '1541984572600602626', NULL);
INSERT INTO `sys_role_permission` VALUES ('1590592156263809026', '1219068446800203777', '1590183775042351106', NULL);
INSERT INTO `sys_role_permission` VALUES ('1590592156414803969', '1219068446800203777', '1587337002368110594', NULL);
INSERT INTO `sys_role_permission` VALUES ('1590592156569993218', '1219068446800203777', '1589513012312358913', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593135695791144962', 'f6817f48af4fb3af11b9e8bf182f618b', '3f915b2769fc80648e92d04e84ca059d', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593135695824699394', 'f6817f48af4fb3af11b9e8bf182f618b', '1a0811914300741f4e11838ff37a1d3a', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593135695833088002', 'f6817f48af4fb3af11b9e8bf182f618b', '7593c9e3523a17bca83b8d7fe8a34e58', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631302496258', '1219068446800203777', '1238376839743078401', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631315079169', '1219068446800203777', '1245519248885874689', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631319273473', '1219068446800203777', '1242749541727641602', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631323467777', '1219068446800203777', '1242655908936572930', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631323467778', '1219068446800203777', '1242293957467336705', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631323467779', '1219068446800203777', '1245237838706941953', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631331856386', '1219068446800203777', '1245595619741204481', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631336050689', '1219068446800203777', '1238377045649850369', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631344439298', '1219068446800203777', '1250665255173427201', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631344439299', '1219068446800203777', '1247409352122843137', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631352827905', '1219068446800203777', '1544523429292359681', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631352827906', '1219068446800203777', '1544525169764941826', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631361216514', '1219068446800203777', '1544524780617416706', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631361216515', '1219068446800203777', '1562355985496731650', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631361216516', '1219068446800203777', '1562332561168723969', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631369605121', '1219068446800203777', '1547761038531772418', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631373799426', '1219068446800203777', '1562356064047656962', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631373799427', '1219068446800203777', '1562333050874687489', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631382188033', '1219068446800203777', '1544524339766706177', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631386382338', '1219068446800203777', '1562334193969328130', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631386382339', '1219068446800203777', '1562333896765140994', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631386382340', '1219068446800203777', '1562333634210099201', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631394770945', '1219068446800203777', '1562356151305957378', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631394770946', '1219068446800203777', '1552227778936283137', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631394770947', '1219068446800203777', '1562359609601880066', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631403159554', '1219068446800203777', '1562359424725348354', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631407353857', '1219068446800203777', '1562359784898621442', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631411548162', '1219068446800203777', '1547520284091559937', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631419936769', '1219068446800203777', '1562359933309874178', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631419936770', '1219068446800203777', '1562360092966055937', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631428325378', '1219068446800203777', '1570667594480623618', NULL);
INSERT INTO `sys_role_permission` VALUES ('1593434631428325379', '1219068446800203777', '1579008347489665025', NULL);
INSERT INTO `sys_role_permission` VALUES ('2', 'f6817f48af4fb3af11b9e8bf182f618b', 'd7d6e2e4e2934f2c9385a623fd98c6f3', NULL);
INSERT INTO `sys_role_permission` VALUES ('45c966826eeff4c99b8f8ebfe74511fc', 'f6817f48af4fb3af11b9e8bf182f618b', '45c966826eeff4c99b8f8ebfe74511fc', NULL);
INSERT INTO `sys_role_permission` VALUES ('54dd5457a3190740005c1bfec55b1c34', 'f6817f48af4fb3af11b9e8bf182f618b', '54dd5457a3190740005c1bfec55b1c34', NULL);
INSERT INTO `sys_role_permission` VALUES ('e08cb190ef230d5d4f03824198773950', 'f6817f48af4fb3af11b9e8bf182f618b', 'e08cb190ef230d5d4f03824198773950', NULL);
INSERT INTO `sys_role_permission` VALUES ('e8af452d8948ea49d37c934f5100ae6a', 'f6817f48af4fb3af11b9e8bf182f618b', 'e8af452d8948ea49d37c934f5100ae6a', NULL);
INSERT INTO `sys_role_permission` VALUES ('ebb9d82ea16ad864071158e0c449d186', 'f6817f48af4fb3af11b9e8bf182f618b', 'ebb9d82ea16ad864071158e0c449d186', NULL);
INSERT INTO `sys_role_permission` VALUES ('f1cb187abf927c88b89470d08615f5ac', 'f6817f48af4fb3af11b9e8bf182f618b', 'f1cb187abf927c88b89470d08615f5ac', NULL);

-- ----------------------------
-- Table structure for sys_sms
-- ----------------------------
DROP TABLE IF EXISTS `sys_sms`;
CREATE TABLE `sys_sms`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `es_title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '消息标题',
  `es_type` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发送方式：1短信 2邮件 3微信',
  `es_receiver` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接收人',
  `es_param` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发送所需参数Json格式',
  `es_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '推送内容',
  `es_send_time` datetime NULL DEFAULT NULL COMMENT '推送时间',
  `es_send_status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '推送状态 0未推送 1推送成功 2推送失败 -1失败不再发送',
  `es_send_num` int(11) NULL DEFAULT NULL COMMENT '发送次数 超过5次不再发送',
  `es_result` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '推送失败原因',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人登录名称',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人登录名称',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_type`(`es_type`) USING BTREE,
  INDEX `index_receiver`(`es_receiver`) USING BTREE,
  INDEX `index_sendtime`(`es_send_time`) USING BTREE,
  INDEX `index_status`(`es_send_status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_sms
-- ----------------------------

-- ----------------------------
-- Table structure for sys_sms_template
-- ----------------------------
DROP TABLE IF EXISTS `sys_sms_template`;
CREATE TABLE `sys_sms_template`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `template_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模板标题',
  `template_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板CODE',
  `template_type` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板类型：1短信 2邮件 3微信',
  `template_content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板内容',
  `template_test_json` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模板测试json',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人登录名称',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新日期',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人登录名称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_templatecode`(`template_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_sms_template
-- ----------------------------

-- ----------------------------
-- Table structure for sys_sysconfig
-- ----------------------------
DROP TABLE IF EXISTS `sys_sysconfig`;
CREATE TABLE `sys_sysconfig`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `paramid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `paramname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `paramdesc` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `grouptype` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '参数配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_sysconfig
-- ----------------------------
INSERT INTO `sys_sysconfig` VALUES ('1248132696320839601', 'AliYunOss_endpoint', 'AliYunOss_endpoint', NULL, '1');
INSERT INTO `sys_sysconfig` VALUES ('1248132696320839602', 'AliYunOss_accessKeyId', 'AliYunOss_accessKeyId', NULL, '1');
INSERT INTO `sys_sysconfig` VALUES ('1248132696320839603', 'AliYunOss_accessKeySecret', 'AliYunOss_accessKeySecret', NULL, '1');
INSERT INTO `sys_sysconfig` VALUES ('1248132696320839604', 'AliYunOss_bucketName', 'AliYunOss_bucketName', NULL, '1');
INSERT INTO `sys_sysconfig` VALUES ('1248132696320839605', 'AliYunOss_roleArn', 'AliYunOss_roleArn', NULL, '1');
INSERT INTO `sys_sysconfig` VALUES ('1248132696320839606', 'AliYunOss_region', 'AliYunOss_region', NULL, '1');
INSERT INTO `sys_sysconfig` VALUES ('1248132696320839607', 'AliYunOss_uploadALi', 'AliYunOss_uploadALi', NULL, '1');
INSERT INTO `sys_sysconfig` VALUES ('1248132696320839608', 'department_select', 'department_select', NULL, '1');
INSERT INTO `sys_sysconfig` VALUES ('1248132696320839609', 'upload_app_path', 'upload_app_path', NULL, '1');
INSERT INTO `sys_sysconfig` VALUES ('1248132696320839610', 'export_excel_max', 'export_excel_max', NULL, '1');
INSERT INTO `sys_sysconfig` VALUES ('1248132696320839682', 'mobile_login_checktype', 'mobile_login_checktype', NULL, '1');
INSERT INTO `sys_sysconfig` VALUES ('1544173645748785153', 'Atm_isVideo', 'Atm_isVideo', NULL, '6');
INSERT INTO `sys_sysconfig` VALUES ('1544174330653462529', 'Atm_noOperate_times', 'Atm_noOperate_times', NULL, '6');
INSERT INTO `sys_sysconfig` VALUES ('1546774685241331713', 'Atm_device_outline_times', 'Atm_device_outline_times', NULL, '5');
INSERT INTO `sys_sysconfig` VALUES ('1546775399581634561', 'Vtcoin_price_refresh_times', 'Vtcoin_price_refresh_times', NULL, '6');
INSERT INTO `sys_sysconfig` VALUES ('1546775935940841473', 'Vtcoin_insertCash_totalmoney', 'Vtcoin_insertCash_totalmoney', NULL, '6');
INSERT INTO `sys_sysconfig` VALUES ('1547382642542944258', 'Emm_autoGrank_Device', 'Emm_autoGrank_Device', NULL, '2');
INSERT INTO `sys_sysconfig` VALUES ('1552807932211531777', 'Atm_deviceStatus_uploadTime', 'Atm_deviceStatus_uploadTime', NULL, '6');
INSERT INTO `sys_sysconfig` VALUES ('1552888302915325954', 'Atm_MaxInsertMoney_pertimes', 'Atm_MaxInsertMoney_pertimes', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1554680902311747585', 'MMATRIX_INF_IP', 'MMATRIX_INF_IP', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1554682583300710401', 'BTC_PRICE', 'BTC_PRICE', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1554683509285593089', 'ETH_PRICE', 'ETH_PRICE', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1554684250456858625', 'BTC_SEND', 'BTC_SEND', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1554684887982678018', 'ETH_SEND', 'ETH_SEND', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1555486711195557889', 'TASK_REFRESH_COIN_PRICES', 'TASK_REFRESH_COIN_PRICES', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1557546390114861057', 'CREATE_WALLET', 'CREATE_WALLET', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1557642772523884545', 'UNRIVAL_INF_IP', 'UNRIVAL_INF_IP', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1557643664371326977', 'X_API_KEY_VALUE', 'X_API_KEY_VALUE', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1557645287206264834', 'getProduct_operationName', 'getProduct_operationName', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1557645528433270785', 'getProduct_variables', 'getProduct_variables', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1557646635133935617', 'getProduct_query', 'getProduct_query', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1557647202052841474', 'listProducts_operationName', 'listProducts_operationName', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1557647522397003778', 'listProducts_variables', 'listProducts_variables', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1557647828983848961', 'listProducts_query', 'listProducts_query', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1557648198380396545', 'createOrder_operationName', 'createOrder_operationName', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1557648561909112834', 'createOrder_variables', 'createOrder_variables', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1557649041099956225', 'createOrder_query', 'createOrder_query', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1557649461398577154', 'getOrderByPartnerOrderId_operationName', 'getOrderByPartnerOrderId_operationName', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1557649667821248514', 'getOrderByPartnerOrderId_variables', 'getOrderByPartnerOrderId_variables', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1557650128557154306', 'getOrderByPartnerOrderId_query', 'getOrderByPartnerOrderId_query', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1557921316667756546', 'X_API_KEY', 'X_API_KEY', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1559433250210684930', 'blockstream_Check_Wallet', 'blockstream_Check_Wallet', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1559433899623161858', 'ethplorer_Check_Wallet', 'ethplorer_Check_Wallet', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1569926888382554114', 'USDT_PRICE', 'USDT_PRICE', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1569930458158882817', 'USDT_SEND', 'USDT_SEND', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1578935815839219714', 'Online_Pay_URL', 'Online_Pay_URL', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1578936265921593345', 'Online_Pay_MID', 'Online_Pay_MID', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1578936820198866945', 'Online_Pay_Alipay_TID', 'Online_Pay_Alipay_TID', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1578937208604000257', 'Online_Pay_Wechat_TID', 'Online_Pay_Wechat_TID', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1579361829067464705', 'Atm_maintenance_password', 'Atm_maintenance_password', NULL, '6');
INSERT INTO `sys_sysconfig` VALUES ('1580019685934342146', 'NFT_SEND', 'NFT_SEND', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1580070560386310145', 'Atm_customer_service_telephone', 'Atm_customer_service_telephone', NULL, '6');
INSERT INTO `sys_sysconfig` VALUES ('1583350625513947138', 'web_login_checktype', 'web_login_checktype', NULL, '1');
INSERT INTO `sys_sysconfig` VALUES ('1583378092979044354', '2fa_login_issuser', '2fa_login_issuser', NULL, '1');
INSERT INTO `sys_sysconfig` VALUES ('1585928792889442305', 'Atm_dashborad_map_url', 'Atm_dashborad_map_url', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1585931213657202689', 'Atm_dashboard_map_url', 'Atm_dashboard_map_url', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1588081602995539969', 'Atm_deviceLogger_uploadTime', 'Atm_deviceLogger_uploadTime', NULL, '6');
INSERT INTO `sys_sysconfig` VALUES ('1589866978439122945', 'Receiving_Email_Address', 'Receiving_Email_Address', NULL, '6');
INSERT INTO `sys_sysconfig` VALUES ('1591002532199337985', 'Is_Not_Telegram', 'Is_Not_Telegram', NULL, '1');
INSERT INTO `sys_sysconfig` VALUES ('1592330004436385794', 'Atm_isEnableDebug', 'Atm_isEnableDebug', NULL, '6');
INSERT INTO `sys_sysconfig` VALUES ('1592791690907582465', 'TelegramNotifier_CHAT_ID', 'TelegramNotifier_CHAT_ID', NULL, '1');
INSERT INTO `sys_sysconfig` VALUES ('1592791957958918146', 'TelegramNotifier_TOKEN', 'TelegramNotifier_TOKEN', NULL, '1');
INSERT INTO `sys_sysconfig` VALUES ('1597520869835902977', 'Atmlog_keepdays', 'Atmlog_keepdays', NULL, '1');
INSERT INTO `sys_sysconfig` VALUES ('1598240432022069250', 'BTC_TRANSFER', 'BTC_TRANSFER', NULL, '4');
INSERT INTO `sys_sysconfig` VALUES ('1599963180593676290', 'ETHS_TRANSFER', 'ETHS_TRANSFER', NULL, '4');

-- ----------------------------
-- Table structure for sys_sysconfigvalue
-- ----------------------------
DROP TABLE IF EXISTS `sys_sysconfigvalue`;
CREATE TABLE `sys_sysconfigvalue`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `paramid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `paramvalue` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人登录名称',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人登录名称',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新日期',
  `companycode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '所属公司',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '参数配置值' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_sysconfigvalue
-- ----------------------------
INSERT INTO `sys_sysconfigvalue` VALUES ('1', 'AliYunOss_endpoint', 'oss-cn-hangzhou.aliyuncs.com', NULL, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('124343423', 'upload_app_path', 'c:/upFiles/', 'app上傳路徑', NULL, NULL, 'admin', '2022-07-05 12:14:16', '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1248132696257925122', 'mobile_login_checktype', '1', '移動設備登錄驗證方式，1：本系統，2：AD驗證，默認:1', 'admin', '2020-04-09 14:16:39', 'admin', '2022-07-05 12:13:42', '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1283236113727909889', 'department_select', '1', '組織機構分公司下為部門或者地區 默認為1（1:部門  2:地區）', 'admin', '2020-07-15 11:05:06', 'admin', '2022-07-05 12:15:24', '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1458682004567744514', 'export_excel_max', '10000', '導出excel最大記錄數，默認值：10000', 'admin', '2021-11-11 14:24:26', 'admin', '2022-07-05 12:14:05', '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1544173645723619329', 'Atm_isVideo', '1', 'ATM是否錄製視頻，0：否，1：是，默認值1', 'admin', '2022-07-05 12:17:41', 'admin', '2022-07-26 09:51:16', '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1544174330603130882', 'Atm_noOperate_times', '30', 'ATM無操作時間間隔返回HOME，默認30秒', 'admin', '2022-07-05 12:20:24', 'admin', '2022-07-26 09:51:27', '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1546774685224554498', 'Atm_device_outline_times', '120', 'ATM離線時間間隔，默認120秒', 'admin', '2022-07-12 16:33:17', 'admin', '2022-07-26 09:52:09', '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1546775399560663042', 'Vtcoin_price_refresh_times', '30', '虛擬幣價格刷新時間間隔，默認30秒', 'admin', '2022-07-12 16:36:08', 'admin', '2022-07-26 09:52:33', '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1546775935928258561', 'Vtcoin_insertCash_totalmoney', '20000', '虛擬幣一次交易最大入錢額度，默認20000元', 'admin', '2022-07-12 16:38:15', 'admin', '2022-07-27 08:18:33', '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1546776856192110593', 'AliYunOss_endpoint', 'oss-cn-hongkong.aliyuncs.com', NULL, 'admin', '2022-07-12 16:41:55', 'atmtest', '2022-10-26 08:19:51', '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1546776856196304898', 'upload_app_path', 'c:/upFiles/', 'app上傳路徑', 'admin', '2022-07-12 16:41:55', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1546776856204693505', 'mobile_login_checktype', '1', '移動設備登錄驗證方式，1：本系統，2：AD驗證，默認:1', 'admin', '2022-07-12 16:41:55', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1546776856204693506', 'department_select', '1', '組織機構分公司下為部門或者地區 默認為1（1:部門  2:地區）', 'admin', '2022-07-12 16:41:55', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1546776856204693507', 'export_excel_max', '10000', '導出excel最大記錄數，默認值：10000', 'admin', '2022-07-12 16:41:55', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1546776856204693511', 'Atm_isVideo', '1', 'ATM是否錄製視頻，0：否，1：是，默認值1', 'admin', '2022-07-12 16:41:55', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1546776856213082113', 'Atm_noOperate_times', '30', 'ATM無操作時間間隔返回HOME，默認30秒', 'admin', '2022-07-12 16:41:55', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1546776856213082114', 'Atm_device_outline_times', '120', 'ATM離線時間間隔，默認120秒', 'admin', '2022-07-12 16:41:55', 'atmtest', '2022-09-14 14:51:20', '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1546776856213082115', 'Vtcoin_price_refresh_times', '30', '虛擬幣價格刷新時間間隔，默認30秒', 'admin', '2022-07-12 16:41:55', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1546776856217276419', 'AliYunOss_accessKeyId', 'LTAI5tA93PLkQvC6jkW1f4GM', NULL, 'admin', '2022-07-12 16:41:55', 'atmtest', '2022-10-26 08:16:55', '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1546776856221470722', 'AliYunOss_accessKeySecret', 'IiIEj7Z7yeBGMb79FCR7dRlhcR8ptJ', NULL, 'admin', '2022-07-12 16:41:55', 'atmtest', '2022-10-26 08:17:21', '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1546776856221470723', 'AliYunOss_bucketName', 'digital-asset-oss-uat', NULL, 'admin', '2022-07-12 16:41:55', 'atmtest', '2022-10-26 08:17:35', '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1546776856221470724', 'AliYunOss_roleArn', 'acs:ram::5108656378534363:role/aliyunossrole', NULL, 'admin', '2022-07-12 16:41:55', 'atmtest', '2022-10-26 08:18:35', '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1546776856221470725', 'AliYunOss_region', 'cn-hongkong', NULL, 'admin', '2022-07-12 16:41:55', 'atmtest', '2022-10-26 08:18:15', '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1546776856221470726', 'AliYunOss_uploadALi', '2', '上傳附件存放位置，1：阿里云OSS，2：本服務器，默認值2', 'admin', '2022-07-12 16:41:55', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1547382642513584129', 'Emm_autoGrank_Device', '1', '移動設備註冊后是否自動審批，0：否，1：是，默認值1', 'admin', '2022-07-14 08:49:06', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1547382677003345922', 'Emm_autoGrank_Device', '0', '移動設備註冊后是否自動審批，0：否，1：是，默認值1', 'admin', '2022-07-14 08:49:14', 'billy', '2022-07-14 12:11:14', '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1552139956563386370', 'Vtcoin_insertCash_totalmoney', '20000', '虛擬幣一次交易最大入錢額度，默認20000元', 'atmtest', '2022-07-27 11:52:58', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1552807931594969089', 'Atm_deviceStatus_uploadTime', '30', 'ATM同步設備狀態回后端的時間間隔，默認30秒', 'admin', '2022-07-29 08:07:15', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1552888302315540481', 'Atm_MaxInsertMoney_pertimes', '1000', 'ATM單次最大進錢金額，默認值1000', 'admin', '2022-07-29 13:26:37', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1552888323027013634', 'Atm_deviceStatus_uploadTime', '30', 'ATM同步設備狀態回后端的時間間隔，默認30秒', 'admin', '2022-07-29 13:26:42', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1552888323807154177', 'Atm_MaxInsertMoney_pertimes', '1000', 'ATM單次最大進錢金額，默認值1000', 'admin', '2022-07-29 13:26:42', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1554680902286581762', 'MMATRIX_INF_IP', 'http://svrtest000.mmatrix.io/', 'mmatrix接口IP地址', 'admin', '2022-08-03 12:09:46', 'admin', '2022-08-03 12:10:28', '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1554682583283933185', 'BTC_PRICE', 'api/000/20220210/quote/btc?q=XBTCHKD', '查詢比特幣價格接口', 'admin', '2022-08-03 12:16:27', 'admin', '2022-08-03 12:19:04', '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1554683509268815874', 'ETH_PRICE', 'api/000/20220210/quote/ethhkd', '查詢以太幣價格接口', 'admin', '2022-08-03 12:20:08', 'admin', '2022-08-03 12:21:10', '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1554684250440081409', 'BTC_SEND', 'btcsend20220211.php', '購買比特幣接口', 'admin', '2022-08-03 12:23:05', 'admin', '2022-08-03 12:23:39', '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1554684887974289410', 'ETH_SEND', 'sendeth20220401.php', '購買以太幣接口', 'admin', '2022-08-03 12:25:37', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1555486211603619841', 'MMATRIX_INF_IP', 'http://svrtest000.mmatrix.io/', 'mmatrix接口IP地址', 'admin', '2022-08-05 17:29:47', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1555486211653951490', 'BTC_PRICE', 'api/000/20220210/quote/btc?q=XBTCHKD', '查詢比特幣價格接口', 'admin', '2022-08-05 17:29:47', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1555486211653951491', 'ETH_PRICE', 'api/000/20220210/quote/ethhkd', '查詢以太幣價格接口', 'admin', '2022-08-05 17:29:47', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1555486211653951492', 'BTC_SEND', 'btcsend20220211.php', '購買比特幣接口', 'admin', '2022-08-05 17:29:47', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1555486211653951493', 'ETH_SEND', 'sendeth20220401.php', '購買以太幣接口', 'admin', '2022-08-05 17:29:47', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1555486711132643329', 'TASK_REFRESH_COIN_PRICES', 'BTC,ETH,USDT', '定時任務進行更新的虛擬幣價格，每種價格以“,\"隔開', 'admin', '2022-08-05 17:31:46', 'admin', '2022-09-14 14:07:49', '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1555487314957230081', 'TASK_REFRESH_COIN_PRICES', 'BTC,ETH,USDT', '定時任務進行更新的虛擬幣價格，每種價格以“,\"隔開', 'admin', '2022-08-05 17:34:10', 'admin', '2022-09-14 14:09:00', '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1557546390072918018', 'CREATE_WALLET', 'api/000/20220210/nfcwallet', '创建钱包地址接口', 'admin', '2022-08-11 09:56:12', 'admin', '2022-08-11 09:57:10', '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1557642772486135810', 'UNRIVAL_INF_IP', 'https://api-sandbox.unrival.io/graphql', 'unrival接口IP地址', 'admin', '2022-08-11 16:19:11', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1557643664350355458', 'X_API_KEY_VALUE', 'b2a62f47-0429-45e3-99d7-7d1648c9db47', 'header參數value', 'admin', '2022-08-11 16:22:44', 'admin', '2022-08-12 10:42:14', '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1557645287181099009', 'getProduct_operationName', 'null', 'getProduct參數1', 'admin', '2022-08-11 16:29:11', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1557645528408104962', 'getProduct_variables', '{}', 'getProduct參數2', 'admin', '2022-08-11 16:30:08', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1557646635117158402', 'getProduct_query', '{  getProduct(id: \"$productId$\") {    id    name    status    quantity    assets {      images {        url      }    }  }}', 'getProduct參數3', 'admin', '2022-08-11 16:34:32', 'admin', '2022-08-12 10:21:29', '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1557647202040258561', 'listProducts_operationName', 'q', 'listProducts參數1', 'admin', '2022-08-11 16:36:47', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1557647522367643649', 'listProducts_variables', '{}', 'listProducts參數2', 'admin', '2022-08-11 16:38:04', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1557647828962877442', 'listProducts_query', 'query q {listProducts(limit: 40) {items {id name status platforms region quantity assets { images { url type } } price { costPrice { amount currencyCode} currentSellingPrice {amount currencyCode} fullSellingPrice {amount currencyCode}}} nextToken}}', 'listProducts參數3', 'admin', '2022-08-11 16:39:17', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1557648198359425026', 'createOrder_operationName', 'null', 'createOrder參數1', 'admin', '2022-08-11 16:40:45', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1557648561883947010', 'createOrder_variables', '{}', 'createOrder參數2', 'admin', '2022-08-11 16:42:12', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1557649041078984705', 'createOrder_query', 'mutation {  createOrder(    input: {partnerOrderId: \"$orderId$\", items: {productId: \"$productId$\", quantity: 1}}  ) {    id    partnerOrderId    redemptionUrl  }}', 'createOrder參數3', 'admin', '2022-08-11 16:44:06', 'admin', '2022-08-12 10:52:42', '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1557649461239193601', 'getOrderByPartnerOrderId_operationName', 'null', 'getOrderByPartnerOrderId參數1', 'admin', '2022-08-11 16:45:46', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1557649667791888385', 'getOrderByPartnerOrderId_variables', '{}', 'getOrderByPartnerOrderId參數2', 'admin', '2022-08-11 16:46:35', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1557650128531988481', 'getOrderByPartnerOrderId_query', '{  getOrderByPartnerOrderId(id: \"$orderId$\") {    id    items {      quantity      fulfilment {        ... on ActivationKeyFulfilment {          keys {            key            activation            receipt          }        }      }    }  }}', 'getOrderByPartnerOrderId參數3', 'admin', '2022-08-11 16:48:25', 'admin', '2022-08-15 11:45:27', '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1557921316638396417', 'X_API_KEY', 'x-api-key', 'header參數key', 'admin', '2022-08-12 10:46:01', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1559433250185519105', 'blockstream_Check_Wallet', 'https://blockstream.info/api/address/', '查询Bitcoin钱包余额', 'admin', '2022-08-16 14:53:54', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1559433899602190337', 'ethplorer_Check_Wallet', 'https://ethplorer.io/service/service.php?data=', '查询ETH与USDT钱包余额', 'admin', '2022-08-16 14:56:29', 'admin', '2022-08-16 16:12:06', '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1569926887770185729', 'USDT_PRICE', 'api/000/20220210/quote/usdthkd/', '查詢USDT價格接口', 'admin', '2022-09-14 13:51:53', 'admin', '2022-09-14 14:06:26', '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1569930457571680258', 'USDT_SEND', 'sendusdt202207.php', '購買USDT接口', 'admin', '2022-09-14 14:06:04', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1569930940717752322', 'CREATE_WALLET', 'api/000/20220210/nfcwallet', '创建钱包地址接口', 'admin', '2022-09-14 14:07:59', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1569930941162348546', 'UNRIVAL_INF_IP', 'https://api-sandbox.unrival.io/graphql', 'unrival接口IP地址', 'admin', '2022-09-14 14:07:59', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1569930941393035266', 'X_API_KEY_VALUE', 'b2a62f47-0429-45e3-99d7-7d1648c9db47', 'header參數value', 'admin', '2022-09-14 14:07:59', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1569930941627916289', 'getProduct_operationName', 'null', 'getProduct參數1', 'admin', '2022-09-14 14:07:59', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1569930941862797313', 'getProduct_variables', '{}', 'getProduct參數2', 'admin', '2022-09-14 14:07:59', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1569930942076706818', 'getProduct_query', '{  getProduct(id: \"$productId$\") {    id    name    status    quantity    assets {      images {        url      }    }  }}', 'getProduct參數3', 'admin', '2022-09-14 14:07:59', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1569930942395473921', 'listProducts_operationName', 'q', 'listProducts參數1', 'admin', '2022-09-14 14:07:59', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1569930942626160641', 'listProducts_variables', '{}', 'listProducts參數2', 'admin', '2022-09-14 14:07:59', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1569930942873624578', 'listProducts_query', 'query q {listProducts(limit: 40) {items {id name status platforms region quantity assets { images { url type } } price { costPrice { amount currencyCode} currentSellingPrice {amount currencyCode} fullSellingPrice {amount currencyCode}}} nextToken}}', 'listProducts參數3', 'admin', '2022-09-14 14:07:59', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1569930943276277761', 'createOrder_operationName', 'null', 'createOrder參數1', 'admin', '2022-09-14 14:07:59', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1569930943511158786', 'createOrder_variables', '{}', 'createOrder參數2', 'admin', '2022-09-14 14:07:59', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1569930943754428418', 'createOrder_query', 'mutation {  createOrder(    input: {partnerOrderId: \"$orderId$\", items: {productId: \"$productId$\", quantity: 1}}  ) {    id    partnerOrderId    redemptionUrl  }}', 'createOrder參數3', 'admin', '2022-09-14 14:08:00', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1569930944031252481', 'getOrderByPartnerOrderId_operationName', 'null', 'getOrderByPartnerOrderId參數1', 'admin', '2022-09-14 14:08:00', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1569930944245161985', 'getOrderByPartnerOrderId_variables', '{}', 'getOrderByPartnerOrderId參數2', 'admin', '2022-09-14 14:08:00', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1569930944492625922', 'getOrderByPartnerOrderId_query', '{  getOrderByPartnerOrderId(id: \"$orderId$\") {    id    items {      quantity      fulfilment {        ... on ActivationKeyFulfilment {          keys {            key            activation            receipt          }        }      }    }  }}', 'getOrderByPartnerOrderId參數3', 'admin', '2022-09-14 14:08:00', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1569930944844947457', 'X_API_KEY', 'x-api-key', 'header參數key', 'admin', '2022-09-14 14:08:00', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1569930945067245570', 'blockstream_Check_Wallet', 'https://blockstream.info/api/address/', '查询Bitcoin钱包余额', 'admin', '2022-09-14 14:08:00', 'alex', '2022-11-10 16:04:47', '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1569930945302126594', 'ethplorer_Check_Wallet', 'https://ethplorer.io/service/service.php?data=', '查询ETH与USDT钱包余额', 'admin', '2022-09-14 14:08:00', 'alex', '2022-11-10 16:04:29', '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1569930945537007618', 'USDT_PRICE', 'api/000/20220210/quote/usdthkd/', '查詢USDT價格接口', 'admin', '2022-09-14 14:08:00', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1569930945746722818', 'USDT_SEND', 'sendusdt202207.php', '購買USDT接口', 'admin', '2022-09-14 14:08:00', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1578935815805665282', 'Online_Pay_URL', 'https://testpri.eftsolutions.com:6443/UAT', '在线支付URL', 'admin', '2022-10-09 10:30:08', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1578936265875456002', 'Online_Pay_MID', '344888800000001', '在线支付参数MID', 'admin', '2022-10-09 10:31:56', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1578936820182089730', 'Online_Pay_Alipay_TID', '28000008', '在线支付参数TID（支付宝）', 'admin', '2022-10-09 10:34:08', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1578937208583028737', 'Online_Pay_Wechat_TID', '13000008', '在线支付参数TID（微信）', 'admin', '2022-10-09 10:35:40', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1579045165790445569', 'Online_Pay_URL', 'https://testpri.eftsolutions.com:6443/UAT', '在线支付URL', 'feynman', '2022-10-09 17:44:39', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1579045165807222786', 'Online_Pay_MID', '344888800000001', '在线支付参数MID', 'feynman', '2022-10-09 17:44:39', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1579045165807222787', 'Online_Pay_Alipay_TID', '28000008', '在线支付参数TID（支付宝）', 'feynman', '2022-10-09 17:44:39', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1579045165807222788', 'Online_Pay_Wechat_TID', '13000008', '在线支付参数TID（微信）', 'feynman', '2022-10-09 17:44:39', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1579361829012938753', 'Atm_maintenance_password', '000000', '點擊logo10次進入運維+導航條頁面時，需要先輸入密碼（仅支持数字）。密码为空，则点击无效', 'admin', '2022-10-10 14:42:58', 'admin', '2022-10-12 13:37:01', '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1580019685909176322', 'NFT_SEND', 'sendetherc721_202209.php', '購買NFT接口', 'admin', '2022-10-12 10:17:03', 'admin', '2022-10-13 08:52:24', '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1580070559782330370', 'Atm_customer_service_telephone', '0756-3667688', '客户服务电话', 'admin', '2022-10-12 13:39:12', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1580359753272401922', 'NFT_SEND', 'sendetherc721_202209.php', '購買NFT接口', 'atmtest', '2022-10-13 08:48:22', 'admin', '2022-10-13 08:52:29', '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1580359753293373441', 'Atm_customer_service_telephone', '0756-3667688', '客户服务电话', 'atmtest', '2022-10-13 08:48:22', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1583350625463615489', 'web_login_checktype', '1', 'WEB登錄驗證方式，1：本系統，2：GOOGLE 2FA', 'admin', '2022-10-21 14:53:01', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1583350795089657858', 'web_login_checktype', '1', 'WEB登錄驗證方式，1：本系統，2：GOOGLE 2FA', 'admin', '2022-10-21 14:53:41', 'atmtest', '2022-11-07 09:17:12', '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1583378092958072833', '2fa_login_issuser', 'DAATM', 'Google 2FA issuser', 'admin', '2022-10-21 16:42:10', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1583378161560109058', '2fa_login_issuser', 'DAATM', 'Google 2FA issuser', 'admin', '2022-10-21 16:42:26', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1585931213627842562', 'Atm_dashboard_map_url', 'http://8.210.130.157:3000/d-solo/xGPiowZVk/transaction-amount?orgId=1&from=1659283200000&to=1660838399000&theme=light&panelId=6', 'dashboard地图URL', 'admin', '2022-10-28 17:47:21', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1585931221982896129', 'Atm_dashboard_map_url', 'http://8.210.130.157:3000/d-solo/xGPiowZVk/transaction-amount?orgId=1&from=1659283200000&to=1660838399000&theme=light&panelId=6', 'dashboard地图URL', 'admin', '2022-10-28 17:47:23', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1588081602186039297', 'Atm_deviceLogger_uploadTime', '120', 'ATM同步設備日志回后端的時間間隔，默認120秒	', 'admin', '2022-11-03 16:12:14', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1588085487860568065', 'Atm_deviceLogger_uploadTime', '120', 'ATM同步設備日志回后端的時間間隔，默認120秒	', 'atmtest', '2022-11-03 16:27:40', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1589866978405568514', 'Receiving_Email_Address', '1587436689@qq.com', '接收日誌郵件的郵箱地址', 'admin', '2022-11-08 14:26:41', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1589867028418449409', 'Receiving_Email_Address', '724125950@q.com', '接收日誌郵件的郵箱地址', 'admin', '2022-11-08 14:26:53', 'alex', '2022-11-15 16:49:27', '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1591002532044148737', 'Is_Not_Telegram', '0', '是否启用机器人通知，0：不启用，1：启用，默认0不启用', 'admin', '2022-11-11 17:38:58', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1591002554781470722', 'Is_Not_Telegram', '0', '是否启用机器人通知，0：不启用，1：启用，默认0不启用', 'admin', '2022-11-11 17:39:03', 'feynman', '2022-11-11 17:48:48', '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1592330004272807938', 'Atm_isEnableDebug', '1', '是否激活日志DEBUG模式，0：否，1：是，默認值0', 'admin', '2022-11-15 09:33:52', 'admin', '2022-11-15 09:34:48', '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1592339789051363330', 'Atm_isEnableDebug', '1', '是否激活日志DEBUG模式，0：否，1：是，默認值0', 'billy', '2022-11-15 10:12:45', 'alex', '2022-11-15 14:54:27', '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1592791690865639426', 'TelegramNotifier_CHAT_ID', '-795050287', 'TelegramNotifier 参数', 'admin', '2022-11-16 16:08:26', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1592791957916975106', 'TelegramNotifier_TOKEN', '5468398690:AAFhcYP-v7ILrVBp_crWu5joGg5vEEhin5E', 'TelegramNotifier token', 'admin', '2022-11-16 16:09:30', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1592791970441166850', 'TelegramNotifier_CHAT_ID', '-795050287', 'TelegramNotifier 参数', 'admin', '2022-11-16 16:09:33', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1592791970457944065', 'TelegramNotifier_TOKEN', '5468398690:AAFhcYP-v7ILrVBp_crWu5joGg5vEEhin5E', 'TelegramNotifier token', 'admin', '2022-11-16 16:09:33', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1597520869777182721', 'Atmlog_keepdays', '7', 'ATM日誌在數據庫中保留最近N天的記錄，默認值：7', 'admin', '2022-11-29 17:20:31', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1597520956364394498', 'Atmlog_keepdays', '7', 'ATM日誌在數據庫中保留最近N天的記錄，默認值：7', 'admin', '2022-11-29 17:20:51', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1598240431975931905', 'BTC_TRANSFER', 'api/000/20220210/nfcwallet/withdraw_btc', '比特幣錢包餘額轉移接口', 'admin', '2022-12-01 16:59:48', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1598240579607044098', 'BTC_TRANSFER', 'api/000/20220210/nfcwallet/withdraw_btc', '比特幣錢包餘額轉移接口', 'admin', '2022-12-01 17:00:23', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('1599963180564316162', 'ETHS_TRANSFER', 'api/000/20220210/nfcwallet/withdraw_eth', 'ETH和USDT錢包餘額一起轉移接口', 'admin', '2022-12-06 11:05:23', 'admin', '2022-12-06 11:07:21', '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('1599964744209235969', 'ETHS_TRANSFER', 'api/000/20220210/nfcwallet/withdraw_eth', 'ETH和USDT錢包餘額一起轉移接口', 'admin', '2022-12-06 11:11:36', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` VALUES ('2', 'AliYunOss_accessKeyId', 'LTAI4GAeh66HiyE3L4zCHym3', NULL, NULL, NULL, 'admin', '2020-09-21 11:56:06', '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('3', 'AliYunOss_accessKeySecret', 'StS0zHhnfgk83JZcqwm3nbRonPUTZG', NULL, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('4', 'AliYunOss_bucketName', 'atm', NULL, NULL, NULL, 'admin', '2021-08-11 14:32:13', '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('5', 'AliYunOss_roleArn', 'acs:ram::1722291666798474:role/202006', NULL, NULL, NULL, 'admin', '2021-08-11 14:32:32', '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('6', 'AliYunOss_region', 'cn-hangzhou', NULL, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` VALUES ('7', 'AliYunOss_uploadALi', '2', '上傳附件存放位置，1：阿里云OSS，2：本服務器，默認值2', NULL, NULL, 'admin', '2022-07-05 12:12:26', '0');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录账号',
  `realname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'md5密码盐',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `birthday` datetime NULL DEFAULT NULL COMMENT '生日',
  `sex` tinyint(1) NULL DEFAULT NULL COMMENT '性别(0-默认未知,1-男,2-女)',
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `org_code` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机构编码',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '性别(1-正常,2-冻结)',
  `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除状态(0-正常,1-已删除)',
  `activiti_sync` tinyint(1) NULL DEFAULT NULL COMMENT '同步工作流引擎(1-同步,0-不同步)',
  `work_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工号，唯一键',
  `post` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职务，关联职务表',
  `telephone` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '座机号',
  `magickey` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '2FAKey',
  `issuser` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '2FAISSUser',
  `isbind` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '2FA isbind',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公司编码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_user_name`(`username`) USING BTREE,
  UNIQUE INDEX `uniq_sys_user_work_no`(`work_no`) USING BTREE,
  INDEX `index_user_status`(`status`) USING BTREE,
  INDEX `index_user_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('9a6fc5bce53a4545bce01bc6a882d2da', 'atmadmin', 'atmadmin', '37a7562cd960ed63d91c882e9c840895', 'CIn1khlv', NULL, NULL, NULL, NULL, NULL, '0A01A04A02', 1, 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2022-11-18 10:47:26', NULL, NULL, '0A01A04');
INSERT INTO `sys_user` VALUES ('e9ca23d68d884d4ebb19d07889727dae', 'admin', '管理员', 'c98fee943c952585', 'RCGTeGiH', '', NULL, NULL, NULL, NULL, '0', 1, 0, 1, '111', '', NULL, NULL, NULL, NULL, NULL, '2019-06-21 17:54:10', 'admin', '2022-11-18 10:53:47', '0');

-- ----------------------------
-- Table structure for sys_user_agent
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_agent`;
CREATE TABLE `sys_user_agent`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '序号',
  `user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `agent_user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '代理人用户名',
  `start_time` datetime NULL DEFAULT NULL COMMENT '代理开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '代理结束时间',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态0无效1有效',
  `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人名称',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人登录名称',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人名称',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人登录名称',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新日期',
  `sys_org_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属部门',
  `sys_company_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属公司',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_username`(`user_name`) USING BTREE,
  INDEX `statux_index`(`status`) USING BTREE,
  INDEX `begintime_index`(`start_time`) USING BTREE,
  INDEX `endtime_index`(`end_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户代理人设置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_agent
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_depart
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_depart`;
CREATE TABLE `sys_user_depart`  (
  `ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `dep_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门id',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `index_depart_groupk_userid`(`user_id`) USING BTREE,
  INDEX `index_depart_groupkorgid`(`dep_id`) USING BTREE,
  INDEX `index_depart_groupk_uidanddid`(`user_id`, `dep_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_depart
-- ----------------------------
INSERT INTO `sys_user_depart` VALUES ('1593435680985792514', '9a6fc5bce53a4545bce01bc6a882d2da', '68a63481331e422cba05417f1cf7683c');
INSERT INTO `sys_user_depart` VALUES ('1593437280059998209', 'e9ca23d68d884d4ebb19d07889727dae', '16fb593249ab4505a2e96ea8da93e152');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index2_groupuu_user_id`(`user_id`) USING BTREE,
  INDEX `index2_groupuu_ole_id`(`role_id`) USING BTREE,
  INDEX `index2_groupuu_useridandroleid`(`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1593435680918683650', '9a6fc5bce53a4545bce01bc6a882d2da', '1219068446800203777');
INSERT INTO `sys_user_role` VALUES ('1593437280194215937', 'e9ca23d68d884d4ebb19d07889727dae', 'f6817f48af4fb3af11b9e8bf182f618b');

-- ----------------------------
-- Table structure for t_em_app
-- ----------------------------
DROP TABLE IF EXISTS `t_em_app`;
CREATE TABLE `t_em_app`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `appcode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '应用编码,解析apk，获取的package名称',
  `appname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '应用名称',
  `platform` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT 'android/ios，默认安卓，0为安卓，1为IOS',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '应用简介',
  `status` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '1:上架 0 下架,默认上架',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ind_code`(`appcode`, `companycode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '应用表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_em_app
-- ----------------------------
INSERT INTO `t_em_app` VALUES ('1539093042779250689', 'com.easyway.digitalassetatm', 'daatm', '0', 'daatm', '1', 'atmtest', '2022-06-21 11:49:11', 'atmtest', '2022-06-21 11:58:44', '0A01A04');

-- ----------------------------
-- Table structure for t_em_appauthorization
-- ----------------------------
DROP TABLE IF EXISTS `t_em_appauthorization`;
CREATE TABLE `t_em_appauthorization`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `app_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '应用ID',
  `authororgid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权组织机构编码',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '简介',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '应用授权表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_em_appauthorization
-- ----------------------------

-- ----------------------------
-- Table structure for t_em_apppermission
-- ----------------------------
DROP TABLE IF EXISTS `t_em_apppermission`;
CREATE TABLE `t_em_apppermission`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `parent_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父id',
  `app_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '应用ID',
  `permissioname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权组织机构编码',
  `permissioncode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `seq` int(11) NULL DEFAULT NULL COMMENT '简介',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '应用授权表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_em_apppermission
-- ----------------------------
INSERT INTO `t_em_apppermission` VALUES ('btn_check_wallet', NULL, '1539093042779250689', '查看钱包', 'btn_check_wallet', 2, 'admin', '2021-07-28 15:20:35', NULL, NULL, '0A01A04');
INSERT INTO `t_em_apppermission` VALUES ('btn_promotion_code', NULL, '1539093042779250689', '优惠码', 'btn_promotion_code', 1, 'admin', '2021-07-28 15:20:35', NULL, NULL, '0A01A04');
INSERT INTO `t_em_apppermission` VALUES ('fun_digital_assets', NULL, '1539093042779250689', '数字资产', 'fun_digital_assets', 4, 'admin', '2022-03-04 15:03:11', NULL, NULL, '0A01A04');
INSERT INTO `t_em_apppermission` VALUES ('fun_gift_cards', NULL, '1539093042779250689', '礼品卡', 'fun_gift_cards', 3, 'admin', '2021-09-13 10:05:19', NULL, NULL, '0A01A04');
INSERT INTO `t_em_apppermission` VALUES ('fun_nft', NULL, '1539093042779250689', '游戏卡&NFT', 'fun_nft', 5, 'admin', '2022-03-04 15:04:32', NULL, NULL, '0A01A04');
INSERT INTO `t_em_apppermission` VALUES ('fun_nft_vip', NULL, '1539093042779250689', 'VIP NFT', 'fun_nft_vip', 6, 'admin', '2022-03-04 15:04:32', NULL, NULL, '0A01A04');

-- ----------------------------
-- Table structure for t_em_device_app
-- ----------------------------
DROP TABLE IF EXISTS `t_em_device_app`;
CREATE TABLE `t_em_device_app`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `appcode` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '应用编码',
  `deviceid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备编号，通常是imei号 ',
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '0 未审核\r\n1 启用\r\n2 禁用\r\n3 审核未通过',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '设备应用关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_em_device_app
-- ----------------------------

-- ----------------------------
-- Table structure for t_em_deviceauthorization
-- ----------------------------
DROP TABLE IF EXISTS `t_em_deviceauthorization`;
CREATE TABLE `t_em_deviceauthorization`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `appcode` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '应用编码 ',
  `deviceid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备编号，通常是imei号',
  `account` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备注册帐号 ',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '0：未审核 1 审核通过 2 审核未通过',
  `actiondesc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '设备审核日志文字描述，如： 设备号(984344443)通过帐号(loyin)申请开通esom应用,审核员(张三)审批通过,审核时间:2020-02-12  ',
  `auditname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核人 ',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '设备审核表日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_em_deviceauthorization
-- ----------------------------

-- ----------------------------
-- Table structure for t_em_mobiledevice
-- ----------------------------
DROP TABLE IF EXISTS `t_em_mobiledevice`;
CREATE TABLE `t_em_mobiledevice`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `deviceid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备IMEI码',
  `devicecode` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备编号',
  `brand` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '品牌',
  `model` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '型号',
  `realname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备使用人',
  `telephone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号 ',
  `registertime` datetime NULL DEFAULT NULL COMMENT '登记时间 ',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'emp_mobiledevice_status 1：启用 ，2：禁用',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '说明 ',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ind_deviceorg`(`deviceid`, `companycode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '设备表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_em_mobiledevice
-- ----------------------------

-- ----------------------------
-- Table structure for t_em_package
-- ----------------------------
DROP TABLE IF EXISTS `t_em_package`;
CREATE TABLE `t_em_package`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `app_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `packageimage` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '应用图标base64编码，存储应用图标',
  `packagename` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '版本名称',
  `status` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '0:未发布 1:已发布 ',
  `version` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '版本号',
  `size` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '包大小',
  `minversion` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'Android' COMMENT '最低支持系统版本',
  `upgrade` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '是否必须升级,0 非必须 1 必须，默认 1',
  `shorturl` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '短链接 应用下载链接',
  `platform` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '平台(Android 或 iOS)，默认 Android ,0为安卓，1为IOS',
  `extra` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '扩展信息(json格式) ',
  `dbpath` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `filename` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件名 filename',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '说明 ',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `packapp`(`app_id`) USING BTREE,
  CONSTRAINT `t_em_package_ibfk_1` FOREIGN KEY (`app_id`) REFERENCES `t_em_app` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '版本表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_em_package
-- ----------------------------

-- ----------------------------
-- Table structure for t_em_packageauthorization
-- ----------------------------
DROP TABLE IF EXISTS `t_em_packageauthorization`;
CREATE TABLE `t_em_packageauthorization`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `packageid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '版本ID',
  `authororgid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权组织机构编码',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '简介',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '版本授权' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_em_packageauthorization
-- ----------------------------

-- ----------------------------
-- Table structure for t_em_role_apppermission
-- ----------------------------
DROP TABLE IF EXISTS `t_em_role_apppermission`;
CREATE TABLE `t_em_role_apppermission`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `permission_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限id',
  `app_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '应用id',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_group_role_per_id`(`role_id`, `permission_id`) USING BTREE,
  INDEX `index_group_role_id`(`role_id`) USING BTREE,
  INDEX `index_group_per_id`(`permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_em_role_apppermission
-- ----------------------------

-- ----------------------------
-- Table structure for t_inf_appuploadprocess
-- ----------------------------
DROP TABLE IF EXISTS `t_inf_appuploadprocess`;
CREATE TABLE `t_inf_appuploadprocess`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `appcode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '应用编码',
  `deviceid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'Android' COMMENT '移动设备编码',
  `datagroup` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据类别',
  `uploadbatchid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上传批次号',
  `uploadman` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上传人',
  `uploadtime` datetime NULL DEFAULT NULL COMMENT '上传时间',
  `filepath` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `attachmentcount` int(11) NULL DEFAULT NULL COMMENT '上传文件数量',
  `needprocesscount` int(11) NULL DEFAULT NULL COMMENT '需处理数',
  `realprocesscount` int(11) NULL DEFAULT NULL COMMENT '实际处理数',
  `processtime` datetime NULL DEFAULT NULL COMMENT '处理时间',
  `state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '处理状态，1：待处理，2：处理完成，3：处理失败',
  `processremark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '处理备注',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uploadprocess_deviceid`(`deviceid`) USING BTREE,
  INDEX `uploadprocess_uploadman`(`uploadman`) USING BTREE,
  INDEX `uploadprocess_uploadtime`(`uploadtime`) USING BTREE,
  INDEX `uploadprocess_state`(`state`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '移动上传处理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_inf_appuploadprocess
-- ----------------------------

-- ----------------------------
-- Table structure for t_inf_interfacelog
-- ----------------------------
DROP TABLE IF EXISTS `t_inf_interfacelog`;
CREATE TABLE `t_inf_interfacelog`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `busino` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务流水号',
  `modulecode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模块编码',
  `interfacebusicode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接口业务编码',
  `interfacename` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'Android' COMMENT '接口名称',
  `reqcontent` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '请求内容',
  `resultcontent` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '响应内容',
  `issuccess` tinyint(1) NULL DEFAULT NULL COMMENT '是否成功',
  `endpoint` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求地址',
  `reqtype` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调用方式',
  `timecost` int(11) NULL DEFAULT NULL COMMENT '调用时长',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `companycode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组织机构编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `interfacelog_busino`(`busino`) USING BTREE,
  INDEX `interfacelog_modulecode`(`modulecode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '接口日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_inf_interfacelog
-- ----------------------------

-- ----------------------------
-- View structure for v_cc_months
-- ----------------------------
DROP VIEW IF EXISTS `v_cc_months`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_cc_months` AS select '01' AS `months` union select '02' AS `months` union select '03' AS `months` union select '04' AS `months` union select '05' AS `months` union select '06' AS `months` union select '07' AS `months` union select '08' AS `months` union select '09' AS `months` union select '10' AS `months` union select '11' AS `months` union select '12' AS `months`;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `sys_sysconfig` (`id`, `paramid`, `paramname`, `paramdesc`, `grouptype`) VALUES ('1600781927197753346', 'Reques_timeOut', 'Reques_timeOut', NULL, '4');
INSERT INTO `sys_sysconfigvalue` (`id`, `paramid`, `paramvalue`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`, `companycode`) VALUES ('1600781946420248578', 'Reques_timeOut', '610000', '请求第三方接口timeOut时间,单位：毫秒。默认：610000', 'admin', '2022-12-08 17:18:52', NULL, NULL, '0A01A04');
INSERT INTO `sys_sysconfigvalue` (`id`, `paramid`, `paramvalue`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`, `companycode`) VALUES ('1600781927155810305', 'Reques_timeOut', '610000', '请求第三方接口timeOut时间单位：毫秒。默认：610000', 'admin', '2022-12-08 17:18:47', NULL, NULL, '0');

INSERT INTO `sys_sysconfig` (`id`, `paramid`, `paramname`, `paramdesc`, `grouptype`) VALUES ('1602133016281354242', 'Atm_nfc_password_range', 'Atm_nfc_password_range', NULL, '6');
INSERT INTO `sys_sysconfigvalue` (`id`, `paramid`, `paramvalue`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`, `companycode`) VALUES ('1602133016235216895', 'Atm_nfc_password_range', '4,8', 'nfc卡创建账号时限定密码长度范围，min,max，英文逗号分隔', 'admin', '2022-12-12 10:47:32', NULL, NULL, '0');
INSERT INTO `sys_sysconfigvalue` (`id`, `paramid`, `paramvalue`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`, `companycode`) VALUES ('1602133016235216898', 'Atm_nfc_password_range', '4,8', 'nfc卡创建账号时限定密码长度范围，min,max，英文逗号分隔', 'admin', '2022-12-12 10:47:32', NULL, NULL, '0A01A04');

alter table atm_transactions modify column remark varchar(1000) COMMENT '备注';

alter table t_inf_interfacelog modify column remark varchar(1000) COMMENT '备注';

ALTER TABLE `atm_wallet_transfer` MODIFY COLUMN `from_wallet_balance` decimal(22, 8) NOT NULL COMMENT '转出金额' AFTER `from_wallet_address`;

ALTER TABLE `atm_wallet_transfer` ADD COLUMN `before_wallet_balance` decimal(22, 8) NULL DEFAULT NULL COMMENT '转前余额' AFTER `from_wallet_balance`;

ALTER TABLE `atm_wallet_transfer` MODIFY COLUMN `to_wallet_balance` decimal(22, 8) NOT NULL COMMENT '转入金额' AFTER `to_wallet_address`;

INSERT INTO `sys_sysconfig` (`id`, `paramid`, `paramname`, `paramdesc`, `grouptype`) VALUES ('1604738108864090113', 'Atm_transfer_insufficient_limit_btc', 'Atm_transfer_insufficient_limit_btc', NULL, '6');
INSERT INTO `sys_sysconfig` (`id`, `paramid`, `paramname`, `paramdesc`, `grouptype`) VALUES ('1604738386879336449', 'Atm_transfer_insufficient_limit_eth', 'Atm_transfer_insufficient_limit_eth', NULL, '6');

INSERT INTO `sys_sysconfigvalue` (`id`, `paramid`, `paramvalue`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`, `companycode`) VALUES ('1604738386283745281', 'Atm_transfer_insufficient_limit_eth', '0.005', '根据此来判断，是否有足够的数量来转移ETH|USDT', 'admin', '2022-12-19 15:20:21', 'admin', '2022-12-19 15:21:57', '0');
INSERT INTO `sys_sysconfigvalue` (`id`, `paramid`, `paramvalue`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`, `companycode`) VALUES ('1604738386283745282', 'Atm_transfer_insufficient_limit_eth', '0.005', '根据此来判断，是否有足够的数量来转移ETH|USDT', 'admin', '2022-12-19 15:20:21', 'admin', '2022-12-19 15:21:57', '0A01A04');
INSERT INTO `sys_sysconfigvalue` (`id`, `paramid`, `paramvalue`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`, `companycode`) VALUES ('1604738108260110337', 'Atm_transfer_insufficient_limit_btc', '0.0005', '根据此来判断，是否有足够的数量来转移BTC', 'admin', '2022-12-19 15:19:14', 'admin', '2022-12-19 15:21:35', '0');
INSERT INTO `sys_sysconfigvalue` (`id`, `paramid`, `paramvalue`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`, `companycode`) VALUES ('1604738108260110338', 'Atm_transfer_insufficient_limit_btc', '0.0005', '根据此来判断，是否有足够的数量来转移BTC', 'admin', '2022-12-19 15:19:14', 'admin', '2022-12-19 15:21:35', '0A01A04');

