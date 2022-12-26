## 打包说明

```
# 开发模式启动
mvn quarkus:dev -Dsmallrye.config.locations=./external.yml
# 用java开启remote debug，连接端口为本机的5005

# fat-jar方式打包、启动
# 在pom.xml中删除quarkus-container-image-docker依赖
mvn package -Dquarkus.package.type=uber-jar
java -jar ./build/qingting-1.0.0-SNAPSHOT-runner.jar -Dsmallrye.config.locations=./external.yml

# native-image方式打包、启动
# 在pom.xml中删除quarkus-container-image-docker依赖
mvn package -Dquarkus.package.type=native
./target/qingting-1.0.0-SNAPSHOT-runner -Dsmallrye.config.locations=./external.yml

# 打包为Docker(可能有的机器需要指定内存大小才能运行)
# 在pom.xml中添加quarkus-container-image-docker依赖
mvn package -Dmaven.test.skip=true -Dquarkus.package.type=native -Dquarkus.container-image.build=true
```

## 配置说明
不用说明了吧

## docker-compose.yml的例子
