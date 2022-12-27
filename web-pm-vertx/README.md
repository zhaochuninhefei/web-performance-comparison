# 1. jar包方式运行
### 1.1 打包成fat jar包
./mvnw clean package -Pjar

### 1.2 配置文件
conf下面的application.json

### 1.3 执行
```shell
java -jar ./target/web-pm-vertx-0.0.1-fat.jar run cn.yjl.vertx.MainVerticle -conf ./conf/application.json
```

# 2.打包成原生方式运行
### 2.1 安装graalvm
[下载地址](https://github.com/graalvm/graalvm-ce-builds/releases/tag/vm-22.3.0)

### 2.2 设置GRAALVM_HOME环境变量
```shell
export GRAALVM_HOME=/opt/graalvm-ce-java17-22.3.0
```

### 2.3 安装native-image
```shell
# 临时设置JAVA_HOME为GRAALVM_HOME
export JAVA_HOME=/opt/graalvm-ce-java17-22.3.0
${JAVA_HOME}/bin/gu install native-image
```

### 2.4 打包成native
```shell
# 临时设置JAVA_HOME为GRAALVM_HOME
export JAVA_HOME=/opt/graalvm-ce-java17-22.3.0
mvn clean package -Pnative
```


### 2.5 执行
```shell
./target/web-pm-vertx run cn.yjl.vertx.MainVerticle -conf ./conf/application.json
```
