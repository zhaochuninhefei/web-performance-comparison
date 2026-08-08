

web-performance-comparison
=====

A web framework performance comparison project, including:
- go + gin + gorm
- java + springboot + mybatis
- kotlin + quarkus + hibernate
- kotlin + vertx
- dotnet
- rust

> This project is merely a casual web performance comparison. For professional results, please refer to the TechEmpower benchmarks:
> 
> <a href="https://www.techempower.com/benchmarks" target="_blank">https://www.techempower.com/benchmarks</a>


# Performance Comparison Methodology

Simple HTTP API applications are developed using popular web frameworks for Golang, Java, and Kotlin, as well as .NET and Rust. Their performance is then compared using JMeter.

- All projects uniformly use MySQL 8 and share the identical database and table schema.
- The HTTP APIs implement four identical endpoints: single-row insertion, full-table query (1000 rows), primary key lookup (1000 rows), and a simple in-memory data retrieval API with no database read/write operations.

# Languages, Frameworks, Tools, and Versions

The languages, frameworks, tools, and their versions are as follows:
- golang version: v1.17.5
- gin version: v1.8.1
- gorm version: v1.24.2
- java version: openJDK-17.0.3
- springboot version: 3.0.0
- mybatis-spring-boot-starter version: 3.0.1
- kotlin version: 1.7.21
- quarkus version: 2.15.1.Final
- hibernate version: 5.6.14.Final
- vertx version: 4.3.7
- JMeter version: 5.1.1
- dotnet version: 6.0.404
- rust version: 1.66.0

# Directory Structure
```
web-performance-comparison
├── db                  Database-related resources
├── deploy              Docker compose files directory
├── jmeter              JMeter test plans and results
├── web-pm-dotnet       .NET Web test project
├── web-pm-gin          Go + Gin + GORM Web test project
├── web-pm-quarkus      Kotlin + Quarkus + Hibernate Web test project
├── web-pm-rust         Rust Web test project
├── web-pm-springboot   Java + Spring Boot + MyBatis Web test project
└── web-pm-vertx        Kotlin + Vert.x Web test project
```

# Performance Test Report

For the detailed performance test report, please refer to:

<a href="./性能测试报告.md" target="_blank">Performance Test Report</a>


# Acknowledgments

Thanks to <a href="https://github.com/aaavieri" target="_blank">aaavieri</a> for providing the implementations of the following web projects:

- `web-pm-dotnet`
- `web-pm-quarkus`
- `web-pm-rust`
- `web-pm-vertx`
- `web-pm-vertx-java`


# JetBrains support
Thanks to JetBrains for supporting open source projects.

https://jb.gg/OpenSourceSupport.
