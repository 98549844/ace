-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 192.168.1.18:3307
-- Generation Time: Sep 18, 2023 at 03:38 PM
-- Server version: 5.7.40
-- PHP Version: 8.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ios`
--

-- --------------------------------------------------------

--
-- Table structure for table `code_column_config`
--

CREATE TABLE `code_column_config` (
  `column_id` bigint(20) NOT NULL COMMENT 'ID',
  `table_name` varchar(191) DEFAULT NULL,
  `column_name` varchar(255) DEFAULT NULL,
  `column_type` varchar(255) DEFAULT NULL,
  `dict_name` varchar(255) DEFAULT NULL,
  `extra` varchar(255) DEFAULT NULL,
  `form_show` bit(1) DEFAULT NULL,
  `form_type` varchar(255) DEFAULT NULL,
  `key_type` varchar(255) DEFAULT NULL,
  `list_show` bit(1) DEFAULT NULL,
  `not_null` bit(1) DEFAULT NULL,
  `query_type` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `date_annotation` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='代码生成字段信息存储' ROW_FORMAT=COMPACT;

--
-- Dumping data for table `code_column_config`
--

INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`, `date_annotation`) VALUES
(191, 'tool_qiniu_content', 'content_id', 'bigint', NULL, 'auto_increment', b'1', NULL, 'PRI', b'1', b'0', NULL, 'ID', NULL),
(192, 'tool_qiniu_content', 'bucket', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, 'Bucket 识别符', NULL),
(193, 'tool_qiniu_content', 'name', 'varchar', NULL, '', b'1', NULL, 'UNI', b'1', b'0', NULL, '文件名称', NULL),
(194, 'tool_qiniu_content', 'size', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '文件大小', NULL),
(195, 'tool_qiniu_content', 'type', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '文件类型：私有或公开', NULL),
(196, 'tool_qiniu_content', 'url', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '文件url', NULL),
(197, 'tool_qiniu_content', 'suffix', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '文件后缀', NULL),
(198, 'tool_qiniu_content', 'update_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '上传或同步的时间', NULL),
(199, 'systemctl_settings', 'mdm_soft_num', 'int', NULL, '', b'1', 'Input', '', b'0', b'0', '', ' mdm企业签第几次下载触发', ''),
(200, 'systemctl_settings', 'mdm_soft_re_count', 'int', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, '额外扣除扣除数量', NULL),
(201, 'systemctl_settings', 'mdm_super_num', 'int', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, ' mdm超级签第几次下载触发', NULL),
(202, 'systemctl_settings', 'mdm_super_re_count', 'int', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, '扣除数量', NULL),
(203, 'systemctl_settings', 'super_num', 'int', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, '超级签第几次下载触发', NULL),
(204, 'systemctl_settings', 'super_re_count', 'int', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, '扣除数量', NULL),
(205, 'systemctl_settings', 'soft_num', 'int', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, '企业签第几次下载触发', NULL),
(206, 'systemctl_settings', 'soft_re_count', 'int', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, '额外扣除扣除数量', NULL),
(207, 'systemctl_settings', 'super_total', 'int', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, '超级签所需公有池', NULL),
(208, 'systemctl_settings', 'mdm_super_total', 'int', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, 'mdm超级签共有池', NULL),
(209, 'systemctl_settings', 'soft_total', 'int', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, '企业分发公有池', NULL),
(210, 'systemctl_settings', 'mdm_soft_total', 'int', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, 'mdm企业分发公有池', NULL),
(211, 'systemctl_settings', 'web_pack_total', 'int', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, '网页打包所需公有池', NULL),
(212, 'systemctl_settings', 'one_super_total', 'int', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, '单点分发扣除次数', NULL),
(213, 'systemctl_settings', 'mdm_domain', 'varchar', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, 'mdm域名', NULL),
(214, 'systemctl_settings', 'down_domain', 'varchar', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, '下载域名', NULL),
(215, 'systemctl_settings', 'mdm_cert_id', 'int', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, 'mdm证书id', NULL),
(216, 'systemctl_settings', 'qiye_cert_id', 'int', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, '企业证书id', NULL),
(217, 'systemctl_settings', 'main_domain', 'varchar', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, '后台域名', NULL),
(218, 'systemctl_settings', 'open_reg', 'int', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, '是否开放注册', NULL),
(219, 'systemctl_settings', 'no_display', 'varchar', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, '不显示列表', NULL),
(220, 'systemctl_settings', 'ali_accessKey', 'varchar', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, '', NULL),
(221, 'systemctl_settings', 'ali_secretKey', 'varchar', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, '', NULL),
(222, 'systemctl_settings', 'ali_bucket', 'varchar', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, '', NULL),
(223, 'systemctl_settings', 'ali_endpoint', 'varchar', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, 'endpoint url 如 https://oss-cn-hangzhou.aliyuncs.com', NULL),
(224, 'systemctl_settings', 'ali_bucket_domain', 'varchar', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, 'bucket域名 如https://xxxx.oss-cn-hangzhou.aliyuncs.com/', NULL),
(225, 'systemctl_settings', 'qiniu_accessKey', 'varchar', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, '', NULL),
(226, 'systemctl_settings', 'qiniu_secretKey', 'varchar', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, '', NULL),
(227, 'systemctl_settings', 'qiniu_bucket', 'varchar', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, '', NULL),
(228, 'systemctl_settings', 'qiniu_url', 'varchar', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, '下载域名https://www.xxx.com', NULL),
(229, 'systemctl_settings', 'qiniu_reg', 'varchar', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, '#内网地区 huadong 或者 huabei 不填则不用内网', NULL),
(230, 'systemctl_settings', 'mdm_device_count', 'int', NULL, '', b'1', 'Input', '', b'0', b'0', NULL, 'mdm下载第几次扣费', NULL),
(231, 'tool_email_config', 'config_id', 'bigint', NULL, '', b'1', NULL, 'PRI', b'1', b'1', NULL, 'ID', NULL),
(232, 'tool_email_config', 'from_user', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '收件人', NULL),
(233, 'tool_email_config', 'host', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '邮件服务器SMTP地址', NULL),
(234, 'tool_email_config', 'pass', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '密码', NULL),
(235, 'tool_email_config', 'port', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '端口', NULL),
(236, 'tool_email_config', 'user', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '发件者用户名', NULL),
(237, 'ios_config', 'id', 'int', NULL, '', b'1', NULL, 'PRI', b'1', b'1', NULL, '系统设置', NULL),
(238, 'ios_config', 'code', 'varchar', NULL, '', b'1', NULL, 'UNI', b'1', b'0', NULL, '配置名称', NULL),
(239, 'ios_config', 'title', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '标题', NULL),
(240, 'ios_config', 'group_id', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '分组名称', NULL),
(241, 'ios_config', 'val', 'text', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '配置值', NULL),
(242, 'ios_config', 'type', 'tinyint', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '类型', NULL),
(243, 'ios_config', 'sort', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '排序', NULL),
(244, 'ios_config', 'value_scope', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '值的范围', NULL),
(245, 'ios_config', 'title_scope', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '对应value_scope的中文解释', NULL),
(246, 'ios_config', 'desc', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '描述', NULL),
(247, 'ios_config', 'status', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '状态', NULL),
(248, 'pack_status_ios_apk', 'id', 'int', NULL, 'auto_increment', b'1', NULL, 'PRI', b'1', b'0', NULL, 'id', NULL),
(249, 'pack_status_ios_apk', 'account', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '创建者', NULL),
(250, 'pack_status_ios_apk', 'create_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '创建时间', NULL),
(251, 'pack_status_ios_apk', 'app_name', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, 'app名称', NULL),
(252, 'pack_status_ios_apk', 'url', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '打包网址', NULL),
(253, 'pack_status_ios_apk', 'name', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '名称', NULL),
(254, 'pack_status_ios_apk', 'organization', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '机构', NULL),
(255, 'pack_status_ios_apk', 'describe', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '描述', NULL),
(256, 'pack_status_ios_apk', 'consent_message', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '同意信息', NULL),
(257, 'pack_status_ios_apk', 'icon', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '桌面图标', NULL),
(258, 'pack_status_ios_apk', 'start_icon', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '启动图路径', NULL),
(259, 'pack_status_ios_apk', 'is_remove', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '是否可移除', NULL),
(260, 'pack_status_ios_apk', 'is_variable', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '', NULL),
(261, 'pack_status_ios_apk', 'page_name', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '包名', NULL),
(262, 'pack_status_ios_apk', 'version', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '版本', NULL),
(263, 'pack_status_ios_apk', 'is_xfive', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '是否集成x5', NULL),
(264, 'pack_status_ios_apk', 'status', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '打包状态', NULL),
(265, 'pack_status_ios_apk', 'preview', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '预览地址', NULL),
(266, 'pack_status_ios_apk', 'down', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '源码下载地址', NULL),
(267, 'pack_status_ios_apk', 'expiration_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '下载过期时间', NULL),
(268, 'pack_status_ios_apk', 'root_cert', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '根证书', NULL),
(269, 'pack_status_ios_apk', 'server_cert', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '根证书', NULL),
(270, 'pack_status_ios_apk', 'key_cert', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '证书秘钥', NULL),
(271, 'pack_status_ios_apk', 'remark', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '', NULL),
(272, 'ios_domain', 'id', 'int', NULL, 'auto_increment', b'0', NULL, 'PRI', b'1', b'0', NULL, '主键', NULL),
(273, 'ios_domain', 'domains', 'varchar', NULL, '', b'1', 'Input', '', b'1', b'1', 'Like', '域名', NULL),
(274, 'ios_domain', 'domain_type', 'int', 'domain_type', '', b'1', 'Select', '', b'1', b'1', '=', '是否泛指域名还是主域名,1:主 2:泛', NULL),
(275, 'ios_domain', 'status', 'int', 'domain_status', '', b'1', 'Select', '', b'1', b'1', '=', '是否启用', NULL),
(276, 'ios_domain', 'share', 'int', 'domain_share_type', '', b'1', 'Select', '', b'1', b'1', '=', '是否私有化', NULL),
(277, 'ios_domain', 'create_by', 'varchar', NULL, '', b'0', NULL, '', b'1', b'0', NULL, '创建者', ''),
(278, 'ios_domain', 'update_by', 'varchar', NULL, '', b'0', NULL, '', b'1', b'0', NULL, '更新者', ''),
(279, 'ios_domain', 'create_time', 'datetime', NULL, '', b'0', NULL, '', b'1', b'0', NULL, '创建时间', 'CreationTimestamp'),
(280, 'ios_domain', 'update_time', 'datetime', NULL, '', b'0', NULL, '', b'1', b'0', NULL, '更新时间', 'UpdateTimestamp'),
(281, 'ios_certificate', 'id', 'bigint', NULL, 'auto_increment', b'0', NULL, 'PRI', b'1', b'0', '', '主键', NULL),
(282, 'ios_certificate', 'user_id', 'bigint', NULL, '', b'0', NULL, '', b'1', b'1', NULL, '用户ID', NULL),
(283, 'ios_certificate', 'team_id', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '', NULL),
(284, 'ios_certificate', 'iss', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, 'iss', NULL),
(285, 'ios_certificate', 'kid', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, 'kid', NULL),
(286, 'ios_certificate', 'tid', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, 'tid', NULL),
(287, 'ios_certificate', 'p12_pwd', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '证书密码', NULL),
(288, 'ios_certificate', 'p12_file', 'varchar', NULL, '', b'0', NULL, '', b'1', b'0', NULL, 'p12文件', NULL),
(289, 'ios_certificate', 'p8_file', 'varchar', NULL, '', b'0', NULL, '', b'1', b'0', NULL, 'p8文件', NULL),
(290, 'ios_certificate', 'type', 'tinyint', 'certificate_own', '', b'1', 'Radio', '', b'1', b'1', NULL, '0私有1共有', NULL),
(291, 'ios_certificate', 'status', 'tinyint', 'certificate_status', '', b'1', 'Select', '', b'1', b'1', '=', '0未启用1启用', NULL),
(292, 'ios_certificate', 'total_count', 'int', NULL, '', b'0', NULL, '', b'1', b'0', NULL, '总次数', NULL),
(293, 'ios_certificate', 'limit_count', 'int', NULL, '', b'0', NULL, '', b'1', b'0', NULL, '剩余次数', NULL),
(294, 'ios_certificate', 'mark', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '备注', NULL),
(295, 'ios_certificate', 'expire_time', 'datetime', NULL, '', b'0', NULL, '', b'1', b'0', NULL, '过期时间', NULL),
(296, 'ios_certificate', 'run_num', 'int', NULL, '', b'0', NULL, '', b'0', b'0', NULL, '证书默认启动次数', NULL),
(297, 'ios_certificate', 'create_by', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '创建者', NULL),
(298, 'ios_certificate', 'update_by', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '更新者', NULL),
(299, 'ios_certificate', 'create_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '创建时间', NULL),
(300, 'ios_certificate', 'update_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '更新时间', NULL),
(301, 'ios_certificate', 'md5', 'varchar', NULL, '', b'0', NULL, '', b'0', b'0', NULL, '证书签名值', NULL),
(302, 'ios_certificate', 'username', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '账号名', NULL),
(303, 'ios_certificate', 'identifier', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '标识', NULL),
(304, 'ios_certificate', 'cert_name', 'varchar', NULL, '', b'0', NULL, '', b'0', b'0', NULL, '证书名', NULL),
(305, 'ios_certificate', 'cert_type', 'int', 'certificate_type', '', b'1', 'Radio', '', b'1', b'0', NULL, '证书类型：0企业，1个人', NULL),
(306, 'ios_certificate', 'mobileconfig', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '描述文件', NULL),
(307, 'ios_apps', 'id', 'bigint', NULL, 'auto_increment', b'1', NULL, 'PRI', b'1', b'0', NULL, '主键', NULL),
(308, 'ios_apps', 'name', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', 'Like', '文件名称', NULL),
(309, 'ios_apps', 'way', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '0公开 1密码安装 2邀请安装', NULL),
(310, 'ios_apps', 'password', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '安装密码', NULL),
(312, 'ios_apps', 'instructions', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '版本更新说明', NULL),
(313, 'ios_apps', 'introduce', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '应用介绍', NULL),
(314, 'ios_apps', 'version', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '版本号', NULL),
(317, 'ios_apps', 'auto_page_name', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '自动更换包名 0否 1是', NULL),
(318, 'ios_apps', 'big', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '文件大小  单位MB', NULL),
(319, 'ios_apps', 'build', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '编译版本号', NULL),
(320, 'ios_apps', 'bundle', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '文件包名', NULL),
(321, 'ios_apps', 'posted_id', 'bigint', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '合并id ', NULL),
(322, 'ios_apps', 'bind_id', 'bigint', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '绑定应用id ', NULL),
(323, 'ios_apps', 'type', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '0 android 1 ios 类型', NULL),
(324, 'ios_apps', 'img', 'longtext', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '图标base64', NULL),
(325, 'ios_apps', 'url_name', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '文件原文件名', NULL),
(326, 'ios_apps', 'status', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '状态：0下架，1正常，2审核中，3已删除，4官方删除', NULL),
(327, 'ios_apps', 'download_type', 'tinyint', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '1公有，2私有 3 共有', NULL),
(328, 'ios_apps', 'only_download', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '是否开启唯一下载1', NULL),
(329, 'ios_apps', 'webclip', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '是否支持闪退助手', NULL),
(330, 'ios_apps', 'test_type', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '1内测，2企业', NULL),
(331, 'ios_apps', 'andriod_url', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '安卓下载地址', NULL),
(332, 'ios_apps', 'warning', 'bigint', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '预警下载值', NULL),
(333, 'ios_apps', 'sign_type', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '签名类型：0企业签名，1超级签名,2 MDM签名，3免签', NULL),
(334, 'ios_apps', 'message', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '消息推送：0、直接推送', NULL),
(335, 'ios_apps', 'info_url', 'text', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '简介图', NULL),
(336, 'ios_apps', 'rand_every', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '每n次', NULL),
(337, 'ios_apps', 'rand_num', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '执行n次', NULL),
(338, 'ios_apps', 'runnum', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '执行了几次判断如果相等那么进行添加假数据', NULL),
(339, 'ios_apps', 'deduction', 'decimal', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '扣除比例', NULL),
(340, 'ios_apps', 'captcha', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '是否需要验证码', NULL),
(341, 'ios_apps', 'lang', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '	下载页 面使用的语言包0为简体中文', NULL),
(342, 'ios_apps', 'user_id', 'bigint', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '用户ID', NULL),
(343, 'ios_apps', 'lock_status', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '0禁用，1启用', NULL),
(344, 'ios_apps', 'guide', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '下载引导', NULL),
(345, 'ios_apps', 'lock_expire_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '过期时间', NULL),
(346, 'ios_apps', 'create_by', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '创建者', NULL),
(347, 'ios_apps', 'update_by', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '更新者', NULL),
(348, 'ios_apps', 'create_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '创建时间', NULL),
(349, 'ios_apps', 'update_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '更新时间', NULL),
(350, 'ios_apps', 'cert_id', 'bigint', NULL, '', b'1', NULL, '', b'1', b'0', NULL, 'MDM TF 企业绑定的证书ID', NULL),
(351, 'ios_apps', 'approval_status', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '0待审核，1审核通过', NULL),
(353, 'ios_apps_posted_log', 'id', 'bigint', NULL, 'auto_increment', b'1', NULL, 'PRI', b'1', b'0', NULL, '主键', NULL),
(354, 'ios_apps_posted_log', 'uid', 'bigint', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '用户id', NULL),
(355, 'ios_apps_posted_log', 'posted_id', 'bigint', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '文件包id', NULL),
(356, 'ios_apps_posted_log', 'create_by', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '创建者', NULL),
(357, 'ios_apps_posted_log', 'update_by', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '更新者', NULL),
(358, 'ios_apps_posted_log', 'create_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '创建时间', NULL),
(359, 'ios_apps_posted_log', 'update_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '更新时间', NULL),
(360, 'ios_apps_posted_log', 'version', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '版本号', NULL),
(361, 'ios_apps_posted_log', 'big', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '文件大小  单位MB', NULL),
(362, 'ios_apps_posted_log', 'ip', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, 'IP地址', NULL),
(363, 'ios_apps', 'anti_custom_url', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '闪退助手落地页链接', NULL),
(364, 'ios_apps', 'chat_link', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '联系客服地址', NULL),
(365, 'ios_apps', 'require_os_version', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, 'app最低版本号', NULL),
(366, 'ios_apps', 'open_way', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '0：直接打开，1：桌面打开', NULL),
(367, 'ios_apps', 'comment', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '评论内容', NULL),
(368, 'ios_apps', 'comment_title', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '评论标题', NULL),
(369, 'ios_udid_list', 'id', 'bigint', NULL, 'auto_increment', b'1', NULL, 'PRI', b'1', b'0', NULL, '主键', NULL),
(370, 'ios_udid_list', 'user_id', 'bigint', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '用户ID', NULL),
(371, 'ios_udid_list', 'app_id', 'bigint', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '对应的应用ID', NULL),
(372, 'ios_udid_list', 'udid', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, 'UDID', NULL),
(373, 'ios_udid_list', 'create_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '创建时间', NULL),
(374, 'ios_udid_list', 'device', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '设备', NULL),
(375, 'ios_udid_list', 'certificate', 'bigint', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '证书ID', NULL),
(376, 'ios_udid_list', 'version', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '版本号', NULL),
(377, 'ios_udid_list', 'ip', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, 'ip', NULL),
(378, 'ios_udid_list', 'ios_version', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, 'ios版本', NULL),
(379, 'ios_udid_list', 'device_name', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '设备名', NULL),
(380, 'ios_udid_list', 'channel', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '渠道ID', NULL),
(381, 'ios_udid_list', 'remark', 'text', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '备注', NULL),
(382, 'ios_udid_list', 'device_type', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '0 android 1 ios 类型', NULL),
(383, 'ios_apps', 'size', 'bigint', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '文件大小', NULL),
(384, 'ios_apps', 'remark', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '备注', NULL),
(385, 'ios_apps', 'download_style', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '页面模板', NULL),
(386, 'ios_apps', 'auto_install_flag', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '自动安装', NULL),
(387, 'ios_apps', 'guide_type', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, 'MDM引导', NULL),
(388, 'ios_apps', 'image_type', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '应用截图类型', NULL),
(389, 'ios_udid_list', 'model', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '手机型号', NULL),
(390, 'ios_udid_list', 'imei', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '手机imei', NULL),
(391, 'ios_udid_list', 'channel_uid', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '渠道id', NULL),
(393, 'ios_udid_list', 'apple_id', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, 'apple', NULL),
(394, 'ios_udid_list', 'apple_uid', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, 'apple', NULL),
(395, 'ios_udid_list', 'xml', 'text', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '信息', NULL),
(396, 'ios_udid_list', 'err_msg', 'text', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '错误信息', NULL),
(397, 'ios_udid_list', 'status', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '0:未打包，1:正在打包,2:打包成功,3:打包失败', NULL),
(398, 'ios_udid_list', 'push_type', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '冗余推送类型，0:不推送，1:用户自有推送证书，2:超级签推送(改bundle id实现)', NULL),
(399, 'ios_udid_list', 'cost_time', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '打包耗时', NULL),
(400, 'ios_udid_list', 'jl', 'int', NULL, '', b'1', NULL, 'MUL', b'1', b'1', NULL, '是否扣量，0：非扣量，1：手动扣量，2：自动扣量', NULL),
(401, 'ios_udid_list', 'mdm_device_uid', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '设备唯一号', NULL),
(402, 'ios_udid_list', 'device_token', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '用户的推送token', NULL),
(403, 'ios_udid_list', 'unlock_token', 'text', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '锁频token', NULL),
(404, 'ios_udid_list', 'mdm_uid', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '', NULL),
(405, 'ios_udid_list', 'mdm_is_push', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, 'mdm是否推送，0：未推送，1：推送中，2：推送完成', NULL),
(406, 'ios_udid_list', 'mdm_push_magic', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, 'mdm的推送token', NULL),
(407, 'ios_udid_list', 'mdm_profile_install', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '是否安装mdm描述文件，0:未安装，1:已安装', NULL),
(408, 'ios_udid_list', 'mdm_app_install_type', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, 'app安装类型，0：不需要重装，1：删除原包重装，比如掉签了，2：直接更新包，比如用户更新版本包', NULL),
(409, 'ios_udid_list', 'sign_type', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '0：超级签，1：mdm企业证书签名', NULL),
(410, 'ios_user_extend', 'id', 'bigint', NULL, 'auto_increment', b'1', NULL, 'PRI', b'1', b'0', NULL, '主键', NULL),
(411, 'ios_user_extend', 'score', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '用户积分', NULL),
(412, 'ios_user_extend', 'coin', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '金币', NULL),
(413, 'ios_user_extend', 'balance', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '余额 不可为负数', NULL),
(414, 'ios_user_extend', 'pri_count', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '私有池', NULL),
(415, 'ios_user_extend', 'pub_count', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '共有池', NULL),
(416, 'ios_user_extend', 'mdm_balance', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, 'MDM余额 不可为负数', NULL),
(417, 'ios_user_extend', 'tf_balance', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, 'tf余额', NULL),
(418, 'ios_user_extend', 'super_balance', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, 'tf余额', NULL),
(419, 'ios_user_extend', 'enterprise_balance', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, 'en余额', NULL),
(420, 'ios_user_extend', 'user_id', 'bigint', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '用户ID', NULL),
(421, 'ios_user_extend', 'pid', 'bigint', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '绑定代理', NULL),
(422, 'ios_user_extend', 'from_domain', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '所属域名，0为总站用户，其他为代理站用户', NULL),
(423, 'ios_user_extend', 'telegramid', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, 'Telegram账号ID', NULL),
(424, 'ios_user_extend', 'extend_text', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '扩展预留', NULL),
(425, 'ios_user_extend', 'mark', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '备注', NULL),
(426, 'ios_user_charge_log', 'id', 'bigint', NULL, 'auto_increment', b'1', NULL, 'PRI', b'1', b'0', NULL, '主键', NULL),
(427, 'ios_user_charge_log', 'user_id', 'bigint', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '用户 id', NULL),
(428, 'ios_user_charge_log', 'puid', 'bigint', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '进行充值的用户（上线）', NULL),
(429, 'ios_user_charge_log', 'num', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '次数', NULL),
(430, 'ios_user_charge_log', 'type', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '1公有，2私有', NULL),
(431, 'ios_user_charge_log', 'sign_type', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '签名类型', NULL),
(432, 'ios_user_charge_log', 'create_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '创建时间', NULL),
(433, 'ios_user_charge_log', 'add_type', 'tinyint', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '0手动，1自动，2上线操作', NULL),
(434, 'ios_user_charge_log', 'is_add', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '0扣减操作，1增加操作', NULL),
(435, 'ios_user_charge_log', 'msg', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '操作信息备注', NULL),
(436, 'ios_apps', 'en_cert_id', 'bigint', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '企业证书绑定', NULL),
(437, 'ios_apps', 'mdm_cert_id', 'bigint', NULL, '', b'1', NULL, '', b'1', b'0', NULL, 'MDM模式绑定的证书', NULL),
(438, 'ios_apps', 'mdm_sign_status', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, 'mdm证书签名状态', NULL),
(439, 'ios_apps', 'en_sign_status', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '企业证书签名状态', NULL),
(440, 'ios_apps', 'backup_url', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '备用下载域名', NULL),
(441, 'ios_apps', 'download_url', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '下载域名', NULL),
(442, 'ios_apps', 'main_short_prefix', 'varchar', NULL, '', b'1', NULL, 'UNI', b'1', b'0', NULL, '短地址前缀', NULL),
(443, 'ios_apps', 'sub_short_prefix', 'varchar', NULL, '', b'1', NULL, 'UNI', b'1', b'0', NULL, '备用前缀', NULL),
(444, 'ios_device_info', 'id', 'bigint', NULL, 'auto_increment', b'1', NULL, 'PRI', b'1', b'0', NULL, '主键', NULL),
(445, 'ios_device_info', 'device_own', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '0：MDM 1:超级 2：TF 3：企业', NULL),
(446, 'ios_device_info', 'udid', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, 'UDID', NULL),
(447, 'ios_device_info', 'mdm_device_uid', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '设备唯一号', NULL),
(448, 'ios_device_info', 'device_token', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '用户的推送token', NULL),
(449, 'ios_device_info', 'info', 'text', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '手机信息', NULL),
(450, 'ios_device_info', 'ios_version', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, 'ios版本', NULL),
(451, 'ios_device_info', 'os_version', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '系统版本', NULL),
(452, 'ios_device_info', 'device_name', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '设备名', NULL),
(453, 'ios_device_info', 'model', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '手机型号', NULL),
(454, 'ios_device_info', 'imei', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '手机imei', NULL),
(455, 'ios_device_info', 'channel_uid', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '渠道id', NULL),
(456, 'ios_device_info', 'serialnum', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '手机序列号', NULL),
(457, 'ios_device_info', 'mdm_profile_install', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '是否安装mdm描述文件，0:未安装，1:已安装', NULL),
(458, 'ios_device_info', 'xml', 'text', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '信息', NULL),
(459, 'ios_device_info', 'remark', 'text', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '备注', NULL),
(460, 'ios_device_info', 'create_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '创建时间', NULL),
(461, 'ios_device_info', 'mdm_push_magic', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, 'mdm的推送token', NULL),
(462, 'ios_device_info', 'unlock_token', 'text', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '锁频token', NULL),
(463, 'ios_apps', 'app_icon', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, 'app图标地址', NULL),
(464, 'ios_apps_device', 'id', 'bigint', NULL, 'auto_increment', b'1', NULL, 'PRI', b'1', b'0', NULL, '主键', NULL),
(465, 'ios_apps_device', 'app_id', 'bigint', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '对应的应用ID', NULL),
(466, 'ios_apps_device', 'uid', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '用户标识ID', NULL),
(467, 'ios_apps_device', 'build_version', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '编译版本', NULL),
(468, 'ios_apps_device', 'create_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '创建时间', NULL),
(469, 'ios_apps_device', 'ip', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, 'IP地址', NULL),
(470, 'ios_apps_device', 'xml', 'text', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '信息', NULL),
(471, 'ios_apps', 'is_open_android', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '安卓开关', NULL),
(472, 'ios_udid_list', 'in_day', 'date', NULL, '', b'0', NULL, 'MUL', b'0', b'0', NULL, '添加时间', NULL),
(473, 'ios_device_info', 'apsp', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, 'apsp', NULL),
(474, 'ios_apps', 'driver_type', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '存储类型', NULL),
(475, 'ios_apps', 'update_flag', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '更新标识', NULL),
(476, 'ios_apps', 'oss_update_datetime', 'timestamp', NULL, '', b'1', NULL, '', b'1', b'0', NULL, 'oss更新时间', NULL),
(477, 'ios_apps', 'sign_datetime', 'timestamp', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '最后签名时间', NULL),
(478, 'ios_apps', 'url_type', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '链接类型', NULL),
(479, 'ios_apps', 'dynamic_billing', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '动态计费', NULL),
(480, 'ios_apps', 'uid', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, 'uuid', NULL),
(481, 'ios_apps', 'third_id', 'varchar', NULL, '', b'1', NULL, 'MUL', b'1', b'0', NULL, '三方平台Id', NULL),
(482, 'ios_apps', 'third_url', 'text', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '三方下载地址', NULL),
(483, 'ios_apps', 'third_platform', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '三方平台', NULL),
(484, 'ios_apps', 'third_use', 'tinyint', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '是否使用三方接口', NULL),
(485, 'ios_apps', 'en_sign_file', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '企业文件名', NULL),
(486, 'ios_apps', 'mdm_sign_file', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, 'MDM文件名', NULL),
(487, 'ios_apps', 'third_status', 'tinyint', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '三方平台状态', NULL),
(488, 'ios_api_certs', 'id', 'bigint', NULL, 'auto_increment', b'0', NULL, 'PRI', b'1', b'0', NULL, '', NULL),
(489, 'ios_api_certs', 'code', 'varchar', NULL, '', b'1', 'Input', '', b'1', b'1', NULL, '编码', NULL),
(490, 'ios_api_certs', 'name', 'varchar', NULL, '', b'1', 'Input', '', b'1', b'1', NULL, '显示名称', NULL),
(491, 'ios_api_certs', 'status', 'int', 'certificate_status', '', b'1', 'Select', '', b'1', b'1', NULL, '状态', NULL),
(492, 'ios_api_certs', 'type', 'int', NULL, '', b'1', 'Select', '', b'0', b'1', NULL, '接口类型', NULL),
(493, 'ios_user_extend', 'min_charge', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '最低消', NULL),
(494, 'ios_apps_extend', 'app_id', 'bigint', NULL, '', b'1', NULL, 'PRI', b'1', b'1', NULL, 'appID', NULL),
(495, 'ios_apps_extend', 'minimum_status', 'tinyint', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '低消状态', NULL),
(496, 'ios_apps_extend', 'minimum', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '最低值', NULL),
(497, 'ios_apps_extend', 'create_by', 'timestamp', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '创建时间', NULL),
(498, 'ios_apps_extend', 'update_by', 'timestamp', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '更新时间', NULL),
(499, 'ios_apps_extend', 'minimum_time', 'timestamp', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '扣除时间', NULL),
(500, 'ios_domain_bind', 'id', 'bigint', NULL, 'auto_increment', b'1', NULL, 'PRI', b'1', b'0', NULL, '主键', NULL),
(501, 'ios_domain_bind', 'domain', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '域名', NULL),
(502, 'ios_domain_bind', 'title', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '网站名', NULL),
(503, 'ios_domain_bind', 'logo', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, 'logo地址', NULL),
(504, 'ios_domain_bind', 'more', 'text', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '更多参数', NULL),
(505, 'ios_domain_bind', 'is_true', 'tinyint', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '是否可以访问', NULL),
(506, 'ios_domain_bind', 'user_id', 'bigint', NULL, '', b'1', NULL, '', b'1', b'1', '=', '管理员账号id（管理员账号主站代理站通用）', NULL),
(507, 'ios_domain_bind', 'qq_img', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '', NULL),
(508, 'ios_domain_bind', 'wechat_img', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '', NULL),
(509, 'ios_apps', 'anti_custom_logo', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '闪退助手logo', NULL),
(510, 'ios_apps', 'anti_custom_title', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '闪退助手title', NULL),
(511, 'ios_domain', 'link_type', 'int', 'url_type', '', b'1', 'Select', '', b'1', b'1', '=', '链接类型默认为0', 'UpdateTimestamp');

-- --------------------------------------------------------

--
-- Table structure for table `code_gen_config`
--

CREATE TABLE `code_gen_config` (
  `config_id` bigint(20) NOT NULL COMMENT 'ID',
  `table_name` varchar(255) DEFAULT NULL COMMENT '表名',
  `author` varchar(255) DEFAULT NULL COMMENT '作者',
  `cover` bit(1) DEFAULT NULL COMMENT '是否覆盖',
  `module_name` varchar(255) DEFAULT NULL COMMENT '模块名称',
  `pack` varchar(255) DEFAULT NULL COMMENT '至于哪个包下',
  `path` varchar(255) DEFAULT NULL COMMENT '前端代码生成的路径',
  `api_path` varchar(255) DEFAULT NULL COMMENT '前端Api文件路径',
  `prefix` varchar(255) DEFAULT NULL COMMENT '表前缀',
  `api_alias` varchar(255) DEFAULT NULL COMMENT '接口名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='代码生成器配置' ROW_FORMAT=COMPACT;

--
-- Dumping data for table `code_gen_config`
--

INSERT INTO `code_gen_config` (`config_id`, `table_name`, `author`, `cover`, `module_name`, `pack`, `path`, `api_path`, `prefix`, `api_alias`) VALUES
(7, 'systemctl_settings', 'Zheng Jie', b'1', 'ios-developer', 'me.zhengjie.settings', '~/root/views', '~/root/views/', NULL, '系统配置'),
(8, 'ios_config', 'Zhe', b'1', 'ios-developer', 'me.zhengjie', '/root/views', '/root/views/', NULL, '系统设置'),
(9, 'ios_domain', 'Zhe', b'1', 'ios-developer', 'me.zhengjie', '/root/views', '/root/views/', NULL, '域名配置'),
(10, 'ios_certificate', 'Zhe', b'1', 'ios-developer', 'me.zhengjie', '/root/views', '/root/views/', NULL, '证书管理'),
(11, 'ios_apps', 'Zhe', b'1', 'ios-developer', 'me.zhengjie', '/root/views', '/root/views/', NULL, 'App管理'),
(12, 'ios_apps_posted_log', 'Zhe', b'0', 'ios-developer', 'me.zhengjie', '/root/views', '/root/views/', NULL, '上传日志'),
(13, 'ios_udid_list', 'Zhe', b'0', 'ios-developer', 'me.zhengjie', '/root/views', '/root/views/', NULL, 'IOS'),
(14, 'ios_user_extend', 'Zhe', b'0', 'ios-developer', 'me.zhengjie', '/root/views', '/root/views/', NULL, '用户信息扩展表'),
(15, 'ios_user_charge_log', 'Zhe', b'1', 'ios-developer', 'me.zhengjie', '/root/views', '/root/views/', NULL, '充值记录表'),
(16, 'ios_device_info', 'Zhe', b'1', 'ios-developer', 'me.zhengjie', '/root/views', '/root/views/', NULL, 'MDM设备信息存储'),
(17, 'ios_apps_device', 'Zhe', b'1', 'ios-developer', 'me.zhengjie', '/root/views', '/root/views/', NULL, 'App启动设备列表'),
(18, 'ios_api_certs', 'Zhe', b'0', 'ios-developer', 'me.zhengjie', '/root/views', '/root/views/', NULL, 'iosApiCertificate'),
(19, 'ios_apps_extend', 'Zhe', b'0', 'ios-developer', 'me.zhengjie', '/root/views', '/root/views/', NULL, '应用扩展信息表'),
(20, 'ios_domain_bind', 'Zhe', b'0', 'ios-developer', 'me.zhengjie', '/root/views', '/root/views/', NULL, 'iosDomainBind');

-- --------------------------------------------------------

--
-- Table structure for table `device_command_task`
--

CREATE TABLE `device_command_task` (
  `task_id` varchar(64) NOT NULL COMMENT 'id',
  `device_id` varchar(64) NOT NULL COMMENT '设备id',
  `cmd` varchar(255) NOT NULL COMMENT '命令 ',
  `exec_result` text NOT NULL COMMENT '执行返回结果',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `exec_time` datetime NOT NULL COMMENT '执行时间',
  `result_time` datetime NOT NULL COMMENT '返回时间',
  `task_status` int(1) NOT NULL COMMENT '0 任务_等待_执行 1 任务_唤醒_命令_已发送 2 任务_命令_已发送 3任务_命令_执行成功 4 任务_命令_执行失败',
  `push_count` int(2) NOT NULL COMMENT '唤醒次数 如果在指定时间没有回应则会重试 ',
  `exec_result_status` varchar(255) NOT NULL COMMENT '执行返回状态码',
  `cmd_append` text NOT NULL COMMENT 'cmd命令的其他参数 json 比如安装app就有其他参数',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `cert_id` varchar(64) NOT NULL COMMENT '证书id',
  `udid` varchar(64) NOT NULL COMMENT '设备id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='执行命令任务' ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Table structure for table `device_info`
--

CREATE TABLE `device_info` (
  `device_id` varchar(64) NOT NULL COMMENT '设备唯一id',
  `cert_id` varchar(64) DEFAULT '' COMMENT '证书id',
  `token` varchar(1000) DEFAULT '' COMMENT '设备token',
  `udid` varchar(255) DEFAULT '' COMMENT '设备uuid',
  `unlock_token` text COMMENT '给设备发送命令配置文件里需要用到',
  `magic` varchar(255) DEFAULT '' COMMENT '向apns服务器唤醒设备的时候需要',
  `topic` varchar(255) DEFAULT '' COMMENT '证书的topic',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` varchar(255) DEFAULT '' COMMENT '设备状态 已卸载CheckOut 注册中Authenticate 更新TokenUpdate  ',
  `remark` varchar(255) DEFAULT '' COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='注册的设备信息' ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Table structure for table `device_status`
--

CREATE TABLE `device_status` (
  `device_id` varchar(64) NOT NULL COMMENT '设备id',
  `status` int(1) NOT NULL COMMENT '0不可控 1可控',
  `udid` varchar(64) NOT NULL COMMENT '设备udid',
  `cert_id` varchar(64) NOT NULL COMMENT '证书id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Table structure for table `distribute`
--

CREATE TABLE `distribute` (
  `id` int(10) UNSIGNED NOT NULL,
  `account` varchar(255) NOT NULL,
  `app_name` varchar(255) NOT NULL,
  `page_name` varchar(255) NOT NULL,
  `version` varchar(255) NOT NULL,
  `icon` varchar(255) NOT NULL,
  `ipa` varchar(255) DEFAULT NULL,
  `apk` varchar(255) DEFAULT NULL,
  `url` text,
  `create_time` datetime NOT NULL,
  `introduce` varchar(255) DEFAULT NULL,
  `images` varchar(255) DEFAULT NULL,
  `down_code` int(1) NOT NULL COMMENT '下载码,0不启用,1启用',
  `buy_down_code_url` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Table structure for table `ios_api_certs`
--

CREATE TABLE `ios_api_certs` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '编码',
  `name` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '显示名称',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `type` int(11) DEFAULT NULL COMMENT '类型'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='api证书列表';

--
-- Dumping data for table `ios_api_certs`
--

INSERT INTO `ios_api_certs` (`id`, `code`, `name`, `status`, `type`) VALUES
(1, 'zmasdf', '123asdfasdf', 1, 90000),
(2, 'ss', 'ss', 1, 90000);

-- --------------------------------------------------------

--
-- Table structure for table `ios_apps`
--

CREATE TABLE `ios_apps` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '文件名称',
  `way` int(2) DEFAULT '0' COMMENT '0公开 1密码安装 2邀请安装',
  `password` varchar(255) DEFAULT NULL COMMENT '安装密码',
  `open_way` int(11) NOT NULL DEFAULT '0' COMMENT '0：直接打开，1：桌面打开',
  `instructions` varchar(255) DEFAULT NULL COMMENT '版本更新说明',
  `introduce` varchar(255) DEFAULT NULL COMMENT '应用介绍',
  `version` varchar(255) DEFAULT NULL COMMENT '版本号',
  `chat_link` varchar(255) DEFAULT NULL COMMENT '联系客服地址',
  `require_os_version` varchar(255) DEFAULT NULL COMMENT 'app最低版本号',
  `auto_page_name` int(1) DEFAULT '0' COMMENT '自动更换包名 0否 1是',
  `big` varchar(255) DEFAULT NULL COMMENT '文件大小  单位MB',
  `size` bigint(20) DEFAULT NULL COMMENT '文件大小',
  `build` varchar(255) DEFAULT NULL COMMENT '编译版本号',
  `bundle` varchar(255) DEFAULT NULL COMMENT '文件包名',
  `posted_id` bigint(20) DEFAULT NULL COMMENT '合并id ',
  `bind_id` bigint(20) DEFAULT NULL COMMENT '绑定应用id ',
  `type` varchar(100) DEFAULT NULL COMMENT '0 android 1 ios 类型',
  `img` longtext COMMENT '图标base64',
  `url_name` varchar(100) DEFAULT NULL COMMENT '文件原文件名',
  `status` int(2) DEFAULT '1' COMMENT '状态：0下架，1正常，2审核中，3已删除，4官方删除',
  `approval_status` int(2) DEFAULT '0' COMMENT '0待审核，1审核通过',
  `download_type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1公有，2私有 3 共有',
  `only_download` int(1) NOT NULL DEFAULT '0' COMMENT '是否开启唯一下载1',
  `webclip` int(1) NOT NULL DEFAULT '0' COMMENT '是否支持闪退助手',
  `anti_custom_url` varchar(255) DEFAULT NULL COMMENT '闪退助手落地页链接',
  `test_type` int(1) NOT NULL DEFAULT '0' COMMENT '1内测，2企业',
  `andriod_url` varchar(255) DEFAULT NULL COMMENT '安卓下载地址',
  `warning` bigint(11) NOT NULL DEFAULT '0' COMMENT '预警下载值',
  `sign_type` int(255) DEFAULT '0' COMMENT '签名类型：0MDM，1超级签名,2 混签，3免签',
  `message` int(11) NOT NULL DEFAULT '0' COMMENT '消息推送：0、直接推送',
  `info_url` text NOT NULL COMMENT '简介图',
  `rand_every` int(11) NOT NULL DEFAULT '0' COMMENT '每n次',
  `rand_num` int(11) NOT NULL DEFAULT '0' COMMENT '执行n次',
  `runnum` int(11) NOT NULL COMMENT '执行了几次判断如果相等那么进行添加假数据',
  `deduction` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '扣除比例',
  `captcha` int(11) DEFAULT '0' COMMENT '是否需要验证码',
  `lang` int(11) NOT NULL DEFAULT '0' COMMENT '	下载页 面使用的语言包0为简体中文',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `lock_status` int(2) DEFAULT '0' COMMENT '0禁用，1启用',
  `guide` int(2) DEFAULT '0' COMMENT '下载引导',
  `cert_id` bigint(20) DEFAULT NULL COMMENT '其他证书ID',
  `en_cert_id` bigint(20) DEFAULT NULL COMMENT '企业绑定的证书ID',
  `mdm_cert_id` bigint(20) DEFAULT NULL COMMENT 'MDM绑定的证书ID',
  `lock_expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `comment` varchar(500) NOT NULL COMMENT '评论内容',
  `comment_title` varchar(255) NOT NULL COMMENT '评论标题',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `download_style` varchar(255) DEFAULT NULL COMMENT '页面模板',
  `auto_install_flag` varchar(255) DEFAULT NULL COMMENT '自动安装',
  `guide_type` varchar(255) DEFAULT NULL COMMENT 'MDM引导',
  `image_type` varchar(255) DEFAULT NULL COMMENT '应用截图类型',
  `mdm_sign_status` int(2) DEFAULT '0' COMMENT 'mdm证书签名状态',
  `en_sign_status` int(2) DEFAULT '0' COMMENT '企业证书签名状态',
  `backup_url` varchar(500) DEFAULT NULL COMMENT '备用下载域名',
  `download_url` varchar(500) DEFAULT NULL COMMENT '下载域名',
  `main_short_prefix` varchar(20) DEFAULT NULL COMMENT '短地址前缀',
  `sub_short_prefix` varchar(20) DEFAULT NULL COMMENT '备用前缀',
  `app_icon` varchar(500) DEFAULT NULL COMMENT 'app图标地址',
  `is_open_android` int(11) DEFAULT '0' COMMENT '安卓开关',
  `driver_type` int(11) DEFAULT '1' COMMENT '存储类型',
  `update_flag` int(11) DEFAULT '1' COMMENT '更新标识',
  `oss_update_datetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'oss更新时间',
  `sign_datetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后签名时间',
  `url_type` int(11) DEFAULT '0' COMMENT '链接类型',
  `dynamic_billing` int(11) DEFAULT '0' COMMENT '动态计费',
  `uid` varchar(64) NOT NULL COMMENT 'uuid',
  `third_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '三方平台Id',
  `third_url` text COMMENT '三方下载地址',
  `third_platform` int(11) DEFAULT '0' COMMENT '三方平台',
  `third_use` tinyint(1) DEFAULT '0' COMMENT '是否使用三方接口',
  `en_sign_file` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '企业文件名',
  `mdm_sign_file` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT 'MDM文件名',
  `third_status` tinyint(1) DEFAULT '0' COMMENT '三方平台状态',
  `anti_custom_logo` varchar(500) DEFAULT NULL COMMENT '闪退助手logo',
  `anti_custom_title` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '闪退助手title'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='应用列表' ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `ios_apps`
--

-- --------------------------------------------------------

--
-- Table structure for table `ios_apps_device`
--

CREATE TABLE `ios_apps_device` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `app_id` bigint(20) NOT NULL COMMENT '对应的应用ID',
  `uid` varchar(64) DEFAULT NULL COMMENT '用户标识ID',
  `build_version` varchar(32) DEFAULT NULL COMMENT '编译版本',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ip` varchar(255) DEFAULT NULL COMMENT 'IP地址',
  `xml` text COMMENT '信息'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='App启动设备列表' ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `ios_apps_device`
--

-- --------------------------------------------------------

--
-- Table structure for table `ios_apps_extend`
--

CREATE TABLE `ios_apps_extend` (
  `app_id` bigint(20) NOT NULL COMMENT 'appID',
  `minimum_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '低消状态',
  `minimum` int(11) NOT NULL DEFAULT '0' COMMENT '最低值',
  `create_by` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `minimum_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '扣除时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ios_apps_extend`
--

INSERT INTO `ios_apps_extend` (`app_id`, `minimum_status`, `minimum`, `create_by`, `update_by`, `minimum_time`) VALUES
(1, 1, 777777, '2023-08-01 15:50:57', '2023-07-31 22:23:45', '2023-08-03 18:50:50');

-- --------------------------------------------------------

--
-- Table structure for table `ios_apps_posted_log`
--

CREATE TABLE `ios_apps_posted_log` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `uid` bigint(20) NOT NULL COMMENT '用户id',
  `posted_id` bigint(20) DEFAULT NULL COMMENT '文件包id',
  `ip` varchar(255) DEFAULT NULL COMMENT 'IP地址',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` varchar(255) DEFAULT NULL COMMENT '版本号',
  `big` varchar(255) NOT NULL COMMENT '文件大小  单位MB'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `ios_apps_posted_log`
--

-- --------------------------------------------------------

--
-- Table structure for table `ios_certificate`
--

CREATE TABLE `ios_certificate` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `team_id` varchar(255) DEFAULT NULL,
  `iss` varchar(255) DEFAULT NULL COMMENT 'iss',
  `kid` varchar(255) DEFAULT NULL COMMENT 'kid',
  `tid` varchar(255) DEFAULT NULL COMMENT 'tid',
  `p12_pwd` varchar(255) DEFAULT NULL COMMENT '证书密码',
  `p12_file` varchar(255) DEFAULT NULL COMMENT 'p12文件',
  `p8_file` varchar(255) DEFAULT NULL COMMENT 'p8文件',
  `mobileconfig` varchar(255) DEFAULT NULL COMMENT '描述文件',
  `type` tinyint(1) NOT NULL COMMENT '0私有1共有',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '0未启用1启用',
  `total_count` int(11) DEFAULT '0' COMMENT '总次数',
  `limit_count` int(11) DEFAULT '0' COMMENT '剩余次数',
  `mark` varchar(50) DEFAULT NULL COMMENT '备注',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `run_num` int(11) DEFAULT '0' COMMENT '证书默认启动次数',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `md5` varchar(64) NOT NULL COMMENT '证书签名值',
  `username` varchar(255) DEFAULT NULL COMMENT '账号名',
  `identifier` varchar(255) DEFAULT NULL COMMENT '标识',
  `cert_name` varchar(500) DEFAULT NULL COMMENT '证书名',
  `cert_type` int(2) DEFAULT '0' COMMENT '证书类型：0企业，1个人'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='证书列表' ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `ios_certificate`
--
-- --------------------------------------------------------

--
-- Table structure for table `ios_config`
--

CREATE TABLE `ios_config` (
  `id` int(11) NOT NULL COMMENT '系统设置',
  `code` varchar(255) DEFAULT NULL COMMENT '配置名称',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `group_id` varchar(50) DEFAULT NULL COMMENT '分组名称',
  `val` text COMMENT '配置值',
  `type` tinyint(1) NOT NULL COMMENT '类型',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `value_scope` varchar(50) DEFAULT NULL COMMENT '值的范围',
  `title_scope` varchar(255) DEFAULT NULL COMMENT '对应value_scope的中文解释',
  `desc` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` int(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `ios_config`
--

INSERT INTO `ios_config` (`id`, `code`, `title`, `group_id`, `val`, `type`, `sort`, `value_scope`, `title_scope`, `desc`, `status`) VALUES
(1, 'ali_save_access_key', '阿里云存储key', 'oss', 'xxx', 0, 0, NULL, NULL, NULL, 1),
(2, 'ali_save_access_secret', '阿里云存储Secret', 'oss', 'xxx', 0, 0, NULL, NULL, NULL, 1),
(3, 'ali_save_endpoint', '阿里云存储上传地址', 'oss', 'oss-accelerate.aliyuncs.com', 0, 0, NULL, NULL, NULL, 1),
(4, 'ali_save_down', '阿里云存储下载地址', 'oss', 'oss-accelerate.aliyuncs.com', 0, 0, NULL, NULL, NULL, 1),
(5, 'ali_save_bucket', '阿里云存储空间', 'oss', 'xxx', 0, 0, NULL, NULL, NULL, 1),
(6, 'basic_main_domain', '后台管理域名', 'basic', 'xxx.com', 0, 0, NULL, NULL, NULL, 1),
(9, 'site_name', '网站名称', 'basic', 'TEst', 0, 0, NULL, NULL, NULL, 1),
(10, 'site_logo', '网站标志', 'basic', 'xxx', 0, 0, NULL, NULL, NULL, 1),
(11, 'ali_save_rolearn', '阿里云存储STS角色', 'oss', 'xxx', 0, 0, NULL, NULL, NULL, 1),
(12, 'ali_save_callbackUrl', '阿里云存储回调处理地址', 'oss', 'xxx', 0, 0, NULL, NULL, NULL, 1),
(13, 'mdm_push_cert', 'MDM推送证书', 'mdmpush', 'xxx', 0, NULL, NULL, NULL, NULL, 1),
(14, 'mdm_push_cert_pwd', 'MDM推送证书PWD', 'mdmpush', '1', 0, NULL, NULL, NULL, NULL, 1),
(15, 'mdm_push_cert_apsp', 'MDM推送APSP', 'mdmpush', 'xxx', 0, NULL, NULL, NULL, NULL, 1),
(16, 'aws_save_access_key', 'AWS存储key', 'oss', 'xxx', 0, NULL, NULL, NULL, NULL, 1),
(17, 'aws_save_access_secret', 'AWS存储Secret', 'oss', 'JW+xxx', 0, NULL, NULL, NULL, NULL, 1),
(18, 'aws_save_bucket', 'AWS存储空间', 'oss', 'xxx', 0, NULL, NULL, NULL, NULL, 1),
(19, 'aws_save_endpoint', 'AWS存储上传地址', 'oss', 'amazonaws.com', 0, NULL, NULL, NULL, NULL, 1),
(20, 'aws_save_down', 'AWS存储下载地址', 'oss', 'amazonaws.com', 0, NULL, NULL, NULL, NULL, 1),
(21, 'aws_save_area', 'AWS区域', 'oss', 'us-east-2', 0, NULL, NULL, NULL, NULL, 1),
(22, 'driver_down_endpoint', '下载服务器', 'oss', '2', 0, NULL, NULL, NULL, NULL, 1),
(23, 'driver_img_endpoint', '图片服务器', 'oss', '2', 0, NULL, NULL, NULL, NULL, 1),
(24, 'driver_upload_endpoint', '上传服务器', 'oss', '2', 0, NULL, NULL, NULL, NULL, 1),
(25, 'driver_downapk_endpoint', 'APK下载服务器', 'oss', '2', 0, NULL, NULL, NULL, NULL, 1),
(26, 'minio_save_access_key', 'Minio存储key', 'oss', 'xxx', 0, NULL, NULL, NULL, NULL, 1),
(27, 'minio_save_access_secret', 'Minio存储Secret', 'oss', 'xxx', 0, NULL, NULL, NULL, NULL, 1),
(28, 'minio_save_endpoint', 'Minio存储上传地址', 'oss', 'xxx', 0, NULL, NULL, NULL, NULL, 1),
(29, 'minio_save_down', 'Minio存储下载地址', 'oss', 'xxx', 0, NULL, NULL, NULL, NULL, 1),
(30, 'minio_save_area', 'Minio区域', 'oss', 'us-east-1', 0, NULL, NULL, NULL, NULL, 1),
(31, 'driver_captcha_endpoint', 'Captcha服务器', 'oss', '2', 0, NULL, NULL, NULL, NULL, 1),
(32, 'minio_save_bucket', 'Minio存储空间', 'oss', 'xxx', 0, NULL, NULL, NULL, NULL, 1),
(33, 'basic_main_filter', 'ID过滤器', 'basic', '2', 0, NULL, NULL, NULL, NULL, 1),
(36, 'telegram_bot_username', '机器人账号', 'telegram', 'xxx', 0, NULL, NULL, NULL, NULL, 1),
(37, 'telegram_bot_groupId', '机器人管理群组', 'telegram', 'xxx', 0, NULL, NULL, NULL, NULL, 1),
(38, 'telegram_bot_token', '机器人密钥', 'telegram', 'xxx', 0, NULL, NULL, NULL, NULL, 1),
(39, 'telegram_bot_manageId', '机器人管理员', 'telegram', '234', 0, NULL, NULL, NULL, NULL, 1),
(40, 'basic_main_downdomain', 'plist域名', 'basic', 'xxx', 0, NULL, NULL, NULL, NULL, 1),
(41, 'basic_main_dylib_status', '动态库独立域名', 'basic', '0', 0, NULL, NULL, NULL, NULL, 1),
(42, 'basic_main_dylib_domain', '动态库域名', 'basic', 'xxx', 0, NULL, NULL, NULL, NULL, 1),
(43, 'basic_main_authuser', '授权下载账户', 'basic', ',,@#xxx', 0, NULL, NULL, NULL, NULL, 1),
(44, 'minio_save_cdn', 'CDN选择', 'oss', '0', 0, NULL, NULL, NULL, NULL, 1),
(45, 'minio_save_limitip', '限制IP', 'oss', '0', 0, NULL, NULL, NULL, NULL, 1),
(46, 'telegram_bot_baseurl', '机器人API', 'telegram', 'xxx', 0, NULL, NULL, NULL, NULL, 1),
(47, 'telegram_bot_hookurl', '机器人HookUrl', 'telegram', 'xxx', 0, NULL, NULL, NULL, NULL, 1),
(48, 'telegram_bot_deploy_status', '机器人所属类型', 'telegram', '1', 0, NULL, NULL, NULL, NULL, 1),
(49, 'web_setting_alerturl', '提示跳转的网址', 'web', 'https://bing.com', 0, NULL, NULL, NULL, NULL, 1),
(50, 'web_setting_alertmsg', '提示信息', 'web', '提示信息', 0, NULL, NULL, NULL, NULL, 1),
(51, 'minio_source', 'Miniosource', 'oss', 'xxx', 0, NULL, NULL, NULL, NULL, 1),
(52, 'third_setting_domain_appid', '域名注册appId', 'third', 'xxx', 0, NULL, NULL, NULL, NULL, 1),
(53, 'third_setting_domain_appKey', '域名注册appKey', 'third', 'xxx', 0, NULL, NULL, NULL, NULL, 1),
(54, 'third_setting_ali_appid', '阿里appId', 'third', 'xxx', 0, NULL, NULL, NULL, NULL, 1),
(55, 'third_setting_ali_appKey', '阿里appId', 'third', 'xxx', 0, NULL, NULL, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `ios_device_info`
--

CREATE TABLE `ios_device_info` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `device_own` int(2) NOT NULL DEFAULT '0' COMMENT '0：MDM 1:超级 2：TF 3：企业',
  `udid` varchar(100) NOT NULL COMMENT 'UDID',
  `mdm_device_uid` varchar(64) DEFAULT NULL COMMENT '设备唯一号',
  `device_token` varchar(64) DEFAULT NULL COMMENT '用户的推送token',
  `info` text COMMENT '手机信息',
  `ios_version` varchar(32) DEFAULT NULL COMMENT 'ios版本',
  `os_version` varchar(32) DEFAULT NULL COMMENT '系统版本',
  `device_name` varchar(32) DEFAULT NULL COMMENT '设备名',
  `model` varchar(64) DEFAULT NULL COMMENT '手机型号',
  `imei` varchar(64) DEFAULT NULL COMMENT '手机imei',
  `channel_uid` varchar(16) DEFAULT NULL COMMENT '渠道id',
  `serialnum` varchar(64) DEFAULT NULL COMMENT '手机序列号',
  `mdm_profile_install` int(11) NOT NULL DEFAULT '0' COMMENT '是否安装mdm描述文件，0:未安装，1:已安装',
  `xml` text COMMENT '信息',
  `remark` text COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mdm_push_magic` varchar(256) DEFAULT NULL COMMENT 'mdm的推送token',
  `unlock_token` text COMMENT '锁频token',
  `apsp` varchar(64) DEFAULT NULL COMMENT 'apsp'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='MDM 设备信息表' ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Table structure for table `ios_domain`
--

CREATE TABLE `ios_domain` (
  `id` int(11) NOT NULL COMMENT '主键',
  `domains` varchar(500) NOT NULL COMMENT '域名',
  `domain_type` int(11) NOT NULL COMMENT '是否泛指域名还是主域名,1:主 2:泛',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '是否启用',
  `share` int(11) NOT NULL DEFAULT '0' COMMENT '是否私有化',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `link_type` int(11) NOT NULL DEFAULT '0' COMMENT '链接类型默认为0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='域名列表' ROW_FORMAT=COMPACT;

--
-- Dumping data for table `ios_domain`
--

-- --------------------------------------------------------

--
-- Table structure for table `ios_domain_bind`
--

CREATE TABLE `ios_domain_bind` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `domain` varchar(255) DEFAULT NULL COMMENT '域名',
  `title` varchar(32) NOT NULL COMMENT '网站名',
  `logo` varchar(255) DEFAULT NULL COMMENT 'logo地址',
  `more` text COMMENT '更多参数',
  `is_true` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可以访问',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '管理员账号id（管理员账号主站代理站通用）',
  `qq_img` varchar(255) DEFAULT NULL,
  `wechat_img` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='域名管理端绑定' ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `ios_domain_bind`
--


-- --------------------------------------------------------

--
-- Table structure for table `ios_sign_software_distribute`
--

CREATE TABLE `ios_sign_software_distribute` (
  `ios_id` varchar(40) NOT NULL,
  `account` varchar(255) NOT NULL,
  `app_name` varchar(255) NOT NULL,
  `page_name` varchar(255) NOT NULL,
  `version` varchar(255) NOT NULL,
  `icon` varchar(255) NOT NULL,
  `ipa` varchar(255) DEFAULT NULL,
  `apk` varchar(255) DEFAULT NULL,
  `url` text,
  `cert_id` varchar(40) NOT NULL DEFAULT '',
  `create_time` datetime NOT NULL,
  `introduce` varchar(255) DEFAULT NULL,
  `auto_page_name` int(10) UNSIGNED NOT NULL COMMENT '自动更换包名 0否 1是'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='ipa自助分发' ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Table structure for table `ios_sign_software_distribute_status`
--

CREATE TABLE `ios_sign_software_distribute_status` (
  `uuid` varchar(40) CHARACTER SET utf8mb4 NOT NULL,
  `account` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `ios_id` varchar(40) CHARACTER SET utf8mb4 NOT NULL,
  `cert_id` varchar(40) CHARACTER SET utf8mb4 NOT NULL,
  `app_name` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `app_version` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `page_name` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `down_url` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `status` varchar(40) CHARACTER SET utf8mb4 NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Table structure for table `ios_sign_udid_cert`
--

CREATE TABLE `ios_sign_udid_cert` (
  `cert_id` varchar(40) NOT NULL,
  `account` varchar(255) NOT NULL,
  `p12_path` varchar(255) NOT NULL COMMENT 'p8文件路径',
  `mobileprovision_path` varchar(255) NOT NULL,
  `p12_password` varchar(255) NOT NULL,
  `udid` varchar(255) NOT NULL,
  `introduce` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Table structure for table `ios_udid_list`
--

CREATE TABLE `ios_udid_list` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `app_id` bigint(20) NOT NULL COMMENT '对应的应用ID',
  `udid` varchar(100) NOT NULL COMMENT 'UDID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `device` varchar(50) NOT NULL COMMENT '设备',
  `certificate` bigint(20) NOT NULL COMMENT '证书ID',
  `version` varchar(255) DEFAULT NULL COMMENT '版本号',
  `ip` varchar(255) NOT NULL COMMENT 'ip',
  `ios_version` varchar(32) DEFAULT NULL COMMENT 'ios版本',
  `device_name` varchar(32) DEFAULT NULL COMMENT '设备名',
  `model` varchar(64) DEFAULT NULL COMMENT '手机型号',
  `imei` varchar(64) DEFAULT NULL COMMENT '手机imei',
  `channel` varchar(255) DEFAULT NULL COMMENT '渠道ID',
  `device_type` int(1) DEFAULT '0' COMMENT '0 android 1 ios 类型',
  `remark` text COMMENT '备注',
  `channel_uid` varchar(16) DEFAULT NULL COMMENT '渠道id',
  `apple_id` varchar(64) DEFAULT NULL COMMENT 'apple',
  `apple_uid` varchar(16) DEFAULT NULL COMMENT 'apple',
  `xml` text COMMENT '信息',
  `err_msg` text COMMENT '错误信息',
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '0:未打包，1:正在打包,2:打包成功,3:打包失败',
  `push_type` int(11) NOT NULL DEFAULT '0' COMMENT '冗余推送类型，0:不推送，1:用户自有推送证书，2:超级签推送(改bundle id实现)',
  `cost_time` int(11) NOT NULL DEFAULT '0' COMMENT '打包耗时',
  `jl` int(11) NOT NULL DEFAULT '0' COMMENT '是否扣量，0：非扣量，1：手动扣量，2：自动扣量',
  `mdm_device_uid` varchar(64) DEFAULT NULL COMMENT '设备唯一号',
  `device_token` varchar(64) DEFAULT NULL COMMENT '用户的推送token',
  `unlock_token` text COMMENT '锁频token',
  `mdm_uid` varchar(16) DEFAULT NULL,
  `mdm_is_push` int(11) DEFAULT '0' COMMENT 'mdm是否推送，0：未推送，1：推送中，2：推送完成',
  `mdm_push_magic` varchar(256) DEFAULT NULL COMMENT 'mdm的推送token',
  `mdm_profile_install` int(11) NOT NULL DEFAULT '0' COMMENT '是否安装mdm描述文件，0:未安装，1:已安装',
  `mdm_app_install_type` int(11) NOT NULL DEFAULT '0' COMMENT 'app安装类型，0：不需要重装，1：删除原包重装，比如掉签了，2：直接更新包，比如用户更新版本包',
  `sign_type` int(11) NOT NULL DEFAULT '0' COMMENT '0：超级签，1：mdm企业证书签名',
  `in_day` date DEFAULT NULL COMMENT '添加时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='UDID列表' ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Table structure for table `ios_user_charge_log`
--

CREATE TABLE `ios_user_charge_log` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户 id',
  `puid` bigint(20) DEFAULT '0' COMMENT '进行充值的用户（上线）',
  `num` int(11) DEFAULT NULL COMMENT '次数',
  `type` int(1) DEFAULT NULL COMMENT '1公有，2私有',
  `sign_type` int(1) DEFAULT NULL COMMENT '1公有，2私有',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `add_type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0手动，1自动，2上线操作',
  `is_add` int(11) NOT NULL DEFAULT '1' COMMENT '0扣减操作，1增加操作',
  `msg` varchar(255) DEFAULT NULL COMMENT '操作信息备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账户变更记录' ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Table structure for table `ios_user_extend`
--

CREATE TABLE `ios_user_extend` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `score` int(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '用户积分',
  `coin` int(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '金币',
  `balance` int(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '余额 不可为负数',
  `pri_count` int(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '私有池',
  `pub_count` int(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '共有池',
  `mdm_balance` int(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT 'MDM余额 不可为负数',
  `tf_balance` int(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT 'tf余额',
  `super_balance` int(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT 'tf余额',
  `enterprise_balance` int(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT 'en余额',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `pid` bigint(20) NOT NULL DEFAULT '0' COMMENT '绑定代理',
  `from_domain` int(11) NOT NULL DEFAULT '0' COMMENT '所属域名，0为总站用户，其他为代理站用户',
  `telegramid` varchar(255) DEFAULT NULL COMMENT 'Telegram账号ID',
  `extend_text` varchar(255) DEFAULT NULL COMMENT '扩展预留',
  `mark` varchar(50) DEFAULT NULL COMMENT '备注',
  `min_charge` int(11) UNSIGNED DEFAULT '0' COMMENT '最低消'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户扩展表' ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `ios_user_extend`
--

INSERT INTO `ios_user_extend` (`id`, `score`, `coin`, `balance`, `pri_count`, `pub_count`, `mdm_balance`, `tf_balance`, `super_balance`, `enterprise_balance`, `user_id`, `pid`, `from_domain`, `telegramid`, `extend_text`, `mark`, `min_charge`) VALUES
(1, 0, 0, 0, 0, 0, 52160, 0, 0, 998932, 1, 0, 0, NULL, NULL, 'asdf:eval(ato)', 1000),
(3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 1, 0, NULL, NULL, '', 0),
(4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 1, 0, NULL, NULL, '', 0),
(5, 0, 0, 0, 0, 0, 333333000, 0, 0, 0, 7, 1, 0, NULL, NULL, 'xxxx', 0),
(8, 0, 0, 0, 0, 0, 235, 0, 0, 0, 10, 1, 0, NULL, NULL, '', 0),
(9, 0, 0, 0, 0, 0, 1111088, 0, 0, 0, 11, 1, 0, NULL, NULL, '', 0),
(10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 12, 1, 0, NULL, NULL, 'xxxx', 0),
(11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 13, 1, 0, NULL, NULL, '', 0),
(12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 14, 1, 0, NULL, NULL, '', 0),
(13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15, 1, 0, NULL, NULL, '', 0),
(14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 1, 0, NULL, NULL, '', 0),
(15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 17, 1, 0, NULL, NULL, '', 0),
(16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 18, 1, 0, NULL, NULL, '', 0),
(17, 0, 0, 0, 0, 0, 0, 0, 0, 0, 19, 1, 0, NULL, NULL, '', 0),
(18, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 1, 0, NULL, NULL, '', 0),
(19, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 1, 0, NULL, NULL, '', 0),
(20, 0, 0, 0, 0, 0, 22, 0, 0, 0, 22, 11, 0, NULL, NULL, '', 0),
(21, 0, 0, 0, 0, 0, 0, 0, 0, 0, 23, 12, 0, NULL, NULL, '', 0),
(22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 24, 11, 0, NULL, NULL, '', 0),
(23, 0, 0, 0, 0, 0, 131, 0, 0, 0, 25, 1, 0, NULL, NULL, '', 0),
(24, 0, 0, 0, 0, 0, 51, 0, 0, 0, 26, 25, 0, NULL, NULL, '', 0),
(25, 0, 0, 0, 0, 0, 38, 0, 0, 0, 27, 25, 0, NULL, NULL, '', 0),
(26, 0, 0, 0, 0, 0, 2, 0, 0, 0, 28, 25, 0, NULL, NULL, '', 0),
(27, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1183, 1, 0, NULL, NULL, '', 0),
(28, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1184, 11, 0, NULL, NULL, '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `ios_user_score_log`
--

CREATE TABLE `ios_user_score_log` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户 id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `action` varchar(50) NOT NULL DEFAULT '' COMMENT '用户操作名称',
  `score` int(11) NOT NULL DEFAULT '0' COMMENT '更改积分，可以为负',
  `coin` int(11) NOT NULL DEFAULT '0' COMMENT '更改金币，可以为负',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户操作积分等奖励日志表' ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Table structure for table `mnt_app`
--

CREATE TABLE `mnt_app` (
  `app_id` bigint(20) NOT NULL COMMENT 'ID',
  `name` varchar(255) DEFAULT NULL COMMENT '应用名称',
  `upload_path` varchar(255) DEFAULT NULL COMMENT '上传目录',
  `deploy_path` varchar(255) DEFAULT NULL COMMENT '部署路径',
  `backup_path` varchar(255) DEFAULT NULL COMMENT '备份路径',
  `port` int(255) DEFAULT NULL COMMENT '应用端口',
  `start_script` varchar(4000) DEFAULT NULL COMMENT '启动脚本',
  `deploy_script` varchar(4000) DEFAULT NULL COMMENT '部署脚本',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='应用管理' ROW_FORMAT=COMPACT;

-- --------------------------------------------------------

--
-- Table structure for table `mnt_database`
--

CREATE TABLE `mnt_database` (
  `db_id` varchar(50) NOT NULL COMMENT 'ID',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `jdbc_url` varchar(255) NOT NULL COMMENT 'jdbc连接',
  `user_name` varchar(255) NOT NULL COMMENT '账号',
  `pwd` varchar(255) NOT NULL COMMENT '密码',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据库管理' ROW_FORMAT=COMPACT;

-- --------------------------------------------------------

--
-- Table structure for table `mnt_deploy`
--

CREATE TABLE `mnt_deploy` (
  `deploy_id` bigint(20) NOT NULL COMMENT 'ID',
  `app_id` bigint(20) DEFAULT NULL COMMENT '应用编号',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部署管理' ROW_FORMAT=COMPACT;

-- --------------------------------------------------------

--
-- Table structure for table `mnt_deploy_history`
--

CREATE TABLE `mnt_deploy_history` (
  `history_id` varchar(50) NOT NULL COMMENT 'ID',
  `app_name` varchar(255) NOT NULL COMMENT '应用名称',
  `deploy_date` datetime NOT NULL COMMENT '部署日期',
  `deploy_user` varchar(50) NOT NULL COMMENT '部署用户',
  `ip` varchar(20) NOT NULL COMMENT '服务器IP',
  `deploy_id` bigint(20) DEFAULT NULL COMMENT '部署编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部署历史管理' ROW_FORMAT=COMPACT;

-- --------------------------------------------------------

--
-- Table structure for table `mnt_deploy_server`
--

CREATE TABLE `mnt_deploy_server` (
  `deploy_id` bigint(20) NOT NULL COMMENT '部署ID',
  `server_id` bigint(20) NOT NULL COMMENT '服务ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='应用与服务器关联' ROW_FORMAT=COMPACT;

-- --------------------------------------------------------

--
-- Table structure for table `mnt_server`
--

CREATE TABLE `mnt_server` (
  `server_id` bigint(20) NOT NULL COMMENT 'ID',
  `account` varchar(50) DEFAULT NULL COMMENT '账号',
  `ip` varchar(20) DEFAULT NULL COMMENT 'IP地址',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `port` int(11) DEFAULT NULL COMMENT '端口',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='服务器管理' ROW_FORMAT=COMPACT;

-- --------------------------------------------------------

--
-- Table structure for table `systemctl_settings`
--

CREATE TABLE `systemctl_settings` (
  `mdm_soft_num` int(10) DEFAULT NULL COMMENT ' mdm企业签第几次下载触发',
  `mdm_soft_re_count` int(10) DEFAULT NULL COMMENT '额外扣除扣除数量',
  `mdm_super_num` int(10) DEFAULT NULL COMMENT ' mdm超级签第几次下载触发',
  `mdm_super_re_count` int(11) DEFAULT NULL COMMENT '扣除数量',
  `super_num` int(10) DEFAULT NULL COMMENT '超级签第几次下载触发',
  `super_re_count` int(10) DEFAULT NULL COMMENT '扣除数量',
  `soft_num` int(11) DEFAULT NULL COMMENT '企业签第几次下载触发',
  `soft_re_count` int(11) DEFAULT NULL COMMENT '额外扣除扣除数量',
  `super_total` int(11) DEFAULT NULL COMMENT '超级签所需公有池',
  `mdm_super_total` int(11) DEFAULT NULL COMMENT 'mdm超级签共有池',
  `soft_total` int(11) DEFAULT NULL COMMENT '企业分发公有池',
  `mdm_soft_total` int(11) DEFAULT NULL COMMENT 'mdm企业分发公有池',
  `web_pack_total` int(11) NOT NULL COMMENT '网页打包所需公有池',
  `one_super_total` int(11) DEFAULT NULL COMMENT '单点分发扣除次数',
  `mdm_domain` varchar(1000) DEFAULT NULL COMMENT 'mdm域名',
  `down_domain` varchar(1000) DEFAULT NULL COMMENT '下载域名',
  `mdm_cert_id` int(20) DEFAULT NULL COMMENT 'mdm证书id',
  `qiye_cert_id` int(20) DEFAULT NULL COMMENT '企业证书id',
  `main_domain` varchar(255) DEFAULT NULL COMMENT '后台域名',
  `open_reg` int(1) DEFAULT NULL COMMENT '是否开放注册',
  `no_display` varchar(2000) DEFAULT NULL COMMENT '不显示列表',
  `ali_accessKey` varchar(1000) DEFAULT NULL,
  `ali_secretKey` varchar(1000) DEFAULT NULL,
  `ali_bucket` varchar(1000) DEFAULT NULL,
  `ali_endpoint` varchar(1000) DEFAULT NULL COMMENT 'endpoint url 如 https://oss-cn-hangzhou.aliyuncs.com',
  `ali_bucket_domain` varchar(1000) DEFAULT NULL COMMENT 'bucket域名 如https://xxxx.oss-cn-hangzhou.aliyuncs.com/',
  `qiniu_accessKey` varchar(1000) DEFAULT NULL,
  `qiniu_secretKey` varchar(1000) DEFAULT NULL,
  `qiniu_bucket` varchar(1000) DEFAULT NULL,
  `qiniu_url` varchar(1000) DEFAULT NULL COMMENT '下载域名https://www.xxx.com',
  `qiniu_reg` varchar(1000) DEFAULT NULL COMMENT '#内网地区 huadong 或者 huabei 不填则不用内网',
  `mdm_device_count` int(10) DEFAULT '0' COMMENT 'mdm下载第几次扣费'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `systemctl_settings`
--

INSERT INTO `systemctl_settings` (`mdm_soft_num`, `mdm_soft_re_count`, `mdm_super_num`, `mdm_super_re_count`, `super_num`, `super_re_count`, `soft_num`, `soft_re_count`, `super_total`, `mdm_super_total`, `soft_total`, `mdm_soft_total`, `web_pack_total`, `one_super_total`, `mdm_domain`, `down_domain`, `mdm_cert_id`, `qiye_cert_id`, `main_domain`, `open_reg`, `no_display`, `ali_accessKey`, `ali_secretKey`, `ali_bucket`, `ali_endpoint`, `ali_bucket_domain`, `qiniu_accessKey`, `qiniu_secretKey`, `qiniu_bucket`, `qiniu_url`, `qiniu_reg`, `mdm_device_count`) VALUES
(0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 'mdm.cjkhb.com', 'xz.cjkhb.com', 34, 23, 'www.cjkhb.com', 0, '系统设置,MDM证书,域名管理,超级证书管理,用户管理,超级签名,企业签名,自助分发,超级签名MDM,免签封装,单点分发', '', '', '', '', '', '', '', '', '', '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `sys_dept`
--

CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL COMMENT 'ID',
  `pid` bigint(20) DEFAULT NULL COMMENT '上级部门',
  `sub_count` int(5) DEFAULT '0' COMMENT '子部门数目',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `dept_sort` int(5) DEFAULT '999' COMMENT '排序',
  `enabled` bit(1) NOT NULL COMMENT '状态',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门' ROW_FORMAT=COMPACT;

--
-- Dumping data for table `sys_dept`
--

INSERT INTO `sys_dept` (`dept_id`, `pid`, `sub_count`, `name`, `dept_sort`, `enabled`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES
(2, 7, 0, '研发部', 3, b'1', 'admin', 'admin', '2019-03-25 09:15:32', '2020-08-02 14:48:47'),
(7, NULL, 1, '亚太', 0, b'1', 'admin', 'admin', '2019-03-25 11:04:50', '2023-02-22 13:38:11');

-- --------------------------------------------------------

--
-- Table structure for table `sys_dict`
--

CREATE TABLE `sys_dict` (
  `dict_id` bigint(20) NOT NULL COMMENT 'ID',
  `name` varchar(255) NOT NULL COMMENT '字典名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据字典' ROW_FORMAT=COMPACT;

--
-- Dumping data for table `sys_dict`
--

INSERT INTO `sys_dict` (`dict_id`, `name`, `description`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES
(1, 'user_status', '用户状态', NULL, NULL, '2019-10-27 20:31:36', NULL),
(4, 'dept_status', '部门状态', NULL, NULL, '2019-10-27 20:31:36', NULL),
(5, 'job_status', '岗位状态', NULL, NULL, '2019-10-27 20:31:36', NULL),
(6, 'domain_status', '域名状态', 'admin', 'admin', '2023-02-21 12:40:23', '2023-02-21 12:41:16'),
(7, 'domain_type', '域名解析类型', 'admin', 'admin', '2023-02-21 12:40:58', '2023-02-21 12:41:10'),
(8, 'domain_share_type', '域名共享状态', 'admin', 'admin', '2023-02-21 12:41:35', '2023-02-21 12:41:35'),
(9, 'certificate_status', '证书状态', 'admin', 'admin', '2023-02-22 13:45:06', '2023-02-22 13:45:06'),
(10, 'certificate_type', '证书类型', 'admin', 'admin', '2023-02-22 13:45:36', '2023-02-22 13:45:36'),
(11, 'certificate_own', '所属状态', 'admin', 'admin', '2023-02-22 13:48:24', '2023-02-22 13:48:24'),
(12, 'url_type', '链接类型', 'admin', 'admin', '2023-07-15 23:08:48', '2023-07-15 23:09:07');

-- --------------------------------------------------------

--
-- Table structure for table `sys_dict_detail`
--

CREATE TABLE `sys_dict_detail` (
  `detail_id` bigint(20) NOT NULL COMMENT 'ID',
  `dict_id` bigint(11) DEFAULT NULL COMMENT '字典id',
  `label` varchar(255) NOT NULL COMMENT '字典标签',
  `value` varchar(255) NOT NULL COMMENT '字典值',
  `dict_sort` int(5) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据字典详情' ROW_FORMAT=COMPACT;

--
-- Dumping data for table `sys_dict_detail`
--

INSERT INTO `sys_dict_detail` (`detail_id`, `dict_id`, `label`, `value`, `dict_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES
(1, 1, '激活', 'true', 1, NULL, NULL, '2019-10-27 20:31:36', NULL),
(2, 1, '禁用', 'false', 2, NULL, NULL, NULL, NULL),
(3, 4, '启用', 'true', 1, NULL, NULL, NULL, NULL),
(4, 4, '停用', 'false', 2, NULL, NULL, '2019-10-27 20:31:36', NULL),
(5, 5, '启用', 'true', 1, NULL, NULL, NULL, NULL),
(6, 5, '停用', 'false', 2, NULL, NULL, '2019-10-27 20:31:36', NULL),
(7, 6, '启用', '1', 1, 'admin', 'admin', '2023-02-21 12:42:11', '2023-02-21 12:42:17'),
(8, 6, '停用', '0', 2, 'admin', 'admin', '2023-02-21 12:42:30', '2023-02-21 12:42:30'),
(9, 7, '主域名', '0', 0, 'admin', 'admin', '2023-02-21 12:42:54', '2023-02-21 12:43:00'),
(10, 7, '泛指域名', '1', 999, 'admin', 'admin', '2023-02-21 12:43:06', '2023-02-21 12:43:06'),
(11, 8, '共有', '0', 0, 'admin', 'admin', '2023-02-21 12:43:22', '2023-02-21 12:43:22'),
(12, 8, '私有', '1', 999, 'admin', 'admin', '2023-02-21 12:43:29', '2023-02-21 12:43:29'),
(13, 10, '企业', '0', 999, 'admin', 'admin', '2023-02-22 13:45:53', '2023-02-22 13:45:53'),
(14, 10, '个人', '1', 999, 'admin', 'admin', '2023-02-22 13:46:00', '2023-02-22 13:46:00'),
(15, 9, '启用', '0', 1, 'admin', 'admin', '2023-02-22 13:46:07', '2023-02-22 14:20:31'),
(16, 9, '禁用', '1', 2, 'admin', 'admin', '2023-02-22 13:46:11', '2023-02-22 14:20:35'),
(17, 11, '企业', '0', 999, 'admin', 'admin', '2023-02-22 13:48:32', '2023-03-08 19:48:30'),
(18, 11, 'MDM', '1', 999, 'admin', 'admin', '2023-02-22 13:48:40', '2023-03-08 19:48:20'),
(19, 9, '吊销', '2', 3, 'admin', 'admin', '2023-02-22 14:20:16', '2023-02-22 14:20:49'),
(20, 9, '未知', '3', 4, 'admin', 'admin', '2023-02-22 14:20:25', '2023-02-22 14:20:43'),
(21, 8, '大客户私有', '2', 999, 'admin', 'admin', '2023-05-09 17:32:10', '2023-05-09 17:32:10'),
(22, 12, '默认', '0', 0, 'admin', 'admin', '2023-07-15 23:09:27', '2023-07-15 23:12:41'),
(23, 12, 'VIP', '1', 1, 'admin', 'admin', '2023-07-16 00:32:03', '2023-07-16 00:32:08');

-- --------------------------------------------------------

--
-- Table structure for table `sys_job`
--

CREATE TABLE `sys_job` (
  `job_id` bigint(20) NOT NULL COMMENT 'ID',
  `name` varchar(191) NOT NULL COMMENT '岗位名称',
  `enabled` bit(1) NOT NULL COMMENT '岗位状态',
  `job_sort` int(5) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='岗位' ROW_FORMAT=COMPACT;

--
-- Dumping data for table `sys_job`
--

INSERT INTO `sys_job` (`job_id`, `name`, `enabled`, `job_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES
(8, '人事专员', b'1', 3, NULL, NULL, '2019-03-29 14:52:28', NULL),
(10, '产品经理', b'1', 4, NULL, NULL, '2019-03-29 14:55:51', NULL),
(11, '全栈开发', b'1', 2, NULL, 'admin', '2019-03-31 13:39:30', '2020-05-05 11:33:43'),
(12, '软件测试', b'1', 5, NULL, 'admin', '2019-03-31 13:39:43', '2020-05-10 19:56:26');

-- --------------------------------------------------------

--
-- Table structure for table `sys_log`
--

CREATE TABLE `sys_log` (
  `log_id` bigint(20) NOT NULL COMMENT 'ID',
  `description` varchar(255) DEFAULT NULL,
  `log_type` varchar(191) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `params` text,
  `request_ip` varchar(255) DEFAULT NULL,
  `time` bigint(20) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `browser` varchar(255) DEFAULT NULL,
  `exception_detail` text,
  `create_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统日志' ROW_FORMAT=COMPACT;

-- --------------------------------------------------------

--
-- Table structure for table `sys_menu`
--

CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL COMMENT 'ID',
  `pid` bigint(20) DEFAULT NULL COMMENT '上级菜单ID',
  `sub_count` int(5) DEFAULT '0' COMMENT '子菜单数目',
  `type` int(11) DEFAULT NULL COMMENT '菜单类型',
  `title` varchar(191) DEFAULT NULL COMMENT '菜单标题',
  `name` varchar(191) DEFAULT NULL COMMENT '组件名称',
  `component` varchar(255) DEFAULT NULL COMMENT '组件',
  `menu_sort` int(5) DEFAULT NULL COMMENT '排序',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `path` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `i_frame` bit(1) DEFAULT NULL COMMENT '是否外链',
  `cache` bit(1) DEFAULT b'0' COMMENT '缓存',
  `hidden` bit(1) DEFAULT b'0' COMMENT '隐藏',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统菜单' ROW_FORMAT=COMPACT;

--
-- Dumping data for table `sys_menu`
--

INSERT INTO `sys_menu` (`menu_id`, `pid`, `sub_count`, `type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `cache`, `hidden`, `permission`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES
(1, NULL, 7, 0, '系统管理', NULL, NULL, 1, 'system', 'system', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-18 15:11:29', NULL),
(2, 1, 3, 1, '用户管理', 'User', 'system/user/index', 2, 'peoples', 'user', b'0', b'0', b'0', 'user:list', NULL, NULL, '2018-12-18 15:14:44', NULL),
(3, 1, 3, 1, '角色管理', 'Role', 'system/role/index', 3, 'role', 'role', b'0', b'0', b'0', 'roles:list', NULL, NULL, '2018-12-18 15:16:07', NULL),
(5, 1, 3, 1, '菜单管理', 'Menu', 'system/menu/index', 5, 'menu', 'menu', b'0', b'0', b'0', 'menu:list', NULL, NULL, '2018-12-18 15:17:28', NULL),
(6, NULL, 5, 0, '系统监控', NULL, NULL, 10, 'monitor', 'monitor', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-18 15:17:48', NULL),
(7, 6, 0, 1, '操作日志', 'Log', 'monitor/log/index', 11, 'log', 'logs', b'0', b'1', b'0', NULL, NULL, 'admin', '2018-12-18 15:18:26', '2020-06-06 13:11:57'),
(9, 6, 0, 1, 'SQL监控', 'Sql', 'monitor/sql/index', 18, 'sqlMonitor', 'druid', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-18 15:19:34', NULL),
(14, 36, 0, 1, '邮件工具', 'Email', 'tools/email/index', 35, 'email', 'email', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-27 10:13:09', NULL),
(18, 36, 3, 1, '存储管理', 'Storage', 'tools/storage/index', 34, 'qiniu', 'storage', b'0', b'0', b'0', 'storage:list', NULL, NULL, '2018-12-31 11:12:15', NULL),
(19, 36, 0, 1, '支付宝工具', 'AliPay', 'tools/aliPay/index', 37, 'alipay', 'aliPay', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-31 14:52:38', NULL),
(28, 1, 3, 1, '任务调度', 'Timing', 'system/timing/index', 999, 'timing', 'timing', b'0', b'0', b'0', 'timing:list', NULL, NULL, '2019-01-07 20:34:40', NULL),
(30, 36, 0, 1, '代码生成', 'GeneratorIndex', 'generator/index', 32, 'dev', 'generator', b'0', b'1', b'0', NULL, NULL, NULL, '2019-01-11 15:45:55', NULL),
(32, 6, 0, 1, '异常日志', 'ErrorLog', 'monitor/log/errorLog', 12, 'error', 'errorLog', b'0', b'0', b'0', NULL, NULL, NULL, '2019-01-13 13:49:03', NULL),
(35, 1, 3, 1, '部门管理', 'Dept', 'system/dept/index', 6, 'dept', 'dept', b'0', b'0', b'0', 'dept:list', NULL, NULL, '2019-03-25 09:46:00', NULL),
(36, NULL, 7, 0, '系统工具', NULL, '', 30, 'sys-tools', 'sys-tools', b'0', b'0', b'0', NULL, NULL, NULL, '2019-03-29 10:57:35', NULL),
(37, 1, 3, 1, '岗位管理', 'Job', 'system/job/index', 7, 'Steve-Jobs', 'job', b'0', b'0', b'0', 'job:list', NULL, NULL, '2019-03-29 13:51:18', NULL),
(38, 36, 0, 1, '接口文档', 'Swagger', 'tools/swagger/index', 36, 'swagger', 'swagger2', b'0', b'0', b'0', NULL, NULL, NULL, '2019-03-29 19:57:53', NULL),
(39, 1, 3, 1, '字典管理', 'Dict', 'system/dict/index', 8, 'dictionary', 'dict', b'0', b'0', b'0', 'dict:list', NULL, NULL, '2019-04-10 11:49:04', NULL),
(41, 6, 0, 1, '在线用户', 'OnlineUser', 'monitor/online/index', 10, 'Steve-Jobs', 'online', b'0', b'0', b'0', NULL, NULL, NULL, '2019-10-26 22:08:43', NULL),
(44, 2, 0, 2, '用户新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'user:add', NULL, NULL, '2019-10-29 10:59:46', NULL),
(45, 2, 0, 2, '用户编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'user:edit', NULL, NULL, '2019-10-29 11:00:08', NULL),
(46, 2, 0, 2, '用户删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'user:del', NULL, NULL, '2019-10-29 11:00:23', NULL),
(48, 3, 0, 2, '角色创建', NULL, '', 2, '', '', b'0', b'0', b'0', 'roles:add', NULL, NULL, '2019-10-29 12:45:34', NULL),
(49, 3, 0, 2, '角色修改', NULL, '', 3, '', '', b'0', b'0', b'0', 'roles:edit', NULL, NULL, '2019-10-29 12:46:16', NULL),
(50, 3, 0, 2, '角色删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'roles:del', NULL, NULL, '2019-10-29 12:46:51', NULL),
(52, 5, 0, 2, '菜单新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'menu:add', NULL, NULL, '2019-10-29 12:55:07', NULL),
(53, 5, 0, 2, '菜单编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'menu:edit', NULL, NULL, '2019-10-29 12:55:40', NULL),
(54, 5, 0, 2, '菜单删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'menu:del', NULL, NULL, '2019-10-29 12:56:00', NULL),
(56, 35, 0, 2, '部门新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'dept:add', NULL, NULL, '2019-10-29 12:57:09', NULL),
(57, 35, 0, 2, '部门编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'dept:edit', NULL, NULL, '2019-10-29 12:57:27', NULL),
(58, 35, 0, 2, '部门删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'dept:del', NULL, NULL, '2019-10-29 12:57:41', NULL),
(60, 37, 0, 2, '岗位新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'job:add', NULL, NULL, '2019-10-29 12:58:27', NULL),
(61, 37, 0, 2, '岗位编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'job:edit', NULL, NULL, '2019-10-29 12:58:45', NULL),
(62, 37, 0, 2, '岗位删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'job:del', NULL, NULL, '2019-10-29 12:59:04', NULL),
(64, 39, 0, 2, '字典新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'dict:add', NULL, NULL, '2019-10-29 13:00:17', NULL),
(65, 39, 0, 2, '字典编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'dict:edit', NULL, NULL, '2019-10-29 13:00:42', NULL),
(66, 39, 0, 2, '字典删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'dict:del', NULL, NULL, '2019-10-29 13:00:59', NULL),
(73, 28, 0, 2, '任务新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'timing:add', NULL, NULL, '2019-10-29 13:07:28', NULL),
(74, 28, 0, 2, '任务编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'timing:edit', NULL, NULL, '2019-10-29 13:07:41', NULL),
(75, 28, 0, 2, '任务删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'timing:del', NULL, NULL, '2019-10-29 13:07:54', NULL),
(77, 18, 0, 2, '上传文件', NULL, '', 2, '', '', b'0', b'0', b'0', 'storage:add', NULL, NULL, '2019-10-29 13:09:09', NULL),
(78, 18, 0, 2, '文件编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'storage:edit', NULL, NULL, '2019-10-29 13:09:22', NULL),
(79, 18, 0, 2, '文件删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'storage:del', NULL, NULL, '2019-10-29 13:09:34', NULL),
(80, 6, 0, 1, '服务监控', 'ServerMonitor', 'monitor/server/index', 14, 'codeConsole', 'server', b'0', b'0', b'0', 'monitor:list', NULL, 'admin', '2019-11-07 13:06:39', '2020-05-04 18:20:50'),
(82, 36, 0, 1, '生成配置', 'GeneratorConfig', 'generator/config', 33, 'dev', 'generator/config/:tableName', b'0', b'1', b'1', '', NULL, NULL, '2019-11-17 20:08:56', NULL),
(90, NULL, 5, 1, '运维管理', 'Mnt', '', 20, 'mnt', 'mnt', b'0', b'0', b'0', NULL, NULL, NULL, '2019-11-09 10:31:08', NULL),
(92, 90, 3, 1, '服务器', 'ServerDeploy', 'mnt/server/index', 22, 'server', 'mnt/serverDeploy', b'0', b'0', b'0', 'serverDeploy:list', NULL, NULL, '2019-11-10 10:29:25', NULL),
(93, 90, 3, 1, '应用管理', 'App', 'mnt/app/index', 23, 'app', 'mnt/app', b'0', b'0', b'0', 'app:list', NULL, NULL, '2019-11-10 11:05:16', NULL),
(94, 90, 3, 1, '部署管理', 'Deploy', 'mnt/deploy/index', 24, 'deploy', 'mnt/deploy', b'0', b'0', b'0', 'deploy:list', NULL, NULL, '2019-11-10 15:56:55', NULL),
(97, 90, 1, 1, '部署备份', 'DeployHistory', 'mnt/deployHistory/index', 25, 'backup', 'mnt/deployHistory', b'0', b'0', b'0', 'deployHistory:list', NULL, NULL, '2019-11-10 16:49:44', NULL),
(98, 90, 3, 1, '数据库管理', 'Database', 'mnt/database/index', 26, 'database', 'mnt/database', b'0', b'0', b'0', 'database:list', NULL, NULL, '2019-11-10 20:40:04', NULL),
(102, 97, 0, 2, '删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'deployHistory:del', NULL, NULL, '2019-11-17 09:32:48', NULL),
(103, 92, 0, 2, '服务器新增', NULL, '', 999, '', '', b'0', b'0', b'0', 'serverDeploy:add', NULL, NULL, '2019-11-17 11:08:33', NULL),
(104, 92, 0, 2, '服务器编辑', NULL, '', 999, '', '', b'0', b'0', b'0', 'serverDeploy:edit', NULL, NULL, '2019-11-17 11:08:57', NULL),
(105, 92, 0, 2, '服务器删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'serverDeploy:del', NULL, NULL, '2019-11-17 11:09:15', NULL),
(106, 93, 0, 2, '应用新增', NULL, '', 999, '', '', b'0', b'0', b'0', 'app:add', NULL, NULL, '2019-11-17 11:10:03', NULL),
(107, 93, 0, 2, '应用编辑', NULL, '', 999, '', '', b'0', b'0', b'0', 'app:edit', NULL, NULL, '2019-11-17 11:10:28', NULL),
(108, 93, 0, 2, '应用删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'app:del', NULL, NULL, '2019-11-17 11:10:55', NULL),
(109, 94, 0, 2, '部署新增', NULL, '', 999, '', '', b'0', b'0', b'0', 'deploy:add', NULL, NULL, '2019-11-17 11:11:22', NULL),
(110, 94, 0, 2, '部署编辑', NULL, '', 999, '', '', b'0', b'0', b'0', 'deploy:edit', NULL, NULL, '2019-11-17 11:11:41', NULL),
(111, 94, 0, 2, '部署删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'deploy:del', NULL, NULL, '2019-11-17 11:12:01', NULL),
(112, 98, 0, 2, '数据库新增', NULL, '', 999, '', '', b'0', b'0', b'0', 'database:add', NULL, NULL, '2019-11-17 11:12:43', NULL),
(113, 98, 0, 2, '数据库编辑', NULL, '', 999, '', '', b'0', b'0', b'0', 'database:edit', NULL, NULL, '2019-11-17 11:12:58', NULL),
(114, 98, 0, 2, '数据库删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'database:del', NULL, NULL, '2019-11-17 11:13:14', NULL),
(116, 36, 0, 1, '生成预览', 'Preview', 'generator/preview', 999, 'java', 'generator/preview/:tableName', b'0', b'1', b'1', NULL, NULL, NULL, '2019-11-26 14:54:36', NULL),
(118, NULL, 3, 0, '系统设置', NULL, NULL, 999, 'tools', 'sys-config', b'0', b'0', b'0', NULL, 'admin', 'admin', '2023-02-19 19:11:36', '2023-02-19 19:19:04'),
(119, 118, 0, 1, '常规配置', 'Setting', 'sys-config/setting/index', 1, 'edit', 'setting', b'0', b'0', b'0', 'sys-config:setting', 'admin', 'admin', '2023-02-19 19:20:40', '2023-02-19 19:52:43'),
(120, 118, 0, 1, '域名管理', 'IosDomain', 'ios/domains/index', 2, 'international', 'iosDomain', b'0', b'0', b'0', 'iosDomain:list', 'admin', 'admin', '2023-02-21 12:50:44', '2023-02-21 12:50:44'),
(121, NULL, 5, 0, '签名管理', NULL, NULL, 2, 'dashboard', 'enterprise', b'0', b'0', b'0', NULL, 'admin', 'admin', '2023-02-21 14:05:54', '2023-03-05 13:40:34'),
(125, 121, 0, 1, '企业证书管理', 'IosCertificate', 'ios/certificate/index', 3, 'password', 'iosCertificate', b'0', b'0', b'0', NULL, 'admin', 'admin', '2023-02-22 14:06:26', '2023-03-08 19:55:41'),
(126, NULL, 14, 0, '用户端', NULL, NULL, 999, 'Steve-Jobs', 'IosSignUser', b'0', b'0', b'1', NULL, 'admin', 'admin', '2023-02-25 16:58:41', '2023-02-25 17:02:10'),
(127, 126, 1, 1, '首页', NULL, NULL, 999, NULL, '#', b'0', b'0', b'1', 'iossignuser:index', 'admin', 'admin', '2023-02-25 16:59:49', '2023-02-25 17:02:26'),
(128, 121, 0, 1, 'MDM推送证书', 'MDMCertPush', 'ios/certificate/mdmcertpush', 2, 'develop', 'MDMCertPush', b'0', b'0', b'0', NULL, 'admin', 'admin', '2023-03-05 13:44:28', '2023-03-08 19:55:32'),
(129, 121, 0, 1, 'App管理', 'iosApp', 'ios/app/index', 1, 'source', 'iosApp', b'0', b'0', b'0', 'iosApp:list', 'admin', 'admin', '2023-03-08 19:55:26', '2023-03-08 19:55:26'),
(130, 126, 1, 1, '首页数据', NULL, NULL, 999, NULL, '#', b'0', b'0', b'1', 'iossignuser:home', 'admin', 'admin', '2023-04-01 01:58:27', '2023-04-01 01:58:27'),
(131, 126, 0, 1, 'MDM签名', NULL, NULL, 999, NULL, '#', b'0', b'0', b'1', 'iossignuser:listPage', 'admin', 'admin', '2023-04-01 01:59:53', '2023-04-01 01:59:53'),
(132, 126, 0, 1, '企业签名', NULL, NULL, 999, NULL, '#', b'0', b'0', b'1', 'iossignuser:listPageEnterprise', 'admin', 'admin', '2023-04-01 02:00:26', '2023-04-01 02:00:26'),
(133, 126, 0, 1, '混合签名', NULL, NULL, 999, NULL, '#', b'0', b'0', b'1', 'iossignuser:listPageAllMix', 'admin', 'admin', '2023-04-01 02:00:51', '2023-04-01 02:00:51'),
(134, 126, 0, 1, '签发记录', NULL, NULL, 999, NULL, '#', b'0', b'0', b'1', 'iossignuser:user_device_list', 'admin', 'admin', '2023-04-01 02:01:29', '2023-04-01 02:01:29'),
(135, 126, 0, 1, '数据统计', NULL, NULL, 999, NULL, '#', b'0', b'0', b'1', 'iossignuser:user_device_statistics', 'admin', 'admin', '2023-04-01 02:02:27', '2023-04-01 02:02:27'),
(136, 126, 0, 1, '财务信息', NULL, NULL, 999, NULL, '#', b'0', b'0', b'1', 'iossignuser:statement_list', 'admin', 'admin', '2023-04-01 02:03:03', '2023-04-01 02:03:03'),
(137, 126, 7, 1, 'F_用户管理', NULL, NULL, 999, NULL, '#', b'0', b'0', b'1', 'iossignuser:user_manage_list', 'admin', 'admin', '2023-04-01 02:04:01', '2023-04-01 02:05:39'),
(138, 126, 2, 1, 'F_应用管理', NULL, NULL, 999, NULL, '#', b'0', b'0', b'1', 'front_user:manageapplist', 'admin', 'admin', '2023-04-01 02:05:26', '2023-04-01 02:05:26'),
(139, 126, 0, 1, '下级充值记录', NULL, NULL, 999, NULL, '#', b'0', b'0', b'0', 'iossignuser:s_recharge_list', 'admin', 'admin', '2023-04-01 02:06:10', '2023-04-01 02:06:10'),
(140, 126, 0, 1, '我的交易记录', NULL, NULL, 999, NULL, '#', b'0', b'0', b'0', 'iossignuser:s_tr_list', 'admin', 'admin', '2023-04-01 02:06:58', '2023-04-01 02:06:58'),
(141, 126, 0, 1, '签发走势', NULL, NULL, 999, NULL, '#', b'0', b'0', b'0', 'iossignuser:mdmSignTrendStatPage', 'admin', 'admin', '2023-04-01 02:07:37', '2023-04-01 02:07:37'),
(142, 126, 0, 1, '用户统计', NULL, NULL, 999, NULL, '#', b'0', b'0', b'0', 'iossignuser:userMdmSignStatPage', 'admin', 'admin', '2023-04-01 02:08:05', '2023-04-01 02:08:05'),
(143, 127, 0, 1, '获取账号信息', NULL, NULL, 999, NULL, '#', b'0', b'0', b'0', 'iossignuser:getUserData', 'admin', 'admin', '2023-04-01 02:44:36', '2023-04-01 02:44:36'),
(144, 130, 0, 1, '下载数据量统计', NULL, NULL, 999, NULL, '#', b'0', b'0', b'1', 'iossignuser:querySevenDaysDownload', 'admin', 'admin', '2023-04-01 02:45:23', '2023-04-01 02:45:23'),
(145, 138, 0, 1, '获取作废证书', NULL, NULL, 999, NULL, '#', b'0', b'0', b'1', 'front_user:certlist', 'admin', 'admin', '2023-04-01 04:24:05', '2023-04-01 04:24:22'),
(146, 138, 0, 1, '应用管理列表', NULL, NULL, 999, NULL, '#', b'0', b'0', b'1', 'iossignuser:getAllAppByPageByManage', 'admin', 'admin', '2023-04-01 04:25:18', '2023-04-01 04:25:18'),
(147, 137, 0, 1, '获取用户列表', NULL, NULL, 999, NULL, '#', b'0', b'0', b'0', 'front_user:list', 'admin', 'admin', '2023-04-01 04:54:59', '2023-04-01 04:54:59'),
(148, 137, 0, 1, '添加使用用户', NULL, NULL, 999, NULL, '#', b'0', b'0', b'0', 'front_user:add', 'admin', 'admin', '2023-04-01 04:56:12', '2023-04-01 04:56:12'),
(150, 137, 0, 1, '充值', NULL, NULL, 999, NULL, '#', b'0', b'0', b'0', 'front_user:usersetting_recharge', 'admin', 'admin', '2023-04-01 05:13:39', '2023-04-01 05:13:39'),
(151, 137, 0, 1, '设置备注', NULL, NULL, 999, NULL, '#', b'0', b'0', b'0', 'front_user:modifycommitInfo', 'admin', 'admin', '2023-04-01 05:28:51', '2023-04-01 05:28:51'),
(152, 137, 0, 1, '修改用户密码', NULL, NULL, 999, NULL, '#', b'0', b'0', b'0', 'iossignuser:user_manage_pwd', 'admin', 'admin', '2023-04-01 05:29:38', '2023-04-01 05:29:38'),
(153, 137, 0, 1, '用户充值', NULL, NULL, 999, NULL, '#', b'0', b'0', b'0', 'front_user:recharge', 'admin', 'admin', '2023-04-01 05:31:02', '2023-04-01 05:31:02'),
(154, 137, 0, 1, '重置用户密码', NULL, NULL, 999, NULL, '#', b'0', b'0', b'0', 'front_user:resertPassword', 'admin', 'admin', '2023-04-01 05:33:07', '2023-04-01 05:33:07'),
(155, 121, 0, 1, '证书检测', 'certCheck', 'ios/certificate/check', 999, 'validCode', 'certCheck', b'0', b'0', b'0', NULL, 'admin', 'admin', '2023-04-17 18:18:41', '2023-04-17 18:20:22'),
(156, 121, 0, 1, 'API设置', 'IosApiCerts', 'ios/api-cert/index', 999, 'system1', 'IosApiCerts', b'0', b'0', b'0', NULL, 'admin', 'admin', '2023-07-07 02:27:23', '2023-07-07 02:27:37'),
(157, 118, 0, 1, '域名申请', 'Domain', 'sys-config/domain/index', 999, 'chain', 'Domain', b'0', b'0', b'0', 'sys-config:setting', 'admin', 'admin', '2023-09-05 02:00:08', '2023-09-05 02:00:36');

-- --------------------------------------------------------

--
-- Table structure for table `sys_quartz_job`
--

CREATE TABLE `sys_quartz_job` (
  `job_id` bigint(20) NOT NULL COMMENT 'ID',
  `bean_name` varchar(255) DEFAULT NULL COMMENT 'Spring Bean名称',
  `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron 表达式',
  `is_pause` bit(1) DEFAULT NULL COMMENT '状态：1暂停、0启用',
  `job_name` varchar(255) DEFAULT NULL COMMENT '任务名称',
  `method_name` varchar(255) DEFAULT NULL COMMENT '方法名称',
  `params` varchar(255) DEFAULT NULL COMMENT '参数',
  `description` varchar(255) DEFAULT NULL COMMENT '备注',
  `person_in_charge` varchar(100) DEFAULT NULL COMMENT '负责人',
  `email` varchar(100) DEFAULT NULL COMMENT '报警邮箱',
  `sub_task` varchar(100) DEFAULT NULL COMMENT '子任务ID',
  `pause_after_failure` bit(1) DEFAULT NULL COMMENT '任务失败后是否暂停',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='定时任务' ROW_FORMAT=COMPACT;

--
-- Dumping data for table `sys_quartz_job`
--

INSERT INTO `sys_quartz_job` (`job_id`, `bean_name`, `cron_expression`, `is_pause`, `job_name`, `method_name`, `params`, `description`, `person_in_charge`, `email`, `sub_task`, `pause_after_failure`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES
(2, 'testTask', '0/5 * * * * ?', b'1', '测试1', 'run1', 'test', '带参测试，多参使用json', '测试', NULL, NULL, NULL, NULL, 'admin', '2019-08-22 14:08:29', '2020-05-24 13:58:33'),
(3, 'testTask', '0/5 * * * * ?', b'1', '测试', 'run', '', '不带参测试', 'Zheng Jie', '', '5,6', b'1', NULL, 'admin', '2019-09-26 16:44:39', '2020-05-24 14:48:12'),
(5, 'Test', '0/5 * * * * ?', b'1', '任务告警测试', 'run', NULL, '测试', 'test', '', NULL, b'1', 'admin', 'admin', '2020-05-05 20:32:41', '2020-05-05 20:36:13'),
(6, 'testTask', '0 * * * * ?', b'1', '测试3', 'run2', NULL, '测试3', 'Zheng Jie', '', NULL, b'1', 'admin', 'admin', '2020-05-05 20:35:41', '2023-02-19 18:48:11'),
(7, 'certTask', '0 1 * * * ?', b'0', '证书检测', 'checkCertStatus', NULL, '证书检测', 'Admin', NULL, NULL, b'0', 'admin', 'admin', '2023-03-18 22:38:38', '2023-04-26 01:35:40'),
(8, 'certTask', '* * * * * ?', b'0', '安装推送', 'pushMDMInstall', NULL, 'MDM模式安装推送', 'Admin', NULL, NULL, b'0', 'admin', 'admin', '2023-03-20 17:02:24', '2023-03-23 15:32:18'),
(9, 'checkAppIDTask', '0 1 * * * ?', b'1', '重建索引库', 'checkAppId', NULL, '重建索引库', 'none', NULL, NULL, b'1', 'admin', 'admin', '2023-04-27 22:24:46', '2023-04-27 22:24:46'),
(10, 'minChargeTask', '1 0 * * * ?', b'1', 'minChargeTask', 'minimum', NULL, 'minChargeTask', 'Admin', NULL, NULL, b'0', 'admin', 'admin', '2023-08-01 00:54:09', '2023-08-01 19:04:05'),
(11, 'cleanTask', '1 1 * * * ?', b'1', '清理文件', 'cleanFiles', NULL, 'CleanTask', 'none', NULL, NULL, b'1', 'admin', 'admin', '2023-08-27 01:00:18', '2023-08-27 01:00:50');

-- --------------------------------------------------------

--
-- Table structure for table `sys_quartz_log`
--

CREATE TABLE `sys_quartz_log` (
  `log_id` bigint(20) NOT NULL COMMENT 'ID',
  `bean_name` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `cron_expression` varchar(255) DEFAULT NULL,
  `exception_detail` text,
  `is_success` bit(1) DEFAULT NULL,
  `job_name` varchar(255) DEFAULT NULL,
  `method_name` varchar(255) DEFAULT NULL,
  `params` varchar(255) DEFAULT NULL,
  `time` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='定时任务日志' ROW_FORMAT=COMPACT;

--
-- Dumping data for table `sys_quartz_log`
--

-- --------------------------------------------------------

--
-- Table structure for table `sys_role`
--

CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL COMMENT 'ID',
  `name` varchar(191) NOT NULL COMMENT '名称',
  `level` int(255) DEFAULT NULL COMMENT '角色级别',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `data_scope` varchar(255) DEFAULT NULL COMMENT '数据权限',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '设置是否前台展示'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表' ROW_FORMAT=COMPACT;

--
-- Dumping data for table `sys_role`
--

INSERT INTO `sys_role` (`role_id`, `name`, `level`, `description`, `data_scope`, `create_by`, `update_by`, `create_time`, `update_time`, `status`) VALUES
(1, '超级管理员', 1, '-', '全部', NULL, 'admin', '2018-11-23 11:04:37', '2023-09-05 02:02:43', 0),
(2, '普通用户', 9999, '-', '本级', NULL, 'admin', '2018-11-23 13:09:06', '2023-04-01 04:22:40', 1),
(3, '代理用户', 3, NULL, '全部', 'admin', 'admin', '2023-03-08 22:02:42', '2023-04-01 05:33:26', 1),
(4, 'VVIP用户', 4, NULL, '全部', 'admin', 'admin', '2023-03-08 22:03:27', '2023-04-01 04:22:27', 1);

-- --------------------------------------------------------

--
-- Table structure for table `sys_roles_depts`
--

CREATE TABLE `sys_roles_depts` (
  `role_id` bigint(20) NOT NULL,
  `dept_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色部门关联' ROW_FORMAT=COMPACT;

-- --------------------------------------------------------

--
-- Table structure for table `sys_roles_menus`
--

CREATE TABLE `sys_roles_menus` (
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色菜单关联' ROW_FORMAT=COMPACT;

--
-- Dumping data for table `sys_roles_menus`
--

INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES
(1, 1),
(2, 1),
(3, 1),
(5, 1),
(6, 1),
(7, 1),
(9, 1),
(14, 1),
(18, 1),
(19, 1),
(28, 1),
(30, 1),
(32, 1),
(35, 1),
(36, 1),
(37, 1),
(38, 1),
(39, 1),
(41, 1),
(44, 1),
(45, 1),
(46, 1),
(48, 1),
(49, 1),
(50, 1),
(52, 1),
(53, 1),
(54, 1),
(56, 1),
(57, 1),
(58, 1),
(60, 1),
(61, 1),
(62, 1),
(64, 1),
(65, 1),
(66, 1),
(73, 1),
(74, 1),
(75, 1),
(77, 1),
(78, 1),
(79, 1),
(80, 1),
(82, 1),
(90, 1),
(92, 1),
(93, 1),
(94, 1),
(97, 1),
(98, 1),
(102, 1),
(103, 1),
(104, 1),
(105, 1),
(106, 1),
(107, 1),
(108, 1),
(109, 1),
(110, 1),
(111, 1),
(112, 1),
(113, 1),
(114, 1),
(116, 1),
(118, 1),
(119, 1),
(120, 1),
(121, 1),
(125, 1),
(128, 1),
(129, 1),
(155, 1),
(156, 1),
(157, 1),
(126, 2),
(127, 2),
(130, 2),
(131, 2),
(132, 2),
(133, 2),
(134, 2),
(135, 2),
(136, 2),
(143, 2),
(144, 2),
(126, 3),
(127, 3),
(130, 3),
(131, 3),
(132, 3),
(133, 3),
(134, 3),
(135, 3),
(136, 3),
(137, 3),
(138, 3),
(139, 3),
(140, 3),
(141, 3),
(142, 3),
(143, 3),
(144, 3),
(145, 3),
(146, 3),
(147, 3),
(148, 3),
(150, 3),
(151, 3),
(152, 3),
(153, 3),
(154, 3),
(126, 4),
(127, 4),
(130, 4),
(131, 4),
(132, 4),
(133, 4),
(134, 4),
(135, 4),
(136, 4),
(143, 4),
(144, 4);

-- --------------------------------------------------------

--
-- Table structure for table `sys_user`
--

CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL COMMENT 'ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门名称',
  `username` varchar(191) DEFAULT NULL COMMENT '用户名',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `gender` varchar(2) DEFAULT NULL COMMENT '性别',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(191) DEFAULT NULL COMMENT '邮箱',
  `avatar_name` varchar(191) DEFAULT NULL COMMENT '头像地址',
  `avatar_path` varchar(255) DEFAULT NULL COMMENT '头像真实路径',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `is_admin` bit(1) DEFAULT b'0' COMMENT '是否为admin账号',
  `enabled` bigint(20) DEFAULT NULL COMMENT '状态：1启用、0禁用',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `pwd_reset_time` datetime DEFAULT NULL COMMENT '修改密码的时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户' ROW_FORMAT=COMPACT;

--
-- Dumping data for table `sys_user`
--

INSERT INTO `sys_user` (`user_id`, `dept_id`, `username`, `nick_name`, `gender`, `phone`, `email`, `avatar_name`, `avatar_path`, `password`, `is_admin`, `enabled`, `create_by`, `update_by`, `pwd_reset_time`, `create_time`, `update_time`) VALUES
(1, 2, 'admin', '管理员', '男', '18888888888', '201507802@qq.com', 'aaca0f5eb4d2d98a6ce6dffa99f8254b-20230819080313899.png', '/root/Desktop/eladmin/tmp/avatar/aaca0f5eb4d2d98a6ce6dffa99f8254b-20230819080313899.png', '$2a$10$2ToiWw54pR6/saIO8VV1oOMzefWVcTAT4xAl3MQ60VVfqMIQDYR7m', b'1', 1, NULL, 'admin', '2023-03-10 22:10:45', '2018-08-23 09:11:56', '2023-08-19 20:03:14'),
(10, 7, 'text123', 'text123', NULL, '1678685327835', 'text123@a.com', NULL, NULL, '$2a$10$Jtf2kFogsMkV69ySIhICTeF2kMetPbPy1kosvL4el08irAYChOHa6', b'0', 1, 'admin', 'admin', '2023-07-27 00:27:07', '2023-03-13 13:28:49', '2023-03-13 13:28:49'),
(11, 7, 'mtest', 'mtest', NULL, '1680284516232', 'mtest@a.com', NULL, NULL, '$2a$10$IefzPDxm0VXhOsxFNDO.ae.tzfsIjztMiMqd3OHBXNEdj4E4LF8dq', b'0', 1, 'admin', 'admin', '2023-08-20 05:23:29', '2023-04-01 01:41:57', '2023-04-01 04:20:42'),
(12, 7, 'asdasd', 'asdasd', NULL, '1680296790723', 'asdasd@a.com', NULL, NULL, '$2a$10$XfVDyPZwxt/BA1WEWoSc0ubNoQbDkGWxLzNV0VeEkBK81bUUnMmq.', b'0', 1, 'mtest', 'admin', '2023-04-17 15:23:43', '2023-04-01 05:06:31', '2023-04-17 15:23:05'),
(13, 7, 'asdfadsf', 'asdfadsf', NULL, '1680626860815', 'asdfadsf@a.com', NULL, NULL, '$2a$10$EQWwqzl.5Lm1BefGA1jmkOdAbq7G6tSGFaN538RITiQ.KK/.OXqnm', b'0', 1, 'admin', 'admin', NULL, '2023-04-05 00:47:41', '2023-04-18 02:00:33'),
(14, 7, 'a1a23123', 'a1a23123', NULL, '1680627077809', 'a1a23123@a.com', NULL, NULL, '$2a$10$vuG4wFe9epwWwEpogbJnzOA8vS/JuzavvGO9XLFwcXEC4qAnnsAIi', b'0', 1, 'admin', 'admin', NULL, '2023-04-05 00:51:18', '2023-04-05 00:51:18'),
(15, 7, 'aaa1231', 'aaa1231', NULL, '1680627178625', 'aaa1231@a.com', NULL, NULL, '$2a$10$3G9uYE.GwYaEltd0E6/gDO/eaaXGqeOnRKlOLFzGXemEkgmg.1IeK', b'0', 1, 'admin', 'admin', NULL, '2023-04-05 00:52:59', '2023-04-05 00:52:59'),
(16, 7, 'aaa12312', 'aaa12312', NULL, '1680627194083', 'aaa12312@a.com', NULL, NULL, '$2a$10$p22y0CAMTi9TXQJZY9p9POYsfl1632q2ZsXyTRXy1wJ7RybncMtm6', b'0', 1, 'admin', 'admin', NULL, '2023-04-05 00:53:15', '2023-04-17 15:26:28'),
(17, 7, 'aaaaa', 'aaaaa', NULL, '1680627244847', 'aaaaa@a.com', NULL, NULL, '$2a$10$32Kise.AvRc4g3jhBVaEy.ZX45gz0w.hZdwCcYHUwp5HKcpf6f1tS', b'0', 1, 'admin', 'admin', NULL, '2023-04-05 00:54:05', '2023-04-05 00:54:05'),
(18, 7, 'ssssss', 'ssssss', NULL, '1680627278532', 'ssssss@a.com', NULL, NULL, '$2a$10$Y84K3sJMETIp2hjSBK/32u1DOOel0oBbFYUg68RNtXiut9mj/umBu', b'0', 1, 'admin', 'admin', NULL, '2023-04-05 00:54:39', '2023-04-05 00:54:39'),
(19, 7, 'a122222', 'a122222', NULL, '1680627396941', 'a122222@a.com', NULL, NULL, '$2a$10$sRBWNeOaCaK8m6U8im66qeiu4lhs04IWAqk/ytFKJSHEpPDxfifd2', b'0', 1, 'admin', 'admin', NULL, '2023-04-05 00:56:37', '2023-04-05 00:56:37'),
(20, 7, 'aaaaasca', 'aaaaasca', NULL, '1680628032773', 'aaaaasca@a.com', NULL, NULL, '$2a$10$5oWSj2S8lIaW8lAmQyg2IeLaXaETitJ9ArjNTbqXO7jqW5KOVUfl.', b'0', 1, 'admin', 'admin', NULL, '2023-04-05 01:07:14', '2023-04-05 01:07:14'),
(21, 7, 'avszdas', 'avszdas', NULL, '1680628124153', 'avszdas@a.com', NULL, NULL, '$2a$10$g6shBbWNXLmz.xGHt0TtE.lk0XzuWH9sIhmPK0lZ6D8w89aGkZyMe', b'0', 1, 'admin', 'admin', NULL, '2023-04-05 01:08:45', '2023-04-05 01:08:45'),
(22, 7, 'aaaaaa', 'aaaaaa', NULL, '1681716144341', 'aaaaaa@a.com', NULL, NULL, '$2a$10$JwMKsA4g7plr1FpEceSlQuGBrBibZ6JVCD2p2hleJjnmAA1Kegkbq', b'0', 1, 'mtest', 'mtest', NULL, '2023-04-17 15:22:25', '2023-04-17 15:22:25'),
(23, 7, 'xxxxaaa', 'xxxxaaa', NULL, '1681716254069', 'xxxxaaa@a.com', NULL, NULL, '$2a$10$55TYqXIQsDf6TVWWPg3Q0Os/naeJuba/9T.BEn3RpkekCm8PA0qV6', b'0', 1, 'asdasd', 'asdasd', NULL, '2023-04-17 15:24:15', '2023-04-17 15:24:15'),
(24, 7, 'test123', 'test123', NULL, '1681750353043', 'test123@a.com', NULL, NULL, '$2a$10$zU9ajGnCJbKkBQBLINN.O.MstgSe3oGhOoG1eiKnCd4iI4QjYuRr6', b'0', 1, 'mtest', 'mtest', NULL, '2023-04-18 00:52:34', '2023-04-18 00:52:34'),
(25, 7, 'text11', 'text11', NULL, '1681813212137', 'text11@a.com', NULL, NULL, '$2a$10$8sfk0BmjzW0A2EivCdHmJujc21ehnZCjOc2QkvMmIBAn4Lcu5Gkqe', b'0', 1, 'admin', 'admin', '2023-04-20 05:21:01', '2023-04-18 18:20:13', '2023-04-18 18:57:00'),
(26, 7, 's00001', 's00001', NULL, '1681815465680', 's00001@a.com', NULL, NULL, '$2a$10$5gGp77xses63nQ5jVkSMquflodcRem1rbl3AvSoU1CIsxAr4gxyVG', b'0', 1, 'text11', 'text11', NULL, '2023-04-18 18:57:46', '2023-04-18 18:57:46'),
(27, 7, 'ccccc', 'ccccc', NULL, '1681815529046', 'ccccc@a.com', NULL, NULL, '$2a$10$iqNhqGVmzbVj8myXDaNyTeHfm4Dp8hiedXEcVejaQZp6Wuuuv4oKq', b'0', 1, 'text11', 'text11', NULL, '2023-04-18 18:58:49', '2023-04-18 18:58:49'),
(28, 7, 'xxxxss', 'xxxxss', NULL, '1681940218251', 'xxxxss@a.com', NULL, NULL, '$2a$10$SG9ZjpWu7uhSoscDQgFu2O952DZS/DCzyNuhmbAf3LZi/zSLM7/m6', b'0', 1, 'text11', 'text11', '2023-04-20 05:37:44', '2023-04-20 05:36:59', '2023-04-20 05:36:59'),
(1183, 7, 'testaa', 'testaa', NULL, '1687528628479', 'testaa@a.com', NULL, NULL, '$2a$10$5N3cc7TiQJx.v4KOLkPJ7OwvaqH1kHZCccsNX.oDefaKuPhUXytcO', b'0', 1, 'admin', 'admin', NULL, '2023-06-23 21:57:09', '2023-06-23 21:57:09'),
(1184, 7, 'ssdfsdf', 'ssdfsdf', NULL, '1692480358005', 'ssdfsdf@a.com', NULL, NULL, '$2a$10$msj6WVa1RCIvmm1G3uxTSuji3Iy1XgmwPm54TiesjLqAGL/n8r0V6', b'0', 1, 'mtest', 'mtest', NULL, '2023-08-20 05:25:59', '2023-08-20 05:25:59');

-- --------------------------------------------------------

--
-- Table structure for table `sys_users_jobs`
--

CREATE TABLE `sys_users_jobs` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `job_id` bigint(20) NOT NULL COMMENT '岗位ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sys_users_jobs`
--

INSERT INTO `sys_users_jobs` (`user_id`, `job_id`) VALUES
(1, 11);

-- --------------------------------------------------------

--
-- Table structure for table `sys_users_roles`
--

CREATE TABLE `sys_users_roles` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联' ROW_FORMAT=COMPACT;

--
-- Dumping data for table `sys_users_roles`
--

INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES
(1, 1),
(10, 2),
(14, 2),
(15, 2),
(17, 2),
(18, 2),
(19, 2),
(20, 2),
(21, 2),
(22, 2),
(23, 2),
(24, 2),
(26, 2),
(27, 2),
(28, 2),
(29, 2),
(1184, 2),
(11, 3),
(12, 3),
(13, 3),
(16, 3),
(25, 3),
(1183, 3);

-- --------------------------------------------------------

--
-- Table structure for table `tool_alipay_config`
--

CREATE TABLE `tool_alipay_config` (
  `config_id` bigint(20) NOT NULL COMMENT 'ID',
  `app_id` varchar(255) DEFAULT NULL COMMENT '应用ID',
  `charset` varchar(255) DEFAULT NULL COMMENT '编码',
  `format` varchar(255) DEFAULT NULL COMMENT '类型 固定格式json',
  `gateway_url` varchar(255) DEFAULT NULL COMMENT '网关地址',
  `notify_url` varchar(255) DEFAULT NULL COMMENT '异步回调',
  `private_key` text COMMENT '私钥',
  `public_key` text COMMENT '公钥',
  `return_url` varchar(255) DEFAULT NULL COMMENT '回调地址',
  `sign_type` varchar(255) DEFAULT NULL COMMENT '签名方式',
  `sys_service_provider_id` varchar(255) DEFAULT NULL COMMENT '商户号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付宝配置类' ROW_FORMAT=COMPACT;

--
-- Dumping data for table `tool_alipay_config`
--

INSERT INTO `tool_alipay_config` (`config_id`, `app_id`, `charset`, `format`, `gateway_url`, `notify_url`, `private_key`, `public_key`, `return_url`, `sign_type`, `sys_service_provider_id`) VALUES
(1, '2016091700532697', 'utf-8', 'JSON', 'https://openapi.alipaydev.com/gateway.do', 'http://api.auauz.net/api/aliPay/notify', 'MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC5js8sInU10AJ0cAQ8UMMyXrQ+oHZEkVt5lBwsStmTJ7YikVYgbskx1YYEXTojRsWCb+SH/kDmDU4pK/u91SJ4KFCRMF2411piYuXU/jF96zKrADznYh/zAraqT6hvAIVtQAlMHN53nx16rLzZ/8jDEkaSwT7+HvHiS+7sxSojnu/3oV7BtgISoUNstmSe8WpWHOaWv19xyS+Mce9MY4BfseFhzTICUymUQdd/8hXA28/H6osUfAgsnxAKv7Wil3aJSgaJczWuflYOve0dJ3InZkhw5Cvr0atwpk8YKBQjy5CdkoHqvkOcIB+cYHXJKzOE5tqU7inSwVbHzOLQ3XbnAgMBAAECggEAVJp5eT0Ixg1eYSqFs9568WdetUNCSUchNxDBu6wxAbhUgfRUGZuJnnAll63OCTGGck+EGkFh48JjRcBpGoeoHLL88QXlZZbC/iLrea6gcDIhuvfzzOffe1RcZtDFEj9hlotg8dQj1tS0gy9pN9g4+EBH7zeu+fyv+qb2e/v1l6FkISXUjpkD7RLQr3ykjiiEw9BpeKb7j5s7Kdx1NNIzhkcQKNqlk8JrTGDNInbDM6inZfwwIO2R1DHinwdfKWkvOTODTYa2MoAvVMFT9Bec9FbLpoWp7ogv1JMV9svgrcF9XLzANZ/OQvkbe9TV9GWYvIbxN6qwQioKCWO4GPnCAQKBgQDgW5MgfhX8yjXqoaUy/d1VjI8dHeIyw8d+OBAYwaxRSlCfyQ+tieWcR2HdTzPca0T0GkWcKZm0ei5xRURgxt4DUDLXNh26HG0qObbtLJdu/AuBUuCqgOiLqJ2f1uIbrz6OZUHns+bT/jGW2Ws8+C13zTCZkZt9CaQsrp3QOGDx5wKBgQDTul39hp3ZPwGNFeZdkGoUoViOSd5Lhowd5wYMGAEXWRLlU8z+smT5v0POz9JnIbCRchIY2FAPKRdVTICzmPk2EPJFxYTcwaNbVqL6lN7J2IlXXMiit5QbiLauo55w7plwV6LQmKm9KV7JsZs5XwqF7CEovI7GevFzyD3w+uizAQKBgC3LY1eRhOlpWOIAhpjG6qOoohmeXOphvdmMlfSHq6WYFqbWwmV4rS5d/6LNpNdL6fItXqIGd8I34jzql49taCmi+A2nlR/E559j0mvM20gjGDIYeZUz5MOE8k+K6/IcrhcgofgqZ2ZED1ksHdB/E8DNWCswZl16V1FrfvjeWSNnAoGAMrBplCrIW5xz+J0Hm9rZKrs+AkK5D4fUv8vxbK/KgxZ2KaUYbNm0xv39c+PZUYuFRCz1HDGdaSPDTE6WeWjkMQd5mS6ikl9hhpqFRkyh0d0fdGToO9yLftQKOGE/q3XUEktI1XvXF0xyPwNgUCnq0QkpHyGVZPtGFxwXiDvpvgECgYA5PoB+nY8iDiRaJNko9w0hL4AeKogwf+4TbCw+KWVEn6jhuJa4LFTdSqp89PktQaoVpwv92el/AhYjWOl/jVCm122f9b7GyoelbjMNolToDwe5pF5RnSpEuDdLy9MfE8LnE3PlbE7E5BipQ3UjSebkgNboLHH/lNZA5qvEtvbfvQ==', 'MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAut9evKRuHJ/2QNfDlLwvN/S8l9hRAgPbb0u61bm4AtzaTGsLeMtScetxTWJnVvAVpMS9luhEJjt+Sbk5TNLArsgzzwARgaTKOLMT1TvWAK5EbHyI+eSrc3s7Awe1VYGwcubRFWDm16eQLv0k7iqiw+4mweHSz/wWyvBJVgwLoQ02btVtAQErCfSJCOmt0Q/oJQjj08YNRV4EKzB19+f5A+HQVAKy72dSybTzAK+3FPtTtNen/+b5wGeat7c32dhYHnGorPkPeXLtsqqUTp1su5fMfd4lElNdZaoCI7osZxWWUo17vBCZnyeXc9fk0qwD9mK6yRAxNbrY72Xx5VqIqwIDAQAB', 'http://api.auauz.net/api/aliPay/return', 'RSA2', '2088102176044281');

-- --------------------------------------------------------

--
-- Table structure for table `tool_email_config`
--

CREATE TABLE `tool_email_config` (
  `config_id` bigint(20) NOT NULL COMMENT 'ID',
  `from_user` varchar(255) DEFAULT NULL COMMENT '收件人',
  `host` varchar(255) DEFAULT NULL COMMENT '邮件服务器SMTP地址',
  `pass` varchar(255) DEFAULT NULL COMMENT '密码',
  `port` varchar(255) DEFAULT NULL COMMENT '端口',
  `user` varchar(255) DEFAULT NULL COMMENT '发件者用户名'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='邮箱配置' ROW_FORMAT=COMPACT;

-- --------------------------------------------------------

--
-- Table structure for table `tool_local_storage`
--

CREATE TABLE `tool_local_storage` (
  `storage_id` bigint(20) NOT NULL COMMENT 'ID',
  `real_name` varchar(255) DEFAULT NULL COMMENT '文件真实的名称',
  `name` varchar(255) DEFAULT NULL COMMENT '文件名',
  `suffix` varchar(255) DEFAULT NULL COMMENT '后缀',
  `path` varchar(255) DEFAULT NULL COMMENT '路径',
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  `size` varchar(100) DEFAULT NULL COMMENT '大小',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='本地存储' ROW_FORMAT=COMPACT;

--
-- Dumping data for table `tool_local_storage`
--

-- --------------------------------------------------------

--
-- Table structure for table `tool_qiniu_config`
--

CREATE TABLE `tool_qiniu_config` (
  `config_id` bigint(20) NOT NULL COMMENT 'ID',
  `access_key` text COMMENT 'accessKey',
  `bucket` varchar(255) DEFAULT NULL COMMENT 'Bucket 识别符',
  `host` varchar(255) NOT NULL COMMENT '外链域名',
  `secret_key` text COMMENT 'secretKey',
  `type` varchar(255) DEFAULT NULL COMMENT '空间类型',
  `zone` varchar(255) DEFAULT NULL COMMENT '机房'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='七牛云配置' ROW_FORMAT=COMPACT;

-- --------------------------------------------------------

--
-- Table structure for table `tool_qiniu_content`
--

CREATE TABLE `tool_qiniu_content` (
  `content_id` bigint(20) NOT NULL COMMENT 'ID',
  `bucket` varchar(255) DEFAULT NULL COMMENT 'Bucket 识别符',
  `name` varchar(191) DEFAULT NULL COMMENT '文件名称',
  `size` varchar(255) DEFAULT NULL COMMENT '文件大小',
  `type` varchar(255) DEFAULT NULL COMMENT '文件类型：私有或公开',
  `url` varchar(255) DEFAULT NULL COMMENT '文件url',
  `suffix` varchar(255) DEFAULT NULL COMMENT '文件后缀',
  `update_time` datetime DEFAULT NULL COMMENT '上传或同步的时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='七牛云文件存储' ROW_FORMAT=COMPACT;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `code_column_config`
--
ALTER TABLE `code_column_config`
  ADD PRIMARY KEY (`column_id`) USING BTREE,
  ADD KEY `idx_table_name` (`table_name`);

--
-- Indexes for table `code_gen_config`
--
ALTER TABLE `code_gen_config`
  ADD PRIMARY KEY (`config_id`) USING BTREE,
  ADD KEY `idx_table_name` (`table_name`(100));

--
-- Indexes for table `device_command_task`
--
ALTER TABLE `device_command_task`
  ADD PRIMARY KEY (`task_id`) USING BTREE;

--
-- Indexes for table `device_info`
--
ALTER TABLE `device_info`
  ADD PRIMARY KEY (`device_id`) USING BTREE;

--
-- Indexes for table `device_status`
--
ALTER TABLE `device_status`
  ADD PRIMARY KEY (`device_id`) USING BTREE;

--
-- Indexes for table `distribute`
--
ALTER TABLE `distribute`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `ios_api_certs`
--
ALTER TABLE `ios_api_certs`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ios_apps`
--
ALTER TABLE `ios_apps`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD UNIQUE KEY `unique_mainshort_prefix` (`main_short_prefix`),
  ADD UNIQUE KEY `unique_subshort_prefix` (`sub_short_prefix`),
  ADD KEY `ios_apps_third_id` (`third_id`) COMMENT '三方ID索引';

--
-- Indexes for table `ios_apps_device`
--
ALTER TABLE `ios_apps_device`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD UNIQUE KEY `uid` (`uid`) USING BTREE,
  ADD KEY `app_countindex` (`create_time`,`app_id`);

--
-- Indexes for table `ios_apps_extend`
--
ALTER TABLE `ios_apps_extend`
  ADD UNIQUE KEY `ios_apps_extend_pk` (`app_id`);

--
-- Indexes for table `ios_apps_posted_log`
--
ALTER TABLE `ios_apps_posted_log`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `ios_certificate`
--
ALTER TABLE `ios_certificate`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `ios_config`
--
ALTER TABLE `ios_config`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD UNIQUE KEY `code` (`code`) USING BTREE;

--
-- Indexes for table `ios_device_info`
--
ALTER TABLE `ios_device_info`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `ios_device_info_udid_index` (`udid`) COMMENT 'UDID';

--
-- Indexes for table `ios_domain`
--
ALTER TABLE `ios_domain`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `ios_domain_bind`
--
ALTER TABLE `ios_domain_bind`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `ios_sign_software_distribute`
--
ALTER TABLE `ios_sign_software_distribute`
  ADD PRIMARY KEY (`ios_id`) USING BTREE;

--
-- Indexes for table `ios_sign_software_distribute_status`
--
ALTER TABLE `ios_sign_software_distribute_status`
  ADD PRIMARY KEY (`uuid`) USING BTREE;

--
-- Indexes for table `ios_sign_udid_cert`
--
ALTER TABLE `ios_sign_udid_cert`
  ADD PRIMARY KEY (`cert_id`) USING BTREE;

--
-- Indexes for table `ios_udid_list`
--
ALTER TABLE `ios_udid_list`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `jl` (`jl`) USING BTREE COMMENT '计量索引',
  ADD KEY `ios_udid_list__index_in_day` (`in_day`) USING BTREE COMMENT '天数默认索引';

--
-- Indexes for table `ios_user_charge_log`
--
ALTER TABLE `ios_user_charge_log`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `ios_user_extend`
--
ALTER TABLE `ios_user_extend`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `ios_user_score_log`
--
ALTER TABLE `ios_user_score_log`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `mnt_app`
--
ALTER TABLE `mnt_app`
  ADD PRIMARY KEY (`app_id`) USING BTREE;

--
-- Indexes for table `mnt_database`
--
ALTER TABLE `mnt_database`
  ADD PRIMARY KEY (`db_id`) USING BTREE;

--
-- Indexes for table `mnt_deploy`
--
ALTER TABLE `mnt_deploy`
  ADD PRIMARY KEY (`deploy_id`) USING BTREE,
  ADD KEY `FK6sy157pseoxx4fmcqr1vnvvhy` (`app_id`) USING BTREE;

--
-- Indexes for table `mnt_deploy_history`
--
ALTER TABLE `mnt_deploy_history`
  ADD PRIMARY KEY (`history_id`) USING BTREE;

--
-- Indexes for table `mnt_deploy_server`
--
ALTER TABLE `mnt_deploy_server`
  ADD PRIMARY KEY (`deploy_id`,`server_id`) USING BTREE,
  ADD KEY `FKeaaha7jew9a02b3bk9ghols53` (`server_id`) USING BTREE;

--
-- Indexes for table `mnt_server`
--
ALTER TABLE `mnt_server`
  ADD PRIMARY KEY (`server_id`) USING BTREE,
  ADD KEY `idx_ip` (`ip`);

--
-- Indexes for table `sys_dept`
--
ALTER TABLE `sys_dept`
  ADD PRIMARY KEY (`dept_id`) USING BTREE,
  ADD KEY `inx_pid` (`pid`),
  ADD KEY `inx_enabled` (`enabled`);

--
-- Indexes for table `sys_dict`
--
ALTER TABLE `sys_dict`
  ADD PRIMARY KEY (`dict_id`) USING BTREE;

--
-- Indexes for table `sys_dict_detail`
--
ALTER TABLE `sys_dict_detail`
  ADD PRIMARY KEY (`detail_id`) USING BTREE,
  ADD KEY `FK5tpkputc6d9nboxojdbgnpmyb` (`dict_id`) USING BTREE;

--
-- Indexes for table `sys_job`
--
ALTER TABLE `sys_job`
  ADD PRIMARY KEY (`job_id`) USING BTREE,
  ADD UNIQUE KEY `uniq_name` (`name`),
  ADD KEY `inx_enabled` (`enabled`);

--
-- Indexes for table `sys_log`
--
ALTER TABLE `sys_log`
  ADD PRIMARY KEY (`log_id`) USING BTREE,
  ADD KEY `log_create_time_index` (`create_time`),
  ADD KEY `inx_log_type` (`log_type`);

--
-- Indexes for table `sys_menu`
--
ALTER TABLE `sys_menu`
  ADD PRIMARY KEY (`menu_id`) USING BTREE,
  ADD UNIQUE KEY `uniq_title` (`title`),
  ADD UNIQUE KEY `uniq_name` (`name`),
  ADD KEY `inx_pid` (`pid`);

--
-- Indexes for table `sys_quartz_job`
--
ALTER TABLE `sys_quartz_job`
  ADD PRIMARY KEY (`job_id`) USING BTREE,
  ADD KEY `inx_is_pause` (`is_pause`);

--
-- Indexes for table `sys_quartz_log`
--
ALTER TABLE `sys_quartz_log`
  ADD PRIMARY KEY (`log_id`) USING BTREE;

--
-- Indexes for table `sys_role`
--
ALTER TABLE `sys_role`
  ADD PRIMARY KEY (`role_id`) USING BTREE,
  ADD UNIQUE KEY `uniq_name` (`name`),
  ADD KEY `role_name_index` (`name`);

--
-- Indexes for table `sys_roles_depts`
--
ALTER TABLE `sys_roles_depts`
  ADD PRIMARY KEY (`role_id`,`dept_id`) USING BTREE,
  ADD KEY `FK7qg6itn5ajdoa9h9o78v9ksur` (`dept_id`) USING BTREE;

--
-- Indexes for table `sys_roles_menus`
--
ALTER TABLE `sys_roles_menus`
  ADD PRIMARY KEY (`menu_id`,`role_id`) USING BTREE,
  ADD KEY `FKcngg2qadojhi3a651a5adkvbq` (`role_id`) USING BTREE;

--
-- Indexes for table `sys_user`
--
ALTER TABLE `sys_user`
  ADD PRIMARY KEY (`user_id`) USING BTREE,
  ADD UNIQUE KEY `UK_kpubos9gc2cvtkb0thktkbkes` (`email`) USING BTREE,
  ADD UNIQUE KEY `username` (`username`) USING BTREE,
  ADD UNIQUE KEY `uniq_username` (`username`),
  ADD UNIQUE KEY `uniq_email` (`email`),
  ADD KEY `FK5rwmryny6jthaaxkogownknqp` (`dept_id`) USING BTREE,
  ADD KEY `FKpq2dhypk2qgt68nauh2by22jb` (`avatar_name`) USING BTREE,
  ADD KEY `inx_enabled` (`enabled`);

--
-- Indexes for table `sys_users_jobs`
--
ALTER TABLE `sys_users_jobs`
  ADD PRIMARY KEY (`user_id`,`job_id`);

--
-- Indexes for table `sys_users_roles`
--
ALTER TABLE `sys_users_roles`
  ADD PRIMARY KEY (`user_id`,`role_id`) USING BTREE,
  ADD KEY `FKq4eq273l04bpu4efj0jd0jb98` (`role_id`) USING BTREE;

--
-- Indexes for table `tool_alipay_config`
--
ALTER TABLE `tool_alipay_config`
  ADD PRIMARY KEY (`config_id`) USING BTREE;

--
-- Indexes for table `tool_email_config`
--
ALTER TABLE `tool_email_config`
  ADD PRIMARY KEY (`config_id`) USING BTREE;

--
-- Indexes for table `tool_local_storage`
--
ALTER TABLE `tool_local_storage`
  ADD PRIMARY KEY (`storage_id`) USING BTREE;

--
-- Indexes for table `tool_qiniu_config`
--
ALTER TABLE `tool_qiniu_config`
  ADD PRIMARY KEY (`config_id`) USING BTREE;

--
-- Indexes for table `tool_qiniu_content`
--
ALTER TABLE `tool_qiniu_content`
  ADD PRIMARY KEY (`content_id`) USING BTREE,
  ADD UNIQUE KEY `uniq_name` (`name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `code_column_config`
--
ALTER TABLE `code_column_config`
  MODIFY `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID', AUTO_INCREMENT=512;

--
-- AUTO_INCREMENT for table `code_gen_config`
--
ALTER TABLE `code_gen_config`
  MODIFY `config_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID', AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `distribute`
--
ALTER TABLE `distribute`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ios_api_certs`
--
ALTER TABLE `ios_api_certs`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `ios_apps`
--
ALTER TABLE `ios_apps`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键', AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `ios_apps_device`
--
ALTER TABLE `ios_apps_device`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键', AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `ios_apps_posted_log`
--
ALTER TABLE `ios_apps_posted_log`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键', AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `ios_certificate`
--
ALTER TABLE `ios_certificate`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键', AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `ios_config`
--
ALTER TABLE `ios_config`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统设置', AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT for table `ios_device_info`
--
ALTER TABLE `ios_device_info`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键';

--
-- AUTO_INCREMENT for table `ios_domain`
--
ALTER TABLE `ios_domain`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键', AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `ios_domain_bind`
--
ALTER TABLE `ios_domain_bind`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键', AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `ios_udid_list`
--
ALTER TABLE `ios_udid_list`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键';

--
-- AUTO_INCREMENT for table `ios_user_charge_log`
--
ALTER TABLE `ios_user_charge_log`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键';

--
-- AUTO_INCREMENT for table `ios_user_extend`
--
ALTER TABLE `ios_user_extend`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键', AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `ios_user_score_log`
--
ALTER TABLE `ios_user_score_log`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键';

--
-- AUTO_INCREMENT for table `mnt_app`
--
ALTER TABLE `mnt_app`
  MODIFY `app_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID';

--
-- AUTO_INCREMENT for table `mnt_deploy`
--
ALTER TABLE `mnt_deploy`
  MODIFY `deploy_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID';

--
-- AUTO_INCREMENT for table `mnt_server`
--
ALTER TABLE `mnt_server`
  MODIFY `server_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID';

--
-- AUTO_INCREMENT for table `sys_dept`
--
ALTER TABLE `sys_dept`
  MODIFY `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID', AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `sys_dict`
--
ALTER TABLE `sys_dict`
  MODIFY `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID', AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `sys_dict_detail`
--
ALTER TABLE `sys_dict_detail`
  MODIFY `detail_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID', AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `sys_job`
--
ALTER TABLE `sys_job`
  MODIFY `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID', AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `sys_log`
--
ALTER TABLE `sys_log`
  MODIFY `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID';

--
-- AUTO_INCREMENT for table `sys_menu`
--
ALTER TABLE `sys_menu`
  MODIFY `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID', AUTO_INCREMENT=158;

--
-- AUTO_INCREMENT for table `sys_quartz_job`
--
ALTER TABLE `sys_quartz_job`
  MODIFY `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID', AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `sys_quartz_log`
--
ALTER TABLE `sys_quartz_log`
  MODIFY `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID', AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT for table `sys_role`
--
ALTER TABLE `sys_role`
  MODIFY `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID', AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `sys_user`
--
ALTER TABLE `sys_user`
  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID', AUTO_INCREMENT=1185;

--
-- AUTO_INCREMENT for table `tool_local_storage`
--
ALTER TABLE `tool_local_storage`
  MODIFY `storage_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID', AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `tool_qiniu_content`
--
ALTER TABLE `tool_qiniu_content`
  MODIFY `content_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID';
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
