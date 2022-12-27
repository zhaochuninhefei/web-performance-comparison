# 1. jar包方式运行
### 1.1 打包成fat jar包
./mvnw clean package -Pjar

### 1.2 配置文件
conf下面的application.json

### 1.3 执行
```bash
java -jar ./target/web-pm-vertx-0.0.1-fat.jar run cn.yjl.vertx.MainVerticle -conf ./conf/application.json
```

# 2.打包成原生方式运行
### 2.1 安装graalvm，[下载地址](https://github.com/graalvm/graalvm-ce-builds/releases/tag/vm-22.3.0)

### 2.2 设置JAVA_HOME和GRAALVM_HOME两个环境变量(值相同)
```bash
export GRAALVM_HOME=/Users/jiaolongyin/Documents/devTools/graalvm-ce-java17-22.3.0/Contents/Home
export JAVA_HOME=/Users/jiaolongyin/Documents/devTools/graalvm-ce-java17-22.3.0/Contents/Home
```

### 2.3 安装native-image
```bash
gu install native-image
```

### 2.4 打包成native
./mvnw clean package -Pnative

### 2.5 执行
```bash
./target/web-pm-vertx run cn.yjl.vertx.MainVerticle -conf ./conf/application.json
```
