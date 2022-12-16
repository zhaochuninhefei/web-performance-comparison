package route

import (
	"github.com/gin-gonic/gin"
	"github.com/zhaochuninhefei/web-performance-comparison/web-pm-go/handler"
)

func RegisterWebRoute() *gin.Engine {
	router := gin.Default()

	// Asset相关资源
	astRouter := router.Group("/asset")
	astRouter.GET("/list", handler.QueryAllAssets)
	astRouter.GET("/query", handler.QueryAssetByID)
	astRouter.POST("/modify", handler.ModifyAsset)

	return router
}
