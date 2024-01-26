use `db_web_pm`;

DROP TABLE accounts ;

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
) ENGINE=InnoDB COMMENT='帐号表';

DROP TABLE `post` ;

CREATE TABLE `post` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `act_id` bigint unsigned NOT NULL COMMENT '所属账户ID',
  `title` varchar(100) NOT NULL COMMENT '帖子标题',
  `content` varchar(255) NOT NULL COMMENT '帖子内容',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `post_unique01` (`act_id`, `title`)
) ENGINE=InnoDB COMMENT='发帖表';



-- db_web_pm.customers definition
DROP TABLE `customers` ;
CREATE TABLE `customers` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `created_at` datetime(3) DEFAULT NULL,
  `updated_at` datetime(3) DEFAULT NULL,
  `deleted_at` datetime(3) DEFAULT NULL,
  `ctm_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户名',
  `ctm_email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户邮箱',
  `ctm_phone` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户手机号',
  `ctm_addr` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户地址',
  `ctm_level` int NOT NULL COMMENT '客户级别',
  PRIMARY KEY (`id`),
  KEY `idx_customers_deleted_at` (`deleted_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='客户表';


-- db_web_pm.products definition
DROP TABLE `products` ;
CREATE TABLE `products` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `created_at` datetime(3) DEFAULT NULL,
  `updated_at` datetime(3) DEFAULT NULL,
  `deleted_at` datetime(3) DEFAULT NULL,
  `prd_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '产品名',
  `prd_desc` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '产品描述',
  `prd_price` decimal(10,2) NOT NULL COMMENT '产品价格',
  `prd_type` int NOT NULL COMMENT '产品类型',
  PRIMARY KEY (`id`),
  KEY `idx_products_deleted_at` (`deleted_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='产品表';


-- db_web_pm.orders definition
DROP TABLE `orders` ;
CREATE TABLE `orders` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `created_at` datetime(3) DEFAULT NULL,
  `updated_at` datetime(3) DEFAULT NULL,
  `deleted_at` datetime(3) DEFAULT NULL,
  `ord_date` datetime DEFAULT NULL COMMENT '订单日期',
  `ord_status` tinyint unsigned DEFAULT NULL COMMENT '订单状态',
  `ctm_id` bigint NOT NULL COMMENT '订单客户ID',
  `prd_id` bigint NOT NULL COMMENT '订单产品ID',
  `prd_num` int NOT NULL COMMENT '订单产品数量',
  PRIMARY KEY (`id`),
  KEY `idx_orders_deleted_at` (`deleted_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单表';

