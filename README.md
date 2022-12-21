web-performance-comparison
=====

一个web性能比较项目。

# 性能比较思路
分别使用golang和java的最流行的web框架(gin和springboot)开发简单的web应用，使用JMeter比较它们的性能表现。

语言、框架与工具及其版本信息如下：
- golang版本 : v1.17.5
- gin版本 : v1.8.1
- java版本 : openJDK-17.0.3
- springboot版本 : 3.0.0
- JMeter版本 : 5.1.1

# 目录说明
```
web-performance-comparison
├── deploy            docker编排文件目录
├── jmeter            JMeter测试计划与结果
├── web-pm-go         go的web测试项目
└── web-pm-java       java的web测试项目
```

# 性能测试报告
<a href="./性能测试报告.md" target="_blank">性能测试报告</a>

