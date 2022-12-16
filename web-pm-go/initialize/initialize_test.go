package initialize

import (
	"fmt"
	"github.com/zhaochuninhefei/web-performance-comparison/web-pm-go/utils"
	"testing"
)

func TestInitAppConfig(t *testing.T) {
	InitAppConfig("../config/app.yaml")

	fmt.Printf("应用配置: \n%v\n", utils.AppConfig)
}
