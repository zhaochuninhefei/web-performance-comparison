web-performance-comparison
=====

一个web框架性能比较项目，包括:
- go + gin + gorm
- java + springboot + mybatis
- kotlin + quarkus + hibernate
- kotlin + vertx
- java + vertx
- dotnet
- rust

> 本项目只是个玩票性质的web性能比较，专业的还是去看techempower的测试结果：
> 
> <a href="https://www.techempower.com/benchmarks" target="_blank">https://www.techempower.com/benchmarks</a>


# 性能比较思路
分别使用golang、java以及kotlin的流行web框架开发简单的HttpAPI应用，使用JMeter比较它们的性能表现。

语言、框架与工具及其版本信息如下：
- golang版本 : v1.17.5
- gin版本 : v1.8.1
- gorm版本 : v1.24.2
- java版本 : openJDK-17.0.3
- springboot版本 : 3.0.0
- mybatis-spring-boot-starter版本 : 3.0.1
- kotlin版本 : 1.7.21
- quarkus版本 : 2.15.1.Final
- hibernate版本 : 5.6.14.Final
- vertx版本 : 4.3.7
- JMeter版本 : 5.1.1
- dotnet版本 : 6.0.404
- rust版本 : cargo 1.66.0

# 目录说明
```
web-performance-comparison
├── db                  数据库相关资源
├── deploy              docker编排文件目录
├── jmeter              JMeter测试计划与结果
├── web-pm-dotnet       dotnet WEB测试项目
├── web-pm-gin          go+gin+gorm WEB测试项目
├── web-pm-quarkus      kotlin+quarkus+hibernate WEB测试项目
├── web-pm-rust         rust WEB测试项目
├── web-pm-springboot   java+springboot+mybatis WEB测试项目
├── web-pm-vertx        kotlin+vertx WEB测试项目
└── web-pm-vertx-java   java+vertx WEB测试项目
```

# 性能测试报告

具体的性能测试报告参考:

<a href="./性能测试报告.md" target="_blank">性能测试报告</a>


# 感谢

感谢<a href="https://github.com/aaavieri" target="_blank">aaavieri</a>提供了以下web项目的实现:

- `web-pm-dotnet`
- `web-pm-quarkus`
- `web-pm-rust`
- `web-pm-vertx`
- `web-pm-vertx-java`


# JetBrains support
Thanks to JetBrains for supporting open source projects.

https://jb.gg/OpenSourceSupport.
