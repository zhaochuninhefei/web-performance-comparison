package test

import (
	"fmt"
	"github.com/zhaochuninhefei/web-performance-comparison/web-pm-go/config"
	"gopkg.in/yaml.v2"
	"io/fs"
	"io/ioutil"
	"testing"
)

func TestGernateYaml(t *testing.T) {
	fmt.Println("TestGernateYaml")
	appConfigTemp := &config.AppicationConfig{
		App: config.AppInfo{
			Name:       "",
			Version:    "",
			Port:       "",
			ConfigFile: "",
		},
		Datasource: config.Datasource{
			Host:     "",
			Port:     "",
			User:     "",
			Password: "",
		},
		Redis: config.Redis{
			Host:     "",
			Port:     "",
			User:     "",
			Password: "",
		},
		Log: config.Log{
			LogForbidStdout:   false,
			LogFileDir:        "test",
			LogFileNamePrefix: "",
			LogFileMaxSizeM:   0,
			LogLevelGlobal:    "debug",
			LogLineFormat:     "",
			LogMod:            0,
			LogChannelCap:     0,
			LogChnOverPolicy:  0,
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
