CREATE DATABASE db_web_pm;

CREATE USER zhaochun1 WITH PASSWORD 'zhaochun@GITHUB';

GRANT ALL PRIVILEGES ON DATABASE db_web_pm to zhaochun1;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO zhaochun1;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO zhaochun1;


CREATE TABLE accounts (
  id bigserial PRIMARY KEY,
  created_at timestamp(3),
  updated_at timestamp(3), 
  deleted_at timestamp(3),
  act_name varchar(100) NOT NULL,
  act_pwd varchar(255) NOT NULL,
  act_nick_name varchar(255),
  act_introduction varchar(255),
  act_status smallint,
  act_register_date timestamp(3)
);

CREATE INDEX idx_accounts_deleted_at ON accounts (deleted_at);

COMMENT ON TABLE accounts IS '帐号表';

