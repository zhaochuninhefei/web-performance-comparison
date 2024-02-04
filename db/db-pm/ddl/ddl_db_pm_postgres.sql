-- 使用超级用户执行:
CREATE DATABASE db_pm_postgres;

CREATE USER zhaochun1 WITH PASSWORD 'zhaochun@GITHUB';

GRANT ALL PRIVILEGES ON DATABASE db_pm_postgres to zhaochun1;

-- 使用超级用户并切换到 db_pm_postgres 数据库后执行:
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO zhaochun1;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO zhaochun1;
GRANT USAGE, CREATE ON SCHEMA public TO zhaochun1;

-- 使用新创建的用户 zhaochun1 执行:
-- 删除已存在的表
DROP TABLE IF EXISTS tb_order;
DROP TABLE IF EXISTS tb_custom;
DROP TABLE IF EXISTS tb_product;
DROP TABLE IF EXISTS tb_warehouse;

-- 创建订单表
CREATE TABLE tb_order (
  id SERIAL PRIMARY KEY, -- 自增主键
  ord_number VARCHAR(20) NOT NULL UNIQUE, -- 订单编号，唯一约束
  custom_number VARCHAR(20) NOT NULL, -- 客户编号
  product_number VARCHAR(20) NOT NULL, -- 商品编号
  warehouse_number VARCHAR(20) NOT NULL, -- 仓库编号
  ord_status SMALLINT NOT NULL, -- 订单状态
  order_time TIMESTAMP NOT NULL -- 下单时间
);

-- 添加表注释
COMMENT ON TABLE tb_order IS '订单表';

-- 添加字段注释
COMMENT ON COLUMN tb_order.id IS 'ID';
COMMENT ON COLUMN tb_order.ord_number IS '订单编号';
COMMENT ON COLUMN tb_order.custom_number IS '客户编号';
COMMENT ON COLUMN tb_order.product_number IS '商品编号';
COMMENT ON COLUMN tb_order.warehouse_number IS '仓库编号';
COMMENT ON COLUMN tb_order.ord_status IS '订单状态';
COMMENT ON COLUMN tb_order.order_time IS '下单时间';

-- 创建客户表
CREATE TABLE tb_custom (
  id SERIAL PRIMARY KEY, -- 自增主键
  custom_number VARCHAR(20) NOT NULL UNIQUE, -- 客户编号，唯一约束
  custom_name VARCHAR(50) NOT NULL, -- 客户姓名
  custom_phone VARCHAR(20) NOT NULL, -- 客户手机号
  custom_address VARCHAR(200) NOT NULL -- 客户地址
);

-- 添加表注释
COMMENT ON TABLE tb_custom IS '客户表';

-- 添加字段注释
COMMENT ON COLUMN tb_custom.id IS 'ID';
COMMENT ON COLUMN tb_custom.custom_number IS '客户编号';
COMMENT ON COLUMN tb_custom.custom_name IS '客户姓名';
COMMENT ON COLUMN tb_custom.custom_phone IS '客户手机号';
COMMENT ON COLUMN tb_custom.custom_address IS '客户地址';

-- 创建商品表
CREATE TABLE tb_product (
  id SERIAL PRIMARY KEY, -- 自增主键
  product_number VARCHAR(20) NOT NULL, -- 商品编号
  product_name VARCHAR(50) NOT NULL -- 商品名称
);

-- 添加表注释
COMMENT ON TABLE tb_product IS '商品表';

-- 添加字段注释
COMMENT ON COLUMN tb_product.id IS 'ID';
COMMENT ON COLUMN tb_product.product_number IS '商品编号';
COMMENT ON COLUMN tb_product.product_name IS '商品名称';

-- 创建仓库表
CREATE TABLE tb_warehouse (
  id SERIAL PRIMARY KEY, -- 自增主键
  warehouse_number VARCHAR(20) NOT NULL, -- 仓库编号
  warehouse_name VARCHAR(50) NOT NULL -- 仓库名称
);

-- 添加表注释
COMMENT ON TABLE tb_warehouse IS '仓库表';

-- 添加字段注释
COMMENT ON COLUMN tb_warehouse.id IS 'ID';
COMMENT ON COLUMN tb_warehouse.warehouse_number IS '仓库编号';
COMMENT ON COLUMN tb_warehouse.warehouse_name IS '仓库名称';

