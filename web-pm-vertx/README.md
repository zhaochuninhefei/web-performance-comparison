## 打包成jar包
./mvnw clean package

## 配置文件
conf下面的application.json

## 执行
```bash
java -jar ./target/vertx-test-1.0.0-SNAPSHOT-fat.jar run cn.yjl.vertx.MainVerticle -conf ./conf/application.json
```
