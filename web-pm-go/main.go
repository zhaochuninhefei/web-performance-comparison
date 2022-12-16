package main

import (
	"github.com/zhaochuninhefei/web-performance-comparison/web-pm-go/initialize"
	"github.com/zhaochuninhefei/web-performance-comparison/web-pm-go/route"
	"github.com/zhaochuninhefei/web-performance-comparison/web-pm-go/utils"
)

func main() {
	// 读取配置文件
	initialize.InitAppConfig()
	// 注册web路由器
	router := route.RegisterWebRoute()
	// 启动http服务，监听端口
	err := router.Run(":" + utils.AppConfig.App.Port)
	if err != nil {
		return
	}
}
