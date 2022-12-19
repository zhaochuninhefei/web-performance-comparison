web-pm-go
=====

`web-pm-go`是一个使用gin开发的web项目，用于web框架性能比较测试。

# 项目结构
`web-pm-go`的项目结构目前如下所示:

```
web-pm-go
├── build_docker.sh // docker编译脚本
├── config // 应用配置包
├── Dockerfile // docker编译文件
├── generator // 代码生成包
├── global // 全局配置包
├── go.mod // 应用依赖配置
├── handler // web处理器包
├── initialize // 初始化处理包
├── logs // 日志文件目录
├── main.go // 应用启动入口
├── readme.md
├── route // web路由器包
├── start.sh // 应用启动脚本
└── utils // 通用处理包
```

# 本地运行
本地安装好go环境后，可直接本地运行:
```shell
go run main.go
```

# 编译
在当前目录下执行脚本`build_docker.sh`即可完成二进制可执行文件的编译以及docker镜像的编译。

1. 二进制可执行文件的编译会在当前目录下生成`web-pm-go`文件
2. docker编译根据`Dockerfile`编译出docker镜像文件
3. 最终容器启动时，使用的是`start.sh`启动脚本


# 容器启动
使用`docker run`命令启动容器:
```shell
# 使用本地镜像
img_web_pm_go=web-pm-go:0.0.1
# 使用仓库镜像
img_web_pm_go=172.17.4.86:5000/web-pm-go:0.0.1

# 启动web-pm-go
docker run -it --name web-pm-go \
  -p 18080:18080 \
  -p 9300:9300 \
  -v /data/volumes/github.com/zhaochuninhefei/web-performance-comparison/web-pm-go/config:/src/github.com/zhaochuninhefei/web-performance-comparison/web-pm-go/config \
  -v /data/volumes/github.com/zhaochuninhefei/web-performance-comparison/web-pm-go/logs:/src/github.com/zhaochuninhefei/web-performance-comparison/web-pm-go/logs \
  ${img_web_pm_go}

# 退出并停止容器：ctrl + c
# 退出容器但不停止容器: ctrl + p + q

# over
```
其中，
1. 挂载了两个端口到宿主机。`18080`是`web-pm-go`的应用服务端口，`9300`是日志级别控制API的端口。
2. 挂载了两个宿主机目录到容器，分别是`config`和`logs`目录。

