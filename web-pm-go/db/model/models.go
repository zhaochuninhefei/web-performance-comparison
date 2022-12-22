package model

import (
	"database/sql"
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
