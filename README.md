web-performance-comparison
=====

一个web框架性能比较项目，包括:
- go + gin + gorm
- java + springboot + mybatis
- kotlin + quarkus + hibernate
- kotlin + vertx

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

# 目录说明
```
web-performance-comparison
├── db                  数据库相关资源
├── deploy              docker编排文件目录
├── jmeter              JMeter测试计划与结果
├── web-pm-gin          go+gin+gorm WEB测试项目
├── web-pm-quarkus      kotlin+quarkus+hibernate WEB测试项目
├── web-pm-springboot   java+springboot+mybatis WEB测试项目
└── web-pm-vertx        kotlin+vertx WEB测试项目
```

# 性能测试报告
在相同的硬件与数据库环境下，同样的高并发压力下，`go+gin+gorm`的性能表现明显好于`java+springboot+mybatis`的性能表现。
> 也许可以这样描述：如果将并发压力简化为一个正整数`M`，硬件资源简化为另一个正整数`N`，那么`M/N`的值越大，`go+gin+gorm`相比`java+springboot+mybatis`的优势就越明显。

这应该主要是由于golang的goroutine相比java的多线程，能使用更少的CPU提供更高的并发能力。

具体的性能测试报告参考:

<a href="./性能测试报告.md" target="_blank">性能测试报告</a>


# 感谢

感谢<a href="https://github.com/aaavieri" target="_blank">aaavieri</a>提供了`web-pm-quarkus`与`web-pm-vertx`的代码。


# JetBrains support
Thanks to JetBrains for supporting open source projects.

https://jb.gg/OpenSourceSupport.
