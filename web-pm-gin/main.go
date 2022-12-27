package main

import (
	"gitee.com/zhaochuninhefei/zcgolog/zclog"
	"github.com/zhaochuninhefei/web-performance-comparison/web-pm-gin/db/mysql"
	"github.com/zhaochuninhefei/web-performance-comparison/web-pm-gin/global"
	"github.com/zhaochuninhefei/web-performance-comparison/web-pm-gin/initialize"
	"github.com/zhaochuninhefei/web-performance-comparison/web-pm-gin/route"
	"gopkg.in/yaml.v2"
)

func main() {
	// 读取配置文件
	initialize.InitAppConfig("config/app.yaml")

	// 配置zcgolog
	logConf := global.AppConfig.Log
	zcgologConfig := &zclog.Config{
		LogForbidStdout:   logConf.LogForbidStdout,
		LogFileDir:        logConf.LogFileDir,
		LogFileNamePrefix: logConf.LogFileNamePrefix,
		LogFileMaxSizeM:   logConf.LogFileMaxSizeM,
		LogLevelGlobal:    zclog.GetLogLevelByStr(logConf.LogLevelGlobal),
		LogLineFormat:     logConf.LogLineFormat,
		LogMod:            logConf.LogMod,
		LogChannelCap:     logConf.LogChannelCap,
		LogChnOverPolicy:  logConf.LogChnOverPolicy,
		LogLevelCtlHost:   logConf.LogLevelCtlHost,
		LogLevelCtlPort:   logConf.LogLevelCtlPort,
	}
	zclog.InitLogger(zcgologConfig)

	zclog.Infof("app_name: %s", global.AppConfig.App.Name)

	yamlAppConfig, _ := yaml.Marshal(global.AppConfig)
	zclog.Debugf("当前配置: \n%s", string(yamlAppConfig))

	// 初始化MySQL客户端
	mysql.InitMySQLClient()

	// 注册web路由器
	router := route.RegisterWebRoute()
	// 启动http服务，监听端口
	err := router.Run(":" + global.AppConfig.App.Port)
	if err != nil {
		zclog.Error(err)
	}
}
