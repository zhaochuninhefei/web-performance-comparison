package global

// global.go 全局定义

import (
	"github.com/zhaochuninhefei/web-performance-comparison/web-pm-go/config"
	"gorm.io/gorm"
)

//goland:noinspection GoUnusedConst,GoSnakeCaseUsage
const (
	// APP_CONFIG_FILE_PATH app配置文件路径，注意，该常量只能在main.go中使用
	APP_CONFIG_FILE_PATH = "config/app.yaml"
)

var (
	// AppConfig 应用配置
	AppConfig config.AppicationConfig
	// MySQLClient MySQL客户端
	MySQLClient *gorm.DB
)
