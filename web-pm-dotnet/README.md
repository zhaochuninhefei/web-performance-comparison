## 这是基于 dotnet6.x 开发的 web 服务，运行和打包方式如下:

### 1.1 安装 dotnet6.x。[SDK 下载网址](https://dotnet.microsoft.com/zh-cn/download/dotnet/6.0)，安装 SDK 而不是运行时。

### 1.2 执行打包

- 修改 appsettings.json 里的数据库连接，不放心的话把 appsettings.Development.json 也一起修改了。

```bash
# 如果是其它操作系统，则把linux-x64换成其它，具体可选值范围参考: https://learn.microsoft.com/zh-cn/dotnet/core/rid-catalog
dotnet publish -c release -r linux-x64
```

### 1.3 执行

```bash
cd bin/release/net6.0/linux-x64/publish
# 可以把端口换成其它的
./web-pm-dotnet --urls=http://localhost:8082
```
