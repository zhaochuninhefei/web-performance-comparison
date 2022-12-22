package model

import (
	"database/sql"
	"fmt"
	"gorm.io/gorm"
)

type Account struct {
	gorm.Model
	ActName         string       `gorm:"type:varchar(100);not null;unique;comment:帐户名"`
	ActPwd          string       `gorm:"type:varchar(255);not null;comment:帐户密码"`
	ActNickName     string       `gorm:"type:varchar(255);comment:帐户昵称"`
	ActIntroduction string       `gorm:"type:varchar(255);comment:帐户介绍"`
	ActStatus       uint8        `gorm:"comment:帐户状态"`
	ActRegisterDate sql.NullTime `gorm:"comment:帐户注册时间"`
}

func (act *Account) String() string {
	return fmt.Sprintf(
		"Account{ID:%d, ActName:%s, ActPwd:%s, ActNickName:%s, ActIntroduction:%s, ActStatus:%d, ActRegisterDate:%s}",
		act.ID, act.ActName, act.ActPwd, act.ActNickName, act.ActIntroduction, act.ActStatus, act.ActRegisterDate.Time)
}
