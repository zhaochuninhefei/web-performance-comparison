CREATE DATABASE `db_web_pm`;

use `db_web_pm`;

CREATE TABLE `accounts` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `created_at` datetime(3) DEFAULT NULL,
  `updated_at` datetime(3) DEFAULT NULL,
  `deleted_at` datetime(3) DEFAULT NULL,
  `act_name` varchar(100) NOT NULL COMMENT '帐户名',
  `act_pwd` varchar(255) NOT NULL COMMENT '帐户密码',
  `act_nick_name` varchar(255) DEFAULT NULL COMMENT '帐户昵称',
  `act_introduction` varchar(255) DEFAULT NULL COMMENT '帐户介绍',
  `act_status` tinyint unsigned DEFAULT NULL COMMENT '帐户状态',
  `act_register_date` datetime(3) DEFAULT NULL COMMENT '帐户注册时间',
  PRIMARY KEY (`id`),
  KEY `idx_accounts_deleted_at` (`deleted_at`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='帐号表';
