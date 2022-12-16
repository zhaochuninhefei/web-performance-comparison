package initialize

import (
	"fmt"
	"github.com/fsnotify/fsnotify"
	"github.com/spf13/viper"
	"github.com/zhaochuninhefei/web-performance-comparison/web-pm-go/config"
	"github.com/zhaochuninhefei/web-performance-comparison/web-pm-go/global"
	"gopkg.in/yaml.v2"
	"io/ioutil"
	"testing"
)

func TestInitAppConfig(t *testing.T) {
	bytesYaml, _ := ioutil.ReadFile("/home/zhaochun/work/sources/github.com/zhaochuninhefei/web-performance-comparison/web-pm-go/config/app.yaml")
	tmpConfig := &config.AppicationConfig{}
	err := yaml.Unmarshal(bytesYaml, &tmpConfig)
	if err != nil {
		fmt.Println(err)
		return
	}
	//fmt.Printf("LogFileDir: %s\n", tmpConfig.LogConfig.Dir)
	tmpConfigYaml, _ := yaml.Marshal(tmpConfig)
	fmt.Printf("应用配置: \n%s\n", tmpConfigYaml)

	InitAppConfig("/home/zhaochun/work/sources/github.com/zhaochuninhefei/web-performance-comparison/web-pm-go/config/app.yaml")
	//fmt.Printf("LogFileDir: %s\n", global.AppConfig.LogConfig.Dir)
	yamlAppConfig, _ := yaml.Marshal(global.AppConfig)
	//zclog.Debugf("当前配置: \n%s", string(yamlAppConfig))
	fmt.Printf("应用配置: \n%s\n", yamlAppConfig)
}

func TestViper(t *testing.T) {
	tmpConfig := &config.AppicationConfig{}

	v := viper.New()
	v.SetConfigFile("/home/zhaochun/work/sources/github.com/zhaochuninhefei/web-performance-comparison/web-pm-go/initialize/testdata/app.yaml")
	if err := v.ReadInConfig(); err != nil {
		panic(fmt.Errorf("配置解析失败:%s\n", err))
	}
	// 动态监测配置文件
	v.WatchConfig()
	v.OnConfigChange(func(in fsnotify.Event) {
		fmt.Println("配置文件发生改变")
		if err := v.Unmarshal(&tmpConfig); err != nil {
			panic(fmt.Errorf("配置重载失败:%s\n", err))
		}
	})
	if err := v.Unmarshal(&tmpConfig); err != nil {
		panic(fmt.Errorf("配置重载失败:%s\n", err))
	}

	tmpConfigYaml, _ := yaml.Marshal(tmpConfig)
	fmt.Printf("应用配置: \n%s\n", tmpConfigYaml)
}
