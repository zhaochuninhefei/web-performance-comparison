web-performance-comparison
=====

一个web框架性能比较项目，包括:
- go + gin + gorm
- java + springboot + mybatis
- kotlin + quarkus + hibernate
- kotlin + vertx

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

**以下结论仅针对本次测试的环境与数据:**

在相同的硬件与数据库环境下，同样的高并发压力下，几种web框架都不做任何额外的调优措施的前提下，综合来看目前`go+gin+gorm`的性能表现是最好的。

具体的性能测试报告请参考:

<a href="./性能测试报告.md" target="_blank">性能测试报告</a>

-----------------

`2022/12/28`追记 : 发生以下变更:
- 为了去除数据库服务对应用服务的影响(主要是CPU)，更改了测试环境，将数据库迁移到另一台单独的MySQL服务器上。
- 添加了quarkus与vertx的测试项目(来自<a href="https://github.com/aaavieri" target="_blank">aaavieri</a>的贡献)，为其增加了测试计划，并完成测试。
- 增加了测试期间CPU上下文切换次数的监视。
- go与java项目重命名为gin与springboot。

`2022/12/29`追记 : 增加了`/account/list`接口的部分测试计划。

# 感谢

感谢<a href="https://github.com/aaavieri" target="_blank">aaavieri</a>提供了`web-pm-quarkus`与`web-pm-vertx`项目。


# JetBrains support
Thanks to JetBrains for supporting open source projects.

https://jb.gg/OpenSourceSupport.
