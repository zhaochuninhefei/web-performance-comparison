web-pm-java
=====

`web-pm-java`是一个使用`springboot + mybatis`开发的web项目，用于web框架性能比较测试。

# 项目结构
`web-pm-java`的项目结构目前如下所示:

```
web-pm-java
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── zhaochuninhefei
│   │   │           └── webpmjava
│   │   │               ├── App.java
│   │   │               ├── controller
│   │   │               ├── db
│   │   │               ├── dto
│   │   │               └── service
│   │   └── resources
│   │       └── application.yaml
│   └── test
│       └── java
└── pom.xml
```

其中项目配置文件是`web-pm-java/src/main/resources/application.yaml`，目前项目的端口配置为`18081`。

# API一览
目前提供以下HttpAPI:
- GET    /asset/list               --> AssetController.queryAllAssets
- GET    /asset/query              --> AssetController.queryAssetById
- POST   /asset/modify             --> AssetController.modifyAsset
- GET    /account/list             --> AccountController.listAllAccounts
- GET    /account/query            --> AccountController.queryAccountByID
- POST   /account/add              --> AccountController.addNewAccount

其中，`/asset/*`是单纯的HttpAPI，没有任何读写数据库或其他磁盘IO操作，`/account/*`是单表读写操作的API。

# 本地运行
本地安装好openjdk17与maven环境后，可直接本地运行:
```shell
mvn clean install package
```

服务启动后，可访问httpAPI，例如:
```shell
curl http://localhost:18081/asset/list
```

# 编译
在当前目录下执行脚本`docker_build_local.sh`即可完成docker镜像的编译。
> 注意要先执行maven编译，得到springboot的fat jar


# 容器启动
使用`docker run`命令启动容器:
```shell
# 使用本地镜像
img_web_pm_java=web-pm-java:0.0.1-SNAPSHOT
# 使用仓库镜像
img_web_pm_java=172.17.4.86:5000/web-pm-java:0.0.1-SNAPSHOT

# 启动web-pm-java
docker run -it --name web-pm-java \
  -p 18081:18081 \
  ${img_web_pm_java}

# 退出并停止容器：ctrl + c
# 退出容器但不停止容器: ctrl + p + q

# over
```