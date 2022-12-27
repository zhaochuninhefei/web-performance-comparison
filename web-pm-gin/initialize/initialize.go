package initialize

import (
	"flag"
	"fmt"
	"gitee.com/zhaochuninhefei/zcgolog/zclog"
	"github.com/fsnotify/fsnotify"
	"github.com/spf13/viper"
	"github.com/zhaochuninhefei/web-performance-comparison/web-pm-gin/global"
	"strings"
)

// InitAppConfig 初始化应用配置
func InitAppConfig(configFilePath string) {
	var configFile string
	// 读取配置文件优先级: 命令行 > 默认值
	flag.StringVar(&configFile, "app_config", configFilePath, "应用配置文件路径")
	flag.Parse()
	if len(configFile) == 0 {
		// 读取默认配置文件
		panic("配置文件不存在！")
	}
	fmt.Printf("configFile: %s\n", configFile)
	// 读取配置文件
	v := viper.New()
	v.AutomaticEnv()
	v.SetEnvKeyReplacer(strings.NewReplacer(".", "_", "-", "_"))
	v.SetConfigFile(configFile)
	if err := v.ReadInConfig(); err != nil {
		panic(fmt.Errorf("配置解析失败:%s\n", err))
	}
	// 动态监测配置文件
	v.WatchConfig()
	v.OnConfigChange(func(in fsnotify.Event) {
		zclog.Info("配置文件发生改变")
		if err := v.Unmarshal(&global.AppConfig); err != nil {
			panic(fmt.Errorf("配置重载失败:%s\n", err))
		}
	})
	if err := v.Unmarshal(&global.AppConfig); err != nil {
		panic(fmt.Errorf("配置重载失败:%s\n", err))
	}
	// 设置配置文件
	global.AppConfig.App.ConfigFile = configFile
}
