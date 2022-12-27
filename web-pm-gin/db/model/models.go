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
