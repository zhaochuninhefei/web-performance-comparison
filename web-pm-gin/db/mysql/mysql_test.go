package mysql

import (
	"gitee.com/zhaochuninhefei/zcgolog/zclog"
	"github.com/zhaochuninhefei/web-performance-comparison/web-pm-gin/db/model"
	"testing"
	"time"
)

func TestConnectMysqlByDefault(t *testing.T) {
	mysqlClient, err := ConnectMysqlByDefault("localhost", "3307", "zhaochun1", "zhaochun@GITHUB", "db_web_pm")
	if err != nil {
		zclog.Errorln(err)
		return
	}
	now := mysqlClient.NowFunc()
	zclog.Infof("数据库时间: %s", now)

	row := mysqlClient.Debug().Raw("select now()").Row()
	var nowStr string
	err = row.Scan(&nowStr)
	if err != nil {
		zclog.Errorln(err)
		return
	}
	zclog.Infof("select now(): %s", nowStr)
}

func TestCreatTable(t *testing.T) {
	mysqlClient, err := ConnectMysqlByDefault("localhost", "3307", "zhaochun1", "zhaochun@GITHUB", "db_web_pm")
	if err != nil {
		zclog.Errorln(err)
		return
	}
	err = mysqlClient.Set("gorm:table_options", "ENGINE=InnoDB COMMENT='帐号表'").AutoMigrate(&model.Account{})
	if err != nil {
		zclog.Errorln(err)
		return
	}
	zclog.Info("TestCreatTable over.")
}

func TestInsert(t *testing.T) {
	mysqlClient, err := ConnectMysqlByDefault("localhost", "3307", "zhaochun1", "zhaochun@GITHUB", "db_web_pm")
	if err != nil {
		zclog.Errorln(err)
		return
	}
	act := &model.Account{
		ActName:         "libai",
		ActPwd:          "libai@DATANG",
		ActNickName:     "诗仙太白",
		ActIntroduction: "李白，唐朝诗人，字太白，号青莲居士，世称诗仙。",
		ActStatus:       0,
		ActRegisterDate: time.Now(),
	}
	result := mysqlClient.Create(act)
	zclog.Infof("Insert Result: %s", result)
	zclog.Infof("Insert ID: %d", act.ID)
	zclog.Infof("Insert 件数: %d", result.RowsAffected)
	if result.Error != nil {
		zclog.Infof("Insert 错误: %s", result.Error.Error())
	}

	var acts []model.Account
	result = mysqlClient.Select("*").Find(&acts)
	for _, act := range acts {
		//zclog.Infof("id:%d, act_name:%s, act_nick_name:%s", act.ID, act.ActName, act.ActNickName)
		zclog.Infof("account: %s", act.String())
	}
}

func TestInsert1000(t *testing.T) {
	mysqlClient, err := ConnectMysqlByDefault("localhost", "3307", "zhaochun1", "zhaochun@GITHUB", "db_web_pm")
	if err != nil {
		zclog.Errorln(err)
		return
	}
	var acts [1000]model.Account
	for i := 0; i < 1000; i++ {
		act := model.Account{
			ActName:         "libai",
			ActPwd:          "libai@DATANG",
			ActNickName:     "诗仙太白",
			ActIntroduction: "李白，唐朝诗人，字太白，号青莲居士，世称诗仙。",
			ActStatus:       0,
			ActRegisterDate: time.Now(),
		}
		acts[i] = act
	}
	result := mysqlClient.Create(&acts)
	zclog.Infof("Insert 件数: %d", result.RowsAffected)
}
