package route

// webRouters.go 用于注册web请求路由

import (
	"github.com/gin-gonic/gin"
	"github.com/zhaochuninhefei/web-performance-comparison/web-pm-gin/handler"
)

// RegisterWebRoute 注册web路由
func RegisterWebRoute() *gin.Engine {
	// 获取路由引擎
	router := gin.Default()

	// 注册Asset相关资源的URI及其对应处理器
	astRouter := router.Group("/asset")
	// 查看所有资产URI: `/asset/list`
	astRouter.GET("/list", handler.QueryAllAssets)
	// 查看指定资产URI: `/asset/query`, 通过url传参: `/asset/query?id=xxx`
	astRouter.GET("/query", handler.QueryAssetByID)
	// 修改指定资产URI: `/asset/modify `, 通过表单提交或json传参
	astRouter.POST("/modify", handler.ModifyAsset)

	// 注册Account相关资源的URI及其对应处理器
	actRouter := router.Group("/account")
	// 查看所有帐户URI: `/account/list`
	actRouter.GET("/list", handler.QueryAllAccounts)
	// 查询指定帐户URI: `/account/query`, 通过url传参: `/account/query?id=xxx`
	actRouter.GET("/query", handler.QueryAccountByID)
	// 新增帐户: `/account/add`, 通过表单提交或json传参
	actRouter.POST("/add", handler.AddNewAccount)

	return router
}
