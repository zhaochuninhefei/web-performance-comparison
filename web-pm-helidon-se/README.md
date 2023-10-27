# 0. 必须在java21以上版本执行

# 1. jar包方式运行
### 1.1 打包成fat jar包
./mvnw clean package

### 1.2 配置文件(数据库配置)
src/main/resources/application.yaml

### 1.3 执行
```shell
# 请自己选择垃圾回收器
java -jar ./target/web-pm-helidon-se.jar
```

# 2.打包成原生方式运行
### 2.1 安装graalvm(java版本是21)，下面链接是linux版本，如果是其它系统请在[graal主页](https://www.graalvm.org/downloads/)查找
[下载地址](https://download.oracle.com/graalvm/21/latest/graalvm-jdk-21_linux-x64_bin.tar.gz)
### 2.2 设置GRAALVM_HOME环境变量
```shell
export GRAALVM_HOME=/opt/graalvm-jdk-21+35.1
```

### 2.3 安装native-image
```shell
# 临时设置JAVA_HOME为GRAALVM_HOME
export JAVA_HOME=/opt/graalvm-jdk-21+35.1
${JAVA_HOME}/bin/gu install native-image
```

### 2.4 打包成native
```shell
# 临时设置JAVA_HOME为GRAALVM_HOME
export JAVA_HOME=/opt/graalvm-jdk-21+35.1
mvn clean package -Pnative-image
```


### 2.5 执行
```shell
# 打包，好像执行的时候会出错
./target/web-pm-helidon-se
```