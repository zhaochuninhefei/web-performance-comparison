package generator

import (
	"fmt"
	"github.com/zhaochuninhefei/web-performance-comparison/web-pm-gin/config"
	"gopkg.in/yaml.v2"
	"io/fs"
	"io/ioutil"
	"testing"
)

// TestGernateAppYaml 用于生成`web-pm-gin`的应用配置文件
func TestGernateAppYaml(t *testing.T) {
	fmt.Println("TestGernateAppYaml")
	appConfigTemp := &config.AppicationConfig{
		App: config.AppInfo{
			Name:       "web-pm-gin",
			Version:    "0.0.1",
			Port:       "18080",
			ConfigFile: "",
		},
		Datasource: config.Datasource{
			DBType:   "mysql",
			Host:     "localhost",
			Port:     "3307",
			DBName:   "db_web_pm",
			User:     "zhaochun1",
			Password: "zhaochun@GITHUB",
		},
		DBConnPool: config.DBConnPool{
			MaxOpenConns:       100,
			MaxIdleConns:       10,
			ConnMaxLifetimeMin: 60,
			ConnMaxIdleTimeMin: 10,
		},
		Redis: config.Redis{
			Host:     "localhost",
			Port:     "",
			User:     "",
			Password: "",
		},
		Log: config.Log{
			LogForbidStdout:   false,
			LogFileDir:        "logs",
			LogFileNamePrefix: "web-pm-gin",
			LogFileMaxSizeM:   2,
			LogLevelGlobal:    "debug",
			LogLineFormat:     "",
			LogMod:            2,
			LogChannelCap:     4096,
			LogChnOverPolicy:  1,
			LogLevelCtlHost:   "",
			LogLevelCtlPort:   "",
		},
	}
	yamlData, err := yaml.Marshal(appConfigTemp)
	if err != nil {
		fmt.Printf("yaml转换错误: %s\n", err)
	}
	fmt.Printf("yaml内容：\n%s\n", string(yamlData))

	err = ioutil.WriteFile("./appConfigTemp.yaml", yamlData, fs.ModePerm)
	if err != nil {
		fmt.Printf("yaml文件写入时发生错误: %s\n", err)
	}
}
