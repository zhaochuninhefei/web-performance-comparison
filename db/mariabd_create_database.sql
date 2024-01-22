CREATE DATABASE `db_web_pm`;

create user 'zhaochun1'@'%' identified by 'zhaochun@GITHUB';

grant all privileges on db_web_pm.* to 'zhaochun1'@'%' with grant option;

flush privileges;
