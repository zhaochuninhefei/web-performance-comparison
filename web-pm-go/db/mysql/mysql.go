package mysql

import (
	"fmt"
	"gitee.com/zhaochuninhefei/zcgolog/zclog"
	"github.com/zhaochuninhefei/web-performance-comparison/web-pm-go/global"
	"gorm.io/driver/mysql"
	"gorm.io/gorm"
	"time"
)

// InitMySQLClient 初始化MySQL客户端
func InitMySQLClient() {
	zclog.Info("初始化mysql客户端开始")
	// 获取数据源配置
	dsConfig := global.AppConfig.Datasource
	// 获取连接客户端
	mysqlClient, err := ConnectMysqlByDefault(dsConfig.Host, dsConfig.Port, dsConfig.User, dsConfig.Password, dsConfig.DBName)
	if err != nil {
		zclog.Errorln(err)
		return
	}
	// 获取连接池
	dbPool := global.AppConfig.DBConnPool
	// 配置连接池
	sqlDB, err := mysqlClient.DB()
	if err != nil {
		zclog.Errorln(err)
		return
	}
	// 最大连接数
	sqlDB.SetMaxOpenConns(dbPool.MaxOpenConns)
	// 最大空闲连接数
	sqlDB.SetMaxIdleConns(dbPool.MaxIdleConns)
	// 连接可复用最大时间
	sqlDB.SetConnMaxLifetime(time.Minute * time.Duration(dbPool.ConnMaxLifetimeMin))
	// 连接空闲最大时间
	sqlDB.SetConnMaxIdleTime(time.Minute * time.Duration(dbPool.ConnMaxIdleTimeMin))

	// 设置MySQL客户端
	global.MySQLClient = mysqlClient
	zclog.Info("初始化mysql客户端结束")
}

// ConnectMysqlByDefault MySQL默认连接
func ConnectMysqlByDefault(host, port, user, pass, dbname string) (*gorm.DB, error) {
	// user:pass@tcp(127.0.0.1:3306)/dbname?charset=utf8mb4&parseTime=True&loc=Local
	dsn := fmt.Sprintf("%s:%s@tcp(%s:%s)/%s?charset=utf8mb4&parseTime=True&loc=Local",
		user, pass, host, port, dbname)
	return gorm.Open(mysql.Open(dsn), &gorm.Config{})
}
