package mysql

import (
	"fmt"
	"gitee.com/zhaochuninhefei/zcgolog/zclog"
	"github.com/zhaochuninhefei/web-performance-comparison/web-pm-gin/db/model"
	"math/rand"
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
	err = mysqlClient.Set("gorm:table_options", "ENGINE=InnoDB COMMENT='客户表'").AutoMigrate(&model.Customer{})
	if err != nil {
		zclog.Errorln(err)
		return
	}
	err = mysqlClient.Set("gorm:table_options", "ENGINE=InnoDB COMMENT='产品表'").AutoMigrate(&model.Product{})
	if err != nil {
		zclog.Errorln(err)
		return
	}
	err = mysqlClient.Set("gorm:table_options", "ENGINE=InnoDB COMMENT='订单表'").AutoMigrate(&model.Order{})
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

func TestTruncateTable(t *testing.T) {
	mysqlClient, err := ConnectMysqlByDefault("192.168.60.60", "3306", "zhaochun1", "zhaochun@GITHUB", "db_web_pm")
	if err != nil {
		zclog.Errorln(err)
		return
	}
	mysqlClient.Exec("TRUNCATE TABLE accounts")
}

func TestTruncateMariaTable(t *testing.T) {
	mysqlClient, err := ConnectMysqlByDefault("192.168.60.60", "3308", "zhaochun1", "zhaochun@GITHUB", "db_web_pm")
	if err != nil {
		zclog.Errorln(err)
		return
	}
	mysqlClient.Exec("TRUNCATE TABLE accounts")
}

func TestInsert1000(t *testing.T) {
	mysqlClient, err := ConnectMysqlByDefault("192.168.60.60", "3306", "zhaochun1", "zhaochun@GITHUB", "db_web_pm")
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

func TestInsertMaria1000(t *testing.T) {
	mysqlClient, err := ConnectMysqlByDefault("192.168.60.60", "3308", "zhaochun1", "zhaochun@GITHUB", "db_web_pm")
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

func TestInsertMaria100000(t *testing.T) {
	mysqlClient, err := ConnectMysqlByDefault("192.168.60.60", "3308", "zhaochun1", "zhaochun@GITHUB", "db_web_pm")
	if err != nil {
		zclog.Errorln(err)
		return
	}
	for n := 0; n < 100; n++ {
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
}

func TestInsert1000_mysql5(t *testing.T) {
	mysqlClient, err := ConnectMysqlByDefault("localhost", "3306", "zhaochun1", "zhaochun@GITHUB", "db_web_pm")
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

func TestPrepareCPOIntoMariadb(t *testing.T) {
	rand.Seed(time.Now().Unix())
	mysqlClient, err := ConnectMysqlByDefault("localhost", "3308", "zhaochun1", "zhaochun@GITHUB", "db_web_pm")
	if err != nil {
		zclog.Errorln(err)
		return
	}
	mysqlClient.Exec("TRUNCATE TABLE customers")
	// 插入10万个客户数据到客户表 customers
	for i := 0; i < 100; i++ {
		var customers [1000]model.Customer
		for j := 0; j < 1000; j++ {
			ctmNum := i*1000 + j + 1
			customer := model.Customer{
				// 客户名: "客户000001" ~ "客户100000", 番号补0满6位
				CtmName:  fmt.Sprintf("客户%06d", ctmNum),
				CtmEmail: fmt.Sprintf("客户%06d@github.com", ctmNum),
				CtmPhone: fmt.Sprintf("13899%06d", ctmNum),
				CtmAddr:  fmt.Sprintf("地址%06d", ctmNum),
				CtmLevel: ctmNum % 5,
			}
			customers[j] = customer
		}
		result := mysqlClient.Create(&customers)
		zclog.Infof("Insert 件数: %d", result.RowsAffected)
	}

	mysqlClient.Exec("TRUNCATE TABLE products")
	// 插入1万个产品数据到产品表 products
	for i := 0; i < 10; i++ {
		var products [1000]model.Product
		for j := 0; j < 1000; j++ {
			prdNum := i*1000 + j + 1
			product := model.Product{
				// 产品名: "产品000001" ~ "产品100000", 番号补0满6位
				PrdName:  fmt.Sprintf("产品%06d", prdNum),
				PrdDesc:  fmt.Sprintf("产品描述%06d", prdNum),
				PrdPrice: float64(prdNum),
				PrdType:  prdNum % 5,
			}
			products[j] = product
		}
		result := mysqlClient.Create(&products)
		zclog.Infof("Insert 件数: %d", result.RowsAffected)
	}

	mysqlClient.Exec("TRUNCATE TABLE orders")
	// 插入100万个订单数据到订单表 orders
	for i := 0; i < 1000; i++ {
		var orders [1000]model.Order
		for j := 0; j < 1000; j++ {
			order := model.Order{
				OrdDate:   time.Now(),
				OrdStatus: uint8(i % 5),
				// 从 10万个客户ID中随机选取一个
				CtmID:  uint(rand.Intn(100000) + 1),
				PrdID:  uint(rand.Intn(10000) + 1),
				PrdNum: rand.Intn(100) + 1,
			}
			orders[j] = order
		}
		result := mysqlClient.Create(&orders)
		zclog.Infof("Insert 件数: %d", result.RowsAffected)
	}
}
