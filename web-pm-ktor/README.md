# web-pm-ktor
### 1.1 打包方式
```bash
# 普通打包
./gradlew clean build
# 如果有时候打包有问题要刷新依赖
./gradlew clean build --refresh-dependencies
```


### 1.2 配置文件
根目录下的application.yaml

### 1.3 执行
```bash
# release方式启动
./build/bin/native/releaseExecutable/web-pm-ktor.kexe
# debug方式启动
./build/bin/native/debugExecutable/web-pm-ktor.kexe
```

### 1.4 相关文档
- [Ktor官方文档](https://ktor.io/docs/home.html)
- [sqlx4k](https://github.com/smyrgeorge/sqlx4k)