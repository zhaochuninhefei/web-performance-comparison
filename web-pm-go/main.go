package main

import "github.com/zhaochuninhefei/web-performance-comparison/web-pm-go/route"

func main() {
	// 注册web路由器
	router := route.RegisterWebRoute()
	// 启动http服务，监听端口 18080
	err := router.Run(":18080")
	if err != nil {
		return
	}
}
