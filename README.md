web-performance-comparison
=====

一个web性能比较项目。

# 性能比较思路
分别使用golang和java的流行web框架开发简单的HttpAPI应用，使用JMeter比较它们的性能表现。
- golang采用`gin + gorm`
- java采用`springboot + mybatis`

语言、框架与工具及其版本信息如下：
- golang版本 : v1.17.5
- gin版本 : v1.8.1
- gorm版本 : v1.24.2
- java版本 : openJDK-17.0.3
- springboot版本 : 3.0.0
- mybatis-spring-boot-starter版本 : 3.0.1
- JMeter版本 : 5.1.1

# 目录说明
```
web-performance-comparison
├── db                数据库相关资源
├── deploy            docker编排文件目录
├── jmeter            JMeter测试计划与结果
├── web-pm-go         go的web测试项目
└── web-pm-java       java的web测试项目
```

# 性能测试报告
在相同的硬件与数据库环境下，同样的高并发压力下，`go+gin+gorm`的性能表现明显好于`java+springboot+mybatis`的性能表现。
> 也许可以这样描述：如果将并发压力简化为一个正整数`M`，硬件资源简化为另一个正整数`N`，那么`M/N`的值越大，`go+gin+gorm`相比`java+springboot+mybatis`的优势就越明显。

这应该主要是由于golang的goroutine相比java的多线程，能使用更少的CPU提供更高的并发能力。

具体的性能测试报告参考:

<a href="./性能测试报告.md" target="_blank">性能测试报告</a>

