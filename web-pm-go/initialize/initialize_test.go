package initialize

import (
	"fmt"
	"github.com/zhaochuninhefei/web-performance-comparison/web-pm-go/config"
	"gopkg.in/yaml.v3"
	"io/ioutil"
	"testing"
)

func TestInitAppConfig(t *testing.T) {
	bytesYaml, _ := ioutil.ReadFile("../test/appConfigTemp.yaml")
	tmpConfig := &config.AppicationConfig{}
	err := yaml.Unmarshal(bytesYaml, &tmpConfig)
	if err != nil {
		fmt.Println(err)
		return
	}
	fmt.Printf("LogFileDir: %s\n", tmpConfig.Log.LogFileDir)

	//InitAppConfig("../test/appConfigTemp.yaml")
	//fmt.Printf("LogFileDir: %s\n", global.AppConfig.Log.LogFileDir)
	//yamlAppConfig, _ := yaml.Marshal(global.AppConfig)
	////zclog.Debugf("当前配置: \n%s", string(yamlAppConfig))
	//fmt.Printf("应用配置: \n%s\n", yamlAppConfig)
}
