package mysql

import (
	"gitee.com/zhaochuninhefei/zcgolog/zclog"
	"testing"
)

func TestConnectMysqlByDefault(t *testing.T) {
	mysqlClient, err := ConnectMysqlByDefault("172.17.13.44", "3306", "root", "overseas", "db_web_pm")
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
