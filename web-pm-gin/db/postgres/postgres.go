package postgres

import (
	"fmt"
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

// ConnectPostgresByDefault Postgres默认连接
func ConnectPostgresByDefault(host, port, user, pass, dbname string) (*gorm.DB, error) {
	dsn := fmt.Sprintf("host=%s user=%s password=%s dbname=%s port=%s sslmode=disable TimeZone=Asia/Shanghai",
		host, user, pass, dbname, port)
	return gorm.Open(postgres.Open(dsn), &gorm.Config{})
}
