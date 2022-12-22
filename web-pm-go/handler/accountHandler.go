package handler

import (
	"database/sql"
	"github.com/gin-gonic/gin"
	"github.com/zhaochuninhefei/web-performance-comparison/web-pm-go/db/model"
	"github.com/zhaochuninhefei/web-performance-comparison/web-pm-go/global"
	"net/http"
)

func QueryAllAccounts(c *gin.Context) {
	var acts []model.Account
	global.MySQLClient.Select("*").Find(&acts)
	c.JSON(http.StatusOK, acts)
}

type Account struct {
	ID              uint
	ActName         string       `gorm:"type:varchar(100);not null;comment:帐户名"`
	ActPwd          string       `gorm:"type:varchar(255);not null;comment:帐户密码"`
	ActNickName     string       `gorm:"type:varchar(255);comment:帐户昵称"`
	ActIntroduction string       `gorm:"type:varchar(255);comment:帐户介绍"`
	ActStatus       uint8        `gorm:"comment:帐户状态"`
	ActRegisterDate sql.NullTime `gorm:"size:0;comment:帐户注册时间"`
}
