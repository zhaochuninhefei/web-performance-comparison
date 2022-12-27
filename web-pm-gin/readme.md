web-pm-gin
=====

`web-pm-gin`是一个使用`gin+gorm`开发的web项目，用于web框架性能比较测试。

# 项目结构
`web-pm-gin`的项目结构目前如下所示:

```
web-pm-gin
├── config        // 应用配置包
├── db            // 数据库连接与数据模型
├── generator     // 代码生成包
├── global        // 全局配置包
├── handler       // web处理器包
├── initialize    // 初始化处理包
├── route         // web路由器包
├── utils         // 通用处理包
└── main.go       // 应用启动入口
```

其中项目配置文件是`config/app.yaml`，目前项目的端口配置为`18080`。

# API一览
目前提供以下HttpAPI:
- GET    /asset/list               --> /handler.QueryAllAssets
- GET    /asset/query              --> /handler.QueryAssetByID
- POST   /asset/modify             --> /handler.ModifyAsset
- GET    /account/list             --> /handler.QueryAllAccounts
- GET    /account/query            --> /handler.QueryAccountByID
- POST   /account/add              --> /handler.AddNewAccount

其中，`/asset/*`是单纯的HttpAPI，没有任何读写数据库或其他磁盘IO操作，`/account/*`是单表读写操作的API。

# 本地运行
本地安装好go环境后，可直接本地运行:
```shell
go run main.go
```

服务启动后，可访问httpAPI，例如:
```shell
curl http://localhost:18080/asset/list
```

# 编译
在当前目录下执行脚本`build_docker.sh`即可完成二进制可执行文件的编译以及docker镜像的编译。

1. 二进制可执行文件的编译会在当前目录下生成`web-pm-gin`文件
2. docker编译根据`Dockerfile`编译出docker镜像文件
3. 最终容器启动时，使用的是`start.sh`启动脚本


# 容器启动
使用`docker run`命令启动容器:
```shell
# 使用本地镜像
img_web_pm_gin=web-pm-gin:0.0.1
# 使用仓库镜像
img_web_pm_gin=172.17.4.86:5000/web-pm-gin:0.0.1

# 启动web-pm-gin
docker run -it --name web-pm-gin \
  -p 18080:18080 \
  -p 9300:9300 \
  -v /data/volumes/github.com/zhaochuninhefei/web-performance-comparison/web-pm-gin/logs:/src/github.com/zhaochuninhefei/web-performance-comparison/web-pm-gin/logs \
  ${img_web_pm_gin}

# 退出并停止容器：ctrl + c
# 退出容器但不停止容器: ctrl + p + q

# over
```
其中，
1. 挂载了两个端口到宿主机。`18080`是`web-pm-gin`的应用服务端口，`9300`是日志级别控制API的端口。
2. 挂载了两个宿主机目录到容器，分别是`config`和`logs`目录。

