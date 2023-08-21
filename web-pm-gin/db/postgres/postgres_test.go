package postgres

import (
	"gitee.com/zhaochuninhefei/zcgolog/zclog"
	"github.com/zhaochuninhefei/web-performance-comparison/web-pm-gin/db/model"
	"gorm.io/gorm"
	"testing"
	"time"
)

func TestConnectPostgresByDefault(t *testing.T) {
	type args struct {
		host   string
		port   string
		user   string
		pass   string
		dbname string
	}
	tests := []struct {
		name    string
		args    args
		want    *gorm.DB
		wantErr bool
	}{
		// TODO: Add test cases.
		{
			name: "ConnectPostgresByDefault",
			args: args{
				host:   "127.0.0.1",
				port:   "5432",
				user:   "zhaochun1",
				pass:   "zhaochun@GITHUB",
				dbname: "db_web_pm",
			},
			want:    nil,
			wantErr: true,
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			db, err := ConnectPostgresByDefault(tt.args.host, tt.args.port, tt.args.user, tt.args.pass, tt.args.dbname)
			if err != nil {
				t.Fatal(err)
			}
			// 查询accounts表数据
			var accounts []model.Account
			err = db.Find(&accounts).Error
			if err != nil {
				t.Fatal(err)
			}
			t.Log(accounts)
		})
	}
}

func TestTruncateTable(t *testing.T) {
	mysqlClient, err := ConnectPostgresByDefault("localhost", "5432", "zhaochun1", "zhaochun@GITHUB", "db_web_pm")
	if err != nil {
		zclog.Errorln(err)
		return
	}
	//goland:noinspection SqlDialectInspection,SqlNoDataSourceInspection
	mysqlClient.Exec("TRUNCATE TABLE accounts restart identity CASCADE")

}

func TestInsert1000(t *testing.T) {
	mysqlClient, err := ConnectPostgresByDefault("localhost", "5432", "zhaochun1", "zhaochun@GITHUB", "db_web_pm")
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
