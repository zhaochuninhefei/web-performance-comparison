package handler

import (
	"fmt"
	"github.com/gin-gonic/gin"
	"log"
	"net/http"
)

func QueryAllAssets(c *gin.Context) {
	c.JSON(http.StatusOK, assets)
}

func QueryAssetByID(c *gin.Context) {
	id := c.DefaultQuery("id", "1")
	ast := astMp[id]
	c.JSON(http.StatusOK, ast)
}

func ModifyAsset(c *gin.Context) {
	//log.Printf("尝试获取表单字段 desc : %s\n", c.PostForm("Desc"))

	var ast Asset
	// ShouldBind 支持JSON/XML/form-data，但使用`form-data`传输表单数据时，注意表单字段应与struct字段名保持一致，而并非其json字段名。
	if c.ShouldBind(&ast) == nil {
		log.Printf("修改目标: %s\n", ast.String())
	}
	c.JSON(http.StatusOK, gin.H{
		"resCd":  "1",
		"resMsg": ast.String(),
	})
}

var assets = []Asset{
	{ID: 1, Name: "asset001", Desc: "测试资产001"},
	{ID: 2, Name: "asset002", Desc: "测试资产002"},
}

var astMp = map[string]Asset{
	"1": {ID: 1, Name: "asset001", Desc: "测试资产001"},
	"2": {ID: 2, Name: "asset002", Desc: "测试资产002"},
}

type Asset struct {
	ID   int64  `json:"id"`
	Name string `json:"name"`
	Desc string `json:"desc"`
}

func (a *Asset) String() string {
	return fmt.Sprintf("[ID: %d, Name: %s, Desc: %s]", a.ID, a.Name, a.Desc)
}
