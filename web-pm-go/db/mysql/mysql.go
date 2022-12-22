package mysql

import (
	"fmt"
	"gitee.com/zhaochuninhefei/zcgolog/zclog"
	"gorm.io/driver/mysql"
	"gorm.io/gorm"
	"time"
)

func InitMySQLClient() {
	zclog.Info("初始化mysql客户端")
	mysqlClient, err := ConnectMysqlByDefault("172.17.13.44", "3306", "zhaochun1", "zhaochun@GITHUB", "db_web_pm")
	if err != nil {
		zclog.Errorln(err)
		return
	}
	sqlDB, err := mysqlClient.DB()
	if err != nil {
		zclog.Errorln(err)
		return
	}
	// 最大连接数
	sqlDB.SetMaxOpenConns(100)
	// 最大空闲连接数
	sqlDB.SetMaxIdleConns(10)
	// 连接可复用最大时间
	sqlDB.SetConnMaxLifetime(time.Hour)
	// 连接空闲最大时间
	sqlDB.SetConnMaxIdleTime(time.Minute * 10)
}

// ConnectMysqlByDefault MySQL默认连接
func ConnectMysqlByDefault(host, port, user, pass, dbname string) (*gorm.DB, error) {
	// user:pass@tcp(127.0.0.1:3306)/dbname?charset=utf8mb4&parseTime=True&loc=Local
	dsn := fmt.Sprintf("%s:%s@tcp(%s:%s)/%s?charset=utf8mb4&parseTime=True&loc=Local",
		user, pass, host, port, dbname)
	return gorm.Open(mysql.Open(dsn), &gorm.Config{})
}
