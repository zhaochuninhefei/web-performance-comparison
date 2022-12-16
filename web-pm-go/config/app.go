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
	ConfigFile string `yaml:"config_file"`
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

type Log struct {
	Level string `yaml:"level"`
}
