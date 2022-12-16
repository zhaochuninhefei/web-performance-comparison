package main

import (
	"gitee.com/zhaochuninhefei/zcgolog/zclog"
	"github.com/zhaochuninhefei/web-performance-comparison/web-pm-go/global"
	"github.com/zhaochuninhefei/web-performance-comparison/web-pm-go/initialize"
	"github.com/zhaochuninhefei/web-performance-comparison/web-pm-go/route"
)

func main() {
	// 读取配置文件
	initialize.InitAppConfig("config/app.yaml")

	zclog.ClearDir("logs")
	zcgologConfig := &zclog.Config{
		LogFileDir:        "logs",
		LogFileNamePrefix: "tlstest",
		LogMod:            zclog.LOG_MODE_SERVER,
		LogLevelGlobal:    zclog.LOG_LEVEL_DEBUG,
	}
	zclog.InitLogger(zcgologConfig)

	// 注册web路由器
	router := route.RegisterWebRoute()
	// 启动http服务，监听端口
	err := router.Run(":" + global.AppConfig.App.Port)
	if err != nil {
		return
	}
}
