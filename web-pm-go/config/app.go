package config

// app.go 应用配置结构体定义。
// 注意，本项目采用`github.com/spf13/viper`读取yaml配置文件，可使用环境变量覆盖配置文件中的对应值。
// 环境变量名为yaml各层名称转大写后用"_"连接。
// 例如，"LOG_LOG_CHANNEL_CAP"表示如下的yaml配置:
// log:
//   log_channel_cap: "4096"

// AppicationConfig 应用配置
type AppicationConfig struct {
	// 应用信息
	App AppInfo `yaml:"app"`
	// 数据源配置
	Datasource Datasource `yaml:"datasource" mapstructure:"datasource"`
	// 数据库连接池配置
	DBConnPool DBConnPool `yaml:"db_conn_pool" mapstructure:"db_conn_pool"`
	// Redis配置
	Redis Redis `yaml:"redis" mapstructure:"redis"`
	// 日志配置
	Log Log `yaml:"log" mapstructure:"log"`
}

// AppInfo 应用信息
type AppInfo struct {
	// 应用名称
	Name string `yaml:"name" mapstructure:"name"`
	// 应用版本
	Version string `yaml:"version" mapstructure:"version"`
	// 服务端口
	Port string `yaml:"port" mapstructure:"port"`
	// 配置文件路径
	ConfigFile string `yaml:"config_file" mapstructure:"config_file"`
}

// Datasource 数据源配置
type Datasource struct {
	// 数据库类型
	DBType string `yaml:"db_type" mapstructure:"db_type"`
	// 数据库域名
	Host string `yaml:"host" mapstructure:"host"`
	// 数据库端口
	Port string `yaml:"port" mapstructure:"port"`
	// 数据库名
	DBName string `yaml:"db_name" mapstructure:"db_name"`
	// 数据库用户
	User string `yaml:"user" mapstructure:"user"`
	// 数据库密码
	Password string `yaml:"password" mapstructure:"password"`
}

// DBConnPool 数据库连接池配置
type DBConnPool struct {
	// 最大连接数
	MaxOpenConns int `yaml:"max_open_conns" mapstructure:"max_open_conns"`
	// 最大空闲连接数
	MaxIdleConns int `yaml:"max_idle_conns" mapstructure:"max_idle_conns"`
	// 连接可复用最大时间(分钟)
	ConnMaxLifetimeMin int64 `yaml:"conn_max_lifetime_min" mapstructure:"conn_max_lifetime_min"`
	// 连接空闲最大时间(分钟)
	ConnMaxIdleTimeMin int64 `yaml:"conn_max_idle_time_min" mapstructure:"conn_max_idle_time_min"`
}

// Redis Redis配置
type Redis struct {
	// Redis域名
	Host string `yaml:"host" mapstructure:"host"`
	// Redis端口
	Port string `yaml:"port" mapstructure:"port"`
	// Redis用户
	User string `yaml:"user" mapstructure:"user"`
	// Redis密码
	Password string `yaml:"password" mapstructure:"password"`
}

// Log 日志配置
type Log struct {
	// 是否需要禁止输出到控制台，默认: false
	LogForbidStdout bool `yaml:"log_forbid_stdout" mapstructure:"log_forbid_stdout"`
	// 日志文件目录，默认: 空，此时日志只输出到控制台
	LogFileDir string `yaml:"log_file_dir" mapstructure:"log_file_dir"`
	// 日志文件名前缀，默认: zcgolog
	LogFileNamePrefix string `yaml:"log_file_name_prefix" mapstructure:"log_file_name_prefix"`
	// 日志文件大小上限，单位M，默认: 2
	LogFileMaxSizeM int `yaml:"log_file_max_size_m" mapstructure:"log_file_max_size_m"`
	// 全局日志级别，默认:INFO
	LogLevelGlobal string `yaml:"log_level_global" mapstructure:"log_level_global"`
	// 日志格式，默认: "%datetime %level %file %line %func %msg"，目前格式固定，该配置暂时没有使用
	LogLineFormat string `yaml:"log_line_format" mapstructure:"log_line_format"`
	// 日志模式，默认采用本地模式，以便于本地测试
	LogMod int `yaml:"log_mod" mapstructure:"log_mod"`
	// 日志缓冲通道容量，默认 4096
	LogChannelCap int `yaml:"log_channel_cap" mapstructure:"log_channel_cap"`
	// 日志缓冲通道填满后处理策略，默认:LOG_CHN_OVER_POLICY_DISCARD 丢弃该条日志
	LogChnOverPolicy int `yaml:"log_chn_over_policy" mapstructure:"log_chn_over_policy"`
	// 日志级别控制监听服务的Host，默认:""
	LogLevelCtlHost string `yaml:"log_level_ctl_host" mapstructure:"log_level_ctl_host"`
	// 日志级别控制监听服务的Port，默认:9300
	LogLevelCtlPort string `yaml:"log_level_ctl_port" mapstructure:"log_level_ctl_port"`
}
