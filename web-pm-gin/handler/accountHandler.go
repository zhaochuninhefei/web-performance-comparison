package handler

import (
	"fmt"
	"gitee.com/zhaochuninhefei/zcgolog/zclog"
	"github.com/gin-gonic/gin"
	"github.com/zhaochuninhefei/web-performance-comparison/web-pm-gin/db/model"
	"github.com/zhaochuninhefei/web-performance-comparison/web-pm-gin/global"
	"net/http"
	"time"
)

// QueryAllAccounts 查询所有帐户
func QueryAllAccounts(c *gin.Context) {
	var acts []model.Account
	global.MySQLClient.Select("*").Find(&acts)
	c.JSON(http.StatusOK, acts)
}

// QueryAccountByID 查询指定帐户
func QueryAccountByID(c *gin.Context) {
	id := c.DefaultQuery("id", "1")
	var act model.Account
	global.MySQLClient.Where("id = ?", id).First(&act)
	c.JSON(http.StatusOK, act)
}

// AddNewAccount 新增帐户
func AddNewAccount(c *gin.Context) {
	var resCd, resMsg string
	var act model.Account
	if c.ShouldBind(&act) == nil {
		//zclog.Infof("新增帐户: %s", act.String())
		act.ActRegisterDate = time.Now()
		result := global.MySQLClient.Create(&act)
		if result.Error != nil {
			zclog.Errorf("Insert 错误: %s", result.Error.Error())
			resCd = "-9"
			resMsg = result.Error.Error()
		} else {
			resCd = "1"
			resMsg = fmt.Sprintf("新增帐户ID: %d", act.ID)
		}
	} else {
		resCd = "-9"
		resMsg = fmt.Sprintf("未能成功接收数据")
		zclog.Errorln(resMsg)
	}
	c.JSON(http.StatusOK, gin.H{
		"resCd":  resCd,
		"resMsg": resMsg,
	})
}
