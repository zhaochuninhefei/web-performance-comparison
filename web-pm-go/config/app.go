package config

// app.go 应用配置结构体定义。
// 注意，本项目采用`github.com/spf13/viper`读取yaml配置文件，字段名不支持下划线风格。

// AppicationConfig 应用配置
type AppicationConfig struct {
	// 应用信息
	App AppInfo `yaml:"app"`
	// 数据源配置
	Datasource Datasource `yaml:"datasource"`
	// Redis配置
	Redis Redis `yaml:"redis"`
	// 日志配置
	Log Log `yaml:"log"`
}

// AppInfo 应用信息
type AppInfo struct {
	// 应用名称
	Name string `yaml:"name"`
	// 应用版本
	Version string `yaml:"version"`
	// 服务端口
	Port string `yaml:"port"`
	// 配置文件路径
	ConfigFile string `yaml:"configFile"`
}

// Datasource 数据源配置
type Datasource struct {
	// 数据库域名
	Host string `yaml:"host"`
	// 数据库端口
	Port string `yaml:"port"`
	// 数据库用户
	User string `yaml:"user"`
	// 数据库密码
	Password string `yaml:"password"`
}

// Redis Redis配置
type Redis struct {
	// Redis域名
	Host string `yaml:"host"`
	// Redis端口
	Port string `yaml:"port"`
	// Redis用户
	User string `yaml:"user"`
	// Redis密码
	Password string `yaml:"password"`
}

// Log 日志配置
type Log struct {
	// 是否需要禁止输出到控制台，默认: false
	LogForbidStdout bool `yaml:"logForbidStdout"`
	// 日志文件目录，默认: 空，此时日志只输出到控制台
	LogFileDir string `yaml:"logFileDir"`
	// 日志文件名前缀，默认: zcgolog
	LogFileNamePrefix string `yaml:"logFileNamePrefix"`
	// 日志文件大小上限，单位M，默认: 2
	LogFileMaxSizeM int `yaml:"logFileMaxSizeM"`
	// 全局日志级别，默认:INFO
	LogLevelGlobal string `yaml:"logLevelGlobal"`
	// 日志格式，默认: "%datetime %level %file %line %func %msg"，目前格式固定，该配置暂时没有使用
	LogLineFormat string `yaml:"logLineFormat"`
	// 日志模式，默认采用本地模式，以便于本地测试
	LogMod int `yaml:"logMod"`
	// 日志缓冲通道容量，默认 4096
	LogChannelCap int `yaml:"logChannelCap"`
	// 日志缓冲通道填满后处理策略，默认:LOG_CHN_OVER_POLICY_DISCARD 丢弃该条日志
	LogChnOverPolicy int `yaml:"logChnOverPolicy"`
	// 日志级别控制监听服务的Host，默认:""
	LogLevelCtlHost string `yaml:"logLevelCtlHost"`
	// 日志级别控制监听服务的Port，默认:9300
	LogLevelCtlPort string `yaml:"logLevelCtlPort"`
}
