package config

type AppicationConfig struct {
	App        AppInfo    `yaml:"app"`
	Datasource Datasource `yaml:"datasource"`
	Redis      Redis      `yaml:"redis"`
	Log        Log        `yaml:"log"`
}

type AppInfo struct {
	Name       string `yaml:"name"`
	Version    string `yaml:"version"`
	Port       string `yaml:"port"`
	ConfigFile string `yaml:"configFile"`
}

type Datasource struct {
	Host     string `yaml:"host"`
	Port     string `yaml:"port"`
	User     string `yaml:"user"`
	Password string `yaml:"password"`
}

type Redis struct {
	Host     string `yaml:"host"`
	Port     string `yaml:"port"`
	User     string `yaml:"user"`
	Password string `yaml:"password"`
}

//type LogConfig struct {
//	Dir123 string `yaml:"dir123"`
//	//LogFileDir string `yaml:"dir"`
//	Dir string `yaml:"dir"`
//}

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

//type LogSettings struct {
//	Test   string `yaml:"test"`
//	LogDir string `yaml:"logdir"`
//}
