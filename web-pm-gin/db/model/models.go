package model

import (
	"fmt"
	"gorm.io/gorm"
	"time"
)

type Account struct {
	gorm.Model
	ActName         string    `gorm:"type:varchar(100);not null;comment:帐户名" json:"actName"`
	ActPwd          string    `gorm:"type:varchar(255);not null;comment:帐户密码" json:"actPwd"`
	ActNickName     string    `gorm:"type:varchar(255);comment:帐户昵称" json:"actNickName"`
	ActIntroduction string    `gorm:"type:varchar(255);comment:帐户介绍" json:"actIntroduction"`
	ActStatus       uint8     `gorm:"comment:帐户状态" json:"actStatus"`
	ActRegisterDate time.Time `gorm:"size:0;comment:帐户注册时间" json:"actRegisterDate"`
}

func (act *Account) String() string {
	return fmt.Sprintf(
		"Account{ID:%d, ActName:%s, ActPwd:%s, ActNickName:%s, ActIntroduction:%s, ActStatus:%d, ActRegisterDate:%s}",
		act.ID, act.ActName, act.ActPwd, act.ActNickName, act.ActIntroduction, act.ActStatus, act.ActRegisterDate)
}

type Customer struct {
	gorm.Model
	CtmName  string `gorm:"type:varchar(100);not null;comment:客户名" json:"ctmName"`
	CtmEmail string `gorm:"type:varchar(100);not null;comment:客户邮箱" json:"ctmEmail"`
	CtmPhone string `gorm:"type:varchar(20);not null;comment:客户手机号" json:"ctmPhone"`
	CtmAddr  string `gorm:"type:varchar(255);not null;comment:客户地址" json:"ctmAddr"`
	CtmLevel int    `gorm:"type:int;not null;comment:客户级别" json:"ctmLevel"`
}

func (ctm *Customer) String() string {
	return fmt.Sprintf(
		"Customer{ID:%d, CtmName:%s, CtmEmail:%s, CtmPhone:%s, CtmAddr:%s, CtmLevel:%d}",
		ctm.ID, ctm.CtmName, ctm.CtmEmail, ctm.CtmPhone, ctm.CtmAddr, ctm.CtmLevel)
}

type Product struct {
	gorm.Model
	PrdName  string  `gorm:"type:varchar(100);not null;comment:产品名" json:"prdName"`
	PrdDesc  string  `gorm:"type:varchar(255);not null;comment:产品描述" json:"prdDesc"`
	PrdPrice float64 `gorm:"type:decimal(10,2);not null;comment:产品价格" json:"prdPrice"`
	PrdType  int     `gorm:"type:int;not null;comment:产品类型" json:"prdType"`
}

func (prd *Product) String() string {
	return fmt.Sprintf(
		"Product{ID:%d, PrdName:%s, PrdDesc:%s, PrdPrice:%f, PrdType:%d}",
		prd.ID, prd.PrdName, prd.PrdDesc, prd.PrdPrice, prd.PrdType)
}

type Order struct {
	gorm.Model
	OrdDate   time.Time `gorm:"type:datetime;size:0;comment:订单日期" json:"ordDate"`
	OrdStatus uint8     `gorm:"comment:订单状态" json:"ordStatus"`
	CtmID     uint      `gorm:"type:int;not null;comment:订单客户ID" json:"ctmID"`
	PrdID     uint      `gorm:"type:int;not null;comment:订单产品ID" json:"prdID"`
	PrdNum    int       `gorm:"type:int;not null;comment:订单产品数量" json:"prdNum"`
}

func (ord *Order) String() string {
	return fmt.Sprintf(
		"Order{ID:%d, OrdDate:%s, OrdStatus:%d, CtmID:%d, PrdID:%d, PrdNum:%d}",
		ord.ID, ord.OrdDate, ord.OrdStatus, ord.CtmID, ord.PrdID, ord.PrdNum)
}
